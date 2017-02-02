package fi.pku;

import java.util.NoSuchElementException;

public interface IQueue {
	/**
	 * Inserts an object at the end of the Queue.
	 * 
	 * @param item the object to add to the Queue
	 */
	public void enQueue(Object item);
	/**
	 * Removes the object at the front of the Queue and
	 * returns the object.
	 * 
	 * @return the object at the front of the Queue,
	 * 		or null if no object exists
	 */
	public Object deQueue() throws NoSuchElementException;
	/**
	 * Returns the object at the front of the Queue without 
	 * removing it from the Queue.
	 * 
	 * @return the object at the front of the Queue,
	 * 		or null if no object exists
	 */
	public Object front();
	/**
	 * Returns the number of objects currently in the Queue.
	 * 
	 * @return the number of objects in the Queue
	 */
	public int queueSize();
	/**
	 * Returns true if and only if the Queue contains no 
	 * objects.
	 * 
	 * @return true if the Queue is not containing any objects,
	 * 		otherwise false
	 */
	public boolean isEmptyQueue();
}