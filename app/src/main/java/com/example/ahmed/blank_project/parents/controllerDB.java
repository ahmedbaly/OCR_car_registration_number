package com.example.ahmed.blank_project.parents;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ahmed.blank_project.databases.HelperDB;

public class controllerDB {

    protected static final String SELECT = "SELECT ";
    protected static final String FROM = " FROM ";
    protected static final String WHERE = " WHERE ";
    protected static final String AND = " AND ";

    protected static final String INSERT_INTO = "INSERT INTO ";
    protected static final String VALUES = " VALUES ";

    protected static SQLiteDatabase db;
    protected HelperDB helperDB;

    protected Context mContext;

    public controllerDB(Context context) {
        this.mContext = context;
        this.helperDB = new HelperDB(context);
        this.db = helperDB.getWritableDatabase();
    }

    public static String encripta(String valor) {
        String valor2 = valor;
        if (valor2 == null) {
            valor2 = "";
        }
        if (valor2.equals("")) {
            return valor2;
        }
        String retorno = "";
        try {
            while (true) {
                String letra = valor2.substring(0, 1);
                valor2 = valor2.substring(1);
                int letra2 = letra.hashCode();
                letra2 += 166;
                String letra3 = Integer.toString(letra2);
                while (true) {
                    if (letra3.length() == 3) {
                        break;
                    }
                    letra3 = "0" + letra3;
                }
                retorno += letra3;
                if (valor2 == "") {
                    break;
                }
            }
        } catch (Exception ex) {
        }
        return retorno;
    }

    public static String decripta(String texto) {
        String retorno = "";
        String stexto = texto;
        if (stexto == "") {
            return stexto;
        }
        try {
            while (true) {
                String letra = stexto.substring(0, 3);
                int snumero = Integer.parseInt(letra);
                snumero -= 166;
                retorno += (char) snumero;
                stexto = stexto.substring(3);
                if (stexto == "") {
                    break;
                }
            }
        } catch (Exception ex) {
        }
        return retorno;
    }
}
