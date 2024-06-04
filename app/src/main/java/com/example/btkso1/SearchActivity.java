package com.example.btkso1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;

public class SearchActivity extends AppCompatActivity {

    private EditText etSearchQuery;
    private Button btnPerformSearch;
    private TextView tvSearchResults;
    private ListView listView;

    private List<Unit> unitList;
    private List<Employee> employeeList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearchQuery = findViewById(R.id.etSearchQuery);
        btnPerformSearch = findViewById(R.id.btnPerformSearch);
        tvSearchResults = findViewById(R.id.tvSearchResults);
        listView = findViewById(R.id.lv);



        adapter = new CustomAdapter(this, R.layout.list_item_layout, unitList, employeeList);
        listView.setAdapter(adapter);

        btnPerformSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String query = etSearchQuery.getText().toString().toLowerCase().trim();
        if (TextUtils.isEmpty(query)) {
            Toast.makeText(this, "Vui lòng nhập từ khóa tìm kiếm", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Unit> searchUnits = new ArrayList<>();
        List<Employee> searchEmployees = new ArrayList<>();



        if (searchUnits.isEmpty() && searchEmployees.isEmpty()) {
            tvSearchResults.setText("Không tìm thấy kết quả phù hợp");
        } else {
            tvSearchResults.setText("Kết quả tìm kiếm:");
        }

        adapter.updateLists(searchUnits, searchEmployees);
    }


}
