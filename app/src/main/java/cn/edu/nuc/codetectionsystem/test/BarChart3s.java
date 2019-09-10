package cn.edu.nuc.codetectionsystem.test;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

/**
 * 柱状图
 *
 * @author zp
 * <p>
 * 2016年2月2日
 */
public class BarChart3s {


    //          System.out.println(df.format(new Date()));// new Date()为获取当前系统时间


    public BarChart3s(BarChart chart) {
        // 数据描述
        chart.setDescription("");
        // 动画
        chart.animateY(1000);
        // 设置是否可以触摸
        chart.setTouchEnabled(true);
        // 是否可以拖拽
        chart.setDragEnabled(true);
        // 是否可以缩放
        chart.setScaleEnabled(true);
        // 集双指缩放
        chart.setPinchZoom(true);
        // 隐藏右边的坐标轴
        chart.getAxisRight().setEnabled(false);
        // 隐藏左边的左边轴
        chart.getAxisLeft().setEnabled(true);

        Legend mLegend = chart.getLegend(); // 设置比例图标示
        // 设置窗体样式
        mLegend.setForm(LegendForm.SQUARE);
        // 字体
        mLegend.setFormSize(4f);
        // 字体颜色
        mLegend.setTextColor(Color.parseColor("#7e7e7e"));

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);//设置x轴label间隔
        chart.invalidate();


    }

    public ArrayList<BarDataSet> getDataSet(List<String> licenses, List<List<Integer>> data_mgs) {
        List<Integer> average = new ArrayList<>();
        for (List i : data_mgs) {
            Integer sum = 0;
            for (int j = 0; j < i.size(); j++) {
                sum += Integer.parseInt(String.valueOf(i.get(j)));
            }
            average.add(sum / i.size());
        }
        System.out.println("average" + average);
        ArrayList<BarEntry> valueSet3 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> valueSet1 = new ArrayList<BarEntry>();
        for (int j = 0; j < average.size(); j++) {
//            if (j<2){
//                BarEntry v1e1 = new BarEntry(average.get(j), j%2); // Jan
//                valueSet1.add(v1e1);
//            }else {
//                BarEntry v3e1 = new BarEntry(average.get(j), j%2); // Jan
//                valueSet3.add(v3e1);
//            }
            switch (j) {
                case 0:
                    BarEntry v1e1 = new BarEntry(average.get(0), 0); // Jan
                    valueSet1.add(v1e1);
                    BarEntry v3e12 = new BarEntry(average.get(1), 0); // Jan
                    valueSet3.add(v3e12);
                    break;
                case 1:
                    BarEntry v1e2 = new BarEntry(average.get(2), 1); // Jan
                    valueSet1.add(v1e2);
                    BarEntry v3e1 = new BarEntry(average.get(3), 1); // Jan
                    valueSet3.add(v3e1);
                    break;
                default:
                    break;
            }
        }
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet2 = new ArrayList<BarEntry>();
        BarEntry v2e1 = new BarEntry(100.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(100.000f, 1); // Feb
        valueSet2.add(v2e2);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "传感器（1）");
        barDataSet1.setColor(Color.parseColor("#00C0BF"));
        barDataSet1.setBarShadowColor(Color.parseColor("#01000000"));

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "标准浓度");
        barDataSet2.setColor(Color.parseColor("#DEAD26"));
        barDataSet2.setBarShadowColor(Color.parseColor("#01000000"));

        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "传感器（2）");
        barDataSet3.setColor(Color.parseColor("#F26077"));
        barDataSet3.setBarShadowColor(Color.parseColor("#01000000"));

        dataSets = new ArrayList<BarDataSet>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);

        return dataSets;
    }


    public ArrayList<String> getXAxisValues(List<String> licenses) {
        //List<String> licenses= new ArrayList<>();
        Log.e("5555", "getXAxisValues: " + licenses);
        ArrayList<String> xAxis = new ArrayList<String>();
        for (int i = 0; i < licenses.size(); i++) {
            xAxis.add(licenses.get(i));

        }
        System.out.println("license222" + licenses);

        return xAxis;
    }


}