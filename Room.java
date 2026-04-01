import java.util.ArrayList;

public class Room {
   private ArrayList<Item> itemsInRoom;
   private ArrayList<Monster> monstersInRoom;
   private final int maxNumberOfMonsters = 2;
   private final int maxNumberOfItems = 2;
   
   public Room() {
      // TODO: Generate a random number of monsters and items between 1 and max (inclusive)
      
      int numItems = (int)(Math.random()*maxNumberOfItems+1);
      int numMonsters =(int)(Math.random()*maxNumberOfMonsters+1);

      // TODO: Create the ArrayLists to hold the Items and Monsters in the Room and populate them with Monsters and Items
      itemsInRoom = new ArrayList<Item>();
      monstersInRoom = new ArrayList<Monster>();
      for (int i = 0; i<numItems; i++) {
         itemsInRoom.add(new Item());
      }
      for (int i = 0; i<numMonsters; i++) {
         monstersInRoom.add(new Monster());
      }

   }
   public boolean containsItem(String str) {
      for(Item item: itemsInRoom) {
            if (item.getItemType().toUpperCase().equals(str)) return true;
      
      }
      
      return false;
   }
   public boolean containsMonster(String str) {
      for(Monster monster: monstersInRoom) {
            if (monster.getMonsterType().toUpperCase().equals(str)) return true;
      
      }
      
      return false;
   }
   public Item itemNameToObj(String str) {
      for(Item item: itemsInRoom) {
            if (item.getItemType().toUpperCase().equals(str)) return item;
            
      }
      
      return null;
   }
   public Monster monsterNameToObj(String str) {
      for(Monster monster: monstersInRoom) {
            if (monster.getMonsterType().toUpperCase().equals(str)) return monster;
            
      }
      
      return null;
   }
   public void removeItem(Item item) {
      itemsInRoom.remove(item);
   
   
   }
   public void removeMonster(Monster m) {
      monstersInRoom.remove(m);
   
   
   }
   public void addItem(Item item) {
      itemsInRoom.add(item);
   
   
   }
   public String toString() {
      String returnString = "There are " + itemsInRoom.size() + " items in the room: ";
      for (int i = 0; i<itemsInRoom.size()-1; i++) {
         returnString+= itemsInRoom.get(i).getItemType()+", ";
      
      
      }
       if (itemsInRoom.size()>0) returnString+= itemsInRoom.get(itemsInRoom.size()-1).getItemType();
       returnString += "\nThere are " + monstersInRoom.size() + " monsters in the room: ";
       for (int i = 0; i<monstersInRoom.size()-1; i++) {
         returnString+= monstersInRoom.get(i).getMonsterType()+", ";
      
      
      }
        returnString+= monstersInRoom.get(monstersInRoom.size()-1).getMonsterType()+"\n";
      // TODO: build a returnString that nicely formats the Monsters and Items in the Room
      // HINT: Use a for-each loop to go through the ArrayLists
      return returnString;
   }

   }