package cn.edu.nuc.codetectionsystem.models;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class ConcentrationJson {
    private static String TAG = "concentration";

    public static List<Concentration> jsonTOObject(String get){
        List<Concentration> concentration;
        concentration = JSON.parseArray(get,Concentration.class);

        return concentration;
    }
}
