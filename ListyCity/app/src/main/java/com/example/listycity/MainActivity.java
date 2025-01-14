package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityListView;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityListView = findViewById(R.id.city_list);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button deleteCityButton = findViewById(R.id.delete_city_button);
        LinearLayout addCityLayout = findViewById(R.id.add_city_layout);
        EditText inputCityName = findViewById(R.id.input_city_name);
        Button confirmButton = findViewById(R.id.confirm_button);

        String[] cities = {
                "Edmonton", "Vancouver", "Moscow", "Sydney",
                "Berlin", "Vienna", "Tokyo", "Beijing",
                "Osaka", "New Delhi"
        };

        dataList = new ArrayList<>(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataList
        );

        cityListView.setAdapter(cityAdapter);
        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
        });
        addCityButton.setOnClickListener(v -> {
            if (addCityLayout.getVisibility() == View.GONE) {
                addCityLayout.setVisibility(View.VISIBLE);
            } else {
                addCityLayout.setVisibility(View.GONE);
            }
        });

        confirmButton.setOnClickListener(v -> {
            String newCity = inputCityName.getText().toString().trim();
            if (!newCity.isEmpty()) {
                if (!dataList.contains(newCity)) {
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    inputCityName.setText("");
                    addCityLayout.setVisibility(View.GONE);
                }
            }
        });

        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                selectedCity = null;
            }
        });
    }
}
