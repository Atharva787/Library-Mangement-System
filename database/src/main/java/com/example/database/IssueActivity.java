package com.example.database;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IssueActivity extends AppCompatActivity {

    TextView title_input, author_input, pages_input;
    Button issue_button,return_button;
    String book_id,title, author, pages;
    int userid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        issue_button = findViewById(R.id.issue_button2);
        return_button = findViewById(R.id.return_button2);
        //First we call this
        getAndSetIntentData();
        userid = getIntent().getIntExtra("user_id",0);
        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        Database db = new Database(this);
        int flag = db.canIssue(Integer.parseInt(book_id),userid);
        if(flag == 0)
            issue_button.setVisibility(View.VISIBLE);
        else
            return_button.setVisibility(View.VISIBLE);
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            book_id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Setting Intent Data
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
            Log.d("stev", title+" "+author+" "+pages);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void issueBook(View view) {
        Database db = new Database(this);
        int flag = db.issueBook(Integer.parseInt(book_id),userid);
        if(flag == 1)
        {
            issue_button.setVisibility(View.INVISIBLE);
            return_button.setVisibility(View.VISIBLE);
        }

    }
    public void returnBook(View view) {
        Database db = new Database(this);
        int flag = db.returnBook(Integer.parseInt(book_id),userid);
        if(flag == 1) {
            return_button.setVisibility(View.INVISIBLE);
            issue_button.setVisibility(View.VISIBLE);
        }
    }
}
