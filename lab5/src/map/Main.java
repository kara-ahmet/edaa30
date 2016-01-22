package map;
import map.SimpleHashMap;
public class Main {

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<Integer, Integer>(10);
		map.put(0, 1);
		map.put(1, 2);
		map.put(2, 3);
		map.put(3, 4);
		map.put(4, 5);
		map.put(5, 6);
		map.put(6, 7);
		map.put(7, 8);
		map.put(8, 9);
		map.put(9, 10);
		map.put(10, 11);
		map.remove(8);
		System.out.println(map.show());
	}
}
