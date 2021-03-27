package com.example.librarymangementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.database.Database;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("pref", MODE_PRIVATE);
        boolean fisrt = sp.getBoolean("first", true);
        if (fisrt) {
            Database db = new Database(this);
            db.deleteall();
            db.addBook("Wings of Fire", "APJ Abdul Kalam", 180);
            db.addBook("The Secret", "Rhonda Byrne", 198);
            db.addBook("Autobiography of a Yogi", "Paramhansa Yogananda", 591);
            db.addBook("The Story of My Experiments with Truth", "Mohandas K. Gandhi", 520);
            db.addBook("Gitanjali", "Rabindranath Tagore",104);
            Toast.makeText(getApplicationContext(),"Welcome!!",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("first",false);
            editor.apply();
        }
    }
    public void adminlog(View v)
    {
        Intent i = new Intent();
        i.setClass(this,AdminLogin.class);
        startActivity(i);
    }

    public void userlog(View v)
    {
        Intent i = new Intent();
        i.setClass(this,UserLogin.class);
        startActivity(i);
    }
}
