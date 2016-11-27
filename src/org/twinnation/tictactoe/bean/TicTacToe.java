package org.twinnation.tictactoe.bean;

import org.twinnation.tictactoe.gui.TicTacToeGui;

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

	private int turn = 0;

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
		turn++;
	}

	public void checkWin(TicTacToeGui.GPanel panel) {
		char[][] b = this.board;
		if (turn<2) {
			return;
		}
		for (int i = 0; i<b.length; i++) {
			// COLUMN |
			if (b[i][0] != ' ' && b[i][0] == b[i][1]
					&& b[i][1] == b[i][2]) {
				System.out.println("TIC TAC TOE (COL) -> "+b[i][0]);
				int x = (i+1)*(TicTacToeGui.FRAME_WIDTH/4);
				panel.drawWin(panel.getGraphics(), x, 0, x, TicTacToeGui.FRAME_HEIGHT);
			}
			// ROW -
			if (b[0][i] != ' ' && b[0][i] == b[1][i] && b[1][i] == b[2][i]) {
				System.out.println("TIC TAC TOE (ROW) -> "+b[0][i]);
				int y = (i+1)*(TicTacToeGui.FRAME_HEIGHT/4);
				panel.drawWin(panel.getGraphics(), 0, y, TicTacToeGui.FRAME_WIDTH, y);
			}
		}

		// Diagonal
		if (b[0][0] != ' ' && b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			System.out.println("TIC TAC TOE (DIAGONAL) -> "+b[0][0]);
			panel.drawWin(panel.getGraphics(), 0, 0, TicTacToeGui.FRAME_WIDTH, TicTacToeGui.FRAME_HEIGHT);
		}

		// Diagonal
		if (b[0][2] != ' ' && b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			System.out.println("TIC TAC TOE (DIAGONAL) -> "+b[0][2]);
			panel.drawWin(panel.getGraphics(), TicTacToeGui.FRAME_WIDTH, 0, 0, TicTacToeGui.FRAME_HEIGHT);
		}

	}


}
