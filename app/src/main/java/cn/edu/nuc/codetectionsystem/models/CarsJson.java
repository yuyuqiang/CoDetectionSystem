package cn.edu.nuc.codetectionsystem.models;

import android.support.v7.widget.CardView;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class CarsJson {

    private static String TAG = "car";

    public static List<Cars> jsonTOObject(String get){
        List<Cars> cars;
        cars = JSON.parseArray(get,Cars.class);

        return cars;
    }
}
