package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

public class PictureViewer extends JPanel{
	private static final long serialVersionUID = 1L;
	ImageIcon[] images;
	String[] fileNames;
	int i;
	
	public PictureViewer(int width, int height,String path){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		fileNames = new String[listOfFiles.length];
		images = new ImageIcon[listOfFiles.length];
		i = 0;
		for (int j=0;j<listOfFiles.length;j++){
			images[j] = createImageIcon(path + "" + listOfFiles[j].getName());
			fileNames[j] = listOfFiles[j].getName();
		}
		final int NUM_OF_PICS = listOfFiles.length;
		
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel first = new JPanel();
		JPanel second = new JPanel();
		JPanel third = new JPanel();
		
		final JLabel pictureArea = new JLabel();
        pictureArea.setHorizontalAlignment(JLabel.CENTER);
        pictureArea.setVerticalAlignment(JLabel.CENTER);
        first.add(pictureArea);
		
		final JTextArea display = new JTextArea();
		second.add(display);

		JButton back = new JButton("<--");
		third.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
        			update(-1,NUM_OF_PICS,pictureArea,display);
			}
		});
		
		JButton forth = new JButton("-->");
		third.add(forth);
		forth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
        		update(1,NUM_OF_PICS,pictureArea,display);
			}
		});
		
		
		add(first);
		add(second);
		add(third);
	}
	private int moveI(int increment, int max){
		int temp = i + increment;
		if (temp < 0)
			temp = max-1;
		if (temp >= max)
			temp = 0;
		return temp;
	}
	private void update(int increment, int max,JLabel picture,JTextArea display){
		
		i = moveI(increment, max);
		ImageIcon temp = images[i];
		Image img = temp.getImage();
		Image newimg = img;
		if (temp.getIconHeight() > 650){
			newimg = img.getScaledInstance(-1, 650,  java.awt.Image.SCALE_SMOOTH);
			temp = new ImageIcon(newimg);
		}
		picture.setIcon(temp);
		display.setText(fileNames[i]);
	}
	
	 protected static ImageIcon createImageIcon(String path2) {
	        java.net.URL imageURL;
			try {
				imageURL = new File(path2).toURI().toURL();
				return new ImageIcon(imageURL);
			} catch (MalformedURLException e) {
				System.err.println("Resource not found: " + path2);
				e.printStackTrace();
			}
			return null;
	    } 
	
	private static void createAndShowGui(){
		int width = 500, height = 300;
		JFrame frame = new JFrame("Picture Viewer");
		frame.setSize(new Dimension(width, height));
		frame.setResizable(true);
		String path = JOptionPane.showInputDialog(null, "Enter file path:", 16);
		frame.setContentPane(new PictureViewer(width, height,path)); /* Adds the panel to the frame */

		/* Centralizing the frame */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int upperLeftCornerX = (screenSize.width - frame.getWidth()) / 2;
		int upperLeftCornerY = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(upperLeftCornerX, upperLeftCornerY);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/* Shows the GUI */
		frame.setVisible(true);
	
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGui();
			}
		});
			
	}

}