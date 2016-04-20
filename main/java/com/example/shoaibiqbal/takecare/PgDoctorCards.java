package com.example.shoaibiqbal.takecare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shoaibiqbal.takecare.data.Reader;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by BoboMan2 on 07/04/2016.
 */
public class PgDoctorCards extends AppCompatActivity {

    private List<Doctor> allDoctors = new ArrayList<Doctor>();
    int i=1;
    private String currentDocSpec;

    public class Doctor {
        public String id;
        public String name;
        public String hospitalName;
        public String location;
        public String experience;
        //public String recm;

        public Doctor(String id, String name, String hospitalName, String location, String experience) {
            this.id = id;
            this.name = name;
            this.hospitalName = hospitalName;
            this.location = location;
            //this.recm = recm;
            this.experience = experience;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    //replace this with cursor adapter as in bookmarks of chrome
    //right now to check cards
    public class DoctorsCardAdapter extends ArrayAdapter<Doctor> implements Filterable{
        private final Context context;
        private final List<Doctor> allDoctorsRef;
        public List<Doctor> filteredDoctorsRef;

        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ArrayList<Doctor> tempList=new ArrayList<Doctor>();
                //constraint is the result from text you want to filter against.
                //objects is your data set you will filter from
                if (constraint == null || constraint.length() == 0) {
                        // No filter implemented we return all the list
                    filterResults.values = allDoctorsRef;
                    filterResults.count = allDoctorsRef.size();
                    filteredDoctorsRef = allDoctorsRef;
                }
                else {
                    for (Doctor p : allDoctorsRef) {
                        if (p.name.toUpperCase().startsWith(constraint.toString().toUpperCase())
                                || p.location.toUpperCase().startsWith(constraint.toString().toUpperCase()))
                            tempList.add(p);
                    }
                    //following two lines is very important
                    //as publish result can only take FilterResults objects
                    filterResults.values = tempList;
                    filterResults.count = tempList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    filteredDoctorsRef = (List<Doctor>) results.values;
                    notifyDataSetChanged();
                }
            }
        };

        @Override
        public Filter getFilter() {
            return myFilter;
        }

        @Override
        public int getCount() {
            return filteredDoctorsRef.size();
        }

        //later articles input can be used for finding specific photos
        public DoctorsCardAdapter(Context context, List<Doctor> doctors) {
            super(context, R.layout.doctors_row_layout, doctors);
            this.context = context;
            this.allDoctorsRef = doctors;
            this.filteredDoctorsRef = doctors;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myInflater.inflate(R.layout.doctors_row_layout, parent, false);
            }

            TextView text = (TextView) convertView.findViewById(R.id.tv_doctor_name);
            text.setText(filteredDoctorsRef.get(position).name);

            text = (TextView) convertView.findViewById(R.id.tv_doctor_hospital);
            text.setText(filteredDoctorsRef.get(position).hospitalName);

            text = (TextView) convertView.findViewById(R.id.tv_doctor_area);
            text.setText(filteredDoctorsRef.get(position).location);

            text = (TextView) convertView.findViewById(R.id.tv_doctor_exp);
            text.setText(filteredDoctorsRef.get(position).experience);

            //text = (TextView) convertView.findViewById(R.id.tv_doctor_recm);
            //text.setText(filteredDoctorsRef.get(position).recm);

            return convertView;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_doctor_cards);

        Toolbar toolbar = (Toolbar) findViewById(R.id.docCardToolbar);
        Bundle data = getIntent().getExtras();
        currentDocSpec = new String(data.getString("doctorSpeciality"));
        toolbar.setTitle(data.getString("doctorSpeciality") + "s");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        try {
            fetchDoctors();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        SearchView docSearch = (SearchView) findViewById(R.id.searchViewDoc);

        ListView LV_Doc_Cards = (ListView) findViewById(R.id.listViewDoctorCards);
        final DoctorsCardAdapter arrayAdapter = new DoctorsCardAdapter(
                this, allDoctors);
        LV_Doc_Cards.setAdapter(arrayAdapter);
        LV_Doc_Cards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PgDoctorProfile.class);
                //intent.putExtra("doctorSpeciality", specialitiesList.get(position));
                intent.putExtra("doctorID", arrayAdapter.filteredDoctorsRef.get(position).id);
                startActivity(intent);
            }
        });


        docSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void fetchDoctors() throws IOException, SAXException, ParserConfigurationException {
//        allDoctors.add(new Doctor("Dr", "Agha Khan Hospital", "Gulshan", "Karachi", "16", "7222"));
//        allDoctors.add(new Doctor("Hr", "Agha K Hospital", "Hadeed", "Karachi", "16", "752"));
//        allDoctors.add(new Doctor("Po", "Agha KHospital", "lol", "Karachi", "14", "7"));
//        allDoctors.add(new Doctor("Rehman", "Agha Khapital", "hello", "Karachi", "64", "22"));
//        allDoctors.add(new Doctor("Abdul", "han Hospital", "pc", "Karachi", "7", "72"));
//        allDoctors.add(new Doctor("Hman", " Khan Hospital", "bahar", "Karachi", "8", "282"));
//        allDoctors.add(new Doctor("An", "A Khan Hpital", "bus", "Karahi", "2", "22"));
//        allDoctors.add(new Doctor("Yo", "Agha Kha", "Uff", "Karhi", "11", "782"));
        InputStream filepath = getResources().openRawResource(R.raw.doctors_db);
        Reader myXMLreader = new Reader(filepath);

        List<Node> result = myXMLreader.fetchDoctor(currentDocSpec);

        for (int i=0; i<result.size(); i++) {
            Node nNode = result.get(i);
            Element eElement = (Element) nNode;
            allDoctors.add(new Doctor(
                    eElement.getAttribute("id"),
                    eElement.getAttribute("name"),
                    eElement.getElementsByTagName("location").item(0).getTextContent(),
                    eElement.getElementsByTagName("area").item(0).getTextContent(),
                    eElement.getElementsByTagName("exp").item(0).getTextContent()
                    )
            );
        }
    }
}
