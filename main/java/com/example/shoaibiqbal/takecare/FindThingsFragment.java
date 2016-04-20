package com.example.shoaibiqbal.takecare;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Shoaib Iqbal on 13-Mar-16.
 */
public class FindThingsFragment extends android.support.v4.app.Fragment{

    private ListView findThingsListView;
    private final String[] listItemsInFind = {"Doctors", "Laboratories", "Pharmacies"};
    private enum paralellListItems                  {DOCTORS, LABS, PHARM };
    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View currentView = inflater.inflate(R.layout.findthings_frg, container, false);
        findThingsListView = (ListView) currentView.findViewById(R.id.listViewFind);

        findThingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //int itemNo = (int) parent.getItemAtPosition(position);
                if (listItemsInFind[position].equals("Doctors")) {
                    Intent intent = new Intent(getActivity(), PgDoctorSpeciality.class);
                    startActivity(intent);
                }
                else if (listItemsInFind[position].equals("Pharmacies")) {
                    Intent intent = new Intent(getActivity(), PgPharmacyCards.class);
                    startActivity(intent);
                }
                else if (listItemsInFind[position].equals("Laboratories")) {
                    Intent intent = new Intent(getActivity(), PgLabCards.class);
                    startActivity(intent);
                }
            }
        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this.getActivity(),
                android.R.layout.simple_list_item_1,
                listItemsInFind);
        findThingsListView.setAdapter(arrayAdapter);

        return currentView;
    }
}
