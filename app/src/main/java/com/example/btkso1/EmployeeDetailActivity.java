package com.example.btkso1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button btnEditEmployee;
    private Button btnUpdateEmployee;
    private Button btnDeleteEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        // Khởi tạo các thành phần giao diện
        imgEmployeeDetailAvatar = findViewById(R.id.imgEmployeeDetailAvatar);
        txtEmployeeDetailID = findViewById(R.id.txtEmployeeDetailID);
        txtEmployeeDetailName = findViewById(R.id.txtEmployeeDetailName);
        txtEmployeeDetailPosition = findViewById(R.id.txtEmployeeDetailPosition);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        txtEmployeeDetailEmail = findViewById(R.id.txtEmployeeDetailEmail);
        txtEmployeeDetailPhone = findViewById(R.id.txtEmployeeDetailPhone);
        txtEmployeeDetailAddress = findViewById(R.id.txtEmployeeDetailAddress);
        btnEditEmployee = findViewById(R.id.btnEditEmployee);
        btnUpdateEmployee = findViewById(R.id.btnUpdateEmployee);
        btnDeleteEmployee = findViewById(R.id.btnDeleteEmployee);

        // Thiết lập sự kiện click cho các nút
        btnEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEmployee();
            }
        });

        btnUpdateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btnDeleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });

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
        loadEmployeeDetails(dbHelper);
    }

    private void loadEmployeeDetails(DatabaseHelper dbHelper) {
        // Lấy thông tin nhân viên từ Intent
        Employee employee = getIntent().getParcelableExtra("employee");
        txtEmployeeDetailID.setText("ID: " + employee.getId());
        txtEmployeeDetailName.setText("Tên: " + employee.getName());
        txtEmployeeDetailPosition.setText("Chức vụ: " + employee.getPosition());
        txtEmployeeDetailEmail.setText("Email: " + employee.getEmail());
        txtEmployeeDetailPhone.setText("Số điện thoại: " + employee.getPhone());
        txtEmployeeDetailAddress.setText("Địa chỉ: " + employee.getAddress());

        // Đặt đúng giá trị cho Spinner dựa trên mã đơn vị của nhân viên
        String employeeDepartmentName = dbHelper.getDepartmentNameById(employee.getDepartmentId());
        if (employeeDepartmentName != null) {
            int departmentIndex = -1;
            for (int i = 0; i < spinnerDepartment.getCount(); i++) {
                if (spinnerDepartment.getItemAtPosition(i).toString().equalsIgnoreCase(employeeDepartmentName)) {
                    departmentIndex = i;
                    break;
                }
            }
            if (departmentIndex != -1) {
                spinnerDepartment.setSelection(departmentIndex);
            }
        }
    }

    private void editEmployee() {
        // Logic để chỉnh sửa thông tin nhân viên
        Toast.makeText(this, "Chỉnh sửa nhân viên", Toast.LENGTH_SHORT).show();
        // Implement your edit logic here
    }

    private void updateEmployee() {
        // Logic để cập nhật thông tin nhân viên
        Toast.makeText(this, "Cập nhật thông tin nhân viên", Toast.LENGTH_SHORT).show();
        // Implement your update logic here
    }

    private void deleteEmployee() {
        // Logic để xóa nhân viên
        Toast.makeText(this, "Xóa nhân viên", Toast.LENGTH_SHORT).show();
        // Implement your delete logic here
    }
}
