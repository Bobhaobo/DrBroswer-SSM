package org.springmvc.dto;

import org.springmvc.pojo.Template;

import java.util.ArrayList;
import java.util.List;

public class TemplateTree {

    private String id;
    private String text;
    private String icon;
    private String parent_id;
    private List<TemplateTree> children = new ArrayList<TemplateTree>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public List<TemplateTree> getChildren() {
        return children;
    }

    public void setChildren(List<TemplateTree> children) {
        this.children = children;
    }

    public TemplateTree(String id, String text, String icon, String parent_id, List<TemplateTree> children) {
        this.id = id;
        this.text = text;
        this.icon = icon;
        this.parent_id = parent_id;
        this.children = children;
    }

    public TemplateTree() {
    }
}
