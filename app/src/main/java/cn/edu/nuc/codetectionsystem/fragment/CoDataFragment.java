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
import android.widget.TextView;

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
    private  String get;
    String  date;
    private  List<Cars> cars;
    private List<String> licenses = new ArrayList<>();
    private List<List<Integer>> data_mgs= new ArrayList<>();

    Handler mHandler;
    private boolean mHasLoadedOnce;
    private boolean isPrepared;

    private TextView tv_shop;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null){
            mView = inflater.inflate(R.layout.co_data_fragment, container, false);
            isPrepared = true;

//        实现懒加载
            lazyLoad();
        }
        //缓存的mView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        tv_shop = (TextView)mView.findViewById(R.id.tv_shop);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SendRequest();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        Log.i(TAG, "onActivityCreated: licenses"+licenses);
                        Log.i(TAG, "onActivityCreated: licenses"+data_mgs);
                        BarChart chart_bar = (BarChart) getActivity().findViewById(R.id.chart_bar);
                        mBarChart3s = new BarChart3s(chart_bar);
                        final BarData data = new BarData(mBarChart3s.getXAxisValues(licenses), mBarChart3s.getDataSet(licenses,data_mgs));
                        // 设置数据
                        chart_bar.setData(data);
                        chart_bar.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                            @Override
                            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

                                tv_shop.setText("汽车" + (e.getXIndex() + 1) + "CO浓度变化");

                                LineChart chart = (LineChart) getActivity().findViewById(R.id.chart);

                                switch (e.getXIndex()) {
                                    case 0:
                                        System.out.println("0  ");
                                        // 折线图
                                        lineCharts = new LineCharts(chart);
                                        // 制作7个数据点（沿x坐标轴）
                                        LineData mLineData = lineCharts.getLineData(data_mgs.get(0),data_mgs.get(1));
                                        // setChartStyle(chart, mLineData, Color.WHITE);
                                        // 设置x,y轴的数据
                                        chart.setData(mLineData);
                                        break;
                                    case 1:
                                        System.out.println("1  ");
                                        lineCharts = new LineCharts(chart);
                                        // 制作7个数据点（沿x坐标轴）
                                        LineData mLineData1 = lineCharts.getLineData(data_mgs.get(2),data_mgs.get(3));
                                        // setChartStyle(chart, mLineData, Color.WHITE);
                                        // 设置x,y轴的数据
                                        chart.setData(mLineData1);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            @Override
                            public void onNothingSelected() {
                            }
                        });
//                         默认显示
//                         折线图
                        LineChart chart = (LineChart) getActivity().findViewById(R.id.chart);
                        lineCharts = new LineCharts(chart);
                        // 制作7个数据点（沿x坐标轴）
                        LineData mLineData = lineCharts.getLineData(data_mgs.get(0),data_mgs.get(1));

                        // setChartStyle(chart, mLineData, Color.WHITE);
                        // 设置x,y轴的数据
                        chart.setData(mLineData);
                        break;
                    default:
                        break;
                }
            }

        };


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
                message.what=2;
                Bundle bundle = new Bundle();
                bundle.putString("get",get);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();
    }

    Handler handler = new Handler() {
        public void handleMessage(final Message msg) {
            List<String> licenses_= new ArrayList<>();
            List<List<Integer>> data_mgs_ = new ArrayList<>();
            if (msg.what == 2) {
                String get = msg.getData().getString("get");
                List<Cars> car;
                try {
                    CarsJson carsJson = null;
                    JSONObject jsonObject = new JSONObject(get);
                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                    car = carsJson.jsonTOObject(String.valueOf(jsonArray1));
                    for (int i = 0; i < car.size(); i++) {
                        licenses_.add(car.get(i).getLicense());
                        data_mgs_.addAll(car.get(i).getData_mg());
                    }
                    licenses = licenses_;
                    data_mgs = data_mgs_;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "handleMessage: licenses"+licenses+"data_mgs"+data_mgs);
            }
            Message message = new Message();
            message.what = 0;
            message.obj = "子线程发送的消息Hi~Hi";
            mHandler .sendMessage(message);

        }
    };

    public static CoDataFragment newInstance(String param1) {
        CoDataFragment fragment = new CoDataFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        //填充各控件的数据
        mHasLoadedOnce = true;
    }

    public void init(){
        Log.e(TAG, "init: "+licenses );


    }


}
