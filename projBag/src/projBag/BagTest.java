package projBag;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BagTest {
	// Sonarlint --> java:S2293 : The diamond operator ("<>") should be used 
	Bag<Object> bag = new Bag<Object>();
	
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
		 bag.add("5");
		 if (bag.isEmpty()) {
			 fail("testSize isn't implemented correctly");
		 }
	}

	@Test
	public void testBag() {
		bag = new Bag<>();
		// Sonarlint : Collection.isEmpty() should be used to test for emptiness (java:S1155)
		if(!bag.isEmpty()) {
			fail("testBag isn't implemented correctly");
		}
		
		// Sonarlint : Double Brace Initialization should not be used (java:S3599)
		// Sonarlint : The diamond operator ("<>") should be used (java:S2293)
		Collection<Object> collection = new ArrayList<>();
		for(int i = 0; i < 10; i++ ) {	
			collection.add(i);
		}
		
		// Sonarlint : The diamond operator ("<>") should be used (java:S2293)
		Bag<Object> bag = new Bag<>(collection);
		
		if (bag.isEmpty()) {
			fail("testBag isn't implemented correctly");
		}
			
	}

	@Test
	public void testAddE() {
		bag = new Bag<>();
		for (int i = 0; i < 10; i++) {
			bag.add(i);
		}
		if (bag.size() != 10) {
			fail("testAddE isn't implemented correctly");
		}
	}

	@Test
	public void testIterator() {
		bag = new Bag<>();
		Iterator<Object> itr = bag.iterator();
		
		if (!itr.hasNext()) {
			fail("testIterator isn't implemented correctly");
		}
	}

	@Test
	public void testIsEmpty() {
		bag = new Bag<>();
		if (!bag.isEmpty()) fail("testIsEmpty isn't implemented correctly");
	}

	@Test
	public void testItrRemove() {
		bag = new Bag<>();
		Iterator<Object> itr = bag.iterator();
		
		
		for (int i = 0; i < 10; i++) {
			bag.add(i);
		}
		
		itr.remove();
		
		if (bag.size() != 9) {
			fail("testIteratorRemove isn't implemented correctly");
		}
	}

}
