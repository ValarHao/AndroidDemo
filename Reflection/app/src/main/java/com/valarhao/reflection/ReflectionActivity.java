package com.valarhao.reflection;

import android.app.Activity;
import android.os.Bundle;

/**
 * 通过反射获取该类信息
 */
public class ReflectionActivity extends Activity {

    public int a;
    private int b;
    private static int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
