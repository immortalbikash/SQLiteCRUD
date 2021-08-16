package com.example.sqlitecrud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String databaseName = "DictionaryDB";
    private static final int dbVersion = 1;

    private static final String tblWord = "tblWord";
    private static final String WordId = "WordId";
    private static final String Word = "Word";
    private static final String Meaning = "Meaning";

    public DBHelper(@Nullable Context context) {
        super(context, databaseName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + tblWord + "("
                + WordId + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Word + " TEXT," +
                Meaning + " Text "
                + ")";
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean InsertData(String word, String meaning, SQLiteDatabase db) {
        try {

            String query = "insert into tblWord(Word, Meaning) values('" + word + "', '" + meaning +
                    "')";
            db.execSQL(query);
            return true;

        } catch (Exception e) {
            Log.d("Error: 0", e.getLocalizedMessage());
            return false;
        }
    }

    public List<Word> GetAllWords(SQLiteDatabase db) {
        List<Word> dictionarList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tblWord", null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                dictionarList.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
        }
        return dictionarList;
    }

}
