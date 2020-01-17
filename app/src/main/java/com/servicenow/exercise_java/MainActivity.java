package com.servicenow.exercise_java;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.servicenow.exercise.R;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Create Main Activity");
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.launchGameListButton);
        button.setOnClickListener(v -> {
            Log.d(TAG, "Launch list of games button clicked");
            startGameListActivity();
        });
    }

    private void startGameListActivity() {
        Intent myIntent = new Intent(MainActivity.this, GameListActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}