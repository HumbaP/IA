package com.company;

public class TicTacData {
    /*Tipo de dato Tic Tac toe*/

    private String status;
    private boolean botLastMove;
    private int lastMoveIndex;
    private int size;

    public TicTacData(String status, boolean botLastMove, int lastMoveIndex,int size){
        this.status=status;
        this.botLastMove=botLastMove;
        this.lastMoveIndex=lastMoveIndex;
        this.size=size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isBotLastMove() {
        return botLastMove;
    }

    public void setBotLastMove(boolean botLastMove) {
        this.botLastMove = botLastMove;
    }

    public int getLastMoveIndex() {
        return lastMoveIndex;
    }

    public void setLastMoveIndex(int lastMoveIndex) {
        this.lastMoveIndex = lastMoveIndex;
    }

    public char getNextMove(){
        return botLastMove? 'O':'X';
    }

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
    
}
