package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import set.ArraySet;

public class AddAllTest {
	ArraySet<Integer> s;

	@Before
	public void setUp() throws Exception {
		s = new ArraySet<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		s = null;
	}

	
	
	@Test
	public void testAddAllToEmptySet() {
		ArraySet<Integer> s2 = new ArraySet<Integer>();
		assertFalse("Wrong result for adding empty set to empty set", s.addAll(s2));
		for (int i = 1; i <= 10; i++) {
			s2.add(i);
		}
		assertTrue("Wrong result for adding nonemptyset to empty set", s.addAll(s2));
		assertEquals("Wrong size():", 10, s.size());
		for (int i = 1; i <= 10; i++) {
			assertTrue("Element not found in set:" + i, s.contains(i));
		}
	}
	
	@Test
	public void testAddAllFromEmptySet() {
		ArraySet<Integer> s2 = new ArraySet<Integer>();
		for (int i = 1; i <= 10; i++) {
			s.add(i);
		}
		assertFalse("Wrong result for adding emptyset to nonempty set", s.addAll(s2));
		assertEquals("Wrong size():", 10, s.size());
		for (int i = 1; i <= 10; i++) {
			assertTrue("Element not found in set:" + i, s.contains(i));
		}
	}

	@Test
	public void testAddAllDuplicates() {
		for (int i = 1; i <= 10; i++) {
			s.add(i);
		}
		ArraySet<Integer> s2 = new ArraySet<Integer>();
		for (int i = 1; i <= 10; i++) {
			s2.add(i);
		}
		assertFalse("Wrong result for adding to set (all duplicates)",s.addAll(s2));
		assertEquals("Wrong size():", 10, s.size());
		for (int i = 1; i <= 10; i++) {
			assertTrue("Element not found in set:" + i, s.contains(i));
		}
	}
	
	@Test
	public void testAddAllNoDuplicates() {
		for (int i = 1; i <= 10; i++) {
			s.add(i);
		}
		ArraySet<Integer> s2 = new ArraySet<Integer>();
		for (int i = 11; i <= 20; i++) {
			s2.add(i);
		}
		assertTrue("Wrong result for adding to set (no duplicates)",s.addAll(s2));
		assertEquals("Wrong size():", 20, s.size());
		for (int i = 1; i <= 20; i++) {
			assertTrue("Element not found in set:" + i, s.contains(i));
		}
	}

	@Test
	public void testAddAllSomeDuplicates() {
		for (int i = 1; i <= 10; i++) {
			s.add(i);
		}
		ArraySet<Integer> s2 = new ArraySet<Integer>();
		for (int i = 5; i <= 15; i++) {
			s2.add(i);
		}
		assertTrue("Wrong result for adding to set (some duplicates)",s.addAll(s2));
		assertEquals("Wrong size():", 15, s.size());
		for (int i = 1; i <= 15; i++) {
			assertTrue("Element not found in set:" + i, s.contains(i));
		}
	}

}
