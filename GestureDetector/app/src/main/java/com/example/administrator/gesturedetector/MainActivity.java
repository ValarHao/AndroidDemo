package com.example.administrator.gesturedetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private GestureDetector mGestureDetector;
    private String tag = "Gesture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureDetector = new GestureDetector(new GestureListener()); // 手势监听
        mGestureDetector.setOnDoubleTapListener(new DoubleTapListener()); // 设置双击监听
        //mGestureDetector = new GestureDetector(new SimpleGestureListener()); // 使用SimpleOnGestureListener的子类

        TextView textView = (TextView) findViewById(R.id.id_tv);
        textView.setOnTouchListener(this);
        textView.setFocusable(true);
        textView.setClickable(true);
        textView.setLongClickable(true);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // 将捕捉到的motionEvent交给mGestureDetector
        return mGestureDetector.onTouchEvent(motionEvent);
    }

    /** 单击事件的接口 */
    private class GestureListener implements GestureDetector.OnGestureListener {

        /** 轻触触摸屏，由一个MotionEvent ACTION_DOWN触发 */
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            Log.d(tag, "omDown");
            Toast.makeText(MainActivity.this, "onDown", Toast.LENGTH_SHORT).show();
            return true;
        }

        /** 轻触触摸屏，尚未松开或拖动，由一个MotionEvent ACTION_DOWN触发 */
        @Override
        public void onShowPress(MotionEvent motionEvent) {
            Log.d(tag, "onShowPress");
            Toast.makeText(MainActivity.this, "onShowPress", Toast.LENGTH_SHORT).show();
        }

        /** 轻触触摸屏后松开，由一个MotionEvent ACTION_UP触发
         *  点击一下非常快的（不滑动）触发顺序：onDown->onSingleTapUp->onSingleTapConfirmed
         *  点击一下稍微慢点（不滑动）触发顺序：onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed */
        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Log.d(tag, "onSingleTapUp");
            Toast.makeText(MainActivity.this, "onSingleTapUp", Toast.LENGTH_SHORT).show();
            return true;
        }

        /** 按下触摸屏并拖动，由一个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
         *  滑屏/拖动：onDown->onScroll->...->onScroll->onFling */
        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.d(tag, "onScroll");
            Toast.makeText(MainActivity.this, "onScroll", Toast.LENGTH_SHORT).show();
            return true;
        }

        /** 长按触摸屏，由多个MotionEvent ACTION_DOWN触发
         *  触发顺序：onDown->onShowPress->onLongPress */
        @Override
        public void onLongPress(MotionEvent motionEvent) {
            Log.d(tag, "onLongPress");
            Toast.makeText(MainActivity.this, "onLongPress", Toast.LENGTH_SHORT).show();
        }

        /** 按下触摸屏，快速移动后松开，由一个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 一个ACTION_UP触发
         *  motionEvent：第1个ACTION_DOWN MotionEvent
         *  motionEvent1：最后一个ACTION_MOVE MotionEvent
         *  v：X轴上的移动速度，像素/秒
         *  v1：Y轴上的移动速度，像素/秒 */
        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.d(tag, "onFling");
            Toast.makeText(MainActivity.this, "onFling", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /** 双击事件的接口 */
    private class DoubleTapListener implements GestureDetector.OnDoubleTapListener {

        /** 单击事件，与onDoubleTap互斥，一次动作两者只能执行其中一个
         * 触发顺序是：OnDown->OnsingleTapUp->OnsingleTapConfirmed
         * onSingleTapUp，只要手抬起就会执行
         * onSingleTapConfirmed，如果双击的话，则onSingleTapConfirmed不会执行 */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            Log.d(tag, "onSingleTapConfirmed");
            Toast.makeText(MainActivity.this, "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();
            return true;
        }

        /** 双击事件 */
        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {
            Log.d(tag, "onDoubleTap");
            Toast.makeText(MainActivity.this, "onDoubleTap", Toast.LENGTH_SHORT).show();
            return true;
        }

        /** 双击间隔中发生的动作。指触发onDoubleTap以后，在双击之间发生的其它动作，包含down、up和move事件 */
        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            Log.d(tag, "onDoubleTapEvent");
            Toast.makeText(MainActivity.this, "onDoubleTapEvent", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    /** 单击和双击事件的类 */
    private class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        // 用到哪个方法重写该方法
    }
}
