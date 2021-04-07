package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());

		//TODO
		//More tests
		//ZhiyuanD

		//submarine
		ship = new Submarine();
		assertEquals(1, ship.getLength());

		//cruiser
		ship = new Cruiser();
		assertEquals(3, ship.getLength());

		//destroyer
		ship = new Destroyer();
		assertEquals(2, ship.getLength());

	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());

		//TODO
		//More tests

		//submarine
		Ship submarine = new Submarine();
		row = 1;
		column = 3;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());

		//cruiser
		Cruiser cruiser = new Cruiser();
		row = 2;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());

		//destroyer
		Destroyer destroyer = new Destroyer();
		row = 4;
		column = 5;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	

		//TODO
		//More tests

		//submarine
		Ship submarine = new Submarine();
		row = 2;
		column = 4;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.setBowColumn(column);
		assertEquals(column, submarine.getBowColumn());	

		//cruiser
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 7;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());	

		//destroyer
		Destroyer destroyer = new Destroyer();
		row = 1;
		column = 6;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());	

	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);

		//TODO
		//More tests
		//Zhiyuan

		//submarine
		ship = new Submarine();
		int row = 1;
		int column = 1;
		boolean horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ocean.shootAt(1, 1);

		hits = new boolean[1];
		//assertArrayEquals(hits, ship.getHit()); ????
		assertTrue(ship.getHit()[0]);

		//cruiser
		ship = new Cruiser();
		row = 2;
		column = 3;
		horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);
		ocean.shootAt(2,1);
		ocean.shootAt(2,2);
		assertTrue(ship.getHit()[1]);
		assertTrue(ship.getHit()[2]);
		assertFalse(ship.getHit()[0]);

		//destroyer
		ship = new Destroyer();
		row = 2;
		column = 3;
		horizontal = false;
		ship.placeShipAt(row, column, horizontal, ocean);
		ocean.shootAt(2, 3);
		ocean.shootAt(1, 3);
		assertTrue(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);


	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());

		//TODO
		//More tests

		//cruiser
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());

		//destroyer
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());

		//submarine
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());



	}

	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());

		//TODO
		//More tests

		//cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertTrue(cruiser.isHorizontal());

		//destroyer
		Ship destroyer = new Destroyer();
		row = 5;
		column = 6;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertFalse(destroyer.isHorizontal());

		//submarine
		Ship submarine = new Submarine();
		row = 3;
		column = 4;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertFalse(submarine.isHorizontal());


	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());

		//TODO
		//More tests

		//cruiser
		Ship cruiser = new Cruiser();
		row = 6;
		column = 3;
		horizontal = true;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());

		//destroyer
		Ship destroyer = new Destroyer();
		row = 4;
		column = 5;
		horizontal = false;
		destroyer.setBowRow(row);
		assertEquals(row, destroyer.getBowRow());

		//submarine
		Ship submarine = new Submarine();
		row = 1;
		column = 8;
		horizontal = false;
		submarine.setBowRow(row);
		assertEquals(row, submarine.getBowRow());

	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());

		//TODO
		//More tests

		//cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 3;
		horizontal = true;
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());

		//destroyer
		Ship destroyer = new Destroyer();
		row = 7;
		column = 5;
		horizontal = false;
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());

		//submarine
		Ship submarine = new Submarine();
		row = 1;
		column = 5;
		submarine.setBowColumn(column);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());

		//TODO
		//More tests

		//cruiser
		Ship cruiser = new Cruiser();
		row = 1;
		column = 3;
		horizontal = true;
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());

		//destroyer
		Ship destroyer = new Destroyer();
		row = 4;
		column = 5;
		horizontal = false;
		destroyer.setHorizontal(horizontal);
		assertFalse(destroyer.isHorizontal());

		//submarine
		Ship submarine = new Submarine();
		row = 8;
		column = 7;
		horizontal = false;
		submarine.setHorizontal(horizontal);
		assertFalse(submarine.isHorizontal());


	}

	@Test
	void testOkToPlaceShipAt() {

		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		//TODO
		//More tests

		//when a cruiser is also in the ocean 
		Ship cruiser = new Cruiser();
		row = 2;
		column = 4;
		horizontal = true;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		cruiser.placeShipAt(row, column, horizontal, ocean);


		//add a destroyer
		Ship destroyer = new Destroyer();
		row = 2;
		column = 7;
		horizontal = false;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		destroyer.placeShipAt(row, column, horizontal, ocean);


		//put a destroyer illegally
		Ship destroyer2 = new Destroyer();
		row = 2;
		column = 5;
		horizontal = true;
		ok = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok);

		//put a submarine
		Ship submarine = new Submarine();
		row = 0;
		column = 5;
		horizontal = true;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok);

		//put a submarine illegally
		Ship submarine2 = new Submarine();
		row = 2;
		column = 2;
		horizontal = true;
		ok = submarine2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok);

	}

	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {

		//test when other ships are in the ocean

		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");

		//TODO
		//More tests

		//test third ship
		Cruiser cruiser = new Cruiser();
		row = 4;
		column = 4;
		horizontal = false;
		boolean ok3 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok3, "OK to place ship here.");
		cruiser.placeShipAt(row, column, horizontal, ocean);

		//test fourth ship
		Cruiser cruiser2 = new Cruiser();
		row = 0;
		column = 7;
		horizontal = true;
		boolean ok4 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship horizontally ajacent to another ship");

		//test fifth ship
		Destroyer destroyer = new Destroyer();
		row = 1;
		column = 6;
		horizontal = true;
		boolean ok5 = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok5, "Not OK to place ship diagonally ajacent to another ship");

		//test sixth ship
		Submarine submarine = new Submarine();
		row = 0;
		column = 5;
		horizontal = true;
		boolean ok6 = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok6, "not OK to place ship vertically adjacent above");

	}

	@Test
	void testPlaceShipAt() {

		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());

		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);


		//TODO
		//More tests
		//zhiyuanD

		//cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());

		assertEquals("cruiser", ocean.getShipArray()[2][1].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[2][2]);
		assertEquals(cruiser, ocean.getShipArray()[2][3]);

		//destroyer
		Ship destroyer = new Destroyer();
		row = 0;
		column = 8;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertTrue(destroyer.isHorizontal());

		assertEquals("destroyer", ocean.getShipArray()[0][7].getShipType());
		assertEquals(destroyer, ocean.getShipArray()[0][8]);

		//submarine
		Ship submarine = new Submarine();
		row = 5;
		column = 6;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		assertEquals(column, submarine.getBowColumn());
		assertFalse(submarine.isHorizontal());

		assertEquals("submarine", ocean.getShipArray()[5][6].getShipType());
		assertEquals(submarine, ocean.getShipArray()[5][6]);
	}

	@Test
	void testShootAt() {

		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());

		//TODO
		//More tests

		//cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(cruiser.shootAt(2, 4));
		assertTrue(cruiser.shootAt(2, 3));
		boolean[] hitArray1 = {true, true, false};
		assertArrayEquals(hitArray1, cruiser.getHit());

		//destroyer
		Ship destroyer = new Destroyer();
		row = 5;
		column = 6;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(destroyer.shootAt(4, 6));
		assertFalse(destroyer.shootAt(7, 7));
		boolean[] hitArray2 = {false, true};
		assertArrayEquals(hitArray2, destroyer.getHit());

		//submarine
		Ship submarine = new Submarine();
		row = 3;
		column = 9;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(submarine.shootAt(3, 9));
		assertFalse(submarine.shootAt(7, 7));
		boolean[] hitArray3 = {true};
		assertArrayEquals(hitArray3, submarine.getHit());
	}

	@Test
	void testIsSunk() {

		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());

		//TODO
		//More tests
		//cruiser
		Ship cruiser = new Cruiser();
		row = 1;
		column = 3;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(1, 3));
		assertTrue(cruiser.shootAt(1, 2));
		assertTrue(cruiser.shootAt(1, 1));
		assertTrue(cruiser.isSunk());

		//destroyer
		Ship destroyer = new Destroyer();
		row = 1;
		column = 7;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(0, 7));
		assertTrue(destroyer.shootAt(1, 7));
		assertTrue(destroyer.isSunk());

		//battleship
		Ship battleship = new Battleship();
		row = 4;
		column = 5;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(4, 5));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(3, 5));
		assertTrue(battleship.shootAt(2, 5));
		assertTrue(battleship.shootAt(1, 5));
		assertTrue(battleship.isSunk());

	}

	@Test
	void testToString() {

		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());

		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());

		//TODO
		//More tests
		//cruiser
		Ship cruiser = new Cruiser();
		assertEquals("x", cruiser.toString());
		
		row = 1;
		column = 5;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		cruiser.shootAt(1, 3);
		cruiser.shootAt(1, 4);
		cruiser.shootAt(1, 5);
		assertEquals("s", cruiser.toString());
		
		//destroyer
		Ship destroyer = new Destroyer();
		assertEquals("x", destroyer.toString());
		
		row = 2;
		column = 6;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		destroyer.shootAt(2, 5);
		assertEquals("x", destroyer.toString());
		destroyer.shootAt(2, 6);
		assertEquals("s", destroyer.toString());
		
		//submarine
		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());
		
		row = 6;
		column = 5;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(6, 5);
		assertEquals("s", submarine.toString());
	}

}
