package com.valarhao.annotation.inject;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewInjectUtil {

    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    /**
     * 注入所有的控件
     * @param activity
     */
    public static void injectViews(Activity activity) {

        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject != null) {
                int viewId = viewInject.value();
                if (viewId != -1) {
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                        Object resView = method.invoke(activity, viewId);
                        field.setAccessible(true);
                        field.set(activity, resView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
