package org.springmvc.tool;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springmvc.dao.AnnoInfoMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName PrimaryKeyGenerator生成各种主键
 * @Author Bob
 * @Date 2018-11-28 11:32
 */
@Component
public class PrimaryKeyGenerator {
    @Autowired
    private AnnoInfoMapper annoAnnoInfoDao;
    public String getAnnoIdN(String modality){
        Date date = new Date();
        //设置时间格式
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//HH表示24小时制
        String presentDate = dateFormat.format(date);
        String param = "A"+modality+presentDate+"___";//生成规则，检查类型加当前年月日时分秒加三位数字
        String id_temp = annoAnnoInfoDao.getAnnoId(param);
        if (id_temp!=null){
            String tempIdOne = id_temp.substring(0,17);
            String tempIdTwo = id_temp.substring(17);
            Integer tempId = Integer.parseInt(tempIdTwo) + 1;
            return tempIdOne+ String.format("%03d",tempId);
        }else{
            return "A" + modality + presentDate + "001";
        }
    }
}
