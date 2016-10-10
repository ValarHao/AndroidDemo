package com.example.administrator.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 注册EventBus
        EventBus.getDefault().register(this);

        textView = (TextView) findViewById(R.id.id_tv);

        findViewById(R.id.id_btn_goto).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // 解除EventBus注册
    }

    /** EventBus 3.0版本后使用@Subscribe注解的方式表明该方法为订阅者处理函数
     *  threadMode有四种可选值：ThreadMode.MAIN => 在UI线程执行
     *                          ThreadMode.BACKGROUND => 在后台线程执行
     *                          ThreadMode.ASYNC => 创建新的子线程执行
     *                          ThreadMode.POSTING => 在发送线程执行
     *  该订阅者接收哪种EventBus.post主要取决于订阅函数内的参数匹配 */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusMsg(EventBusMsg eventBusMsg) {
        String msg = eventBusMsg.getMsg();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        textView.setText(msg);
    }
}
