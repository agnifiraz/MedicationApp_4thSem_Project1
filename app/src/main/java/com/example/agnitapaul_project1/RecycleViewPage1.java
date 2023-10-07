package com.example.agnitapaul_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecycleViewPage1 extends AppCompatActivity implements RecyclerViewInterface{
    private ArrayList<Medication> medicationArrayList;
    private RecyclerView recyclerView;
    ImageView BackMain;
    Button resetButton;
    ImageView addMainButton;
    MyAdapter adapter;
    ImageView saveBtn;
    ImageView settingsBtn;
    EditText countMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_page1);

        recyclerView=findViewById(R.id.RecycleViewWork);
        BackMain=findViewById(R.id.BackMain);
        resetButton=findViewById(R.id.ResetButton);
        addMainButton=findViewById(R.id.AddMainButton);
        saveBtn=findViewById(R.id.saveButton);
        settingsBtn = findViewById(R.id.setting_button);
        countMed=findViewById(R.id.countMedication);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(RecycleViewPage1.this, AboutActivity.class);
                startActivity(intent);

            }
        });

        // medicationArrayList = new ArrayList<>();
        loadData();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(getApplicationContext(), "Your medicine list is saved",Toast.LENGTH_LONG).show();
            }
        });
        addMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                countMed.setText(String.valueOf(medicationArrayList.size()+1));

                Dialog dialog = new Dialog(RecycleViewPage1.this);
                dialog.setContentView(R.layout.dialog_add_update);
                EditText editName= dialog.findViewById(R.id.editTextMedicineName_);
                EditText editTime = dialog.findViewById(R.id.editTextTime_);
                EditText editDay = dialog.findViewById(R.id.editTextDay_);
                EditText editDescription = dialog.findViewById(R.id.editTextDescription_);
                Button addButton_ =  dialog.findViewById(R.id.button_);

                addButton_.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String name = editName.getText().toString();
                        String time = editTime.getText().toString();
                        String day = editDay.getText().toString();
                        String description = editDescription.getText().toString();

                        medicationArrayList.add(new Medication(name,time,day,description));

                        adapter.notifyItemInserted(medicationArrayList.size()-1);
                        recyclerView.scrollToPosition(medicationArrayList.size()-1);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicationArrayList.clear();
                adapter.notifyDataSetChanged();
                //finish();

            }
        });

        BackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        setMedicationInfo();
        setAdapter();
    }

    private  void saveData(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(medicationArrayList);
        editor.putString("medicine list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json=sharedPreferences.getString("medicine list", null);
        Type type = new TypeToken<ArrayList<Medication>>(){}.getType();
        medicationArrayList=gson.fromJson(json,type);
        if (medicationArrayList==null){
            medicationArrayList=new ArrayList<>();
        }

    }

    private void setAdapter() {
        adapter= new MyAdapter(this, medicationArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setMedicationInfo() {
         // medicationArrayList.add(new Medication("name2", "time2", "day2"));
            //medicationArrayList.add(new Medication("name", "time", "day"));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(RecycleViewPage1.this, ViewDetails.class);
        intent.putExtra("name", medicationArrayList.get(position).getMedicationName());
        intent.putExtra("time", medicationArrayList.get(position).getTime_());
        intent.putExtra("day", medicationArrayList.get(position).getDay_());
        intent.putExtra("des", medicationArrayList.get(position).getDescription_());
        startActivity(intent);

    }
}