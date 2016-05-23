public class BubbleSort extends Sort {

	public BubbleSort(int[] l) {
		super(l);
	}

	@Override
	public void step() {
		if (!sorted) {
			boolean swapped = false;
			for (int i = 1; i < list.length; i++) {
				if (compare(list[i], list[i - 1]) < 0) {
					swap(i, i - 1);
					swapped = true;
				}
			}
			sorted = !swapped;
		}
	}
}