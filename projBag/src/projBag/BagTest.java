package projBag;

import static org.junit.Assert.*;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
		System.out.println("testSize");
		bag = new Bag<>();
		 if (!bag.isEmpty()) {
			 fail("testSize isn't implemented correctly");
		 }
		 bag.add("5");
		 if (bag.isEmpty()) {
			 fail("testSize isn't implemented correctly");
		 }
		 System.out.println("Test : Ok");
	}

	@Test
	public void testBag() {
		System.out.println("testBag");
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
		System.out.println("Test : Ok");
			
	}

	@Test
	public void testAddE() {
		System.out.println("testAddE");
		bag = new Bag<>();
		for (int i = 0; i < 100; i++) {
			bag.add(i);
		}
		if (bag.size() != 100) {
			fail("testAddE isn't implemented correctly");
		}
		System.out.println("Test : Ok");
		
	}

	@Test
	public void testIterator() {
		System.out.println("testIterator");
		bag = new Bag<>();
		Iterator<Object> itr = bag.iterator();
		
		if (!itr.hasNext()) {
			fail("testIterator isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testIsEmpty() {
		System.out.println("testIsEmpty");
		bag = new Bag<>();
		if (!bag.isEmpty()) fail("testIsEmpty isn't implemented correctly");
		System.out.println("Test : Ok");
	}

	@Test
	public void testItrRemove() {
		System.out.println("testItrRemove");
		bag = new Bag<>();
		Iterator<Object> itr = bag.iterator();
		
		
		for (int i = 0; i < 10; i++) {
			bag.add(i);
		}
		
		
		
		for (int i = 0; i < 10; i++) { 
			itr.remove();
			if (bag.size() != 10-(i+1)) {
				fail("testItrRemove isn't implemented correctly");
			}
		}
		
		try {
			itr.remove();
			 fail("testItrRemove isn't implemented correctly");
		    } catch(NoSuchElementException e) {
		    	NoSuchElementException wantedError = new NoSuchElementException("The iterator doesn't have an element to remove");
		    	if (!e.getMessage().equals(wantedError.getMessage())) {
		    		 fail("testItrRemove isn't implemented correctly");
		    	 }
		    	 
		    }
		
		System.out.println("Test : Ok");
	}
	
	@Test
	public void testItrHasNext() {
		System.out.println("testItrHasNext");
		bag = new Bag<>();
		Iterator<Object> itr = bag.iterator();
		
		
		for (int i = 0; i < 10; i++) {
			bag.add(i);
		}
		
		for (int i = 0; i < 10; i++) {
			if (!itr.hasNext()) {
				fail("testItrHasNext isn't implemented correctly");
				} else {
				itr.next();
			}
		}
		if (itr.hasNext()) {
			fail("testItrHasNext isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}
	
	@Test
	public void testItrNext() {
		System.out.println("testItrNext");
		bag = new Bag<>();
		Iterator<Object> itr = bag.iterator();
		
		
		for (int i = 0; i < 100; i++) {
			bag.add(i);
		}
		
		for (int i = 0; i < 100; i++) {
			itr.next();
		}
		try {
			itr.next();
			 fail("testItrHasNext isn't implemented correctly");
		    } catch(NoSuchElementException e) {
		    	if (e.getMessage() != null) {
		    		fail("testItrHasNext isn't implemented correctly");
		    	}
		    	 
		    }
		System.out.println("Test : Ok");
	}
	

	@Test
	public void testAbstractCollection() {
		System.out.println("testAbstractCollection");
		AbstractCollection<Object> ac = new ArrayList<>();
		if (!ac.isEmpty()) {
			fail("testAbstractCollection isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testAbstractCollectionIterator() {
		System.out.println("testAbstractCollectionIterator");
		AbstractCollection<Object> ac = new ArrayList<>();
		Iterator<Object> itr = ac.iterator();
		
		
		
		try {
			itr.next();
			 fail("testAbstractCollectionIterator isn't implemented correctly");
		    } catch(NoSuchElementException e) {
		    	// Sonarlint : Silly equality checks should not be made (java:S2159)
		    	if (e.getMessage() != null) {
		    		fail("testAbstractCollectionIterator isn't implemented correctly");
		    	}
		    	 
		    }
		System.out.println("Test : Ok");
	}


	@Test
	public void testAbstractCollectionAddE() {
		System.out.println("testAbstractCollectionAddE");
		AbstractCollection<Object> ac = new ArrayList<>();
		ac.add("5");
		// Sonarlint : Silly equality checks should not be made (java:S2159)
		// Sonarlint : Strings and Boxed types should be compared using "equals()" (java:S4973)
		if (!ac.toArray()[0].toString().equals("5") && ac.size() != 1) {
			fail("testAbstractCollectionAddE isn't implemented correctly");
			}
		System.out.println("Test : Ok");
		}

	@Test
	public void testAbstractCollectionRemove() {
		System.out.println("testAbstractCollectionRemove");
		AbstractCollection<Object> ac = new ArrayList<>();
		ac.add("5");
		ac.add("10");
		ac.remove("5");
		if (!ac.toArray()[0].toString().equals("5") && ac.size() != 1) {
			fail("testAbstractCollectionRemove isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}
	
	@Test
	public void testIteratorHasNext() {
		System.out.println("testIteratorHasNext");
		AbstractCollection<Object> ac = new ArrayList<>();
		Iterator<Object> itr = ac.iterator();
		ac.add("5");
		ac.add("10");
		if (!itr.hasNext()) {
			fail("testIteratorhasNext isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}
	
	@Test
	public void testIteratorNext() {
		System.out.println("testIteratorNext");
		AbstractCollection<Object> ac = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ac.add(i);
		}
		Iterator<Object> itr = ac.iterator();
		for (int i = 0; i < 10; i++) {
			if (itr.next() == null) {
				fail("testIteratorNext isn't implemented correctly");
			}
		}
		try {
			itr.next();
			 fail("testIteratorNext isn't implemented correctly");
		    } catch(NoSuchElementException e) {

		    	// Sonarlint : java:S4973
		    	// Sonarlint : Silly equality checks should not be made (java:S2159)
		    	if (e.getMessage() != null) {
		    		fail("testIteratorNext isn't implemented correctly");
		    	}
		    	 
		    }
		System.out.println("Test : Ok");
	}
	
	@Test
	public void testIteratorRemove() {
		System.out.println("testIteratorRemove");
		AbstractCollection<Object> ac = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			ac.add(i);
		}
		Iterator<Object> itr = ac.iterator();
		
		for (int i = 0; i < 10; i++) {
			itr.next();
			itr.remove();
		}
		if (!ac.isEmpty()) {
			fail("testIteratorRemove isn't implemented correctly");
		}
		
		System.out.println("Test : Ok");
		
	}


}
