package inl1;

public class Calculator {
	private int[] stack;
	public Calculator() {
		stack = new int[4]; 
	}
	public int getFirst() { 
		return stack[0]; 
	}
	public int getSecond() {
		return stack[1];
	}
	public int getThird() {
		return stack[2];
	}
	public int getFourth() {
		return stack[3];
	}
	public int minus() {
		int diff= stack[1] - stack[0]; 
		stack[0] = diff; 
		return diff;
	}
	public int multiply() {
		int prod  = stack[0] * stack [1];
		stack[0] = prod;	
		return prod;
	}
	public int division() {
		int nbr = stack[1] / stack [0];
		stack[0] = nbr;
		return nbr;
	}
	public int plus() {
		int sum = stack[0] + stack[1];
		stack[0] = sum;
		return sum;
	}
	public void reset(int y) {
		stack[y] = 0; 
	}
	public void add(int y, int x) {	
		stack[y] = x;
	}
	public void resetAll() {
		for (int i = 0; i < 4; i++) { 
			stack[i] = 0; 
		}
	}
}
