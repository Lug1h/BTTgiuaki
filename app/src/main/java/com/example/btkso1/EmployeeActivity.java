package com.example.btkso1;

import static com.example.btkso1.R.id.etEmployeeId;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeActivity extends AppCompatActivity {
    private EditText etEmployeeId;
    private EditText etEmployeeName;
    private EditText etEmployeePosition;
    private EditText etEmployeeEmail;
    private EditText etEmployeePhone;
    private EditText etEmployeeAddress;
    private ImageButton ibEmployeeAvatar;
    private Button btnSaveEmployee;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmployeeId = findViewById(R.id.etEmployeeId);
        etEmployeeName = findViewById(R.id.etEmployeeName);
        etEmployeePosition = findViewById(R.id.etEmployeePosition);
        etEmployeeEmail = findViewById(R.id.etEmployeeEmail);
        etEmployeePhone = findViewById(R.id.etEmployeePhone);
        etEmployeeAddress = findViewById(R.id.etEmployeeAddress);
        ibEmployeeAvatar = findViewById(R.id.Avatar);
        btnSaveEmployee = findViewById(R.id.btnSaveEmployee);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        btnSaveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();
            }
        });
    }

    private void saveEmployee() {
        String employeeId = etEmployeeId.getText().toString().trim();
        String name = etEmployeeName.getText().toString().trim();
        String position = etEmployeePosition.getText().toString().trim();
        String email = etEmployeeEmail.getText().toString().trim();
        String phone = etEmployeePhone.getText().toString().trim();
        String address = etEmployeeAddress.getText().toString().trim();
        String avatar = "default_avatar"; // This can be replaced with actual avatar path or data

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền vào các trường bắt buộc", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_ID, employeeId);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_NAME, name);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_POSITION, position);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_PHONE, phone);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_ADDRESS, address);
        values.put(DatabaseHelper.COLUMN_EMPLOYEE_AVATAR, avatar);

        long result = db.insert(DatabaseHelper.TABLE_EMPLOYEE, null, values);

        if (result != -1) {
            Toast.makeText(this, "Đã lưu thông tin nhân viên thành công", Toast.LENGTH_SHORT).show();
            clearFields();
        } else {
            Toast.makeText(this, "Lỗi khi lưu thông tin nhân viên", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etEmployeeId.setText("");
        etEmployeeName.setText("");
        etEmployeePosition.setText("");
        etEmployeeEmail.setText("");
        etEmployeePhone.setText("");
        etEmployeeAddress.setText("");
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
