package org.twinnation.tictactoe.main;

import org.twinnation.tictactoe.gui.TicTacToeController;
import org.twinnation.tictactoe.gui.TicTacToeGui;

/**
 * @author chris
 */
public class Main {

	public static void main(String[] args) {
		TicTacToeGui gui = new TicTacToeGui();
		@SuppressWarnings("unused")
		TicTacToeController controller = new TicTacToeController(gui);
	}
}
