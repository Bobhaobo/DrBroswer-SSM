package org.springmvc.tool;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *@Description: 根据传入的病人年龄和年龄单位，估算返回病人生日
 *@Author: Shalldid
 *@Date:Created in 16:57 2018-04-24
 **/
@Service
public class BirthGenerator {

    private static final Long UNIT_YEAR = 1000*60*60*24*365L;
    private static final Long UNIT_MONTH = 1000*60*60*24*30L;
    private static final Long UNIT_WEEKEND = 1000*60*60*24*7L;
    private static final Long UNIT_DAY = 1000*60*60*24L;
    private static final Long UNIT_HOUR = 1000*60*60L;

    public String[] getAgeAndType(Date patBirthDay, Date now){
        Long diff = now.getTime() - patBirthDay.getTime();
        String[] result = new String[2];
        if(diff / UNIT_YEAR <= 0){
            if(diff / UNIT_MONTH <= 0){
                if(diff / UNIT_WEEKEND <= 0){
                    if(diff / UNIT_DAY <= 0){
                        result[0] = diff / UNIT_HOUR + "";
                        result[1] = "小时";
                    }else {
                        result[0] = diff / UNIT_DAY + "";
                        result[1] = "天";
                    }
                }else {
                    result[0] = diff / UNIT_WEEKEND + "";
                    result[1] = "周";
                }
            }else{
                result[0] = diff / UNIT_MONTH + "";
                result[1] = "月";
            }
        }else{
            result[0] = diff / UNIT_YEAR + "";
            result[1] = "岁";
        }
        return result;
    }
    private String PatBirth(String age,String ageType) throws Exception{
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int ageInt = Integer.parseInt(age);
        String dateString = sdf.format(date);

        if(ageType.equals("year")){
            String dateOne = dateString.substring(0,4);
            String dateTwo = dateString.substring(4, 19);
            Integer dateYear = Integer.parseInt(dateOne)-ageInt;
            String dateAfter = dateYear.toString() + dateTwo;
            return dateAfter;
        }else if(ageType.equals("month")){
            String dateOne = dateString.substring(0,4);
            String dateTwo = dateString.substring(5,7);
            String dateThree = dateString.substring(7,19);
            Integer dateYear = Integer.parseInt(dateOne);
            Integer dateMonth = Integer.parseInt(dateTwo);
            int years = ageInt/12;
            dateYear = dateYear -years;
            int month = ageInt%12;
            if(month>dateMonth){
                dateYear = dateYear-1;
                dateMonth = 12 + dateMonth -month;
            }else if(month == dateMonth){
                dateYear = dateYear-1;
                dateMonth = 12;
            }else {
                dateMonth = dateMonth - month;
            }
            String dateAfter = dateYear.toString()+"-"+String.format("%02d", dateMonth)+dateThree;
            return dateAfter;
        }else if(ageType.equals("day")){
            String dateFour = dateString.substring(10,19);
            Calendar calendar =Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            calendar.add(Calendar.DAY_OF_MONTH,(-ageInt));
            String tempDate = simpleDateFormat.format(calendar.getTime());
            return tempDate + dateFour;
        }else{
            Calendar calendar =Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY,(-ageInt));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(calendar.getTime());
        }
    }
    public Timestamp getBirth(String age, String ageType) throws Exception{
        BirthGenerator patBirthGenerator = new BirthGenerator();
        String tempDate = patBirthGenerator.PatBirth(age, ageType);
        Timestamp patBrith =  Timestamp.valueOf(tempDate);
        return patBrith;
    }
}
