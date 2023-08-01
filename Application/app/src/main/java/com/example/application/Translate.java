package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Translate extends AppCompatActivity {

    private EditText editTextToTranslate;
    private Spinner sourceLanguageSpinner;
    private Spinner targetLanguageSpinner;
    private Button translateButton;
    private TextView translatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        editTextToTranslate = findViewById(R.id.editTextToTranslate);
        targetLanguageSpinner = findViewById(R.id.targetLanguageSpinner);
        translateButton = findViewById(R.id.translateButton);
        translatedText = findViewById(R.id.translatedText);

        // Liste des langues cibles prises en charge
        final List<String> supportedTargetLanguages = new ArrayList<>();
        supportedTargetLanguages.add("en");
        supportedTargetLanguages.add("fr");
        // Ajoutez d'autres langues cibles prises en charge par l'API de traduction si nécessaire.

        // Adapter pour le Spinner de la langue cible
        ArrayAdapter<String> targetLanguageAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, supportedTargetLanguages);
        targetLanguageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        targetLanguageSpinner.setAdapter(targetLanguageAdapter);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToTranslate = editTextToTranslate.getText().toString();
                String targetLanguage = supportedTargetLanguages.get(targetLanguageSpinner.getSelectedItemPosition());

                String url = "https://libretranslate.com/translate";
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("q", textToTranslate);
                    requestBody.put("source", "auto");
                    requestBody.put("target", targetLanguage);
                    requestBody.put("format", "text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String translatedTextString = response.getString("translatedText");
                                    translatedText.setText(translatedTextString);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(Translate.this, "Translation failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                Volley.newRequestQueue(Translate.this).add(jsonObjectRequest);
            }
        });
    }
}


