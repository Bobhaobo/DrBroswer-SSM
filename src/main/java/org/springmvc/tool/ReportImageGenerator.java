package org.springmvc.tool;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportImageGenerator {
    /**
     * get empty BufferImage
     */

    @Resource
    private ImageAndReportPathGenerator imageAndReportPathGenerator;

    public static BufferedImage initImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public static void textToTypesetting(Graphics2D g, String str, int row) {
        List<String> list = new ArrayList<String>();
        int textWidth = 33;
        int val = 1;
        while(str.length() >= textWidth) {
            if(val == 1) {
                list.add(str.substring(0, textWidth - 3));
                str = str.substring(textWidth,str.length());
                val++;
            }else {
                list.add(str.substring(0, textWidth));
                str = str.substring(textWidth,str.length());
            }
        }
        list.add(str);
        String[] strings = new String[list.size()];
        list.toArray(strings);
        for(int i = 0; i < strings.length; i++) {
            if(i == 0) {
                g.drawString(strings[i], 95, row + i * 25);
            }else {
                g.drawString(strings[i], 53, row + i * 25);
            }
        }
    }

    public static BufferedImage setText(String title,String examItemName,String name,String gender,String age,String checkNum,String deptName,String clinicId,
                                         String bedNo,String jcbw,String examDosc,String examDiagnosis,String reprotDoc,String checkDoc,String date, String redcross) throws IOException {
        BufferedImage bi = initImage(793, 1122);
        BufferedImage icon = ImageIO.read(new File(redcross));
        Graphics2D g = bi.createGraphics();
        g.setColor(new Color(250,250, 250));
        g.fillRect(0, 0, 793, 1122);
        g.drawImage(icon,93,45,55,55,null);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("微软雅黑", Font.BOLD, 40));
        g.setColor(new Color(0, 0, 0));
        g.drawString(title, 270, 70);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        g.drawString(examItemName + "检查报告单", 335, 115);
        Line2D line2d = new Line2D.Float(40,151,756,151);
        g.draw(line2d);
        line2d.setLine(40, 287, 756, 287);
        g.draw(line2d);
        line2d.setLine(40, 1047, 756, 1047);
        g.draw(line2d);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        g.drawString("姓名：" + name, 53, 180);
        g.drawString("性别：" + gender, 237, 180);
        g.drawString("年龄：" + age, 375, 180);
        g.drawString("检查号：" + checkNum, 530, 180);
        g.drawString("科室：" + deptName, 53, 220);
        g.drawString("门诊/住院号：" + clinicId, 260, 220);
        g.drawString("床号：" + bedNo, 551, 220);
        g.drawString("检查部位：" + jcbw, 53, 260);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        g.drawString("影像表现：", 53, 315);
        textToTypesetting(g, examDosc,350);
        g.drawString("诊断意见：",53,750);
        textToTypesetting(g, examDiagnosis,785);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        g.drawString("报告医师：" + reprotDoc,53,1023);
        g.drawString("审核医师：" + checkDoc,270,1023);
        g.drawString("报告日期：" + date,490,1023);
        g.dispose();
        return bi;
    }

    public String outputReport(String checkNum,String title,String examItemName,String name,String gender,String age,String deptName,String clinicId,
                               String bedNo,String jcbw,String examDosc,String examDiagnosis,String reprotDoc,String checkDoc,String date) throws IOException {
        BufferedImage bi = setText(title,examItemName,name,gender,age,checkNum,deptName,clinicId,
                bedNo,jcbw,examDosc,examDiagnosis,reprotDoc,checkDoc,date,imageAndReportPathGenerator.getRedcrossPath());
        String report_image_url = imageAndReportPathGenerator.getInnerReportPath(checkNum,"jpg");
        OutputStream os;
        if("0".equals(imageAndReportPathGenerator.getInnerReportUseSMB())){
            File f = new File(report_image_url);
            os = new BufferedOutputStream(new FileOutputStream(f));
        }else{
            SmbFile sf = new SmbFile(report_image_url);
            os = new BufferedOutputStream(new SmbFileOutputStream(sf));
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "JPG", baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        try {
            byte [] buffer =  new byte [ 1024 ];
            while  (is.read(buffer) != - 1 ) {
                os.write(buffer);
                buffer = new byte [ 1024 ];
            }
            return report_image_url;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }finally {
            os.close();
            is.close();
        }
    }
}
