package com.valarhao.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.valarhao.annotation.inject.ViewInject;
import com.valarhao.annotation.inject.ViewInjectUtil;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.btn1)
    private Button mBtn1;
    @ViewInject(R.id.btn2)
    private Button mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewInjectUtil.injectViews(this); //将所有注解的View注入

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You click button1", Toast.LENGTH_SHORT).show();
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You click button2", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
