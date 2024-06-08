package com.example.contactmanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.example.btkso1.Employee;
import com.example.btkso1.EmployeeDetailActivity;
import com.example.btkso1.R;

import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    private Context context;
    private ArrayList<Employee> employees;

    public EmployeeAdapter(@NonNull Context context, ArrayList<Employee> employees) {
        super(context, 0, employees);
        this.context = context;
        this.employees = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        }

        Employee employee = employees.get(position);

        // Liên kết các TextView và ImageView
        TextView tvEmployeeID = convertView.findViewById(R.id.txtEmployeeDetailID);
        TextView tvEmployeeName = convertView.findViewById(R.id.txtEmployeeDetailName);
        TextView tvEmployeePosition = convertView.findViewById(R.id.txtEmployeeDetailPosition);
        TextView tvEmployeePhone = convertView.findViewById(R.id.txtEmployeeDetailPhone);
        TextView tvEmployeeEmail = convertView.findViewById(R.id.txtEmployeeDetailEmail);
        TextView tvEmployeeAddress = convertView.findViewById(R.id.txtEmployeeDetailAddress);
        ImageView imgEmployeeAvatar = convertView.findViewById(R.id.imgEmployeeDetailAvatar);

        // Cập nhật dữ liệu vào các TextView và ImageView
        tvEmployeeID.setText("ID: " + employee.getId());
        tvEmployeeName.setText("Tên: " + employee.getName());
        tvEmployeePosition.setText("Chức vụ: " + employee.getPosition());
        tvEmployeeEmail.setText("Email: " + employee.getEmail());
        tvEmployeePhone.setText("Số điện thoại: " + employee.getPhone());
        tvEmployeeAddress.setText("Địa chỉ: " + employee.getAddress());

        // Cập nhật ảnh đại diện nếu cần
        // Glide.with(context).load(employee.getAvatar()).into(imgEmployeeAvatar);

        // Thêm sự kiện nhấn vào item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EmployeeDetailActivity.class);
                intent.putExtra("ID", employee.getId());
                intent.putExtra("Name", employee.getName());
                intent.putExtra("Position", employee.getPosition());
                intent.putExtra("Email", employee.getEmail());
                intent.putExtra("Phone", employee.getPhone());
                intent.putExtra("Address", employee.getAddress());
                intent.putExtra("Avatar", employee.getAvatar());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
