package datastruct;

public interface List {
	public void insert ( Object data );
	public boolean delete ();
	public boolean contains ( Object data);
	public void add (int index, Object data );
	public Object getValue();
	public void setValue (Object newData);
	public boolean isEmpty ();
	public int getSize ();
}

