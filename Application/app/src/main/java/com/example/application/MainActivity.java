package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import com.example.application.controler.Methods;
import com.example.application.controler.ProfilControler;
import com.example.application.model.Model;
import com.example.application.model.Profil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button getData;
    private Button btnSendPostRequest;
    private static final String TAG = "MainActivity";
    Switch switcher;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        switcher = findViewById(R.id.switcher);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false); // light mode default

        if(nightMode){
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });

        getData = findViewById(R.id.getData);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<List<Model>> call = methods.getAllData();

                call.enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                        Log.e(TAG, "onResponse: code : "+ response.code());

                        List<Model> datas = response.body();
                        for (int i = 0; i < datas.size(); i++) {
                            Log.e(TAG, "onResponse: name : "+datas.get(i).getName());
                            Log.e(TAG, "onResponse: office : "+datas.get(i).getOffice());
                            Log.e(TAG, "onResponse: position : "+datas.get(i).getPosition());
                            Log.e(TAG, "onResponse: salary : "+datas.get(i).getSalary());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());
                    }
                });
            }
        });

        btnSendPostRequest = findViewById(R.id.btnSendPostRequest);
        btnSendPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSendPostRequestCliqued();
            }
        });

        ListView listView = findViewById(R.id.listview);

        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Orange");
        list.add("Banana");
        list.add("Grapes");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //clicked apple

                    startActivity(new Intent(MainActivity.this,AppleActivity.class));

                }else if(position==1){
                    //clicked orange
                    startActivity(new Intent(MainActivity.this,OrangeActivity.class));
                }else if(position==2){
                    //clicked orange
                    startActivity(new Intent(MainActivity.this,Acceuil.class));
                }else{

                }
            }
        });
    }

    private void btnSendPostRequestCliqued() {
        /*Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getModelInformation("Mimi", "CEO", "Bold entreprise", "20000000");
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.e(TAG, "onResponse: code : "+response.code());
                Log.e(TAG, "onResponse: name : "+response.body().getName());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        }); */
        Profil profil = new Profil();
        profil.traitementLogin("jayks", "12345", new Profil.LoginCallback() {
            @Override
            public void onLoginResult(boolean isSuccess) {
                if (isSuccess) {
                    Log.i(TAG, "onLoginResult: success");
                } else {
                    Log.e(TAG, "onLoginResult: error");
                }
            }
        });

    }
}