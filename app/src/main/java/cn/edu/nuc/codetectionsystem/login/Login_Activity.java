package cn.edu.nuc.codetectionsystem.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.edu.nuc.codetectionsystem.MainActivity;
import cn.edu.nuc.codetectionsystem.R;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText username = null;
    private EditText password = null;
    private Button login_bt = null;
    private TextView register = null;
    private Button cuo_png;
    private Button eye_png;
    private TextWatcher username_watcher;
    private TextWatcher password_watcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login_bt =(Button)findViewById(R.id.login_bt);
        register =(TextView)findViewById(R.id.register);
        cuo_png = (Button)findViewById(R.id.cuo_png);
        eye_png = (Button)findViewById(R.id.eye_png);

        initWatcher();

        login_bt.setOnClickListener(this);
        register.setOnClickListener(this);
        cuo_png.setOnClickListener(this);
        eye_png.setOnClickListener(this);
    }


    private void initWatcher() {
        username_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                password.setText("");
                if (s.toString().length() > 0) {
                    cuo_png.setVisibility(View.VISIBLE);
                } else {
                    cuo_png.setVisibility(View.INVISIBLE);
                }
            }
        };

        password_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    eye_png.setVisibility(View.VISIBLE);
                }else{
                    eye_png.setVisibility(View.INVISIBLE);
                }
            }
        };

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Intent intent_regist = new Intent(Login_Activity.this,Register_Activity.class);
                startActivityForResult(intent_regist,0);//请求码为0
                break;

            case R.id.login_bt:
                Intent intent_login = new Intent(Login_Activity.this, MainActivity.class);
                startActivity(intent_login);
                break;
        }
    }
}
