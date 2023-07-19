package br.com.bilangieri.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

import br.com.bilangieri.minesweeper.exception.ExplosionException;

public class Board {

	private int line;
	private int col;
	private int mines;

	private final List<Field> fields = new ArrayList<>();

	public Board(int line, int col, int mines) {
		this.line = line;
		this.col = col;
		this.mines = mines;
		generateFields();
		neighborhood();
		placeMines();
	}

	private void placeMines() {
		int armedMines = 1;
		do {
			fields.get((int) (Math.random() * fields.size())).mine();
			armedMines = (int) fields.stream().filter(f -> f.isMined()).count();

		} while (armedMines < mines);

	}

	private void neighborhood() {
		for (Field f1 : fields) {
			for (Field f2 : fields) {
				f1.addNeighbor(f2);
			}
		}

	}

	private void generateFields() {
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < col; j++) {
				fields.add(new Field(i, j));
			}
		}

	}

	public boolean finish() {
		return fields.stream().allMatch(f -> f.goal());
	}

	public void restart() {
		fields.stream().forEach(f -> f.restart());
		placeMines();
	}

	public void open(int line, int col) {
		try {
			fields.parallelStream().filter(f -> f.getLine() == line && f.getCol() == col).findFirst()
					.ifPresent(f -> f.open());
		} catch (ExplosionException e) {
			fields.forEach(f -> f.forceOpen());
			throw e;
		}

	}

	public void changeMarked(int line, int col) {
		fields.parallelStream().filter(f -> f.getLine() == line && f.getCol() == col).findFirst()
				.ifPresent(f -> f.changeMarked());

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < col; j++) {
				sb.append(" ");
				sb.append(fields.get(count));
				sb.append(" ");
				count++;

			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
