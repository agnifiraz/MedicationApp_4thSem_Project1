package com.example.agnitapaul_project1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final  RecyclerViewInterface recyclerViewInterface;

    public ArrayList<Medication> medicationArrayList;
    Context context;

    public MyAdapter(Context context, ArrayList<Medication> values, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.medicationArrayList = values;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medicationNameValue_;
        public TextView valueTime_;
        public TextView dayValue_;
        public TextView description_;
        ConstraintLayout llRow;
        Button viewDetailsButton;

        public ViewHolder(View v, RecyclerViewInterface recyclerViewInterface) {
            super(v);

            medicationNameValue_ = v.findViewById(R.id.medicationNameValue);
            valueTime_ = v.findViewById(R.id.valueTime);
            dayValue_ = v.findViewById(R.id.dayValue);
            description_=v.findViewById(R.id.descriptionValue);
            llRow=v.findViewById(R.id.LlRow);

            viewDetailsButton= v.findViewById(R.id.ViewSpecificData);
            viewDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }

    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewItems = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(viewItems, recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        holder.medicationNameValue_.setText(medicationArrayList.get(position).getMedicationName());
        holder.valueTime_.setText(medicationArrayList.get(position).getTime_());
        holder.dayValue_.setText(medicationArrayList.get(position).getDay_());
        holder.description_.setText(medicationArrayList.get(position).getDescription_());
        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_add_update);

                EditText editName= dialog.findViewById(R.id.editTextMedicineName_);
                EditText editTime = dialog.findViewById(R.id.editTextTime_);
                EditText editDay = dialog.findViewById(R.id.editTextDay_);
                EditText editDescription = dialog.findViewById(R.id.editTextDescription_);

                Button updateButton_ =  dialog.findViewById(R.id.button_);

                updateButton_.setText("Update");

                editName.setText((medicationArrayList.get(position)).getMedicationName());
                editTime.setText((medicationArrayList.get(position)).getTime_());
                editDay.setText((medicationArrayList.get(position)).getDay_());
                editDescription.setText((medicationArrayList.get(position).getDescription_()));

                updateButton_.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String name = editName.getText().toString();
                        String time = editTime.getText().toString();
                        String day = editDay.getText().toString();
                        String description = editDescription.getText().toString();

                        medicationArrayList.set(position, new Medication(name, time, day, description));

                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Medicine list")
                        .setMessage("Are you sure want to delete?")
                        .setIcon(R.drawable.ic_baseline_delete_24).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                medicationArrayList.remove(position);
                                notifyItemRemoved(position);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicationArrayList.size();
    }


}
