package cn.edu.nuc.codetectionsystem.models;

import android.content.SyncRequest;

public class Cars {
    private String userName;
    private String number;
    private String gender;
    private String license;
    private String concentrationid;
    private String sensorname;

    public  Cars(){
        super();
    }

    public Cars(String userName, String number, String gender, String license, String concentrationid, String sensorname) {
        super();
        this.userName = userName;
        this.number = number;
        this.gender = gender;
        this.license = license;
        this.concentrationid = concentrationid;
        this.sensorname = sensorname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getConcentrationid() {
        return concentrationid;
    }

    public void setConcentrationid(String concentrationid) {
        this.concentrationid = concentrationid;
    }

    public String getSensorname() {
        return sensorname;
    }

    public void setSensorname(String sensorname) {
        this.sensorname = sensorname;
    }
}
