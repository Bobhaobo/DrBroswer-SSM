package org.springmvc.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageAndReportPathGenerator {

    @Value("#{imagePath.innerImagePath}")
    private String innerImagePath;      //院内图像读取服务器地址
    @Value("#{imagePath.innerImageUseSMB}")
    private String innerImageUseSMB;    //院内图像读取是否使用smb,0不使用,1使用
    @Value("#{imagePath.remoteImagePath}")
    private String remoteImagePath;     //远程图像读取服务器地址
    @Value("#{imagePath.remoteImageUseSMB}")
    private String remoteImageUseSMB;   //远程图像读取是否使用smb,0不使用,1使用
    @Value("#{imagePath.innerReportPath}")
    private String innerReportPath;     //院内报告读取服务器地址
    @Value("#{imagePath.innerReportUseSMB}")
    private String innerReportUseSMB;   //院内报告读取是否使用smb,0不使用,1使用
    @Value("#{imagePath.remoteReportPath}")
    private String remoteReportPath;    //远程报告读取服务器地址
    @Value("#{imagePath.remoteReportUseSMB}")
    private String remoteReportUseSMB;  //远程报告读取是否使用smb,0不使用,1使用
    @Value("#{imagePath.redcrossPath}")
    private String redcrossPath;        //生成报告时所用的redcross地址

    public String getRedcrossPath() {
        return redcrossPath;
    }

    /**
     * @Description: 返回院内图像地址，目前不使用smb读取
     * @Author: Shalldid
     * @Date: Created in 10:06 2018-05-04
     * @Return:
     **/
    public String getInnerImagePath(String patientId, String startDate, String orderSource){
        StringBuilder url = new StringBuilder();
        if("1".equals(innerImageUseSMB)){
            url.append("smb:");
        }
        url.append(innerImagePath + "\\" + startDate.substring(0,4) + "-" + startDate.substring(4, 6) + "\\" + startDate.substring(6,8) + "\\"
                + patientId);
        return url.toString();
    }
    /**
     * @Description: 返回远程图像地址，目前不适用smb读取
     * @Author: Shalldid
     * @Date: Created in 10:07 2018-05-04
     * @Return:
     **/
    public String getRemoteImagePath(String Id, String startDate, String orderSource){
        StringBuilder url = new StringBuilder();
        if("1".equals(remoteImageUseSMB)){
            url.append("smb:");
        }
        url.append(remoteImagePath + orderSource + "\\DICOM\\"+ startDate.substring(0,4) + "-" + startDate.substring(4, 6) + "\\" + startDate.substring(6,8) + "\\"
                + Id);
        return url.toString();
    }
    /**
     * @Description: 返回院内报告地址，若在本机则不适用smb读取
     * @Author: Shalldid
     * @Date: Created in 10:07 2018-05-04
     * @Return:
     **/
    public String getInnerReportPath(String checkNum, String imageformat){
        StringBuilder url = new StringBuilder();
        if("1".equals(innerReportUseSMB)){
            url.append("smb:");
        }
        url.append(innerReportPath + checkNum + "." + imageformat);
        return url.toString();
    }
    /**
     * @Description: 返回远程报告地址，若在本机则不适用smb读取
     * @Author: Shalldid
     * @Date: Created in 10:07 2018-05-04
     * @Return:
     **/
    public String getRemoteReportPath(String checkNum, String imageformat){
        StringBuilder url = new StringBuilder();
        if("1".equals(remoteImageUseSMB)){
            url.append("smb:");
        }
        url.append(remoteReportPath + checkNum + "." + imageformat);
        return url.toString();
    }

    public String getInnerReportUseSMB() {
        return innerReportUseSMB;
    }

    public String getRemoteReportUseSMB() {
        return remoteReportUseSMB;
    }
}
