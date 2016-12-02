package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainScreenActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ACTIVITY_TWO =100;
    Button btnStartProgress;
    ProgressBar progressBar;
    private int mProgressStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;
    Context mContext;
    private String TAG = MainScreenActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        mContext = getApplicationContext();

        Log.i(TAG, "onCreate: "+PreferanceManager.getFirstName(mContext));
        Log.i(TAG, "onCreate: "+PreferanceManager.getLastName(mContext));
        Log.i(TAG, "onCreate: "+PreferanceManager.getMobile(mContext));
        Log.i(TAG, "onCreate: "+PreferanceManager.getEmail(mContext));
        progressBar = (ProgressBar)findViewById(R.id.progressbar_check);

//        btnStartProgress =(Button)findViewById(R.id.btnHome);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    public void run() {
                        while (mProgressStatus < 100) {
                            mProgressStatus = doOperation();

                            // Update the progress bar
                            progressBarHandler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(mProgressStatus);
                                }
                            });
                        }
                    }
                }).start();

            }
        });
    }






    public int doOperation()
    {
        if ((PreferanceManager.getEmail(mContext).isEmpty()) && (PreferanceManager.getPassword(mContext).isEmpty()) )
        {
            Intent intent = new Intent(MainScreenActivity.this,LoginActivity.class);
            startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
        }
        else if((PreferanceManager.getFirstName(mContext).isEmpty()) && (PreferanceManager.getLastName(mContext).isEmpty()) && (PreferanceManager.getMobile(mContext).isEmpty()))
        {
            Log.i(TAG, "doOperation: ");

            Intent intent = new Intent(MainScreenActivity.this,InformationActivity.class);
            startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);

        }
        else if ((PreferanceManager.getBusinessName(mContext).isEmpty()) && (PreferanceManager.getBusinessCategory(mContext).isEmpty()) && (PreferanceManager.getBusinessSubCategory(mContext).isEmpty()) && (PreferanceManager.getBusinessSubType(mContext).isEmpty()))
        {
            Intent intent = new Intent(MainScreenActivity.this,businessActivity.class);
            startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
        }
        else if((PreferanceManager.getBusinessLocation(mContext).isEmpty()) && (PreferanceManager.getBusinessOptionalLocation(mContext).isEmpty()) && (PreferanceManager.getBusinessPersonName(mContext).isEmpty()) && (PreferanceManager.getBusinessPersonContact(mContext).isEmpty()))
        {
            Intent intent = new Intent(MainScreenActivity.this,BusinessLocationActivity.class);
            startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
        }
        else
        {
            Intent intent = new Intent(MainScreenActivity.this,MapActivity.class);
            startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
        }
        return 100;
    }
}

