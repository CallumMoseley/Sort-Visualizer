import java.util.Arrays;

public class MergeSort extends Sort {
	
	private BinaryNode a;
	
	public MergeSort(int[] l) {
		super(l);
		a = new BinaryNode(l);
	}

	@Override
	public void step() {
		if (!sorted) {
			BinaryNode merge = getNext(a);
			
			int[] left = merge.left.array;
			int[] right = merge.right.array;
			
			int[] merged = new int[left.length + right.length];
			
			int l = 0;
			int r = 0;
			
			while (l + r < merged.length) {
				if (l == left.length) {
					merged[l + r] = right[r];
					r++;
				} else if (r == right.length) {
					merged[l + r] = left[l];
					l++;
				} else if (compare(left[l], right[r]) < 0) {
					merged[l + r] = left[l];
					l++;
				} else {
					merged[l + r] = right[r];
					r++;
				}
			}
			
			merge.left = null;
			merge.right = null;
			merge.array = merged;
			swap(0, 0);
			
			updateParents(merge);
			
			list = a.array.clone();
			
			if (a.isLeaf()) {
				sorted = true;
			}
		}
	}
	
	private void updateParents(BinaryNode n) {
		if (n.left != null && n.right != null) {
			n.array = appendArrays(n.left.array, n.right.array);
		}
		if (n.parent != null) {
			updateParents(n.parent);
		}
	}
	
	private int[] appendArrays(int[] a, int[] b) {
		int[] result = new int[a.length + b.length];
		for (int i = 0; i < a.length; i++) {
			result[i] = a[i];
		}
		for (int i = 0; i < b.length; i++) {
			result[i + a.length] = b[i];
		}
		return result;
	}

	private BinaryNode getNext(BinaryNode start) {
		if (start.left.isLeaf() && start.right.isLeaf()) return start;
		if (!start.left.isLeaf()) return getNext(start.left);
		return getNext(start.right);
	}

	private static class BinaryNode {
		int[] array;
		BinaryNode left;
		BinaryNode right;
		BinaryNode parent;
		
		public BinaryNode(int[] a) {
			array = a;
			if (a.length > 1) {
				left = new BinaryNode(Arrays.copyOfRange(array, 0, array.length / 2));
				left.parent = this;
				right = new BinaryNode(Arrays.copyOfRange(array, array.length / 2, array.length));
				right.parent = this;
			}
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
	}
}