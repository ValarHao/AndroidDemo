package com.valarhao.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

/**------------------------被观察者的三种方式---------------------------*/
        /**
         * 使用create创建一个被观察者
         * onNext() => 往事件发送队列中存放事件
         * onCompleted() => 事件队列完结
         * onError() => 事件队列异常
         */
        /*Observable<String> observable = rx.Observable.create(
                new rx.Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("create ");
                        subscriber.onNext("RxJava!");
                        subscriber.onCompleted();
                    }
                }
        );*/

        /**
         * 使用just快捷创建事件队列
         * 相当于依次调用：onNext("Hello "); => onNext("RxAndroid!"); => onCompleted();
         */
        Observable observable = Observable.just("just ", "RxAndroid!");

        /**
         * 使用from将传入的数组拆分成具体对象后发送
         */
        //String[] datas = { "from ", "RxAndroid!" };
        //Observable observable = Observable.from(datas);

/**-------------------------观察者的两种方式----------------------------*/
        /**
         * 观察者可使用Observer接口或实现了该接口的抽象类Subscriber
         * 接口有三个方法：onNext()、onCompleted()、onError()
         * 抽象类多了两个方法：
         * onStart() => 做一些初始化工作
         * unsubscribe() => 取消订阅
         */
        /*Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                tv.setText(tv.getText() + s);
            }
        };*/

        /**
         * Subscriber支持不完整定义
         * 当不关心onCompleted()和onError()时采用Action1代替onNext()
         */
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                tv.setText(tv.getText() + s);
            }
        };

/**-----------------------将被观察者和观察者关联起来--------------------------*/
        /**
         * Subscription subscribe(Subscriber subscriber)源码中主要做了三件事：
         *     subscriber.onStart();
         *     onSubscribe.call(subscriber);
         *     return subscriber;
         */
        //observable.subscribe(subscriber);
        observable.subscribe(onNextAction);
    }
}
