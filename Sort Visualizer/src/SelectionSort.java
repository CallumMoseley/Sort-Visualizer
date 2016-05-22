public class SelectionSort extends Sort {

	int index = 0;
	
	public SelectionSort(int[] l) {
		super(l);
	}

	@Override
	public void step() {
		if (!sorted) {
			int min = index;
			for (int i = index + 1; i < list.length; i++) {
				if (compare(i, min) < 0) {
					min = i;
				}
			}
			swap(index, min);
			index++;
			if (index == list.length - 1) {
				sorted = true;
			}
		}
	}
}