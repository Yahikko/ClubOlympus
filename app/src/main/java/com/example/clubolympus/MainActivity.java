package com.example.clubolympus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clubolympus.data.MemberCursorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.clubolympus.data.ClubOlympusContract.*;

public class MainActivity extends AppCompatActivity {

    private ListView lvMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMembers = findViewById(R.id.dataListView);

        FloatingActionButton floatingActionButton =
                findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayData() {

        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_FIRST_NAME,
                MemberEntry.COLUMN_LAST_NAME,
                MemberEntry.COLUMN_GENDER,
                MemberEntry.COLUMN_SPORT};

        Cursor cursor = getContentResolver().query(
                MemberEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

        MemberCursorAdapter memberCursorAdapter = new MemberCursorAdapter(this, cursor);
        lvMembers.setAdapter(memberCursorAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayData();
    }
}