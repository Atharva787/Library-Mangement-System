package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBooks extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        title_input = findViewById(R.id.uname);
        author_input = findViewById(R.id.email);
        pages_input = findViewById(R.id.pass);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(AddBooks.this);
                db.addBook(title_input.getText().toString().trim(),author_input.getText().toString().trim(),
                        Integer.parseInt(pages_input.getText().toString().trim()));
                finish();
            }
        });
    }
}
