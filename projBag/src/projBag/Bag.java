package projBag;

import java.util.AbstractCollection;
import java.util.Iterator;

public class Bag<E> extends AbstractCollection<E> { 
	private int size;
	private Bag<E>.Element sentinel;
	
	public Bag() {
		this.sentinel = new Element(null,null);
		this.size = 0;
	}
	
	
	public int size() {
		return this.size;
	}
	//c
	
	
	public Iterator<E> iterator() {
		return new Itr();
 }
	
	private class Element {
		Element next; 
		E data; 
		
		Element (E data, Element next ) {
			this.data = data;
			this.next = next;
		}
	}
	
	private class Itr implements Iterator<E> {
		Element current; 
		Element pastCurrent;
		
		
		Itr() {
			this.current = pastCurrent = Bag.this.sentinel;
		}

		@Override
		public boolean hasNext() {
			return (this.current.next != Bag.this.sentinel);
		}

		@Override
		public E next() {
			E data = null;
			if (hasNext()) {
				this.pastCurrent = this.current;
				this.current = this.current.next;
				data = this.current.data;
			}
			return data;
		}
		
	}



}
