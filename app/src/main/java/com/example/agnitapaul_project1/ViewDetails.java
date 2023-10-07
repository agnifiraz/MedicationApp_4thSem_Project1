package com.example.agnitapaul_project1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_specific_data);

        TextView nameTxt = findViewById(R.id.nameViewS);
        TextView timeTxt = findViewById(R.id.timeViewS);
        TextView dayTxt = findViewById(R.id.dayViewS);
        TextView descriptionTxt = findViewById(R.id.descriptionViewS);

        nameTxt.setText(getIntent().getExtras().getString("name"));
        timeTxt.setText(getIntent().getExtras().getString("time"));
        dayTxt.setText(getIntent().getExtras().getString("day"));
        descriptionTxt.setText(getIntent().getExtras().getString("des"));



    }

}
