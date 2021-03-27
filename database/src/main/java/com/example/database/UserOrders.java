package com.example.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserOrders extends AppCompatActivity
{

    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    Database db;
    ArrayList<String> book_id,book_title, book_author, book_pages,issued_book;
    AdapterUser adapterUser;

    int userid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        userid = getIntent().getIntExtra("user_id",0);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);


        db = new Database(this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        issued_book = new ArrayList<>();

        storeDataInArrays();

        adapterUser = new AdapterUser(this, this, book_id, book_title, book_author,
                book_pages,issued_book,userid);
        recyclerView.setAdapter(adapterUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else{
            while (cursor.moveToNext()) {
                if( cursor.getInt(4) == userid)
                {
                    book_id.add(cursor.getString(0));
                    book_title.add(cursor.getString(1));
                    book_author.add(cursor.getString(2));
                    book_pages.add(cursor.getString(3));
                    issued_book.add(cursor.getString(4));
                }
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.profile)
            viewprofile();
        return super.onOptionsItemSelected(item);
    }

    public void viewprofile()
    {
        Intent i = new Intent();
        i.setClass(this,Profile.class);
        i.putExtra("user_id",userid);
        i.putExtra("account","user");
        startActivity(i);
    }
}