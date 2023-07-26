package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application.model.Profil;

public class LoginActivity extends AppCompatActivity {

    EditText loginText , passwordText ;
    Button btnLogin ;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginText = (EditText) findViewById(R.id.user);
        passwordText = (EditText) findViewById((R.id.password));
        String username = loginText.getText().toString();
        String password = passwordText.getText().toString();
        Log.i(TAG, "laaaaaaaaaaaaaaaaaaaaa"+ username);
        Log.i(TAG, "looooooooooooooooooooo"+password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                /*String username = loginText.getText().toString();
                String password = passwordText.getText().toString();*/
                Log.i(TAG, username);
                Log.i(TAG, password);

                Profil profil = new Profil();
                profil.traitementLogin(username, password, new Profil.LoginCallback() {
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
        });


    }
}
