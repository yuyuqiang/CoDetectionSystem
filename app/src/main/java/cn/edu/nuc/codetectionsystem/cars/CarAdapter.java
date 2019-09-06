package cn.edu.nuc.codetectionsystem.cars;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.edu.nuc.codetectionsystem.MainActivity;
import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.fragment.CoDataFragment;
import cn.edu.nuc.codetectionsystem.fragment.Dialog_self;
import cn.edu.nuc.codetectionsystem.login.Register_Activity;
import cn.edu.nuc.codetectionsystem.models.User;
import cn.edu.nuc.codetectionsystem.models.UserJson;
import cn.edu.nuc.codetectionsystem.test.LineCharts;
import cn.edu.nuc.codetectionsystem.until.GetPostUtil;
import cn.edu.nuc.codetectionsystem.until.SaveUtils;
import cn.edu.nuc.codetectionsystem.viewpage.ViewpageManage_Activity;

public class CarAdapter extends ArrayAdapter<Car> {

    private int resourceId;
    //public static List<TextView> list= new ArrayList<>();
    private List<Car> objects;

    private String data;
    private String get;
    private String number;
    private String aaa;
    Car car;

    public void my_notify(Car car){
        objects.add(car);
        notifyDataSetChanged();
    }

//    public void my_notify1(final Car car){
//        record_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                new DatePickerDialog(getContext(), DatePickerDialog.THEME_HOLO_LIGHT,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                                Log.e("hyu", "onDateSet: "+ i + "/" + (1 + i1) + "/" + i2);
//                               objects.get(car).setRecord_tv().setText(i + "/" + (1 + i1) + "/" + i2);
//                            }
//                        },
//                        calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH)
//
//                ).show();
//            }
//        });
//
//    }



    public CarAdapter(@NonNull Context context, int resource, List<Car>objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        car = getItem(position);//获取当前的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        final TextView record_tv;
        final ImageView car_iv;
        final ImageView warn_iv;
        final TextView name_tv;
        final TextView name1_tv;
        final TextView phone_tv;
        final ImageView license_iv;
        final TextView license_tv;
        final ImageView sensor1_iv;
        final TextView co_one_tv;
        final TextView danwei1;
        final ImageView sensor2_iv;
        final TextView co_two_tv;
        final TextView danwei2;
        final  ImageView delete_iv;
        final LineCharts lineCharts;

        car_iv = view.findViewById(R.id.car_iv);
        warn_iv =view.findViewById(R.id.warn_iv);
        name_tv = view.findViewById(R.id.name_tv);
        name1_tv =view.findViewById(R.id.name_tv1);
        phone_tv = view.findViewById(R.id.phone_tv1);
        record_tv = view.findViewById(R.id.record_tv);
        license_iv = view.findViewById(R.id.license_iv);
        license_tv =view.findViewById(R.id.license_tv);
        sensor1_iv = view.findViewById(R.id.sensor1_iv);
        sensor2_iv =view.findViewById(R.id.sensor2_iv);
        co_one_tv =view.findViewById(R.id.co_one_tv);
        co_two_tv = view.findViewById(R.id.co_two_tv);
        danwei1 =view.findViewById(R.id.danwei1);
        danwei2 = view.findViewById(R.id.danwei2);
        delete_iv =view.findViewById(R.id.delete_iv);

        delete_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).
                        setTitle("车辆信息").
                        setIcon(R.drawable.ic_delete).
                        setMessage("是否删除此车辆信息?").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        number = SaveUtils.getSettingNote(getContext(), "userInfo", "number");
                                        String url = "http://47.94.19.124:8080/Winds/car/delete?";
                                        data ="number="+number+"&license="+car.getLicense_tv()+"";
                                        Log.e("add", "run: "+data );
                                        get = GetPostUtil.sendPostRequest(url,data);
                                        Log.i("get:", get);

                                        Log.e("delete", "run: "+number+car.getLicense_tv() );


                                    }
                                }).start();
                                 Toast.makeText(getContext(),"删除成功",Toast.LENGTH_LONG).show();


                            }
                        });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"取消删除",Toast.LENGTH_LONG).show();


                    }
                });
                builder.create().show();
            }
        });


        record_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(getContext(), DatePickerDialog.THEME_HOLO_LIGHT,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                getItem(position).setRecord_tv(i + "/" + (1 + i1) + "/" + i2);
                                record_tv.setText(getItem(position).getRecord_tv());
                                //TextView textView = null;
                                Dialog_self.Builder dialogBuild = new Dialog_self.Builder(getContext());
                                String station = i + "/" + (1 + i1) + "/" + i2+"CO浓度变化";
                                //textView.setText(i + "/" + (1 + i1) + "/" + i2+"CO浓度变化");
                                Dialog_self dialog = dialogBuild.create(position,station);
                                dialog.setCanceledOnTouchOutside(true);// 点击外部区域关闭
                                dialog.show();


                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)

                ).show();


            }
        });



        name_tv.setText(car.getName_tv());
        name1_tv.setText(car.getName1_tv());
        phone_tv.setText(car.getPhone1_tv());
        record_tv.setText(car.getRecord_tv());
        license_tv.setText(car.getLicense_tv());



        co_one_tv.setText(car.getCo_one_tv());
        co_two_tv.setText(car.getCo_two_tv());
        danwei1.setText(car.getDanwei1());
        danwei2.setText(car.getDanwei2());

        car_iv.setImageResource(car.getCar_iv());
        warn_iv.setImageResource(car.getWarn_iv());
        license_iv.setImageResource(car.getLicense_iv());
        sensor1_iv.setImageResource(car.getSensor_iv1());
        sensor2_iv.setImageResource(car.getSensor_iv2());
        delete_iv.setImageResource(car.getDelete_iv());

        try {
            if (Integer.valueOf(car.getCo_one_tv())>50){
                co_one_tv.setTextColor(Color.RED);
                warn_iv.setVisibility(View.VISIBLE);
            }

            if (Integer.valueOf(car.getCo_two_tv())>50){
                co_two_tv.setTextColor(Color.RED);
                warn_iv.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){

        }


//        void judgeOne(String s){
//            if(Integer.valueOf(s)>300){
//                warn_iv.setVisibility(View.VISIBLE);
//                co_one_tv.setTextColor(Color.RED);
//            }
//        }
        return view;
    }


}
