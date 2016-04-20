package com.example.shoaibiqbal.takecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by BoboMan2 on 07/04/2016.
 */
public class PgPharmacyArea extends AppCompatActivity {

    private List<String> areaList = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_doctor_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.docCategoryToolbar);
        toolbar.setTitle("Select Area");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fetchSpecialities();
        ListView LV_Doc_Specs = (ListView) findViewById(R.id.listViewSpecialities);
        LV_Doc_Specs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PgDoctorCards.class);
                intent.putExtra("Areas", areaList.get(position));
                startActivity(intent);
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                areaList);
        LV_Doc_Specs.setAdapter(arrayAdapter);
    }

    private void addIntoList(String incoming) {
        areaList.add(incoming);
        Collections.sort(areaList);
    }

    private void fetchSpecialities() {
        addIntoList("Teen hatti");
        addIntoList("Aisha manzil");
        addIntoList("guru mandir");
        addIntoList("sadar");
        addIntoList("jail chorangi");
        addIntoList("PIB");
        addIntoList("Garden");
        addIntoList("Karimabad");
        addIntoList("Gulshan");
        addIntoList("Light house");
    }
}
