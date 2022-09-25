package projBag;


import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


/***
 * 
 * @author Micha�l BESILY
 *
 */

public class Bag<E> extends AbstractCollection<E> { 
	private int size;
	private Element sentinel;

	
	/**
	 * It's a collection in which the data to be stored are inserted in any order.
	 * 	There is no notion of primary key (in the database sense), so two identical data can coexist in the same collection.
	 */
	public Bag() {
		this.sentinel = new Element(null,this.sentinel);
		this.size = 0;
	}
	
	/**
	 * @return This method gives the size of the Bag.
	 */
	public int size() {
		return this.size;
	}
	
	
	/**
	 * He inserts an element in random position on the bag.
	 * @param Data - It's an object to add on the Bag.
	 * @throws IllegalArgumentException if the data is null.
	 * @throws NullPointerException if the next current or the actual current is null
	 */
	@Override
	public boolean add(E data) throws IllegalArgumentException,NullPointerException  {
		if (data == null) throw new IllegalArgumentException("data is null");
		
		boolean ans = true;
		if(this.size >= Integer.MAX_VALUE) {
			ans = false;
		}
		
		
		if (ans) {
		// java:S2140 - > Methods of "Random" that return floating point values should not be used in random integer generation
			int index = (int) (Math.random() * (this.size + 1));
			Element current = getElement(index);
			
			if (size == 0) {
				current = new Element(data, this.sentinel);
				this.sentinel.next = current;
			} else {
				// Sonarlint: Null pointers should not be dereferenced (java:S2259)
				try {
					Element newCurrentNext = current.next;
					current.next = new Element(data, newCurrentNext);
				} catch(NullPointerException e) {
			    	throw new NullPointerException("Next current or the actual current is null");
				}
			}
			this.size++;
		}
		invariant();
		return ans;
		
	}
	
	/**
	 * It's a collection in which the data to be stored are inserted in any order.
	 * There is no notion of primary key (in the database sense), so two identical data can coexist in the same collection.
	 * With this constructor we can put elements of an Collection<E>
	 */
	public Bag(Collection<E> c) {
		this.sentinel = new Element(null,this.sentinel);
		this.size = 0;
		
		for (E element : c) {
			this.add(element);
		}
		
		invariant();
	}
	
	

	
	private Element getElement(int index)  throws IndexOutOfBoundsException,NullPointerException {
		if (index < 0 || index > this.size) throw new IndexOutOfBoundsException();
		Element e = this.sentinel;
		for (int i = 0; i<index; i++) {
				e = e.next;
			}
		if (e == null && e != this.sentinel) throw new NullPointerException();
		
		
		invariant();
		return e;
	}

	
	/**
	 * Returns a new object of type java.util.Iterator (Java interface).
	 */
	public Iterator<E> iterator() {
		return new Itr();
 }
	
	
	
	/***
	 * @return He returns an boolean if the method finds an issue on each method.
	 * @throws IllegalArgumentException. If the method find errors in the constructor he returns an IllegalArgumentException.
	 */
	private boolean invariant() throws IllegalArgumentException {
		// Sonarlint : java:S3973
		boolean res = true;
		if (this.size < 0 || this.size > Integer.MAX_VALUE) {
			res = !res;
		}
		if (!res) throw new IllegalArgumentException("Constructor Error");
		else return res;
	}
	
	
	
	private class Element {
		private Element next; 
		private E data; 
		
		Element (E data, Element next ) {
			this.data = data;
			this.next = next;
		}
	}
	
	private class Itr implements Iterator<E> {
		private Element current; 
		private Element pastCurrent;
		
		/** 
		 * @param current is an Element
		 * @param pastCurrent is a Element
		 */
		Itr() {
			this.current = pastCurrent = Bag.this.sentinel;
		}
		
		
		/**
		 * It searches if we can pass a next element
		 * @return a boolean
		 */
		@Override
		public boolean hasNext() {
			return (this.current.next != Bag.this.sentinel);
		}
		
		
		
		
		/**
		 * He moves the current element in next	current.
		 * @return If the current.next is null or it's the sentinel he returns false else he returns true
		 * @throws NoSuchElementException if the method didn't find a new next current.
		 */
		@Override
		public E next() throws NoSuchElementException {
			// Sonarlint : java:S2272 -> "Iterator.next()" methods should throw "NoSuchElementException"
			E data = null;
			if (hasNext()) {
				this.pastCurrent = this.current;
				this.current = this.current.next;
				data = this.current.data;
			} else throw new NoSuchElementException();
			invariant();
			return data;
		}
		
		
		/**
		 * This method removes the current element.
		 * @return if the current has been remove he returns true.
		 * @throws NoSuchElementException if the method didn't find an Element in the bag.
		 */
		@Override
		public void remove() throws NoSuchElementException {
			if (size > 0) {
				this.current = this.pastCurrent;
				this.current.next = this.current.next.next;
				Bag.this.size--;
			} else {
				throw new NoSuchElementException("The iterator doesn't have an element to remove");
			}
			invariant();
		}
		
		/***
		 * @return He returns an boolean if the method finds an issue on each method.
		 * @throws IllegalArgumentException. If the method find errors in the constructor he returns an IllegalArgumentException.
		 */
		private boolean invariant() throws IllegalArgumentException {
			// Sonarlint : java:S3973
			boolean res = true;
			if ((this.current == null && this.current != Bag.this.sentinel) || (this.pastCurrent == null && this.pastCurrent != Bag.this.sentinel)) {
				res = !res;
			}
			if (!res) throw new IllegalArgumentException("Constructor Error");
			else return res;
		}
	}



}
