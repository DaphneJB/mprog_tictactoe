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

    public GameState won() {
        int move = 0;
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(board[i][j] != TileState.BLANK)
                    move++;
            }
        }
        if(move != BOARD_SIZE * 3) {
            return GameState.IN_PROGRESS;
        }
        else {
            System.out.println("status: " + endGame());
            return endGame();
        }
    }

    public GameState endGame()
    {
        int j = 0;
        int k = 1;
        int l = 2;
        int winner = 0;
        TileState state = TileState.BLANK;
        for(int i=0; i < 3; i+=2) {
            if(board[i][i].equals(board[j][k]) && board[j][k].equals(board[0][2]) && board[0][2].equals(board[i][i])) {
                winner++;
                state = board[i][i];
            }
            if (board[i][i].equals(board[k][1]) && board[k][1].equals(board[2][l]) && board[2][l].equals(board[i][i])){
                winner++;
                state = board[i][i];
            }
                j++;
                k++;
                l-=2;
        }
        if(board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && board[2][0].equals(board[0][0])){
            winner++;
            state = board[0][0];
        }
        if(board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && board[1][2].equals(board[1][0])) {
            winner++;
            state = board[1][0];
        }
        if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && board[2][1].equals(board[0][1])){
            winner++;
            state = board[0][1];
        }
        if(winner == 0 || winner > 1)
        {
            return GameState.DRAW;
        }
        else {
            if(state == TileState.CROSS){
                return GameState.PLAYER_ONE;
            }
            else{
                return GameState.PLAYER_TWO;
            }
        }
    }
}
