package com.geewhizstuff.testandroid;

import android.database.Cursor;
import android.database.sqlite.*;
import android.content.Context;
import android.widget.TextView;


/**
 * Created by sapanbhatia on 10/15/15.
 */

class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE table qna(id INTEGER PRIMARY KEY AUTOINCREMENT, date DATE DEFAULT CURRENT_TIMESTAMP, question VARCHAR, answer VARCHAR, count INT, played INT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db,int a, int b) {
    }

    public void onDelete(SQLiteDatabase db) {
    }
}

public class DBAccess {
    private DBHelper mDBHelper;
    private int lastQ = 0;

    public DBAccess(Context context) {
        super();
        mDBHelper = new DBHelper(context);
    }

    public void ping () {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        // Creates the database if it's not there
    }

    public void addQuestionToDB(String question, String answer, String count) {

    }

    public String getNewQuestion(){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        String[] projection = {"question"};
        Cursor c = db.query(
                "qna",  // The table to query
                projection,
                null,
                null,
                "question",
                null,
                "id",
                String.valueOf(lastQ).concat(",1")
        );

        c.moveToFirst();
        if(c.isAfterLast()) //empty result
            return "";
        String result = c.getString(0);
        return result;

    }
    public String[] getNewAnswers(String question){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        String[] projection = {"answer"};
        Cursor c = db.query(
                "qna",  // The table to query
                projection,                               // The columns to return
                "question=?",                                // The columns for the WHERE clause
                new String[]{ question},                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                "id"
        );

        String[] result = new String[]{"", "", "", "", "", ""};
        c.moveToFirst();
        if(c.isAfterLast()) //empty result
            return new String[] {};
        int i = 0;
        while(!c.isAfterLast()) {
            result[i] = c.getString(0);
            i++;
            c.moveToNext();
        }
        return result;
    }

}
