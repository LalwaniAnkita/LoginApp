package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,TimePickerDialog.OnTimeSetListener,View.OnClickListener {
    public static final int REQUEST_CODE_ACTIVITY_TWO =100;

    MapFragment mMapFragment;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation,mCurrentLocation;
    double mLatitude;
    double mLongitude;
    private String TAG = MapActivity.class.getSimpleName();
    private GoogleMap mMap;
    private LocationRequest mLocationRequest;
    Context mContext;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private RadioGroup radioGroup;
    TextView textStartTime, textTimeTo, textEndTime;
    Button btn_Mapsubmit;

    Marker mMarker;
    ArrayList<Marker> markers = new ArrayList<Marker>();
    boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().setTitle("Search for Employee");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext=getApplicationContext();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        textStartTime = (TextView) findViewById(R.id.textStartTime);
        textStartTime.setOnClickListener(new View.OnClickListener() {
            @java.lang.Override
            public void onClick(View view) {
                flag = true;

                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        (TimePickerDialog.OnTimeSetListener) MapActivity.this,
                        now.get(Calendar.DAY_OF_MONTH),
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );

                tpd.show(getFragmentManager(), "Time Picker");

            }
        });

        textTimeTo = (TextView) findViewById(R.id.textTo);

        textEndTime = (TextView) findViewById(R.id.textEndTime);
        textEndTime.setOnClickListener(new View.OnClickListener() {
            @java.lang.Override
            public void onClick(View view) {

                flag = false;
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        (TimePickerDialog.OnTimeSetListener) MapActivity.this,
                        now.get(Calendar.DAY_OF_MONTH),
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );

                tpd.show(getFragmentManager(), "Time Picker");
            }
        });


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @java.lang.Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)

                {
                    case R.id.radioSunday:
                        Toast.makeText(MapActivity.this, "Sunday", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;


                }

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,R.string.drawer_open,R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Log.i(TAG, "onNavigationItemSelected: ");
                Intent intent;
                switch (item.getItemId())
                {
                    case R.id.Add_more_biz_locations:
                        intent = new Intent(MapActivity.this,BusinessLocationActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
                    break;

                    case R.id.Personal_Details:
                        intent = new Intent(MapActivity.this,InformationActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
                        break;
                    case R.id.Sign_Out:
                        intent= new Intent(MapActivity.this,MainActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
                        default:
                        break;


                }

                return false;

            }
        });


    }



    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Log.i(TAG, "onConnected: started");

        startLocationUpdates();

    }

    @Override
    public void onConnectionSuspended(int i) {

        Log.i(TAG, "onConnectionSuspended: ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.i(TAG, "onConnectionFailed: ");
    }

    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }
    public void setMapZoom(Marker Marker) {
        if (mMap != null) {
            LatLngBounds.Builder b = new LatLngBounds.Builder();
            for (Marker m : markers) {
                b.include(m.getPosition());
            }
            LatLngBounds bounds = b.build();
//Change the padding as per needed
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 25, 25, 5);
            mMap.animateCamera(cu);
        }
    }


    @Override
    public void onLocationChanged(Location location) {

        /*if (location != null) {
            Log.i(TAG, "onConnected: last location not null");
            if (mMap != null) {
                Log.i(TAG, "onConnected: map not null");
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title("Marker"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(location.getLatitude(), location.getLongitude())), 30));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
            }
            Log.i(TAG, "onConnected Location" + location.getLatitude() + ":" + location.getLongitude());
        } else {
            Log.i(TAG, "Location not fount");
        }
*/

        mCurrentLocation = location;

        if (mCurrentLocation != null) {
            LatLng latlng = new LatLng(mLatitude, mLongitude);
            Log.i(TAG, "onLocationChanged: location map ! null");
            mMarker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()))
                    .title("Marker"));
            markers.add(mMarker);
            setMapZoom(mMarker);

            Log.i(TAG, "onLocationChanged: location marker add");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()), 18));
            // mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.getCameraPosition().zoom));
            Log.i(TAG, "onLocationChanged: location complete");
        }
    }

    @java.lang.Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        if (flag == true) {
            Calendar calender = Calendar.getInstance();
            calender.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calender.set(Calendar.MINUTE, minute);
            SimpleDateFormat format1 = new SimpleDateFormat("hh:mm a");

            String formatted = format1.format(calender.getTime());
            textStartTime.setText(formatted);
        } else {
            Calendar calender = Calendar.getInstance();
            calender.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calender.set(Calendar.MINUTE, minute);
            SimpleDateFormat format1 = new SimpleDateFormat("hh:mm a");

            String formatted = format1.format(calender.getTime());
            textEndTime.setText(formatted);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
