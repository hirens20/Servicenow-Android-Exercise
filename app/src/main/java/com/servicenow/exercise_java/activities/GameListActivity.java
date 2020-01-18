package com.servicenow.exercise_java.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.recyclerView.GameAdapter;
import com.servicenow.exercise_java.recyclerView.RecyclerViewTouchListener;
import com.servicenow.resources.Game;
import com.servicenow.resources.NESGames;

import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity {
    private String TAG = GameListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        ArrayList<Game> gameArrayList = extras.getParcelableArrayList("Games");

        Game[] nesGames;
        if (gameArrayList == null || gameArrayList.isEmpty()) {
            Log.d(TAG, "onCreate: Got games stored locally");
            nesGames = NESGames.INSTANCE.getList();
        } else {
            Log.d(TAG, "onCreate: Got games from server");
            nesGames = gameArrayList.toArray(new Game[gameArrayList.size()]);
        }

        setContentView(R.layout.game_list);
        GameAdapter adapter           = new GameAdapter(R.layout.game_list_item, nesGames);
        RecyclerView gameListingsView = findViewById(R.id.game_list_recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        gameListingsView.setLayoutManager(layoutManager);
        gameListingsView.setAdapter(adapter);
        gameListingsView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), gameListingsView, new RecyclerViewTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "onClick: Game " + nesGames[position].getName() + " Clicked. Position - " + position);
                Intent intent = new Intent(GameListActivity.this, GameDetailActivity.class);
                intent.putExtra("Game", nesGames[position]);
                GameListActivity.this.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

}
