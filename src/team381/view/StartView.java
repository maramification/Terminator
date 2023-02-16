package team381.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.Game;

public class StartView extends JFrame implements ActionListener, MouseListener {
	
	private JLabel bg;
	private JTextArea playerName;
	private JLabel startGame;
	private JComboBox cities;
	private JLabel selectCity;
	private JLabel enterName;
	private Font pixelArt;
	private Game NewGame;
	
	
	public StartView() {
		
		
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("TERMINATOR");
	     ImageIcon I = new ImageIcon("images/icon.jpg") ;
		this.setIconImage(I.getImage());
		
		
		
	;
		
		
		 try {
	        	pixelArt = Font.createFont(Font.TRUETYPE_FONT, new File("pixelart.ttf")).deriveFont(20f);
	        	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        	ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("pixelart.ttf")));
	        }
	        
	        catch(IOException | FontFormatException e) {
	        	
	        }
		 
			
			
			enterName = new JLabel();
			enterName.setFont(pixelArt);
		    enterName.setText("Enter name");
			enterName.setForeground(Color.MAGENTA);
			enterName.setBounds(690, 250, 300, 50);
			enterName.addMouseListener(this);
			enterName.addMouseListener(this);
			this.add(enterName);
			
			
			
//			selectCity = new JLabel();
//			selectCity.setFont(pixelArt);
//			selectCity.setText("Select your city");
//			selectCity.setForeground(Color.magenta);
//			selectCity.setBounds(650, 450, 300, 50);
//			selectCity.addMouseListener(this);
//			this.add(selectCity);
//			
			
			String[] c = {"Cairo", "Rome", "Sparta"};
			cities = new JComboBox(c);
			cities.setForeground(Color.magenta);
			cities.setBackground(Color.black);
			cities.setFont(pixelArt);
			cities.setBounds(620, 390, 300, 50);
			cities.addMouseListener(this);
			this.add(cities);
			
			
			
			playerName = new JTextArea();
			playerName.setEditable(true);
			playerName.setFont(pixelArt);
			playerName.setForeground(Color.magenta);
			playerName.setBackground(Color.black);
			playerName.setBounds(620, 300, 300, 50);
			playerName.addMouseListener(this);
			this.add(playerName);
			
			
			startGame = new JLabel();
			startGame.setText("START");
			startGame.setBounds(720, 500, 300, 50);
			startGame.setFont(pixelArt);
			startGame.setForeground(Color.magenta);
			startGame.addMouseListener(this);
			this.add(startGame);
			
			
			
			 bg = new JLabel();
		       bg.setIcon(new ImageIcon("images/startt.gif"));
		       bg.setBounds(0, 0, this.getWidth(), this.getHeight());
		       this.add(bg);
		       
		       
		       
		       this.validate();
				this.revalidate();
	}
	
	
	
	public static void main(String[] args) {
		new StartView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == startGame) {
			//System.out.println("here");
			if (playerName.getText().equals("") || cities.getSelectedItem() == null || cities.getSelectedItem().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please make sure you submitted a name and a city", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				try {
					NewGame = new Game(playerName.getText(), cities.getSelectedItem().toString());
					//System.out.println(playerName.getText() + " " + selectedCity.getText());
						WorldMap WMap = new WorldMap(this, NewGame);
						this.setVisible(false);
					}
				
				catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Please make sure you submitted a name and a city", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
		}
			
		}
		
		
		
	}
	
	
	
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == startGame) {
			startGame.setForeground(Color.white);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		startGame.setForeground(Color.magenta);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
