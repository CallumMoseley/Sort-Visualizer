import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortVisualizer extends JFrame {
	
	public SortVisualizer() {
		super("Sort Visualizer");
		SortPanel dp = new SortPanel(new SelectionSort(randList(1000, 0, 1000000)));
		setResizable(true);
		setContentPane(dp);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args) {
		SortVisualizer pdt = new SortVisualizer();
		pdt.setVisible(true);
	}
	
	static class SortPanel extends JPanel implements MouseListener {
		
		private Sort sort;
		private int max;
		
		public SortPanel(Sort s) {
			setPreferredSize(new Dimension(1280, 768));
			addMouseListener(this);
			
			sort = s;
			max = s.getList()[0];
			for (int i = 1 ; i < s.getList().length; i++) {
				max = Math.max(max, s.getList()[i]);
			}
			
			(new Thread() {
				@Override
				public void run() {
					while (!sort.sorted) {
						sort.step();
						repaint(0);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(Color.WHITE);
			for (int i = 0; i < getWidth(); i++) {
				int value = sort.getList()[(int) (((double) i / getWidth()) * sort.getList().length)];
				int height = (int) (((double) value / max) * getHeight());
				int y = getHeight() - height;
				g.fillRect(i, getHeight() - (int) (((double) value / max) * getHeight()), 1, height);
			}
			
			g.drawString("Comparisons: " + sort.getComparisons(), 5, 20);
			g.drawString("Swaps: " + sort.getSwaps(), 5, 32);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			sort.setList(randList(1000, 0, 1000000));
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	
	public static int[] randList(int length, int min, int max) {
		int[] list = new int[length];
		for (int i = 0; i < length; i++) {
			list[i] = (int) (Math.random() * (max - min + 1)) + min;
		}
		return list;
	}
}