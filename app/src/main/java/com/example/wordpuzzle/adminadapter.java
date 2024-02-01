package com.example.wordpuzzle;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class adminadapter extends FirebaseRecyclerAdapter<adminholder,adminadapter.MyViewHolder> {
    public adminadapter(@NonNull FirebaseRecyclerOptions<adminholder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adminadapter.MyViewHolder holder, int position, @NonNull adminholder model) {

        holder.name.setText(model.getname());
        holder.level.setText(model.getlevel());
        holder.score.setText(String.valueOf(model.getscore()));
//        holder.score.setText(model.getscore());
        holder.catg.setText(model.getcatg());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Delete Item");
                builder.setMessage("Are you Sure you want to delete");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("users")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }


    @NonNull
    @Override
    public adminadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowadmin,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,level,score,catg;
        ImageView delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.uname);
            level=(TextView)itemView.findViewById(R.id.ulevel);
            score=(TextView)itemView.findViewById(R.id.uscor);
            catg=(TextView)itemView.findViewById(R.id.ucat);
//            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView) itemView.findViewById(R.id.deleteicon);
//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if(position!=RecyclerView.NO_POSITION){
//                        String userId = getRef(position).getKey();
//                        Intent intent = new Intent(itemView.getContext(), UpdateActivityScor.class);
//                        intent.putExtra("userId",userId);
//                        itemView.getContext().startActivity(intent);
//                    }
//                }
//            });
        }
    }
}
