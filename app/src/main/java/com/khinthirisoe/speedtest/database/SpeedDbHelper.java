package com.khinthirisoe.speedtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.khinthirisoe.speedtest.BenchMark;
import com.khinthirisoe.speedtest.Utils.PrefUtils;

/**
 * Created by khinthirisoe on 5/11/17.
 */

public class SpeedDbHelper extends SQLiteOpenHelper {

    private String SQL_CREATE_SPEEDS_TABLE;

    public static final String LOG_TAG = SpeedDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "speeds.db";

    public static final String TABLE_NAME = "speed";

    private static final int DATABASE_VERSION = 1;

    public SpeedDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        SQL_CREATE_SPEEDS_TABLE = "CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "download REAL, " +
                "upload REAL, " +
                "ping READ, " +
                "date LONG)";

        db.execSQL(SQL_CREATE_SPEEDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SQL_CREATE_SPEEDS_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public void addSpeed(float download, float upload, float ping, long date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PrefUtils.PREF_DOWNLOAD, download);
        values.put(PrefUtils.PREF_UPLOAD, upload);
        values.put(PrefUtils.PREF_PING, ping);
        values.put(PrefUtils.PREF_END_DATE, date);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public BenchMark findSpeed(long date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{PrefUtils.PREF_DOWNLOAD, PrefUtils.PREF_UPLOAD, PrefUtils.PREF_PING},
                PrefUtils.PREF_END_DATE + "=?", new String[]{String.valueOf(date)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return new BenchMark(cursor.getFloat(0), cursor.getFloat(1), cursor.getFloat(2));
    }
}
