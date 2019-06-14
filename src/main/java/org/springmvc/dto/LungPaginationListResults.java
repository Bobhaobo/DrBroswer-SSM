package org.springmvc.dto;

import java.util.List;

public class LungPaginationListResults {
    private List<lungDto> lungDto;

    //返回Base64编码
    private String base64Url;

    public LungPaginationListResults(List<org.springmvc.dto.lungDto> lungDto, String base64Url) {
        this.lungDto = lungDto;
        this.base64Url = base64Url;
    }

    public List<org.springmvc.dto.lungDto> getLungDto() {
        return lungDto;
    }

    public void setLungDto(List<org.springmvc.dto.lungDto> lungDto) {
        this.lungDto = lungDto;
    }

    public String getBase64Url() {
        return base64Url;
    }

    public void setBase64Url(String base64Url) {
        this.base64Url = base64Url;
    }

    public LungPaginationListResults() {
    }

    @Override
    public String toString() {
        return "LungPaginationListResults{" +
                "lungDto=" + lungDto +
                ", base64Url='" + base64Url + '\'' +
                '}';
    }
}
