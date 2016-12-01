package org.twinnation.tictactoe.game;

import org.twinnation.tictactoe.gui.TicTacToeGui;

import java.util.Arrays;

/**
 * Class that assume the role of the TicTacToe game
 */
public class TicTacToe {

	private int turn;
	private boolean isGameOver;
	private boolean isCurrentTurnX;
	private char[][] board = new char[3][3];


	/** Constructor */
	public TicTacToe() {
		System.out.println("New game started!");
		for (int i = 0; i<board.length; i++) {
			for (int j = 0; j<board[i].length; j++) {
				this.board[i][j] = ' ';
			}
		}
		this.isCurrentTurnX = false;
		this.isGameOver = false;
		this.turn = 0;
	}

	/**
	 *  Moves a piece to the given coordinate
	 * @param col Column coordinate
	 * @param row Row coordinate
	 */
	public void move(int col, int row) {
		//System.out.println("("+col+", "+row+")");
		if (board[col-1][row-1] == ' ') {
			board[col-1][row-1] = isCurrentTurnX ? 'X' : 'O';
			isCurrentTurnX = !isCurrentTurnX;
			turn++;
		} else {
			System.out.println("This case has already been played");
		}
	}


	/**
	 * Gets the TicTacToe board
	 * @return array representing the tictactoe board
	 */
	public char[][] getBoard() {
		System.out.println("returning board: \n"+ Arrays.toString(board[0])+"\n"+Arrays.toString(board[1])+"\n"+Arrays.toString(board[2]));
		return board;
	}

	/**
	 * Sets the tictactoe board
	 * @param board The board to set
	 */
	public void setBoard(char[][] board) {
		for (int i = 0; i<board.length; i++) {
			for (int j = 0; j<board[i].length; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}


	/**
	 * Gets the status of the game
	 * @return true if game is over, false if game isn't
	 */
	public boolean isOver() {
		return isGameOver;
	}


	/**
	 * Checks if there is a win, and if there is, draw a line on the panel
	 * @param panel Panel to draw the line on
	 */
	public void checkWin(TicTacToeGui.GPanel panel) {
		char[][] b = this.board;
		boolean gameOver = false;
		if (turn<2) {
			return;
		} else if (turn == 9) {
			System.out.println("Draw!");
			gameOver = true;
		}
		for (int i = 0; i<b.length; i++) {
			// COLUMN |
			if (b[i][0] != ' ' && b[i][0] == b[i][1]
					&& b[i][1] == b[i][2]) {
				//System.out.println("TIC TAC TOE (COL) -> "+b[i][0]);
				int x = (i+1)*(TicTacToeGui.FRAME_WIDTH/4);
				panel.drawWin(panel.getGraphics(), x, 0, x, TicTacToeGui.FRAME_HEIGHT);
				gameOver = true;
			}
			// ROW -
			if (b[0][i] != ' ' && b[0][i] == b[1][i] && b[1][i] == b[2][i]) {
				//System.out.println("TIC TAC TOE (ROW) -> "+b[0][i]);
				int y = (i+1)*(TicTacToeGui.FRAME_HEIGHT/4);
				panel.drawWin(panel.getGraphics(), 0, y, TicTacToeGui.FRAME_WIDTH, y);
				gameOver = true;
			}
		}
		// Diagonal
		if (b[0][0] != ' ' && b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			//System.out.println("TIC TAC TOE (DIAGONAL) -> "+b[0][0]);
			panel.drawWin(panel.getGraphics(), 0, 0, TicTacToeGui.FRAME_WIDTH, TicTacToeGui.FRAME_HEIGHT);
			gameOver = true;
		}
		// Diagonal
		if (b[0][2] != ' ' && b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			//System.out.println("TIC TAC TOE (DIAGONAL) -> "+b[0][2]);
			panel.drawWin(panel.getGraphics(), TicTacToeGui.FRAME_WIDTH, 0, 0, TicTacToeGui.FRAME_HEIGHT);
			gameOver = true;
		}
		isGameOver = gameOver;
	}

}
