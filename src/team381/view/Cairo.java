package team381.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import buildings.*;
import engine.City;
import engine.Game;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class Cairo extends JFrame implements ActionListener, MouseListener {

    private JLabel back;
    private WorldMap WMap;
    private Game NewGame;
    private City currCity;
    private JLabel build;
    private JComboBox buildings;
    private JLabel bg;
    private JTextArea buildingsInfo;
    private String s = "";
    private JLabel MB;
    private JLabel EB;
    private JLabel recruit;
    private JComboBox units;
    private JLabel upgrade;
    private JLabel upgradeCost;
    private JComboBox presentBuildings;
    private JTextArea armyInfo;
    private JLabel cityArmy;
    private String armyInfoText;
    private JLabel endTurn;
    private JLabel display;
    private String displayInfo;
    private JLabel playerArmy;
    private JTextArea playerArmyInfo;
    private String pArmy = "";
    private JLabel intiateArmy;
    
    Font pixelArt;



    public Cairo(WorldMap WMap, Game NewGame) {

        this.WMap = WMap;
        this.NewGame = NewGame;

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.addMouseListener(this);
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
        
        
        
        back = new JLabel();
        back.setText("Go back");
        back.setFont(pixelArt);
        back.setBounds(10, 0, 200, 50);
        back.setForeground(Color.white);
        back.addMouseListener(this);
        this.add(back);
        
        
        
        
        build = new JLabel();
        build.setText("BUILD");
        build.setPreferredSize(new Dimension(100,50));
        build.setFont(pixelArt);
	   // build.setOpaque(true);
	    build.setForeground(Color.white);
	    build.setBounds(800, 70, 200, 50);
        this.add(build);
        build.addMouseListener(this);
        
        
        String[] buildingz = {"", "Archery Range", "Barracks", "Stable", "Farm", "Market"};
        buildings = new JComboBox(buildingz);
        buildings.setBounds(450, 80, 300, 30);
        buildings.setBackground(Color.white);
        buildings.setForeground(Color.black);
        buildings.setFont(pixelArt);
        this.add(buildings);
        
        
        
        MB = new JLabel();
        MB.setText("MILITARY BUILDINGS");
        MB.setPreferredSize(new Dimension(100, 50));
        MB.setFont(pixelArt);
        MB.setForeground(Color.white);
        MB.setBounds(800, 120, 500, 50);
        this.add(MB);
        MB.addMouseListener(this);
        
        
        EB = new JLabel();
        EB.setText("ECONOMIC BUILDINGS");
        EB.setPreferredSize(new Dimension(100, 50));
        EB.setFont(pixelArt);
        EB.setForeground(Color.white);
        EB.setBounds(800, 170, 500, 50);
        this.add(EB);
        EB.addMouseListener(this);
        
        buildingsInfo = new JTextArea();
        buildingsInfo.setBackground(Color.white);
        buildingsInfo.setEditable(false);
        buildingsInfo.setBounds(450, 130, 300, 90);
        buildingsInfo.setBorder(BorderFactory.createBevelBorder(1));
        buildingsInfo.setForeground(Color.black);
        buildingsInfo.setFont(new Font("Arial", Font.BOLD, 10));
        //buildingsInfo.setText("Level: 1 Building Type: Archery Range");
        buildingsInfo.addMouseListener(this);
        this.add(buildingsInfo);
        
        
        
        recruit = new JLabel();
        recruit.setText("RECRUIT");
        recruit.setPreferredSize(new Dimension(100, 50));
        recruit.setFont(pixelArt);
        recruit.setForeground(Color.white);
        recruit.setBounds(800, 220, 500, 50);
        this.add(recruit);
        recruit.addMouseListener(this);
        
        
        String[] unitz = {"", "Archer", "Cavalry", "Infantry"};
        units = new JComboBox(unitz);
        units.setBounds(450, 230, 300, 30);
        this.add(units);
        units.addMouseListener(this);
        units.setBackground(Color.white);
        units.setForeground(Color.black);
        units.setFont(pixelArt);
        
        
        upgrade = new JLabel();
        upgrade.setText("UPGRADE");
        upgrade.setPreferredSize(new Dimension(100, 50));
        upgrade.setFont(pixelArt);
        upgrade.setForeground(Color.white);
        upgrade.setBounds(800, 270, 500, 50);
        this.add(upgrade);
        upgrade.addMouseListener(this);
        
        
        upgradeCost = new JLabel();
        upgradeCost.setBounds(100, 270, 500, 40);
        upgradeCost.setFont(pixelArt);
        upgradeCost.setForeground(Color.white);
        this.add(upgradeCost);
        upgradeCost.addMouseListener(this);
        
       
        
        String[] prezentB = {"", "Archery Range", "Barracks", "Stable", "Farm", "Market"};
        presentBuildings = new JComboBox(prezentB);
        presentBuildings.setBounds(450, 280, 300, 30);
        this.add(presentBuildings);
        presentBuildings.addMouseListener(this);
        presentBuildings.setBackground(Color.white);
        presentBuildings.setForeground(Color.black);
        presentBuildings.setFont(pixelArt);
        
        
        cityArmy = new JLabel();
        cityArmy.setText("CITY ARMY");
        cityArmy.setPreferredSize(new Dimension(100, 50));
        cityArmy.setFont(pixelArt);
        cityArmy.setForeground(Color.white);
        cityArmy.setBounds(290, 420, 500, 50);
        this.add(cityArmy);
        cityArmy.addMouseListener(this);
        
        armyInfo = new JTextArea();
        armyInfo.setBackground(Color.white);
        armyInfo.setEditable(false);
        armyInfo.setBounds(15, 470, 700, 250);
        armyInfo.setForeground(Color.black);
        armyInfo.setFont(new Font("Arial", Font.BOLD, 10));
        //buildingsInfo.setText("Level: 1 Building Type: Archery Range");
        armyInfo.addMouseListener(this);
//       JScrollPane sp = new JScrollPane(armyInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //sp.setBounds(450, 260, 300, 90);
        //this.add(sp);
        this.add(armyInfo);
        
        playerArmy = new JLabel();
        playerArmy.setText("YOUR ARMY");
        playerArmy.setPreferredSize(new Dimension(100, 50));
        playerArmy.setFont(pixelArt);
        playerArmy.setForeground(Color.white);
        playerArmy.setBounds(1100, 420, 500, 50);
        this.add(playerArmy);
        playerArmy.addMouseListener(this);
        
        
        playerArmyInfo = new JTextArea();
        playerArmyInfo.setBackground(Color.white);
        playerArmyInfo.setEditable(false);
        playerArmyInfo.setBounds(800, 470, 700, 250);
        playerArmyInfo.setForeground(Color.black);
        playerArmyInfo.setFont(new Font("Arial", Font.BOLD, 10));
        //buildingsInfo.setText("Level: 1 Building Type: Archery Range");
        playerArmyInfo.addMouseListener(this);
        this.add(playerArmyInfo);
   
        
        
        endTurn = new JLabel();
        endTurn.setText("END TURN");
        endTurn.setPreferredSize(new Dimension(100, 50));
        endTurn.setFont(pixelArt);
        endTurn.setForeground(Color.white);
        endTurn.setBounds(1390, 0, 200, 50);
        endTurn.addMouseListener(this);
        this.add(endTurn);
        
        
        
        display = new JLabel();
        display.setPreferredSize(new Dimension(100, 50));
        display.setFont(pixelArt);
        display.setForeground(Color.white);
        display.setBounds(450, 0, 900, 50);
        update();
        display.setText(displayInfo);
        this.add(display);
        display.addMouseListener(this);
        
        
        
        intiateArmy = new JLabel();
        intiateArmy.setText("Intiate Army");
        intiateArmy.setPreferredSize(new Dimension(100, 50));
        intiateArmy.setFont(pixelArt);
        intiateArmy.setForeground(Color.white);
        intiateArmy.setBounds(800, 320, 500, 50);
        this.add(intiateArmy);
        intiateArmy.addMouseListener(this);

        
        
        
        
        
        
        
       bg = new JLabel();
       bg.setIcon(new ImageIcon("images/cairo1.png"));
       bg.setBounds(0, 0, this.getWidth(), this.getHeight());
       this.add(bg);
       

        for (int i = 0; i < NewGame.getPlayer().getControlledCities().size(); i++) {
            if (NewGame.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase("Cairo")) {
            currCity = NewGame.getPlayer().getControlledCities().get(i);
            break;
        }
        }
                     
        
 
        
        
        this.validate();
		this.revalidate();
	   
   

    
    
    

    }
    
    
   public void update() {
	   displayInfo =  NewGame.getPlayer().getName() + "   " + "TURN  " + NewGame.getCurrentTurnCount() +  "   " +  NewGame.getPlayer().getFood() + "  FOOD" + "   " + NewGame.getPlayer().getTreasury() + "  GOLD";
		display.setText(displayInfo);
		WMap.getDisplay().setText(displayInfo);
		
   }


    @Override
    public void actionPerformed(ActionEvent e) {

 }





	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == back) {
		  back.setForeground(Color.black);
		  WMap.setNewGame(NewGame);
          WMap.setVisible(true);
          this.dispose();

      }
      
      if (buildings.getSelectedItem().equals("Archery Range") && (e.getSource() == build)) {
      	
    	  
    	  try {
      		
				NewGame.getPlayer().build("ArcheryRange", "Cairo");
				buildings.removeItem(buildings.getSelectedItem());
				update();
				
		      
				
				
    	  
				
				
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough gold!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
      	
      
      }
      
      if (buildings.getSelectedItem().equals("Barracks") && (e.getSource() == build)) {
      	try {
      		
				NewGame.getPlayer().build("Barracks", "Cairo");
				//addMBuilding();
				buildings.removeItem(buildings.getSelectedItem());
				update();
				
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough gold!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
      	
      
      }
      
      
      
      if (buildings.getSelectedItem().equals("Stable") && (e.getSource() == build)) {
      	try {
      		
      		
				NewGame.getPlayer().build("Stable", "Cairo");
				buildings.removeItem(buildings.getSelectedItem());
				update();
				
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough gold!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
      	
      
      }
      
      
      
      
      if (buildings.getSelectedItem().equals("Farm") && (e.getSource() == build)) {
      	try {
      		
      		    
				NewGame.getPlayer().build("Farm", "Cairo");
				buildings.removeItem(buildings.getSelectedItem());
				update();
				
				
				
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough gold!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
      	
      
      }
      
      
      if (buildings.getSelectedItem().equals("Market") && e.getSource() == build) {
      
      	
      	try {
      		
      		    
				NewGame.getPlayer().build("Market", "Cairo");
				buildings.removeItem(buildings.getSelectedItem());
				update();
				
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough gold!", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
      	
      
      }
      

      if (e.getSource() == recruit) {
    	 if (units.getSelectedItem().equals("Archer")) {
    		 try {
				NewGame.getPlayer().recruitUnit("Archer", "Cairo");
				update();
			} catch (BuildingInCoolDownException e1) {
				JOptionPane.showMessageDialog(null, "Building is cooling down! Please end turn.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (MaxRecruitedException e1) {
				JOptionPane.showMessageDialog(null, "Maximum number of units recruited", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (NotEnoughGoldException e1) {
				JOptionPane.showMessageDialog(null, "You do not have enough gold", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
    	 }
      }
      
      if (e.getSource() == recruit) {
     	 if (units.getSelectedItem().equals("Cavalry")) {
     		 try {
 				NewGame.getPlayer().recruitUnit("Cavalry", "Cairo");
 				update();
 			} catch (BuildingInCoolDownException e1) {
 				JOptionPane.showMessageDialog(null, "Building is cooling down! Please end Turn.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
 				e1.printStackTrace();
 			} catch (MaxRecruitedException e1) {
 				JOptionPane.showMessageDialog(null, "Maximum number of units recruited", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
 				e1.printStackTrace();
 			} catch (NotEnoughGoldException e1) {
 				JOptionPane.showMessageDialog(null, "You do not have enough gold", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
 				e1.printStackTrace();
 			}
     	 }
       }
      
      
      if (e.getSource() == recruit) {
     	 if (units.getSelectedItem().equals("Infantry")) {
     		 try {
 				NewGame.getPlayer().recruitUnit("Infantry", "Cairo");
 				update();
 				
 			} catch (BuildingInCoolDownException e1) {
 				JOptionPane.showMessageDialog(null, "Building is cooling down! Please end turn.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
 				e1.printStackTrace();
 			} catch (MaxRecruitedException e1) {
 				JOptionPane.showMessageDialog(null, "Maximum number of units recruited", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
 				e1.printStackTrace();
 			} catch (NotEnoughGoldException e1) {
 				JOptionPane.showMessageDialog(null, "You do not have enough gold", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
 				e1.printStackTrace();
 			}
     	 }
       }
      
      
      
      if (presentBuildings.getSelectedItem().equals("Archery Range") && (e.getSource() == upgrade)) {
    	  
    	  
    	  
    	  for (int i = 0; i < currCity.getMilitaryBuildings().size(); i++) {
    		MilitaryBuilding  mb  = currCity.getMilitaryBuildings().get(i); 
    		  if (mb instanceof ArcheryRange) {
    			  try {
					mb.upgrade();
					update();
					upgradeCost.setText("cost is " + mb.getUpgradeCost());
				} catch (BuildingInCoolDownException e1) {
					JOptionPane.showMessageDialog(null, "Building is cooling down! Please end turn.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (MaxLevelException e1) {
					JOptionPane.showMessageDialog(null, "Building has reached maximum level of upgrade.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
    		  }
    		  
    		  break;
    	  }
      }
      
      
      if (presentBuildings.getSelectedItem().equals("Barracks") && (e.getSource() == upgrade)) {
    	  for (int i = 0; i < currCity.getMilitaryBuildings().size(); i++) {
    		  MilitaryBuilding mb = currCity.getMilitaryBuildings().get(i);
    		  if (mb instanceof Barracks) {
    			  try {
					mb.upgrade();
					update();
					upgradeCost.setText("cost is " + mb.getUpgradeCost());
				} catch (BuildingInCoolDownException e1) {
					JOptionPane.showMessageDialog(null, "Building is cooling down! Please end turn.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (MaxLevelException e1) {
					JOptionPane.showMessageDialog(null, "Building has reached maximum level of upgrade.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
    		  }
    		  break;
    	  }
      }
      
      
      if (presentBuildings.getSelectedItem().equals("Stable") && (e.getSource() == upgrade)) {
    	  for (int i = 0; i < currCity.getMilitaryBuildings().size(); i++) {
    		  MilitaryBuilding mb = currCity.getMilitaryBuildings().get(i);
    		  if (mb instanceof Stable) {
    			  try {
					mb.upgrade();
					update();
					upgradeCost.setText("cost is " + mb.getUpgradeCost());
				} catch (BuildingInCoolDownException e1) {
					JOptionPane.showMessageDialog(null, "Building is cooling down! Please end turn.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (MaxLevelException e1) {
					JOptionPane.showMessageDialog(null, "Building has reached maximum level of upgrade.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
    		  }
    		  break;
    	  }
      }
      
      
      if (presentBuildings.getSelectedItem().equals("Farm") && (e.getSource() == upgrade)) {
    	  for (int i = 0; i < currCity.getEconomicalBuildings().size(); i++) {
    		  EconomicBuilding eb = currCity.getEconomicalBuildings().get(i);
    		  if (eb instanceof Farm) {
    			  try {
					eb.upgrade();
					update();
					upgradeCost.setText("cost is " + eb.getUpgradeCost());
				} catch (BuildingInCoolDownException e1) {
					JOptionPane.showMessageDialog(null, "Building is cooling down! Please end turn.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (MaxLevelException e1) {
					JOptionPane.showMessageDialog(null, "Building has reached maximum level of upgrade.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
    		  }
    		  break;
    	  }
      }
      
      
      
      
      if (presentBuildings.getSelectedItem().equals("Market") && (e.getSource() == upgrade)) {
    	  for (int i = 0; i < currCity.getEconomicalBuildings().size(); i++) {
    		  EconomicBuilding eb = currCity.getEconomicalBuildings().get(i);
    		  if (eb instanceof Market) {
    			  try {
					eb.upgrade();
					update();
				} catch (BuildingInCoolDownException e1) {
					JOptionPane.showMessageDialog(null, "Building is cooling down! Please end turn.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (MaxLevelException e1) {
					JOptionPane.showMessageDialog(null, "Building has reached maximum level of upgrade.", "Cannot perform action", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
    		  }
    		  
    		  break;
    	  }
      }
      
      
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
   	 }
  	}
      
      if (e.getSource() == intiateArmy) {
    	  
    	  for (int i = 0; i < currCity.getDefendingArmy().getUnits().size(); i++) {
    		  NewGame.getPlayer().initiateArmy(currCity, currCity.getDefendingArmy().getUnits().get(i));
    		  WMap.setPlayerArmies(currCity.getDefendingArmy());
    		 break;
    	  }
    	  
    	 
    	  
    	  
//    	  WMap.setNewGame(NewGame);
    	  
      }
  	}
      
      
      
    
      
    

		
	





	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == cityArmy) {
	    	
	    	for (int i = 0; i < currCity.getDefendingArmy().getUnits().size(); i++) {
	    		Unit u = currCity.getDefendingArmy().getUnits().get(i);
	    		if (u instanceof Archer) {
	    			 s = s + "  Unit type: " + "Archer"  + "  Unit level: " + u.getLevel()  + "  Unit soldier count: " + u.getCurrentSoldierCount()  + "  Idle upkeep: " +  u.getIdleUpkeep()  + "  Siege upkeep:  " + u.getSiegeUpkeep()  + "  March upkeep:  " + u.getMarchingUpkeep() + "  Max soldier count:  " + u.getMaxSoldierCount() + "\n";
	    		}
	    		else if (u instanceof Cavalry) {
	    			s = s + "  Unit type: " + "Cavalry"  + "  Unit level: " + u.getLevel()  + "  Unit soldier count: " + u.getCurrentSoldierCount()  + "  Idle upkeep: " +  u.getIdleUpkeep()  + "  Siege upkeep:  " + u.getSiegeUpkeep()  + "  March upkeep:  " + u.getMarchingUpkeep()  + "  Max soldier count:  " + u.getMaxSoldierCount() + "\n";
	    		}
	    	
	    		else if (u instanceof Infantry) {
	    			s = s + "  Unit type: " + "Infantry"  + "  Unit level: " + u.getLevel()  + "  Unit soldier count: " + u.getCurrentSoldierCount()  + "  Idle upkeep: " +  u.getIdleUpkeep()  + "  Siege upkeep:  " + u.getSiegeUpkeep()  + "  March upkeep:  " + u.getMarchingUpkeep() + "  Max soldier count:  " + u.getMaxSoldierCount() + "\n";
	    		}
	    		
		    	
	    }
	    	armyInfo.setText(s);
	    	//System.out.println(currCity.getDefendingArmy().getUnits().size());
	    	
		}
		
if (e.getSource() == playerArmy) {
	    	
	         Army a = null;
              Unit u;
	
	    	for (int i = 0; i < NewGame.getPlayer().getControlledArmies().size(); i++) {
	    	     
	    	     if (NewGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Cairo")) {
	    	    	  a = NewGame.getPlayer().getControlledArmies().get(i); 
	    	    	  }
	    	}
	    	    	 
	    	    	 if (a != null)
	    	    	 for (int j = 0; j < a.getUnits().size(); j++) {
	    	    		u = a.getUnits().get(j);
	    	    		//System.out.println(a.getUnits().size());
	    		if (u instanceof Archer) {
	    			 pArmy = pArmy + "  Unit type: " + "Archer"  + "  Unit level: " + u.getLevel()  + "  Unit soldier count: " + u.getCurrentSoldierCount()  + "  Idle upkeep: " +  u.getIdleUpkeep()  + "  Siege upkeep:  " + u.getSiegeUpkeep()  + "  March upkeep:  " + u.getMarchingUpkeep() + "  Max soldier count:  " + u.getMaxSoldierCount() + "\n";
	    		}
	    		else if (u instanceof Cavalry) {
	    			pArmy = pArmy + "  Unit type: " + "Cavalry"  + "  Unit level: " + u.getLevel()  + "  Unit soldier count: " + u.getCurrentSoldierCount()  + "  Idle upkeep: " +  u.getIdleUpkeep()  + "  Siege upkeep:  " + u.getSiegeUpkeep()  + "  March upkeep:  " + u.getMarchingUpkeep()  + "  Max soldier count:  " + u.getMaxSoldierCount() + "\n";
	    		}
	    	
	    		else if (u instanceof Infantry) {
	    			pArmy = pArmy + "  Unit type: " + "Infantry"  + "  Unit level: " + u.getLevel()  + "  Unit soldier count: " + u.getCurrentSoldierCount()  + "  Idle upkeep: " +  u.getIdleUpkeep()  + "  Siege upkeep:  " + u.getSiegeUpkeep()  + "  March upkeep:  " + u.getMarchingUpkeep() + "  Max soldier count:  " + u.getMaxSoldierCount() + "\n";
	    		}
	    		
	    }
	    	    	 playerArmyInfo.setText(pArmy);
	    	    	 
}

	    	  
	 	    	
		


	    	   	
	    	




if (e.getSource() == MB) {
	  
	 // ArcheryRange r = new ArcheryRange();
    // currCity.getMilitaryBuildings().add(r);
   
   for (int i = 0; i < currCity.getMilitaryBuildings().size(); i++) {
   	
   	MilitaryBuilding mb = currCity.getMilitaryBuildings().get(i);
   	
       if (mb instanceof ArcheryRange) {
            s = s + "Level: " + mb.getLevel() + ", " + "Building type: " + "Archery Range" + "\n";
           
           
       }
       else if (mb instanceof Stable) {
       	 s = s + "Level: " + mb.getLevel() + ", " + "Building type: " + "Stable" + "\n" ;

       }
       else if (mb instanceof Barracks) {
       	 s = s + "Level: " + mb.getLevel() + ", " + "Building type: " + "Barracks" + "\n" ;
       }
     }
   buildingsInfo.setText(s);
 }
 
 
 
 if (e.getSource() == EB) {
     
     for (int i = 0; i < currCity.getEconomicalBuildings().size(); i++) {
     	
     	EconomicBuilding mb = currCity.getEconomicalBuildings().get(i);
     	
         if (mb instanceof Market) {
              s = s + "Level: " + mb.getLevel() + ", " + "Building type: " + "Market" + "\n";
             
             
         }
         else if (mb instanceof Farm) {
         	 s = s + "Level: " + mb.getLevel() + ", " + "Building type: " + "Farm" + "\n" ;

         } 
     }
         
     buildingsInfo.setText(s);
   }
}
	





	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == cityArmy) {
			s = "";
			armyInfo.setText("");
		}
		
		
		if (e.getSource() == playerArmy) {
			pArmy = "";
			playerArmyInfo.setText("");
		}
		
		if (e.getSource() == MB) {
			s = "";
		}
		
		if (e.getSource() == EB) {
			s = "";
		}
		
	}





	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == build) {
			build.setForeground(Color.black);
		}
		
		if (e.getSource() == MB) {
			MB.setForeground(Color.black);
		}
		
		if (e.getSource() == EB) {
			EB.setForeground(Color.black);
		}
		
		if (e.getSource() == back) {
			back.setForeground(Color.black);
		}
		
		if (e.getSource() == recruit) {
			recruit.setForeground(Color.black);
		}
		
		if (e.getSource() == upgrade) {
			upgrade.setForeground(Color.black);
		}
		
		if (e.getSource() == cityArmy) {
			cityArmy.setForeground(Color.black);
		}
		
		if (e.getSource() == endTurn) {
			endTurn.setForeground(Color.black);
		}
		
		if (e.getSource() == playerArmy) {
			playerArmy.setForeground(Color.black);
		}
		
		if (e.getSource() == intiateArmy) {
			intiateArmy.setForeground(Color.black);
		}
		
	}





	@Override
	public void mouseExited(MouseEvent e) {
		build.setForeground(Color.white);
			MB.setForeground(Color.white);
			EB.setForeground(Color.white);
			back.setForeground(Color.white);
			recruit.setForeground(Color.white);
			upgrade.setForeground(Color.white);
			cityArmy.setForeground(Color.white);
			endTurn.setForeground(Color.white);
			playerArmy.setForeground(Color.white);
			intiateArmy.setForeground(Color.white);
	}
	
	

}
