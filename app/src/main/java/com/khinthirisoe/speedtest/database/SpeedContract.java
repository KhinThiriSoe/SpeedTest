package com.khinthirisoe.speedtest.database;

import android.provider.BaseColumns;

/**
 * Created by khinthirisoe on 5/11/17.
 */

public class SpeedContract {

    public SpeedContract() {
    }

    public static final class SpeedEntry implements BaseColumns {

        public final static String TABLE_NAME = "speed";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_DOWNLOAD = "download";

        public final static String COLUMN_UPLOAD = "upload";

        public final static String COLUMN_PING = "ping";

        public final static String COLUMN_DATE = "date";
    }
}