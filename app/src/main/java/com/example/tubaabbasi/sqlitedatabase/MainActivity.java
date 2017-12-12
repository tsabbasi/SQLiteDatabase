package com.example.tubaabbasi.sqlitedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Global variables
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("MyDatabase", MODE_PRIVATE, null);

        db.execSQL("Create table if not exists MyTable (Roll int, Marks int);");

        ((Button) findViewById(R.id.button_get)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowRecords();
            }
        });

        ((Button) findViewById(R.id.button_insert)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertRandomRecord();
            }
        });
    }

    public void InsertRandomRecord() {

        Random r = new Random();

        int roll, marks;

        roll = r.nextInt(40);
        marks = r.nextInt(100);

        db.execSQL("Insert into MyTable values (" + Integer.toString(roll) + "," + Integer.toString(marks) + ") ");

        // Record inserted

    }

    public void ShowRecords() {

        Cursor c;
        int temp;
        String data;
        data = "";

        c = db.rawQuery("Select * from MyTable;", null);

        c.moveToFirst();

        for (int i = 0; c.moveToPosition(i); i++) {
            temp = c.getInt(0); // column index = 0 i.e. roll num
            data += Integer.toString(temp);
            temp = c.getInt(1); // column index = 1 i.e. marks
            data += "   " + Integer.toString(temp) + "\n";
        }

        ((TextView)findViewById(R.id.textview_records)).setText(data);



    }

}
