package cn.edu.nuc.codetectionsystem.models;


import com.alibaba.fastjson.JSON;

import java.util.List;

public class UserJson {


    private static String TAG = "good";

    public static List<User>jsonTOObject(String get){
        List<User> users;
        users = JSON.parseArray(get,User.class);

        return users;
    }


}
