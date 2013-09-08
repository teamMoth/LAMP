package gui;

import java.awt.*;

import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

import model.Calendar;
import model.TimeSlot;
import data.Time;

public class MainGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private static int PANEL_MIN_WIDTH = 100;
	private static int CELL_HEIGHT = 15;
	private static int CELL_WIDTH = 60;
	private static int DAYS_IN_WEEK = 7;
	private static int HOURS_IN_DAY = 18;
	private static int SECTIONS_IN_HOUR = 2;
	private static boolean RAISED_CELL = true;
	private Calendar myCalendar;
	private int hardCodedNumber = 21;
	private static Status status = Status.FREE;
	
	
	public MainGUI(){
		myCalendar = new Calendar(HOURS_IN_DAY * SECTIONS_IN_HOUR, DAYS_IN_WEEK);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JLabel leftTitle = new JLabel("Time");
		leftTitle.setPreferredSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
		JLabel middleTitle = new JLabel("Middle");
		JLabel rightTitle = new JLabel("Right");
		
		JTextArea textArea = new JTextArea("SUN MON TUE WED THU FRI SAT");
		textArea.setFont(new Font("Serif", Font.ITALIC, 11));
		textArea.setEditable(false);
			    	
		JPanel left = new JPanel();
		left.setPreferredSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		left.setMinimumSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		left.setMaximumSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.add(leftTitle);
		for (int i = 0; i < HOURS_IN_DAY; i ++){
			System.out.println(i);
			for (int j = 0; j < SECTIONS_IN_HOUR; j ++){
				String time = (Time.START_HOUR + i) + ":" + (j * 30);
				JLabel label = new JLabel(time);
				label.setPreferredSize(new Dimension(PANEL_MIN_WIDTH, CELL_HEIGHT));
				label.setMinimumSize(new Dimension(PANEL_MIN_WIDTH, CELL_HEIGHT));
				label.setMaximumSize(new Dimension(PANEL_MIN_WIDTH, CELL_HEIGHT));
				left.add(label);
				System.out.println("label added");
			}
		}
		
		JPanel middle = new JPanel();
		middle.setPreferredSize(new Dimension(DAYS_IN_WEEK * CELL_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		middle.setMinimumSize(new Dimension(DAYS_IN_WEEK * CELL_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		middle.setMaximumSize(new Dimension(DAYS_IN_WEEK * CELL_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		
		JPanel midTop = new JPanel();
		midTop.setLayout(new BoxLayout(midTop, BoxLayout.X_AXIS));
		//midTop.add(textArea);
		JLabel sunLabel = new JLabel("SUN");
		sunLabel.setSize(CELL_WIDTH, CELL_HEIGHT);
		sunLabel.setMinimumSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
		
		midTop.add(new JLabel("SUN"));
		midTop.add(new JLabel("MON"));
		midTop.add(new JLabel("TUE"));
		midTop.add(new JLabel("WED"));
		midTop.add(new JLabel("THU"));
		midTop.add(new JLabel("FRI"));
		midTop.add(new JLabel("SAT"));
		
		JPanel midBot = new JPanel();
		midBot.add(new CalendarGUI());

		middle.add(midTop);
		middle.add(midBot);
		
		JPanel right = new JPanel();
		right.setPreferredSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		right.setMinimumSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		right.setMaximumSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		JButton classButton = new JButton("CLASS");
		classButton.setBackground(Color.RED);
		classButton.setForeground(Color.YELLOW);
		classButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                status = Status.CLASS;
            }
        });      
		
		JButton workButton = new JButton("WORK");
		workButton.setBackground(Color.BLACK);
		workButton.setForeground(Color.WHITE);
		workButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                status = Status.WORK;
            }
        });      
		
		JButton busyButton = new JButton("BUSY");
		busyButton.setBackground(Color.BLUE);
		busyButton.setForeground(Color.WHITE);
		busyButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                status = Status.BUSY;
            }
        });      
		
		JButton freeButton = new JButton("FREE");
		freeButton.setBackground(Color.WHITE);
		freeButton.setForeground(Color.BLACK);
		freeButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                status = Status.FREE;
            }
        });      
		
		
		right.add(classButton);
		right.add(workButton);
		right.add(busyButton);
		right.add(freeButton);

		addMouseListener(new MouseHandler(this));

		add(left);
		add(middle);
		add(right);
	
	}
	
	private enum Status {
		CLASS("CLASS", Color.RED),
		WORK("WORK", Color.BLACK),
		BUSY("BUSY", Color.BLUE),
		FREE("FREE", Color.WHITE);
		
		private final String name;
		private final Color color;
		
		Status(String name, Color color){
			this.name = name;
			this.color = color;
		}
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
		MainGUI mainGUI;
		
		public MouseHandler(MainGUI mainGUI){
			this.mainGUI = mainGUI;
		}
		
		public void mouseReleased(MouseEvent event) {
			
			Point point = event.getPoint();
			
			System.out.println("GUI width: " + mainGUI.getWidth());
			
			System.out.println("point.x: " + point.x + "\npoint.y: " + point.y);
			
			double xStart = (mainGUI.getWidth() / 2.0) - (DAYS_IN_WEEK * CELL_WIDTH / 2.0);
			
			System.out.println("xStart: " + xStart);
			
			if (point.x < xStart || point.y < hardCodedNumber)
				return;
			
			double xDistance = point.x - xStart;
			
			System.out.println("xDistance: " + xDistance);
			
			/* Determining the cell clicked */
			int rowIndex = (int) ((point.y - hardCodedNumber) / CELL_HEIGHT);
			int colIndex = (int) (xDistance / CELL_WIDTH);
			
			if (rowIndex < HOURS_IN_DAY * SECTIONS_IN_HOUR && colIndex < DAYS_IN_WEEK){
				processCell(rowIndex, colIndex);
				repaint();
			}
		}
	}
	
	public void processCell(int rowIndex, int colIndex){
		myCalendar.schedule[rowIndex][colIndex].setColor(status.color);
		myCalendar.schedule[rowIndex][colIndex].setName(status.name);
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
