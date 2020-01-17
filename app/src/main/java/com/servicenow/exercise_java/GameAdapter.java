package com.servicenow.exercise_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.resources.Game;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    private final Game[] games;
    private Context context;
    private int itemResource;

    public GameAdapter(Context context, int itemResource, Game[] games) {
        this.games        = games;
        this.context      = context;
        this.itemResource = itemResource;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemResource, parent, false);
        return new GameViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = this.games[position];
        holder.bindGame(game);
    }

    @Override
    public int getItemCount() {
        return this.games.length;
    }
}
