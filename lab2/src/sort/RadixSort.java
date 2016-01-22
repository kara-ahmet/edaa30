package sort;

import java.util.Iterator;

import queue.FifoQueue;

public class RadixSort {
	public static void radixSort(int[] a, int maxNbrOfDigits) {
		FifoQueue<Integer> numbers = new FifoQueue<Integer>();
		FifoQueue<Integer>[] queues = (FifoQueue<Integer>[]) new FifoQueue[10];
		for (int i : a) {
			numbers.offer(i);
		}

		for (int i = 0; i < 10; i++) {
			queues[i] = new FifoQueue<Integer>();
		}
		// int i = 0;
		Iterator<Integer> itr;
		for (int i = 0; i < maxNbrOfDigits; i++) {
			itr = numbers.iterator();
			int index = (int) (Math.pow(10, i));
//			System.out.println("Tal nr " + index + " från slutet räknat");
			while (itr.hasNext()) {
				int num = itr.next();
				queues[(num / index) % 10].offer(num);
			}
			numbers = new FifoQueue<Integer>();
			for (int j = 0; j < 10; j++) {
				FifoQueue<Integer> temp = queues[j];
//				System.out.println(temp.toString());
				numbers.append(temp);
			}
//			System.out.println(numbers.toString());
//			System.out.println("Varv " + (i + 1) + " avklarat!");
		}

		for (int i = 0; i < a.length; i++) {
			a[i] = numbers.poll();
		}
	}
}