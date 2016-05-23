public class ShellSort extends Sort {

	private static final int[] passes = new int[] {701, 301, 132, 57, 23, 10, 4, 1};
	private int pass;
	private int index;
	
	public ShellSort(int[] l) {
		super(l);
		pass = 0;
		index = passes[0];
	}

	@Override
	public void step() {
		if (!sorted) {
			int i = index;
			
			while (i >= passes[pass] && compare(list[i], list[i - passes[pass]]) < 0) {
				swap(i, i - passes[pass]);
				i -= passes[pass];
			}
			
			index++;

			if (index == list.length) {
				if (pass < passes.length - 1) {
					pass++;
					index = passes[pass];
				} else {
					sorted = true;
				}
			}
		}
	}
}