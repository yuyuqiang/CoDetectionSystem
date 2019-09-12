package cn.edu.nuc.codetectionsystem.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.cars.Car;
import cn.edu.nuc.codetectionsystem.cars.CarAdapter;
import cn.edu.nuc.codetectionsystem.models.Cars;
import cn.edu.nuc.codetectionsystem.models.CarsJson;
import cn.edu.nuc.codetectionsystem.until.GetPostUtil;
import cn.edu.nuc.codetectionsystem.until.SaveUtils;
import cn.edu.nuc.codetectionsystem.viewpage.ViewpageManage_Activity;

import static cn.edu.nuc.codetectionsystem.fragment.CoDataFragment.instance;

public class PhoneFragment extends BaseFragment {


    Handler mHandler;

    private static List<Car> carList = new ArrayList<>();
    public static CarAdapter adapter;
    private ListView car_listview;
    private ImageButton car_add;
    private ImageView warn;

    private String number,username_p,gender_p;
    private List<Cars> cars;
    private String get;
    private String license;
    private List<List<Integer>> data_mg;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView co_one_tv;
    private TextView co_two_tv;
    private TextWatcher one_watcher;
    private TextWatcher two_watcher;
    private List<String> licenses ;

    private Integer one_data,two_data;
    String  date;

    public static List<Integer> first,second;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phone_fragment,null);

        swipeRefreshLayout=view.findViewById(R.id.linear);

       // init();

//        adapter = new CarAdapter(getContext(), R.layout.car_item, carList);
        car_listview = (ListView) view.findViewById(R.id.car_listview);
        car_add =(ImageButton)view.findViewById(R.id.car_add);
        warn =(ImageView)view.findViewById(R.id.warn_iv);
        co_one_tv=(TextView)view.findViewById(R.id.co_one_tv);
        co_two_tv=(TextView)view.findViewById(R.id.co_two_tv);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                //carList.clear();
                init();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        init();


        car_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = carList.get(position);



            }
        });
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        adapter = new CarAdapter(getContext(), R.layout.car_item, carList,licenses);
                        car_listview.setAdapter(adapter);
                        break;
                }
            }
        };


        car_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(getContext(), Car_ADD_Activity.class);
                startActivity(intent1);
            }
        });

        initWatcher();

        return view;
    }

    public void initWatcher() {
        one_watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        };

        two_watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                two_data =Integer.parseInt(co_two_tv.getText().toString());
                if(Integer.valueOf(two_data)>50){
                    warn.setVisibility(View.VISIBLE);
                    co_two_tv.setTextColor(Color.RED);
                }

            }
        };

    }
    @Override
    public void lazyLoad() {

    }

    public static PhoneFragment newInstance(String param1) {
        PhoneFragment fragment = new PhoneFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }


    private void init(){

        username_p = SaveUtils.getSettingNote(getContext(), "userInfo", "username");
        number = SaveUtils.getSettingNote(getContext(), "userInfo", "number");
        gender_p = SaveUtils.getSettingNote(getContext(), "userInfo", "gender");
        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        String year = df.format(new Date());
        SimpleDateFormat df1 = new SimpleDateFormat("MM");//设置日期格式
        String month = df1.format(new Date());
        SimpleDateFormat df2 = new SimpleDateFormat("dd");//设置日期格式
        String day = df2.format(new Date());
        date = year+month+day;
        instance();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<List<Integer>> data_mgs_ = new ArrayList<>();
                String url = "http://47.94.19.124:8080/Winds/android/concentration?number=" + number + "&date="+date;
                get = GetPostUtil.sendGetRequest(url);
                Log.e("CO", "run: " + get);
                List<Cars> car;
                List<String> licenses_ = new ArrayList<>();
                try {
                    CarsJson carsJson = null;
                    JSONObject jsonObject = new JSONObject(get);
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    car = carsJson.jsonTOObject(String.valueOf(jsonArray1));
                    cars = car;
                    carList.clear();

                    for (int i = 0; i < car.size(); i++) {
                        data_mgs_.addAll(cars.get(i).getData_mg());
                        license = cars.get(i).getLicense();
                        licenses_.add(license);
                        data_mg = cars.get(i).getData_mg();

                        for (int j = 0;j<data_mg.size();j++){
                            if(data_mg.get(j).size()!= 12){
                                for(int k = data_mg.get(j).size();k<12;k++){
                                    data_mg.get(j).add(0);
                                }
                            }
                        }
                        licenses = licenses_;
                        Log.d("data_mg000:", "run: "+data_mg);
                        Car car1 = new Car(R.drawable.theme3,R.drawable.ic_warn,"用户：",username_p,"电话:",
                                number,"查看记录",R.drawable.ic_license,license,
                                R.drawable.ic_sensor,R.drawable.ic_sensor,judgeDateOne(data_mg),judgeDateTwo(data_mg),"ppm","ppm",R.drawable.ic_delete);
                        carList.add(car1);

                    }
                    Dialog_self.init(data_mgs_);

                    Message message=new Message();
                    message.what=0;
                    Bundle bundle = new Bundle();
                    bundle.putString("get",get);
                    message.setData(bundle);
                    mHandler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }

    private String judgeDateOne(List<List<Integer>> date){
        if (date.size()==0){
            return "暂无";
        }
        int backtime = 1;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Log.d("this time is ", "judgeDate: " +hour);
        if (hour%2!=0){
            hour = hour-1;
        }
        switch (hour){
            case 0:
                backtime = date.get(0).get(0);
                break;
            case 2:
                backtime = date.get(0).get(1);
                break;
            case 4:
                backtime = date.get(0).get(2);
                break;
            case 6:
                backtime = date.get(0).get(3);
                break;
            case 8:
                backtime = date.get(0).get(4);
                break;
            case 10:
                backtime = date.get(0).get(5);
                break;
            case 12:
                backtime = date.get(0).get(6);
                break;
            case 14:
                backtime = date.get(0).get(7);
                break;
            case 16:
                backtime = date.get(0).get(8);
                break;
            case 18:
                backtime = date.get(0).get(9);
                break;
            case 20:
                backtime = date.get(0).get(10);
                break;
            case 22:
                backtime = date.get(0).get(11);
                break;
        }
        return String.valueOf(backtime);
////获取系统的日期
////年
//        int year = calendar.get(Calendar.YEAR);
////月
//        int month = calendar.get(Calendar.MONTH)+1;
////日
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
////获取系统时间
////小时
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
////分钟
//        int minute = calendar.get(Calendar.MINUTE);
////秒
//        int second = calendar.get(Calendar.SECOND);
//
//
    }

    private String judgeDateTwo(List<List<Integer>> date){
        if (date.size()==0){
            return "暂无";
        }
        int backtime = 1;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Log.d("this time is ", "judgeDate: " +hour);
        if (hour%2!=0){
            hour = hour-1;
        }
        switch (hour){
            case 0:
                backtime = date.get(1).get(0);
                break;
            case 2:
                backtime = date.get(1).get(1);
                break;
            case 4:
                backtime = date.get(1).get(2);
                break;
            case 6:
                backtime = date.get(1).get(3);
                break;
            case 8:
                backtime = date.get(1).get(4);
                break;
            case 10:
                backtime = date.get(1).get(5);
                break;
            case 12:
                backtime = date.get(1).get(6);
                break;
            case 14:
                backtime = date.get(1).get(7);
                break;
            case 16:
                backtime = date.get(1).get(8);
                break;
            case 18:
                backtime = date.get(1).get(9);
                break;
            case 20:
                backtime = date.get(1).get(10);
                break;
            case 22:
                backtime = date.get(1).get(11);
                break;
        }
        return String.valueOf(backtime);
    }
}
