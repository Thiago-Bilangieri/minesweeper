package br.com.bilangieri.minesweeper;

import br.com.bilangieri.minesweeper.model.Board;
import br.com.bilangieri.minesweeper.view.ConsoleBoard;

public class Application {

	public static void main(String[] args) {
		Board board = new Board(6, 6, 2);
		new ConsoleBoard(board);
		

	}

}
