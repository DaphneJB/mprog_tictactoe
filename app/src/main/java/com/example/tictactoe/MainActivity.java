package com.example.tictactoe;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Game game;
    private int[] buttons = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        if(savedInstanceState != null) {
            game.setState(0,0,savedInstanceState.getSerializable("button1"));
            game.setState(0,1,savedInstanceState.getSerializable("button2"));
            game.setState(0,2,savedInstanceState.getSerializable("button3"));
            game.setState(1,0,savedInstanceState.getSerializable("button4"));
            game.setState(1,1,savedInstanceState.getSerializable("button5"));
            game.setState(1,2,savedInstanceState.getSerializable("button6"));
            game.setState(2,0,savedInstanceState.getSerializable("button7"));
            game.setState(2,1,savedInstanceState.getSerializable("button8"));
            game.setState(2,2,savedInstanceState.getSerializable("button9"));
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
        TileState state = game.choose(row, column);

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
                System.out.println("check");
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
                findViewById(buttons[i]).setEnabled(false);
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
        outState.putSerializable("button1", game.getState(0,0));
        outState.putSerializable("button2", game.getState(0,1));
        outState.putSerializable("button3", game.getState(0,2));
        outState.putSerializable("button4", game.getState(1,0));
        outState.putSerializable("button5", game.getState(1,1));
        outState.putSerializable("button6", game.getState(1,2));
        outState.putSerializable("button7", game.getState(2,0));
        outState.putSerializable("button8", game.getState(2,1));
        outState.putSerializable("button9", game.getState(2,2));
    }

}
