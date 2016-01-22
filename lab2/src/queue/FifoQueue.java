package queue;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() { 
		size = 0; 
		last = null;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator(); 
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() { 
		return size;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param x
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else
	 *         false
	 */

	public boolean offer(E x) {
		QueueNode<E> temp;
		if (isEmpty()) {
			temp = new QueueNode<E>(x);
			last = temp;
			last.next = last; 
		} else {
			temp = new QueueNode<E>(x);
			temp.next = last.next;
			last.next = temp;
			last = temp;
		}
		size++;
		return true; 
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (isEmpty()) {
			return null; 
		}

		QueueNode<E> out = last.next;
		last.next = out.next;
		if(size == 1){
			size--;
		last.next = null;
		return out.element;
		}
		size--; 
		return out.element;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (isEmpty()) {
			return null; 
		} 
		return last.next.element;
	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue.
	 * 
	 * @param q
	 *            the queue to append
	 */
	public void append(FifoQueue<E> q) { 
		if (isEmpty()) {
			last = q.last;
			size += q.size;
		} else if (!q.isEmpty()) { 
			QueueNode<E> temp = last.next;
			last.next = q.last.next;
			q.last.next = temp;
			last = q.last;
			size += q.size;
		}
		q.last = null; 
		q.size = 0; 
	}
	
	public String toString(){
		StringBuilder writer = new StringBuilder();
		Iterator<E> itr = iterator();
		while(itr.hasNext()){
			E temp = itr.next();
			writer.append(temp.toString() + ", ");
		}
		return writer.toString();
	}

	private static class QueueNode<E> {
		E element; 
		QueueNode<E> next; 

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int count;

		private QueueIterator() {
			if (last == null) {   
				pos = null;
			} else {
				pos = last.next; 
			}
			count = 0;
		}

		public boolean hasNext() {
			if (pos == null) {
				return false; 
			}

			if (count >= size) {
				return false; 
			}

			return true;
		}

		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException(); 
			}
			QueueNode<E> temp = pos;
			pos = temp.next;
			count++;
			return temp.element; 
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}