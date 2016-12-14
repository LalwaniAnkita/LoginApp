package com.example.ankita.loginapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.ankita.loginapp.NewJobPostActivity.REQUEST_CODE_ACTIVITY_JOB;

public class JobPostActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ACTIVITY_TWO = 100;
    Button btn_SubmitJobPost;
    private Context mCotext;

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String TAG = "RecyclerViewActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        getSupportActionBar().setTitle("Job Post");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);


        mCotext = getApplicationContext();
        btn_SubmitJobPost = (Button) findViewById(R.id.btnSubmitjobpost);
        btn_SubmitJobPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(JobPostActivity.this,"submit",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(JobPostActivity.this, NewJobPostActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ACTIVITY_TWO);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: ");
        if (resultCode == RESULT_OK) {
            Log.i(TAG, "onActivityResult: resultok");
            if (requestCode == REQUEST_CODE_ACTIVITY_JOB) {
                Log.i(TAG, "onActivityResult: requestcode equals");
                if (data != null) {
                    Log.i(TAG, "onActivityResult: data not null ");

                    Intent i = new Intent();
                    String location = data.getExtras().getString("LOCATION");
                    String post = data.getExtras().getString("POST");
                    String experience = data.getExtras().getString("EXPERIENCE");
                    String time = data.getExtras().getString("TIME");
                    String date = data.getExtras().getString("DATE");
                    String minwage = data.getExtras().getString("MINWAGE");
                    String maxwage = data.getExtras().getString("MAXWAGE");

                    Log.i(TAG, "onActivityResult: location");

                    DataObject ObjDataObject = new DataObject(location, post,experience,time,date,minwage,maxwage);
                    Log.i(TAG, "onActivityResult: dataobj");
                    mAdapter.addItem(ObjDataObject, mAdapter.getItemCount());
                    Log.i(TAG, "onActivityResult: additem");
                }
                else
                {
                    Log.i(TAG, "onActivityResult: data null");
                }

            }


        }
    }

    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(TAG, "onItemClick:" + position);
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 0; index++) {
            DataObject obj = new DataObject(""+index,""+"months"+index+"",""+index,""+index,""+index,"$"+index,""+index);
            results.add(index, obj);
        }
        return results;
    }
}
