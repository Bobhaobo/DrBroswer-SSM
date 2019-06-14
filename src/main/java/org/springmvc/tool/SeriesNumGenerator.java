package org.springmvc.tool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springmvc.dao.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@Description: pacs内各种编号生成器
 *@Author: Shalldid
 *@Date:Created in 10:58 2018-04-25
 **/
@Service
public class SeriesNumGenerator {

    private static final Logger logger = Logger.getLogger(SeriesNumGenerator.class);

    @Autowired
    private DicomWorkListMapper dicomWorkListMapper;

    @Autowired
    private RegisterInfoMapper registerInfoMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private RemoteRegisterMapper remoteRegisterMapper;

    @Autowired
    private DicomWorkListLocalMapper dicomWorkListLocalMapper;

    @Autowired
    private RegisterInfoLocalMapper registerInfoLocalMapper;
    /**
     *@Description: 从数据库查询当前worklist表内最大的AccessionN号，若有则在当天的最大号加1，若无则返回检查类型+日期+001
     *@Author: Shalldid
     *@Date:Created in 10:44 2018-04-25
     **/
    public String getLocalAccessionN(String modality){

        Date systemDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(systemDate);
        String param = "R" + modality + date + "___"; //生成规则，检查类型加当前年月日加三位数字
        String accessionN_temp = dicomWorkListLocalMapper.getAccessionN(modality, param);
        if(accessionN_temp != null){
            String tempAccessionNOne = accessionN_temp.substring(0,11);
            String tempAccessionNTwo = accessionN_temp.substring(11,14);
            Integer tempInteger = Integer.parseInt(tempAccessionNTwo) + 1;
            return tempAccessionNOne + String.format("%03d", tempInteger);
        }else {
            return "R" + modality + date + "001";
        }
    }


    public String getLocalAccessionNLocal(String modality){

        Date systemDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(systemDate);
        String param = "R" + modality + date + "___"; //生成规则，检查类型加当前年月日加三位数字
        String accessionN_temp = dicomWorkListLocalMapper.getAccessionNLocal(modality, param);
        if(accessionN_temp != null){
            String tempAccessionNOne = accessionN_temp.substring(0,11);
            String tempAccessionNTwo = accessionN_temp.substring(11,14);
            Integer tempInteger = Integer.parseInt(tempAccessionNTwo) + 1;
            return tempAccessionNOne + String.format("%03d", tempInteger);
        }else {
            return "R" + modality + date + "001";
        }
    }

    /**
     *@Description: 从数据库查询当前worklist表内最大的AccessionN号，若有则在当天的最大号加1，若无则返回检查类型+日期+001
     *@Author: Shalldid
     *@Date:Created in 10:44 2018-04-25
     **/
    public String getAccessionN(String modality){

        Date systemDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(systemDate);
        String param = "R" + modality + date + "___"; //生成规则，检查类型加当前年月日加三位数字
        String accessionN_temp = dicomWorkListMapper.getAccessionN(modality, param);
        if(accessionN_temp != null){
            String tempAccessionNOne = accessionN_temp.substring(0,11);
            String tempAccessionNTwo = accessionN_temp.substring(11,14);
            Integer tempInteger = Integer.parseInt(tempAccessionNTwo) + 1;
            return tempAccessionNOne + String.format("%03d", tempInteger);
        }else {
            return "R" + modality + date + "001";
        }
    }

    /**
     *@Description: 从数据库查询当前reg_info表内最大的checkNum号，若有则在当天的最大号加1，若无则返回检查类型+000001
     *@Author: Shalldid
     *@Date:Created in 11:07 2018-04-25
     **/
    public String getLocalCheckNum(String modality){
        String param = "R" + modality + "______"; //生成规则，检查类型加六位数字
        String checkNum_temp = registerInfoLocalMapper.getCheckNum(modality,param);
        System.out.println();
        if(checkNum_temp != null){
            int tempNum = Integer.parseInt(checkNum_temp.substring(3,9)) + 1;
            return "R" + modality + String.format("%06d", tempNum);
        }else{
            return "R" + modality + "000001";
        }
    }

    /**
     *@Description: 从数据库查询当前reg_info表内最大的checkNum号，若有则在当天的最大号加1，若无则返回检查类型+000001
     *@Author: Shalldid
     *@Date:Created in 11:07 2018-04-25
     **/
    public String getCheckNum(String modality){
        String param = "R" + modality + "______"; //生成规则，检查类型加六位数字
        String checkNum_temp = registerInfoMapper.getCheckNum(modality,param);
        if(checkNum_temp != null){
            int tempNum = Integer.parseInt(checkNum_temp.substring(3,9)) + 1;
            return "R" + modality + String.format("%06d", tempNum);
        }else{
            return "R" + modality + "000001";
        }
    }

    /**
     *@Description: 从数据库查询当前reg_info表内最大的patientID，若有则在当天的最大号加1，若无则返回检查类型+000001
     *@Author: Shalldid
     *@Date:Created in 12:52 2018-04-25
     **/
    public String getLocalPatientID(String modality){
        String param = "R" + modality + "______"; //生成规则，检查类型加六位数字
        String checkNum_temp = registerInfoLocalMapper.getPatientID(modality,param);
        if(checkNum_temp != null){
            int tempNum = Integer.parseInt(checkNum_temp.substring(3,9)) + 1;
            return "R" + modality + String.format("%06d", tempNum);
        }else{
            return "R" + modality + "000001";
        }
    }
    /**
     *@Description: 从数据库查询当前reg_info表内最大的patientID，若有则在当天的最大号加1，若无则返回检查类型+000001
     *@Author: Shalldid
     *@Date:Created in 12:52 2018-04-25
     **/
    public String getPatientID(String modality){
        String param = "R" + modality + "______"; //生成规则，检查类型加六位数字
        String checkNum_temp = registerInfoMapper.getPatientID(modality,param);
        if(checkNum_temp != null){
            int tempNum = Integer.parseInt(checkNum_temp.substring(3,9)) + 1;
            return "R" + modality + String.format("%06d", tempNum);
        }else{
            return "R" + modality + "000001";
        }
    }
    /**
     *@Description: 从数据库查询当前reg_info表内最大的RecordID号，若有则在当天的最大号加1，若无则返回检查类型+日期+000001
     *@Author: Shalldid
     *@Date:Created in 12:54 2018-04-25
     **/
    public String getRecordID(String modality){
        Date systemDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(systemDate);
        String param = "R" + modality + date + "______"; //生成规则，检查类型加当前年月日加六位数字
        String recordID_temp = registerInfoMapper.getRecordID(modality, param);
        if(recordID_temp != null){
            return "R" + modality + date +String.format("%06d", Integer.parseInt(recordID_temp.substring(11)) + 1);
        }else{
            return "R" + modality + date + "000001";
        }
    }
    /**
     *@Description: 从数据库查询当前reg_info表内最大的RecordID号，若有则在当天的最大号加1，若无则返回检查类型+日期+000001
     *@Author: Shalldid
     *@Date:Created in 12:54 2018-04-25
     **/
    public String getLocalRecordID(String modality){
        Date systemDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(systemDate);
        String param = "R" + modality + date + "______"; //生成规则，检查类型加当前年月日加六位数字
        String recordID_temp = registerInfoLocalMapper.getRecordID(modality, param);
        if(recordID_temp != null){
            return "R" + modality + date +String.format("%06d", Integer.parseInt(recordID_temp.substring(11)) + 1);
        }else{
            return "R" + modality + date + "000001";
        }
    }
    /**
     *@Description: 从数据库查询当前report表内最大的ReportCode号，若有则在当天的最大号加1，若无则返回BG+日期+00001
     *@Author: Shalldid
     *@Date:Created in 14:58 2018-04-25
     **/
    public String getReportCode(){
        Date systemDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String suffix = "BG" + dateFormat.format(systemDate) + "_____";
        String reportCode_temp = reportMapper.getReportCode(suffix);
        //System.out.println(reportCode_temp);
        if(reportCode_temp != null) {
            String tempCode_1 = reportCode_temp.substring(0,10);
            String tempCode_2 = reportCode_temp.substring(10,15);
            Integer tempInteger = Integer.parseInt(tempCode_2) + 1;
            return tempCode_1 + String.format("%05d", tempInteger);
        }else {
            return "BG" + dateFormat.format(systemDate) + "00001";
        }
    }
    /**
     * @Description: remoteReigster生成方法，格式为R+modality+yyyyMMdd+序列号3位，如RCT20180510001
     * @Author: Shalldid
     * @Date: Created in 10:14 2018-05-10
     * @Return:
     **/
    public String getRemoteChecknum(String modality){
        Date systemDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String suffix = "R" + modality + dateFormat.format(systemDate) + "___";
        String checkNum_temp = remoteRegisterMapper.getRemoteCheckNum(modality,suffix);
        if(checkNum_temp != null){
            String remoteCheckNumone = checkNum_temp.substring(0,11);
            String remoteCheckNumtwo = checkNum_temp.substring(11,14);
            Integer tempInteger = Integer.parseInt(remoteCheckNumtwo) + 1;
            return remoteCheckNumone + String.format("%03d", tempInteger);
        }else{
            return "R" + modality + dateFormat.format(systemDate) + "001";
        }
    }
}
