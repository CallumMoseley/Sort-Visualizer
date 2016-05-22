public class InsertionSort extends Sort {

	private int index;
	
	public InsertionSort(int[] l) {
		super(l);
		index = 1;
	}
	
	@Override
	public void setList(int[] l) {
		super.setList(l);
		index = 1;
	}
	
	@Override
	public void step() {
		if (!sorted) {
			int i = index;
			
			while (i > 0 && compare(i, i - 1) < 0) {
				swap(i, i - 1);
				i--;
			}
			
			index++;
			if (index == list.length) sorted = true;
		}
	}
}