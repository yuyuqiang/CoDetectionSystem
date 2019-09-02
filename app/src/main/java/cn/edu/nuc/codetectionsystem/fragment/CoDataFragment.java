package cn.edu.nuc.codetectionsystem.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.edu.nuc.codetectionsystem.MainActivity;
import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.models.Cars;
import cn.edu.nuc.codetectionsystem.models.CarsJson;
import cn.edu.nuc.codetectionsystem.test.BarChart3s;
import cn.edu.nuc.codetectionsystem.test.LineCharts;
import cn.edu.nuc.codetectionsystem.until.GetPostUtil;
import cn.edu.nuc.codetectionsystem.until.SaveUtils;

import static android.content.ContentValues.TAG;

public class CoDataFragment extends BaseFragment {


    private BarChart3s mBarChart3s;
    private LineCharts lineCharts;
    private static String get;
    String  date;
    private static List<Cars> cars;
    private String license;
    private List<List<Integer>> data_mg;
    public List<String> licenses = new ArrayList<>();
    public  List<List<Integer>> data_mgs= new ArrayList<>();
    //private  String a[]=new String[10];



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.co_data_fragment, null);
        SendRequest();
//        init();
        // 柱状图
        BarChart chart_bar = (BarChart) view.findViewById(R.id.chart_bar);
        mBarChart3s = new BarChart3s(chart_bar);
        BarData data = new BarData(mBarChart3s.getXAxisValues(), mBarChart3s.getDataSet(licenses,data_mgs));
        // 设置数据
       chart_bar.setData(data);
        chart_bar
                .setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                    @Override
                    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                        // ToastUtil.showToast(Activity_detail_gongdan.this,
                        // "点击了~~" + e.getXIndex());
                        // tv_shop.setText("门店" + (e.getXIndex() + 1) + "近一周交易额");

                        // 折线图
                        LineChart chart = (LineChart) view.findViewById(R.id.chart);
                        lineCharts = new LineCharts(chart);
                        // 制作7个数据点（沿x坐标轴）
                        LineData mLineData = lineCharts.getLineData(25);
                        // setChartStyle(chart, mLineData, Color.WHITE);
                        // 设置x,y轴的数据
                        chart.setData(mLineData);
                    }

                    @Override
                    public void onNothingSelected() {
                    }
                });

        // 默认显示
        // 折线图
        LineChart chart = (LineChart) view.findViewById(R.id.chart);
        lineCharts = new LineCharts(chart);
        // 制作7个数据点（沿x坐标轴）
        LineData mLineData = lineCharts.getLineData(25);
        // setChartStyle(chart, mLineData, Color.WHITE);
        // 设置x,y轴的数据
        chart.setData(mLineData);


        return view;
    }


    public  void SendRequest() {
        final String number = SaveUtils.getSettingNote(getContext(), "userInfo", "number");
        Log.e(TAG, "SendRequest: " + number);

        SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
        String year = df.format(new Date());
        SimpleDateFormat df1 = new SimpleDateFormat("MM");//设置日期格式
        String month = df1.format(new Date());
        SimpleDateFormat df2 = new SimpleDateFormat("dd");//设置日期格式
        String day = df2.format(new Date());
        date = year+month+day;
        Log.e(TAG, "SendRequest: " + date);

        new Thread(new Runnable() {

            @Override
            public void run() {
                String url = "http://47.94.19.124:8080/Winds/android/concentration?number=" + number + "&date=20190802";
                get = GetPostUtil.sendGetRequest(url);
                Log.e("CO", "run: " + get);
                Message message=new Message();
                message.what=1;
                Bundle bundle = new Bundle();
                bundle.putString("get",get);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(final Message msg) {
            List<String> licenses_= new ArrayList<>();
            List<List<Integer>> data_mgs_ = new ArrayList<>();
            if (msg.what == 1) {
                String get = msg.getData().getString("get");
                List<Cars> car;
                try {
                    CarsJson carsJson = null;
                    JSONObject jsonObject = new JSONObject(get);
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    car = carsJson.jsonTOObject(String.valueOf(jsonArray1));
                    cars = car;
                    for (int i = 0; i < car.size(); i++) {
                        license = cars.get(i).getLicense();
                        data_mg = cars.get(i).getData_mg();
                        licenses_.add(license);
                        data_mgs_.addAll(data_mg);
//                        licenses .addAll(licenses_);
//                        data_mgs.addAll(data_mgs_);
                    }
                    licenses = licenses_;
                    data_mgs = data_mgs_;
            } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "handleMessage: licenses"+licenses+"data_mgs"+data_mgs);
//                for (List i : data_mgs){
//                    Integer sum =0;
//                    for(int j = 0;j<i.size();j++){
//                        sum+=Integer.parseInt(String.valueOf(i.get(j)));
//                        System.out.println(i.get(j));
//                    }
//                    System.out.println("sum"+sum/i.size());
//                }
            }
        }
    };
    public static CoDataFragment newInstance(String param1) {
        CoDataFragment fragment = new CoDataFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

//    public ArrayList<String> getXAxisValues() {
//
//        ArrayList<String> xAxis = new ArrayList<String>();
//        xAxis.add(licenses[0]);
//        xAxis.add(licenses[1]);
//        xAxis.add("车辆三");
//        xAxis.add("车辆四");
//        xAxis.add("车辆五");
//        xAxis.add("车辆六");
//        return xAxis;
//    }

    @Override
    public void lazyLoad() {

    }

    public void init(){
        Log.e(TAG, "init: "+licenses );


    }


}
