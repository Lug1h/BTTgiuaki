package com.example.btkso1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnDepartmentManager, btnEmployeeManager, btnSearch,btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDepartmentManager = findViewById(R.id.btnDepartmentManager);
        btnEmployeeManager = findViewById(R.id.btnEmployeeManager);
        btnSearch = findViewById(R.id.btnSearch);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnDepartmentManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DepartmentActivity.class);
                startActivity(intent);
            }
        });

        btnDepartmentManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DepartmentActivity.class);
                startActivity(intent);
            }
        });
        btnEmployeeManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class); // Bạn cần tạo SearchActivity để xử lý tìm kiếm
                startActivity(intent);
            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin phiên đăng nhập ở đây (nếu có)


                // Chuyển hướng về màn hình đăng nhập
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Đóng MainActivity để người dùng không thể quay lại bằng nút Back
            }
        });
    }
}
