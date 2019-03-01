package org.springmvc.controller;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import org.springmvc.dto.ReturnStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConvertJSONTest {
    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            sdf.parse("2018-04-28");
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
