package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUser extends AppCompatActivity {

    EditText uname,email,pass;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        uname = findViewById(R.id.uname);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(AddUser.this);
                db.addUser(uname.getText().toString().trim(),email.getText().toString().trim(),pass.getText().toString().trim());
                finish();
            }
        });
    }
}
