package com.example.btkso1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class DepartmentActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etDepartmentId, etDepartmentName, etDepartmentEmail, etDepartmentWebsite, etDepartmentAddress, etDepartmentPhone;
    private ImageButton btnSelectLogo;
    private Button btnSaveDepartment;
    private Uri logoUri;
    private DepartmentDAO departmentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        etDepartmentId = findViewById(R.id.etDepartmentId);
        etDepartmentName = findViewById(R.id.etDepartmentName);
        etDepartmentEmail = findViewById(R.id.etDepartmentEmail);
        etDepartmentWebsite = findViewById(R.id.etDepartmentWebsite);
        etDepartmentAddress = findViewById(R.id.etDepartmentAddress);
        etDepartmentPhone = findViewById(R.id.etDepartmentPhone);
        btnSelectLogo = findViewById(R.id.btnSelectLogo);
        btnSaveDepartment = findViewById(R.id.btnSaveDepartment);
        departmentDAO = new DepartmentDAO(this);

        btnSelectLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });

        btnSaveDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDepartment();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            logoUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), logoUri);
                btnSelectLogo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveDepartment() {
        String id = etDepartmentId.getText().toString().trim();
        String name = etDepartmentName.getText().toString().trim();
        String email = etDepartmentEmail.getText().toString().trim();
        String website = etDepartmentWebsite.getText().toString().trim();
        String address = etDepartmentAddress.getText().toString().trim();
        String phone = etDepartmentPhone.getText().toString().trim();
        String logoPath = logoUri != null ? logoUri.toString() : null;

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập thông tin của bạn!", Toast.LENGTH_SHORT).show();
            return;
        }

        Department department = new Department(Integer.parseInt(id), name, email, website, logoPath, address, phone, 0);
        boolean isInserted = departmentDAO.insertDepartment(department);

        if (isInserted) {
            Toast.makeText(this, "Thành công.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Thất bại.", Toast.LENGTH_SHORT).show();
        }
    }
}
