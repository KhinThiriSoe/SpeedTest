package com.khinthirisoe.speedtest;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by khinthirisoe on 5/9/17.
 */

public class SpeedTest {

    public static float downloadSpeed() {
        Random downloadSpeedRandom = new Random();
        float downloadSpeed = downloadSpeedRandom.nextFloat() * (100 - 0) + 0;
        DecimalFormat df = new DecimalFormat("0.00");

        return Float.parseFloat(df.format(downloadSpeed));
    }

    public static float uploadSpeed() {
        Random uploadSpeedRandom = new Random();
        float downloadSpeed = uploadSpeedRandom.nextFloat() * (100 - 0) + 0;
        DecimalFormat df = new DecimalFormat("0.00");

        return Float.parseFloat(df.format(downloadSpeed));
    }

    public static float pingSpeed() {
        Random pingSpeedRandom = new Random();
        float pingSpeed = pingSpeedRandom.nextFloat() * (100 - 0) + 0;
        DecimalFormat df = new DecimalFormat("0.00");

        return Float.parseFloat(df.format(pingSpeed));
    }

    public static Date getDate(){
        BenchMark benchMark = new BenchMark();
        return benchMark.getDate();
    }

}
