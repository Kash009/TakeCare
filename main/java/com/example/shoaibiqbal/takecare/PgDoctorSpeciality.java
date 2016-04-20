package com.example.shoaibiqbal.takecare;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.shoaibiqbal.takecare.data.Reader;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by BoboMan2 on 07/04/2016.
 */
public class PgDoctorSpeciality extends AppCompatActivity {

    private List<String> specialitiesList = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_doctor_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.docCategoryToolbar);
        toolbar.setTitle("Select Area of Speciality");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        try {
            fetchSpecialities();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ListView LV_Doc_Specs = (ListView) findViewById(R.id.listViewSpecialities);
        LV_Doc_Specs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PgDoctorCards.class);
                intent.putExtra("doctorSpeciality", specialitiesList.get(position));
                startActivity(intent);
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                specialitiesList);
        LV_Doc_Specs.setAdapter(arrayAdapter);
    }

    private void addIntoList(String incoming) {
        specialitiesList.add(incoming);
        Collections.sort(specialitiesList);
    }

    private void setSpecialitiesList(List<String> databaseList) {
        specialitiesList = databaseList;
        Collections.sort(specialitiesList);
    }

    private void fetchSpecialities() throws IOException, SAXException, ParserConfigurationException, PackageManager.NameNotFoundException {
        InputStream is = getResources().openRawResource(R.raw.doctors_db);

        Reader reader_xml = new Reader(is);
        setSpecialitiesList(reader_xml.fetchDoctorCategory());
    }
}
