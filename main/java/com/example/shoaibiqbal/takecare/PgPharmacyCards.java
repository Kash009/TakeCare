package com.example.shoaibiqbal.takecare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BoboMan2 on 07/04/2016.
 */
public class PgPharmacyCards extends AppCompatActivity {

    protected List<Pharmacy> allPharmacies = new ArrayList<Pharmacy>();

    public class Pharmacy {
        public String name;
        public String location;
        public String address;

        public Pharmacy(String name, String location) {
            this.name = name;
            this.location = location;
            this.address = "PLACE ADDRESS HEREE FORM DB/XML";
        }
    }
    //replace this with cursor adapter as in bookmarks of chrome
    //right now to check cards
    public class PharmacyCardAdapter extends ArrayAdapter<Pharmacy> {
        private final Context context;
        private final List<Pharmacy> allPharmacyRef;
        //later articles input can be used for finding specific photos
        public PharmacyCardAdapter(Context context, List<Pharmacy> pharmacies) {
            super(context, R.layout.pharmacy_row_layout, pharmacies);
            this.context = context;
            this.allPharmacyRef = pharmacies;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myInflater.inflate(R.layout.pharmacy_row_layout, parent, false);
            }

            TextView text = (TextView) convertView.findViewById(R.id.tv_pharmacy_name);
            text.setText(allPharmacyRef.get(position).name);

            text = (TextView) convertView.findViewById(R.id.tv_pharmacy_area);
            text.setText(allPharmacyRef.get(position).location);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_pharmacy_cards);

        Toolbar toolbar = (Toolbar) findViewById(R.id.pharmCardToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fetchPharmacies();
        ListView LV_Pharm_Cards = (ListView) findViewById(R.id.listViewPharmCards);
        LV_Pharm_Cards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(getApplicationContext(), PgDoctorProfile.class);
                //intent.putExtra("doctorSpeciality", specialitiesList.get(position));
                //startActivity(intent);
            }
        });
        PharmacyCardAdapter arrayAdapter = new PharmacyCardAdapter(
                this, allPharmacies);

        LV_Pharm_Cards.setAdapter(arrayAdapter);
    }

    protected void fetchPharmacies() {
        allPharmacies.add(new Pharmacy("Universal Medicos", "Gulistan-e-Jauhar"));
        allPharmacies.add(new Pharmacy("Seven 86", "Gulistan-e-Jauhar"));
        allPharmacies.add(new Pharmacy("Ifjn asdksa", "Gulshan"));
        allPharmacies.add(new Pharmacy("Hello World", "Nazimabad"));
        allPharmacies.add(new Pharmacy("Key Medicos", "Karimabad"));
        allPharmacies.add(new Pharmacy("Suffa Aloba", "DHA"));
    }
}
