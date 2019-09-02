package cn.edu.nuc.codetectionsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.cars.Car;
import cn.edu.nuc.codetectionsystem.until.GetPostUtil;
import cn.edu.nuc.codetectionsystem.until.SaveUtils;

public class Car_ADD_Activity extends AppCompatActivity {

    private ImageView baocun =null;
    private String number_p,username_p,gender_p;
    public static  String license;
    private EditText license_add=null;
    private EditText phone_add=null;
    private String data;
    private String get;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_add);
        baocun = findViewById(R.id.baocun);
        license_add = findViewById(R.id.license_add);
        phone_add = findViewById(R.id.phone_add);

        username_p = SaveUtils.getSettingNote(Car_ADD_Activity.this, "userInfo", "username");
        number_p = SaveUtils.getSettingNote(Car_ADD_Activity.this, "userInfo", "number");
        gender_p = SaveUtils.getSettingNote(Car_ADD_Activity.this, "userInfo", "gender");


        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                license = license_add.getText().toString();

                Car car = new Car(R.drawable.theme3,R.drawable.ic_warning,"用户：",username_p,"电话:",
                        number_p,"性别:",gender_p,R.drawable.ic_license,license,
                        R.drawable.ic_sensor,R.drawable.ic_sensor,"200","200","ppm","ppm");

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String url = "http://47.94.19.124:8080/Winds/car/addCar?";
                        data ="number="+number_p+"&license="+license+"";
                        Log.e("add", "run: "+data );
                        get = GetPostUtil.sendPostRequest(url,data);
                        Log.i("get:", get);

                       final Car car = new Car(R.drawable.theme3,R.drawable.ic_warning,"用户：",username_p,"电话:",
                                number_p,"性别:",gender_p,R.drawable.ic_license,license,
                                R.drawable.ic_sensor,R.drawable.ic_sensor,"200","200","ppm","ppm");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                PhoneFragment.adapter.my_notify(car);
                                Log.e("car:", "onClick: "+car);
                            }
                        });


                    }
                }).start();
                   finish();

            }
        });
    }
}
