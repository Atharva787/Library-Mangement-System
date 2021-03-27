package com.example.librarymangementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.Database;
import com.example.user.UserMain;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    public void reg(View v) {
        int flag = 0;
        AlertDialog.Builder alertmessage = new AlertDialog.Builder(this); // To display error message
        EditText name = (EditText) findViewById(R.id.reg_name);
        EditText email = (EditText) findViewById(R.id.reg_email);
        EditText password = (EditText) findViewById(R.id.reg_pass);
        EditText conf_password = (EditText) findViewById(R.id.reg_conf_pass);

        if (name.length() == 0) {
            flag = 1;
            name.setError("Please Enter Name");
        }
        if (email.length() == 0) {
            flag = 1;
            email.setError("Please Enter E-Mail");
        }
        if (password.length() == 0) {
            flag = 1;
            password.setError("Please Enter Password");
        }
        if (conf_password.length() == 0) {
            flag = 1;
            conf_password.setError("Please Re-Enter Password");
        }
        if (flag == 1)
            return;

        if (!(password.getText().toString().equals(conf_password.getText().toString()))) {
            alertmessage.setMessage("Re-entered password does not match with the password");
            alertmessage.setTitle("Error...");
            alertmessage.setPositiveButton("OK", null);
            alertmessage.setCancelable(true);
            alertmessage.create().show();
            flag = 1;
        }
        if (flag == 1)
            return;


        Database db = new Database(this);
        db.addUser(name.getText().toString().trim(), email.getText().toString().trim(), password.getText().toString().trim());
        Toast.makeText(getApplicationContext(), "Registration Successful\nPlease Login", Toast.LENGTH_SHORT).show();


        Intent i = new Intent();
        i.setClass(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}
