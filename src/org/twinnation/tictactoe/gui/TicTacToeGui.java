package org.twinnation.tictactoe.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author chris
 */
public class TicTacToeGui extends JFrame {

	public static final int FRAME_WIDTH = 300;
	public static final int FRAME_HEIGHT = 300;

	private JFrame frame = new JFrame();
	private GPanel panel;

	//char[][] board;


	public TicTacToeGui() {
		frame.setTitle("TicTacToe");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.getContentPane().setPreferredSize(new Dimension(
				FRAME_WIDTH, FRAME_HEIGHT));
		panel = new GPanel();

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public GPanel getPanel() {
		return panel;
	}

	@Override
	public void repaint() {
		panel.repaint();
	}



	public class GPanel extends JPanel {

		private String winLine;
		private char[][] board = new char[3][3];

		public GPanel() {
			for (int i = 0; i<board.length; i++) {
				for (int j = 0; j<board[i].length; j++) {
					this.board[i][j] = ' ';
				}
			}
		}

		public void setBoard(char[][] board) {
			for (int i = 0; i<board.length; i++) {
				for (int j = 0; j<board[i].length; j++) {
					this.board[i][j] = board[i][j];
				}
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawRect(1, 1, FRAME_WIDTH-2, FRAME_HEIGHT-2);
			// draws the outline of the TicTacToe board
			int x = 3;
			while (x --> 0) {
				g.drawLine((x*FRAME_WIDTH) / 3, 0, (x*FRAME_WIDTH) / 3, FRAME_HEIGHT);
				g.drawLine(0, (x*FRAME_HEIGHT) / 3, FRAME_WIDTH, (x*FRAME_HEIGHT) / 3);
			}
			if (board != null) {

				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[i].length; j++) {
						g.setFont(new Font("Arial", 1, 48));
						g.drawString(board[i][j] + "",
								((i*FRAME_WIDTH)/3)+(FRAME_WIDTH/3)/2, ((j*FRAME_HEIGHT)/3)+(FRAME_HEIGHT/3)/2);
						if (!(this.winLine == null)) {
							String[] win = winLine.split(",");
							g.setColor(Color.RED);
							g.drawLine(Integer.parseInt(win[0]), Integer.parseInt(win[1]),
									Integer.parseInt(win[2]), Integer.parseInt(win[3]));
							g.setColor(Color.BLACK);
						}
					}
				}
			} else {
				System.out.println("board is null");
			}
		}

		public void drawWin(Graphics g, int x1, int y1, int x2, int y2) {
			this.winLine = x1+","+y1+","+x2+","+y2;
		}

		public void restartGame() {
			System.out.println("TicTacToeGui > GPanel > restartGame()");
			this.winLine = null;
		}
	}

}
