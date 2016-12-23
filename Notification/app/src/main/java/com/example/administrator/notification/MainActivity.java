package com.example.administrator.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormal = (Button) findViewById(R.id.id_normal);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setAutoCancel(true);
                builder.setContentTitle("普通通知");
                builder.setContentText("Hello");

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
        });

        Button btnFold = (Button) findViewById(R.id.id_fold);
        btnFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setAutoCancel(true);
                builder.setContentTitle("折叠式通知");
                builder.setContentText("Hello");
                //用RemoteViews来创建自定义Notification视图
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_fold);
                Notification notification = builder.build();
                //指定展开时的视图
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notification.bigContentView = remoteViews;
                notificationManager.notify(1, notification);
            }
        });

        Button btnPend = (Button) findViewById(R.id.id_pend);
        btnPend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setAutoCancel(true);
                builder.setContentTitle("悬挂式通知");
                builder.setContentText("Hello");
                //设置点击跳转
                Intent hangIntent = new Intent();
                hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                hangIntent.setClass(MainActivity.this, SecondActivity.class);
                //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
                PendingIntent hangPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setFullScreenIntent(hangPendingIntent, true);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(2, builder.build());
            }
        });
    }
}
