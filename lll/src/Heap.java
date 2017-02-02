import java.util.*;

class LList implements Comparable{
	String name;
	int listlength = Integer.MAX_VALUE;
	LList next;
	
	public LList(String name){
		this.name = name;
	}
	
	public LList(String name, int list_length, LList next){
		this.name = name;
		this.listlength = listlength;
		this.next = next;
	}
	//,((LList)that).list_length
	
	public int compareTo(Object check){
		if(this.listlength < ((LList)check).listlength) {
			return -1;
		}
		
		else if( this.listlength > ((LList)check).listlength) {
			return 1;
		}
		
		else { return 0; }
	//return Integer.compareTo(list_length,((LList)that));
		
	}
	
	public boolean equals(Object check){
		if (check != null){
			if (name.equals(((LList)check).name)){
				return true;
			}
			return false;
		}
		return false;
	}
}

public class Heap {
private ArrayList<LList> arr;
	
	public Heap(){
		arr = new ArrayList<LList>();
	}
	
	private void siftUp() {
		int n = arr.size() - 1;
		while (n > 0) {
			int p = (n-1)/2;
			LList item = arr.get(n);
			LList parent = arr.get(p);
			if (item.compareTo(parent) < 0) {
				// swap
				arr.set(n, parent);
				arr.set(p, item);
				
				// move up one level
				n = p;
			} else {
				break;
			}
		}
	}
	
	public boolean contains(LList item){
		return arr.contains(item);
	}
	public void insert(LList item) {
		arr.add(item);
		siftUp();
	}
	
	public void replace(LList item) {
		for (int i = 0; i < arr.size(); i++){
			if (arr.get(i).name.equals(item.name)){
				int c = (arr.get(i).compareTo(item));
				if (c > 0){
					arr.set(i, item);
					break;
				}
			}
		}
	}
	
	private void siftDown() {
		int n = 0;
		int l = 2*n+1;
		while (l < arr.size()) {
			int min=l, r=l+1;
			if (r < arr.size()) { // there is a right child
				if (arr.get(r).compareTo(arr.get(l)) < 0) {
					min++;
				}
			}
			if (arr.get(n).compareTo(arr.get(min)) > 0) {
					// switch
					LList temp = arr.get(n);
					arr.set(n, arr.get(min));
					arr.set(min, temp);
					n = min;
					l = 2*n+1;
			} else {
				break;
			}
		}
	}
	
	public LList delete() 
	throws NoSuchElementException {
		if (arr.size() == 0) {
			throw new NoSuchElementException();
		}
		if (arr.size() == 1) {
			return arr.remove(0);
		}
		LList keep = arr.get(0);
		arr.set(0, arr.remove(arr.size()-1));
		siftDown();
		return keep;
	}

	public int size() {
		return arr.size();
	}
	
	public boolean isEmpty() {
		return arr.isEmpty();
		
	}
	
	public String toString() {
		return arr.toString();
	}

}
