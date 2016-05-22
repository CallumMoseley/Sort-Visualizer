public class Bogosort extends Sort {

	public Bogosort(int[] l) {
		super(l);
	}

	@Override
	public void step() {
		if (!sorted) {
			for (int i = list.length - 1; i >= 1; i--) {
				int j = (int) (Math.random() * (i + 1));
				swap(i, j);
			}
			
			sorted = true;
			for (int i = 1; i < list.length; i++) {
				if (list[i] < list[i - 1]) {
					sorted = false;
					break;
				}
			}
		}
	}
}