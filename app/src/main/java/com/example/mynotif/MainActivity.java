package com.example.mynotif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomething(v);
            }
        });
    }
    public void doSomething(View  view){
        NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                        "Pemberitahuan",
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
                mNotificationManager.createNotificationChannel(channel);
            }
        NotificationManagerCompat myManager = NotificationManagerCompat.from(this);
        NotificationCompat.Builder myNotif = new NotificationCompat.Builder(this, "01");

        myNotif.setContentTitle("Pesan dari PNS");
        myNotif.setContentText("Selamat Anda diterima menjadi PNS");
        myNotif.setSmallIcon(android.R.drawable.ic_btn_speak_now);
        myNotif.setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.drawable.maudy));

        Intent il = new Intent(this,MainActivity.class);
        PendingIntent pd = PendingIntent.getActivity(this,2,il,0);

        myNotif.setContentIntent(pd);
        myNotif.setAutoCancel(true);

        myManager.notify(2,myNotif.build());
        finish();
    }
}
