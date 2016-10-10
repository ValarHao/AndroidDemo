package com.example.administrator.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        /**StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("TAG", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", volleyError.getMessage(), volleyError);
            }
        });

        requestQueue.add(stringRequest);*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://www.weather.com.cn/data/cityinfo/101190404.html", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("TAG", jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG", volleyError.getMessage(), volleyError);
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
