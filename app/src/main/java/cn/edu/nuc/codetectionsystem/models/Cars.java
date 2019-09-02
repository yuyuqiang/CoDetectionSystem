package cn.edu.nuc.codetectionsystem.models;


import java.util.List;

public class Cars {
    private String license;
    private List< List<Integer> >data_mg;

    public Cars() {
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public List<List<Integer>> getData_mg() {
        return data_mg;
    }

    public void setData_mg(List<List<Integer>> data_mg) {
        this.data_mg = data_mg;
    }
}
