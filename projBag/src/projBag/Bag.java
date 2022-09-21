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
	
	@Override
	public boolean add(E data) {
		if(this.size == Integer.MAX_VALUE) return false;
		else {
			int index = (int) (Math.random() * (this.size + 1));
			Element current = getElement(index);
			current.next = new Element(data, current.next.next);
		}
		return true;
		
	}
	
	private Element getElement(int index)  throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size) throw new IndexOutOfBoundsException();
		Element e = this.sentinel;
		for (int i = 0; i<index; i++) {
				e = e.next;
			}
		if (e == null) throw new NullPointerException();
		
		return e;
	}

	
	
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
		
		@Override
		public void remove() {
			this.current = this.pastCurrent;
			this.current.next = this.current.next.next;
			Bag.this.size--;
		}
		
	}



}
