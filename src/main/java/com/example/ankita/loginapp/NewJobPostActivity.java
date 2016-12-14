package com.example.ankita.loginapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewJobPostActivity extends AppCompatActivity implements TextWatcher,View.OnClickListener,DatePickerDialog.OnDateSetListener {

    public static final int REQUEST_CODE_ACTIVITY_JOB =100;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final String TAG = "NewJobPostActivity";

    EditText editJobLocation,editJobMinHour,editJobMaxHour,editJobExperience,editJobResponsibilty,editjobDescription;
    AutoCompleteTextView autotextJobCatogary;
    TextInputLayout textJobLocation,textJobCategory,textJobMinHour,textJobMaxHour,textJobExperience,textJobResponsibility,textJobDescription;
    RadioGroup rdogrp_JobTime,rdogrp_JobDay;
    TextView textJobdate;
    Button btn_Jobsubmit;
    public static final String[] JOBCATEGORY={"Accountant","Clerk","Lecturer","Assistant","Artisan","Cashier"};
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job_post);


        getSupportActionBar().setTitle("Add New Job Post");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editJobLocation = (EditText)findViewById(R.id.editJobLocation);
        editJobLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try
                {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(NewJobPostActivity.this);
                    startActivityForResult(intent,PLACE_AUTOCOMPLETE_REQUEST_CODE);
                }
                catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        editJobMinHour = (EditText)findViewById(R.id.editJobMinHour);
        editJobMinHour.addTextChangedListener(this);

        editJobMaxHour = (EditText)findViewById(R.id.editJobMaxHour);
        editJobMaxHour.addTextChangedListener(this);

        editJobExperience = (EditText)findViewById(R.id.editJobExperience);
        editJobExperience.addTextChangedListener(this);

        textJobdate = (TextView) findViewById(R.id.textjobStartDate);
        textJobdate.setText("Date");
        textJobdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: ");
                flag = false;
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd=DatePickerDialog.newInstance(NewJobPostActivity.this,
                        now.get(Calendar.DAY_OF_MONTH),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.YEAR));
                Log.i(TAG, "onClick: DatePicker Date display");
                dpd.show(getFragmentManager(), "Date Picker");
                Log.i(TAG, "onClick: Date picker Date Found");
            }
        });

        editjobDescription = (EditText)findViewById(R.id.editJobdescription);
        editjobDescription.addTextChangedListener(this);

        editJobResponsibilty = (EditText)findViewById(R.id.editJobResponsibilty);
        editJobResponsibilty.addTextChangedListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, JOBCATEGORY);
        autotextJobCatogary=(AutoCompleteTextView)findViewById(R.id.autoTextJobcategory);
        autotextJobCatogary.setAdapter(adapter);
        autotextJobCatogary.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    autotextJobCatogary.showDropDown();
                }
            }
        });

        textJobLocation=(TextInputLayout)findViewById(R.id.textJobLocation);
        textJobCategory=(TextInputLayout)findViewById(R.id.textJobcategory);
        textJobMinHour=(TextInputLayout)findViewById(R.id.textJobMinHour);
        textJobMaxHour=(TextInputLayout)findViewById(R.id.textJobMaxHour);
        textJobExperience=(TextInputLayout)findViewById(R.id.textJobExperience);
        textJobResponsibility=(TextInputLayout)findViewById(R.id.textJobResponsibilty);
        textJobDescription=(TextInputLayout)findViewById(R.id.textJobDescription);

        rdogrp_JobTime=(RadioGroup)findViewById(R.id.rdogrpjobTime);
        rdogrp_JobDay=(RadioGroup)findViewById(R.id.rdogrpjobDays);

        btn_Jobsubmit=(Button)findViewById(R.id.btn_JobNewSubmit);
        btn_Jobsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                Log.i(TAG, "onClick: startActivityForResult");
                intent.putExtra("LOCATION",editJobLocation.getText().toString());
                intent.putExtra("POST",autotextJobCatogary.getText().toString());
                intent.putExtra("EXPERIENCE",editJobExperience.getText().toString());
                if(rdogrp_JobTime.getCheckedRadioButtonId()==R.id.radio_FullTime)
                {
                    intent.putExtra("TIME","Full Time");
                }
                else if(rdogrp_JobTime.getCheckedRadioButtonId()==R.id.radio_PartTime)
                {
                    intent.putExtra("TIME","Part Time");
                }
                else
                {
                    intent.putExtra("TIME","Seasonal");
                }
                intent.putExtra("DATE",textJobdate.getText().toString());
                intent.putExtra("MINWAGE",editJobMinHour.getText().toString());
                intent.putExtra("MAXWAGE",editJobMaxHour.getText().toString());
                Log.i(TAG, "onClick: location n post found");
                setResult(RESULT_OK,intent);
                Log.i(TAG, "onClick: resultok");
                finish();
            }
        });


    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        textJobLocation.setError("");
        textJobCategory.setError("");
        textJobMinHour.setError("");
        textJobMaxHour.setError("");
        textJobExperience.setError("");
        textJobResponsibility.setError("");
        textJobDescription.setError("");

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);
                place.getName();
                editJobLocation.setText(place.getAddress());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(NewJobPostActivity.this,"Submit",Toast.LENGTH_LONG).show();

    }

    /*@Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.DAY_OF_MONTH, hourOfDay);
        calender.set(Calendar.MONTH, minute);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-mm-yy");

        String formatted = format1.format(calender.getTime());
        editJobdate.setText(formatted);
    }*/

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal=Calendar.getInstance();
        Log.i(TAG, "onDateSet: ");
        cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        cal.set(Calendar.MONTH,monthOfYear);
        cal.set(Calendar.YEAR,year);
        Log.i(TAG, "onDateSet: DateFormat");
        SimpleDateFormat format1 = new SimpleDateFormat("dd-mm-yyyy");

        String formatted = format1.format(cal.getTime());
        textJobdate.setText(formatted);
        Log.i(TAG, "onDateSet: DateFormat Found");
    }
}
