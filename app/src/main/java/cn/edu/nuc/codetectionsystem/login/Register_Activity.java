package cn.edu.nuc.codetectionsystem.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cn.edu.nuc.codetectionsystem.MainActivity;
import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.until.GetPostUtil;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText username_regist = null;
    private EditText password_regist =null;
    private EditText telephone_regist = null;
    private Button register_bt = null;
    private RadioGroup gendergroup = null;
    private RadioButton male = null;
    private RadioButton female = null;

    private String userNameValue,passwordValue,telephoneValue,genderValue;

    private String TAG = "这是一个标签";

    private String data;
    private String get;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        username_regist = (EditText)findViewById(R.id.username_regist);
        password_regist= (EditText)findViewById(R.id.password_regist);
        telephone_regist = (EditText)findViewById(R.id.telephone_regist);
        register_bt = (Button)findViewById(R.id.register_bt);
        gendergroup = (RadioGroup)findViewById(R.id.gender_group);
        male = (RadioButton)findViewById(R.id.male_Radio);
        female = (RadioButton)findViewById(R.id.female_Radio);

        register_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_bt:
                userNameValue = username_regist.getText().toString();
                passwordValue = password_regist.getText().toString();
                telephoneValue = telephone_regist.getText().toString();
                if(male.isChecked()){
                    genderValue = "男";
                }else if(female.isChecked()){
                    genderValue = "女";
                }else {
                    Toast.makeText(Register_Activity.this,"请选择性别",Toast.LENGTH_LONG).show();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this).
                        setTitle("注册信息").
                        setIcon(R.drawable.ic_zhuce1).
                        setMessage("账号： " + username_regist.getText().toString() + "\n密码： "
                                + password_regist.getText().toString()+"\n电话： "
                                + telephone_regist.getText().toString()+"\n性别："
                                + genderValue.toString()
                                + "\n请点击“确定”").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        String url = "http://192.168.137.1:8000/post_test/";
//                                        data ="userName="+username_regist.getText().toString()+"&passord="+password_regist.getText().toString()+"&gender="+genderValue.toString()+"&number="+telephone_regist.getText().toString()+"";
//                                        Log.e(TAG, "run: "+data );
//                                        get = GetPostUtil.sendPostRequest(url,data);
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Intent intent_register = new Intent(Register_Activity.this, MainActivity.class);
//                                                startActivity(intent_register);
//
//                                            }
//                                        });
//
//                                    }
//                                }).start();
                                Intent intent_register = new Intent(Register_Activity.this, MainActivity.class);
                                startActivity(intent_register);

                                    }
                        });
                builder.create().show();

                break;
        }
    }
}
