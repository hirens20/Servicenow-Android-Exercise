package com.servicenow.exercise_java.recyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.exercise.R;
import com.servicenow.resources.Game;

public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private String TAG = GameViewHolder.class.getSimpleName();

    private final ImageView gameCover;
    private final TextView gameName;
    private final TextView gameDescription;
    private Game game;

    /**
     * Generic Viewholder. Can pass context too, but not useful here in this case so not passing.
     * @param itemView
     */
    public GameViewHolder(View itemView) {
        super(itemView);

        this.gameCover       = itemView.findViewById(R.id.game_cover);
        this.gameName        = itemView.findViewById(R.id.game_name);
        this.gameDescription = itemView.findViewById(R.id.game_description);

        itemView.setOnClickListener(this);
    }

    /**
     * Binding the game data.
     * @param game
     */
    public void bindGame(Game game) {
        this.game = game;
        this.gameName.setText(game.getName());
        this.gameDescription.setText(game.getShortDescription());
        this.gameCover.setImageResource(Game.Companion.getIconResource(game.getCover()));
    }

    /**
     * Added onclick listener in GameListActivity so doing nothing here.
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (this.game != null) {
            Log.d(TAG, "onClick: Clicked on game - " + this.game.getName());
        }
    }
}
