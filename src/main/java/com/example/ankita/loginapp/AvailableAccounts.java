package com.example.ankita.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AvailableAccounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_accounts);
        Toast.makeText(AvailableAccounts.this,"Success",Toast.LENGTH_LONG).show();
    }
}
