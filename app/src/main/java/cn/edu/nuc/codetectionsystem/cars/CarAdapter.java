package cn.edu.nuc.codetectionsystem.cars;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.viewpage.ViewpageManage_Activity;

public class CarAdapter extends ArrayAdapter<Car> {

    private int resourceId;
    public static List<TextView> list= new ArrayList<>();
    private List<Car> objects;

    private ImageView car_iv;
    private ImageView warn_iv;
    private TextView name_tv;
    private TextView name1_tv;
    private TextView phone_tv;
    private TextView phone1_tv;
    private TextView gender_tv;
    private TextView gender1_tv;
    private ImageView license_iv;
    private TextView license_tv;
    private ImageView sensor1_iv;
    private TextView co_one_tv;
    private TextView danwei1;
    private ImageView sensor2_iv;
    private TextView co_two_tv;
    private TextView danwei2;
    public static TextView detail_tv;


    public CarAdapter(@NonNull Context context, int resource, List<Car>objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Car car = getItem(position);//获取当前的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        car_iv = view.findViewById(R.id.car_iv);
        warn_iv =view.findViewById(R.id.warn_iv);
        name_tv = view.findViewById(R.id.name_tv);
        name1_tv =view.findViewById(R.id.name_tv1);
        phone_tv = view.findViewById(R.id.phone_tv);
        phone1_tv =view.findViewById(R.id.phone_tv1);
        gender_tv = view.findViewById(R.id.gender_tv);
        gender1_tv =view.findViewById(R.id.gender_tv1);
        license_iv = view.findViewById(R.id.license_iv);
        license_tv =view.findViewById(R.id.license_tv);
        sensor1_iv = view.findViewById(R.id.sensor1_iv);
        sensor2_iv =view.findViewById(R.id.sensor2_iv);
        co_one_tv =view.findViewById(R.id.co_one_tv);
        co_two_tv = view.findViewById(R.id.co_two_tv);
        danwei1 =view.findViewById(R.id.danwei1);
        danwei2 = view.findViewById(R.id.danwei2);
        detail_tv =view.findViewById(R.id.detail_tv);

        detail_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"ydfukfFffff",Toast.LENGTH_LONG).show();
                Intent intent =new Intent(getContext(), ViewpageManage_Activity.class);
                getContext().startActivity(intent);//请求码为0
            }
        });

        name_tv.setText(car.getName_tv());
        name1_tv.setText(car.getName1_tv());
        phone_tv.setText(car.getPhone_tv());
        phone1_tv.setText(car.getPhone1_tv());
        gender_tv.setText(car.getGender_tv());
        gender1_tv.setText(car.getGender1_tv());
        license_tv.setText(car.getLicense_tv());
        co_one_tv.setText(car.getCo_one_tv());
        co_two_tv.setText(car.getCo_two_tv());
        danwei1.setText(car.getDanwei1());
        danwei2.setText(car.getDanwei2());
        detail_tv.setText(car.getDetail_tv());

        car_iv.setImageResource(car.getCar_iv());
        warn_iv.setImageResource(car.getWarn_iv());
        license_iv.setImageResource(car.getLicense_iv());
        sensor1_iv.setImageResource(car.getSensor_iv1());
        sensor2_iv.setImageResource(car.getSensor_iv2());
        return view;
    }
}
