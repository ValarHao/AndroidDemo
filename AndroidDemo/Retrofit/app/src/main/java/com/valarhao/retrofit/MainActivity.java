package com.valarhao.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/news/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ZhihuService zhihuService = retrofit.create(ZhihuService.class);

        Call<DateNews> call = zhihuService.getNewsItemRepos("20160924");

        call.enqueue(new Callback<DateNews>() {

            @Override
            public void onResponse(Call<DateNews> call, Response<DateNews> response) {
                DateNews dateNews = response.body();
                Log.d(TAG, "date: " + dateNews.getDate());
                List<DateNews.NewsItem> newsItems = dateNews.getStories();
                for (DateNews.NewsItem item : newsItems) {
                    Log.d(TAG, item.getTitle());
                }
            }

            @Override
            public void onFailure(Call<DateNews> call, Throwable t) {
                Log.e(TAG, "获取网络失败或解析失败！");
            }
        });
    }

    public interface ZhihuService {
        @GET("before/{date}")
        Call<DateNews> getNewsItemRepos(@Path("date") String date);
    }
}
