package com.example.ankita.loginapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static com.google.android.gms.internal.zzs.TAG;


public class PreferanceManager
{
    SharedPreferences sharedPreferences;
    public static final String MYPREFERENCE= null;

    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_REFERAL="referal";

    public static final String KEYBUSINESSNAME="name";
    public static final String KEYBUSINESSCATEGORY="category";
    public static final String KEYBUSINESSSUBCATEGORY="subcategory";
    public static final String KEYBUSINESSSUBTYPE="subtype"
            ;
    public static final String KEYFIRSTNAME="fname";
    public static final String KEYLASTNAME="lname";
    public static final String KEYMOBILENO="mobile";
    public static final String KEYGENDER="gender";

    public static final String KEYBUSINESSADDRESS="gender";
    public static final String KEYBUSINESSOPTIONALLOCATION="gender";
    public static final String KEYBUSINESSPERSONNAME="gender";
    public static final String KEYBUSINESSPERSONCONTACT="gender";




    //Login Activity
    public static final String getEmail(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefEmail = sharedPreferences.getString(KEY_EMAIL,"");
        return prefEmail;

    }

    public static void putEmail(Context mContext, String email) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, String.valueOf(email));
        editor.commit();
    }
    public static final String getPassword(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefPassword = sharedPreferences.getString(KEY_PASSWORD,"");
        return prefPassword;

    }
    public static void putPassword(Context mContext, String  password) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }
    public static final String getReferal(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefreferal = sharedPreferences.getString(KEY_REFERAL,"");
        return prefreferal;

    }
    public static void putReferal(Context mContext, String  referal) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_REFERAL, referal);
        editor.commit();
    }



//Information Activity
    public static final String getFirstName(Context mContext)
    {
        Log.i(TAG, "getFirstName: "+mContext);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String preffName = sharedPreferences.getString(KEYFIRSTNAME,"");
        return preffName;

    }
    public static void putFirstName(Context mContext, String fName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYFIRSTNAME, fName);
        editor.commit();
    }

    public static final String getLastName(Context mContext)
    {
        Log.i(TAG, "getLastName: "+mContext);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String preflName = sharedPreferences.getString(KEYLASTNAME,"");
        return preflName;

    }
    public static void putLastName(Context mContext, String lName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYLASTNAME, lName);
        editor.commit();
    }
    public static final String getMobile(Context mContext)
    {
        Log.i(TAG, "getMobile: "+mContext);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefMobile = sharedPreferences.getString(KEYMOBILENO,"");
        return prefMobile;

    }
    public static void putMobile(Context mContext, String mobile) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_REFERAL, mobile);
        editor.commit();
    }
    public static final String getGender(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefGender = sharedPreferences.getString(KEYGENDER,"");
        return prefGender;

    }
    public static void putGender(Context mContext, String gender) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYGENDER, gender);
        editor.commit();
    }






//Business Profile Activity

    public static final String getBusinessName(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefName = sharedPreferences.getString(KEYBUSINESSNAME,"");
        return prefName;

    }
    public static void putBusinessName(Context mContext,String businessname) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_REFERAL, businessname);
        editor.commit();
    }

    public static final String getBusinessCategory(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefcategory = sharedPreferences.getString(KEYBUSINESSCATEGORY,"");
        return prefcategory;

    }
    public static void putBusinessCategory(Context mContext,String category) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYBUSINESSCATEGORY, category);
        editor.commit();
    }

    public static final String getBusinessSubCategory(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String presubcategory = sharedPreferences.getString(KEYBUSINESSCATEGORY,"");
        return presubcategory;

    }
    public static void putBusinessSubCategory(Context mContext,String subcategory) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYBUSINESSSUBCATEGORY, subcategory);
        editor.commit();
    }
    public static final String getBusinessSubType(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String presubcategory = sharedPreferences.getString(KEYBUSINESSSUBTYPE,"");
        return presubcategory;

    }
    public static void putBusinessSubCategoryType(Context mContext,String subtype) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_REFERAL, subtype);
        editor.commit();
    }







    //Busisness Location Activity

    public static final String getBusinessLocation(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefBusinessAddress = sharedPreferences.getString(KEYBUSINESSADDRESS,"");
        return prefBusinessAddress;

    }
    public static void putBusinessLocation(Context mContext,String location) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYBUSINESSADDRESS, location);
        editor.commit();
    }

    public static final String getBusinessOptionalLocation(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefOptional = sharedPreferences.getString(KEYBUSINESSOPTIONALLOCATION,"");
        return prefOptional;

    }
    public static void putBusinessOptinalLocation(Context mContext,String optional) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYBUSINESSOPTIONALLOCATION, optional);
        editor.commit();
    }

    public static final String getBusinessPersonName(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefPersonName = sharedPreferences.getString(KEYBUSINESSPERSONNAME,"");
        return prefPersonName;

    }
    public static void putBusinessPersonName(Context mContext,String personName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYBUSINESSPERSONNAME, personName);
        editor.commit();
    }

    public static final String getBusinessPersonContact(Context mContext)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        String prefPersonContact = sharedPreferences.getString(KEYBUSINESSPERSONCONTACT,"");
        return prefPersonContact;

    }
    public static void putBusinessPersonContact(Context mContext,String contact) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYBUSINESSPERSONCONTACT, contact);
        editor.commit();
    }



}

