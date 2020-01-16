package com.servicenow.exercise_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.servicenow.exercise.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.launchGameList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameListActivity();
            }
        });
    }

    private void startGameListActivity() {
        Intent myIntent = new Intent(MainActivity.this, GameListActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}