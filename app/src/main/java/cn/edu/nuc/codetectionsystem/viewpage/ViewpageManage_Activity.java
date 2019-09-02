package cn.edu.nuc.codetectionsystem.viewpage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.codetectionsystem.R;

public class ViewpageManage_Activity extends AppCompatActivity implements View.OnClickListener{
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private ImageView cursor1;
    private ImageView cursor2;
    private ImageView cursor3;
    private ViewPager myViewPager;
    private List<Fragment> list;
    private TabFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpage_manage);

        initViews();
        // 设置菜单栏的点击事件
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        myViewPager.addOnPageChangeListener(new MyPagerChangeListener());

//把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new FirstViewpage());
        list.add(new SecondViewpage());
        list.add(new ThirdViewpage());
        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), list);
        myViewPager.setAdapter(adapter);
        myViewPager.setCurrentItem(0);  //初始化显示第一个页面
        text1.setTextColor(Color.GREEN);
    }

    public void initViews() {

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        cursor1 =(ImageView)findViewById(R.id.cursor1);
        cursor2 =(ImageView)findViewById(R.id.cursor2);
        cursor3 =(ImageView)findViewById(R.id.cursor3);

        myViewPager = (ViewPager) findViewById(R.id.viewPager);
        cursor1.setVisibility(View.VISIBLE);
        cursor2.setVisibility(View.INVISIBLE);
        cursor3.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                myViewPager.setCurrentItem(0);
                text1.setTextColor(Color.GREEN);
                text2.setTextColor(Color.BLACK);
                text3.setTextColor(Color.BLACK);
                cursor1.setVisibility(View.VISIBLE);
                cursor2.setVisibility(View.INVISIBLE);
                cursor3.setVisibility(View.INVISIBLE);
                break;
            case R.id.text2:
                myViewPager.setCurrentItem(1);
                text1.setTextColor(Color.BLACK);
                text2.setTextColor(Color.GREEN);
                text3.setTextColor(Color.BLACK);
                cursor1.setVisibility(View.INVISIBLE);
                cursor2.setVisibility(View.VISIBLE);
                cursor3.setVisibility(View.INVISIBLE);
                break;
            case R.id.text3:
                myViewPager.setCurrentItem(2);
                text1.setTextColor(Color.BLACK);
                text2.setTextColor(Color.BLACK);
                text3.setTextColor(Color.GREEN);
                cursor1.setVisibility(View.INVISIBLE);
                cursor2.setVisibility(View.INVISIBLE);
                cursor3.setVisibility(View.VISIBLE);
                break;
        }

    }

    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    text1.setTextColor(Color.GREEN);
                    text2.setTextColor(Color.BLACK);
                    text3.setTextColor(Color.BLACK);
                    cursor1.setVisibility(View.VISIBLE);
                    cursor2.setVisibility(View.INVISIBLE);
                    cursor3.setVisibility(View.INVISIBLE);

                    break;
                case 1:
                    text1.setTextColor(Color.BLACK);
                    text2.setTextColor(Color.GREEN);
                    text3.setTextColor(Color.BLACK);
                    cursor1.setVisibility(View.INVISIBLE);
                    cursor2.setVisibility(View.VISIBLE);
                    cursor3.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    text1.setTextColor(Color.BLACK);
                    text2.setTextColor(Color.BLACK);
                    text3.setTextColor(Color.GREEN);
                    cursor1.setVisibility(View.INVISIBLE);
                    cursor2.setVisibility(View.INVISIBLE);
                    cursor3.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}
