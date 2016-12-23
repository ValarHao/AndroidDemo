package com.valarhao.reflection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);

        /**
         * 获取对象的三种方法
         */

        //获取对象的第一种方式：知道一个类，直接获取Class对象
        Class<?> class1 = ReflectionActivity.class;
        mTv.setText(mTv.getText() + "class1 name -> " + class1.getSimpleName() + "\n");

        //获取对象的第二种方式：已经得到了某个对象，通过这个对象获取其Class对象
        ReflectionActivity reflectionActivity = new ReflectionActivity();
        Class<?> class2 = reflectionActivity.getClass();
        mTv.setText(mTv.getText() + "class2 name -> " + class2.getSimpleName() + "\n");

        //获取对象的第三种方式：通过类路径获取Class对象
        try {
            Class<?> class3 = Class.forName("com.valarhao.reflection.ReflectionActivity");
            mTv.setText(mTv.getText() + "class3 name -> " + class3.getSimpleName() + "\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        mTv.setText(mTv.getText() + "----------------------------------------------------------------\n");

        /**
         * 反射的相关方法：
         *
         *     getName()：获得类的完整名字
         *     newInstance()：通过类的不带参数的构造方法创建这个类的一个对象
         *
         *     getFields()：获得类的public类型的属性
         *     getDeclaredFields()：获得类的所有属性
         *
         *     getMethods()：获得类的public类型的方法
         *     getDeclaredMethods()：获得类的所有方法
         *     getMethod(String name, Class[] parameterTypes)：获得类的特定方法
         *
         *     getModifiers()和Modifier.toString()：获得属性修饰符
         *     getReturnType()：获得方法的返回类型
         *     getParameterTypes()：获得方法的参数类型
         *
         *     getConstructors()：获得类的public类型的构造方法
         *     getConstructor(Class[] parameterTypes)：获得类的特定构造方法
         *
         *     getSuperclass()：获取某类的父类
         *     getInterfaces()：获取某类实现的接口
         */

        Class<ReflectionActivity> clazz = ReflectionActivity.class;

        /**
         * 获得类的所有方法
         */
        Method[] methods = clazz.getDeclaredMethods();
        StringBuilder sb1 = new StringBuilder();
        for (Method method : methods) {
            sb1.append(Modifier.toString(method.getModifiers())).append(" ");
            sb1.append(method.getReturnType()).append(" ");
            sb1.append(method.getName()).append("(");
            Class[] parameters = method.getParameterTypes();
            if (parameters != null) {
                for (int i=0; i<parameters.length; i++) {
                    Class paramCls = parameters[i];
                    sb1.append(paramCls.getSimpleName());
                    if (i < parameters.length - 1) sb1.append(", ");
                }
            }
            sb1.append(")\n");
        }
        mTv.setText(mTv.getText() + sb1.toString());

        mTv.setText(mTv.getText() + "----------------------------------------------------------------\n");

        /**
         * 获得类的所有属性
         */
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb2 = new StringBuilder();
        for (Field field : fields) {
            sb2.append(Modifier.toString(field.getModifiers())).append(" ");
            sb2.append(field.getType().getSimpleName()).append(" ");
            sb2.append(field.getName()).append(";");
            sb2.append("\n");
        }
        mTv.setText(mTv.getText() + sb2.toString());
    }
}
