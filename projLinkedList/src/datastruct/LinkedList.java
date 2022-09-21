package datastruct;

/**
 * @author Michaël Besily
 */

public class LinkedList implements List {
	
	private Element sentinel ; 
	private Element current ; 
	private int size;
	private final String dataNull = "data is null";
	
	
	public LinkedList () {
		this.sentinel = new Element(this.sentinel,this.sentinel,null);
		this.current = this.sentinel;
		this.size = 0;
	}
	

	/**
	 * He moves the current element at first element of LinkedList
	 */
	public void goToHead() {
		while (this.current.prev != this.sentinel) {
			previous();
		}
		} 
	/**
	 * He moves the current element at last element of LinkedList
	 */
	public void goToEnd() {
		while (this.current.next != this.sentinel) {
			next();
		}
		} 
	
	/**
	 * He moves the current element in next	current.
	 * @return If the current.next is null or it's the sentinel he returns false else he returns true
	 * @throws IllegalArgumentException if The new current has a previous element who is null or it's linked with the sentinel.
	 */
	public boolean next() throws IllegalArgumentException {
		boolean res = false;
		if (this.current.next==null || this.current.next == this.sentinel ) {
			res = false;
		} else {
			Element nCurrent = this.current.next;
			nCurrent.prev = this.current;
			this.current = nCurrent;
			res = true;
			// Sonarlint : java:S2589
			if (current == this.sentinel) throw new IllegalArgumentException("The new current is the sentinel");
		}
		invariant();
		return res;
	} 
	
	/**
	 * He moves the current element in previous	current.
	 * @return If the current.prev is null or it's the sentinel he returns false else he returns true.
	 * @throws IllegalArgumentException if The new current has a previous element who is null or it's linked with the sentinel.
	 */
	public boolean previous() throws IllegalArgumentException {
		boolean res = false;
		if (this.current.prev==null || this.current.prev == this.sentinel) {
			res = false;
		} else {
			Element pCurrent = this.current.prev;
			pCurrent.next = this.current;
			this.current = pCurrent;
			res = true;
			// Sonarlint : java:S2589
			if (current == this.sentinel) throw new IllegalArgumentException("The new current is the sentinel");
		}
		invariant();
		return res;
		
	}
	/** 
	 * @return He returns all elements of the LinkedList with his value with a string type.
	 */
	public String toString() {
		StringBuilder concat = new StringBuilder(); // Sonarlint : java:S1643
		goToHead();
		for (int i=0; i < this.size;i++) {
			concat.append("elem" + i + " : " + getValueAt(i) + "\n");
		}
		invariant();
		return concat.toString();
	} 
	
	/**
	 * He gets the value of the index.
	 * @param The index is the position of the new current
	 * @return He returns an object. It's the value of the new current
	 * @throws IllegalArgumentException If the index is inferior to 0 or superior to the maximum of the linkedlist, he returns an error.
	 */
	public Object getValueAt(int index) throws IllegalArgumentException {
		goToHead();
		if (index < 0 || index > this.size) throw new IllegalArgumentException("Index Error");
		
		for (int i=0; i < index;i++) {
			next();
			
		}
		invariant();
		return current.theValue;
	} 


	/***
	 * He put a new element in the preview current.
	 * @param data - It's an object for the creation an element
	 * @throws IllegalArgumentException if the data is null.
	 */
	@Override
	public void insert(Object data) throws IllegalArgumentException {
		// Sonarlint : java:S1192
		if (data == null) throw new IllegalArgumentException(dataNull);
		
		if (this.size == 0) {
			this.current = new Element(this.sentinel,this.sentinel,data);
		}  
		else {
			if (current.prev == this.sentinel) {
				Element newE = new Element(this.sentinel,current,data);
				this.current.prev = newE;
			} else {
				Element pCurrent = current.prev;
				Element newE = new Element(pCurrent,current,data);
				newE.next = current;
				current.prev = newE;
			}	
			
		}
		invariant();
		size++;
		
	}
	
	
	/**
	 * This method removes the current element.
	 * @return if the current has been remove he returns true.
	 */
	@Override
	public boolean delete() {
		boolean ans = false;
		if (this.isEmpty()) {
			ans = false;
		} else {
			current.next.prev = current.prev;
			current = current.prev;
			current.next = current.next.next;
			ans = true;
		}
		invariant();
		return ans;
	}
	
	
	/**
	 * @param data - It's an object to find on the LinkedList.
	 * @throws IllegalArgumentException if the data is null.
	 * @return if the data has been find he returns true. Else he returns false.
	 */
	@Override
	public boolean contains(Object data) throws IllegalArgumentException {
		if (data == null) throw new IllegalArgumentException(dataNull);
		
		goToHead();
		while (current != this.sentinel) {
			if (current.theValue == data) {
				return true;
			}
			else {
				if (!next()) return false;
			}
		}
		invariant();
		return false;
	}
	
	
	/**
	 * He inserts an element in the position of the index.
	 * @param Index of the position in LinkedList.
	 * @param Data - It's an object to add on the LinkedList.
	 * @throws IndexOutOfBoundsException if the index is outside of the LinkedList.
	 * @throws IllegalArgumentException if the data is null.
	 */
	@Override
	public void add(int index, Object data) throws IndexOutOfBoundsException,IllegalArgumentException {
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException("index is out of the LinkedList");
		} else if (data == null) throw new IllegalArgumentException(dataNull);
			else {
			goToHead();
			for (int i = 0; i < index; i++) {
				next();
			}
			insert(data);
		}
		invariant();
		
		
	}
	
	
	/**
	 * @return He returns the value of the current element.
	 */
	@Override
	public Object getValue() {
		return this.current.theValue;
	}

	
	/**
	 * @param newData is an object
	 * @throws IllegalArgumentException if the data is null
	 */
	@Override
	public void setValue(Object newData) throws IllegalArgumentException {
		if (newData == null)  throw new IllegalArgumentException(dataNull);
		this.current.theValue = newData;

	}
	
	
	/**
	 * @return He returns a boolean if the LinkedList is empty or not.
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	
	/**
	 * @return This method gives the size of the LinkedList.
	 */
	@Override
	public int getSize() {
		return this.size;
	}
	
	
	/***
	 * @return He returns an boolean if the method finds an issue on each method.
	 * @throws IllegalArgumentException. If the method find errors in the constructor he returns an IllegalArgumentException.
	 */
	private boolean invariant() throws IllegalArgumentException {
		// Sonarlint : java:S3973
		boolean res = true;
		if (this.sentinel.theValue != null || this.current == null || this.size < 0) {
			res = !res;
		}
		if (!res) throw new IllegalArgumentException("Constructor Error");
		else return res;
	}
	
	private class Element {
		Element prev ;
		Element next ;
		Object theValue ; 
		
		/**
		 * @param prev is the previous element of the current
		 * @param next is the next element of the current
		 * @param data is the data of the current element
		 */
		Element ( Element prev, Element next, Object data ) {
			this.prev = prev;
			this.next = next;
			this.theValue = data;
		}
	}

}
