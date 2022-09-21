package datastruct;

public interface Table {
	/***
	 * He selects a tuple with his key.
	 * @param key - It's a string
	 * @return He returns the object of his data
	 */
	public Object select (String key);
	
	/***
	 * He put a new tuple with his key and his data.
	 * @param key - It's a string
	 * @param data - It's an object for the creation an element
	 * @return He returns a boolean if the key and the data has been insert in the HashTable
	 */
	public boolean insert (String key, Object data);
	
	/**
	 * This method removes a tuple with his key
	 * @param key is a string
	 * @return if the current has been remove he returns true.
	 */
	public boolean delete ( String key );
}
