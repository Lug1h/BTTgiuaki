package com.example.btkso1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;

public class EmployeeDetailActivity extends AppCompatActivity {

    private ImageView imgEmployeeDetailAvatar;
    private TextView txtEmployeeDetailID;
    private TextView txtEmployeeDetailName;
    private TextView txtEmployeeDetailPosition;
    private Spinner spinnerDepartment;
    private TextView txtEmployeeDetailEmail;
    private TextView txtEmployeeDetailPhone;
    private TextView txtEmployeeDetailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        imgEmployeeDetailAvatar = findViewById(R.id.imgEmployeeDetailAvatar);
        txtEmployeeDetailID = findViewById(R.id.txtEmployeeDetailID);
        txtEmployeeDetailName = findViewById(R.id.txtEmployeeDetailName);
        txtEmployeeDetailPosition = findViewById(R.id.txtEmployeeDetailPosition);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        txtEmployeeDetailEmail = findViewById(R.id.txtEmployeeDetailEmail);
        txtEmployeeDetailPhone = findViewById(R.id.txtEmployeeDetailPhone);
        txtEmployeeDetailAddress = findViewById(R.id.txtEmployeeDetailAddress);

        // Lấy danh sách đơn vị từ cơ sở dữ liệu
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Collection<Department> departments = dbHelper.getAllDepartments();

        // Tạo một danh sách tên đơn vị
        ArrayList<String> departmentNames = new ArrayList<>();
        for (Department department : departments) {
            departmentNames.add(department.getName());
        }

        // Tạo adapter cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(adapter);

        // Load dữ liệu nhân viên từ Intent hoặc cơ sở dữ liệu
        loadEmployeeDetails();
    }

    private void loadEmployeeDetails() {

         Employee employee = getIntent().getParcelableExtra("employee");
         txtEmployeeDetailID.setText("ID: " + employee.getId());
         txtEmployeeDetailName.setText("Tên: " + employee.getName());
         txtEmployeeDetailPosition.setText("Chức vụ: " + employee.getPosition());
         txtEmployeeDetailEmail.setText("Email: " + employee.getEmail());
         txtEmployeeDetailPhone.setText("Số điện thoại: " + employee.getPhone());
         txtEmployeeDetailAddress.setText("Địa chỉ: " + employee.getAddress());

        // Đặt đúng giá trị cho Spinner dựa trên mã đơn vị của nhân viên
        // int departmentIndex = ...; // Lấy vị trí của đơn vị trong Spinner
        // spinnerDepartment.setSelection(departmentIndex);
    }
}
