package lampGui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.ColorUIResource;

import midend.MidEndFormatting;

import aurelienribon.slidinglayout.SLAnimator;
import aurelienribon.tweenengine.Tween;

public class LAMP{
	
	private static void createAndShowGui(){
		Tween.registerAccessor(LampPanel.class, new LampPanel.Accessor());
		SLAnimator.start();
		LampFrame frame = new LampFrame("");
		
		if (MidEndFormatting.listOfEntityGroups().length > 1){
			frame = new LampFrame((String)JOptionPane.showInputDialog(
                    frame, "Which group are you looking for?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null, MidEndFormatting.listOfEntityGroups(), ""));
		}
		else if (MidEndFormatting.listOfEntityGroups().length == 1){
			frame = new LampFrame(MidEndFormatting.listOfEntityGroups()[0]);
		}
		else{
			frame = new LampFrame((String)JOptionPane.showInputDialog(
                    frame, "What should the group of people you want to organize be called?",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    null, null, ""));
		}
		
		frame.setSize(900, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// reverting to standard look and feel
		}
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGui();
			}
		});
			
			
	}

}
