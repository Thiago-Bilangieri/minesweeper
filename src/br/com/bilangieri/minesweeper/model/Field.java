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

	public boolean isMarked() {
		return marked;
	};

	public boolean isOpen() {
		return open;
	};

	public int getLine() {
		return line;
	}

	public int getCol() {
		return col;
	}
	
	public boolean isMined() {
		return mined;
	}


	int mineInBorder() {
		return (int) borders.stream().filter(b -> b.mined).count();
	}

	void restart() {
		marked = false;
		mined = false;
		open = false;
	}

	@Override
	public String toString() {
		if (marked) {
			return "x";
		} else if (open && mined) {
			return "*";
		} else if (open && mineInBorder() > 0) {
			return Integer.toString(mineInBorder());
		}else if(open) {
			return " ";
		}else {
			return "?";
		}
	}

	public boolean goal() {
		var unveiled = !mined && open;
		var protect = mined && marked;
		return unveiled || protect;
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
	void forceOpen() {
		open = true;
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
			return n.mined;
		});
	}

}
