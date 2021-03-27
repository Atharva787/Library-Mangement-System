package com.example.database;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUser extends AppCompatActivity {

    EditText userid,uname,email;
    Button update_button, delete_button;

    String userid_txt,uname_txt,email_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_books);

        userid = findViewById(R.id.title_input2);
        uname = findViewById(R.id.author_input2);
        email = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(uname.getText().toString().trim());
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(UpdateUser.this);
                uname_txt = uname.getText().toString().trim();
                email_txt = email.getText().toString().trim();
                userid_txt = userid.getText().toString().trim();
                db.updateUser(Integer.parseInt(userid_txt), uname_txt, email_txt);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("userid") && getIntent().hasExtra("uname") &&
                getIntent().hasExtra("email")){
            //Getting Data from Intent
            userid_txt = getIntent().getStringExtra("userid");
            uname_txt = getIntent().getStringExtra("uname");
            email_txt = getIntent().getStringExtra("email");

            userid.setText(userid_txt);
            uname.setText(uname_txt);
            email.setText(email_txt);
            Log.d("stev", userid_txt+" "+ uname_txt +" "+ email_txt);
        }else{
            Toast.makeText(this, "No User", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + uname);
        builder.setMessage("Are you sure you want to delete " + uname);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database db = new Database(UpdateUser.this);
                db.deleteUser(Integer.parseInt(userid_txt));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}