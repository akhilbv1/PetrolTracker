package com.example.anuaki.petroltracker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
  Created by anuaki on 3/25/2018.
 */

public class PetrolListActivity extends AppCompatActivity {
  List<PetrolTrackerFirebaseTable> mlist = new ArrayList<>();
  RecyclerView recyclerView;
  PetrolListAdapter adapter;
  ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrolist);
        recyclerView = findViewById(R.id.recylerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        dialog = new ProgressDialog(this);
        dialog.setMessage("fetching petrol details");
        getFromFirebase();
    }



    private void getFromFirebase(){
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("PetrolDetails");
         dialog.show();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("data",""+dataSnapshot.getValue());
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren() ;
                for(DataSnapshot dataSnapshotss:iterable){
                    Log.i("data",""+dataSnapshotss.getValue());
                    PetrolTrackerFirebaseTable petrolDetails = dataSnapshotss.getValue(PetrolTrackerFirebaseTable.class);
                    mlist.add(petrolDetails);
                    Log.i("data",""+dataSnapshotss.getValue());

                }
                dialog.cancel();
                adapter = new PetrolListAdapter(mlist);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.cancel();
               Log.i("error",""+databaseError.getMessage());
                Toast.makeText(PetrolListActivity.this,"database error",Toast.LENGTH_LONG).show();
            }
        });
        dialog.cancel();
    }


}
