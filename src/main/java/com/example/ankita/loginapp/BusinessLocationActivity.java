package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.MapView;

import static com.example.ankita.loginapp.businessActivity.CATEGORY;

public class BusinessLocationActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher {

    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final int REQUEST_CODE_ACTIVITY_TWO = 100;
    private static final String TAG = "BusinessLocationActivity";

    EditText editBusinessAddress, editBusinessOptional, editBusinessPersonName, editBusinessPersonContact;
    Button btnBusinessLocationSubmit;
    TextInputLayout textInputAddress, textInputAddOptional, textInputPersonName, textInputPersonContact;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_location);

        mContext = getApplicationContext();

        getSupportActionBar().setTitle("Business Location");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        editBusinessAddress = (EditText) findViewById(R.id.editBusinessAddress);
       // editBusinessAddress.setSelected(false);
        editBusinessAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try
                {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(BusinessLocationActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                }
                catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }


            }
        });


        editBusinessOptional = (EditText) findViewById(R.id.editOptionalAddress);
        editBusinessOptional.addTextChangedListener(BusinessLocationActivity.this);

        editBusinessPersonName = (EditText) findViewById(R.id.editPersonName);
        editBusinessPersonName.addTextChangedListener(BusinessLocationActivity.this);

        editBusinessPersonContact = (EditText) findViewById(R.id.editPersonNo);
        editBusinessPersonContact.addTextChangedListener(BusinessLocationActivity.this);

        textInputAddress = (TextInputLayout) findViewById(R.id.textInputAddress);
        textInputAddOptional = (TextInputLayout) findViewById(R.id.textAddressOptional);
        textInputPersonName = (TextInputLayout) findViewById(R.id.textPersonName);
        textInputPersonContact = (TextInputLayout) findViewById(R.id.textPersonNo);

        btnBusinessLocationSubmit = (Button)findViewById(R.id.btn_BusinessLocationSubmit);
        btnBusinessLocationSubmit.setOnClickListener(this);


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Place place = PlaceAutocomplete.getPlace(this, data);
                place.getName();
                editBusinessAddress.setText(place.getAddress());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void onClick(View v) {

        String businessAddress = editBusinessAddress.getText().toString();
        String businessAddOptional = editBusinessOptional.getText().toString();
        String businessPersonName = editBusinessPersonName.getText().toString();
        String businessPersonContact = editBusinessPersonContact.getText().toString();

        if (businessAddOptional.length() < 4) {
            textInputAddOptional.setError("Invalid optional address");
        } else if (businessPersonName.length() < 4) {
            textInputPersonName.setError("Invalid Person Name ");

        } else if (businessPersonContact.length() < 10) {
            textInputPersonContact.setError("Invalid Contact No");
        } else {
            PreferanceManager.putBusinessLocation(mContext, businessAddress);
            PreferanceManager.putBusinessOptinalLocation(mContext, businessAddOptional);
            PreferanceManager.putBusinessPersonName(mContext, businessPersonName);
            PreferanceManager.putBusinessPersonContact(mContext, businessPersonContact);

            Intent intent = new Intent(BusinessLocationActivity.this, MapActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ACTIVITY_TWO);
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
        textInputAddOptional.setError("");
        textInputPersonName.setError("");
        textInputPersonContact.setError("");

    }



}
