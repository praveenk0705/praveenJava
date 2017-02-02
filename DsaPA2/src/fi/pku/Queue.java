package fi.pku;

import java.util.NoSuchElementException;

public class Queue implements IQueue {
	protected Node front, end;
	protected int count;
	static protected class Node {
		Object element;
		Node next;
	}
	
	/**
	 * Constructor that creates an empty Queue.
	 */
	public Queue() {
		front = null;
		end = null;
		count = 0;
	}

	@Override
	public void enQueue(Object item) {
		Node n = new Node();
		n.element = item;
		n.next = null;
		if (front != null)
			end.next = n;
		else
			front = n; // if front is null then must be set to new node
		end = n; // end is always the newest node
		count ++;
	}

	@Override
	public Object deQueue() {
		if (isEmptyQueue()) 
			throw new NoSuchElementException("Queue is empty.");
			//return null;
		
		Object item = front.element;
		if (front.next == null)
			end = null; // if there is no next node, then end must be set to null
		front = front.next; // null if no next node exists
		count--;
		return item;
	}

	@Override
	public Object front() {
		return (front == null ? null : front.element);
	}

	@Override
	public int queueSize() {
		return count;
	}

	@Override
	public boolean isEmptyQueue() {
		return (front == null);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Node n = front; n != null; n = n.next) {
			sb.append(n.element);
			sb.append("\n");
		}
		return sb.toString();
	}
}