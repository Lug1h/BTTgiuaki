package com.example.btkso1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btkso1.R;

public class MainActivity extends AppCompatActivity {
    private Button btnManageDepartments, btnManageEmployees, btnSearchInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManageDepartments = findViewById(R.id.btnManageDepartments);
        btnManageEmployees = findViewById(R.id.btnManageEmployees);
        btnSearchInformation = findViewById(R.id.btnSearchInformation);

        btnManageDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DepartmentActivity.class);
                startActivity(intent);
            }
        });

        btnManageEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });

        btnSearchInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class); // Bạn cần tạo SearchActivity để xử lý tìm kiếm
                startActivity(intent);
            }
        });
    }
}
