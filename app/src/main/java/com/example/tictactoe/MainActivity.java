package com.example.tictactoe;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Game game;
    private int[][] buttons = {{R.id.button1, R.id.button2, R.id.button3 }, { R.id.button4, R.id.button5,
            R.id.button6 }, {R.id.button7, R.id.button8, R.id.button9}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        if(savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("game");
            getButtonState();
        }
    }

    public void getButtonState() {
        TileState states;
        for(int i = 0; i < buttons.length; i++)
        {
            for(int j = 0; j <buttons.length; j++) {
                states = game.getState(i, j);
                Button button = findViewById(buttons[i][j]);
                if(states == TileState.CIRCLE) {
                    button.setText("0");
                }
                else if(states == TileState.CROSS) {
                    button.setText("X");
                }
            }
        }
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
        TileState state = game.turn(row, column);

        switch(state) {
            case CROSS:
                findViewById(R.id.Invalid).setVisibility(View.INVISIBLE);
                button.setText("X");
                break;
            case CIRCLE:
                findViewById(R.id.Invalid).setVisibility(View.INVISIBLE);
                button.setText("0");
                break;
            case INVALID:
                findViewById(R.id.Invalid).setVisibility(View.VISIBLE);
                break;
        }
        getGameState();
    }

    public void getGameState()
    {
        GameState winner = game.won();
        if(winner != GameState.IN_PROGRESS) {
            for (int i = 0; i < buttons.length; i++) {
                for(int j = 0; j < buttons.length; j++) {
                    findViewById(buttons[i][j]).setEnabled(false);
                }

            }
            if(winner == GameState.DRAW) {
                findViewById(R.id.draw).setVisibility(View.VISIBLE);
            }
            else if(winner == GameState.PLAYER_ONE) {
                findViewById(R.id.player1).setVisibility(View.VISIBLE);
            }
            else {
                findViewById(R.id.player2).setVisibility(View.VISIBLE);
            }
        }
    }

    public void resetClicked(View view) {
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }
}
