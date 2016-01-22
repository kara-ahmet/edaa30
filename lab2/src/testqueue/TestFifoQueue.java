package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Iterator;

import queue.FifoQueue;

public class TestFifoQueue {
	private FifoQueue<Integer> myIntQueue;
	private FifoQueue<String> myStringQueue;

	@Before
	public void setUp() throws Exception {
		myIntQueue = new FifoQueue<Integer>();
		myStringQueue = new FifoQueue<String>();
	}

	@After
	public void tearDown() throws Exception {
		myIntQueue = null;
		myStringQueue = null;
	}

	/**
	 * Test if a newly created queue is empty.
	 */
	@Test
	public final void testNewFifoQueue() {
		assertTrue(myIntQueue.isEmpty());
		assertTrue(myIntQueue.size() == 0);
	}

	/** Test a single offer followed by a single peek. */
	@Test
	public final void testPeek() {
		myIntQueue.offer(1);
		int i = myIntQueue.peek();
		assertEquals("peek on queue of size 1", 1, i);
		assertTrue(myIntQueue.size() == 1);
	}

	/**
	 * Test a single offer followed by a single poll.
	 */
	@Test
	public final void testPoll() {
		myIntQueue.offer(1);
		int i = myIntQueue.poll();
		assertEquals("poll on queue of size 1", 1, i);
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
	}

	/**
	 * Test peek of empty queue.
	 */
	@Test
	public final void testPeekOfEmpty() {
		assertEquals("Front of empty queue not null", null, myIntQueue.peek());
	}

	/**
	 * Test poll of empty queue.
	 */
	@Test
	public final void testPollOfEmpty() {
		assertEquals("Poll of empty queue should return null", null, myIntQueue
				.poll());
	}

	/**
	 * Test that implementation works for a queue of strings.
	 */
	@Test
	public final void testStringQueue() {
		myStringQueue.offer("First");
		myStringQueue.offer("Second");
		myStringQueue.offer("Third");
		assertTrue("Wrong size of queue", myStringQueue.size() == 3);
		assertEquals("peek on queue of strings", "First", myStringQueue.peek());
		assertEquals("String First expected", "First", myStringQueue.poll());
		assertEquals("String Second expected", "Second", myStringQueue.poll());
		assertEquals("String Third expected", "Third", myStringQueue.poll());
		assertTrue("Queue of strings should be empty", myStringQueue.isEmpty());
	}

	/**
	 * Test that polling gives elements in right order.
	 */
	@Test
	public final void testOrder() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		myIntQueue.offer(5);
		for (int i = 1; i <= 5; i++) {
			int k = myIntQueue.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Queue not empty", myIntQueue.isEmpty());
	}
	
	/**
	 * Test that polling all elements gives an empty queue.
	 */
	@Test
	public final void testMakeQueueEmpty() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.poll();
		myIntQueue.poll();
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
		assertTrue("Queue not empty after poll", myIntQueue.isEmpty());
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		assertTrue("Wrong size after offer", myIntQueue.size() == 2);
		for (int i = 3; i <= 4; i++) {
			int k = myIntQueue.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
		assertTrue("Queue not empty after poll", myIntQueue.isEmpty());
	}
	
	/**
	 * Test iterator of empty queue.
	 */
	@Test
	public final void testIteratorOfEmpty() {
		Iterator<Integer> itr = myIntQueue.iterator();
		assertFalse("hasNext of empty queue should return false", itr.hasNext());
		try {
			itr.next();
			fail("next() of empty set should throw exception");
		} catch (NoSuchElementException e) {
			//success
		}
	}
	
	/**
	 * Test a single offer followed by a single next().
	 */
	@Test
	public final void testIteratorNext() {
		myIntQueue.offer(7);
		Iterator<Integer> itr = myIntQueue.iterator();
		assertTrue("hasNext() of queue with one element should return true", itr.hasNext());
		while(itr.hasNext()){
			assertEquals("next() should return 7", new Integer(7), itr.next());			
		}
		assertFalse("hasNext() of queue with one element should return false", itr.hasNext());
	}
	
	/**
	 * Test that implementation works for a queue of strings.
	 */
	@Test
	public final void testStringQueueIterator() {
		myStringQueue.offer("First");
		myStringQueue.offer("Second");
		myStringQueue.offer("Third");
		Iterator<String> itr = myStringQueue.iterator();		
		assertTrue("Wrong size of queue", myStringQueue.size() == 3);
		assertEquals("peek on queue of strings", "First", itr.next());
		assertEquals("String Second expected", "Second", itr.next());
		assertEquals("String Third expected", "Third", itr.next());
		assertFalse("hasNext() should return false since whole list is traversed", itr.hasNext()); 
	}
	
	/**
	 * Test that traversing gives elements in right order.
	 */
	@Test
	public final void testIteratorOrder() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		myIntQueue.offer(5);
		Iterator<Integer> itr = myIntQueue.iterator();
		for (int i = 1; i <= 5; i++) {
			int k = itr.next();
			assertEquals("next() returns incorrect element", i, k);
//			System.out.println(k);
		}
		assertFalse("Queue not empty", itr.hasNext());
	}
	
	/**
	 * Test that traversing gives elements in right order.
	 */
	@Test
	public final void testIteratorOrder2() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		myIntQueue.offer(5);
		Iterator<Integer> itr = myIntQueue.iterator();
		int i = 1;
		assertFalse("isEmpty() should return false", myIntQueue.isEmpty());
		assertTrue("hasNext() should return true", itr.hasNext());
		while(itr.hasNext()){
			int k = itr.next();
			assertEquals("next() returns wrong element", i, k);
			i++;
//			System.out.println(k);
		}
		assertFalse("Queue not empty", itr.hasNext());
	}
	
	/**
	 * Test appending two empty queues
	 */
	@Test
	public final void testAppendEmptyQueue() {
		FifoQueue<Integer> myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue.append(myIntQueue1);
		
		assertTrue("isEmpty() should return true", myIntQueue.isEmpty());
		assertEquals("size() should be 0", 0, myIntQueue.size());
		
		Iterator<Integer> itr = myIntQueue.iterator();
		
		assertFalse("hasNext() should return false", itr.hasNext());
		
		try {
			itr.next();
			fail("next() of empty set should throw exception");
		} catch (NoSuchElementException e) {
			//success
		}
	}
	
	/**
	 * Test appending two non-empty queues and check the order
	 */
	@Test
	public final void testAppendQueue() {
		FifoQueue<Integer> myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue1.offer(3);
		myIntQueue1.offer(4);
		myIntQueue1.offer(5);
		myIntQueue.append(myIntQueue1);
		
		assertTrue("isEmpty should be true since we moved the elemnts to the other list", myIntQueue1.isEmpty());
		Iterator<Integer> itr = myIntQueue.iterator();
		int i = 1;
		assertFalse("isEmpty() should return false", myIntQueue.isEmpty());
		assertTrue("hasNext() should return true", itr.hasNext());
		while(itr.hasNext()){
			int k = itr.next();
			assertEquals("next() returns wrong element", i, k);
			i++;
		}
		assertFalse("Queue not empty", itr.hasNext());
	}
	
	/**
	 * Test appending a non-empty queue with a empty queue
	 */
	@Test
	public final void testAppendFromEmptyQueue() {
		FifoQueue<Integer> myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		myIntQueue.offer(5);
		myIntQueue.append(myIntQueue1);
		
		Iterator<Integer> itr = myIntQueue.iterator();
		int i = 1;
		assertFalse("isEmpty() should return false", myIntQueue.isEmpty());
		assertTrue("hasNext() should return true", itr.hasNext());
		while(itr.hasNext()){
			int k = itr.next();
			assertEquals("next() returns wrong element", i, k);
			i++;
		}
		assertFalse("Queue not empty", itr.hasNext());
	}	
	
	/**
	 * Test appending a empty queue with a non-empty queue
	 */
	@Test
	public final void testAppendIntoEmptyQueue() {
		FifoQueue<Integer> myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		myIntQueue1.offer(4);
		myIntQueue1.offer(5);
		myIntQueue.append(myIntQueue1);
		
		assertTrue("isEmpty should be true since we moved the elemnts to the other list", myIntQueue1.isEmpty());
		Iterator<Integer> itr = myIntQueue.iterator();
		int i = 1;
		assertFalse("isEmpty() should return false", myIntQueue.isEmpty());
		assertTrue("hasNext() should return true", itr.hasNext());
		while(itr.hasNext()){
			int k = itr.next();
			assertEquals("next() returns wrong element", i, k);
			i++;
		}
		assertFalse("Queue not empty", itr.hasNext());
	}
	
	/**
	 * Test toString on an empty queue
	 */
	@Test
	public final void testEmptyToString() {
		String s = myIntQueue.toString();
		assertEquals("toString should return empty string", "", s);
	}

	/**
	 * Test toString on a non-empty queue
	 */
	@Test
	public final void testToString() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		
		String s = myIntQueue.toString();
		System.out.println(s);
		assertEquals("toString should return empty string", "1, 2, 3, ", s);
	}
	
	/**
	 * Test that implementation works for a queue of strings.
	 */
	@Test
	public final void testToStringOfString() {
		myStringQueue.offer("First");
		myStringQueue.offer("Second");
		myStringQueue.offer("Third");
		assertTrue("Wrong size of queue", myStringQueue.size() == 3);
		assertEquals("peek on queue of strings", "First, Second, Third, ", myStringQueue.toString());
	}
}
