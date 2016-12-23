package com.valarhao.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class MapActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        /**
         * map() => 事件对象的直接変换
         */
        /*Observable.just("Hello RxJava!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " -ValarHao";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tv.setText(tv.getText() + s);
                    }
                });*/

        /**
         * flatMap() => 事件对象的一对多变换
         * 传送的是Student经过flatmap后发送的是其内含有的Course
         */
        List<Student> students = new ArrayList<Student>();

        Student jack = new Student("Jack");
        jack.addCourse("Chinese");
        jack.addCourse("English");
        students.add(jack);

        Student john = new Student("John");
        john.addCourse("Math");
        john.addCourse("Physics");
        students.add(john);

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        tv.setText(tv.getText() + course.getName());
                    }
                });
    }

    /**
     * 学生类
     */
    class Student {
        private String name;
        private List<Course> courses = new ArrayList<Course>();

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void addCourse(String name) {
            courses.add(new Course(name));
        }

        public List<Course> getCourses() {
            return courses;
        }
    }

    /**
     * 课程类
     */
    class Course {
        private String name;

        public Course(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}


