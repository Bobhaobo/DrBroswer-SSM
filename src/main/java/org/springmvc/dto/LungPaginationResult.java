package org.springmvc.dto;

import java.util.List;

public class LungPaginationResult {

    /**
     * @Description: 返回前端数据的分页数据结构
     * @Author: Shalldid
     * @Date: Created in 12:08 2018-04-28
     * @Return:
     **/


        private lungDto lungDto;

        //返回Base64编码
        private String base64Url;

    public LungPaginationResult() {
    }

    public LungPaginationResult(org.springmvc.dto.lungDto lungDto) {
        this.lungDto = lungDto;
    }

    public LungPaginationResult(org.springmvc.dto.lungDto lungDto, String base64Url) {
        this.lungDto = lungDto;
        this.base64Url = base64Url;
    }

    @Override
    public String toString() {
        return "LungPaginationResult{" +
                "lungDto=" + lungDto +
                ", base64Url='" + base64Url + '\'' +
                '}';
    }

    public org.springmvc.dto.lungDto getLungDto() {
        return lungDto;
    }

    public void setLungDto(org.springmvc.dto.lungDto lungDto) {
        this.lungDto = lungDto;
    }

    public String getBase64Url() {
        return base64Url;
    }

    public void setBase64Url(String base64Url) {
        this.base64Url = base64Url;
    }
}
