package datastruct;


/***
 * 
 * @author Michaël BESILY
 *
 */
public class HashTable implements Table {
	private int nbTuples;
	private Tuple[] table; 
	private final int TAILLE = 10;
	
	/**
	 * It is a data structure that allows a key-value association, 
	 * i.e. an implementation of the abstract associative array type
	 */
	public HashTable () {
		this.table = new Tuple[TAILLE];
		this.nbTuples = 0;
	}
	
	
	
	/***
	 * He selects a tuple with his key.
	 * @param key - It's a string
	 * @param data - It's an object for the creation an element
	 * @throws IllegalArgumentException if the data or/and the key is null.
	 */
	@Override
	public Object select(String key) throws IllegalArgumentException {
		Object data = null;
		if(key == null) throw new IllegalArgumentException("The key or/and the data is null");
		
		if (!table[computeIndex(key)].sameKey(key)) {
			int iKey = circularSearch(key,computeIndex(key));
			if (iKey != -1) {
				data = table[iKey].data;
			}
		} else {
			data = table[computeIndex(key)].data;
		}
		invariant();
		return data;
	}
	
	
	/***
	 * He put a new tuple with his key and his data.
	 * @param key - It's a string
	 * @param data - It's an object for the creation an element
	 * @throws IllegalArgumentException if the data is null.
	 * @return He returns a boolean if the key and the data has been insert in the HashTable
	 */
	
	@Override
	public boolean insert(String key, Object data) throws IllegalArgumentException {
		if(key == null || data == null) throw new IllegalArgumentException("The key or/and the data is null");
		boolean ans = false;
		
		if ((this.nbTuples <= TAILLE || select(key) != null)) {
			
			int index =  computeIndex(key);
			final int DEBINDEX = index;
			
			
			if (table[index] != null && !ans) {
				boolean blockLoop = false;
				for (int i = index+1; i < this.nbTuples; i++) {
					// java:S2583 : Conditionally executed code should be reachable
					if (!blockLoop) {
							if (i == DEBINDEX && table[i] != null) {
								blockLoop = true;
								
							} else if (table[i].sameKey(key) && table[i].data.equals(data)) {
								blockLoop = true;
								
							}  
							// java:S1871 -> Two branches in a conditional structure 
							// should not have exactly the same implementation 
							else if(table[i] == null) {
								table[i] = new Tuple(key,data);
								ans = true;
								
							} else if (i >= this.nbTuples-1) {
								i = 0;
							}
					}
				}
			} else {
				if (!ans) {
					ans = true;
					this.nbTuples++;
					table[index] = new Tuple(key,data);
				}
				
			}
		}
		invariant();
		return ans;
	}
	
	/**
	 * This method removes a tuple with his key
	 * @param key is a string
	 * @return if the current has been remove he returns true.
	 * @throws IllegalArgumentException if the key is null.
	 */
	
	@Override
	public boolean delete(String key) throws IllegalArgumentException {
		if (key == null) throw new IllegalArgumentException("The key is null");
		boolean ans = false;
		
		if (this.nbTuples != 0) {
			int iKey = circularSearch(key,0);
			if (iKey != -1) {
				table[iKey] = null;
				ans = true;
				this.nbTuples--;
			}
		}
		invariant();
		return ans;
	}
	
	
	/**
	 * @return This method gives the size of the HashTable.
	 */
	public int getNbTuples () {
		return this.nbTuples;
	}
	
	
	/** 
	 * @return He returns all elements of the HashTable with his value and his key in a string type.
	 */
	
	public String toString() {
		StringBuilder concat = new StringBuilder(); // Sonarlint : java:S1643
		
		for (int i=0; i < this.nbTuples;i++) {
			concat.append(table[i].toString() + "\n");
		}
		invariant();
		return concat.toString();
	} 
	
	/** The search on the table is circular and starts at (index + 1) and ends at index - 1
	 * 
	 * @param key is a string
	 * @throws IllegalArgumentException if the ASCII Value is superior of the size of the HashTable
	 * @return He returns the index of the hashTable when he sees the desired tuple (or -1 if he doesn't find it)
	 */
	private int computeIndex(String key) throws IllegalArgumentException {
		int count = 0;
		for(int i = 0; i < key.length(); i++) {
			// java:S1905 : Redundant casts should not be used 
			count += key.charAt(i);	
		}
		count = count%TAILLE;
		if (count > TAILLE) 
			throw new IllegalArgumentException("The ASCII value is superior to the size of the HashTable");

		invariant();
		return count;
	}
	
	/** The search on the table is circular and starts at (index + 1) and ends at index - 1
	 * 
	 * @param key is a string
	 * @param indice is an integer
	 * @return He returns the index of the hashTable when he sees the desired tuple (or -1 if he doesn't find it)
	 */
	private int circularSearch (String key, int indice) {
		final int DEBINDEX = computeIndex(key);
		int ans = computeIndex(key);
		boolean blockLoop = false;
		if (!table[DEBINDEX].sameKey(key)) {
			for (int i = indice+1; i < this.nbTuples; i++) {
				if (!blockLoop) {
					if (table[i].sameKey(key)) {
						ans = i;
						blockLoop = true;
					} else if (i == DEBINDEX && table[i] != null) {
							ans = -1;
							blockLoop = true;
						} else if (i >= this.nbTuples-1) {
							i = 0;
						}
				}
			}
		}
		invariant();
		return ans;
	}


	/***
	 * @return He returns an boolean if the method finds an issue on each method.
	 * @throws IllegalArgumentException. If the method find errors in the constructor he returns an IllegalArgumentException.
	 */
	private boolean invariant() throws IllegalArgumentException {
		// Sonarlint : java:S3973
		boolean res = true;
		if (this.TAILLE != 10 || this.nbTuples > this.TAILLE || this.table.length != this.TAILLE) {
			res = !res;
		}
		if (!res) throw new IllegalArgumentException("Constructor Error");
		else return res;
	}
	
	
	private class Tuple {
		String key ; 
		Object data ; 
		
		
		/** 
		 * @param key is a string
		 * @param data is a object
		 */
		Tuple (String key, Object data ) {
			this.key = key;
			this.data = data;
			invariant();
			}
		
		/** 
		 * @return He returns a boolean if they key is the same of otherKey.
		 * @param otherKey is a string
		 */
		
		boolean sameKey (String otherKey) {
			// java:S4973 : Major -> Strings and Boxed types should be compared using "equals()" 
			// java:S1858 : Minor -> "toString()" should never be called on a String object
			invariant();
			return this.key.equals(otherKey);
		}
		
		/** 
		 * @return He returns the key and value of a tuple in a string type.
		 */
		public String toString() {
			invariant();
			return "key : "+  this.key + ", data : " + this.data;
		}
		
		/***
		 * @return He returns an boolean if the method finds an issue on each method.
		 * @throws IllegalArgumentException. If the method find errors in the constructor he returns an IllegalArgumentException.
		 */
		private boolean invariant() throws IllegalArgumentException {
			// Sonarlint : java:S3973
			boolean res = true;
			if (this.key == null || this.data == null) {
				res = !res;
			}
			if (!res) throw new IllegalArgumentException("Constructor Error");
			else return res;
		}
		
}

		
		

}
