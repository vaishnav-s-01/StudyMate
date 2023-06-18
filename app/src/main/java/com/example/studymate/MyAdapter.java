package com.example.studymate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name_id,email_id,teacher_id;

    private ButtonClickListener buttonClickListener;


    public MyAdapter(Context context, ArrayList<String> name_id, ArrayList<String> email_id, ArrayList<String> teacher_id, ButtonClickListener listener) {
        this.context = context;
        this.name_id = name_id;
        this.email_id = email_id;
        this.teacher_id = teacher_id;
        this.buttonClickListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);

        return new MyViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));
        holder.teacher_id.setText(String.valueOf(teacher_id.get(position)));

    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id,email_id,teacher_id;
        FloatingActionButton approve,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.TeacherName);
            email_id = itemView.findViewById(R.id.TeacherMail);
            teacher_id = itemView.findViewById(R.id.TeacherID);
            approve = itemView.findViewById(R.id.approve);
            delete = itemView.findViewById(R.id.delete);

            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttonClickListener != null) {
                        int position = getAdapterPosition();
                        buttonClickListener.onApproveClick(position);
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttonClickListener != null) {
                        int position = getAdapterPosition();
                        buttonClickListener.onDeleteClick(position);
                    }
                }
            });


        }
    }

    public interface ButtonClickListener {
        void onApproveClick(int position);
        void onDeleteClick(int position);

    }

}
