package com.example.a1.chachob;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.BDLocationListener;

import java.util.ArrayList;
import java.util.List;


public class main_menu_activity extends AppCompatActivity implements View.OnClickListener {
    public double longitude, latitude;
    public LocationClient myLocationClient;
    private String debugTag = "debug_wrong_rzc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        //为各个按钮设置监听器
        Button user = (Button) findViewById(R.id.main_menu_user);
        Button release = (Button) findViewById(R.id.main_menu_release);
        Button message = (Button) findViewById(R.id.main_menu_message);
        user.setOnClickListener(this);
        release.setOnClickListener(this);
        message.setOnClickListener(this);
        //初始化，声明一个LocationClient实例
        myLocationClient = new LocationClient(getApplicationContext());
        myLocationClient.registerLocationListener(new myLocationListener());
        //运行时权限
        List<String> reqPermissionList = new ArrayList<>();
        String[] permissionList = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        for (int i = 0; i < 3; i++) {
            if (ContextCompat.checkSelfPermission(this, permissionList[i]) != PackageManager.PERMISSION_GRANTED) {
                reqPermissionList.add(permissionList[i]);
            }
        }
        if (!reqPermissionList.isEmpty()) {
            String[] permissions = reqPermissionList.toArray(new String[reqPermissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            myLocationClient.start();
            Log.v(debugTag, "LocationClient已启动（已授权）");
        }
    }

    //跳转到其他三个活动中
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.main_menu_user:
                intent = new Intent(this, user_activity.class);
                startActivity(intent);
                break;
            case R.id.main_menu_release:
                intent = new Intent(this, release_activity.class);
                startActivity(intent);
                break;
            case R.id.main_menu_message:
                intent = new Intent(this, message_activity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    //终于到了一年一度的回调时间了，今年的回调胜出者是：运行时访问权限的回调方法onRequestPermissionsResult()!
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    Log.v(debugTag, "LocationClient已启动（初次授权）");
                    myLocationClient.start();
                    //这里的逻辑是：如果有相关权限，那么直接在上面的处理过程中就完成了，不需要申请权限，也就不会执行这里的语句了
                    //但是如果没有相关权限，那么会开始申请权限，申请完了再核对一下，如果没有同意所有权限，则弹出Toast；如果同意了所有权限，那么由于之前没有获取位置，现在应该再获取一下
                    //没有bug，因为每次获取位置之前都会检查一下是否拥有相关权限，没有就尝试申请，有就直接获取
                } else {
                    Log.v(debugTag, "申请权限的回调函数中出现异常");
                }
                break;
            default:
        }
    }

    //每次更新了位置信息都会回调此监听器
    public class myLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    main_menu_activity.this.longitude = location.getLongitude();
                    main_menu_activity.this.latitude = location.getLatitude();
                    Log.v(debugTag, "经度：" + main_menu_activity.this.longitude + "\n纬度：" + main_menu_activity.this.latitude);
                    Toast.makeText(main_menu_activity.this, "经度：" + main_menu_activity.this.longitude + "\n纬度：" + main_menu_activity.this.latitude, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //停止定位，节省电量
    @Override
    protected void onDestroy() {
        myLocationClient.stop();
        super.onDestroy();
    }

    //访问云端服务器，获得任务题目列表
    protected String[] myGetWorkList() {
        return new String[]{"有谁帮忙去图书馆打印东西", "买西瓜，有拼团的么？", "你好陌生人", "今天好晒呀/(ㄒoㄒ)/~~", "和北邮说再见", "泡衣服的盆子就不能放在水池台下面吗？？", "代码风格的重要程度", "这么大的太阳，男生们还是打个伞吧", "双黄蛋？？双黄蛋？？", "有谁帮忙去图书馆打印东西", "买西瓜，有拼团的么？", "你好陌生人", "今天好晒呀/(ㄒoㄒ)/~~", "和北邮说再见", "泡衣服的盆子就不能放在水池台下面吗？？", "代码风格的重要程度", "这么大的太阳，男生们还是打个伞吧", "双黄蛋？？双黄蛋？？", "有谁帮忙去图书馆打印东西", "买西瓜，有拼团的么？", "你好陌生人", "今天好晒呀/(ㄒoㄒ)/~~", "和北邮说再见", "泡衣服的盆子就不能放在水池台下面吗？？", "代码风格的重要程度", "这么大的太阳，男生们还是打个伞吧", "双黄蛋？？双黄蛋？？"};
    }
}