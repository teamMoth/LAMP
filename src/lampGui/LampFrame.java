package lampGui;

import aurelienribon.slidinglayout.SLAnimator;
import aurelienribon.slidinglayout.SLConfig;
import aurelienribon.slidinglayout.SLKeyframe;
import aurelienribon.slidinglayout.SLPanel;
import aurelienribon.slidinglayout.SLSide;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class LampFrame extends JFrame{
	private final SLPanel panel = new SLPanel();
	private final LampPanel p1 = new LampPanel("Home", 0xFFFFFF,0x000000,0x000000);
	private final LampPanel p2 = new LampPanel("Add schedule", 0xFFFFFF,0x000000,0xB0171F);
	private final LampPanel p3 = new LampPanel("Change time", 0xFFFFFF,0x000000,0xFFD700);
	private final SLConfig mainCfg, p1Cfg, p2Cfg;
	private String modifyName;
	String dayOfWeek,timeOfDay;
	Icon icon = createImageIcon("images/lamp_small.png","logo");

	public LampFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("List All My Possibilities");
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().add(panel, BorderLayout.CENTER);
	
		p1.setAction(menuAction);
		
		JButton setTimeToCurrent = new JButton("See what's up right now");
		JButton newTime = new JButton("Select a different time");
		JButton addSchedule = new JButton("Enter a schedule");
		JButton editSchedule = new JButton("Modify a schedule");
		JButton backToMenu = new JButton("Submit");
		JButton aboutButton = new JButton("About");
		setTimeToCurrent.setBackground(Color.YELLOW);
		newTime.setBackground(Color.YELLOW);
		addSchedule.setBackground(Color.YELLOW);
		editSchedule.setBackground(Color.YELLOW);
		backToMenu.setBackground(Color.BLACK);
		aboutButton.setBackground(Color.YELLOW);
		
		newTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Object[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
				dayOfWeek = (String)JOptionPane.showInputDialog(
				                    p2, "What day of the week?",
				                    "Customized Dialog",
				                    JOptionPane.PLAIN_MESSAGE,
				                    null, days,
				                    "Sunday");
				//Object[] times = {"7 am","7:30 am","8 am","8:30 am","9am","9:30 am","10 am","10:30 am", "11 am", "11:30 am", "noon", "12:30pm", "1 pm", "1:30 pm", "2 pm", "2:30 pm", "3 pm", "3:30 pm","4 pm", "4:30 pm", "5 pm", "5:30 pm", "6 pm", "6:30 pm", "7 pm", "7:30 pm", "8 pm", "8:30 pm", "9 pm", "9:30pm","10 pm", "10:30pm", "11 pm", "11:30 pm","midnight","12:30 am", "1 am"};
				timeOfDay = (String)JOptionPane.showInputDialog(
	                    p2, "What time of day?",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null, null,
	                    "3:00 pm");
				}
			// refresh p1 with new info
			
		});
		setTimeToCurrent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
    			dayOfWeek = "RightNow";
				// refresh p1 with new info
			}
		});
		aboutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(p2,"Created by teamMoth at daemondash2013", "About",JOptionPane.PLAIN_MESSAGE,icon);
			}
		});
		
		
		addSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					modifyName = timeOfDay = (String)JOptionPane.showInputDialog(
		                    p2, "Who do you want to add a schedule for?",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null, null,
		                    "Bob Lamp");
        			calendarAction.run();
			}
		});
		editSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				modifyName = timeOfDay = (String)JOptionPane.showInputDialog(
	                    p2, "Whose schedule do you want to edit?",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null, null,
	                    "Bob Lamp");
        			calendarAction.run();
			}
		});
		backToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					endCalendarAction.run();
			}
		});
		

        new Timer(500, new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                
            }
        }).start();
		
		p1.setBackground(Color.BLACK);
		p2.setBackground(Color.RED);
		p2.add(setTimeToCurrent);
		p2.add(newTime);
		p2.add(addSchedule);
		p2.add(editSchedule);
		p2.add(aboutButton);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS ));
		p3.add(Box.createVerticalGlue());
		backToMenu.setAlignmentX(Box.CENTER_ALIGNMENT);
		p3.add(backToMenu);
		
	
	mainCfg = new SLConfig(panel)
		.row(1f).col(1f)
		.place(0, 0, p1);
	
	p1Cfg = new SLConfig(panel)
		.row(11f).row(1f).col(1f)
		.place(0, 0, p1)
		.place(1, 0, p2);

	p2Cfg = new SLConfig(panel)
		.row(1f).col(1f)
		.place(0, 0, p3);
	
	panel.setTweenManager(SLAnimator.createTweenManager());
	panel.initialize(mainCfg);

	}
	private void disableActions() {
		p1.disableAction();
		p2.disableAction();
		p3.disableAction();
	}

	private void enableActions() {
		p1.enableAction();
		p2.enableAction();
		p3.enableAction();
	}
	
	private final Runnable menuAction = new Runnable() {@Override public void run() {

		panel.createTransition()
			.push(new SLKeyframe(p1Cfg, 0.6f)
				.setStartSide(SLSide.BOTTOM, p2)
				.setCallback(new SLKeyframe.Callback() {@Override public void done() {
					p1.setAction(splashAction);
					p1.enableAction();
				}}))
			.play();
	}};
	

	private final Runnable calendarAction = new Runnable() {@Override public void run() {
		//disableActions();

		panel.createTransition()
			.push(new SLKeyframe(p2Cfg, 0.6f)
				.setEndSide(SLSide.LEFT, p1,p2)
				.setStartSide(SLSide.RIGHT, p3)
				.setCallback(new SLKeyframe.Callback() {@Override public void done() {
				}}))
			.play();
	}};


	private final Runnable endCalendarAction = new Runnable() {@Override public void run() {
		//disableActions();

		panel.createTransition()
			.push(new SLKeyframe(p1Cfg, 0.6f)
				.setEndSide(SLSide.RIGHT, p3)
				.setStartSide(SLSide.LEFT, p1,p2)
				.setCallback(new SLKeyframe.Callback() {@Override public void done() {
				}}))
			.play();
	}};
	
	private final Runnable splashAction = new Runnable() {@Override public void run() {
		//disableActions();

		panel.createTransition()
			.push(new SLKeyframe(mainCfg, 0.6f)
				.setEndSide(SLSide.BOTTOM, p2)
				.setCallback(new SLKeyframe.Callback() {@Override public void done() {
					p1.setAction(menuAction);
					p1.enableAction();
				}}))
			.play();
	}};

	protected ImageIcon createImageIcon(String path,
			String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	public String getName(){
		return modifyName;
	}

}