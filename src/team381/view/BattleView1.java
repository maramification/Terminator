package team381.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.City;
import engine.Game;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Unit;

public class BattleView1 extends JFrame implements MouseListener, ActionListener  {
   
	private ArrayList<JButton> playerUnits;
	private ArrayList<JButton> enemyUnits;
	private WorldMap WMap;
	private Game NewGame;
	private Army attacker;
	private Army defender;
	private City city;
	private JPanel playerPanel;
	private JPanel enemyPanel;
	private JLabel attack;
	private Font pixelArt;
	private JLabel defenderSoldierCount;
	private JLabel attackerSoldierCount;
	private Unit playerUnit = null;
	private Unit enemyUnit = null;
	
	
	
	
//	public BattleView1() {
//    	
//    }
    
    public BattleView1(WorldMap WMap, Game NewGame, Army attacker, City city)  {
    	this.WMap = WMap;
    	this.NewGame = NewGame;
    	this.attacker = attacker;
    	this.city = city;
    	defender = city.getDefendingArmy();
    	
    	this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("TERMINATOR");
	     ImageIcon I = new ImageIcon("images/icon.jpg") ;
		this.setIconImage(I.getImage());
		
		
		try {
	       	pixelArt = Font.createFont(Font.TRUETYPE_FONT, new File("pixelart.ttf")).deriveFont(20f);
	       	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        	ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("pixelart.ttf")));
	        }
	       
	        catch(IOException | FontFormatException e) {
	       	
	        }
		
		enemyPanel = new JPanel();
        enemyPanel.setBackground(Color.black);
        enemyPanel.setLayout(new GridLayout(0,1));
        enemyPanel.setBounds(0, 200, 300, 500);
        //enemyPanel.setPreferredSize(new Dimension(400, 200));
        this.add(enemyPanel, BorderLayout.WEST);
		
		playerPanel = new JPanel();
		playerPanel.setBackground(Color.black);
		playerPanel.setLayout(new GridLayout(0,1));
		playerPanel.setBounds(400, 200, 300, 500);
        this.add(playerPanel, BorderLayout.EAST); 
        //attackerUnits.setPreferredSize(new Dimension(400, 200));
        
        
//        attack = new JLabel();
//		attack.setText("Attack");
//		attack.setFont(pixelArt);
//		attack.setBounds(600, 700, 300, 300);
//		attack.setForeground(Color.black);
//		attack.addMouseListener(this);
//		this.add(attack);
      
        
        
	for (int i = 0; i < attacker.getUnits().size(); i++) {
			
			if (attacker.getUnits().get(i) instanceof Archer) {
				String s = "Archer" + attacker.getUnits().get(i).getLevel();
				JButton b =  new JButton(s);
				b.addActionListener(this);
				playerPanel.add(b);
				playerUnits.add(b);
			}
			
			else if (attacker.getUnits().get(i) instanceof Cavalry) {
			   
				String s="Cavalry" + attacker.getUnits().get(i).getLevel();
				JButton b =  new JButton(s);
				b.addActionListener(this);
				playerPanel.add(b);
				playerUnits.add(b);
				
			}
			
			else {
				String s="Infantry " + attacker.getUnits().get(i).getLevel();
				JButton b =  new JButton(s);
				b.addActionListener(this);
				playerPanel.add(b);
				playerUnits.add(b);
				
				
			}
			
			
			
    	}
	
	
        
        
        
    	for (int i = 0; i < defender.getUnits().size(); i++) {
			//System.out.println(i);
			if (defender.getUnits().get(i) instanceof Archer) {
				String s = "Archer" + defender.getUnits().get(i).getLevel();
				JButton b =  new JButton(s);
				b.addActionListener(this);
				enemyUnits.add(b);
				enemyPanel.add(b);
			}
			
			else if (defender.getUnits().get(i) instanceof Cavalry) {
			   
				String s="Cavalry" + defender.getUnits().get(i).getLevel();
				JButton b =  new JButton(s);
				b.addActionListener(this);
				enemyUnits.add(b);
				enemyPanel.add(b);
				
			}
			
			else {
				String s="Infantry " + defender.getUnits().get(i).getLevel();
				JButton b =  new JButton(s);
				b.addActionListener(this);
				enemyUnits.add(b);
				enemyPanel.add(b);
				
				
				
			}
			
			
			
    	}
    	
    	
    	defenderSoldierCount = new JLabel();
		defenderSoldierCount.setFont(pixelArt);
		defenderSoldierCount.setBounds(200, 700, 300, 300);
		defenderSoldierCount.setForeground(Color.blue);
		defenderSoldierCount.addMouseListener(this);
		this.add(defenderSoldierCount);
		
		
		attackerSoldierCount = new JLabel();
		attackerSoldierCount.setFont(pixelArt);
		attackerSoldierCount.setBounds(600, 700, 300, 300);
		attackerSoldierCount.setForeground(Color.blue);
		attackerSoldierCount.addMouseListener(this);
		this.add(attackerSoldierCount);
    	
    	
    	
    	
    	
			
		
    	
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (playerUnits.contains(b)) {
		int attackerIndex =	playerUnits.indexOf(b);
	    playerUnit  = 	attacker.getUnits().get(attackerIndex); 
	    attackerSoldierCount.setText("Your soldier count: " + playerUnit.getCurrentSoldierCount());
	    
	}
		
		else if (enemyUnits.contains(b)) {
			int defenderIndex =	enemyUnits.indexOf(b);
			enemyUnit  = defender.getUnits().get(defenderIndex); 
			defenderSoldierCount.setText("Enemy soldier count: " + playerUnit.getCurrentSoldierCount());
		}
		
		
		
		
		
		if (b.getActionCommand().equals("Attack")) {
			
			
			if (playerUnit == null || enemyUnit == null) {
				JOptionPane.showMessageDialog(null, "Make sure you picked yours and your enemies' unit!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			
			else {
				
				Army a = null;
				for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
					if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase(city.getName())) {
						a = NewGame.getPlayer().getControlledArmies().get(i);
						break;
					}
				}
				
				
				
				if (a != null) {
				
				try {
					playerUnit.attack(enemyUnit);
					attackerSoldierCount.setText("Your soldier count: " + playerUnit.getCurrentSoldierCount());
					defenderSoldierCount.setText("Enemy soldier count: " + playerUnit.getCurrentSoldierCount());
					
					
				} catch (FriendlyFireException e1) {
					JOptionPane.showMessageDialog(null, "You're attacking a unit of your own army!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
			}
			
			
		}
		
		
		
		
		
	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
