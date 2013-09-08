package gui;

import java.awt.*;

import java.awt.event.*;
import java.text.Format.Field;
import java.util.Random;

import javax.swing.*;

import model.Calendar;
import model.TimeSlot;
import data.Time;

public class ScheduleGUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private static int PANEL_MIN_WIDTH = 100;
	private static int CELL_HEIGHT = 15;
	private static int CELL_WIDTH = 60;
	private static int DAYS_IN_WEEK = Time.DAYS_A_WEEK;
	private static int HOURS_IN_DAY = Time.HOURS_A_DAY;
	private static int SECTIONS_IN_HOUR = 60/Time.TIME_INTERVAL;
	private static boolean RAISED_CELL = true;
	private Calendar myCalendar;
	private int hardCodedNumber = 21;
	private static Status status = Status.FREE;
	private String scheduleName;
	
	
	public ScheduleGUI(String name, String[][] strArr){
		
		myCalendar = new Calendar(HOURS_IN_DAY * SECTIONS_IN_HOUR, DAYS_IN_WEEK);
			
		if (strArr != null){
			myCalendar.schedule = strToTimeSlot(strArr);
		}
		
		this.scheduleName = name;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JLabel leftTitle = new JLabel();
		leftTitle.setText("<html><b>TIME</b></html>");
			    	
		JPanel left = new JPanel();
		left.setSize(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber);
		left.setMinimumSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		left.setMaximumSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.setBackground(Color.decode("0xFFE1E1"));
		left.add(leftTitle);
		for (int i = 0; i < HOURS_IN_DAY; i ++){
			for (int j = 0; j < SECTIONS_IN_HOUR; j ++){
				
				String time;
				if (j == 0){
					time = (Time.START_HOUR + i) + ":" + (j * 30) + "0";
				}
				else{
					time = (Time.START_HOUR + i) + ":" + (j * 30);
				}
				
				JLabel label = new JLabel(time);
				left.add(label);
			}
		}
		
		JPanel middle = new JPanel();
		middle.setSize(DAYS_IN_WEEK * CELL_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber);
		middle.setMinimumSize(new Dimension(DAYS_IN_WEEK * CELL_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		middle.setMaximumSize(new Dimension(DAYS_IN_WEEK * CELL_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		middle.setBackground(Color.decode("0xFFE1E1"));
		
		JPanel midTop = new JPanel();
		midTop.setLayout(new BoxLayout(midTop, BoxLayout.X_AXIS));
		midTop.setBackground(Color.decode("0xFFE1E1"));
		JLabel sunLabel = new JLabel("SUN");
		sunLabel.setSize(CELL_WIDTH, CELL_HEIGHT);
		sunLabel.setMinimumSize(new Dimension(CELL_WIDTH, CELL_HEIGHT));
		
		midTop.add(new JLabel("SUN"));
		midTop.add(Box.createRigidArea(new Dimension(CELL_WIDTH / 2, 0)));
		midTop.add(new JLabel("MON"));
		midTop.add(Box.createRigidArea(new Dimension(CELL_WIDTH / 2, 0)));
		midTop.add(new JLabel("TUE"));
		midTop.add(Box.createRigidArea(new Dimension(CELL_WIDTH / 2, 0)));
		midTop.add(new JLabel("WED"));
		midTop.add(Box.createRigidArea(new Dimension(CELL_WIDTH / 2, 0)));
		midTop.add(new JLabel("THU"));
		midTop.add(Box.createRigidArea(new Dimension(CELL_WIDTH / 2, 0)));
		midTop.add(new JLabel("FRI"));
		midTop.add(Box.createRigidArea(new Dimension(CELL_WIDTH / 2, 0)));
		midTop.add(new JLabel("SAT"));
		
		JPanel midMid = new JPanel();
		midMid.add(new CalendarGUI());
		midMid.setBackground(Color.WHITE);
		
		JPanel midBot = new JPanel();
		midBot.setBackground(Color.decode("0xFFE1E1"));
		
		middle.add(midTop);
		middle.add(midMid);
		middle.add(midBot);
		
		JPanel right = new JPanel();
		right.setSize(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber);
		right.setMinimumSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		right.setMaximumSize(new Dimension(PANEL_MIN_WIDTH, HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.setBackground(Color.decode("0xFFE1E1"));
		
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
		
		right.add(Box.createRigidArea(new Dimension(0, right.getHeight() / 2 - 80)));
		right.add(classButton);
		right.add(Box.createRigidArea(new Dimension(0, 10)));
		right.add(workButton);
		right.add(Box.createRigidArea(new Dimension(0, 10)));
		right.add(busyButton);
		right.add(Box.createRigidArea(new Dimension(0, 10)));
		right.add(freeButton);

		addMouseListener(new MouseHandler(this));

		this.setSize(PANEL_MIN_WIDTH * 2 + middle.getWidth(), HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber);
		this.setMinimumSize(new Dimension(PANEL_MIN_WIDTH * 2 + middle.getWidth(), HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		this.setMaximumSize(new Dimension(PANEL_MIN_WIDTH * 2 + middle.getWidth(), HOURS_IN_DAY * SECTIONS_IN_HOUR * CELL_HEIGHT + hardCodedNumber));
		
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
		ScheduleGUI scheduleGUI;
		
		public MouseHandler(ScheduleGUI scheduleGUI){
			this.scheduleGUI = scheduleGUI;
		}
		
		public void mouseReleased(MouseEvent event) {
			
			Point point = event.getPoint();
			
			double xStart = (scheduleGUI.getWidth() / 2.0) - (DAYS_IN_WEEK * CELL_WIDTH / 2.0);
			
			if (point.x < xStart || point.y < hardCodedNumber)
				return;
			
			double xDistance = point.x - xStart;
			
			/* Determining the cell clicked */
			int rowIndex = (int) ((point.y - hardCodedNumber) / CELL_HEIGHT);
			int colIndex = (int) (xDistance / CELL_WIDTH);
			
			if (rowIndex < HOURS_IN_DAY * SECTIONS_IN_HOUR && colIndex < DAYS_IN_WEEK){
				processCell(rowIndex, colIndex);
				repaint();
			}
		}
	}
	
	public TimeSlot[][] strToTimeSlot(String[][] strArr){
		TimeSlot[][] newSchedule = new TimeSlot[strArr.length][strArr[0].length];
		
		for (int i = 0; i < strArr.length; i ++){
			for (int j = 0; j < strArr[0].length; j ++){
				String[] nameAndColor = strArr[i][j].split(";");
				newSchedule[i][j].setName(nameAndColor[0]);
				
				Color color;
				try {
				    java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(nameAndColor[1]);
				    color = (Color)field.get(null);
				} catch (Exception e) {
				    color = null; // Not defined
				}
				
				newSchedule[i][j].setColor(color);
			}
		}
		return newSchedule;
	}
	
	public String[][] getSchedule(){
		TimeSlot[][] schedule = myCalendar.schedule;
		String[][] stringSchedule = new String[schedule.length][schedule[0].length];
		
		for (int i = 0; i < schedule.length; i ++){
			for (int j = 0; j < schedule[0].length; j ++){
				stringSchedule[i][j] = schedule[i][j].getName() + ";" + schedule[i][j].getColor();
			}
		}
		
		return stringSchedule;
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
	
	/* delete this method */
	public static void createAndDisplayGUI(Calendar calendar){
		ScheduleGUI scheduleGUI = new ScheduleGUI("Dan", null);
		
		JFrame frame = new JFrame("Calendar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scheduleGUI);
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
				ScheduleGUI.createAndDisplayGUI(new Calendar(maxRows, maxCols));
			}
		};
		SwingUtilities.invokeLater(createShowGUI);
	}
	
}
