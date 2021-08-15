package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText word, meaning;
    private Button add_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        word = findViewById(R.id.editText_word);
        meaning = findViewById(R.id.editText_meaning);
        add_word = findViewById(R.id.btnAddWord);

        final DBHelper helper = new DBHelper(this);
        final SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        add_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean id = helper.InsertData(word.getText().toString(), meaning.getText().toString(), sqLiteDatabase);
                if (id) {
                    Toast.makeText(MainActivity.this, "Data added sucessfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}