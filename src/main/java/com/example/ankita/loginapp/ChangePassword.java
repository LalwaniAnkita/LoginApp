package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity implements TextWatcher{

    public static final int REQUEST_CODE_ACTIVITY_TWO =100;
    TextInputLayout textcurrentPassword, textnewPassword, textconfirmPassword;
    EditText editCurrentPass,editNewPass,editConfirmPass;
    Button btn_Change_Password;
    Context mCotext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        getSupportActionBar().setTitle("Change Password");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCotext=getApplicationContext();

        editCurrentPass=(EditText)findViewById(R.id.editCurrentPassword);
        editCurrentPass.addTextChangedListener(ChangePassword.this);

        editNewPass=(EditText)findViewById(R.id.editNewpassword);
        editNewPass.addTextChangedListener(ChangePassword.this);

        editConfirmPass=(EditText)findViewById(R.id.editConfirmPassword);
        editConfirmPass.addTextChangedListener(ChangePassword.this);

        textcurrentPassword=(TextInputLayout)findViewById(R.id.textCurrentPassword);
        textnewPassword=(TextInputLayout)findViewById(R.id.textNewpassword);
        textconfirmPassword=(TextInputLayout)findViewById(R.id.textConfirmPassword);

        btn_Change_Password=(Button)findViewById(R.id.btnSubmitChangePassword);
        btn_Change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String current=editCurrentPass.getText().toString();
                String newPass=editNewPass.getText().toString();
                String confirm=editConfirmPass.getText().toString();

                if(!(PreferanceManager.getPassword(mCotext).equalsIgnoreCase(current)))
                {
                    textcurrentPassword.setError("Incorrect Current Password");
                }
                else if(newPass.length()<6)
                {
                    textnewPassword.setError("Week password");
                }
                else if(!(newPass.equalsIgnoreCase(confirm)))
                {
                    textconfirmPassword.setError("Re-type Confirm Password");
                }
                else
                {
                    PreferanceManager.putPassword(mCotext,newPass);
                    Intent intent=new Intent(ChangePassword.this,LoginActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        textcurrentPassword.setError("");
        textnewPassword.setError("");
        textconfirmPassword.setError("");
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}
