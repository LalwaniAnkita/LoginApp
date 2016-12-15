package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,TimePickerDialog.OnTimeSetListener{
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

    private RecyclerView horizontal_recycler_view;
    private MyHorizontalRecyclerViewRecyclerView mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList horizontalList;
    MyClickListener myclickListner;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private RadioGroup radioGroup;
    TextView textStartTime, textTimeTo, textEndTime;
    Button btn_finish;

    Marker mMarker;
    ArrayList<Marker> markers = new ArrayList<Marker>();
    boolean flag;

    public static final String[] JOBCATEGORY={"Accountant","Account Payable Clerk","Account Receivable Clerk","Admin","Assistant","Android Developer","Animator","Any","Art Director","Artisan","Assembler","Assistant Editor","Assistant Manager","Auto Mechanic","Baby Sitting","Barback","Bartender","Billing Clert","Billing Coordinator","Billing Specialist","Billman","Bookkeeper","Brand Manager","Busser","Camera Operator","Caregiver","Carpenter","Cashier","Chef","Chef de Partie","Cleaner","Communication Specialist","Community Manager","Construction Worker","Content Manager","Content Marketing Manager","Cashier","Cook","Cosmetologist","Costume Designer","Creative Designer","Custodian",
            "Customer Service","Data Entry Clerk","Data Entry Operator","Database Administrator","Delivary Driver","Dental Assistant","Developer","Dietary","Digital Account Manager","Digital Editor","Digital media Specialist","Dishwasher","Editor","Electrician","Email Marketing Manager","Engineering","Event Coordinator","Event Manager","Event Planner","Executive assistant","Executive Chef","Executive Recruiter","Executive Secretary","Fashion Editor","Field Sales Reprentative","File Clerk","Flooring","Food Expenditor","Food Runner","Forklift Operator","Front Office Manager","General labor","Graphic Designer","Guest Relative manager","HandyMan","Head Chef","Health Unit Coordinator",
            "Hospice Nurse","Hospitality manager","Host","Hotel Concierge","Hotel Manager","House Sitting","House Keeper","HR","HR Assistant","HR Coordinator","HR Manager","Inside Sales","Interior Deginer","IOS Developer","Janitor","Journalist","Junior Accountant","Junior Analyst","Lecturer","Leasing Consultant","Legal Assistant","Legal Secretary","Line Cook","Litigation Paralegal","Logistices Coordinator","Maid","Maintenance Technician","Maintenance Worker","Makeup Artist","Manager","Managing Editor","Market Research Analyst","Marketing Assistant","Marketing Consultant","Marketing Executive","Marketing Intern","Marketing Manager","Marketing Officer","marketing Specialist",
            "Massage Therapist","Medical Assistant","Network Administrator","New Producer","Nurse","Nursing Supervisor","Occupational Therapist","Office Assistant","Office Admin","Office Clerk","Office Coordinator","Office Manager","Opration Manager","Order Picker","Painter","Party Host/Hostess","Pastry Chef","Payroll Clerk","payroll Specialist","Personal Assistant","Pet Sitting","Pharmacy Technician","Photo Editor","Photographer","Physical Theropist","Plumber","PR Executive","Prep Cook","Prep Person","Production Assistant","Project Coordinator","Promoter Template","Puchasing Agent","Receiving Clerk","Receptionist","Recruiter","Remodeling Homes","Restraurant Server","Retail Cashier",
            "Retail Manager","Safety Officer","Safety Manager","Sales Admin","Sales Advisor","Sales Assisatant","Sales Consultant","Sales Coordinator","Sales Representative","Secretary","Security Guard","Senior Admin Assistant","Shipping Manager","Social Media Manager","Sound Engineering","Sous Chef","Spa Therapist"," Staff Writer","Staffing Coordinator","Team Leader","Telemarketer","telesales Represenatative","Test Job Category","Tester","Truck Driver","Tutor","UI Designer","Video Editor","Visual Merchandiser","Volunteer Coordinator","Wait Staff","Waiter","Warehouse","Warehouse Manager","Warehouse Manager","Warehouse Associate","Warehouse Worker","Web Designer","Web Editor","Worker"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().setTitle("Search for Employee");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext=getApplicationContext();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000000);
        mLocationRequest.setFastestInterval(5000000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        myclickListner= new MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(TAG, "onItemClick: ");
                switch (v.getId())
                {
                    case R.id.btnArraylist:
                    {
                        Toast.makeText(MapActivity.this, "ID" + mAdapter.getItemId(position), Toast.LENGTH_LONG).show();
                    }

                }
            }
        };

        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);

        horizontalList= new ArrayList<String>();
        horizontalList.add("Accountant");
        horizontalList.add("Clerk");
        horizontalList.add("Lecturer");
        horizontalList.add("Assistant");
        horizontalList.add("Artisan");
        horizontalList.add("Cashier");
        horizontalList.add("Developer");
        horizontalList.add("worker");
        horizontalList.add("Electrician");
        horizontalList.add("Plumber");

        horizontal_recycler_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        horizontal_recycler_view.setLayoutManager(mLayoutManager);
        mAdapter = new MyHorizontalRecyclerViewRecyclerView(horizontalList,myclickListner);
        horizontal_recycler_view.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
        horizontal_recycler_view.addItemDecoration(itemDecoration);

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
        btn_finish=(Button)findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MapActivity.this, AvailableAccounts.class);
                    startActivity(intent);

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
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

                    case R.id.Job_Post:
                        intent= new Intent(MapActivity.this,JobPostActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
                        break;

                    case R.id.Setting:
                        intent= new Intent(MapActivity.this,SettingActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
                        break;

                    case R.id.Sign_Out:
                        intent= new Intent(MapActivity.this,MainActivity.class);
                        startActivityForResult(intent,REQUEST_CODE_ACTIVITY_TWO);
                        break;

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

    public MapActivity()
    {

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





}
