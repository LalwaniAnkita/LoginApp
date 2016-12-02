package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{
    private static final int REQUEST_CODE_ACTIVITY_TOW = 100;
    Button signin,signup;
    SharedPreferences sharedPreferences;
    String MYPREFERENCE;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin = (Button) findViewById(R.id.btnSignin);
        signup = (Button) findViewById(R.id.btnSignup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("IS_LOGIN",true);
                startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TOW);


            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("IS_LOGIN",false);
                startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TOW);
            }
        });

    }

}
