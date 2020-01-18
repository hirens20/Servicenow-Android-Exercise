package com.servicenow.exercise_java.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.resources.Game;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    private final Game[] games;
    private int itemResource;

    /**
     * Adapter constructor. Context can be passed but not getting used here so removed.
     * @param itemResource
     * @param games
     */
    public GameAdapter(int itemResource, Game[] games) {
        this.games        = games;
        this.itemResource = itemResource;
    }

    /**
     * Layout inflator
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemResource, parent, false);
        return new GameViewHolder(view);
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
