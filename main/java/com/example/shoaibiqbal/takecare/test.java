//package com.example.shoaibiqbal.takecare;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//
///**
// * Created by BoboMan2 on 19/03/2016.
// */
//public class test extends Activity {
//
//    private ListView newsItemsListView;
//    ArrayList<String> listItems=new ArrayList<String>();
//    String[] days = {"ad", "adasd", "fgndgn"};
//
//    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
//    ArrayAdapter<String> adapter;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.newsfeed_frg);
//        newsItemsListView = (ListView) findViewById(R.id.listView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, days);
//        newsItemsListView.setAdapter(adapter);
//    }
//}
