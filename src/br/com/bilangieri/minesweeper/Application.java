package br.com.bilangieri.minesweeper;

import br.com.bilangieri.minesweeper.model.Board;

public class Application {

	public static void main(String[] args) {
		Board board = new Board(6, 6, 2);
		System.out.println(board);
		
		board.open(3, 3);
		board.changeMarked(4, 4);
		System.out.println(board);

	}

}
