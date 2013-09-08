package model;

import java.awt.*;
import java.io.Serializable;

import javax.swing.JLabel;

public class TimeSlot implements Serializable{
	
	private String name;
	private Color color;
	
	public TimeSlot(){
		this.name = "FREE";
		this.color = Color.WHITE;
	}
	
	public TimeSlot(String name, Color color){
		this.name = name;
		this.color = color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public String getName(){
		return name;
	}
	
}
