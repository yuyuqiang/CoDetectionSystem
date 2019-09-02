package cn.edu.nuc.codetectionsystem.test;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * 折线图，3条
 *
 */
public class LineCharts {
    /**
     * 设置chart显示的样式
     *
     * @param mLineChart
     */
    public LineCharts(LineChart mLineChart) {
        // 是否在折线图上添加边框
        mLineChart.setDrawBorders(false);
        mLineChart.setDescription("（mg/m³）");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        mLineChart.setNoDataTextDescription("暂无数据");
        // 是否绘制背景颜色。
        // 如果mLineChart.setDrawGridBackground(false)，
        // 那么mLineChart.setGridBackgroundColor(Color.CYAN)将失效;
        mLineChart.setDrawGridBackground(false);
        mLineChart.setGridBackgroundColor(Color.CYAN);
        // 触摸
        mLineChart.setTouchEnabled(true);
        // 拖拽
        mLineChart.setDragEnabled(true);
        // 缩放
        mLineChart.setScaleEnabled(true);
        mLineChart.setPinchZoom(false);
        // 设置背景
        mLineChart.setBackgroundColor(Color.WHITE);
        // // 设置x,y轴的数据
        // mLineChart.setData(lineData);

        // 设置比例图标示，就是那个一组y的value的
        Legend mLegend = mLineChart.getLegend();
        mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);
        mLegend.setForm(LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(15.0f);// 字体
        mLegend.setTextColor(Color.BLUE);// 颜色
        // 沿x轴动画，时间2000毫秒。
        mLineChart.animateX(2000);
        mLineChart.getAxisRight().setEnabled(true); // 隐藏右边 的坐标轴(true不隐藏)
        mLineChart.getXAxis().setPosition(XAxisPosition.BOTTOM); // 让x轴在下面
        // // 隐藏左边坐标轴横网格线
        // mLineChart.getAxisLeft().setDrawGridLines(false);
        // 隐藏右边坐标轴横网格线
        mLineChart.getAxisRight().setDrawGridLines(false);
        // 隐藏X轴竖网格线
        // mLineChart.getXAxis().setDrawGridLines(false);
        // enable / disable grid lines
        // mLineChart.setDrawVerticalGrid(false); // 是否显示水平的表格线
        // mChart.setDrawHorizontalGrid(false);/ 让x轴在下面
    }

    /**
     * @param first
     *            数据点的数量。
     * @return
     */
    public LineData getLineData(List<Integer> first,List<Integer> second) {
        // ArrayList<String> x = new ArrayList<String>();
        // for (int i = 0; i < count; i++) {
        // // x轴显示的数据
        // x.add("周" + i);
        // }

            int count = 25;
        // y轴的数据
        ArrayList<Entry> y_had = new ArrayList<Entry>();
        ArrayList<Entry> y_wait = new ArrayList<Entry>();
        int k = 0;
        for (int i =0 ;i<first.size();i++){

                Entry entry = new Entry(first.get(i), k++);
                y_had.add(entry);

        }
        int l =0;
        for (int j =0 ;j<second.size();j++){
                Entry entry = new Entry(second.get(j), l++);
                y_wait.add(entry);
        }
        System.out.println("y_had = "+y_had);
        System.out.println("y_wait = "+y_wait);
        //ArrayList<Entry> y_yuqi = new ArrayList<Entry>();
//        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() * 100);
//            Entry entry = new Entry(val, i);
//            y_had.add(entry);
//        }
//        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() * 100 - 10);
//            Entry entry = new Entry(val, i);
//            y_wait.add(entry);
//        }
                System.out.println("y_had = "+y_had);
        System.out.println("y_wait = "+y_wait);
//        for (int i = 0; i < count; i++) {
//            float val = (float) (Math.random() * 100 - 10);
//            Entry entry = new Entry(val, i);
//            y_yuqi.add(entry);
//        }

        // y轴数据集
        LineDataSet mLineDataSet_had = new LineDataSet(y_had, "1号传感器");
        LineDataSet mLineDataSet_wait = new LineDataSet(y_wait, "2号传感器");
       // LineDataSet mLineDataSet_yuqi = new LineDataSet(y_yuqi, "3号传感器");

        // 折线的颜色
        mLineDataSet_had.setColor(Color.parseColor("#00C0BF"));
        mLineDataSet_wait.setColor(Color.parseColor("#F26077"));
       // mLineDataSet_yuqi.setColor(Color.parseColor("#DEAD26"));
        setLineStyle(mLineDataSet_had);
        setLineStyle(mLineDataSet_wait);
       // setLineStyle(mLineDataSet_yuqi);

        ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
        mLineDataSets.add(mLineDataSet_had);
        mLineDataSets.add(mLineDataSet_wait);
       // mLineDataSets.add(mLineDataSet_yuqi);

        LineData mLineData = new LineData(getXAxisValues(), mLineDataSets);

        return mLineData;
    }

    /**
     * 设置折线样式，除了颜色（颜色单独设置）
     *
     * @param mLineDataSet
     */
    public void setLineStyle(LineDataSet mLineDataSet) {
        // 用y轴的集合来设置参数
        // 线宽
        mLineDataSet.setLineWidth(1.0f);
        // 显示的圆形大小
        mLineDataSet.setCircleSize(1.0f);
        // // 折线的颜色
        // mLineDataSet.setColor(Color.DKGRAY);
        // 圆球的颜色
        mLineDataSet.setCircleColor(Color.GREEN);
        // 设置mLineDataSet.setDrawHighlightIndicators(false)后，
        // Highlight的十字交叉的纵横线将不会显示，
        // 同时，mLineDataSet.setHighLightColor(Color.CYAN)失效。
        mLineDataSet.setDrawHighlightIndicators(true);
        // 按击后，十字交叉线的颜色
        mLineDataSet.setHighLightColor(Color.CYAN);
        // 设置这项上显示的数据点的字体大小。
        mLineDataSet.setValueTextSize(8.0f);
        // mLineDataSet.setDrawCircleHole(true);
        // 改变折线样式，用曲线。
        // mLineDataSet.setDrawCubic(true);
        // 默认是直线
        // 曲线的平滑度，值越大越平滑。
        // mLineDataSet.setCubicIntensity(0.2f);
        // 填充曲线下方的区域，红色，半透明。
        // mLineDataSet.setDrawFilled(true);
        // mLineDataSet.setFillAlpha(128);
        // mLineDataSet.setFillColor(Color.RED);

        // 填充折线上数据点、圆球里面包裹的中心空白处的颜色。
        mLineDataSet.setCircleColorHole(Color.YELLOW);
        // 设置折线上显示数据的格式。如果不设置，将默认显示float数据格式。
        mLineDataSet.setValueFormatter(new ValueFormatter() {

            @Override
            public String getFormattedValue(float value, Entry entry,
                                            int dataSetIndex, ViewPortHandler viewPortHandler) {
                int n = (int) value;
                String s = "" + n;
                return s;
            }
        });
    }

    public ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<String>();
        xAxis.add("00:00");
//        xAxis.add("01:00");
        xAxis.add("02:00");
//        xAxis.add("03:00");
        xAxis.add("04:00");
//        xAxis.add("05:00");
        xAxis.add("06:00");
//        xAxis.add("07:00");
        xAxis.add("08:00");
//        xAxis.add("09:00");
        xAxis.add("10:00");
//        xAxis.add("11:00");
        xAxis.add("12:00");
//        xAxis.add("13:00");
        xAxis.add("14:00");
//        xAxis.add("15:00");
        xAxis.add("16:00");
//        xAxis.add("17:00");
        xAxis.add("18:00");
//        xAxis.add("19:00");
        xAxis.add("20:00");
//        xAxis.add("21:00");
        xAxis.add("22:00");
//        xAxis.add("23:00");
//        xAxis.add("24:00");
        return xAxis;
    }
}