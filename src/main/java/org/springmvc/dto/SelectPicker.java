package org.springmvc.dto;

public class SelectPicker {

    private String value;
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "SelectPicker{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
