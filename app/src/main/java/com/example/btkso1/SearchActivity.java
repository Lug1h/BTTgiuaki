package com.example.btkso1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    private EditText etSearch;
    private Button btnSearch;
    private ListView lvEmployees;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        lvEmployees = findViewById(R.id.lvEmployees);
        dbHelper = new DatabaseHelper(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEmployees();
            }
        });
    }

    private void searchEmployees() {
        String searchTerm = etSearch.getText().toString();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + DatabaseHelper.TABLE_EMPLOYEE +
                " WHERE " + DatabaseHelper.COLUMN_EMPLOYEE_NAME + " LIKE ? " +
                " OR " + DatabaseHelper.COLUMN_EMPLOYEE_ID + " LIKE ?";
        Cursor cursor = db.rawQuery(query, new String[]{"%" + searchTerm + "%", "%" + searchTerm + "%"});

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Không tìm thấy nhân viên", Toast.LENGTH_SHORT).show();
        } else {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_2,
                    cursor,
                    new String[]{DatabaseHelper.COLUMN_EMPLOYEE_NAME, DatabaseHelper.COLUMN_EMPLOYEE_EMAIL},
                    new int[]{android.R.id.text1, android.R.id.text2},
                    0
            );

            lvEmployees.setAdapter(adapter);
        }
    }
}
