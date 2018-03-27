package com.example.zeynep.kutuphanedeyimm;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomListView extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        toolbar=(Toolbar)findViewById(R.id.toolbarview);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kütüphane Arama");

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<String> arrayLibrary=new ArrayList<>();
        arrayLibrary.addAll(Arrays.asList(getResources().getStringArray(R.array.array_library)));
        mAdapter = new ArrayAdapter<String>(CustomListView.this, android.R.layout.simple_list_item_1, arrayLibrary);
        listView.setAdapter(mAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomListView.this, SecondActivity.class);
                intent.putExtra("Kütüphane Adı", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

    listView.setAdapter(mAdapter);
}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.menu_search);
        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
