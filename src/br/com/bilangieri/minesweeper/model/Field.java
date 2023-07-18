package br.com.bilangieri.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

import br.com.bilangieri.minesweeper.exception.ExplosionException;

public class Field {

	private boolean mined;
	private boolean open = false;
	private boolean marked = false;
	private final int line;
	private final int col;

	private List<Field> borders = new ArrayList<>();

	public Field(int line, int col) {
		this.col = col;
		this.line = line;
	}

	boolean addNeighbor(Field borderer) {
		boolean differentLine = this.line != borderer.line;
		boolean differentCol = this.col != borderer.col;
		boolean diagonal = differentCol && differentLine;
		int deltaLine = Math.abs(this.line - borderer.line);
		int deltaCol = Math.abs(this.col - borderer.col);
		int deltaSun = deltaCol + deltaLine;

		if (deltaSun == 1 && !diagonal) {
			borders.add(borderer);
			return true;

		} else if (deltaSun == 2 && diagonal) {
			borders.add(borderer);
			return true;

		} else {
			return false;
		}
	}

	void changeMarked() {
		
			marked = !marked;		
	}
	
	void mine() {
		mined = true;
	}

	boolean open() {
		if (!open && !marked) {
			open = true;
			if (mined) {
				throw new ExplosionException();
			}
			if (safe()) {
				borders.forEach(n -> n.open());
			}
			return true;
		}
		
		return false;
	}

	boolean safe() {
		return borders.stream().noneMatch(n -> {
			System.out.println("CAMPO " +n.line+","+n.col+ "MINADO?: " + n.mined);
			return n.mined;
		});
	}

	public boolean isMarked() {
		return marked;
	};
	public boolean isOpen() {
		return open;
	};

}
