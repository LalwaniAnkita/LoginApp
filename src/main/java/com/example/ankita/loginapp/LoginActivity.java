package com.example.ankita.loginapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    public static final int REQUEST_CODE_ACTIVITY_TWO =100;
    public static final  String TAG = LoginActivity.class.getSimpleName();

    EditText edit_Email,edit_Password,edit_Referal;
    Button btn_submit,btn_facebook,btn_gmail;
    boolean islogin;
    TextInputLayout text_email,text_password,text_referal;
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = getApplicationContext();

        getSupportActionBar().setTitle("Sign Up With");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        islogin = (getIntent().getBooleanExtra("IS_LOGIN",false));

        edit_Email = (EditText)findViewById(R.id.editEmail);
        edit_Email.addTextChangedListener(LoginActivity.this);

        edit_Password = (EditText)findViewById(R.id.editPassword);
        edit_Password.addTextChangedListener(LoginActivity.this);

        edit_Referal = (EditText)findViewById(R.id.editReferal);
        edit_Referal.addTextChangedListener(LoginActivity.this);

        if(islogin)
        {
            edit_Referal.setVisibility(View.GONE);
        }
        else
        {
            edit_Referal.setVisibility(View.VISIBLE);
        }

        text_email = (TextInputLayout)findViewById(R.id.email);
        text_password = (TextInputLayout)findViewById(R.id.password);
        text_referal = (TextInputLayout)findViewById(R.id.referal);

        btn_submit = (Button)findViewById(R.id.btnSubmit);
        btn_submit.setOnClickListener(this);

        btn_facebook = (Button)findViewById(R.id.btnfb);
        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSearchRequestedfb();
            }


        });
        btn_gmail = (Button)findViewById(R.id.btnfb);
        btn_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnSearchRequestedgmail();
            }


        });

    }


    @Override
    public void onClick(View v)
    {
        if(islogin)
        {
            signIn();
        }
        else
        {
            signUp();
        }
    }
    void  signUp()
    {
        String email= edit_Email.getText().toString();
        String password = edit_Password.getText().toString();
        String referal =  edit_Referal.getText().toString();
        if(email.length()<6)
        {
            text_email.setError("Invalid Email");
        }
        else if(password.length()<6)
        {
            text_password.setError("Invalid password");
        }
        else if(referal.length()<4)
        {
            text_referal.setError("Invalid referalcode");
        }
        else
        {
            PreferanceManager.putEmail(mContext,email);
            PreferanceManager.putPassword(mContext,password);
            PreferanceManager.putReferal(mContext,referal);
            Intent intent = new Intent(LoginActivity.this,InformationActivity.class);
            intent.putExtra("EXTRA_EMAIL",edit_Email.getText().toString());
            startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);

        }

    }
    void signIn()
    {
        if(edit_Email.getText().toString().length()<4)
        {
            text_email.setError("Invalid Email");
        }
        else if(edit_Password.getText().toString().length()<4)
        {
            text_password.setError("Invalid password");
        }
        else
        {

            String email = edit_Email.getText().toString();
            String password = edit_Password.getText().toString();


            if(PreferanceManager.getEmail(mContext).equalsIgnoreCase(email) && PreferanceManager.getPassword(mContext).equalsIgnoreCase(password))
            {
                Intent intent = new Intent(LoginActivity.this,InformationActivity.class);
                intent.putExtra("EXTRA_EMAIL",edit_Email.getText().toString());
                startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);

            }
            else
            {
                Toast.makeText(LoginActivity.this,"Invalid Login",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "onActivityResult: ");

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ACTIVITY_TWO) {
                int resultFig = data.getIntExtra(Const.EXTRA_RESULT_DATA, 0);

                Log.i(TAG, "onActivityResult: " + resultFig);

            }
        }
    }

    protected void OnSearchRequestedfb()
    {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY,"www.facebook.com");
        startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
    }

    protected void OnSearchRequestedgmail()
    {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY,"www.gmail.com");
        startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        text_email.setError("");
        text_password.setError("");
        text_referal.setError("");
    }

}





