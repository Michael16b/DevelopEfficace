package datastruct;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HashTableTest {
	HashTable ht = new HashTable();
	
	@Before
	public void setUp() throws Exception {
		ht = new HashTable();
	}

	@After
	public void tearDown() throws Exception {
		ht = new HashTable();
		ht = null;
	}

	@Test
	public void testHashTable() {
		System.out.println("testHashTable");
		ht = new HashTable();
		if(ht.getNbTuples() != 0) {
			fail("testHashTable isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}
	

	
	@Test
	public void testToString() {
		System.out.println("testToString");
		ht = new HashTable();
		StringBuilder concat = new StringBuilder();
		
		ArrayList<Object> tabKey = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			tabKey.add("Hola"+i);
		}
		
		
		for (int i = 0; i < 10; i++) {
			Object data = new Object();
			ht.insert(tabKey.get(i).toString(),data);
			concat.append("key : "+  tabKey.get(i).toString() + ", data : " + data + "\n");
		}
		
		if(concat.toString().equals(ht.toString())) {
			fail("testToString isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}
	

	@Test
	public void testSelect() {
		System.out.println("testSelect");
		ht = new HashTable();
		ArrayList<Object> tabKey = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			tabKey.add("bonjour"+i);
		}
		
		
		for (int i = 0; i < 10; i++) {
			Object data = new Object();
			ht.insert(tabKey.get(i).toString(),data);
		}
		
		for (int i = 0; i < 10; i++) {
			if (ht.select(tabKey.get(i).toString()) == null) {
				fail("testSelect isn't implemented correctly");
			}
		}
		
		
			if (ht.select("¯\\_(ツ)_/¯") != null) {
				fail("testSelect isn't implemented correctly");
			}
			
			 try {
				 ht.select(null);
				 fail("testSelect isn't implemented correctly");
			    } catch(IllegalArgumentException e) {
			    	IllegalArgumentException wantedError = new IllegalArgumentException("The key or/and the data is null");
			    	// Sonarlint : java:S4973
			    	 if (!e.getMessage().equals(wantedError.getMessage())) {
			    		 fail("testSelect isn't implemented correctly");
			    	 }
			    }
			 System.out.println("Test : Ok");
	    
	    }
		
		
		

	@Test
	public void testInsert() {
		System.out.println("testInsert");
		ht = new HashTable();
		ArrayList<Object> tabKey = new ArrayList<>();
		
		for (int i = 0; i < 12; i++) {
			tabKey.add("coucou"+i);
		}
		
		
		for (int i = 0; i < 12; i++) {
			Object data = new Object();
			// java:S1125 -> Boolean literals should not be redundant
			if (i < 10) {
				if (!ht.insert(tabKey.get(i).toString(),data)) {
					fail("testInsert isn't implemented correctly");
				}
			} else {
				if (ht.insert(tabKey.get(i).toString(),data)) {
					fail("testInsert isn't implemented correctly");
				}
			}
		}
	
		
		if(ht.getNbTuples() != 10) {
			fail("testInsert isn't implemented correctly");
		}
		
		// 
		
		 try {
			 ht.insert(null,null);
			 fail("testInsert isn't implemented correctly");
		    } catch(IllegalArgumentException e) {
		    	IllegalArgumentException wantedError = new IllegalArgumentException("The key or/and the data is null");
		    	// Sonarlint : java:S4973
		    	 if (!e.getMessage().equals(wantedError.getMessage())) {
		    		 fail("testInsert isn't implemented correctly");
		    	 }
		    }
		 System.out.println("Test : Ok");
	}

	@Test
	public void testDelete() {
		System.out.println("testDelete");
		ht = new HashTable();
		ArrayList<Object> tabKey = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			tabKey.add("bonjour"+i);
		}
		
		
		
		for (int i = 0; i < 10; i++) {
			Object data = new Object();
			ht.insert(tabKey.get(i).toString(),data);
		}
		
		for (int i = 0; i < 10; i++) {
			ht.delete(tabKey.get(i).toString());
			if(ht.getNbTuples() != 10-(i+1)) {
				fail("testDelete isn't implemented correctly");
			}
		}
		if (ht.delete(tabKey.get(0).toString())) {
			fail("testDelete isn't implemented correctly");
		}
		
		 try {
			 ht.delete(null);
			 fail("testDelete isn't implemented correctly");
		    } catch(IllegalArgumentException e) {
		    	IllegalArgumentException wantedError = new IllegalArgumentException("The key is null");
		    	// Sonarlint : java:S4973
		    	 if (!e.getMessage().equals(wantedError.getMessage())) {
		    		 fail("testDelete isn't implemented correctly");
		    	 }
		    }
		 System.out.println("Test : Ok");
	}

	@Test
	public void testGetNbTuples() {
		System.out.println("testDelete");
		ht = new HashTable();
		Object data = new Object();
		ht.insert("coucou", data);
		if (ht.getNbTuples() != 1) {
			fail("testGetNbTuples isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}

}
