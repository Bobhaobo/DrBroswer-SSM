package org.springmvc.dto;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName ReadDcmTagInfo
 * @Author Bob
 * @Date 2018-11-21 16:50
 */
public class ReadDcmTagInfo {
    //患者出生年月
    private String birth;
    //患者性别
    private String sex ;
    //患者年龄
    private String age ;
    //患者检查日期
    private String studyDate ;
    //患者序列日期
    private String seriesDate;
    //患者ID
    private String patientId ;
    //患者检查类型
    private String modality;
    //患者姓名
    private String patName;
    //检查实例UID
    private String studyUID;
    //序列实例UID
    private String seriesUID;
    //SOP实例UID
    private String sopUID;
    //生成XML文件需要的一个参数
    private Integer perPixel;
    //文件所在的文件夹名称
    private String folder;
    //文件名称
    private String fileName;
    //图像宽
    private Integer width;

    private Integer height;

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getPerPixel() {
        return perPixel;
    }

    public String getSeriesDate() {
        return seriesDate;
    }
    public String getBirth() {
        return birth;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getStudyDate() {
        return studyDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getModality() {
        return modality;
    }

    public String getPatName() {
        return patName;
    }

    public String getStudyUID() {
        return studyUID;
    }

    public String getSeriesUID() {
        return seriesUID;
    }

    public String getSopUID() {
        return sopUID;
    }

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

    public void getTagInfo(String filePath) throws IOException {

        File src = new File(filePath);
        this.setFolder(new File(src.getParent()).getName());
        this.setFileName(src.getName());
        Attributes attr1 = this.loadDicomObject(src);
        //修改默认字符集GB18030
        attr1.setString(Tag.SpecificCharacterSet, VR.CS,"GB18030");//解决中文乱码问题
        patientId = attr1.getString(Tag.PatientID,"");
        studyUID = attr1.getString(Tag.StudyInstanceUID,"");
        seriesUID = attr1.getString(Tag.SeriesInstanceUID,"");
        sopUID = attr1.getString(Tag.SOPInstanceUID,"");
        modality = attr1.getString(Tag.Modality,"");
        studyDate = attr1.getString(Tag.StudyDate,"");
        patName = attr1.getString(Tag.PatientName,"");
        birth = attr1.getString(Tag.PatientBirthDate,"");
        sex = attr1.getString(Tag.PatientSex,"");
        age = attr1.getString(Tag.PatientAge,"");
        perPixel = attr1.getInt(Tag.SamplesPerPixel,1);
        seriesDate = attr1.getString(Tag.SeriesDate,"");
        width = attr1.getInt(Tag.Columns,1);
        height = attr1.getInt(Tag.Rows,1);

    }
    public Attributes loadDicomObject(File file) throws IOException {
        if (file == null){
            return null;
        }
        else{
            DicomInputStream dis = new DicomInputStream(file);
            Attributes dcmObj = dis.readDataset(-1,-1);
            dis.close();
            return dcmObj;
        }
    }
    public static void main(String[] args) throws IOException {
        ReadDcmTagInfo readDcmTagInfo = new ReadDcmTagInfo();
        readDcmTagInfo.getTagInfo("E:\\2.dcm");
        System.out.println(readDcmTagInfo.patientId);
        System.out.println(readDcmTagInfo.patName);
        System.out.println(readDcmTagInfo.birth);
        System.out.println(readDcmTagInfo.studyDate);
        System.out.println("perpixel:"+readDcmTagInfo.perPixel);
        System.out.println("width"+readDcmTagInfo.width);
    }

}
