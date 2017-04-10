package com.wearenicecorp.gymapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JulioAndres on 3/31/16.
 */
public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gym_app.db";
    private static final int DB_VERSION = 1;

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MUSCULAR_GROUP= "muscular_group";
    public static final String COLUMN_MESURE_TYPE = "mesure_type";

    private static final String CREATE_TABLE_EXERCISES ="CREATE TABLE " +
            "exercises(" +
            COLUMN_NAME + " TEXT, " +
            COLUMN_MUSCULAR_GROUP + " TEXT, " +
            COLUMN_MESURE_TYPE + " TEXT)";

    public static final String TABLE_EXERCISES= "exercises";

    public AppDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREATE_TABLE_EXERCISES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
