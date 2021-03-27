package com.example.database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList userid, uname, email;

    UserListAdapter(Activity activity, Context context, ArrayList user_id, ArrayList uname, ArrayList email){
        this.activity = activity;
        this.context = context;
        this.userid = user_id;
        this.uname = uname;
        this.email = email;
    }

    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new UserListAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final UserListAdapter.MyViewHolder holder, final int position) {
            holder.user_id_txt.setText(String.valueOf(userid.get(position)));
            holder.uname_txt.setText(String.valueOf(uname.get(position)));
            holder.email_txt.setText(String.valueOf(email.get(position)));
//            Recyclerview onClickListener
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateUser.class);
                    intent.putExtra("userid", String.valueOf(userid.get(position)));
                    intent.putExtra("uname", String.valueOf(uname.get(position)));
                    intent.putExtra("email", String.valueOf(email.get(position)));
                    activity.startActivityForResult(intent, 1);
                }
            });
    }

    @Override
    public int getItemCount() {
        return userid.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, uname_txt, email_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

                user_id_txt = itemView.findViewById(R.id.book_id_txt);
                uname_txt = itemView.findViewById(R.id.book_title_txt);
                email_txt = itemView.findViewById(R.id.book_author_txt);
                mainLayout = itemView.findViewById(R.id.mainLayout);
                //Animate Recyclerview
                Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
                mainLayout.setAnimation(translate_anim);

        }

    }

}

