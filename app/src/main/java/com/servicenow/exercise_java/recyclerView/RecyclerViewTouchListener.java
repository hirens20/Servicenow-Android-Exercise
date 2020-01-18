package com.servicenow.exercise_java.recyclerView;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created this listener because NOT using the overidden onClick method in viewHolder.
 * Using this in GameListActivity
 */
public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
    private String TAG = RecyclerViewTouchListener.class.getSimpleName();
    private GestureDetector gestureDetector;
    private ClickListener clickListener;

    public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d(TAG, "onSingleTapUp: User Tapped on a game");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    Log.d(TAG, "onLongPress: Doing nothing on long press");
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildLayoutPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    /**
     * Implemented these methods in GameListActivity
     */
    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
}
