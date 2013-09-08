package gui;

import java.awt.*;

import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

import model.Calendar;
import model.TimeSlot;

public class MainGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private static int CELL_HEIGHT = 30;
	private static int CELL_WIDTH = 30;
	private static int DAYS_IN_WEEK = 7;
	private static int HOURS_IN_DAY = 18;
	private static int SECTIONS_IN_HOUR = 2;
	private static boolean RAISED_CELL = true;
	private Calendar myCalendar;
	
	
	public MainGUI(){
		myCalendar = new Calendar(HOURS_IN_DAY * SECTIONS_IN_HOUR, DAYS_IN_WEEK);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JLabel leftTitle = new JLabel("Left");
		JLabel middleTitle = new JLabel("Middle");
		JLabel rightTitle = new JLabel("Right");
		
		JTextArea textArea = new JTextArea("MON TUE WED THU FRI SAT SUN");
		textArea.setFont(new Font("Serif", Font.ITALIC, 11));
		textArea.setEditable(false);
			    	
		JPanel left = new JPanel();	
		left.add(leftTitle);

		JPanel middle = new JPanel();
		middle.setPreferredSize(new Dimension(DAYS_IN_WEEK * CELL_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT));	
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		
		JPanel midTop = new JPanel();
		midTop.setLayout(new BoxLayout(midTop, BoxLayout.X_AXIS));
		midTop.add(textArea);
		
		JPanel midBot = new JPanel();
		midBot.add(new CalendarGUI());

		middle.add(midTop);
		middle.add(midBot);
		
		JPanel right = new JPanel();
		right.add(rightTitle);

		addMouseListener(new MouseHandler());

		add(left);
		add(middle);
		add(right);
	}
	
	
	private class CalendarGUI extends JComponent{

		private static final long serialVersionUID = 1L;
		
		public CalendarGUI() {
			setPreferredSize(new Dimension(DAYS_IN_WEEK * CELL_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT));
		}
		
		protected void paintComponent(Graphics g) {
			Graphics g2 = (Graphics2D) g;
			
			/* Setting background */
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, getWidth(), getHeight());

			/* Drawing the grid */
			TimeSlot[][] grid = getGrid(myCalendar);
			for (int row = 0; row < grid.length; row++) {
				for (int col = 0; col < grid[row].length; col++) {
					g2.setColor(myCalendar.schedule[row][col].getColor());
					g2.fill3DRect(col * CELL_WIDTH, row * CELL_HEIGHT,
								 CELL_WIDTH, CELL_HEIGHT, RAISED_CELL);
				}
			}
		}
	}

	
	private class MouseHandler extends MouseAdapter {
		public void mouseReleased(MouseEvent event) {
			Point point = event.getPoint();
			
			System.out.println("point.x is " + point.x + "\npoint.y is " + point.y);

			/* Determining the cell clicked */
			int rowIndex = (int) (point.y / CELL_HEIGHT) - 1;
			int colIndex = (int) (point.x / CELL_WIDTH) - 1;
			
			System.out.println("x is " + colIndex + "\ny is " + rowIndex);
			
			if (rowIndex < HOURS_IN_DAY * SECTIONS_IN_HOUR && colIndex < DAYS_IN_WEEK){
				processCell(rowIndex, colIndex);
				repaint();
			}
		}
	}
	
	public void processCell(int rowIndex, int colIndex){
		myCalendar.schedule[rowIndex][colIndex].setColor(Color.RED);
	}
	
	private static TimeSlot[][] getGrid(Calendar calendar){
		TimeSlot[][] grid = new TimeSlot[HOURS_IN_DAY * SECTIONS_IN_HOUR][DAYS_IN_WEEK];
		
		for (int row = 0; row < HOURS_IN_DAY * SECTIONS_IN_HOUR; row ++){
			for (int col = 0; col < DAYS_IN_WEEK; col ++){
				grid[row][col] = calendar.schedule[row][col];
			}
		}
		return grid;
	}
	
	public static void createAndDisplayGUI(Calendar calendar){
		MainGUI mainGUI = new MainGUI();
		
		JFrame frame = new JFrame("Calendar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainGUI);
		frame.pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int upperLeftCornerX = (screenSize.width - frame.getWidth()) / 2;
	    int upperLeftCornerY = (screenSize.height - frame.getHeight()) / 2;
	    frame.setLocation(upperLeftCornerX, upperLeftCornerY);
	   
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args){
		Runnable createShowGUI = new Runnable() {
			public void run(){
				int maxRows = HOURS_IN_DAY * SECTIONS_IN_HOUR;
				int maxCols = DAYS_IN_WEEK;
				MainGUI.createAndDisplayGUI(new Calendar(maxRows, maxCols));
			}
		};
		SwingUtilities.invokeLater(createShowGUI);
	}
	
}
