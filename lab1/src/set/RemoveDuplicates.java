package set;

public class RemoveDuplicates {
	public static void main(String[] args){
		int[] ints = new int[]{7,5,3,5,2,2,7};
		int[] sorted  = uniqueElements(ints);
		for(int i : sorted){
			System.out.print(i + ", ");
		}		
	}
	public static int[] uniqueElements(int[] ints){
		MaxSet<Integer> set = new MaxSet<Integer>();
		for(int i : ints){
			set.add(i);
		}
		int[] out = new int[set.size()];
		for(int i = set.size()-1; i >= 0; i--){
			out[i] = set.getMax();
			set.remove(out[i]);
		}
		return out;
	}
}
