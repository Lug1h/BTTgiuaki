package com.example.btkso1;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtRegisterPassword, edtRegisterPhone, edtRegisterEmail, edtRegisterFullName;
    private Button btnRegister;
    private TextView txtLoginFG;
    private DatabaseHelper dbHelper;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Liên kết các thành phần giao diện với các biến
        edtRegisterFullName = findViewById(R.id.edtRegisterFullName);
        edtRegisterEmail = findViewById(R.id.edtRegisterEmail);
        edtRegisterPassword = findViewById(R.id.edtRegisterPassword);
        edtRegisterPhone = findViewById(R.id.edtRegisterPhone);
        btnRegister = findViewById(R.id.btnRegister);
        txtLoginFG = findViewById(R.id.txtLoginFG);
        dbHelper = new DatabaseHelper(this);

        // Khởi tạo ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang đăng ký...");
        progressDialog.setCancelable(false);

        // Xử lý sự kiện khi nút "Register" được nhấn
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = edtRegisterFullName.getText().toString().trim();
                String email = edtRegisterEmail.getText().toString().trim();
                String password = edtRegisterPassword.getText().toString().trim();
                String phone = edtRegisterPhone.getText().toString().trim();

                // Kiểm tra xem các trường nhập liệu có trống không
                if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                } else {
                    new RegisterTask().execute(fullName, email, password, phone);
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào liên kết "Login"
        txtLoginFG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    // AsyncTask để thêm người dùng mới vào cơ sở dữ liệu
    private class RegisterTask extends AsyncTask<String, Void, Long> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Long doInBackground(String... params) {
            String fullName = params[0];
            String email = params[1];
            String password = params[2];
            String phone = params[3];

            return addUser(fullName, email, password, phone);
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            // Kiểm tra kết quả thêm người dùng vào cơ sở dữ liệu
            if (result > 0) {
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công ! Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Thêm người dùng mới vào cơ sở dữ liệu
    private long addUser(String fullName, String email, String password, String phone) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullName", fullName);
        values.put("email", email);
        values.put("password", password);
        values.put("phone", phone);

        return db.insert("users", null, values);
    }
}
