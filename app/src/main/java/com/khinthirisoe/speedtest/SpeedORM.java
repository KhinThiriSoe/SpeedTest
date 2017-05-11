package com.khinthirisoe.speedtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.khinthirisoe.speedtest.Utils.PrefUtils;
import com.khinthirisoe.speedtest.database.SpeedDbHelper;

import static com.khinthirisoe.speedtest.database.SpeedContract.SpeedEntry.TABLE_NAME;

/**
 * Created by khinthirisoe on 5/11/17.
 */

public class SpeedORM {

    Context context;

    public SpeedORM(Context context) {
        this.context = context;
    }

    SpeedDbHelper dbHelper = new SpeedDbHelper(context);

    public void addSpeed(float download, float upload, float ping, long date) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PrefUtils.PREF_DOWNLOAD, download);
        values.put(PrefUtils.PREF_UPLOAD, upload);
        values.put(PrefUtils.PREF_PING, ping);
        values.put(PrefUtils.PREF_END_DATE, date);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public BenchMark findSpeed(long date) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{PrefUtils.PREF_DOWNLOAD, PrefUtils.PREF_UPLOAD, PrefUtils.PREF_PING},
                PrefUtils.PREF_END_DATE + "=?", new String[]{String.valueOf(date)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return new BenchMark(cursor.getFloat(0), cursor.getFloat(1), cursor.getFloat(2));
    }
}

