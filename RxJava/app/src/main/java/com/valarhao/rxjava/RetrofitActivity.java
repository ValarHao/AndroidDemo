package com.valarhao.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/news/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 2.别忘了添加RxJava的适配器！！
                .build();

        ZhihuService zhihuService = retrofit.create(ZhihuService.class);

        zhihuService.getNewsItemRepos("20160924")
                .subscribeOn(Schedulers.io()) //因为有网络请求所以新建线程池
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<DateNews, Observable<DateNews.NewsItem>>() {
                    @Override
                    public Observable<DateNews.NewsItem> call(DateNews dateNews) {
                        tv.setText(tv.getText() + "date: " + dateNews.getDate() + "\n");
                        return Observable.from(dateNews.getStories());
                    }
                })
                .subscribe(new Observer<DateNews.NewsItem>() {
                    // 3.原先Call中onResponse()中的代码换到这里，但这里我多加了一层flatMap()
                    @Override
                    public void onNext(DateNews.NewsItem newsItem) {
                        tv.setText(tv.getText() + newsItem.getTitle() + "\n");
                    }

                    @Override
                    public void onCompleted() {

                    }
                    // 4.原先Call中onFailure()中的代码换到这里
                    @Override
                    public void onError(Throwable e) {
                        tv.setText(tv.getText() + "获取网络失败或解析失败！");
                    }
                });
    }

    // 1.将返回值变为Observable
    public interface ZhihuService {
        @GET("before/{date}")
        Observable<DateNews> getNewsItemRepos(@Path("date") String date);
    }
}
