package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    int id = 0;
    String account = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        EditText username = (EditText) findViewById(R.id.uname);
        EditText email = (EditText) findViewById(R.id.email);
        TextView tv = (TextView) findViewById(R.id.tv);

        Database db = new Database(this);
        String[] details;

        account = getIntent().getStringExtra("account");
        if(account.equals("user")) {
            id = getIntent().getIntExtra("user_id", 0);
            details = db.findUser(id);
            tv.setText("USER");
        }
        else {
            id = getIntent().getIntExtra("admin_id", 0);
            details = db.findAdmin(id);
            tv.setText("ADMIN");
        }

        username.setText(details[0]);
        email.setText(details[1]);
    }

    public void update(View v)
    {
        EditText username = (EditText) findViewById(R.id.uname);
        EditText email = (EditText) findViewById(R.id.email);

        Database db = new Database(this);
        if(account.equals("user")) {
            db.updateUser(id, username.getText().toString().trim(), email.getText().toString().trim());
        }
        else{
            db.updateAdmin(id, username.getText().toString().trim(), email.getText().toString().trim());
        }
    }

}
