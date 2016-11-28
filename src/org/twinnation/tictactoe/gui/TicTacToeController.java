package org.twinnation.tictactoe.gui;

import org.twinnation.tictactoe.game.TicTacToe;

import java.awt.event.*;

public class TicTacToeController {

	private TicTacToeGui gui;
	private TicTacToe game;
	private Thread thread;
	private GameThread gameThread;
	private boolean newMove = false;


	public TicTacToeController(TicTacToeGui gui) {
		this.gui = gui;
		this.game = new TicTacToe();
		//this.gui.getPanel().addMouseListener(new MouseClickHandler());
		this.gui.getPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col, row;
				// get column
				if (e.getX()<(TicTacToeGui.FRAME_WIDTH/3)) {
					col = 1;
				} else if (e.getX()<((2*TicTacToeGui.FRAME_WIDTH)/3)) {
					col = 2;
				} else {
					col = 3;
				}
				// get row
				if (e.getY()<(TicTacToeGui.FRAME_HEIGHT/3)) {
					row = 1;
				} else if (e.getY()<((2*TicTacToeGui.FRAME_HEIGHT)/3)) {
					row = 2;
				} else {
					row = 3;
				}

				if (game.isOver()) {
					System.out.println("- THE GAME HAS BEEN SET AS TERMINATED -");
					game = new TicTacToe();
					gui.getPanel().restartGame();
					gui.getPanel().repaint();

				} else {
					game.move(col, row);
				}
				newMove = true;
			}
		});
		// menu > exit
		this.gui.getFrame().getJMenuBar().getMenu(0).getItem(0).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// menu > restart
		this.gui.getFrame().getJMenuBar().getMenu(0).getItem(1).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				game = new TicTacToe();
				gui.getPanel().restartGame();
				gui.getPanel().repaint();
				newMove = true;
			}
		});

		thread = new Thread(new GameThread());
		thread.run();
	}

	/** Mouse click handler */
	/*
	class MouseClickHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int col, row;
			// get column
			if (e.getX()<(TicTacToeGui.FRAME_WIDTH/3)) {
				col = 1;
			} else if (e.getX()<((2*TicTacToeGui.FRAME_WIDTH)/3)) {
				col = 2;
			} else {
				col = 3;
			}
			// get row
			if (e.getY()<(TicTacToeGui.FRAME_HEIGHT/3)) {
				row = 1;
			} else if (e.getY()<((2*TicTacToeGui.FRAME_HEIGHT)/3)) {
				row = 2;
			} else {
				row = 3;
			}

			if (game.isOver()) {
				System.out.println("- THE GAME HAS BEEN SET AS TERMINATED -");
				game = new TicTacToe();
				gui.getPanel().restartGame();
				gui.getPanel().repaint();

			} else {
				game.move(col, row);
			}
			newMove = true;
		}

		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
	*/

	class GameThread implements Runnable {
		public void run() {
			try {
				while (true) {
					while (game == null){
						//System.out.println("game is null");
						Thread.sleep(50);
					}
					while (!game.isOver()) {
						while (!newMove) {
							//System.out.println("no new move made");
							Thread.sleep(50);
						}
						game.checkWin(gui.getPanel());
						gui.getPanel().setBoard(game.getBoard());
						gui.repaint();
						newMove = false;
					}
					//gui.setBoard(game.getBoard()); // FIXME: duplicate
					//gui.repaint();
					Thread.sleep(300);
				}
			} catch (Exception e) {
				System.out.println("[ERROR] Something happened.");
				e.printStackTrace();
			}
		}
	}
}
