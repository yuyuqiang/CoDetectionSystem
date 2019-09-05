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

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.cars.Car;
import cn.edu.nuc.codetectionsystem.cars.CarAdapter;
import cn.edu.nuc.codetectionsystem.models.Cars;
import cn.edu.nuc.codetectionsystem.models.CarsJson;
import cn.edu.nuc.codetectionsystem.until.GetPostUtil;
import cn.edu.nuc.codetectionsystem.until.SaveUtils;
import cn.edu.nuc.codetectionsystem.viewpage.ViewpageManage_Activity;

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

    private Integer one_data,two_data;

    public static List<Integer> first,second;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phone_fragment,null);

        swipeRefreshLayout=view.findViewById(R.id.linear);

       // init();

        adapter = new CarAdapter(getContext(), R.layout.car_item, carList);
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
                one_data =Integer.parseInt(co_one_tv.getText().toString());
                if(one_data>300){
                    warn.setVisibility(View.VISIBLE);
                    co_one_tv.setTextColor(Color.RED);
                }


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
                if(two_data>300){
                    warn.setVisibility(View.VISIBLE);
                    co_two_tv.setTextColor(Color.RED);
                }

            }
        };

    }

    public static PhoneFragment newInstance(String param1) {
        PhoneFragment fragment = new PhoneFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void lazyLoad() {

    }

    public static void init2(List<Integer> first, List<Integer> second) {
        first = first;
        second = second;
    }

    private void init(){

        username_p = SaveUtils.getSettingNote(getContext(), "userInfo", "username");
        number = SaveUtils.getSettingNote(getContext(), "userInfo", "number");
        gender_p = SaveUtils.getSettingNote(getContext(), "userInfo", "gender");


        new Thread(new Runnable() {

            @Override
            public void run() {
                String url = "http://47.94.19.124:8080/Winds/android/concentration?number=" + number + "&date=20190802";
                get = GetPostUtil.sendGetRequest(url);
                Log.e("CO", "run: " + get);
                List<Cars> car;
                try {
                    CarsJson carsJson = null;
                    JSONObject jsonObject = new JSONObject(get);
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    car = carsJson.jsonTOObject(String.valueOf(jsonArray1));
                    cars = car;
                    carList.clear();
                    for (int i = 0; i < car.size(); i++) {
                        license = cars.get(i).getLicense();
                        data_mg = cars.get(i).getData_mg();

                        Car car1 = new Car(R.drawable.theme3,R.drawable.ic_warn,"用户：",username_p,"电话:",
                                number,"查看记录",R.drawable.ic_license,license,
                                R.drawable.ic_sensor,R.drawable.ic_sensor,"300","200","ppm","ppm",R.drawable.ic_delete);
                        carList.add(car1);
                        Message message=new Message();
                        message.what=0;
                        Bundle bundle = new Bundle();
                        bundle.putString("get",get);
                        message.setData(bundle);
                        mHandler.sendMessage(message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }


}
