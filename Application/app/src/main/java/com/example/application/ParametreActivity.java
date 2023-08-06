package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ParametreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        getSupportActionBar().hide();

        ListView listView = findViewById(R.id.listview);

        List<String> list = new ArrayList<>();
        list.add("Changer de mode");
        list.add("Se deconnecter");

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.parametres);
        images.add(R.drawable.se_deconnecter);

        CustomListAdapter customListAdapter = new CustomListAdapter(this, list, images);
        listView.setAdapter(customListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //changer mode
                    startActivity(new Intent(ParametreActivity.this, LoginActivity.class));
                }else if (position == 1) {
                    //se deconnecter
                    SharedPreferences sharedPreferences = getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();
                    startActivity(new Intent(ParametreActivity.this, LoginActivity.class));
                } else {
                    //clicked grapes
                    // Do something for grapes
                }
            }
        });
    }




}



