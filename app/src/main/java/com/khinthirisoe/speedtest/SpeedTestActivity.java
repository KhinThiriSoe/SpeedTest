package com.khinthirisoe.speedtest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.khinthirisoe.speedtest.Utils.PrefUtils;
import com.khinthirisoe.speedtest.database.SpeedDbHelper;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpeedTestActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btnTest)
    Button btnTest;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    int year, month, day;
    Date date;
    BenchMark benchMark;

    SpeedDbHelper dbHelper;

    private final int DATE_PICKER_TO = 0;
    private final int DATE_PICKER_FROM = 1;

    private long sDate;
    private long eDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_test);

        ButterKnife.bind(this);

        dbHelper = new SpeedDbHelper(this);

        benchMark = new BenchMark(this);

        SharedPreferences.Editor pref = this.getSharedPreferences(PrefUtils.PREF_NAME, MODE_PRIVATE).edit();
        pref.putLong(PrefUtils.PREF_START_DATE, System.currentTimeMillis());
        pref.apply();

        tvStartDate.setOnClickListener(this);
        btnTest.setOnClickListener(this);

        SharedPreferences preferences = this.getSharedPreferences(PrefUtils.PREF_NAME, MODE_PRIVATE);
        sDate = preferences.getLong(PrefUtils.PREF_START_DATE, 0);
        eDate = preferences.getLong(PrefUtils.PREF_END_DATE, 0);

    }

    @Override
    public void onClick(View v) {
        int getId = v.getId();
        switch (getId) {
            case R.id.tv_start_date:
                showDialog(0);
                benchMark.setDate(date);
                break;
            case R.id.btnTest:
                benchMark.Hourly();
                break;
        }

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        date = c.getTime();
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;
            tvStartDate.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);

        }
    };

}


