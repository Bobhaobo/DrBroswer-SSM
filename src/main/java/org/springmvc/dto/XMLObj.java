package org.springmvc.dto;

/**
 * @ClassName XMLObj
 * @Author Bob
 * @Date 2018-12-03 16:54
 */
public class XMLObj {
    //文件夹名称
    private String folder;
    //文件名
    private String fileName;
    //路径
    private String path;
    //图片宽
    private String width;
    //图片长
    private String height;

    private String depth;
    //存有坐标信息的二维数组
    private float[][] annoInfo;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public float[][] getAnnoInfo() {
        return annoInfo;
    }

    public void setAnnoInfo(float[][] annoInfo) {
        this.annoInfo = annoInfo;
    }
}
