package cn.edu.nuc.codetectionsystem.fragment;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import java.util.List;

import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.test.LineCharts;

public class Dialog_self extends Dialog {

    public static List<List<Integer>> data_mgs;


    public Dialog_self(@NonNull Context context) {
        super(context);
    }

    public Dialog_self(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static void init(List<List<Integer>> data_mgs0) {
        data_mgs = data_mgs0;
    }


    public static class Builder {
        private Context context;
        private LineCharts lineCharts;
        private String textView;

        public Builder(Context context) {
            this.context = context;
        }


        public void setLineCharts(LineCharts lineCharts) {
            this.lineCharts = lineCharts;
        }

        public String getTextView() {
            return textView;
        }

        public void setTextView(String textView) {
            this.textView = textView;
        }


        public Dialog_self create() {
          //  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final Dialog_self dialog = new Dialog_self(context, R.style.Dialog);
            View layout = LayoutInflater.from(context).inflate(R.layout.dialog_window, null);

            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT));
            dialog.setContentView(layout);
            TextView dialog_tv =(TextView)layout.findViewById(R.id.dialog_tv);
            dialog_tv.setText(getTextView());
            LineChart chart = (LineChart)layout.findViewById(R.id.line_chart);
            lineCharts = new LineCharts(chart);
            LineData mLineData = lineCharts.getLineData(data_mgs.get(0),data_mgs.get(1));
            chart.setData(mLineData);






//            CoDataFragment coDataFragment = new CoDataFragment();
//            coDataFragment.init1();
            return dialog;




        }

    }

    }
