package com.example.btkso1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "company.db";
    private static final int DATABASE_VERSION = 1;


    public static final String TABLE_DEPARTMENT = "donvi";
    public static final String TABLE_EMPLOYEE = "nhanvien";
    //Table Department

    public static final String COLUMN_DEPARTMENT_ID = "madonvi";
    public static final String COLUMN_DEPARTMENT_NAME = "ten_donvi";
    public static final String COLUMN_DEPARTMENT_EMAIL = "email";
    public static final String COLUMN_DEPARTMENT_WEBSITE = "website";
    public static final String COLUMN_DEPARTMENT_LOGO = "logo";
    public static final String COLUMN_DEPARTMENT_ADDRESS = "diachi";
    public static final String COLUMN_DEPARTMENT_PHONE = "sodienthoai";
    public static final String COLUMN_DEPARTMENT_PARENT_ID = "madonvi_cha";

    // Employee Table
    public static final String COLUMN_EMPLOYEE_ID = "manhanvien";
    public static final String COLUMN_EMPLOYEE_NAME = "hoten";
    public static final String COLUMN_EMPLOYEE_POSITION = "chucvu";
    public static final String COLUMN_EMPLOYEE_EMAIL = "email";
    public static final String COLUMN_EMPLOYEE_PHONE = "sodienthoai";
    public static final String COLUMN_EMPLOYEE_ADDRESS = "diachi";
    public static final String COLUMN_EMPLOYEE_AVATAR = "avatar";

    // Create Table SQL
    private static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + TABLE_DEPARTMENT + "("
            + COLUMN_DEPARTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DEPARTMENT_NAME + " TEXT,"
            + COLUMN_DEPARTMENT_EMAIL + " TEXT,"
            + COLUMN_DEPARTMENT_WEBSITE + " TEXT,"
            + COLUMN_DEPARTMENT_LOGO + " TEXT,"
            + COLUMN_DEPARTMENT_ADDRESS + " TEXT,"
            + COLUMN_DEPARTMENT_PHONE + " TEXT,"
            + COLUMN_DEPARTMENT_PARENT_ID + " INTEGER"
            + ")";

    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
            + COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMPLOYEE_NAME + " TEXT,"
            + COLUMN_EMPLOYEE_POSITION + " TEXT,"
            + COLUMN_EMPLOYEE_EMAIL + " TEXT,"
            + COLUMN_EMPLOYEE_PHONE + " TEXT,"
            + COLUMN_EMPLOYEE_ADDRESS + " TEXT,"
            + COLUMN_EMPLOYEE_AVATAR + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        db.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(db);
    }
}
