package com.example.ankita.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class workActivity extends AppCompatActivity {

    TextView textWork,textHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);


        getSupportActionBar().setTitle("I am looking for");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textWork = (TextView) findViewById(R.id.textWork);
        textWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(workActivity.this,businessActivity.class);

                startActivity(intent);
            }
        });
        textHelp =(TextView)findViewById(R.id.textHelp);



    }
}
