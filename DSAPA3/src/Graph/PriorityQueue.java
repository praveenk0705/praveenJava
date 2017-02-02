package Graph;

public interface PriorityQueue {
public interface Position {

	Comparable getValue();
	}
	
	Position insert(Comparable x);
	
	Comparable findMin();
	
	Comparable deleteMin();
	
	boolean isEmpty();
	
	void makeEmpty();
	
	int size();
	
	void decreaseKey(Position p, Comparable newVal);
	}