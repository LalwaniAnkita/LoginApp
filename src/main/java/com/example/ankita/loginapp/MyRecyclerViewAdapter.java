package com.example.ankita.loginapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ankita on 11/29/2016.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder>
{

    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;



    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView text_Location,text_job_Post,text_job_Experience,text_job_ExperienceTime,text_JobDate,text_minwage,text_maxwage;
        Button btn_ViewApplied;
        ImageButton recycleview_delete;

        public DataObjectHolder(View itemView) {
            super(itemView);
            text_Location = (TextView) itemView.findViewById(R.id.textLocationJob);
            text_job_Post = (TextView) itemView.findViewById(R.id.text_JobPost);
            text_job_Experience=(TextView)itemView.findViewById(R.id.text_JobExperience);
            text_job_ExperienceTime=(TextView)itemView.findViewById(R.id.text_JobExperienceTime);
            text_JobDate=(TextView)itemView.findViewById(R.id.text_Job_Date);
            text_minwage=(TextView)itemView.findViewById(R.id.text_Job_minwage);
            text_maxwage=(TextView)itemView.findViewById(R.id.text_Job_maxwage);

            btn_ViewApplied=(Button)itemView.findViewById(R.id.btn_JobApplied);
            btn_ViewApplied.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myClickListener.onItemClick(getLayoutPosition(),view);
                }
            });
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
            recycleview_delete=(ImageButton)itemView.findViewById(R.id.imgbtn_delete);
            recycleview_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myClickListener.onItemClick(getLayoutPosition(),view);
                }
            });
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }


    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset,MyClickListener myclickListner)
    {
        mDataset = myDataset;
        this.myClickListener=myclickListner;

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.text_Location.setText(mDataset.get(position).getmText1());
        holder.text_job_Post.setText(mDataset.get(position).getmText2());
        holder.text_job_Experience.setText(mDataset.get(position).getmText3()+" Months");
        holder.text_job_ExperienceTime.setText(mDataset.get(position).getmText4());
        holder.text_JobDate.setText(mDataset.get(position).getmText5());
        holder.text_minwage.setText("$"+mDataset.get(position).getmText6());
        holder.text_maxwage.setText("-"+mDataset.get(position).getmText7());

    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
