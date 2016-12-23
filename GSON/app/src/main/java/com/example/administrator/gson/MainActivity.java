package com.example.administrator.gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = "{\"name\":\"jack\",\"age\":26,\"address\":\"huzhou\"}";
        Gson gson = new Gson();
        Person person = gson.fromJson(json, Person.class);
        Log.d("gson", "name is " + person.getName());
        Log.d("gson", "age is " + person.getAge());
        Log.d("gson", "address is " + person.getAddress());

        String str = gson.toJson(person);
        Log.d("gson", str);
    }

    class Person {
        private String name;
        private int age;
        private String address;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getAddress() {
            return address;
        }
    }
}
