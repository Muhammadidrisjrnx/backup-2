package com.example.rplrus021.timer;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class service extends IntentService {
    int angka = 60;
    SharedPreferences sharedPreferences, sharedPreferences2;
    SharedPreferences.Editor editor, editor2;

    public service() {
        super("service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            if (angka != 1) {
                for (int i = 60; i > 0; i--) {
                    Thread.sleep(1000);
                    angka--;
                    Intent notifyFinishIntent = new Intent("time");
                    String format = String.format("%s", angka);
                    sharedPreferences = getSharedPreferences("timer", MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putString("angka", format);
                    editor.apply();
                    sendBroadcast(notifyFinishIntent);
                }

            }
//            else {
//                sharedPreferences2 = getSharedPreferences("finish2", MODE_PRIVATE);
//                editor2 = sharedPreferences2.edit();
//                String format = String.format("%s", angka);
//                editor2.putString("finish", format);
//                editor2.apply();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//        Intent notifyFinishIntent = new Intent("time_finish");
//        sendBroadcast(notifyFinishIntent);

    }


}
