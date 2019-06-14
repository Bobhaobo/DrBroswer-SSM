package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springmvc.dao.AnnoImageMapper;
import org.springmvc.dto.*;
import org.springmvc.dto.Dcm2jpgIOStreamOutput;
import org.springmvc.pojo.AnnoImage;
import org.springmvc.pojo.AnnoSeries;
import org.springmvc.service.*;
import org.springmvc.tool.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/get")
public class AnnoController {

    private File file;
    @Autowired
    private AnnoService annoService;
    @Autowired
    private AnnoPatientService patientService;
    @Autowired
    private AnnoStudyService studyService;
    @Autowired
    private AnnoSeriesService seriesService;
    @Autowired
    private AnnoImageService imageService;
    @Autowired
    private XMLGenerator xmlGenerator;
    @Resource
    private PrimaryKeyGenerator primaryKeyGenerator;
    @Autowired
    private ReadDcmImageTool readDcmImageTool;
    @Autowired
    private AnnoImageMapper annoImageMapper;

    //发送文件
    @RequestMapping(value = "/sendDcm1" ,method = RequestMethod.POST)
    @ResponseBody
    public String sendDcm1(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "url") String imageUrl) throws Exception {
        ResourceBundle resource = ResourceBundle.getBundle("imgWebPath");//test为属性文件名，放在包com.mmq下，如果是放在src下，直接用test即可
        List<List<String>> listList = new ArrayList<List<String>>();
      //List<List<String>> listImageName = readDcmImageTool.getImageSeriesList("D:/dcmtest/DJ20170701B0018");
      List<List<String>> listImageName = readDcmImageTool.getImageSeriesList(imageUrl);
        System.out.println(listImageName);
      for (int i=0;i<listImageName.size();i++){
          List<String> list = new ArrayList<String>();
          for(int j=0;j<listImageName.get(i).size();j++){
            //list.add("http://10.168.1.137:8080/down/DJ20170701B0018/"+listImageName.get(i).get(j) );
              //list.add("http://10.168.1.137:8080/test/T1855634/1.2.840.113704.1.111.6200.1498892502.1/1.2.840.113704.1.111.6200.1498892508.3/"+listImageName.get(i).get(j) );
            listImageName.get(i).set(j,listImageName.get(i).get(j).replace(resource.getString("web.innerImagePath"),resource.getString("web.virtualImagePath")));

          }
          listList.add(list);
      }
      return JSON.toJSONString(listImageName);
      //return JSON.toJSONString(listList, SerializerFeature.DisableCircularReferenceDetect);
    }

    //下载dcm格式的Zip
    @RequestMapping(value = "/sendDcmZip",method = RequestMethod.POST)
    @ResponseBody
    public void sendDcmZip(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="filename") String filename) throws IOException {
        /** 下面为下载zip压缩包相关流程 */
        long start = System.currentTimeMillis();
        String filePath="";
        if(filename.contains("http")){
            ResourceBundle resource = ResourceBundle.getBundle("imgWebPath");//test为属性文件名，放在包com.mmq下，如果是放在src下，直接用test即可
            filePath = filename.replace(resource.getString("web.virtualImagePath"),resource.getString("web.innerImagePath"));
        }else {
            filePath=filename;
        }
        /** 1.创建临时文件夹  */
        File temDir = new File(filePath);
        System.out.println(filePath);
        if(!temDir.exists()){
            temDir.mkdirs();
        }


        /** 3.设置response的header */
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename="+temDir.getName()+".zip");

        /** 4.调用工具类，下载zip压缩包 */
        ZipUtils.toZip(temDir.getPath(), response.getOutputStream(),true);
        long end = System.currentTimeMillis();
        System.out.println("总用时："+(end-start));

        /** 5.删除临时文件和文件夹 */
        // 这里我没写递归，直接就这样删除了
      /*  File[] listFiles = temDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
        }
        temDir.delete();*/

    }

    //下载Jpg格式的Zip
    @RequestMapping(value = "/sendJpgZip",method = RequestMethod.POST)
    @ResponseBody
    public void sendJpgZip(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="filename") String filename) throws IOException {
        /** 下面为下载zip压缩包相关流程 */
        String filePath="";
        ResourceBundle resource = ResourceBundle.getBundle("imgWebPath");//test为属性文件名，放在包com.mmq下，如果是放在src下，直接用test即可
        if(filename.contains("http")){
            filePath = filename.replace(resource.getString("web.virtualImagePath"),resource.getString("web.innerImagePath"));
        }else {
            filePath=filename;
        }
        /** 1.创建临时文件夹  */
        long start = System.currentTimeMillis();
        File temDir = new File(filePath);
        System.out.println(filePath);
        if(!temDir.exists()){
            temDir.mkdirs();
        }
        //判断是否是单张的
        if(temDir.isDirectory()){
            /** .设置response的header */
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename="+temDir.getName()+".zip");

            /** .调用工具类，下载zip压缩包 */
            try {
                ZipUtils.toZip(temDir.getPath(), response.getOutputStream(),true);
            }
            finally {
                /** 5.删除临时文件和文件夹 */
                // 这里我没写递归，直接就这样删除了
                CopyDcmFolders copyDcmFolders = new CopyDcmFolders();
                copyDcmFolders.removeDir(temDir.getParentFile());
            }
        }else {
            try{
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setHeader("Content-disposition", "attachment;filename=" + temDir.getName());
                byte[] buf = new byte[2048];
                int len;
                FileInputStream in = new FileInputStream(temDir);
                while ((len = in.read(buf)) != -1) {
                    response.getOutputStream().write(buf, 0, len);
                }
                in.close();
            }catch (IOException e){
                System.out.println("下载错误");
            }finally {
                CopyDcmFolders copyDcmFolders = new CopyDcmFolders();
                copyDcmFolders.removeDir(temDir.getParentFile());
            }

        }

    }

    //转换DCM图片的格式为JPG
    @RequestMapping(value = "/convertFormat",method = RequestMethod.POST)
    @ResponseBody
    public String convertFormat(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="filename") String filename) throws IOException {
        /** 下面为下载zip压缩包相关流程 */
        String filePath="";
        ResourceBundle resource = ResourceBundle.getBundle("imgWebPath");//test为属性文件名，放在包com.mmq下，如果是放在src下，直接用test即可
        if(filename.contains("http")){
            filePath = filename.replace(resource.getString("web.virtualImagePath"),resource.getString("web.innerImagePath"));
        }else {
            filePath=filename;
        }
        /** 2.创建临时文件夹  */
        long start = System.currentTimeMillis();
        File temDir = new File(resource.getString("web.innerImagePath")+"\\"+UUID.randomUUID());
        System.out.println(filePath);
        if(!temDir.exists()){
            temDir.mkdirs();
        }
        /** 2.进行格式转换*/
        File srcFile = new File(filePath);
        System.out.println("filePath:"+filePath);
        CopyDcmFolders copyDcmFolders = new CopyDcmFolders();
        copyDcmFolders.copyFolder(srcFile,temDir);
        long end = System.currentTimeMillis();
        System.out.println("总用时："+(end-start));
        /** 3.设置response的header */
        //response.setContentType("application/zip");
        //response.setHeader("Content-Disposition", "attachment; filename="+temDir.getName()+".zip");

        /** 4.调用工具类，下载zip压缩包 */
        //ZipUtils.toZip(temDir.getPath(), response.getOutputStream(),true);

        /** 5.删除临时文件和文件夹 */
        // 这里我没写递归，直接就这样删除了
      /*  File[] listFiles = temDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
        }
        temDir.delete();*/
      if(srcFile.isDirectory()){
          return JSON.toJSONString(new File (temDir,srcFile.getName()).getPath());
      }else {
          int index = srcFile.getName().lastIndexOf(".dcm");
          String imgName = srcFile.getName().substring(0,index);
          return JSON.toJSONString(new File (temDir,imgName+".jpg").getPath());
      }

    }

    //发送文件
    @RequestMapping(value = "/sendDcm" , method = RequestMethod.GET)
    @ResponseBody
    public void sendDcm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取下载路径
        String filePath1 = "E:\\2.dcm";
        String filePath2 = "E:\\2.dcm";
        File outfile1 = new File(filePath1);
        File outfile2 = new File(filePath2);
        //获取输入流
        InputStream bis1 = new BufferedInputStream(new FileInputStream(outfile1));
        InputStream bis2 = new BufferedInputStream(new FileInputStream(outfile2));
        byte[] buffer1 = new byte[bis1.available()];
        byte[] buffer2 = new byte[bis2.available()];
        //读取文件流
        bis1.read(buffer1);
        bis2.read(buffer2);

        bis1.close();
        bis2.close();
        //重置结果集
        response.reset();
        ArrayList<byte[]> list = new ArrayList<byte[]>();
        list.add(buffer1);

        //返回头 文件大小
        response.addHeader("Content-Length",""+outfile1.length());
        //设置文件Contentype类型
        response.setContentType("application/octet-stream");
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(buffer1);//输出文件
        out.flush();
        out.close();
    }
    //上传文件
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO uploadFile(@RequestParam("inputFile") MultipartFile file) throws IOException {
        //本地使用，上传位置
        String rootPath = "E:\\upload\\image\\";
        if(!file.isEmpty()) {
            //文件的完整名称
            String filename = file.getOriginalFilename();
            //文件名
            String name = filename.substring(0, filename.lastIndexOf("."));
            //文件后缀
            String suffix = filename.substring(filename.lastIndexOf("."));

            //创建年月文件
            Calendar date = Calendar.getInstance();
            File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
            //目标文件
            File descFile = new File(rootPath + File.separator + dateDirs + File.separator + filename);
            //判断目标文件所在的目录是否存在
            if (!descFile.getParentFile().exists()) {
                descFile.getParentFile().mkdirs();
            }
            //将内存中的数据写入磁盘
            file.transferTo(descFile);
            //完整的url
            String fileUrl = descFile+"";
            //返回的base64编码的字符串
            String base64Url = null;
            try{
                File src = new File(fileUrl);
                Dcm2jpgIOStreamOutput dcm2jpg = new Dcm2jpgIOStreamOutput();
                //,windowWidth,windowCenter
                //返回的base64编码的字符串
                base64Url = dcm2jpg.convert(src);
                System.out.println(base64Url);
            }catch (Exception e) {
                e.printStackTrace();
            }
            //this.file = new File(fileUrl);
            ResultVO resultVO = new ResultVO();
            resultVO.setCode(0);
            resultVO.setMsg("成功");

            UploadFileVO uploadFileVO = new UploadFileVO();
            uploadFileVO.setTitle(filename);
            uploadFileVO.setUrl(base64Url);
            uploadFileVO.setFileUrl(fileUrl);
            resultVO.setData(uploadFileVO);

            return resultVO;
        }
        return null;
    }
   /* @RequestMapping(value="/receiveList",method = RequestMethod.POST)
    @ResponseBody
    public String receiveList1(@RequestParam(value = "xy",required = false*//*,defaultValue = ""*//*) String[] xy ,
                              @RequestParam(value = "fileSrc") String fileSrc , HttpServletRequest request ,
                              @RequestParam(value = "width") String width, @RequestParam(value = "height") String height){
        //判断是否为空
        if (xy == null){
            return "0";
        }
        //获取由前端接收过来的坐标信息
        float[][] xOy = annoService.getCoordinateInfo(xy);
        ReadDcmTagInfo readDcmTagInfo = new ReadDcmTagInfo();
        DateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
        System.out.println("width"+width);
        System.out.println("height"+height);
        try {
            readDcmTagInfo.getTagInfo(fileSrc);
            //向患者信息表中插入信息
            patientService.insertOrUpdatePatient(readDcmTagInfo.getPatientId(), readDcmTagInfo.getPatName()
                    ,myFormat.parse(readDcmTagInfo.getBirth()),readDcmTagInfo.getSex());
            //向检查信息表中插入信息
            studyService.insertOrUpdateStudy(readDcmTagInfo.getStudyUID(),readDcmTagInfo.getPatientId(),
                    readDcmTagInfo.getModality(),myFormat.parse(readDcmTagInfo.getStudyDate()));
            //向序列信息表中插入信息
            seriesService.insertOrUpdateSeries(readDcmTagInfo.getSeriesUID(),readDcmTagInfo.getPatientId(),
                    readDcmTagInfo.getStudyUID());
            //向图像信息表中插入信息
            imageService.insertOrUpdateImage(readDcmTagInfo.getSopUID(),readDcmTagInfo.getPatientId(),
                    readDcmTagInfo.getStudyUID(),readDcmTagInfo.getSeriesUID(),"已标注",
                    readDcmTagInfo.getFolder(),readDcmTagInfo.getFileName(),fileSrc);
            annoService.deleteAnnoInfo(readDcmTagInfo.getSopUID());
            for (int i=0;i<xOy.length;i++) {
                annoService.insertAnnoInfo(primaryKeyGenerator.getAnnoIdN(readDcmTagInfo.getModality()),
                        readDcmTagInfo.getPatientId(), readDcmTagInfo.getStudyUID(), readDcmTagInfo.getSeriesUID()
                        , readDcmTagInfo.getSopUID(), xOy[i][0], xOy[i][1], xOy[i][2], xOy[i][3]);
            }
            //xmlGenerator.createXml(readDcmTagInfo.getFolder(),readDcmTagInfo.getFileName(),fileSrc,width,height,readDcmTagInfo.getPerPixel(),xOy);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "1";

    }*/

    @RequestMapping(value="/receiveList",method = RequestMethod.POST)
    @ResponseBody
    public String receiveList(@RequestParam(value = "xy",required = false/*,defaultValue = ""*/) String[] xy ,
                              @RequestParam(value = "fileSrc") String fileSrc , HttpServletRequest request,
                              @RequestParam(value = "annoInfo") String seriesAnnoInfo){
        //判断是否为空
        if (xy == null){
            return "0";
        }
        ResourceBundle resource = ResourceBundle.getBundle("imgWebPath");
        String imagePath = fileSrc.substring(0,fileSrc.indexOf(";")).replace("wadouri:"+resource.getString("web.virtualImagePath"),resource.getString("web.innerImagePath"));
        //获取由前端接收过来的坐标信息
        int[][] xOy = annoService.getCoordinateInfo(xy);
        ReadDcmTagInfo readDcmTagInfo = new ReadDcmTagInfo();
        DateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            readDcmTagInfo.getTagInfo(imagePath);
            //向患者信息表中插入信息
            patientService.insertOrUpdatePatient(readDcmTagInfo.getPatientId(), readDcmTagInfo.getPatName()
                    ,myFormat.parse(readDcmTagInfo.getBirth()),readDcmTagInfo.getSex());
            //向检查信息表中插入信息
            studyService.insertOrUpdateStudy(readDcmTagInfo.getStudyUID(),readDcmTagInfo.getPatientId(),
                    readDcmTagInfo.getModality(),myFormat.parse(readDcmTagInfo.getStudyDate()));
            //向序列信息表中插入信息
            seriesService.insertOrUpdateSeries(readDcmTagInfo.getSeriesUID(),readDcmTagInfo.getPatientId(),
                    readDcmTagInfo.getStudyUID(),seriesAnnoInfo);
            //向图像信息表中插入信息
            imageService.insertOrUpdateImage(readDcmTagInfo.getSopUID(),readDcmTagInfo.getPatientId(),
                    readDcmTagInfo.getStudyUID(),readDcmTagInfo.getSeriesUID(),"已标注",
                    readDcmTagInfo.getFolder(),readDcmTagInfo.getFileName(),imagePath);
            annoService.deleteAnnoInfo(readDcmTagInfo.getSopUID());
            for (int i=0;i<xOy.length;i++) {
                annoService.insertAnnoInfo(primaryKeyGenerator.getAnnoIdN(readDcmTagInfo.getModality()),
                        readDcmTagInfo.getPatientId(), readDcmTagInfo.getStudyUID(), readDcmTagInfo.getSeriesUID()
                        , readDcmTagInfo.getSopUID(), xOy[i][0], xOy[i][1], xOy[i][2], xOy[i][3],readDcmTagInfo.getWidth(),readDcmTagInfo.getHeight(),readDcmTagInfo.getPerPixel());
            }
            return "1";
            //xmlGenerator.createXml(readDcmTagInfo.getFolder(),readDcmTagInfo.getFileName(),fileSrc,width,height,readDcmTagInfo.getPerPixel(),xOy);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "0";

    }

    @RequestMapping(value = "/loadAnno" ,method = RequestMethod.POST)
    @ResponseBody
    public String loadAnnoInfo (@RequestParam(value = "fileSrc") String fileSrc ,HttpServletRequest request,HttpServletResponse response){
        ResourceBundle resource = ResourceBundle.getBundle("imgWebPath");
        String imagePath = fileSrc.substring(0,fileSrc.indexOf(";")).replace("wadouri:"+resource.getString("web.virtualImagePath"),resource.getString("web.innerImagePath"));
        ReadDcmTagInfo readDcmTagInfo = new ReadDcmTagInfo();
        try {
            readDcmTagInfo.getTagInfo(imagePath);

            //向序列信息表中插入信息
            AnnoSeries annoSeries = seriesService.getById(readDcmTagInfo.getSeriesUID());
            if (annoSeries == null){
                return "0";
            }
            else {
            return JSON.toJSONString(annoSeries.getSeriesAnnoInfo());
            }
            //xmlGenerator.createXml(readDcmTagInfo.getFolder(),readDcmTagInfo.getFileName(),fileSrc,width,height,readDcmTagInfo.getPerPixel(),xOy);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return "0";
    }


    /**
     * @Description: 返回已写报告的分页信息
     * @Author: Shalldid
     * @Date: Created in 16:02 2018-05-07
     * @Return:
     **/
    @RequestMapping(value = "/getAnnoXML")
    @ResponseBody
    public String getAnnoXML(Pagination p, HttpSession httpSession){
        int currIndex = (p.getPageCurrent() - 1) * p.getPageSize();
        int pageSize  = p.getPageSize();
        System.out.println("currIndex:"+currIndex+","+"pageSize:"+pageSize);
        boolean ifFirst = (p.getPageCurrent() == 1);    //如果当前页为1则是第一页
        int totalRow = annoImageMapper.getCountByAnnoFlag("已标注"); //得到未安排预约的总数量；
//        int totalRow = regInfoService.countCheckListByFlag("已写报告"); //得到未安排预约的总数量；
        System.out.println(totalRow);
        boolean ifLast = ((currIndex + pageSize) <= totalRow) ? true : false;   //当前数据index加上pageSize是否小于等于总数量，若是则为最后一页
        int totalPage = (totalRow % pageSize) == 0 ? (totalRow / pageSize) : ((totalRow / pageSize) + 1);
        try {
            List<AnnoImage> annoImages = annoImageMapper.selectAllByAnnoFlag("已标注");
            PaginationResult<AnnoImage> paginationResult = new PaginationResult();
            paginationResult.setFirstPage(ifFirst);
            paginationResult.setLastPage(ifLast);
            paginationResult.setList(annoImages);
            paginationResult.setPageNumber(p.getPageCurrent());
            paginationResult.setTotalRow(totalRow);
            paginationResult.setTotalPage(totalPage);
            paginationResult.setPageSize(pageSize);
            System.out.println(JSON.toJSONString(paginationResult));
            return JSON.toJSONString(paginationResult);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
