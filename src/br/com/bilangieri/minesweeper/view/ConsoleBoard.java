package br.com.bilangieri.minesweeper.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.bilangieri.minesweeper.exception.CloseException;
import br.com.bilangieri.minesweeper.exception.ExplosionException;
import br.com.bilangieri.minesweeper.model.Board;

public class ConsoleBoard {

	private Board board;
	private Scanner sc = new Scanner(System.in);

	public ConsoleBoard(Board board) {
		this.board = board;
		startGame();
	}

	private void startGame() {
		try {
			boolean keep = true;

			while (keep) {

				gameCycle();
				System.out.println("Jogar novamente?  (S/n)");
				String response = sc.nextLine();
				if ("n".equalsIgnoreCase(response)) {
					keep = false;
					throw new CloseException();
				} else {
					board.restart();
				}
			}
		} catch (CloseException e) {
			System.out.println("Bye!");
		} finally {

			sc.close();
		}
	}

	private void gameCycle() {
		try {
			while (!board.finish()) {
				System.out.println(board);

				String entry = valueEntry("Digite (x, y): ");

				Iterator<Integer> xy = Arrays.stream(entry.trim().split(",")).map(i -> Integer.parseInt(i.trim()))
						.iterator();
				entry = valueEntry("1 -> Abrir Campo ou 2 -> (Des)Marcar Campo");
				if ("1".equalsIgnoreCase(entry.trim())) {
					board.open(xy.next(), xy.next());
				} else if ("2".equalsIgnoreCase(entry.trim())) {
					board.changeMarked(xy.next(), xy.next());
				}
			}
			System.out.println("Você Ganhou, Parabéns!");
		} catch (ExplosionException e) {

			System.out.println(board);
			System.out.println("Você perdeu o jogo");
		}
	}

	private String valueEntry(String message) {
		System.out.print(message);
		String entry = sc.nextLine();
		if ("Exit".equalsIgnoreCase(entry)) {
			throw new CloseException();
		}

		return entry;
	}

}
