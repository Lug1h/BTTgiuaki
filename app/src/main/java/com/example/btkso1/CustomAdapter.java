package com.example.btkso1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import kotlin.Unit;

public class CustomAdapter extends ArrayAdapter<Object> {

    private List<Unit> unitList;
    private List<Employee> employeeList;

    public CustomAdapter(Context context, int list_item_layout, List<Unit> unitList, List<Employee> employeeList) {
        super(context, 0);
        this.unitList = unitList;
        this.employeeList = employeeList;
    }

    @Override
    public int getCount() {
        return unitList.size() + employeeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < unitList.size()) {
            return 0; // View type for unit
        } else {
            return 1; // View type for employee
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2; // Two types of views: unit and employee
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            switch (viewType) {
                case 0:
                    convertView = inflater.inflate(R.layout.unit_item_layout, parent, false);
                    break;
                case 1:
                    convertView = inflater.inflate(R.layout.employee_item_layout, parent, false);
                    break;
            }
        }


        return convertView;
    }

    public void updateLists(List<Unit> unitList, List<Employee> employeeList) {
        this.unitList = unitList;
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }
}
