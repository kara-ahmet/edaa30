package test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import set.RemoveDuplicates;
public class TestUniqueElements {
	int[] testArray;
	boolean check;
	int[] checkArrayInt;
	@Before
	public void setUp() throws Exception{
		
	}
	@After
	public void tearDown() throws Exception{
		testArray = null;
	}
	@Test
	public final void testDiffElements() {
		testArray = new int[]{100, 500, 200, 400, 300, 800};
		check = false;
		checkArrayInt = RemoveDuplicates.uniqueElements(testArray);
		for(int i = 0; i < 6; i++){
			if(checkArrayInt[i] != testArray[i]){
				check = true;
			}
		}
		assertTrue("Blablabla", check);
	}
	@Test
	public final void testSameElement() {
		testArray = new int[]{5, 5, 5, 5, 5};
		check = true;
		int[] intArray = new int[]{5}; 
		checkArrayInt = RemoveDuplicates.uniqueElements(testArray);
		for(int i = 0; i < 5; i++){
			if(checkArrayInt[0] != intArray[0]){
				check = false;
			}
			assertTrue("Blablabla", check);
		}
	}
	@Test
	public final void testVarSize() {
		testArray = new int[]{8, 3, 5, 7, 9, 7, 4, 5, 8};
		check = true;
		int[] intArray = new int[]{3, 4, 5, 7, 8, 9};
		checkArrayInt = RemoveDuplicates.uniqueElements(testArray);
		for(int i = 0; i < 6; i++){
			if(checkArrayInt[i] != intArray[i]){
				check = false;
			}
		}
		assertTrue("Blablabla", check);
	}
}
