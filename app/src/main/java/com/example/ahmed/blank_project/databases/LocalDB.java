package com.example.ahmed.blank_project.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.ahmed.blank_project.parents.controllerDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LocalDB extends controllerDB {
    private static Context appContext;
    protected SQLiteDatabase db;
    private HelperDB helperDB;
    private static LocalDB localDB;

    public LocalDB(Context context) {
        super(context);
        appContext = context;
        helperDB = new HelperDB(appContext);
    }
    public HelperDB getHelperDB() {
        return this.helperDB;
    }
    //     ------ System and basic functions---------  ///

    public void OpenDatabase() {
        boolean open = false;
        try {
            if (db.isOpen())
                open = true;
        } catch (Exception ex) {
        }
        if(open==false)
            db = helperDB.getReadableDatabase();
    }

    public void ExecuteQuery(String query) {
        OpenDatabase();
        boolean flag = true;
        try {
            db.execSQL(query);
        } catch (Exception ex) {
        }
    }
    public String returnString(String query) {
        String Return = "";
        Cursor cursor = this.db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst())
                Return  = cursor.getString(0);
        } catch (Exception ex) {
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();

        return Return ;
    }
    public String returnNumero(String query) {
        OpenDatabase();
        String Return = "";
        Cursor cursor = this.db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst())
                Return = Integer.toString(cursor.getInt(0));
        } catch (Exception ex) {
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();

        return Return;
    }

    public void DeleteTable(String table) {
        OpenDatabase();
        db.delete(table, null, null);
    }






}
