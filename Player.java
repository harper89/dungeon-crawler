import java.util.ArrayList;

public class Player {
   private ArrayList<Item> inventory;
   private String playerName;
   private int health;
   private int maxHealth = 100;
   private int currentX;
   private int currentY;
   private Item equippedWeapon;

   public Player(int health, String playerName, int startingX, int startingY) {
      // set the health and playerName to the passed parameters
      this.health = health;
      this.playerName = playerName;      
      
      // TODO: create the empty Inventory
      inventory = new ArrayList<Item>();
      // TODO: set the startingX and Y of the player
      this.currentX = startingX;
      this.currentY = startingY;
   }

   public void addItemToInventory(Item item) {
      // TODO: add an item to inventory
      inventory.add(item);
   }
   
   public Item removeItemFromInventory(Item item) {
      // TODO: remove an item to inventory. Return the Item so it can returned to the Room that it is in
      inventory.remove(item);
      return item;
   }
   
   
   public boolean hasItem(String itemName) {
      for (Item item : inventory) {
         if (item.getItemType().toUpperCase().equals(itemName)) return true;
      }
      return false;
   }
   
   public Item itemNameToObj(String str) {
      for (Item item: inventory) {
            if (item.getItemType().toUpperCase().equals(str)) return item;
            
      }
      
      return null;
   }
   
   public String getItemStr(int x) {
      return inventory.get(x).getItemType(); 
      
   }
   
   public int getInventorySize() {
      return inventory.size();
      
   }
   public void setEquippedWeapon(Item item) {
      equippedWeapon = item;
      
   }
   public Item getEquippedWeapon() {
      return equippedWeapon;
      
   }
   
   
   public String look(Map map) {
      // TODO: This method will take the player's current X and Y, and return the contents of the room
      // they are in with a descriptive String
      // HINT: Use map.getRoom(currentX, currentY) and call toString()
      return ""+map.getRoom(currentX, currentY);
   }
   public void moveTo(int x, int y) {
      // TODO: Update the Player's currentX and currentY values.
      currentX = x;
      currentY = y;
   }
   
   public Room getRoom(Map map) {
      // TODO: return the room object that the player is currently in, given the Map that was passed
      return map.getRoom(currentX,currentY);
   }
    public int getX() {
      // TODO: return the room object that the player is currently in, given the Map that was passed
      return currentX;
   }
    public int getY() {
      // TODO: return the room object that the player is currently in, given the Map that was passed
      return currentY;
   }
   public int getHealth() {
      // TODO: return the room object that the player is currently in, given the Map that was passed
      return health;
   }
   public void changeHealth(int i) {
      // TODO: return the room object that the player is currently in, given the Map that was passed
      health+=i;
      if (health<=0) health=0;
   }
   public int getMaxHealth() {
      // TODO: return the room object that the player is currently in, given the Map that was passed
      return maxHealth;
   }
   public void setMaxHealth(int i) {
      // TODO: return the room object that the player is currently in, given the Map that was passed
      maxHealth += i;
   }
}