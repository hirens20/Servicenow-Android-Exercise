package com.servicenow.exercise_java.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.servicenow.exercise.R;
import com.servicenow.resources.Game;

public class GameDetailActivity extends AppCompatActivity {
    private String TAG = GameDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        Bundle extras = getIntent().getExtras();
        Game game = extras.getParcelable("Game");

        Log.d(TAG, "Opening detail for game " + game.getName());

        String gameName = getResources().getString(R.string.game_detail, game.getName());
        Toolbar toolbar = findViewById(R.id.gameDetailCustomToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(gameName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView gameCover = findViewById(R.id.gameCoverImage);
        gameCover.setImageResource(Game.Companion.getIconResource(game.getCover()));

        TextView gameDescription = findViewById(R.id.gameDescription);
        gameDescription.setText(game.getLongDescription());
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
