package com.example.ankita.loginapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;
import java.util.jar.Manifest;

public class SettingActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ACTIVITY_TWO = 100;

    TextView textChangePassword, textTerms, textContactUs, textCall, textShare, textRate;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        textChangePassword = (TextView) findViewById(R.id.textChangePassword);
        textChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ChangePassword.class);
                startActivityForResult(intent, REQUEST_CODE_ACTIVITY_TWO);
            }
        });
        textTerms = (TextView) findViewById(R.id.textTermConditions);
        textTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg = textTerms.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        textContactUs = (TextView) findViewById(R.id.textContactUs);
        textContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String recepientEmail = ""; // either set to destination email or leave empty
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + recepientEmail));
                startActivity(intent);
            }
        });
        textCall = (TextView) findViewById(R.id.textCallSupport);
        textCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SettingActivity.this, "call", Toast.LENGTH_LONG).show();
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9595501819"));
                    if (ActivityCompat.checkSelfPermission(SettingActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        textShare = (TextView) findViewById(R.id.textShare);
        textShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg = textShare.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                intent.setType("text/plain");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        textRate = (TextView) findViewById(R.id.textRateus);
        textRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg = textRate.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DEFAULT);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                intent.setType("text/plain");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }
}
