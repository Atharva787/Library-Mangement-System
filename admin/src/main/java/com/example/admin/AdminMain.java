package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.database.ManageUsers;
import com.example.database.Profile;
import com.example.database.ManageBooks;

public class AdminMain extends AppCompatActivity {

    int adminid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        adminid = getIntent().getIntExtra("admin_id",0);
    }

    public void manageBooks(View v)
    {
        Intent i = new Intent();
        i.setClass(this, ManageBooks.class);
        i.putExtra("admin_id",adminid);
        startActivity(i);
    }

    public void manageUsers(View v)
    {
        Intent i = new Intent();
        i.setClass(this, ManageUsers.class);
        i.putExtra("admin_id",adminid);
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
        i.putExtra("admin_id",adminid);
        i.putExtra("account","admin");
        startActivity(i);
    }
}
