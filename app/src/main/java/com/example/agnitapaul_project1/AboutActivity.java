package com.example.agnitapaul_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {

    private ImageView backHome;
    private ImageView backPrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        backHome=findViewById(R.id.BackHome);
        backPrev = findViewById(R.id.BackToMain);

        backPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}