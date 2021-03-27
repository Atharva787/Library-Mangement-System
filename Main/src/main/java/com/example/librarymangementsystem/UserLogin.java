package com.example.librarymangementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.Database;
import com.example.user.UserMain;

public class UserLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }

    public void reg(View v)
    {
        Intent i = new Intent();
        i.setClass(this,RegisterUser.class);
        startActivity(i);
    }

    public void login(View v)
    {
        EditText uname = (EditText) findViewById(R.id.uname);
        EditText pass = (EditText) findViewById(R.id.pass);

        int flag = 0;
        if (uname.length() == 0) {
            flag = 1;
            uname.setError("Please Enter Name");
        }
        if (pass.length() == 0) {
            flag = 1;
            pass.setError("Please Enter Password");
        }
        if(flag == 1)
            return;

        Database db = new Database(this);
        int id = db.findUser(uname.getText().toString().trim(),pass.getText().toString().trim());
        if(id != -1)
        {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

            Intent i = new Intent();
            i.setClass(this, UserMain.class);
            i.putExtra("user_id",id);
            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Login Failed!!\n Incorrect Username or Password.",Toast.LENGTH_SHORT).show();
        }
    }
}
