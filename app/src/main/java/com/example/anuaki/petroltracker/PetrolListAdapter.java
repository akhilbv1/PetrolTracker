package com.example.anuaki.petroltracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
  Created by anuaki on 3/25/2018.
 */

public class PetrolListAdapter extends RecyclerView.Adapter<PetrolListAdapter.MyViewHolder> {
     List<PetrolTrackerFirebaseTable> mlist;

     PetrolListAdapter(List<PetrolTrackerFirebaseTable> list){
         this.mlist = list;
     }
    @Override
    public PetrolListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petrol_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PetrolListAdapter.MyViewHolder holder, int position) {
       holder.updateUi(position);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends ViewHolder {
         TextView petrol_price,petrol_quantity,petrol_isReserve,petrol_distance;
        public MyViewHolder(View itemView) {
            super(itemView);
            petrol_price = itemView.findViewById(R.id.Text_price);
            petrol_quantity = itemView.findViewById(R.id.Text_quantity);
            petrol_distance = itemView.findViewById(R.id.Text_distance);
            petrol_isReserve = itemView.findViewById(R.id.Text_isReserve);
        }

        private void updateUi(int position){
            petrol_price.setText("Price:-"+mlist.get(position).getPetrol_price()+"/-");
            petrol_quantity.setText("Quantity:-"+mlist.get(position).getPetrol_quantity()+"Litres");
            petrol_isReserve.setText("IsReserve:-"+mlist.get(position).getPetrol_isreserve());
            petrol_distance.setText("Distance:-"+mlist.get(position).getPetrol_kilometres()+"Kms");
        }
     }
}
