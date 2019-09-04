package cn.edu.nuc.codetectionsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import cn.edu.nuc.codetectionsystem.fragment.CoDataFragment;
import cn.edu.nuc.codetectionsystem.fragment.MineFragment;
import cn.edu.nuc.codetectionsystem.fragment.PhoneFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private ArrayList<Fragment> fragments;
    private BottomNavigationBar bottomNavigationBar;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    int lastSelectedPosition = 0;
    private CoDataFragment coDataFragment = null;
    private MineFragment mineFragment = null;
    private PhoneFragment phoneFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        init();

    }

    private void init() {

        //要先设计模式后再添加图标！
        //设置按钮模式  MODE_FIXED表示固定   MODE_SHIFTING表示转移
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor("#FF45C273") //选中颜色
                .setInActiveColor("#545252") //未选中颜色#e9e6e6
                .setBarBackgroundColor("#FF45C273");//导航栏背景色


        //设置背景风格
        // BACKGROUND_STYLE_STATIC表示静态的
        //BACKGROUND_STYLE_RIPPLE表示涟漪的，也就是可以变化的 ，跟随setActiveColor里面的颜色变化
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        //添加并设置图标、图标的颜色和文字
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_car, "车")).setActiveColor(R.color.colorWhite)
                .addItem(new BottomNavigationItem(R.drawable.ic_map, "图表")).setActiveColor(R.color.colorWhite)
                .addItem(new BottomNavigationItem(R.drawable.ic_wode1, "我 ")).setActiveColor(R.color.colorWhite)
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    //设置初始界面
    private void setDefaultFragment() {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.layFrame, phoneFragment.newInstance("Phone"));
        transaction.commit();
    }


    @Override
    public void onTabSelected(int position) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                if (phoneFragment == null) {
                    phoneFragment = phoneFragment.newInstance("Phone");

                }
                transaction.replace(R.id.layFrame, phoneFragment);
                break;
            case 1:

                if (coDataFragment == null) {
                    coDataFragment = coDataFragment.newInstance("Co");
                }

                transaction.replace(R.id.layFrame, coDataFragment);
                break;
            case 2:
                if (mineFragment == null) {
                    mineFragment = mineFragment.newInstance("Mine");
                }
                transaction.replace(R.id.layFrame, mineFragment);
                break;
        }
        // 事务提交
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {
        Log.d("dongtaiFragment", "onTabUnselected() called with: " + "position = [" + position + "]");

    }

    @Override
    public void onTabReselected(int position) {

    }
}
