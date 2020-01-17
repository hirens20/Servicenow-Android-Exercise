package com.servicenow.exercise_java;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.servicenow.exercise.R;
import com.servicenow.resources.Game;
import com.servicenow.resources.NESGames;

public class GameDetailActivity extends AppCompatActivity {
    private String TAG = GameDetailActivity.class.getSimpleName();
    public static final Game[] games = NESGames.INSTANCE.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        Bundle extras = getIntent().getExtras();
        int position = extras.getInt("Position");
        Game game = games[position];

        Log.d(TAG, "Got intent with position and game - " + game.getLongDescription());


        ImageView gameCover = findViewById(R.id.gameCoverImage);
        gameCover.setImageResource(Game.Companion.getIconResource(game.getCover()));

        TextView gameDescription = findViewById(R.id.gameDescription);
        gameDescription.setText(game.getLongDescription());
    }
}
