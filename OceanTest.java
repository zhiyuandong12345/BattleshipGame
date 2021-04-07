package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;

	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testEmptyOcean() {

		//tests that all locations in the ocean are "empty"

		Ship[][] ships = ocean.getShipArray();

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];

				assertEquals("empty", ship.getShipType());
			}
		}

		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());

		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());

		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}

	@Test
	void testPlaceAllShipsRandomly() {

		//tests that the correct number of each ship type is placed in the ocean

		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();

		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}

		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}

		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);

		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);

		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(1, 5));

		//TODO
		//More tests
		//Zhiyuan

		//create a new battleship
		Battleship battleship = new Battleship();
		row = 3;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(3, 2));

		//create a new cruiser
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(5, 3));

		//create another destroyer
		Destroyer destroyer2 = new Destroyer();
		row = 2;
		column = 7;
		horizontal = false;
		destroyer2.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(1, 7));

		//create another submarine
		Submarine submarine2 = new Submarine();
		row = 1;
		column = 1;
		horizontal = false;
		submarine2.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(1, 1));
	}

	@Test
	void testShootAt() {

		assertFalse(ocean.shootAt(0, 1));

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));

		//TODO
		//More tests
		//Zhiyuan

		//create a new battleship

		assertFalse(ocean.shootAt(4, 3));

		Battleship battleship = new Battleship();
		row = 8;
		column = 2;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(7, 2));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(6, 2));
		assertTrue(ocean.shootAt(5, 2));
		assertFalse(ocean.shootAt(9,2));
		assertTrue(ocean.shootAt(8, 2));

		assertTrue(battleship.isSunk());

		//create a new cruiser
		Cruiser cruiser = new Cruiser();
		row = 1;
		column = 2;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 0));
		assertFalse(cruiser.isSunk());
		assertFalse(ocean.shootAt(1, 3));
		assertTrue(ocean.shootAt(1, 1));
		assertTrue(ocean.shootAt(1, 2));
		assertTrue(cruiser.isSunk());

		//create a new submarine
		Submarine submarine = new Submarine();
		row = 0;
		column = 5;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertFalse(ocean.shootAt(0, 6));
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(submarine.isSunk());

	}

	@Test
	void testGetShotsFired() {

		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());

		//TODO
		//More tests
		//Zhiyuan

		//Add a cruiser
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(5, 2));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(5, 3));
		assertFalse(ocean.shootAt(5, 5));
		assertTrue(ocean.shootAt(5, 4));
		assertTrue(cruiser.isSunk());

		assertEquals(10, ocean.getShotsFired());

		//Add a battleship
		Battleship battleship = new Battleship();
		row = 4;
		column = 6;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 6));
		assertFalse(ocean.shootAt(5, 6));
		assertFalse(ocean.shootAt(0, 6));
		assertTrue(ocean.shootAt(2, 6));
		assertTrue(ocean.shootAt(3, 6));
		assertTrue(ocean.shootAt(4, 6));
		assertTrue(battleship.isSunk());

		assertEquals(16, ocean.getShotsFired());

	}

	@Test
	void testGetHitCount() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());

		//TODO
		//More tests
		//Zhiyuan

		//cruiser
		Cruiser cruiser = new Cruiser();
		row = 7;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(7, 2));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(7, 3));
		assertTrue(ocean.shootAt(7, 4));
		assertTrue(cruiser.isSunk());

		assertEquals(4, ocean.getHitCount());

		//submarine
		Submarine submarine = new Submarine();
		row = 9;
		column = 2;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 2));
		assertTrue(submarine.isSunk());

		assertEquals(5, ocean.getHitCount());

		//battleship
		Battleship battleship = new Battleship();
		row = 4;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 9));
		assertTrue(ocean.shootAt(2, 9));
		assertTrue(ocean.shootAt(3, 9));
		assertTrue(ocean.shootAt(4, 9));
		assertFalse(ocean.shootAt(5, 9));
		assertFalse(ocean.shootAt(1, 9)); // when shoot location that has been shot
		assertTrue(battleship.isSunk());


		assertEquals(9, ocean.getHitCount());

	}

	@Test
	void testGetShipsSunk() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());

		//TODO
		//More tests
		//Zhiyuan 

		//cruiser

		Cruiser cruiser = new Cruiser();
		row = 1;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 1));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(1, 2));
		assertTrue(ocean.shootAt(1, 3));
		
		assertEquals(4, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		//submarine
		Submarine submarine = new Submarine();
		row = 2;
		column = 3;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(2, 3));
		assertTrue(submarine.isSunk());
		
		assertEquals(5, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		
		//battleship
		Battleship battleship = new Battleship();
		row = 3;
		column = 6;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(3, 6));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(2, 6));
		assertTrue(ocean.shootAt(1, 6));
		assertTrue(ocean.shootAt(0, 6));
		assertTrue(battleship.isSunk());
		
		assertEquals(9, ocean.getHitCount());
		assertEquals(3, ocean.getShipsSunk());
		

	}

	@Test
	void testGetShipArray() {

		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);

		assertEquals("empty", shipArray[0][0].getShipType());

		//TODO
		//More tests
		//Zhiyuan
		
		//put a submarine in the ocean
		Submarine submarine = new Submarine();
		int row = 1;
		int column = 2;
		boolean horizontal = true;
		
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("submarine", shipArray[1][2].getShipType());
		
		//put a cruiser in the ocean
		Cruiser cruiser = new Cruiser();
		row = 3;
		column = 5;
		horizontal = false;
		
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("cruiser", shipArray[3][5].getShipType());
		assertEquals("cruiser", shipArray[2][5].getShipType());
		assertEquals("cruiser", shipArray[1][5].getShipType());
		
		//put a destroyer in the ocean
		Destroyer destroyer = new Destroyer();
		
		row = 4;
		column = 2;
		horizontal = true;
		
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("destroyer", shipArray[4][1].getShipType());
		assertEquals("destroyer", shipArray[4][2].getShipType());
		
		//put a battleship in the ocean
		Battleship battleship = new Battleship();
		
		row = 4;
		column = 9;
		horizontal = true;
		
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals("battleship", shipArray[4][6].getShipType());
		assertEquals("battleship", shipArray[4][7].getShipType());
		assertEquals("battleship", shipArray[4][8].getShipType());
		assertEquals("battleship", shipArray[4][9].getShipType());
		
		assertEquals("empty", shipArray[5][9].getShipType());
		
	}

}