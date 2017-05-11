package com.khinthirisoe.speedtest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.khinthirisoe.speedtest.database.SpeedContract.SpeedEntry;
import static com.khinthirisoe.speedtest.database.SpeedContract.SpeedEntry.TABLE_NAME;

/**
 * Created by khinthirisoe on 5/11/17.
 */

public class SpeedDbHelper extends SQLiteOpenHelper {

    private String SQL_CREATE_SPEEDS_TABLE;

    public static final String LOG_TAG = SpeedDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "speeds.db";

    private static final int DATABASE_VERSION = 1;

    public SpeedDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        SQL_CREATE_SPEEDS_TABLE = "CREATE TABLE " + TABLE_NAME + " " +
                "(" + SpeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SpeedEntry.COLUMN_DOWNLOAD + " REAL, " +
                SpeedEntry.COLUMN_UPLOAD + " REAL, " +
                SpeedEntry.COLUMN_PING + " READ, " +
                SpeedEntry.COLUMN_DATE + " LONG)";

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
}
