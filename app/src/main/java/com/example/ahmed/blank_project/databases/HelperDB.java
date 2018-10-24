package com.example.ahmed.blank_project.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tecnew on 23/11/2016.
 */

public class HelperDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 15;
    public static final String DATABASE_NAME = "database_name.db";

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        try {
            //db.execSQL(ScriptDB.CREATE_CONFIGURACAO);


        }catch (Exception e){
            Log.d("CREATE PROBLEM", e.getMessage());
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Run when database is upgraded / changed, like drop tables, add tables etc.
        //db.execSQL(ScriptDB.DELETE_DEVICE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}