package com.khinthirisoe.speedtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class SpeedTestActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean click;

    private SpeedTest speedTest;
    private ArrayList<SpeedTest> arr;
    Button btnTest;
    Button btnShowSpeed;
    TextView tvDownloadSpeed;
    TextView tvUploadSpeed;
    TextView tvPingSpeed;
    LinearLayout lnSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_test);

        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(this);
        btnShowSpeed = (Button) findViewById(R.id.btnShowSpeed);
        btnShowSpeed.setOnClickListener(this);

        tvDownloadSpeed = (TextView) findViewById(R.id.tv_download_speed);
        tvUploadSpeed = (TextView) findViewById(R.id.tv_upload_speed);
        tvPingSpeed = (TextView) findViewById(R.id.tv_ping_speed);
        lnSpeed = (LinearLayout) findViewById(R.id.ln_speed);

        speedTest = new SpeedTest();
        arr = new ArrayList<>();

    }

    private void generateSpeedPerHourly() {

        btnShowSpeed.setVisibility(View.VISIBLE);

        do {
            Random uploadSpeedRandom = new Random();
            float uploadSpeed = uploadSpeedRandom.nextFloat() * (100 - 0) + 0;
            DecimalFormat df = new DecimalFormat("0.00");

            Random downloadSpeedRandom = new Random();
            float downloadSpeed = downloadSpeedRandom.nextFloat() * (100 - 0) + 0;

            Random pingRandom = new Random();
            float pingSpeed = pingRandom.nextFloat() * (100 - 0) + 0;

            speedTest.setDownloadSpeed(Float.parseFloat(df.format(downloadSpeed)));
            speedTest.setUploadSpeed(Float.parseFloat(df.format(uploadSpeed)));
            speedTest.setPingSpeed(Float.parseFloat(df.format(pingSpeed)));

            arr.add(new SpeedTest(speedTest.getDownloadSpeed(), speedTest.getUploadSpeed(), speedTest.getPingSpeed()));
        } while (click);
    }

    @Override
    public void onClick(View v) {
        int getId = v.getId();
        switch (getId) {
            case R.id.btnTest:
                generateSpeedPerHourly();
                break;
            case R.id.btnShowSpeed:
                click = false;
                showSpeed();
                break;
            default:
                break;
        }
    }

    private void showSpeed() {

        lnSpeed.setVisibility(View.VISIBLE);

        float dSpeed = 0;
        float uSpeed = 0;
        float ping = 0;

        for (int i = 0; i < arr.size(); i++) {
            dSpeed += arr.get(i).getDownloadSpeed();
            uSpeed += arr.get(i).getUploadSpeed();
            ping += arr.get(i).getPingSpeed();
        }

        tvDownloadSpeed.setText(String.valueOf(dSpeed / arr.size()) + " per sec");
        tvUploadSpeed.setText(String.valueOf(uSpeed / arr.size()) + " per sec");
        tvPingSpeed.setText(String.valueOf(ping / arr.size()) + " per sec");
    }

}
