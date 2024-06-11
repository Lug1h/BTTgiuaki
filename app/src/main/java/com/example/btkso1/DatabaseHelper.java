package com.example.btkso1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.ArrayList;
import java.util.Collection;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Khai báo tên và phiên bản của cơ sở dữ liệu
    private static final String DATABASE_NAME = "QLDanhba.db";
    private static final int DATABASE_VERSION = 1;

    // Khai báo tên bảng và các cột cho bảng Department (Phòng ban)
    public static final String TABLE_DEPARTMENT = "donvi";
    public static final String COLUMN_DEPARTMENT_ID = "madonvi";
    public static final String COLUMN_DEPARTMENT_NAME = "ten_donvi";
    public static final String COLUMN_DEPARTMENT_EMAIL = "email";
    public static final String COLUMN_DEPARTMENT_WEBSITE = "website";
    public static final String COLUMN_DEPARTMENT_LOGO = "logo";
    public static final String COLUMN_DEPARTMENT_ADDRESS = "diachi";
    public static final String COLUMN_DEPARTMENT_PHONE = "sodienthoai";
    public static final String COLUMN_DEPARTMENT_PARENT_ID = "madonvi_cha";

    // Khai báo tên bảng và các cột cho bảng Employee (Nhân viên)
    public static final String TABLE_EMPLOYEE = "nhanvien";
    public static final String COLUMN_EMPLOYEE_ID = "manhanvien";
    public static final String COLUMN_EMPLOYEE_NAME = "hoten";
    public static final String COLUMN_EMPLOYEE_POSITION = "chucvu";
    public static final String COLUMN_EMPLOYEE_EMAIL = "email";
    public static final String COLUMN_EMPLOYEE_PHONE = "sodienthoai";
    public static final String COLUMN_EMPLOYEE_ADDRESS = "diachi";
    public static final String COLUMN_EMPLOYEE_AVATAR = "avatar";
    public static final String COLUMN_EMPLOYEE_DEPARTMENT_ID = "madonvi";

    // Khai báo tên bảng và các cột cho bảng Users (Người dùng)
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_FULL_NAME = "fullName";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_PHONE = "phone";

    // Câu lệnh SQL để tạo bảng Department
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

    // Câu lệnh SQL để tạo bảng Employee
    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
            + COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMPLOYEE_NAME + " TEXT,"
            + COLUMN_EMPLOYEE_POSITION + " TEXT,"
            + COLUMN_EMPLOYEE_EMAIL + " TEXT,"
            + COLUMN_EMPLOYEE_PHONE + " TEXT,"
            + COLUMN_EMPLOYEE_ADDRESS + " TEXT,"
            + COLUMN_EMPLOYEE_AVATAR + " TEXT,"
            + COLUMN_EMPLOYEE_DEPARTMENT_ID + " INTEGER,"
            + "FOREIGN KEY(" + COLUMN_EMPLOYEE_DEPARTMENT_ID + ") REFERENCES " + TABLE_DEPARTMENT + "(" + COLUMN_DEPARTMENT_ID + ")"
            + ")";

    // Câu lệnh SQL để tạo bảng Users
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_FULL_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_PHONE + " TEXT"
            + ")";

    // Constructor cho DatabaseHelper
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Phương thức onCreate được gọi khi cơ sở dữ liệu được tạo lần đầu
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTMENT); // Tạo bảng Department
        db.execSQL(CREATE_TABLE_EMPLOYEE);   // Tạo bảng Employee
        db.execSQL(CREATE_TABLE_USERS);      // Tạo bảng Users
    }

    // Phương thức onUpgrade được gọi khi cơ sở dữ liệu cần nâng cấp
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT); // Xóa bảng Department nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);   // Xóa bảng Employee nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);      // Xóa bảng Users nếu tồn tại
        onCreate(db); // Tạo lại cơ sở dữ liệu
    }

    // Phương thức kiểm tra người dùng
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_EMAIL + " = ? AND " + COLUMN_USER_PASSWORD + " = ?", new String[]{email, password});
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    // Phương thức tìm kiếm nhân viên theo từ khóa
    @SuppressLint("Range")
    public Collection<Employee> searchEmployees(String query) {
        ArrayList<Employee> employees = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String searchQuery = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE "
                + COLUMN_EMPLOYEE_NAME + " LIKE ? OR "
                + COLUMN_EMPLOYEE_POSITION + " LIKE ? OR "
                + COLUMN_EMPLOYEE_EMAIL + " LIKE ? OR "
                + COLUMN_EMPLOYEE_PHONE + " LIKE ?";

        // Sử dụng wildcard % để tìm kiếm
        String wildcardQuery = "%" + query + "%";
        Cursor cursor = db.rawQuery(searchQuery, new String[]{wildcardQuery, wildcardQuery, wildcardQuery, wildcardQuery});

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_EMPLOYEE_ID)));
                employee.setName(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_NAME)));
                employee.setPosition(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_POSITION)));
                employee.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_EMAIL)));
                employee.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_PHONE)));
                employee.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_ADDRESS)));
                employee.setAvatar(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_AVATAR)));
                employee.setDepartmentId(cursor.getInt(cursor.getColumnIndex(COLUMN_EMPLOYEE_DEPARTMENT_ID)));
                employees.add(employee);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return employees;
    }

    // Phương thức lấy tất cả đơn vị
    @SuppressLint("Range")
    public Collection<Department> getAllDepartments() {
        ArrayList<Department> departments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DEPARTMENT, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Department department = new Department(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_DEPARTMENT_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT_WEBSITE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT_LOGO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT_PHONE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_DEPARTMENT_PARENT_ID))
                );
                departments.add(department);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return departments;
    }

    // Phương thức lấy tên đơn vị theo mã đơn vị
    @SuppressLint("Range")
    public String getDepartmentNameById(int departmentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DEPARTMENT,
                new String[]{COLUMN_DEPARTMENT_NAME},
                COLUMN_DEPARTMENT_ID + " = ?",
                new String[]{String.valueOf(departmentId)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            String departmentName = cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT_NAME));
            cursor.close();
            return departmentName;
        }

        return null;
    }

    // Phương thức thêm nhân viên
    public long addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_NAME, employee.getName());
        values.put(COLUMN_EMPLOYEE_POSITION, employee.getPosition());
        values.put(COLUMN_EMPLOYEE_EMAIL, employee.getEmail());
        values.put(COLUMN_EMPLOYEE_PHONE, employee.getPhone());
        values.put(COLUMN_EMPLOYEE_ADDRESS, employee.getAddress());
        values.put(COLUMN_EMPLOYEE_AVATAR, employee.getAvatar());
        values.put(COLUMN_EMPLOYEE_DEPARTMENT_ID, employee.getDepartmentId());

        long result = db.insert(TABLE_EMPLOYEE, null, values);
        db.close();
        return result;
    }

    // Phương thức cập nhật thông tin nhân viên
    public int updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_NAME, employee.getName());
        values.put(COLUMN_EMPLOYEE_POSITION, employee.getPosition());
        values.put(COLUMN_EMPLOYEE_EMAIL, employee.getEmail());
        values.put(COLUMN_EMPLOYEE_PHONE, employee.getPhone());
        values.put(COLUMN_EMPLOYEE_ADDRESS, employee.getAddress());
        values.put(COLUMN_EMPLOYEE_AVATAR, employee.getAvatar());
        values.put(COLUMN_EMPLOYEE_DEPARTMENT_ID, employee.getDepartmentId());

        int result = db.update(TABLE_EMPLOYEE, values, COLUMN_EMPLOYEE_ID + " = ?", new String[]{String.valueOf(employee.getId())});
        db.close();
        return result;
    }

    // Phương thức xóa nhân viên
    public int deleteEmployee(int employeeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_EMPLOYEE, COLUMN_EMPLOYEE_ID + " = ?", new String[]{String.valueOf(employeeId)});
        db.close();
        return result;
    }

    // Phương thức thêm đơn vị
    public long addDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEPARTMENT_NAME, department.getName());
        values.put(COLUMN_DEPARTMENT_EMAIL, department.getEmail());
        values.put(COLUMN_DEPARTMENT_WEBSITE, department.getWebsite());
        values.put(COLUMN_DEPARTMENT_LOGO, department.getLogoPath());
        values.put(COLUMN_DEPARTMENT_ADDRESS, department.getAddress());
        values.put(COLUMN_DEPARTMENT_PHONE, department.getPhone());
        values.put(COLUMN_DEPARTMENT_PARENT_ID, department.getParentId());

        long result = db.insert(TABLE_DEPARTMENT, null, values);
        db.close();
        return result;
    }

    // Phương thức cập nhật thông tin đơn vị
    public int updateDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEPARTMENT_NAME, department.getName());
        values.put(COLUMN_DEPARTMENT_EMAIL, department.getEmail());
        values.put(COLUMN_DEPARTMENT_WEBSITE, department.getWebsite());
        values.put(COLUMN_DEPARTMENT_LOGO, department.getLogoPath());
        values.put(COLUMN_DEPARTMENT_ADDRESS, department.getAddress());
        values.put(COLUMN_DEPARTMENT_PHONE, department.getPhone());
        values.put(COLUMN_DEPARTMENT_PARENT_ID, department.getParentId());

        int result = db.update(TABLE_DEPARTMENT, values, COLUMN_DEPARTMENT_ID + " = ?", new String[]{String.valueOf(department.getId())});
        db.close();
        return result;
    }

    // Phương thức xóa đơn vị
    public int deleteDepartment(int departmentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_DEPARTMENT, COLUMN_DEPARTMENT_ID + " = ?", new String[]{String.valueOf(departmentId)});
        db.close();
        return result;
    }
}
