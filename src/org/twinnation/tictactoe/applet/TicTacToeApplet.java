package org.twinnation.tictactoe.applet;

import org.twinnation.tictactoe.gui.TicTacToeController;
import org.twinnation.tictactoe.gui.TicTacToeGui;

import javax.swing.*;


public class TicTacToeApplet extends JApplet {
	public void init() {
		TicTacToeGui gui = new TicTacToeGui();
		@SuppressWarnings("unused")
		TicTacToeController controller = new TicTacToeController(gui);
	}
}
