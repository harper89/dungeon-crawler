import java.util.*;
public class Item {
   private String itemType;
   private boolean isEquipped;
   private final String[] itemTypes = {
            "Rusty Sword",
            "Healing Potion",
            "Torch",
            "Ancient Scroll",
            "Leather Armor",
            "Silver Key",
            "Magic Amulet",
            "Goblin Dagger",
            "Spellbook of Fire",
            "Bag of Gold Coins",
            "Mysterious Rune Stone",
            "Skeleton Bone",
            "Cursed Ring",
            "Potion of Invisibility",
            "Iron Shield",
            "Dragon Egg",
            "Trap Disarming Kit",
            "Crystal Orb",
            "Venomous Dagger",
            "Tattered Map"
        };
        private final boolean[] isWeapon = {
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            true,
            false,
            false,
            false,
            true,
            true,
            false,
            false,
            false,
            false,
            false,
            true,
            false
        };
        private final int[] damage = {
            8,
            0,
            0,
            0,
            0,
            0,
            0,
            20,
            0,
            0,
            0,
            10,
            15,
            0,
            0,
            0,
            0,
            0,
            25,
            0
        };
   public Item() {
      // TODO: Pick a random item type from itemTypes
            itemType = itemTypes[(int) (itemTypes.length * Math.random())];

   }
   
   public String getItemType() {
      return this.itemType;
   }
   public int getID(String str) {
      for(int i = 0; i<itemTypes.length; i++) {
            if (itemTypes[i].toUpperCase().equals(str.toUpperCase())) return i;
            
      }
      
      return -1;
   }
   public boolean getWeapon(int index) {
      return isWeapon[index];
   }
   public int getDamage(int index) {
      return damage[index];
   }
  
   
   public String toString() {
      // TODO: Build a descriptive String for use by other methods
            return "This item is a " +itemType+".";

   }
}