package org.twinnation.tictactoe.bean;

/**
 * @author chris
 */
public class TicTacToe {

	private boolean isCurrentTurnX = false;

	private char[][] board = {
			{' ', ' ', ' '},
			{' ', ' ', ' '},
			{' ', ' ', ' '}
	};

	private boolean isGameOver;

	public TicTacToe() {
		System.out.println("Game is supposedly started");
		isGameOver = false;
	}

	public void move(int col, int row) {
		System.out.println("("+col+", "+row+")");
		if (board[col-1][row-1] == ' ') {
			board[col - 1][row - 1] = isCurrentTurnX ? 'X' : 'O';
			isCurrentTurnX = !isCurrentTurnX;
		} else {
			System.out.println("This case has already been played");
		}
	}

	public char[][] getBoard() {
		return board;
	}

	public boolean isOver() {
		return isGameOver;
	}

	public void play() {
		System.out.println("Next!");
	}


}
