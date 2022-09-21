package datastruct;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class LinkedListTest {
	// Sonarlint : java:S116
	LinkedList ll;

	@Before
	public void setUp() throws Exception {
		System.out.println("Test : setUp");
		ll = new LinkedList();
		System.out.println("Test : Ok");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test : tearDown");
		ll = new LinkedList();
		ll = null;
		System.out.println("Test : Ok");
	}
	
	@Test
	public void testLinkedList() {
		System.out.println("Test : testLinkedList");
		ll = new LinkedList();
		// Sonarlint : java:S1125
		if (ll.next() || ll.previous() || ll.getSize() != 0) {
			fail("LinkedList isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testGoToHead() {
		System.out.println("Test : testGoToHead");
		ll = new LinkedList();
		Object data = new Object();
		for (int i = 0; i < 10; i++) {
			ll.insert(data);
		}
		ll.goToHead();
		if (ll.getSize() != 10 && ll.previous() != false && ll.next() != true) {
			fail("testGoToHead isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testGoToEnd() {
		System.out.println("Test : testGoToEnd");
		ll = new LinkedList();
		Object data = new Object();
		for (int i = 0; i < 10; i++) {
			ll.insert(data);
		}
		ll.goToEnd();
		if (ll.getSize() != 10 && ll.previous() != true && ll.next() != false) {
			fail("testGoToEnd isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testNext() {
		System.out.println("Test : testNext");
		ll = new LinkedList();
		int n = 10;
		for (int i = 0; i < n; i++) {
			Object data = new Object();
			ll.insert(data);
		}
		ll.goToHead();
		
		for (int i = 0; i < n-1; i++) {
			// Sonarlint : java:S1125
;			if(!ll.next()) {
				fail("testNext isn't implemented correctly");
			}
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testPrevious() {
		System.out.println("Test : testPrevious");
		ll = new LinkedList();
		int n = 10;
		for (int i = 0; i < n; i++) {
			Object data = new Object();
			ll.insert(data);
		}
		ll.goToEnd();
		
		for (int i = 0; i < n-1; i++) {
			//Sonarlint : java:S1125
;			if(!ll.previous()) {
				fail("testPrevious isn't implemented correctly");
			}
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testToString() {
		System.out.println("Test : testToString");
		ll = new LinkedList();
		StringBuilder concat = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			Object data = new Object();
			ll.insert(data);
			ll.setValue(data);
			concat.append("elem" + i + " : " + data + "\n");
		}
		
		if(concat.toString().equals(ll.toString())) {
			fail("testToString isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testGetValueAt() {
		System.out.println("Test : testGetValueAt");
		ll = new LinkedList();
		ArrayList<Object> tabData = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Object data = new Object();
			ll.insert(data);
			ll.previous();
			ll.setValue(data);
			tabData.add(data);
		}
		Collections.reverse(tabData);
		if (tabData.get(3) != ll.getValueAt(3)) {
			fail("testGetValueAt isn't implemented correctly");
		}
		
	    try {
	    	ll.getValueAt(-3);
	    	fail("testGetValueAt isn't implemented correctly");
	    } catch(IllegalArgumentException e) {
	        //if execution reaches here, 
	        //it indicates this exception was occured.
	        //so we need not handle it.
	    	IllegalArgumentException wantedError = new IllegalArgumentException("Index Error");
	    	
	    	// Sonarlint : java:S4973
	    	 if (!e.getMessage().equals(wantedError.getMessage())) {
	    		 fail("testGetValueAt isn't implemented correctly");
	    	 }
	    }
	    
	    
		System.out.println("Test : Ok");
	}

	@Test
	public void testInsert() {
		System.out.println("Test : testInsert");
		ll = new LinkedList();
		int n = 10;
		ArrayList<Object> tabData = new ArrayList<>();
		ArrayList<Object> tabll = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Object data = new Object();
			ll.insert(data);
			ll.previous();
			ll.setValue(data);
			tabData.add(data);
		}
		Collections.reverse(tabData);
		
		for (int i = 0; i < n; i++) { 
			tabll.add(ll.getValueAt(i));
		}
		for (int i = 0; i < n; i++) {
			if(tabData.get(i) != ll.getValueAt(i) && ll.getValueAt(i) != null ) {
				fail("testInsert isn't implemented correctly");
			}
			ll.next();
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testDelete() {
		System.out.println("Test : testDelete");
		ll = new LinkedList();
		// Sonarlint : java:S3973
		if (ll.delete()) {
			fail("testDelete isn't implemented correctly");
		}
		int n = 10;
		int nDelete = 3;
		ArrayList<Object> tabData = new ArrayList<>();
		ArrayList<Object> tabll = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Object data = new Object();
			ll.insert(data);
			ll.previous();
			ll.setValue(data);
			tabData.add(data);
		}
		Collections.reverse(tabData);
		
		
		for (int i = 0; i < n; i++) { 
			tabll.add(ll.getValueAt(i));
		}
		
		for (int i = 0; i < nDelete; i++) { 
			ll.delete();
		}
		
		for (int i = 0; i < n-nDelete; i++) {
			if(tabData.get(i) != ll.getValueAt(i) && ll.getValueAt(i) != null ) {
				fail("testDelete isn't implemented correctly");
			}
			ll.next();
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testContains() {
		System.out.println("Test : testContains");
		final String res = "testContains isn't implemented correctly";
		ll = new LinkedList();
		int n = 3;
		ArrayList<Object> tabData = new ArrayList<>();
		ArrayList<Object> tabFakeData = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Object data = new Object();
			ll.insert(data);
			ll.previous();
			ll.setValue(data);
			tabData.add(data);
		}
		
		for (int i = 0; i < n; i++) {
			Object data = new Object();
			tabFakeData.add(data);
		}
		
		for (int i = 0; i < n; i++) {
			if(!ll.contains(tabData.get(i)) || ll.contains(tabFakeData.get(i)) ) {
				// Sonarlint : java:S1192
				fail(res);
			}
			ll.next();
		}
		

	    try {
	    	ll.contains(null);
	    	fail(res);
	    } catch(IllegalArgumentException e) {
	    	IllegalArgumentException wantedError = new IllegalArgumentException("data is null");
	    	
	    	// Sonarlint : java:S4973
	    	 if (!e.getMessage().equals(wantedError.getMessage())) {
	    		 fail(res);
	    	 }
	    }
		System.out.println("Test : Ok");
	}

	@Test
	public void testAdd() {
		System.out.println("Test : testAdd");
		final String res = "testAdd isn't implemented correctly";
		ll = new LinkedList();
		Object data = new Object();
		
		
		for (int i = 0; i < 10; i++) {
			ll.insert(data);
		}
		
		
		for (int i = 0; i < 10; i++) {
			ll.add(i, data);
		}
		if (ll.getSize() != 20) {
			fail(res);
		}
		
		try {
	    	ll.add(3,null);
	    	fail("testAdd isn't implemented correctly");
	    } catch(IllegalArgumentException e) {
	    	IllegalArgumentException wantedError = new IllegalArgumentException("data is null");
	    	
	    	// Sonarlint : java:S4973
	    	 if (!e.getMessage().equals(wantedError.getMessage())) {
	    		 fail(res);
	    	 }
	    }
		
		try {
	    	ll.add(-5,data);
	    	fail(res);
	    } catch(IndexOutOfBoundsException e) {
	    	IndexOutOfBoundsException wantedError = new IndexOutOfBoundsException("index is out of the LinkedList");
	    	
	    	// Sonarlint : java:S4973
	    	 if (!e.getMessage().equals(wantedError.getMessage())) {
	    		 fail(res);
	    	 }
	    }
		System.out.println("Test : Ok");
	}

	@Test
	public void testGetValue() {
		System.out.println("Test : testGetValue");
		ll = new LinkedList();
		int n = 4;
		ArrayList<Object> tabData = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Object data = new Object();
			ll.insert(data);
			ll.previous();
			ll.setValue(data);
			tabData.add(data);
		}
		Collections.reverse(tabData);
		ll.goToHead();
		for (int i = 0; i < n; i++) {
			if(tabData.get(i) != ll.getValue() && ll.getValue() != null ) {
				fail("testGetValue isn't implemented correctly");
			}
			ll.next();
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testSetValue() {
		System.out.println("Test : testSetValue");
		final String res = "testSetValue isn't implemented correctly";
		ll = new LinkedList();
		int n = 10;
		for (int i = 0; i < n; i++) {
			Object data = new Object();
			ll.insert(data);
			ll.previous();
			ll.setValue(data);
			if (ll.getValue() != data && ll.getValue() != null) {
				fail(res);
			}
		}
		

	    try {
	    	ll.setValue(null);
	    	fail(res);
	    } catch(IllegalArgumentException e) {
	        //if execution reaches here, 
	        //it indicates this exception was occured.
	        //so we need not handle it.
	    	IllegalArgumentException wantedError = new IllegalArgumentException("data is null");
	    	
	    	// Sonarlint : java:S4973
	    	 if (!e.getMessage().equals(wantedError.getMessage())) {
	    		 fail(res);
	    	 }
	    	 
	    	 
		System.out.println("Test : Ok");
	}
	}

	@Test
	public void testIsEmpty() {
		System.out.println("Test : testIsEmpty");
		ll = new LinkedList();
		if (ll.getSize() != 0) {
			fail("testIsEmpty isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}

	@Test
	public void testGetSize() {
		System.out.println("Test : testGetSize");
		ll = new LinkedList();
		
		Object data = new Object();
		for (int i = 0; i < 10; i++) {
			ll.insert(data);
			
		}
		
		if (ll.getSize() != 10) {
			fail("testGetSize isn't implemented correctly");
		}
		System.out.println("Test : Ok");
	}
	

}
