package projBag;


import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<E> extends AbstractCollection<E> { 
	private int size;
	private Element sentinel;
	
	public Bag() {
		this.sentinel = new Element(null,this.sentinel);
		this.size = 0;
	}
	
	
	public int size() {
		return this.size;
	}
	
	public boolean add(E data) {
		if(this.size >= Integer.MAX_VALUE) {
			return false;
		}
		
		// java:S2140 - > Methods of "Random" that return floating point values should not be used in random integer generation
		int index = (int) (Math.random() * (this.size + 1));
		Element current = getElement(index);
		
		if (size == 0) {
			current = new Element(data, this.sentinel);
			this.sentinel.next = current;
		} else {
			Element newCurrentNext = current.next;
			current.next = new Element(data, newCurrentNext);
		}
		this.size++;
		return true;
		
	}
	
	
	public Bag(Collection<E> c) {
		this.sentinel = new Element(null,this.sentinel);
		this.size = 0;
		for (E element : c) {
			this.add(element);
		}
		
	}
	
	

	
	private Element getElement(int index)  throws IndexOutOfBoundsException,NullPointerException {
		if (index < 0 || index > this.size) throw new IndexOutOfBoundsException();
		Element e = this.sentinel;
		for (int i = 0; i<index; i++) {
				e = e.next;
			}
		if (e == null && e != this.sentinel) throw new NullPointerException();
		
		return e;
	}

	
	
	public Iterator<E> iterator() {
		return new Itr();
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
		
		
		Itr() {
			this.current = pastCurrent = Bag.this.sentinel;
		}

		@Override
		public boolean hasNext() {
			return (this.current.next != Bag.this.sentinel);
		}
		
		// Sonarlint : java:S2272 -> "Iterator.next()" methods should throw "NoSuchElementException"
		@Override
		public E next() throws NoSuchElementException {
			E data = null;
			if (hasNext()) {
				this.pastCurrent = this.current;
				this.current = this.current.next;
				data = this.current.data;
			} else throw new NoSuchElementException();
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
