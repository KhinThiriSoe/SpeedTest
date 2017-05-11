package com.khinthirisoe.speedtest;

import android.content.Context;
import android.content.SharedPreferences;

import com.khinthirisoe.speedtest.Utils.PrefUtils;
import com.khinthirisoe.speedtest.database.SpeedDbHelper;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by khinthirisoe on 5/11/17.
 */

public class BenchMark {

    public Context context;
    public Date date;
    public float download, upload, ping;
    SpeedDbHelper dbHelper;


    public BenchMark() {

    }

    public BenchMark(Context context) {
        this.context = context;
    }

    public BenchMark(float download, float upload, float ping) {
        this.download = download;
        this.upload = upload;
        this.ping = ping;
    }

    public void Hourly() {

        final long endDate = System.currentTimeMillis();

        SharedPreferences.Editor pref = context.getSharedPreferences(PrefUtils.PREF_NAME, MODE_PRIVATE).edit();
        pref.putLong(PrefUtils.PREF_END_DATE, endDate);
        pref.apply();

//        final long period = 5;
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
        download = SpeedTest.downloadSpeed();
        upload = SpeedTest.uploadSpeed();
        ping = SpeedTest.pingSpeed();
        dbHelper.addSpeed(download, upload, ping, endDate);

    }
//        }, 0, period);

//    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public static float downloadAverage(Date date) {
        return 0;
    }

    public static float uploadAverage(Date date) {
        return 0;

    }

    public static float pingAverage(Date date) {
        return 0;

    }

}
