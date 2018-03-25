package com.example.anuaki.petroltracker;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
  Created by anuaki on 3/25/2018.
 */

public class AddPetrolDataActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText etPrice,etQuantity,etReserve,etKilometres;
    Button addData;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpetrol);
        addData = findViewById(R.id.Btn_add);
        etPrice = findViewById(R.id.ET_price);
        etQuantity = findViewById(R.id.ET_quantity);
        etReserve = findViewById(R.id.ET_reserve);
        etKilometres = findViewById(R.id.ET_kilometers);
        dialog = new ProgressDialog(this);
        dialog.setMessage("fetching your Petrol details");
        addData.setOnClickListener(this);

    }


    private void insertIntoFirebase(){

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("PetrolDetails");

        PetrolTrackerFirebaseTable petrolDetails = new PetrolTrackerFirebaseTable();
        petrolDetails.setPetrol_price(etPrice.getText().toString().trim());
        petrolDetails.setPetrol_quantity(etQuantity.getText().toString().trim());
        petrolDetails.setPetrol_kilometres(etKilometres.getText().toString().trim());
        petrolDetails.setPetrol_isreserve(etReserve.getText().toString().trim());

        final String dataKey = mRef.push().getKey();
        mRef.child(dataKey).setValue(petrolDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    dialog.cancel();
                    Toast.makeText(AddPetrolDataActivity.this,"data addded success",Toast.LENGTH_LONG).show();
                }
                else
                {
                    dialog.cancel();
                    Log.i("failed",""+task.getException());
                    Toast.makeText(AddPetrolDataActivity.this,"data addded failed",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        dialog.show();
        insertIntoFirebase();
    }
}
