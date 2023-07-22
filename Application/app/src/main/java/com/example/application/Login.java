package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText loginText , passwordText ;
    Button btnLogin ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginText = (EditText) findViewById(R.id.login);
        passwordText = (EditText) findViewById((R.id.password));

        btnLogin = (Button) findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginText.getText().toString();
                String password = passwordText.getText().toString();

                if(username.equals("user") && (password.equals("123"))){
                    Toast.makeText(Login.this, "Welcome" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Notification.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
