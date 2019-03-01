package org.springmvc.pojo_inner;

import org.springmvc.pojo.DicomWorkList;

public class DicomWorkListInner {
    private String accessionn;

    private String patientid;

    private String patientnam;

    private String patientbir;

    private String patientsex;

    private String medicalale;

    private String contrastal;

    private String studyinsta;

    private String reqphysici;

    private String reqprocdes;

    private String modality;

    private String reqcontras;

    private String scheduleda;

    private String startdate;

    private String starttime;

    private String perfphysic;

    private String schedpsdes;

    private String schedpsid;

    private String schedstati;

    private String schedpsloc;

    private String premedicat;

    private String schedpscom;

    private String reqprocid;

    private String reqprocpri;

    private String studyid;

    public String getAccessionn() {
        return accessionn;
    }

    public void setAccessionn(String accessionn) {
        this.accessionn = accessionn == null ? null : accessionn.trim();
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid == null ? null : patientid.trim();
    }

    public String getPatientnam() {
        return patientnam;
    }

    public void setPatientnam(String patientnam) {
        this.patientnam = patientnam == null ? null : patientnam.trim();
    }

    public String getPatientbir() {
        return patientbir;
    }

    public void setPatientbir(String patientbir) {
        this.patientbir = patientbir == null ? null : patientbir.trim();
    }

    public String getPatientsex() {
        return patientsex;
    }

    public void setPatientsex(String patientsex) {
        this.patientsex = patientsex == null ? null : patientsex.trim();
    }

    public String getMedicalale() {
        return medicalale;
    }

    public void setMedicalale(String medicalale) {
        this.medicalale = medicalale == null ? null : medicalale.trim();
    }

    public String getContrastal() {
        return contrastal;
    }

    public void setContrastal(String contrastal) {
        this.contrastal = contrastal == null ? null : contrastal.trim();
    }

    public String getStudyinsta() {
        return studyinsta;
    }

    public void setStudyinsta(String studyinsta) {
        this.studyinsta = studyinsta == null ? null : studyinsta.trim();
    }

    public String getReqphysici() {
        return reqphysici;
    }

    public void setReqphysici(String reqphysici) {
        this.reqphysici = reqphysici == null ? null : reqphysici.trim();
    }

    public String getReqprocdes() {
        return reqprocdes;
    }

    public void setReqprocdes(String reqprocdes) {
        this.reqprocdes = reqprocdes == null ? null : reqprocdes.trim();
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality == null ? null : modality.trim();
    }

    public String getReqcontras() {
        return reqcontras;
    }

    public void setReqcontras(String reqcontras) {
        this.reqcontras = reqcontras == null ? null : reqcontras.trim();
    }

    public String getScheduleda() {
        return scheduleda;
    }

    public void setScheduleda(String scheduleda) {
        this.scheduleda = scheduleda == null ? null : scheduleda.trim();
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate == null ? null : startdate.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getPerfphysic() {
        return perfphysic;
    }

    public void setPerfphysic(String perfphysic) {
        this.perfphysic = perfphysic == null ? null : perfphysic.trim();
    }

    public String getSchedpsdes() {
        return schedpsdes;
    }

    public void setSchedpsdes(String schedpsdes) {
        this.schedpsdes = schedpsdes == null ? null : schedpsdes.trim();
    }

    public String getSchedpsid() {
        return schedpsid;
    }

    public void setSchedpsid(String schedpsid) {
        this.schedpsid = schedpsid == null ? null : schedpsid.trim();
    }

    public String getSchedstati() {
        return schedstati;
    }

    public void setSchedstati(String schedstati) {
        this.schedstati = schedstati == null ? null : schedstati.trim();
    }

    public String getSchedpsloc() {
        return schedpsloc;
    }

    public void setSchedpsloc(String schedpsloc) {
        this.schedpsloc = schedpsloc == null ? null : schedpsloc.trim();
    }

    public String getPremedicat() {
        return premedicat;
    }

    public void setPremedicat(String premedicat) {
        this.premedicat = premedicat == null ? null : premedicat.trim();
    }

    public String getSchedpscom() {
        return schedpscom;
    }

    public void setSchedpscom(String schedpscom) {
        this.schedpscom = schedpscom == null ? null : schedpscom.trim();
    }

    public String getReqprocid() {
        return reqprocid;
    }

    public void setReqprocid(String reqprocid) {
        this.reqprocid = reqprocid == null ? null : reqprocid.trim();
    }

    public String getReqprocpri() {
        return reqprocpri;
    }

    public void setReqprocpri(String reqprocpri) {
        this.reqprocpri = reqprocpri == null ? null : reqprocpri.trim();
    }

    public String getStudyid() {
        return studyid;
    }

    public void setStudyid(String studyid) {
        this.studyid = studyid == null ? null : studyid.trim();
    }

    public DicomWorkListInner() {

    }

    public DicomWorkListInner(DicomWorkList dicomWorkList){
        this.accessionn = dicomWorkList.getAccessionn();
        this.patientid = dicomWorkList.getPatientid();
        this.patientnam = dicomWorkList.getPatientnam();
        this.patientbir = dicomWorkList.getPatientbir();
        this.patientsex = dicomWorkList.getPatientsex();
        this.medicalale = dicomWorkList.getMedicalale();
        this.contrastal = dicomWorkList.getContrastal();
        this.studyinsta = dicomWorkList.getStudyinsta();
        this.reqphysici = dicomWorkList.getReqphysici();
        this.reqprocdes = dicomWorkList.getReqprocdes();
        this.modality = dicomWorkList.getModality();
        this.reqcontras = dicomWorkList.getReqcontras();
        this.scheduleda = dicomWorkList.getScheduleda();
        this.startdate = dicomWorkList.getStartdate();
        this.starttime = dicomWorkList.getStarttime();
        this.perfphysic = dicomWorkList.getPerfphysic();
        this.schedpsdes = dicomWorkList.getSchedpsdes();
        this.schedpsid = dicomWorkList.getSchedpsid();
        this.schedstati = dicomWorkList.getSchedstati();
        this.schedpsloc = dicomWorkList.getSchedpsloc();
        this.premedicat = dicomWorkList.getPremedicat();
        this.schedpscom = dicomWorkList.getSchedpscom();
        this.reqprocid = dicomWorkList.getReqprocid();
        this.reqprocpri = dicomWorkList.getReqprocpri();
        this.studyid = dicomWorkList.getStudyid();
    }

    @Override
    public String toString() {
        return "DicomWorkListInner{" +
                "accessionn='" + accessionn + '\'' +
                ", patientid='" + patientid + '\'' +
                ", patientnam='" + patientnam + '\'' +
                ", patientbir='" + patientbir + '\'' +
                ", patientsex='" + patientsex + '\'' +
                ", medicalale='" + medicalale + '\'' +
                ", contrastal='" + contrastal + '\'' +
                ", studyinsta='" + studyinsta + '\'' +
                ", reqphysici='" + reqphysici + '\'' +
                ", reqprocdes='" + reqprocdes + '\'' +
                ", modality='" + modality + '\'' +
                ", reqcontras='" + reqcontras + '\'' +
                ", scheduleda='" + scheduleda + '\'' +
                ", startdate='" + startdate + '\'' +
                ", starttime='" + starttime + '\'' +
                ", perfphysic='" + perfphysic + '\'' +
                ", schedpsdes='" + schedpsdes + '\'' +
                ", schedpsid='" + schedpsid + '\'' +
                ", schedstati='" + schedstati + '\'' +
                ", schedpsloc='" + schedpsloc + '\'' +
                ", premedicat='" + premedicat + '\'' +
                ", schedpscom='" + schedpscom + '\'' +
                ", reqprocid='" + reqprocid + '\'' +
                ", reqprocpri='" + reqprocpri + '\'' +
                ", studyid='" + studyid + '\'' +
                '}';
    }
}