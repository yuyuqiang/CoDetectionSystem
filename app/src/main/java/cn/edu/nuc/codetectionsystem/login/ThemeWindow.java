package cn.edu.nuc.codetectionsystem.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import cn.edu.nuc.codetectionsystem.R;

public class ThemeWindow extends AppCompatActivity {

    private ImageView startwindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_window);
        startwindow=(ImageView) findViewById(R.id.startwindow);
        //startwindows.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        // getSupportActionBar().hide();//隐藏标题栏
        Thread myThread=new Thread(){//创建子线程
            @Override
            public void run() {
                try{
                    sleep(2000);//使程序休眠五秒
                    Intent it=new Intent(getApplicationContext(),Login_Activity.class);//启动MainActivity
                    startActivity(it);
                    finish();//关闭当前活动
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程
    }


}
