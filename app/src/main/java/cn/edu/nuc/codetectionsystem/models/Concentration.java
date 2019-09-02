package cn.edu.nuc.codetectionsystem.models;

public class Concentration {
    private  int id;
    private  String concentrationId;
    private String date;
    private int time;
    private  int data_mg;
    public Concentration(int id, String concentrationId, String date, int time, int data_mg) {
        this.id = id;
        this.concentrationId = concentrationId;
        this.date = date;
        this.time = time;
        this.data_mg = data_mg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcentrationId() {
        return concentrationId;
    }

    public void setConcentrationId(String concentrationId) {
        this.concentrationId = concentrationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getData_mg() {
        return data_mg;
    }

    public void setData_mg(int data_mg) {
        this.data_mg = data_mg;
    }
}
