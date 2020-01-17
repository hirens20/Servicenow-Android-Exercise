package com.servicenow.exercise_java.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.exercise.R;
import com.servicenow.exercise_java.recyclerView.GameAdapter;
import com.servicenow.exercise_java.recyclerView.RecyclerViewTouchListener;
import com.servicenow.resources.Game;
import com.servicenow.resources.NESGames;

public class GameListActivity extends AppCompatActivity {
    private String TAG = GameListActivity.class.getSimpleName();
    public static final Game[] nesGames = NESGames.INSTANCE.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_list);
        GameAdapter adapter           = new GameAdapter(this, R.layout.game_list_item, nesGames);
        RecyclerView gameListingsView = findViewById(R.id.game_list_recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        gameListingsView.setLayoutManager(layoutManager);
        gameListingsView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

        gameListingsView.setAdapter(adapter);

        gameListingsView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), gameListingsView, new RecyclerViewTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "onClick: Game " + nesGames[position].getName() + " Clicked. Position - " + position);
                Intent intent = new Intent(GameListActivity.this, GameDetailActivity.class);
                intent.putExtra("Position", position);
                GameListActivity.this.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

}
