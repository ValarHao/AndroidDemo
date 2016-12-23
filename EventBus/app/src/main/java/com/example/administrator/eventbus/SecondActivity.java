package com.example.administrator.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/5/19.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.id_btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 发布者发布消息
                EventBus.getDefault().post(new EventBusMsg("SecondActivity向您问好！"));
            }
        });
    }
}
