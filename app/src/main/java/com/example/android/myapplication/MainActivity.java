package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private View mUndoBarLayout;
    private TextView mIsUndoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUndoBarLayout = findViewById(R.id.fl_undo_bar);
        mIsUndoTextView = (TextView) findViewById(R.id.tv_is_undo);

    }

    public void testUndo(View view) {
        MyUndoBar myUndoBar = new MyUndoBar(mUndoBarLayout);
        myUndoBar.start();
    }

    public class MyUndoBar extends UndoBar {

        public MyUndoBar(View view) {
            super(view);
        }
        @Override
        public void onUndoClicked() {
            mIsUndoTextView.setText("UNDO CLICKED");
        }

        @Override
        public void onUndoNotClicked() {
            mIsUndoTextView.setText("UNDO NOT CLICKED");
        }
    }

}

