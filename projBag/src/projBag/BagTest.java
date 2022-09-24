package projBag;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BagTest {
	// Sonarlint --> java:S2293 : The diamond operator ("<>") should be used 
	Bag<Object> bag = new Bag<>();
	
	@Before
	public void setUp() throws Exception {
		bag = new Bag<>();
	}

	@After
	public void tearDown() throws Exception {
		bag = new Bag<>();
		bag = null;
	}

	@Test
	public void testSize() {
		bag = new Bag<>();
		
		 if (!bag.isEmpty()) {
			 fail("testSize isn't implemented correctly");
		 }
	}

	@Test
	public void testBag() {
		bag = new Bag<>();
		if(bag.size() != 0) {
			fail("testBag isn't implemented correctly");
		}
	}

	@Test
	public void testAddE() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbstractCollection() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator1() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmpty() {
		bag = new Bag<>();
		if (!bag.isEmpty()) fail("testIsEmpty isn't implemented correctly");
	}

	@Test
	public void testContains() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddE1() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented");
	}

}
