package com.example.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application.model.Profil;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText loginText , passwordText ;
    Button btnLogin ;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginText = (TextInputEditText) findViewById(R.id.user);
        passwordText = (TextInputEditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                /*String username = loginText.getText().toString();
                String password = passwordText.getText().toString();*/
                String username = loginText.getText().toString();
                String password = passwordText.getText().toString();

                Profil profil = new Profil();
                profil.traitementLogin(username, password, new Profil.LoginCallback() {
                    @Override
                    public void onLoginResult(boolean isSuccess) {
                        if (isSuccess) {
                            Log.i(TAG, "onLoginResult: success");
                            SharedPreferences sharedPreferences = getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user", username);
                            editor.apply();
                            startActivity(new Intent(LoginActivity.this,Acceuil.class));
                        } else {
                            Log.e(TAG, "onLoginResult: error");
                        }
                    }
                });
            }
        });
    }
    public void inscription(View view){
        startActivity(new Intent(  LoginActivity.this,Inscription.class));
    }
}
