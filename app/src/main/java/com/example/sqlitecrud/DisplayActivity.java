package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private ListView lstword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lstword = findViewById(R.id.listview);
        LoadWord();
    }

    private void LoadWord() {
        final DBHelper dbHelper = new DBHelper(this);
        final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = dbHelper.GetAllWords(sqLiteDatabase);

        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 0; i<wordList.size(); i++){
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>(hashMap.keySet()));

        lstword.setAdapter(stringArrayAdapter);
    }
}