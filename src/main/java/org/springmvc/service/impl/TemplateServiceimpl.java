package org.springmvc.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springmvc.dao.TemplateMapper;
import org.springmvc.dto.TemplateTree;
import org.springmvc.pojo.Template;
import org.springmvc.service.TemplateService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TemplateServiceimpl implements TemplateService {

    private static final Logger logger = Logger.getLogger(RegInfoServiceimpl.class);

    @Resource
    private TemplateMapper templateMapper;
    /**
     * @Description: 返回报告模板简介，具体生成算法以后有空再注释 /捂脸
     * @Author: Shalldid
     * @Date: Created in 14:58 2018-05-04
     * @Return:
     **/
    @Override
    public String getTemplateIntro(){
        List<Template> root_list = templateMapper.loadLevelItem(1);
        List<Template> level_2_list = templateMapper.loadLevelItem(2);
        List<Template> level_3_list = templateMapper.loadLevelItem(3);

        List<TemplateTree> output_level_2 = new ArrayList<TemplateTree>();
        List<TemplateTree> output_level_1 = new ArrayList<TemplateTree>();
        HashMap<String, List<TemplateTree>> level_2_map = new HashMap<String,List<TemplateTree>>();
        HashMap<String, List<TemplateTree>> level_1_map = new HashMap<String,List<TemplateTree>>();

        for(Template t2 : level_3_list) {
            if(!level_2_map.containsKey(t2.getParentId())) {
                level_2_map.put(t2.getParentId(), new ArrayList<TemplateTree>());
                level_2_map.get(t2.getParentId()).add(new TemplateTree(t2.getParentId(),t2.getTitle(),"",t2.getParentId(),new ArrayList<TemplateTree>()));
            }else {
                level_2_map.get(t2.getParentId()).add(new TemplateTree(t2.getParentId(), t2.getTitle(), "", t2.getParentId(),new ArrayList<TemplateTree>()));
            }
        }
        //System.out.println("level_2_map : " + level_2_map);
        for(Template t2 : level_2_list) {
            output_level_2.add(new TemplateTree(t2.getTemplateId(),t2.getTitle(),"",t2.getParentId(),level_2_map.get(t2.getTemplateId())));
        }

        for(TemplateTree t : output_level_2) {
            //System.out.println("t.toString: " + t.toString());
            if(!level_1_map.containsKey(t.getParent_id())) {
                level_1_map.put(t.getParent_id(),new ArrayList<TemplateTree>());
                level_1_map.get(t.getParent_id()).add(new TemplateTree(t.getId(), t.getText(), "", t.getParent_id(),t.getChildren()));
            }else {
                level_1_map.get(t.getParent_id()).add(new TemplateTree(t.getId(), t.getText(), "", t.getParent_id(),t.getChildren()));
            }
        }
        for(Template t : root_list) {
            output_level_1.add(new TemplateTree(t.getTemplateId(), t.getTitle(), "", t.getParentId(),level_1_map.get(t.getTemplateId())));
        }

        return JSON.toJSONString(output_level_1);
    }
    /**
     * @Description: 根据template_id返回模板具体信息
     * @Author: Shalldid
     * @Date: Created in 15:23 2018-05-04
     * @Return:
     **/
    @Override
    public Template getTemplateDetailById(String id){
        return templateMapper.selectByPrimaryKey(id);
    }
}
