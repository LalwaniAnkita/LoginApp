package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class businessActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher {

    private static final int REQUEST_CODE_ACTIVITY_TWO = 100 ;
    EditText edit_BusinessName,edit_BusinessCategoryType;
    AutoCompleteTextView autoedit_SubCategory,autoedit_Category;
    Button btnBusinessSubmit;
    TextInputLayout textInputName,textInputCategoryType;
    public static final String[] SUBCATEGORY={"Awesome","Good","Better","worst"};

    public  static  final String[] CATEGORY = {"Admin","Analyst","Designer","Developer","Manager"};

    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        mContext = getApplicationContext();

        getSupportActionBar().setTitle("Business Profile");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit_BusinessName = (EditText)findViewById(R.id.editBusinessName);
        edit_BusinessName.addTextChangedListener(businessActivity.this);
        edit_BusinessCategoryType = (EditText)findViewById(R.id.editSubCategoryType);
        edit_BusinessCategoryType.addTextChangedListener(businessActivity.this);

        btnBusinessSubmit = (Button)findViewById(R.id.btn_BusinessSubmit);
        btnBusinessSubmit.setOnClickListener(businessActivity.this);

        textInputName = (TextInputLayout)findViewById(R.id.textInputName);
        textInputCategoryType = (TextInputLayout)findViewById(R.id.textCategoryType);

        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, CATEGORY);
        autoedit_Category = (AutoCompleteTextView)
                findViewById(R.id.textautoBusinessCategory);
        autoedit_Category.setAdapter(adapter1);
        autoedit_Category.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    autoedit_Category.showDropDown();
                }
            }
        });


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SUBCATEGORY);
        autoedit_SubCategory = (AutoCompleteTextView)
                findViewById(R.id.autotextSubCategory);
        autoedit_SubCategory.setAdapter(adapter2);

        autoedit_SubCategory.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    autoedit_SubCategory.showDropDown();
                }

            }
        });





    }

    @Override
    public void onClick(View v)
    {
        String businessName = edit_BusinessName.getText().toString();
        String businessCategory = autoedit_Category.getText().toString();
        String businessSubCategory = autoedit_SubCategory.getText().toString();
        String businessSubType = edit_BusinessCategoryType.getText().toString();

        if(businessName.length() < 5 )
        {
            textInputName.setError("Invalid business name");
        }
        else if(businessSubType.length() < 4)
        {
            textInputCategoryType.setError("Invalid business category ");

        }
        else
        {
            PreferanceManager.putBusinessName(mContext,businessName);
            PreferanceManager.putBusinessCategory(mContext,businessCategory);
            PreferanceManager.putBusinessSubCategory(mContext,businessSubCategory);
            PreferanceManager.putBusinessSubCategoryType(mContext,businessSubType);


              Intent intent = new Intent(businessActivity.this,BusinessLocationActivity.class);
              startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
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
        textInputName.setError("");
        textInputCategoryType.setError("");

    }
}
