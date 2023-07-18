package br.com.bilangieri.minesweeper.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bilangieri.minesweeper.exception.ExplosionException;
import br.com.bilangieri.minesweeper.model.Field;

class FieldTest {

	private Field field;

	@BeforeEach
	void start() {
		field = new Field(3, 4);
	}

	@Test
	void testBorders() {
		Field vizinho = new Field(4, 4);
		assertTrue(field.addNeighbor(vizinho));
	}
	
	@Test
	void testNotBorders() {
		Field vizinho = new Field(1, 4);
		assertFalse(field.addNeighbor(vizinho));
	}

	@Test
	void testBordersDiagonal() {
		Field vizinho = new Field(4, 3);
		assertTrue(field.addNeighbor(vizinho));
	}

	@Test
	void testValueDefaultMarked() {
		assertFalse(field.isMarked());
	}

	@Test
	void testChangeMarker() {
		field.changeMarked();
		assertTrue(field.isMarked());
	}

	@Test
	void testTwiceChangeMarker() {
		field.changeMarked();
		field.changeMarked();
		assertFalse(field.isMarked());
	}

	@Test
	void openFieldNotMinedAndUnmarked() {
		assertTrue(field.open());
	}

	@Test
	void openFieldNotMinedAndMarked() {
		field.changeMarked();
		assertFalse(field.open());
	}
	
	@Test
	void openFieldMinedAndUnmarked() {
		field.mine();
		assertThrows(ExplosionException.class,()-> field.open());
	}
	
	@Test
	void openBorder() {
		Field b1 = new Field(2,4);
		Field b2 = new Field(3,3);
		Field b3 = new Field(3,5);
		Field b4 = new Field(4,4);
		Field bb1 = new Field(2,3);
		Field bb2 = new Field(2,2);
		Field bb3 = new Field(3,2);
		Field bb4 = new Field(4,2);
		
		b2.addNeighbor(bb1);
		b2.addNeighbor(bb2);
		b2.addNeighbor(bb3);
		b2.addNeighbor(bb4);
		
		
		field.addNeighbor(b1);
		field.addNeighbor(b2);
		field.addNeighbor(b3);
		field.addNeighbor(b4);
		
		field.open();
		assertTrue(b2.isOpen() && bb2.isOpen());
		
	}
	@Test
	void openBorderwithMine() {
		Field b1 = new Field(2,4);
		Field b2 = new Field(3,3);
		Field b3 = new Field(3,5);
		Field b4 = new Field(4,4);
		Field bb1 = new Field(2,3);
		Field bb2 = new Field(2,2);
		Field bb3 = new Field(3,2);
		Field bb4 = new Field(4,2);
		
		b2.addNeighbor(bb1);
		b2.addNeighbor(bb2);
		b2.addNeighbor(bb3);
		b2.addNeighbor(bb4);
		
		bb2.mine();
		b3.changeMarked();
		
		field.addNeighbor(b1);
		field.addNeighbor(b2);
		field.addNeighbor(b3);
		field.addNeighbor(b4);
		 field.open();
		assertFalse(bb2.isOpen() && b2.isOpen() && b3.isMarked());
		
	}
	

}
