package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    public void tileClicked(View view) {
        int id = view.getId();
        int row = 0;
        int column = 0;
        switch (id) {
            case R.id.button1: row = 0;
                column = 0;
                break;
            case R.id.button2: row = 0;
                column = 1;
                break;
            case R.id.button3: row = 0;
                column = 2;
                break;
            case R.id.button4: row = 1;
                column = 0;
                break;
            case R.id.button5: row = 1;
                column = 1;
                break;
            case R.id.button6: row = 1;
                column = 2;
                break;
            case R.id.button7: row = 2;
                column = 0;
                break;
            case R.id.button8: row = 2;
                column = 1;
                break;
            case R.id.button9: row = 2;
                column = 2;
                break;
        }
        Button button = (Button) view;
        TileState state = game.choose(row, column);

        switch(state) {
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("0");
                break;
            case INVALID:
                // do something different
                break;
        }
    }

    public void resetClicked(View view) {
        game = new Game();
    }
}
