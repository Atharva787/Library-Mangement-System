package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.database.Database;
import com.example.database.Profile;
import com.example.database.UserOrders;
import com.example.database.ViewBooks;

public class UserMain extends AppCompatActivity
{
    int userid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        userid = getIntent().getIntExtra("user_id",0);
    }

    public void browseBooks(View v)
    {
        Intent i = new Intent();
        i.setClass(this, ViewBooks.class);
        i.putExtra("user_id",userid);
        startActivity(i);
    }

    public void orders(View v)
    {
        Intent i = new Intent();
        i.setClass(this, UserOrders.class);
        i.putExtra("user_id",userid);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(com.example.database.R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == com.example.database.R.id.profile)
            viewprofile();
        return super.onOptionsItemSelected(item);
    }

    public void viewprofile()
    {
        Intent i = new Intent();
        i.setClass(this, Profile.class);
        i.putExtra("user_id",userid);
        i.putExtra("account","user");
        startActivity(i);
    }
}