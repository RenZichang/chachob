//实现功能：
// 在两个框中输入用户名和密码->点击按钮，上传到云端，获得返回确认信息->
// 正确，则跳转到下一活动；错误，则弹出toast，提示错误
package com.example.a1.chachob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//继承了OnClickListener()接口
public class login_activity extends Activity implements View.OnClickListener {
    private information_class login_inf;
    private EditText username, password;
    private String debugTag = "debug_wrong_rzc";

    //重写活动创建时调用的onCreate()方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //初始化
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Button loginbutton = (Button) findViewById(R.id.login_activity_loginbutton);
        this.login_inf = new information_class();
        this.username = (EditText) findViewById(R.id.login_activity_username);
        this.password = (EditText) findViewById(R.id.login_activity_password);
        TextView forget = (TextView) findViewById(R.id.login_activity_forget);
        TextView register = (TextView) findViewById(R.id.login_activity_register);
        TextView aboutus = (TextView) findViewById(R.id.login_activity_aboutus);
        //为button设置监听器
        loginbutton.setOnClickListener(this);
        forget.setOnClickListener(this);
        register.setOnClickListener(this);
        aboutus.setOnClickListener(this);
    }

    //监听器实现逻辑
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_loginbutton:
                login_inf.username = this.username.getText().toString();
                login_inf.password = this.password.getText().toString();
                //验证成功，则跳转到主菜单，否则提示错误
                if (getAuthorization()) {
                    Intent intent = new Intent(login_activity.this, main_menu_activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(login_activity.this, "用户名或密码输入错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_activity_forget:
                Toast.makeText(login_activity.this, "功能正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_activity_register:
                Toast.makeText(login_activity.this, "功能正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_activity_aboutus:
                Toast.makeText(login_activity.this, "功能正在开发中", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

    //访问云端服务器，获得登录授权
    private boolean getAuthorization() {
//        //如果为空，那么就不访问网络
//        if (login_inf.username.equals("") || login_inf.password.equals(""))
//            return false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建Client对象
                    Log.d(debugTag, "创建Client对象");
                    OkHttpClient client = new OkHttpClient();
                    //创建request对象
                    Log.d(debugTag, "创建request对象");
                    Request request = new Request.Builder()
                            .url("http://127.0.0.1/data")
                            .build();
                    //创建response对象
                    Log.d(debugTag, "创建response对象");
                    Response response = client.newCall(request).execute();
                    //获取返回值
                    Log.d(debugTag, "获取返回值");
                    String data = response.body().string();
                    //打印
                    Log.d(debugTag, data);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return false;
    }
}
