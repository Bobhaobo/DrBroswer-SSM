package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springmvc.dto.Dcm2jpgIOStreamOutput;
import org.springmvc.dto.ReadDcmTagInfo;
import org.springmvc.dto.ResultVO;
import org.springmvc.dto.UploadFileVO;
import org.springmvc.service.*;
import org.springmvc.tool.PrimaryKeyGenerator;
import org.springmvc.tool.ReadDcmImageTool;
import org.springmvc.tool.XMLGenerator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    //发送文件
    @RequestMapping(value = "/sendDcm1" , method = RequestMethod.POST)
    @ResponseBody
    public String sendDcm1(HttpServletRequest request,HttpServletResponse response) throws Exception {

      List<List<String>> listList = new ArrayList<List<String>>();
      List<List<String>> listImageName = readDcmImageTool.getImageSeriesList("D:/dcmtest/DJ20170701B0018");

      for (int i=0;i<listImageName.size();i++){
          List<String> list = new ArrayList<String>();
          for(int j=0;j<listImageName.get(i).size();j++){
            list.add("http://10.168.1.137:8080/down/DJ20170701B0018/"+listImageName.get(i).get(j) );
          }
          listList.add(list);
      }
      return JSON.toJSONString(listList);
      //return JSON.toJSONString(listList, SerializerFeature.DisableCircularReferenceDetect);
    }

    //发送文件
    @RequestMapping(value = "/sendDcm" , method = RequestMethod.GET)
    @ResponseBody
    public void sendDcm(HttpServletRequest request,HttpServletResponse response) throws Exception {
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
    public ResultVO uploadFile(@RequestParam("inputFile")MultipartFile file) throws IOException {
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
    @RequestMapping(value="/receiveList",method = RequestMethod.POST)
    @ResponseBody
    public String receiveList(@RequestParam(value = "xy",required = false/*,defaultValue = ""*/) String[] xy ,
                              @RequestParam(value = "fileSrc") String fileSrc ,HttpServletRequest request ,
                              @RequestParam(value = "width") String width,@RequestParam(value = "height") String height){
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
            xmlGenerator.createXml(readDcmTagInfo.getFolder(),readDcmTagInfo.getFileName(),fileSrc,width,height,readDcmTagInfo.getPerPixel(),xOy);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "1";

    }


}
