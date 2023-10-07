package com.example.agnitapaul_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button view_button;
    private ImageView about_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_button= findViewById(R.id.goToRecycle);
        about_button= findViewById(R.id.goToAbout);


        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

            }
        });

        view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, RecycleViewPage1.class);
                startActivity(intent);

            }
        });


    }
}