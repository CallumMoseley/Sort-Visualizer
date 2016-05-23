public abstract class Sort {
	protected int[] list;
	protected boolean sorted;
	private int comparisons;
	private int swaps;
	
	public Sort(int[] l) {
		list = l;
		comparisons = 0;
		swaps = 0;
		sorted = false;
	}
	
	public void setList(int[] l) {
		list = l;
		comparisons = 0;
		swaps = 0;
		sorted = false;
	}
	
	public int[] getList() {
		return list;
	}
	
	public int getComparisons() {
		return comparisons;
	}
	
	public int getSwaps() {
		return swaps;
	}
	
	public boolean isSorted() {
		return sorted;
	}
	
	protected int compare(int a, int b) {
		comparisons++;
		return ((Integer) a).compareTo(b);
	}
	
	protected void swap(int a, int b) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
		swaps++;
	}
	
	public abstract void step();
}