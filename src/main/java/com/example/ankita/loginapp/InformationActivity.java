package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private static final int REQUEST_CODE_ACTIVITY_TOW = 100;
    EditText fname, lname, mobile, email;
    RadioGroup rdogrp;
    RadioButton male, female;
    TextInputLayout textfName, textlName, textMobile;
    Button btn_submit;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        getSupportActionBar().setTitle("Your Information");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = getApplicationContext();

        fname = (EditText) findViewById(R.id.editfname);
        fname.addTextChangedListener(InformationActivity.this);

        lname = (EditText) findViewById(R.id.editlname);
        lname.addTextChangedListener(InformationActivity.this);

        mobile = (EditText) findViewById(R.id.editmobile);
        mobile.addTextChangedListener(InformationActivity.this);

        email = (EditText) findViewById(R.id.editEmail);

        textfName = (TextInputLayout) findViewById(R.id.textfirstName);
        textlName = (TextInputLayout) findViewById(R.id.textlastName);
        textMobile = (TextInputLayout) findViewById(R.id.textmobile);

        btn_submit = (Button) findViewById(R.id.btn_infomationSubmit);
        btn_submit.setOnClickListener(this);

        rdogrp = (RadioGroup)findViewById(R.id.rdogrp);
        /*male = (RadioButton) findViewById(R.id.radio_Male);
        female = (RadioButton) findViewById(R.id.radio_Female);*/

        fname.setText(PreferanceManager.getFirstName(mContext));
        lname.setText(PreferanceManager.getLastName(mContext));
        mobile.setText(PreferanceManager.getMobile(mContext));
        email.setText(PreferanceManager.getEmail(mContext));
        if(PreferanceManager.getGender(mContext).equalsIgnoreCase("Male"))
        {
            rdogrp.check(R.id.radio_Male);
        }
        else
        {
            rdogrp.check(R.id.radio_Female);
        }





    }

    @Override
    public void onClick(View v) {

        Log.i("InformationActivity", "onClick: ");

        String fName = fname.getText().toString();
        String lName = lname.getText().toString();
        String mobileNo = mobile.getText().toString();

        if (fName.length() < 4) {
            textfName.setError("Invalid name");
        } else if (lName.length() < 4) {
            textlName.setError("Invalid last name");
        } else if (mobileNo.length() < 10) {
            textMobile.setError("Ivalid Mobile No..");
        } else {
            PreferanceManager.putFirstName(mContext, fName);
            PreferanceManager.putLastName(mContext, lName);
            PreferanceManager.putMobile(mContext, mobileNo);
            if(rdogrp.getCheckedRadioButtonId()== R.id.radio_Male)
            {
                PreferanceManager.putGender(mContext,"Male");
            }
            else
            {
                PreferanceManager.putGender(mContext,"Female");
            }
            Intent intent = new Intent(InformationActivity.this, workActivity.class);
            intent.putExtra("SUBMIT", true);
            startActivityForResult(intent, REQUEST_CODE_ACTIVITY_TOW);
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        textfName.setError("");
        textlName.setError("");
        textMobile.setError("");
    }
}
