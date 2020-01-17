package com.servicenow.exercise_java.recyclerView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.servicenow.exercise.R;
import com.servicenow.resources.Game;

public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ImageView gameCover;
    private final TextView gameName;
    private final TextView gameDescription;


    private Game game;
    private Context context;

    public GameViewHolder(Context context, View itemView) {
        super(itemView);

        this.context         = context;
        this.gameCover       = itemView.findViewById(R.id.game_cover);
        this.gameName        = itemView.findViewById(R.id.game_name);
        this.gameDescription = itemView.findViewById(R.id.game_description);

        itemView.setOnClickListener(this);
    }

    public void bindGame(Game game) {
        this.game = game;
        this.gameName.setText(game.getName());
        this.gameDescription.setText(game.getShortDescription());
        this.gameCover.setImageResource(Game.Companion.getIconResource(game.getCover()));
    }

    @Override
    public void onClick(View v) {
        if (this.game != null) {
            Toast.makeText(this.context, "Clicked on " + this.game.getName(), Toast.LENGTH_SHORT ).show();
        }
    }
}
