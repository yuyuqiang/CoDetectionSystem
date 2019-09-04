package cn.edu.nuc.codetectionsystem.cars;

public class Car {

    private int car_iv;
    private int warn_iv;
    private String name_tv;
    private String name1_tv;
    private String phone_tv;
    private String phone1_tv;
    private String record_tv;
    private int license_iv;
    private String license_tv;
    private int sensor_iv1;
    private int sensor_iv2;
    private String co_one_tv;
    private String co_two_tv;
    private String danwei1;
    private String danwei2;
    private int delete_iv;

    public Car(int car_iv, int warn_iv, String name_tv, String name1_tv, String phone_tv, String phone1_tv,String record_tv, int license_iv, String license_tv, int sensor_iv1, int sensor_iv2, String co_one_tv, String co_two_tv, String danwei1, String danwei2,int delete_iv) {
        this.car_iv = car_iv;
        this.warn_iv = warn_iv;
        this.name_tv = name_tv;
        this.name1_tv = name1_tv;
        this.phone_tv = phone_tv;
        this.phone1_tv = phone1_tv;
        this.record_tv = record_tv;
        this.license_iv = license_iv;
        this.license_tv = license_tv;
        this.sensor_iv1 = sensor_iv1;
        this.sensor_iv2 = sensor_iv2;
        this.co_one_tv = co_one_tv;
        this.co_two_tv = co_two_tv;
        this.danwei1 = danwei1;
        this.danwei2 = danwei2;
        this.delete_iv=delete_iv;
    }

    public int getCar_iv() {
        return car_iv;
    }

    public void setCar_iv(int car_iv) {
        this.car_iv = car_iv;
    }

    public int getWarn_iv() {
        return warn_iv;
    }

    public void setWarn_iv(int warn_iv) {
        this.warn_iv = warn_iv;
    }

    public String getName_tv() {
        return name_tv;
    }

    public void setName_tv(String name_tv) {
        this.name_tv = name_tv;
    }

    public String getName1_tv() {
        return name1_tv;
    }

    public void setName1_tv(String name1_tv) {
        this.name1_tv = name1_tv;
    }

    public String getPhone_tv() {
        return phone_tv;
    }

    public void setPhone_tv(String phone_tv) {
        this.phone_tv = phone_tv;
    }

    public String getPhone1_tv() {
        return phone1_tv;
    }

    public void setPhone1_tv(String phone1_tv) {
        this.phone1_tv = phone1_tv;
    }

    public int getLicense_iv() {
        return license_iv;
    }

    public void setLicense_iv(int license_iv) {
        this.license_iv = license_iv;
    }

    public String getLicense_tv() {
        return license_tv;
    }

    public void setLicense_tv(String license_tv) {
        this.license_tv = license_tv;
    }

    public int getSensor_iv1() {
        return sensor_iv1;
    }

    public void setSensor_iv1(int sensor_iv1) {
        this.sensor_iv1 = sensor_iv1;
    }

    public int getSensor_iv2() {
        return sensor_iv2;
    }

    public void setSensor_iv2(int sensor_iv2) {
        this.sensor_iv2 = sensor_iv2;
    }

    public String getCo_one_tv() {
        return co_one_tv;
    }

    public void setCo_one_tv(String co_one_tv) {
        this.co_one_tv = co_one_tv;
    }

    public String getCo_two_tv() {
        return co_two_tv;
    }

    public void setCo_two_tv(String co_two_tv) {
        this.co_two_tv = co_two_tv;
    }

    public String getDanwei1() {
        return danwei1;
    }

    public void setDanwei1(String danwei1) {
        this.danwei1 = danwei1;
    }

    public String getDanwei2() {
        return danwei2;
    }

    public void setDanwei2(String danwei2) {
        this.danwei2 = danwei2;
    }

    public int getDelete_iv() {
        return delete_iv;
    }

    public void setDelete_iv(int delete_iv) {
        this.delete_iv = delete_iv;
    }

    public String getRecord_tv() {
        return record_tv;
    }

    public void setRecord_tv(String record_tv) {
        this.record_tv = record_tv;
    }
}
