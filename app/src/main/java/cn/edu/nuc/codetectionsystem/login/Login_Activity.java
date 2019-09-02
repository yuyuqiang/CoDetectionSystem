package cn.edu.nuc.codetectionsystem.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.nuc.codetectionsystem.MainActivity;
import cn.edu.nuc.codetectionsystem.R;
import cn.edu.nuc.codetectionsystem.models.User;
import cn.edu.nuc.codetectionsystem.models.UserJson;
import cn.edu.nuc.codetectionsystem.until.GetPostUtil;
import cn.edu.nuc.codetectionsystem.until.SaveUtils;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText telephone = null;
    private EditText password = null;
    private Button login_bt = null;
    private TextView register = null;
    private Button cuo_png;
    private Button eye_png;
    private TextWatcher username_watcher;
    private TextWatcher password_watcher;
    private String get;
    private List<User> users;
    private UserJson userJson;
    private String passwordValue;
    private String number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        telephone = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login_bt =(Button)findViewById(R.id.login_bt);
        register =(TextView)findViewById(R.id.register);
        cuo_png = (Button)findViewById(R.id.cuo_png);
        eye_png = (Button)findViewById(R.id.eye_png);



        login_bt.setOnClickListener(this);
        register.setOnClickListener(this);
        cuo_png.setOnClickListener(this);
        eye_png.setOnClickListener(this);

        initWatcher();
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        number = telephone.getText().toString();
                        passwordValue = password.getText().toString();
                        Log.i("", "run: "+number+"password"+passwordValue);

                        String url = "http://47.94.19.124:8080/Winds/android/login?";
                        String data ="number="+number+"&password="+passwordValue;
                        get = GetPostUtil.sendPostRequest(url,data);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //users = userJson.jsonTOObject(get);
                                List<User> user;
                                try {

                                    UserJson userJson= null;
                                    JSONObject jsonObject = new JSONObject(get);
                                    JSONArray jsonArray1 = jsonObject.getJSONArray("data");

                                    user = userJson.jsonTOObject(String.valueOf(jsonArray1));
                                    users=user;
                                            Log.e("", String.valueOf(users));
                                            for (User i :users) {
                                                Log.i("", "run: user " + i.getUserName());
                                            }
                                    if (number.toString().length()<=16 ) {
                                        if (passwordValue.toString().length()==6){
                                            for (int i=0;i<user.size();i++){
                                                if(number.equals(users.get(i).getNumber().toString())&&passwordValue.equals(users.get(i).getPassword().toString())){

                                                    Intent intent_login = new Intent(Login_Activity.this, MainActivity.class);
                                                    intent_login.putExtra("id",users.get(i).getId());
                                                    Map<String, String> map = new HashMap<String, String>(); //本地保存数据
                                                    map.put("username",users.get(i).getUserName());
                                                    map.put("password",users.get(i).getPassword());
                                                    map.put("number",users.get(i).getNumber());
                                                    map.put("gender",users.get(i).getGender());
                                                    SaveUtils.saveSettingNote(Login_Activity.this, "userInfo",map);
                                                    Toast.makeText(Login_Activity.this, "登陆成功！", Toast.LENGTH_LONG).show();
                                                    startActivity(intent_login);
                                                }
                                            }

                                        }else{
                                            Toast.makeText(Login_Activity.this, "密码格式错误！", Toast.LENGTH_LONG).show();}
                                    }else{Toast.makeText(Login_Activity.this, "账号格式错误！", Toast.LENGTH_LONG).show();}
//                                            }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //判断信息是否符合


                            }
                        });

                    }
                }).start();




                break;
        }
    }
}
