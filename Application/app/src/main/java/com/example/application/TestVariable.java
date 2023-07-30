package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import android.os.Bundle;

import com.example.application.databinding.ActivityTestVariableBinding;

public class TestVariable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_variable);

        // Utilisez DataBindingUtil pour lier le layout
        ActivityTestVariableBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_test_variable);
        binding.setMyVariable("Valeur à afficher");
        // Assigne la valeur à la variable "myVariable"
        //String myVariableValue = "Valeur à afficher";
        //binding.setVariable(BR.myVariable, myVariableValue);

    }
}