package com.example.radmushroom.contentprovider;

// @formatter:off
import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.radmushroom.contentprovider.base.BaseSQLiteOpenHelperCallbacks;
import com.example.radmushroom.contentprovider.link.LinkColumns;

public class LinkSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = LinkSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "links.db";
    private static final int DATABASE_VERSION = 1;
    private static LinkSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final BaseSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    public static final String SQL_CREATE_TABLE_LINK = "CREATE TABLE IF NOT EXISTS "
            + LinkColumns.TABLE_NAME + " ( "
            + LinkColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LinkColumns.LINK + " TEXT DEFAULT '', "
            + LinkColumns.TIME + " TEXT DEFAULT '', "
            + LinkColumns.STATUS + " INTEGER "
            + " );";


    public static LinkSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static LinkSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static LinkSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new LinkSQLiteOpenHelper(context);
    }

    private LinkSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new BaseSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static LinkSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new LinkSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private LinkSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new BaseSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_LINK);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        setForeignKeyConstraintsEnabledPostJellyBean(db);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
