package com.example.tictactoe;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private Boolean playerOneTurn;  //true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        TileState tile = board[row][column];
        if(tile == TileState.BLANK) {
            if(playerOneTurn) {
                playerOneTurn = false;
                board[row][column] = TileState.CROSS;
                return TileState.CROSS;
            }
            else {
                playerOneTurn = true;
                board[row][column] = TileState.CIRCLE;
                return TileState.CIRCLE;
            }
        }
        else {
            return TileState.INVALID;
        }
    }
}
