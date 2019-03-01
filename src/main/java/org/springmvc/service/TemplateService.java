package org.springmvc.service;

import org.springmvc.pojo.Template;

public interface TemplateService {

    String getTemplateIntro();  //返回全部的模板简介

    Template getTemplateDetailById(String id); //通过模板id得到模板细节
}
