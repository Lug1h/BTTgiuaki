package com.example.btkso1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DepartmentDAO {
    private SQLiteOpenHelper dbHelper;

    public DepartmentDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public boolean insertDepartment(Department department) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_ID, department.getId());
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_NAME, department.getName());
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_EMAIL, department.getEmail());
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_WEBSITE, department.getWebsite());
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_LOGO, department.getLogoPath());
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_ADDRESS, department.getAddress());
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_PHONE, department.getPhone());

        long result = db.insert(DatabaseHelper.TABLE_DEPARTMENT, null, values);
        db.close();
        return result != -1;
    }
}
