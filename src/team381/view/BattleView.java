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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.City;
import engine.Game;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Infantry;
import units.Unit;

public class BattleView extends JFrame implements ActionListener, MouseListener {
	private Army playerArmy;
	private Army cityArmy;
	private WorldMap WMap;
	private Game NewGame;
	private JLabel back;
	private JLabel attack;
	private JLabel autoResolve;
	private JPanel attackerUnits;
	private JPanel defenderUnits;
	private JLabel log;
	private Font pixelArt;
	private JLabel display;
	private String displayInfo;
	private JLabel bg;
	private JLabel edrab;
	private JLabel balashWaga3Demagh;
	private JLabel endTurn;
	private JComboBox cities;
	private City chosenCity;
	private ArrayList<JButton> buttons;
	private int localBattleTurnCount;
	private ArrayList<String> c;
	
	
	
	public  BattleView( WorldMap WMap, Game NewGame) {
		
		this.WMap = WMap;
		this.NewGame = NewGame;
		
		
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("TERMINATOR");
	     ImageIcon I = new ImageIcon("images/icon.jpg") ;
		this.setIconImage(I.getImage());
		this.setBackground(Color.black);
		this.localBattleTurnCount = 0;
		
		
		
		
		
		
		try {
	       	pixelArt = Font.createFont(Font.TRUETYPE_FONT, new File("pixelart.ttf")).deriveFont(20f);
	       	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        	ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("pixelart.ttf")));
	        }
	       
	        catch(IOException | FontFormatException e) {
	       	
	        }
			
		
			
			display = new JLabel();
	        display.setPreferredSize(new Dimension(100, 50));
	        display.setFont(pixelArt);
	        display.setForeground(Color.black);
	        display.setBounds(450, 0, 900, 50);
	        update();
	        display.setText(displayInfo);
	        this.add(display);
	        display.addMouseListener(this);
	        
			
			
			
			
			back = new JLabel();
	        back.setText("Go back");
	        back.setFont(pixelArt);
	        back.setBounds(10, 0, 200, 50);
	        back.setForeground(Color.black);
	        back.addMouseListener(this);
	        this.add(back);
	        
	        
	        endTurn = new JLabel();
	        endTurn.setText("END TURN");
	        endTurn.setPreferredSize(new Dimension(100, 50));
	        endTurn.setFont(pixelArt);
	        endTurn.setForeground(Color.black);
	        endTurn.setBounds(1390, 0, 200, 50);
	        endTurn.addMouseListener(this);
	        this.add(endTurn);
	        
			
			
			
			
	        balashWaga3Demagh = new JLabel();
	        balashWaga3Demagh.setPreferredSize(new Dimension(200,200));
	        balashWaga3Demagh.setBounds(890, 90, 300, 300);
	        balashWaga3Demagh.setIcon(new ImageIcon("images/auto.gif"));
		    this.add(balashWaga3Demagh);
		    
		    
		    
		    edrab = new JLabel();
		    edrab.setPreferredSize(new Dimension(200,200));
		    edrab.setBounds(310, 90, 300, 300);
		    edrab.setIcon(new ImageIcon("images/attackk.gif"));
		    this.add(edrab);
	        
	        
	        
			
			attack = new JLabel();
			attack.setText("Attack");
			attack.setFont(pixelArt);
			attack.setBounds(430, 270, 300, 300);
			attack.setForeground(Color.black);
			attack.addMouseListener(this);
			this.add(attack);
			
			
			
			autoResolve = new JLabel();
			autoResolve.setText("Auto Resolve");
			autoResolve.setFont(pixelArt);
			autoResolve.setBounds(960, 270, 300, 300);
			autoResolve.setForeground(Color.black);
			autoResolve.addMouseListener(this);
			this.add(autoResolve);
			
			
		
//			String [] enemyCityComboBoxList = new String [c.size()];
//			enemyCityComboBoxList = c.toArray(enemyCityComboBoxList);
//	        cities = new JComboBox(enemyCityComboBoxList);
//            cities.setBounds(570, 400, 300, 30);
//	        this.add(cities);
//	        cities.addMouseListener(this);
//	        cities.setBackground(Color.black);
//	        cities.setForeground(Color.blue);
//	        cities.setFont(pixelArt);
//	        this.cityArmy.getUnits().add(new Archer(0, 1 ,2, 3 ,4));
//	        this.cityArmy.getUnits().add(new Infantry(0,0,0,4,5));
	        
	       
//	        attackerUnits = new JPanel();
//	        attackerUnits.setBackground(Color.white);
//	        GridLayout unitLayout = new GridLayout(0,5);
//	        attackerUnits.setLayout(unitLayout);
//	        attackerUnits.setOpaque(false);
//	        attackerUnits.setBounds(0, 200, 300, 500);
//	        attackerUnits.setPreferredSize(new Dimension(400, 200));
//	        for(int i = 0 ; i < NewGame.getPlayer().getControlledArmies().get(0).getUnits().size(); i++) {
//	        	JButton unitAttackBtn = new JButton();
//	        	unitAttackBtn.addActionListener(this);
//	        	unitAttackBtn.setText(NewGame.getPlayer().getControlledArmies().get(0).getUnits().get(i).getClass().toString());
//	        	attackerUnits.add(unitAttackBtn);	 	
//	        }
//	        this.add(attackerUnits);
////	        
//	        
//	        defenderUnits = new JPanel();
//	        defenderUnits.setBackground(Color.white);
//	        defenderUnits.setLayout(unitLayout);
//	        defenderUnits.setOpaque(false);
//	        defenderUnits.setBounds(0, 200, 300, 500);
//	        defenderUnits.setPreferredSize(new Dimension(400, 200));
//	        for(int i = 0 ; i < NewGame.getPlayer().getControlledArmies().get(0).getUnits().size(); i++) {
//	        	JButton unitAttackBtn = new JButton();
//	        	unitAttackBtn.addActionListener(this);
//	        	unitAttackBtn.setText(NewGame.getPlayer().getControlledArmies().get(0).getUnits().get(i).getClass().toString());
//	        	defenderUnits.add(unitAttackBtn);	 	
//	        }
//	        this.add(defenderUnits);
//	        
//			
			
			
			bg = new JLabel();
		       bg.setIcon(new ImageIcon("images/fu.jpg"));
		       bg.setBounds(0, 0, this.getWidth(), this.getHeight());
		       this.add(bg);
		       
		       
		       
		  
			
		       for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
					if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Cairo")) {
						chosenCity = NewGame.getAvailableCities().get(i);
						break;
					}
				}
					
//					for (int i = 0; i < chosenCity.getDefendingArmy().getUnits().size(); i++) {
//						JButton b =  new JButton();
//						b.addActionListener(this);
//						b.setText(chosenCity.getDefendingArmy().getUnits().get(i).toString());
//						attackerUnits.add(b);
//						buttons.add(b);
//				}
			
			
//		   	ArrayList<String> c = new ArrayList<String>();
//			c.add("Rome");
//			c.add("Cairo");
//			c.add("Sparta");
//			int numberOfControlledCities = NewGame.getPlayer().getControlledCities().size();
//			for(int i = 0; i < numberOfControlledCities; i++) {
//				if( NewGame.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Rome")) {
//					c.remove("Rome");
//				}
//				else if( NewGame.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Cairo")) {
//					c.remove("Cairo");
//				}
//				else if( NewGame.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Sparta")) {
//					c.remove("Sparta");
//				}
//			}
			
			
			
			this.validate();
			this.revalidate();
	    	 
	    
		
	}
		
		

		
		
		
	
		
		

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		
		
	}
	
	
	public void update() {
		displayInfo =  NewGame.getPlayer().getName() + "   "  + "TURN  "+ NewGame.getCurrentTurnCount() + "   " +  NewGame.getPlayer().getFood() + "  FOOD" + "   " + NewGame.getPlayer().getTreasury() + "  GOLD";
		display.setText(displayInfo);
		WMap.getDisplay().setText(displayInfo);
   }


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == back) {
			  
	          WMap.setVisible(true);
	          this.dispose();

	      }
		
		
		if (e.getSource() == attack) {
				int PlayerUnitEPowerLevel = NewGame.getPlayer().getControlledArmies().get(0).getUnits().get(this.localBattleTurnCount).getLevel() * (int) NewGame.getPlayer().getControlledArmies().get(0).getUnits().get(this.localBattleTurnCount).getMarchingUpkeep();
				int EnemyUnitEPowerLevel = (int) (this.cityArmy.getUnits().get(this.localBattleTurnCount).getLevel() *  this.cityArmy.getUnits().get(this.localBattleTurnCount).getSiegeUpkeep());
				if(PlayerUnitEPowerLevel >= EnemyUnitEPowerLevel) {
					System.out.println("Player" + this.NewGame.getPlayer().getControlledArmies().get(0).getUnits().get(this.localBattleTurnCount).getClass().toString() + "unit has won this round and Enemy unit" + this.cityArmy.getUnits().get(this.localBattleTurnCount).getClass().toString() + "has lost!");
				}
				else {
					System.out.println("Enemy" + this.cityArmy.getUnits().get(this.localBattleTurnCount).getClass().toString() + "unit has won this round and Player unit" + this.NewGame.getPlayer().getControlledArmies().get(0).getUnits().get(this.localBattleTurnCount).getClass().toString() + "has lost!");
				}
			
		}
		
		if (e.getSource() == autoResolve) {
			
		}
		
		
		
		
		if (e.getSource() == endTurn) {
			this.localBattleTurnCount += 1;
	    	 
	    	  if (NewGame.getCurrentTurnCount() < 50) {
	    	  NewGame.endTurn();
	    	  update();
	    	  }
	    	  else if (NewGame.getCurrentTurnCount() == 50) {
	    		  this.dispose();
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
		if (e.getSource() == back) {
			back.setForeground(Color.white);
		}
		
		if (e.getSource() == attack) {
			attack.setForeground(Color.white);
		}
		
		if (e.getSource() == endTurn) {
			endTurn.setForeground(Color.white);
		}
		
		if (e.getSource() == autoResolve) {
			autoResolve.setForeground(Color.white);
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		back.setForeground(Color.black);
		attack.setForeground(Color.black);
		endTurn.setForeground(Color.black);
		autoResolve.setForeground(Color.black);
		
		
	}
	
	 public static void main(String[] args) {
		 
	 }

}


