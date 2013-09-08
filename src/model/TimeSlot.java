package model;

import java.awt.*;
import javax.swing.JLabel;

public class TimeSlot {
	
	private String name;
	private Color color;
	private JLabel label;
	
	public TimeSlot(){
		this.name = "FREE";
		this.color = Color.WHITE;
		this.label = new JLabel(name);
	}
	
	public TimeSlot(String name, Color color){
		this.name = name;
		this.color = color;
		this.label = new JLabel(name);
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public String getName(){
		return name;
	}
	
}
