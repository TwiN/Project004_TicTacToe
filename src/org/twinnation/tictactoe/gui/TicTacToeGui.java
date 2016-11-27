package org.twinnation.tictactoe.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author chris
 */
public class TicTacToeGui extends JFrame {

	public static final int FRAME_WIDTH = 300;
	public static final int FRAME_HEIGHT = 300;

	private JFrame frame = new JFrame();
	private GPanel panel;

	char[][] board;

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

	public void setBoard(char[][] board) {
		System.out.println("Board has been set.");
		this.board = board;
	}


	public class GPanel extends JPanel {



		public GPanel() {

		}

		public void test() {
			System.out.println("Testsetsetstsetstst");
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			System.out.println("paintComponent in GPanel has been called.");
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
						g.drawString(board[i][j] + "", ((i * FRAME_WIDTH) / 3)+(FRAME_WIDTH/3)/2, ((j * FRAME_HEIGHT) / 3)+(FRAME_HEIGHT/3)/2);
					}
				}
			} else {
				System.out.println("board is null");
			}
		}
	}

}
