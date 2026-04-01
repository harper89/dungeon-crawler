import java.util.*;

public class Game {   
   public static void main(String[] args) {
     
      boolean exitGame = false;
      boolean isFighting = false;
      int numFights = 0;
      
      Game game = new Game();
      
         
      Scanner scanner = new Scanner(System.in);
     

      // TODO: Create a Map which populates each element of the grid with a Room
      // that contains a random assortment of Monsters and Items
      Map map = new Map(10, 10);
      // TODO: Create a Player at a random location in the Map
      // HINT: Generate random row/column values using Math.random()
      // and pass them into the Player constructor
           
            System.out.println("Hello! What is your name?");
             String name = scanner.nextLine().toUpperCase();

            Player p = new Player(100, name, (int)(Math.random()*map.getNumRows()), (int)(Math.random()*map.getNumCols()));
            if (name.equals("")) {
               name = "Player";
               enterText("Anonymous, I see.");
            } else {
               enterText(name+"... I like your name!");
               
            }  
            enterText("Anyway, welcome to the Dungeon Discovery Tour, where you will get to see all kinds of monsters from the safety of this minecart!");
            enterText(name+", Just make sure you stay inside the vehicle at all times.");
            enterText(name+"! Don't fall!");
            enterText(name+"!!!!!!");
            newLine(30);
            System.out.println("Type \"help\" for a list of commands.\n");
      while (exitGame != true) {
            
            
            
            System.out.println("\nYour position is (" + p.getX()+ "," + p.getY()+")\n");
            printHealthBar(p);
   
            System.out.print("Enter command: ");
            String input = scanner.nextLine().toUpperCase();
            newLine(30);
            Room currRoom = map.getRoom(p.getX(),p.getY());            
            if (input.equals("EXIT")) {
                System.out.println("Exiting game. Goodbye!\n");
                exitGame = true;
            }
            else if (input.equals("LOOK") || input.equals("SEARCH")) {
                  System.out.print("\n"+p.look(map));
            }
            // TODO: get the player movement working correctly. It should NOT let the player
            // move beyond the boundaries of the map, and provide the user with descriptions
            // of what is happening. You'll have to change the if/else/if statements here to 
            // do this.
            else if (input.startsWith("MOVE ")||input.startsWith("GO ")||input.startsWith("UP")||input.startsWith("DOWN")||input.startsWith("LEFT")||input.startsWith("RIGHT")) {
               String direction = "";
               if (input.startsWith("MOVE ")) {
                   direction = input.substring(5);
                } else if (input.startsWith("GO ")) {
                  direction = input.substring(3);
                } else {
                  direction = input.substring(0);
                }
                
               if (direction.equals("UP")) {
                  if(p.getY()<map.getNumCols()-1) p.moveTo(p.getX(),p.getY()+1);
               } else if (direction.equals("DOWN")) {
                  if(p.getY()>0) p.moveTo(p.getX(),p.getY()-1);
               } else if (direction.equals("LEFT")) { 
                  if(p.getX()>0) p.moveTo(p.getX()-1,p.getY());
               } else if (direction.equals("RIGHT")) {
                  if(p.getX()<map.getNumRows()-1) p.moveTo(p.getX()+1,p.getY());
               } else {
                  System.out.println("\nYou're trying to move in an invalid direction. Please choose UP, DOWN, LEFT or RIGHT");
               }
            } else if (input.startsWith("TAKE ")) {
            
               String item = input.substring(5);
               if (map.getRoom(p.getX(),p.getY()).containsItem(item)) {
                  System.out.println("You took the " + item +".");
                  p.addItemToInventory(map.getRoom(p.getX(),p.getY()).itemNameToObj(item));
                  map.getRoom(p.getX(),p.getY()).removeItem(map.getRoom(p.getX(),p.getY()).itemNameToObj(item));
               } else {
                  System.out.println("There is no " + item + " in the room.");

               }
            
            } else if (input.startsWith("DROP ")) {
            
               String drop = input.substring(5);
               Item inventoryItem = p.itemNameToObj(drop);
               if (p.hasItem(drop)) {
                  System.out.println("You dropped the " + inventoryItem.getItemType() +".");
                  currRoom.addItem(p.removeItemFromInventory(inventoryItem));
               } else {
                  System.out.println("You do not have a " +drop+ " to drop.");

               }   
               if ( inventoryItem.equals(p.getEquippedWeapon()))  p.setEquippedWeapon(null);
                        
            } else if (input.startsWith("FIGHT ")||input.startsWith("ATTACK ")) {
               String monster = "";
               if (input.startsWith("FIGHT ") ) monster = input.substring(6).toUpperCase();
               if (input.startsWith("ATTACK ") ) monster = input.substring(7).toUpperCase();

               Monster monsterObj = currRoom.monsterNameToObj(monster);
              
                              if (map.getRoom(p.getX(),p.getY()).containsMonster(monster)) {
                 monsterObj.setHealth(monsterObj.getMaxHealth(monsterObj.getID(monster)));

                  System.out.println("You attacked the " + monster +".");
                  isFighting = true;
                  while (isFighting) {
                  
                   
                     boolean didAct = false;
                     while (!didAct) {
                        printHealthBar(monsterObj);
                        printHealthBar(p);
                        System.out.print("Enter command: ");
                        String fightCommand = scanner.nextLine().toUpperCase();
                        newLine(30);
                        if (fightCommand.startsWith("ATTACK")||fightCommand.startsWith("FIGHT")) {
                           didAct = true;
                           newLine(30);
                           if (p.getEquippedWeapon() != null) {
                              Item weap = p.getEquippedWeapon();
                              int playerDMG = (int) (weap.getDamage(weap.getID(weap.getItemType())) + weap.getDamage(weap.getID(weap.getItemType()))*(Math.random()*2-1)/5);

                              System.out.println("You attacked the " + monster+ " for " + playerDMG + " DMG!");
                               monsterObj.changeHealth(-1*playerDMG);
                           } else {
                              System.out.println("You didn't have a weapon. You punched the " + monster + " for 1 DMG.");
                              monsterObj.changeHealth(-1);
                           }

                        } else if (fightCommand.startsWith("FLEE") || fightCommand.startsWith("RUN")|| fightCommand.startsWith("LEAVE") || fightCommand.startsWith("EXIT")) {
                           didAct = true;
                           isFighting = false;
                           System.out.println("You fled the scene.");
                        }

                     }
                      if (monsterObj.getHealth()<=0) {
                        isFighting = false;
                        newLine(30);  
                        System.out.println("Congratulations! You defeated the "+monsterObj.getMonsterType()+".");
                        currRoom.removeMonster(monsterObj);
                  }
                     
                     int monsterDMG = (int) (monsterObj.getDamage(monsterObj.getID(monster)) + monsterObj.getDamage(monsterObj.getID(monster))*(Math.random()*2-1)/5);
                     if (isFighting) {
                     
                        System.out.println("The " + monster+ " attacked you for " + monsterDMG+ " DMG!");
                        p.changeHealth(-1*monsterDMG);
                     }
                     if (p.getHealth()<=0) {
                        isFighting = false;
                        exitGame = true;
                        printHealthBar(p);
                        System.out.println("You died!");
                  }
                 
                  }
                  
               } else {
                  System.out.println("There is no " + monster + " here to fight.");

               }    
                        
            } else if (input.startsWith("CHECK ")) {
            
               String check = input.substring(6);
               Monster monsterObj = currRoom.monsterNameToObj(check);
               Item itemObj = p.itemNameToObj(check);
               if (map.getRoom(p.getX(),p.getY()).containsMonster(check)) {
               
                  System.out.print("This "+check+" has " + monsterObj.getMaxHealth(monsterObj.getID(check)));
                  System.out.println(" HP and " +  monsterObj.getDamage(monsterObj.getID(check))+ " DMG.");
                  
               } else if (p.hasItem(check)) {
                  if (itemObj.getDamage(itemObj.getID(check))>0) {
                    System.out.print("This "+check+" does " + itemObj.getDamage(itemObj.getID(check)) + " DMG.");
                  } else {
                     if (itemObj.getID(check) == 1) System.out.print("This healing potion will restore 50 HP when used.");
                     if (itemObj.getID(check) == 2) System.out.print("This torch helps you see your opponent's next attack.");
                     if (itemObj.getID(check) == 3) System.out.print("This ancient scroll seems important.");
                     if (itemObj.getID(check) == 4) System.out.print("This leather armor will increase your max HP by 75.");
                     if (itemObj.getID(check) == 5) System.out.print("This silver key seems important.");
                     if (itemObj.getID(check) == 6) System.out.print("This magic amulet will boost your DMG by 5.");
                     if (itemObj.getID(check) == 8) System.out.print("This spellbook of fire allows you to light a room on fire.");
                     if (itemObj.getID(check) == 9) System.out.print("A monster might want these coins...");
                     if (itemObj.getID(check) == 10) System.out.print("This mysterious rune stone might help you get out of this place.");
                     if (itemObj.getID(check) == 13) System.out.print("This potion of invisibility will make your opponent miss for 3 turns.");
                     if (itemObj.getID(check) == 14) System.out.print("This iron shield will reduce opponent DMG by 25%.");
                     if (itemObj.getID(check) == 15) System.out.print("This dragon egg is dangerous to carry around. Don't drop it!");
                     if (itemObj.getID(check) == 16) System.out.print("This trap disarming kit seems important.");
                     if (itemObj.getID(check) == 17) System.out.print("This crystal orb is useless.");
                     if (itemObj.getID(check) == 19) System.out.print("As you tried to read the map, it disintegrated into your hands.");



                  }
               
               } else {
                  System.out.println("There is no " + check + " here to check. If you are trying to check an item, pick it up first.");

               }    
                        
            } else if (input.startsWith("EQUIP ")) {
            
               String equip = input.substring(6);
               Item itemObj = p.itemNameToObj(equip);
               if (p.hasItem(equip)) {
                  if (itemObj.getWeapon(itemObj.getID(equip))) {
                  System.out.println("You equipped the " + equip  + " as your weapon.");
                  p.setEquippedWeapon(itemObj);
                  } else {
                  System.out.println("You equipped the " + equip +".");
                  
                  }
                  
               } else {
                  System.out.println("You do not have a " + equip + " to equip.");

               }    
                        
            } else if (input.startsWith("INVENTORY")) {
            
                  System.out.print("You have " + p.getInventorySize() + " items in your inventory: ");
                  for (int i = 0; i<p.getInventorySize() -1; i++) {
                     System.out.print(p.getItemStr(i)+", ");
                  
                  }
                  if (p.getInventorySize()>0) System.out.print(p.getItemStr(p.getInventorySize()-1)+".");
                  if (p.getEquippedWeapon() == null) {
                       System.out.println("\nYou do not have a weapon equipped.");

                  } else {
                        System.out.println("\nYou have a " + p.getEquippedWeapon().getItemType() + " equipped as your weapon.");
                  }
               } else if (input.startsWith("HELP")) {
                  System.out.println("\"HELP\": View a list of commands.");
                  System.out.println("\"LOOK\": View monsters and items in your current room.");
                  System.out.println("\"MOVE UP/DOWN/LEFT/RIGHT\": Move to another room.");
                  System.out.println("\"TAKE (ITEM)\": Take an item from the current room.");
                  System.out.println("\"DROP (ITEM)\": Drop an item from your inventory.");
                  System.out.println("\"EQUIP (ITEM)\": Equip an item from your inventory.");
                  System.out.println("\"INVENTORY\": View your inventory.");
                  System.out.println("\"FIGHT (MONSTER)\": Fight a monster in the current room.");
                  System.out.println("\"CHECK (MONSTER)\": View details on a monster in the current room.");
                  System.out.println("\"CHECK (ITEM)\": View details on an item in your inventory.");

                  } 
         // TODO: display a message to the user telling them their current coordinates every time they move
       }
   }
   public static void enterText(String str) {
      Scanner scanner = new Scanner(System.in);
      System.out.println(str);
      String name = scanner.nextLine();
   
   }
   public static void newLine(int n) {
      for (int i = 0; i<n; i++) System.out.println();
   
   }
   public static void spaces(int n) {
      for (int i = 0; i<n; i++) System.out.print(" ");
   
   }
   public static void printHealthBar(Player p) {
            
            System.out.print("Your current health is " + p.getHealth()+ "/"+p.getMaxHealth()+"\n");
            int bars = (int) Math.ceil(10.0*p.getHealth()/p.getMaxHealth());
            System.out.print ("[ ");
            for (int i = 0; i< bars; i++) System.out.print("/");
            for (int i = 0; i< 10-bars; i++) System.out.print("-");
            System.out.println(" ]\n");
   }
   public static void printHealthBar(Monster m) {
            System.out.println();
            spaces(30);
            System.out.print(m.getMonsterType().toUpperCase()+ " "+ m.getHealth()+ "/"+m.getMaxHealth(m.getID(m.getMonsterType()))+"\n");
            int bars = (int) Math.ceil(10.0*m.getHealth()/m.getMaxHealth(m.getID(m.getMonsterType())));
            spaces(30);
            System.out.print ("[ ");
            for (int i = 0; i< bars; i++) System.out.print("/");
            for (int i = 0; i< 10-bars; i++) System.out.print("-");
            System.out.println(" ]\n");
   }
}