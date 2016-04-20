package com.example.shoaibiqbal.takecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BoboMan2 on 15/04/2016.
 */
public class FrgRemedies extends Fragment {

    private ListView remediesListView;
    private List<String> articlesArray = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View currentView = inflater.inflate(R.layout.frg_remedies, container, false);
        remediesListView = (ListView) currentView.findViewById(R.id.listViewRemedies);
        fetchCommonProblems();
        remediesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //int itemNo = (int) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), PgFullArticle.class);
                intent.putExtra("problem", articlesArray.get(position));
                startActivity(intent);
            }
        });


        ArrayAdapter<String> arrayListAdapter = new ArrayAdapter<String>(
               this.getActivity(),
                android.R.layout.simple_list_item_1,
                articlesArray);

        remediesListView.setAdapter(arrayListAdapter);
        return currentView;
    }

    private void fetchCommonProblems() {
        articlesArray.add("Cold");
        articlesArray.add("Flu");
        articlesArray.add("Headache");
        articlesArray.add("Nausea");
        articlesArray.add("Acidity");
        articlesArray.add("Light Fever");
        articlesArray.add("Red Eye");
        articlesArray.add("Sore Throat");
    }
}
