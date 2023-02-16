package team381.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import engine.Player;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;

public class WorldMap extends JFrame implements ActionListener, MouseListener {
	
	private Game NewGame;
	private StartView firstView;
	private Font pixelArt;
	private JLabel bg;
	private JButton sparta;
	private JButton rome;
	private JButton cairo;
	private JLabel display;
    private String displayInfo;
    private JLabel marching;
    private JLabel besieging;
    private JLabel idle;
    private JTextArea armyInfo;
    private Unit currUnit;
	private Army currArmy;
	private City currCity;
	private String unitInfoMarch = "";
	private String unitInfoIdle = "";
	private String unitInfoBesiege = "";
	private JLabel laySiegeCairo;
	private JLabel laySiegeRome;
	private JLabel laySiegeSparta;
	private JComboBox cities;
	private Army pArmy;
	private City cc;
	private  Army selectedArmy;
	//private  JLabel goToBattle;
	private JLabel attackCairo;
	private JLabel attackRome;
	private JLabel attackSparta;
	private JLabel autoResolveCairo;
	private JLabel autoResolveRome;
	private JLabel autoResolveSparta;
	private Army a;
	private City c;
	private JComboBox<Army> playerArmies;
	//private JTextArea ctrldArmies;
	private String ca = "";
	private JLabel yourArmies;
	private JLabel endTurn;
	private JLabel targetCairo;
	private JLabel targetRome;
	private JLabel targetSparta;
	private City targetCity;
	private Cairo cairoView;
	private Rome romeView;
	private Sparta spartaView;
	
    
    
    
    


	
	public WorldMap(StartView firstView, Game NewGame) {
		this.firstView = firstView;
		this.NewGame = NewGame;
//		Unit u1 = new Archer(1,1,0.8,0.8,0.9);
//		Unit u2 = new Infantry(1,1,0.8,0.8,0.9);
//		this.NewGame.getPlayer().getControlledArmies().add(new Army("Cairo"));
//		this.NewGame.getPlayer().getControlledArmies().get(0).getUnits().add(u1);
//		this.NewGame.getPlayer().getControlledArmies().get(0).getUnits().add(u2);

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
		 
		 
		 
		 

			   displayInfo =  NewGame.getPlayer().getName() + "   " + "TURN  " + NewGame.getCurrentTurnCount() +  "   " +  NewGame.getPlayer().getFood() + "  FOOD" + "   " + NewGame.getPlayer().getTreasury() + "  GOLD";
			
		 
		 
		 
		 display = new JLabel();
	        display.setPreferredSize(new Dimension(100, 50));
	        display.setFont(pixelArt);
	        display.setForeground(Color.white);
	        display.setBounds(450, 0, 900, 50);
	        display.setText(displayInfo);
	        this.add(display);
	        display.addMouseListener(this);
	        
	        
	        
	        
	        
	        
	         cairoView = new Cairo(this, NewGame);
	         romeView = new Rome(this, NewGame);
	         spartaView = new Sparta(this, NewGame);
	        
	        cairoView.setVisible(false);
	        romeView.setVisible(false);
	        spartaView.setVisible(false);
	        
	        
	        
	        
	        
	        endTurn = new JLabel();
	        endTurn.setText("END TURN");
	        endTurn.setPreferredSize(new Dimension(100, 50));
	        endTurn.setFont(pixelArt);
	        endTurn.setForeground(Color.white);
	        endTurn.setBounds(1390, 0, 200, 50);
	        endTurn.addMouseListener(this);
	        this.add(endTurn);
	        
	        
	        
	        rome = new JButton();
		    rome.setPreferredSize(new Dimension(200,200));
		    rome.setBounds(290, 90, 300, 300);
		    rome.setText("Rome");
		    rome.setFont(pixelArt);
		    rome.setForeground(Color.blue);
            rome.addMouseListener(this);
            rome.addActionListener(this);
		    rome.setBackground(Color.black);
		    rome.setEnabled(checkCityIsControlled("Rome"));
		    this.add(rome);

	        
		    sparta = new JButton();
		    sparta.setPreferredSize(new Dimension(200,200));
		    sparta.setBounds(890, 90, 300, 300);
		    sparta.setText("Sparta");
		    sparta.setFont(pixelArt);
		    sparta.setForeground(Color.blue);
            sparta.addMouseListener(this);
            sparta.addActionListener(this);
		    sparta.setBackground(Color.black);
		    sparta.setEnabled(checkCityIsControlled("Sparta"));
		    this.add(sparta);
		    
		    
		    cairo = new JButton();
		    cairo.setPreferredSize(new Dimension(200,200));
		    cairo.setBounds(590, 90, 300, 300);
		    cairo.setText("Cairo");
		    cairo.setFont(pixelArt);
		    cairo.setForeground(Color.blue);
            cairo.addMouseListener(this);
            cairo.addActionListener(this);
		    cairo.setBackground(Color.black);
		    cairo.setEnabled(checkCityIsControlled("Cairo"));
		    this.add(cairo);
		 
		    armyInfo = new JTextArea();
	        armyInfo.setBackground(Color.black);
	        armyInfo.setEditable(false);
	        armyInfo.setBounds(600, 600, 700, 100);
	        armyInfo.setForeground(Color.blue);
	        armyInfo.setFont(new Font("Arial", Font.BOLD, 10));
	        //buildingsInfo.setText("Level: 1 Building Type: Archery Range");
	        armyInfo.addMouseListener(this);
	        this.add(armyInfo);
	        
	        
	        
	        yourArmies = new JLabel();
	        yourArmies.setBounds(270, 670, 200, 50);
	        yourArmies.setText("Select Army");
	        yourArmies.setFont(pixelArt);
	        yourArmies.setForeground(Color.white);
	        yourArmies.addMouseListener(this);
	        this.add(yourArmies);

	        
	        marching = new JLabel();
	        marching.setBounds(290, 730, 200, 50);
	        marching.setText("Marching");
	        marching.setFont(pixelArt);
	        marching.setForeground(Color.white);
	        marching.addMouseListener(this);
	        this.add(marching);
	        
	        besieging = new JLabel();
	        besieging.setBounds(690, 730, 200, 50);
	        besieging.setText("Besieging");
	        besieging.setFont(pixelArt);
	        besieging.setForeground(Color.white);
	        besieging.addMouseListener(this);
	        this.add(besieging);
	        
	        idle = new JLabel();
	        idle.setBounds(1100, 730, 200, 50);
	        idle.setText("Idle");
	        idle.setFont(pixelArt);
	        idle.setForeground(Color.white);
	        idle.addMouseListener(this);
	        this.add(idle);
	               
	       
	       
	       laySiegeCairo = new JLabel();
	       laySiegeCairo.setForeground(Color.white);
	       laySiegeCairo.setBounds(640, 400, 300, 50);
	       laySiegeCairo.setText("Lay Siege Cairo");
	       laySiegeCairo.setFont(pixelArt);
	       laySiegeCairo.addMouseListener(this);
	       this.add(laySiegeCairo);
	       
	       
	       
	       laySiegeRome = new JLabel();
	       laySiegeRome.setForeground(Color.white);
	       laySiegeRome.setBounds(340, 400, 300, 50);
	       laySiegeRome.setText("Lay Siege Rome");
	       laySiegeRome.setFont(pixelArt);
	       laySiegeRome.addMouseListener(this);
	       this.add(laySiegeRome);
	       
	       
	       laySiegeSparta = new JLabel();
	       laySiegeSparta.setForeground(Color.white);
	       laySiegeSparta.setBounds(940, 400, 300, 50);
	       laySiegeSparta.setText("Lay Siege Sparta");
	       laySiegeSparta.setFont(pixelArt);
	       laySiegeSparta.addMouseListener(this);
	       this.add(laySiegeSparta);
	       
	       
	       attackCairo = new JLabel();
	       attackCairo.setBounds(660, 450, 300, 50);
	       attackCairo.setText("Attack Cairo");
	       attackCairo.setForeground(Color.white);
	       attackCairo.setFont(pixelArt);
	       attackCairo.addMouseListener(this);
	       this.add(attackCairo);
	       
	       attackRome = new JLabel();
	       attackRome.setBounds(350, 450, 300, 50);
	       attackRome.setText("Attack Rome");
	       attackRome.setForeground(Color.white);
	       attackRome.setFont(pixelArt);
	       attackRome.addMouseListener(this);
	       this.add(attackRome);
	       
	       
	       attackSparta = new JLabel();
	       attackSparta.setBounds(960, 450, 300, 50);
	       attackSparta.setText("Attack Sparta");
	       attackSparta.setForeground(Color.white);
	       attackSparta.setFont(pixelArt);
	       attackSparta.addMouseListener(this);
	       this.add(attackSparta);
	       
	       
	       autoResolveCairo = new JLabel();
	       autoResolveCairo.setBounds(620, 500, 300, 50);
	       autoResolveCairo.setText("Auto Resolve Cairo");
	       autoResolveCairo.setForeground(Color.white);
	       autoResolveCairo.setFont(pixelArt);
	       autoResolveCairo.addMouseListener(this);
	       this.add(autoResolveCairo);
	       
	       
	       autoResolveRome = new JLabel();
	       autoResolveRome.setBounds(320, 500, 300, 50);
	       autoResolveRome.setText("Auto Resolve Rome");
	       autoResolveRome.setForeground(Color.white);
	       autoResolveRome.setFont(pixelArt);
	       autoResolveRome.addMouseListener(this);
	       this.add(autoResolveRome);
	       
	       
	       autoResolveSparta = new JLabel();
	       autoResolveSparta.setBounds(920, 500, 300, 50);
	       autoResolveSparta.setText("Auto Resolve Sparta");
	       autoResolveSparta.setForeground(Color.white);
	       autoResolveSparta.setFont(pixelArt);
	       autoResolveSparta.addMouseListener(this);
	       this.add(autoResolveSparta);
	       
	       
	       targetSparta = new JLabel();
	       targetSparta.setBounds(950, 540, 300, 50);
	       targetSparta.setText("Target Sparta");
	       targetSparta.setForeground(Color.white);
	       targetSparta.setFont(pixelArt);
	       targetSparta.addMouseListener(this);
	       this.add(targetSparta);
	       
	       targetRome = new JLabel();
	       targetRome.setBounds(350, 540, 300, 50);
	       targetRome.setText("Target Rome");
	       targetRome.setForeground(Color.white);
	       targetRome.setFont(pixelArt);
	       targetRome.addMouseListener(this);
	       this.add(targetRome);
	       
	       targetCairo = new JLabel();
	       targetCairo.setBounds(640, 550, 300, 50);
	       targetCairo.setText("Target Cairo");
	       targetCairo.setForeground(Color.white);
	       targetCairo.setFont(pixelArt);
	       targetCairo.addMouseListener(this);
	       this.add(targetCairo);
	       
	       
	       
   
	        String[] armiez = new String[NewGame.getPlayer().getControlledArmies().size()];
	        
	        for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++ ) {
	        	armiez[i] = NewGame.getPlayer().getControlledArmies().get(i).toString();
	        }
	        
	        
	        playerArmies = new JComboBox();
            playerArmies.setBounds(200, 640, 300, 30);
	        playerArmies.addMouseListener(this);
	        playerArmies.setBackground(Color.black);
	        playerArmies.setForeground(Color.blue);
	        playerArmies.setFont(pixelArt);
	        this.add(playerArmies);
	        
	        

	        
	        
	        
//	        playerArmies = new JComboBox();
//            playerArmies.setBounds(200, 640, 300, 30);
//	        playerArmies.addMouseListener(this);
//	        playerArmies.setBackground(Color.black);
//	        playerArmies.setForeground(Color.blue);
//	        playerArmies.setFont(pixelArt);
//	        this.add(playerArmies);
//	               
	        
	        
	        
	       
		 
		 
		 bg = new JLabel();
	       bg.setIcon(new ImageIcon("images/map.jpg"));
	       bg.setBounds(0, 0, this.getWidth(), this.getHeight());
	       this.add(bg);
	       
	       
	    
	    
	    
	    this.validate();
		this.revalidate();
		
		for (int i = 0; i < NewGame.getPlayer().getControlledCities().size(); i++) {
			if (NewGame.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Cairo")) {
				laySiegeCairo.setVisible(false);
				autoResolveCairo.setVisible(false);
				attackCairo.setVisible(false);
				targetCairo.setVisible(false);
			}
			
			
			if (NewGame.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Rome")) {
				laySiegeRome.setVisible(false);
				autoResolveRome.setVisible(false);
				attackRome.setVisible(false);
				targetRome.setVisible(false);
			}
			
			if (NewGame.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Sparta")) {
				laySiegeSparta.setVisible(false);
				autoResolveSparta.setVisible(false);
				attackSparta.setVisible(false);
				targetRome.setVisible(false);
			}
		}
		
		 
		
}

	public JLabel getDisplay() {
		return display;
	}

	public void setDisplay(JLabel display) {
		this.display = display;
	}

	public boolean checkCityIsControlled(String cityName) {
	for(int i = 0; i < this.NewGame.getPlayer().getControlledCities().size() ; i++) {
		if (this.NewGame.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase(cityName)) {
			return true;
		}
	}
	return false;
}

	




		



	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		
		
		
		
		
		
		if (e.getSource() == endTurn) {
	    	 if (NewGame.isGameOver() || NewGame.getCurrentTurnCount() == 50) {
	    		 if (NewGame.getAvailableCities().size() == NewGame.getPlayer().getControlledCities().size()) {
	    			 JOptionPane.showMessageDialog(null, "WINNER WINNER CHICKEN DINNER!! :D", "YOU WON", JOptionPane.ERROR_MESSAGE);
	    			 this.dispose();
	    		 }
	    		 
	    		 else {
	    			 JOptionPane.showMessageDialog(null, "SORE LOSER!", "YOU SUCK", JOptionPane.ERROR_MESSAGE);
	    			 this.dispose();
	    		
	    		 
	    		 }
	    		 
	    		 //System.out.println(NewGame.getCurrentTurnCount());
	    	 }
	    	 
	    	 
	    	 
	    	 
	    	 
	    	
	    	 
	    	 else {
	    		 
	    		 NewGame.endTurn();
	    		 update();
	    		// System.out.println(selectedArmy.getDistancetoTarget());
	    		 for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
	    		 
	    			 City t =  NewGame.getAvailableCities().get(i);
	    			 
	    		 if (t.getTurnsUnderSiege() >= 3) {
	    			 JOptionPane.showMessageDialog(null, "You must attack or auto-resolve with " + t.getName(), "Cannot perform action", JOptionPane.ERROR_MESSAGE);
	    		 }
	    		 }
	    		 
	    		 
	    	 }
	    	
	    	  
	      }
		
		
		if (e.getSource() == yourArmies) {
			
			selectedArmy = (Army) playerArmies.getSelectedItem();
			playerArmies.removeItem(playerArmies.getSelectedItem());
			
		}
		
		
		
		
		
		
		if (e.getSource() == targetCairo) {
			if (selectedArmy == null) {
				JOptionPane.showMessageDialog(null, "You must select an army.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				String target = null;
				for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
					if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Cairo")) {
						target = NewGame.getAvailableCities().get(i).getName();
						break;
					}
				}
				NewGame.targetCity(selectedArmy, target);
				targetCairo.setVisible(false);
				JOptionPane.showMessageDialog(null, "CAIRO TARGETED", "CITY TARGETED", JOptionPane.ERROR_MESSAGE);
				
				
			}
		}
		
		
		
		if (e.getSource() == targetRome) {
			if (selectedArmy == null) {
				JOptionPane.showMessageDialog(null, "You must select an army.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				String target = null;
				for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
					if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Rome")) {
						target = NewGame.getAvailableCities().get(i).getName();
						break;
					}
				}
				
				NewGame.targetCity(selectedArmy, target);
				targetRome.setVisible(false);
				JOptionPane.showMessageDialog(null, "ROME TARGETED", "CITY TARGETED", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		
		
		
		if (e.getSource() == targetSparta) {
			if (selectedArmy == null) {
				JOptionPane.showMessageDialog(null, "You must select an army.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				
				// System.out.println("here");
				String target = null;
				for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
					if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Sparta")) {
						target = NewGame.getAvailableCities().get(i).getName();
						break;
					}
				}
				
				NewGame.targetCity(selectedArmy, target);
				// System.out.println(selectedArmy.getDistancetoTarget());
				targetSparta.setVisible(false);
				JOptionPane.showMessageDialog(null, "SPARTA TARGETED", "CITY TARGETED", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		
		
		
		
		
		
		if (e.getSource() == laySiegeRome) {
			
			Army playerArmy = null;
			for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
				
				if(NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Rome")) {
					cc = NewGame.getAvailableCities().get(i);
					break;
				}
				
			}
			
			if (NewGame.getPlayer().getControlledArmies().size() == 0) {
				JOptionPane.showMessageDialog(null, "You have no armies!", "Cannot perform action", JOptionPane.ERROR_MESSAGE); 
				}
			
			
			for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
			
			if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Rome")) {
				playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
				break;
			}
			}
			
			if (playerArmy != null) {
			
			try {
				NewGame.getPlayer().laySiege(playerArmy, cc);
				JOptionPane.showMessageDialog(null, "Siege laid on city!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				laySiegeRome.setVisible(false);
				
			} catch (TargetNotReachedException e1) {
				JOptionPane.showMessageDialog(null, "Target not reached yet!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (FriendlyCityException e1) {
				JOptionPane.showMessageDialog(null, "You're attacking a friendly city!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} 
			
		
		}
		}
		
		
		
		
if (e.getSource() == laySiegeSparta) {
			Army playerArmy = null;
			
			for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
				
				if(NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Sparta")) {
					cc = NewGame.getAvailableCities().get(i);
					break;
				}
				
			}
			
			
			if (NewGame.getPlayer().getControlledArmies().size() == 0) {
				JOptionPane.showMessageDialog(null, "You have no armies!", "Cannot perform action", JOptionPane.ERROR_MESSAGE); 
				}
			
			
		
			for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
				
				if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Sparta")) {
					playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
					break;
			         	}
				}
			
			
			
			if (playerArmy != null) {
			
			try {
				NewGame.getPlayer().laySiege(playerArmy, cc);
				JOptionPane.showMessageDialog(null, "Siege laid on city!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				laySiegeSparta.setVisible(false);
				
			} catch (TargetNotReachedException e1) {
				JOptionPane.showMessageDialog(null, "Target not reached yet!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (FriendlyCityException e1) {
				JOptionPane.showMessageDialog(null, "You're attacking a friendly city!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} 
			
		
		}
}




if (e.getSource() == laySiegeCairo) {
	Army playerArmy = null;
	
	for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
		
		if(NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Sparta")) {
			cc = NewGame.getAvailableCities().get(i);
			break;
		}
		
	}
	
	
	
	if (NewGame.getPlayer().getControlledArmies().size() == 0) {
		JOptionPane.showMessageDialog(null, "You have no armies!", "Cannot perform action", JOptionPane.ERROR_MESSAGE); 
		}

	for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
		
		if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Sparta")) {
			playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
			break;
	         	}
		}
	if (playerArmy != null) {
	
	try {
		NewGame.getPlayer().laySiege(playerArmy, cc);
		JOptionPane.showMessageDialog(null, "Siege laid on city!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
		laySiegeCairo.setVisible(false);
		
	} catch (TargetNotReachedException e1) {
		JOptionPane.showMessageDialog(null, "Target not reached yet!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
		e1.printStackTrace();
	} catch (FriendlyCityException e1) {
		JOptionPane.showMessageDialog(null, "You're attacking a friendly city!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
		e1.printStackTrace();
	} 
	

}
}


	
	    	


			if (e.getSource() == autoResolveCairo) {
				
				City enemy = null;
				Army playerArmy = null;
				
				for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
					if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Cairo")) {
						enemy = NewGame.getAvailableCities().get(i);
						break;
					}
				}
					
					if (NewGame.getPlayer().getControlledArmies().size() == 0) {
						JOptionPane.showMessageDialog(null, "You have no armies!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					}
					
					else {
						Army enemyArmy = enemy.getDefendingArmy();
						
						if (enemyArmy != null) {
						for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
							if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Cairo")) {
								playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
								break;
							}
						}
						
						{
						try {
							NewGame.autoResolve(playerArmy, enemyArmy);
							if (playerArmy.getUnits().size() == 0) {
								JOptionPane.showMessageDialog(null, "YOU LOST.", "oopsie", JOptionPane.ERROR_MESSAGE);
							}
							else if (enemyArmy.getUnits().size() == 0) {
								JOptionPane.showMessageDialog(null, "YOU WON!! :D", "DING DING DING", JOptionPane.ERROR_MESSAGE);
							}
						} catch (FriendlyFireException e1) {
							JOptionPane.showMessageDialog(null, "You're attacking a friendly army!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}
						}
					}
				}
			
			
			
			
			
			
if (e.getSource() == autoResolveRome) {
				

City enemy = null;
Army playerArmy = null;

for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
	if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Rome")) {
		enemy = NewGame.getAvailableCities().get(i);
		break;
	}
}
	
	if (NewGame.getPlayer().getControlledArmies().size() == 0) {
		JOptionPane.showMessageDialog(null, "You have no armies!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
	}
	
	else {
		Army enemyArmy = enemy.getDefendingArmy();
		
		if (enemyArmy != null) {
		for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
			if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Rome")) {
				playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
				break;
			}
		}
		
		if (playerArmy != null) {
		try {
			NewGame.autoResolve(playerArmy, enemyArmy);
			if (playerArmy.getUnits().size() == 0) {
				JOptionPane.showMessageDialog(null, "YOU LOST.", "oopsie", JOptionPane.ERROR_MESSAGE);
			}
			else if (enemyArmy.getUnits().size() == 0) {
				JOptionPane.showMessageDialog(null, "YOU WON!! :D", "DING DING DING", JOptionPane.ERROR_MESSAGE);
			}
		} catch (FriendlyFireException e1) {
			JOptionPane.showMessageDialog(null, "You're attacking a friendly army!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
		}
	}
	}




if (e.getSource() == autoResolveSparta) {
	

City enemy = null;
Army playerArmy = null;

for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
	if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Sparta")) {
		enemy = NewGame.getAvailableCities().get(i);
		break;
	}
}
	
	if (NewGame.getPlayer().getControlledArmies().size() == 0) {
		JOptionPane.showMessageDialog(null, "You have no armies!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
	}
	
	else {
		Army enemyArmy = enemy.getDefendingArmy();
		
		if (enemyArmy != null) {
		for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
			if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Sparta")) {
				playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
				break;
			}
		}
		
		if (playerArmy != null) {
		try {
			NewGame.autoResolve(playerArmy, enemyArmy);
			if (playerArmy.getUnits().size() == 0) {
				JOptionPane.showMessageDialog(null, "YOU LOST.", "oopsie", JOptionPane.ERROR_MESSAGE);
			}
			else if (enemyArmy.getUnits().size() == 0) {
				JOptionPane.showMessageDialog(null, "YOU WON!! :D", "DING DING DING", JOptionPane.ERROR_MESSAGE);
			}
		} catch (FriendlyFireException e1) {
			JOptionPane.showMessageDialog(null, "You're attacking a friendly army!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
		}
	}
	}

		
		

		
		if (e.getSource() == attackCairo) {
			Army playerArmy = null;
			
			if (NewGame.getPlayer().getControlledArmies().size() == 0) {
				JOptionPane.showMessageDialog(null, "You don't have any armies yet!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			}
			
			
			else {
				
				for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
				        if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Cairo")) {
				        	
				                 c = NewGame.getAvailableCities().get(i);
				                 break;
				     }
				}
				
				
				for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
					if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Cairo")) {
						playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
						break;
					}
				}
			
				//Army cc = c.getDefendingArmy();
//				System.out.println(playerArmy);
//				System.out.println(c);
				if (c != null && playerArmy != null) 
			
				 {
					
				BattleView1 BV = new BattleView1(this, NewGame, playerArmy, c);
				}
				
			
				 
				 
				 else {
					JOptionPane.showMessageDialog(null, "TARGET NOT REACHED.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
			}
		
		
		
		
		if (e.getSource() == attackRome) {
			
			Army playerArmy = null;
			
		
			if (NewGame.getPlayer().getControlledArmies().size() == 0) {
				JOptionPane.showMessageDialog(null, "You don't have any armies yet!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			}
			
			
			else {
				
				for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
				        if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Rome")) {
				        	
				                 c = NewGame.getAvailableCities().get(i);
				                 break;
				     }
				}
			
				
				
				if (c != null) {
			
					for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
						if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Rome")) {
							playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
							break;
						} }
						
						if (playerArmy != null)
						{
				
				BattleView1 BV = new BattleView1(this, NewGame, a, c);
				
			}
				
			}
				else {
					JOptionPane.showMessageDialog(null, "TARGET NOT REACHED.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				}
		} }
		
		
		
		
		
		
		
		
		if (e.getSource() == attackSparta) {
	
			Army playerArmy = null;
			if (NewGame.getPlayer().getControlledArmies().size() == 0) {
				JOptionPane.showMessageDialog(null, "You don't have any armies yet!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			}
			
			
			else {
				
				for (int i = 0; i < NewGame.getAvailableCities().size(); i++) {
				        if (NewGame.getAvailableCities().get(i).getName().equalsIgnoreCase("Sparta")) {
				        	
				                 c = NewGame.getAvailableCities().get(i);
				                 break;
				     }
				}
				
				
				
				for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
					if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Sparta")) {
						playerArmy = NewGame.getPlayer().getControlledArmies().get(i);
						break;
					}}
				
			//	Army cc = c.getDefendingArmy();
					
				
			
			if (playerArmy != null ) {
			
				
				BattleView1 BV = new BattleView1(this, NewGame, a, cc);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "TARGET NOT REACHED.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
			}
			}
		}
		}
		
			
			
				
				
				
						
		
		
		
	
			
		
		
		
		
		
		
		
	
		
		
		
		
	



	
	

	public String getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(String displayInfo) {
		this.displayInfo = displayInfo;
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
		
		if (e.getSource() == besieging) {
			//System.out.println("besiege");

			for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {

				currArmy = NewGame.getPlayer().getControlledArmies().get(i);
				currCity = NewGame.getPlayer().getControlledCities().get(i);

				if (currArmy.getCurrentStatus().equals(Status.BESIEGING)) {


					for (int j = 0; j < currArmy.getUnits().size(); j++) {
						currUnit = NewGame.getPlayer().getControlledArmies().get(i).getUnits().get(i);

						if (currUnit instanceof Cavalry) {
							unitInfoBesiege = unitInfoBesiege + "\n" + "Unit type: " + "Cavalry " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
						} else if (currUnit instanceof Infantry) {
							unitInfoBesiege = unitInfoBesiege + "\n" + "Unit type: " + "Infantry " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
						} else {
							unitInfoBesiege = unitInfoBesiege + "\n" + "Unit type: " + "Archer " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
						}

					}
				}

			}

			armyInfo.setText(unitInfoBesiege);
			armyInfo.setVisible(true);
			armyInfo.setEditable(false);
			
			
			
		}
			
			
			
			if (e.getSource() == marching) {
			//	System.out.println("marching");

				for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {

					currArmy = NewGame.getPlayer().getControlledArmies().get(i);
					currCity = NewGame.getPlayer().getControlledCities().get(i);

					if (currArmy.getCurrentStatus().equals(Status.MARCHING)) {


						for (int j = 0; j < currArmy.getUnits().size(); j++) {
							currUnit = NewGame.getPlayer().getControlledArmies().get(i).getUnits().get(i);

							if (currUnit instanceof Cavalry) {
								unitInfoMarch = unitInfoMarch + "\n" + "Unit type: " + "Cavalry " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
							} else if (currUnit instanceof Infantry) {
								unitInfoMarch = unitInfoMarch + "\n" + "Unit type: " + "Infantry " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
							} else {
								unitInfoMarch = unitInfoMarch + "\n" + "Unit type: " + "Archer " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
							}

						}
					}

				}

				armyInfo.setText(unitInfoMarch);
				armyInfo.setVisible(true);
				armyInfo.setEditable(false);
			
		}
			
			
			
			if (e.getSource() == idle) {
				//System.out.println("idle");

				for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {

					currArmy = NewGame.getPlayer().getControlledArmies().get(i);
					currCity = NewGame.getPlayer().getControlledCities().get(i);

					if (currArmy.getCurrentStatus().equals(Status.IDLE)) {


						for (int j = 0; j < currArmy.getUnits().size(); j++) {
							currUnit = NewGame.getPlayer().getControlledArmies().get(i).getUnits().get(i);

							if (currUnit instanceof Cavalry) {
								unitInfoIdle = unitInfoIdle + "\n" + "Unit type: " + "Cavalry " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
							} else if (currUnit instanceof Infantry) {
								unitInfoIdle = unitInfoIdle + "\n" + "Unit type: " + "Infantry " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
							} else {
								unitInfoIdle = unitInfoIdle + "\n" + "Unit type: " + "Archer " + " level: " + currUnit.getLevel() + " Current soldier count: " + currUnit.getCurrentSoldierCount() + " Max soldier count: " + currUnit.getMaxSoldierCount() + " Turns undersiege: " + currCity.getTurnsUnderSiege() + " City under siege: " + currArmy.getCurrentLocation() + "\n";
							}

						}
					}
					

				}

				armyInfo.setText(unitInfoIdle);
				armyInfo.setVisible(true);
				armyInfo.setEditable(false); 

				
			}
				
//			}		
	}

	
	


	@Override
	public void mouseReleased(MouseEvent e) {
		unitInfoIdle = "";
		unitInfoMarch = "";
		unitInfoBesiege = "";
		armyInfo.setText("");
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == rome) {
			rome.setIcon(new ImageIcon("images/huhu.gif"));
		}
		
		
		if (e.getSource() == sparta) {
			sparta.setIcon(new ImageIcon("images/spartaa.gif"));
		}
		
		if (e.getSource() == cairo) {
			cairo.setIcon(new ImageIcon("images/RomeIcon.gif"));
		}
		
		if (e.getSource() == marching) {
			marching.setForeground(Color.black);
		}
		
		if (e.getSource() == besieging) {
			besieging.setForeground(Color.black);
		}
		
		if (e.getSource() == idle) {
			idle.setForeground(Color.black);
		}
		
		
		if (e.getSource() == laySiegeCairo) {
			laySiegeCairo.setForeground(Color.black);
		}
		
		if (e.getSource() == laySiegeRome) {
			laySiegeRome.setForeground(Color.black);
		}
		
		
		if (e.getSource() == laySiegeSparta) {
			laySiegeSparta.setForeground(Color.black);
		}
		
		
		
		if (e.getSource() == attackCairo) {
			attackCairo.setForeground(Color.black);
		}
		
		
		if (e.getSource() == attackSparta) {
			attackSparta.setForeground(Color.black);
		}
		
		
		if (e.getSource() == attackRome) {
			attackRome.setForeground(Color.black);
		}
		
		
		if (e.getSource() == autoResolveCairo) {
			autoResolveCairo.setForeground(Color.black);
		}
		
		if (e.getSource() == autoResolveRome) {
			autoResolveRome.setForeground(Color.black);
		}
		
		if (e.getSource() == autoResolveSparta) {
			autoResolveSparta.setForeground(Color.black);
		}
		
		if (e.getSource() == targetCairo) {
			targetCairo.setForeground(Color.black);
		}
		
		if (e.getSource() == targetRome) {
			targetRome.setForeground(Color.black);
		}
		
		if (e.getSource() == targetSparta) {
			targetSparta.setForeground(Color.black);
		}
		
		if (e.getSource() == yourArmies) {
			yourArmies.setForeground(Color.black);
		}

	}



	@Override
	public void mouseExited(MouseEvent e) {
		rome.setIcon(null);
		sparta.setIcon(null);
		cairo.setIcon(null);
		marching.setForeground(Color.white);
		besieging.setForeground(Color.white);
		idle.setForeground(Color.white);
		laySiegeCairo.setForeground(Color.white);
		laySiegeRome.setForeground(Color.white);
		laySiegeSparta.setForeground(Color.white);
		targetCairo.setForeground(Color.white);
		targetRome.setForeground(Color.white);
		targetSparta.setForeground(Color.white);
		attackCairo.setForeground(Color.white);
		attackRome.setForeground(Color.white);
		attackSparta.setForeground(Color.white);
		autoResolveCairo.setForeground(Color.white);
		autoResolveRome.setForeground(Color.white);
		autoResolveSparta.setForeground(Color.white);
		yourArmies.setForeground(Color.white);
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
	
		if (b.getActionCommand().equals("Sparta")) {
		
	
	        spartaView.setVisible(true);
	        this.setVisible(false);
	       
	        
	       
	        	        
	        
			
			
		} else if (b.getActionCommand().equals("Cairo")) {
			cairoView.setVisible(true);
			this.setVisible(false);
			
			
			
			
			
			
		} else if (b.getActionCommand().equals("Rome")) {
			romeView.setVisible(true);
			this.setVisible(false);

		}
		
		
	}

	public JComboBox<Army> getPlayerArmies() {
		return playerArmies;
	}

	public void setPlayerArmies(Army a) {
		
		this.playerArmies.addItem(a);
	}

	public Game getNewGame() {
		return NewGame;
	}

	public void setNewGame(Game newGame) {
		NewGame = newGame;
	}
	
	public void update() {
		   displayInfo =  NewGame.getPlayer().getName() + "   " + "TURN  " + NewGame.getCurrentTurnCount() +  "   " +  NewGame.getPlayer().getFood() + "  FOOD" + "   " + NewGame.getPlayer().getTreasury() + "  GOLD";
			display.setText(displayInfo);
			
			
	   }
	
	
	

	

}
