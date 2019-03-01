package org.springmvc.dao;

import org.springmvc.pojo.Template;

import java.util.List;

public interface TemplateMapper {
    int deleteByPrimaryKey(String templateId);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(String templateId);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);

    List<Template> loadLevelItem(int i);

    List<Template> findByParentId(String root_id);
}