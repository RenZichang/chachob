package com.example.a1.chachob;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class user_activity extends AppCompatActivity {
    //插入两个按键，一个是个人资料，一个是用户设置
    private  Button user_identity,user_setting,user_mytask;
    String identity = "come to identity";
    String setting = "come to setting";
    String mytask = "come to my task";
    String bug = "Something wrong";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
        user_identity = findViewById(R.id.button_user_identity);
        user_setting = findViewById(R.id.button_user_setting);
        user_mytask = findViewById(R.id.button_user_mytask);
        //在去设置其他的界面
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_user_identity:
                //intent = new Intent(main_menu_activity.this, user_activity.class);
                //startActivity(intent);
                Log.v(identity,"come to identity");
                break;
            case R.id.button_user_mytask:
                //intent = new Intent(main_menu_activity.this, release_activity.class);
                //startActivity(intent);
                Log.v(mytask,"come to mytask");
                break;
            case R.id.button_user_setting:
                //intent = new Intent(main_menu_activity.this, message_activity.class);
                //startActivity(intent);
                Log.v(setting,"come to mytask");
                break;
            default:
                Log.v(bug, "something wrong");
        }
    }
}
