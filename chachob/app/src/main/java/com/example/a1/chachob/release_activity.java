package com.example.a1.chachob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class release_activity extends AppCompatActivity implements View.OnClickListener{
    private String release_position;
    private String release_describe;
    private String release_payback;
    private String other;
    private Button button_release;
    private EditText edit_release_position,edit_release_describe,edit_release_payback;
    String debugTag = "请正确输入";
    String success="发布成功";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_layout);

        button_release = findViewById(R.id.button_release);
        button_release.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_release:
                //传数据有问题
                release_position=edit_release_position.getText().toString();
                release_describe=edit_release_describe.getText().toString();
                release_payback=edit_release_payback.getText().toString();
                //还需要将这部分传出
                Log.v(success,"发布成功");
                break;
            default:
                Log.v(debugTag, "something is wrong in intent");
        }
    }
}

