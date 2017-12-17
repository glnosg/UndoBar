package com.example.android.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.AsyncTask;
import android.view.View;

/**
 * Created by pawel on 17.12.17.
 */

abstract class UndoBar {

    // Time of duration of UndoBar in milliseconds
    private static final int UNDO_BAR_DURATION = 2500;
    //View used as an UndoBar
    private View mUndoBarView;
    // true if UndoBar was clicked, false if not
    private boolean isUndoClicked;

    /**
     *
     * @param undoBarView - View which is going to be used as an UndoBar
     */
    public UndoBar (View undoBarView) {
        mUndoBarView = undoBarView;
        setUpUndoBar();
    }

    // Method triggered when UndoBar is clicked
    public abstract void onUndoClicked();

    // Method triggered when UndoBar is NOT clicked
    public abstract void onUndoNotClicked();

    //start executing UndoBar
    public void start() {
        new UndoTask().execute();
    }

    // Sets up OnClickListener
    private void setUpUndoBar() {
        mUndoBarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUndoClicked = true;
                onUndoClicked();
                hideUndoBar();
            }
        });
    }

    private void showUndoBar() {
        mUndoBarView.setVisibility(View.VISIBLE);
        mUndoBarView.setAlpha(0.0f);

        mUndoBarView.animate()
                .alpha(1.0f)
                .setListener(null);
    }

    private void hideUndoBar() {
        mUndoBarView.animate()
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mUndoBarView.setVisibility(View.GONE);
                    }
                });
    }

    private class UndoTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            isUndoClicked = false;
            showUndoBar();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                Thread.sleep(UNDO_BAR_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        }

        protected void onPostExecute(Boolean result) {
            hideUndoBar();

            if (!isUndoClicked)
                onUndoNotClicked();
        }
    }
}