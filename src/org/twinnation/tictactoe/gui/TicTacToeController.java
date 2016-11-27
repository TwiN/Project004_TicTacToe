package org.twinnation.tictactoe.gui;

import org.twinnation.tictactoe.bean.TicTacToe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author chris
 */
public class TicTacToeController {

	private TicTacToeGui gui;
	private TicTacToe game;
	private Thread thread;
	private GameThread gameThread;
	private boolean newMove = false;


	public TicTacToeController(TicTacToeGui gui) {
		this.gui = gui;
		this.game = new TicTacToe();
		this.gui.addMouseListener(new MouseClickHandler());
		this.gui.getPanel().addMouseListener(new MouseClickHandler());
		thread = new Thread(new GameThread());
		thread.run();
	}

	/** Mouse click handler */
	class MouseClickHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			//System.out.println("Detected mouse click @ "+e.getX()+","+e.getY());

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

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}

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
						game.play();
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
