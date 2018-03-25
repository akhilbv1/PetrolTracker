package com.example.anuaki.petroltracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   Button petrolList,addPetrolData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        petrolList = findViewById(R.id.Btn_showlist);
        addPetrolData = findViewById(R.id.Btn_addtoList);
        petrolList.setOnClickListener(this);
        addPetrolData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.Btn_showlist)
        {
            Intent intent = new Intent(this,PetrolListActivity.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.Btn_addtoList)
        {
            Intent intent = new Intent(this,AddPetrolDataActivity.class);
            startActivity(intent);
        }
    }
}
