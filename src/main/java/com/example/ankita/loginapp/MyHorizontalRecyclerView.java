package com.example.ankita.loginapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Ankita on 12/14/2016.
 */

class MyHorizontalRecyclerViewRecyclerView extends RecyclerView.Adapter<MyHorizontalRecyclerViewRecyclerView.MyViewHolder>
        {
            private static String LOG_TAG = "MyRecyclerViewAdapter";
            //private ArrayList<DataObject2> mDataset;
            private static MyClickListener myClickListener;
            private ArrayList<DataObject2> horizontalList;




            public static class MyViewHolder extends RecyclerView.ViewHolder implements View
                    .OnClickListener  {
                public Button btnView;


                public MyViewHolder(View view) {
                    super(view);
                    btnView = (Button) view.findViewById(R.id.btnArraylist);
                    btnView.setOnClickListener(this);
                }

                @Override
                public void onClick(View view) {
                    myClickListener.onItemClick(getPosition(), view);
                }
            }

            public MyHorizontalRecyclerViewRecyclerView(ArrayList<DataObject2> horizontalList, MyClickListener myclickListner)
            {
                this.horizontalList = horizontalList;
                this.myClickListener=myclickListner;
            }
            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recyclerviewmap, parent, false);

                MyViewHolder dataObjectHolder = new MyViewHolder(itemView);
                return dataObjectHolder;
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {

                DataObject2 objdata=horizontalList.get(position);

                holder.btnView.setText(horizontalList.get(position)+"Accountant");
                holder.btnView.setText(horizontalList.get(position)+"Clerk");
                holder.btnView.setText(horizontalList.get(position)+"Lecturer");
                holder.btnView.setText(horizontalList.get(position)+"Assistant");
                holder.btnView.setText(horizontalList.get(position)+"Artisan");
                holder.btnView.setText(horizontalList.get(position)+"Cashier");
                holder.btnView.setText(horizontalList.get(position)+"Developer");
                holder.btnView.setText(horizontalList.get(position)+"worker");
                holder.btnView.setText(horizontalList.get(position)+"Electrician");
                holder.btnView.setText(horizontalList.get(position)+"Plumber");

            }

            public void addItem(DataObject2 dataObj, int index) {
                horizontalList.add(dataObj);
                notifyItemInserted(index);
            }

            public void deleteItem(int index) {
                horizontalList.remove(index);
                notifyItemRemoved(index);
            }





    @Override
    public int getItemCount() {
        return horizontalList.size();
    }


}
