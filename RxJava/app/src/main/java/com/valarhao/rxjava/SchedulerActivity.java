package com.valarhao.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SchedulerActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        /**
         * subscribeOn() => 指定subscribe()所发生的线程，即事件产生线程
         * observeOn() => 指定Subscriber所运行在的线程，即事件消费线程
         * 线程有五种模式：
         *     Schedulers.immediate() => 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler
         *     Schedulers.newThread() => 总是启用新线程，并在新线程执行操作
         *     Schedulers.io() => I/O操作（读写文件、读写数据库、网络信息交互等）所使用的Scheduler。
         *                        行为模式和newThread()差不多，区别在于io()的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，
         *                        因此多数情况下io()比newThread()更有效率。不要把计算工作放在io()中，可以避免创建不必要的线程
         *     Schedulers.computation() => 计算所使用的Scheduler。这个计算指的是CPU密集型计算，即不会被I/O等操作限制性能的操作，例如图形的计算。
         *                                 这个Scheduler使用的固定的线程池，大小为CPU核数。不要把I/O操作放在computation()中，否则I/O操作的等待时间会浪费CPU
         *     AndroidSchedulers.mainThread() => 在Android主线程运行
         */

        /**
         * observeOn() 指定的是它之后的操作所在的线程。因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn()
         * subscribeOn() 的位置放在哪里都可以，但它是只能调用一次的
         */

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) //指定subscribe()发生在IO线程
                .observeOn(AndroidSchedulers.mainThread()) //指定Subscriber的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        tv.setText(tv.getText() + "number: " + integer + "\n");
                    }
                });
    }
}
