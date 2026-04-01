public class Monster {
   private String monsterType;
   private int health;
   private final String[] monsterTypes = {
            "Goblin",
            "Skeleton Warrior",
            "Zombie",
            "Orc",
            "Dark Sorcerer",
            "Giant Spider",
            "Vampire Bat",
            "Slime",
            "Mimic",
            "Lich",
            "Troll",
            "Werewolf",
            "Gargoyle",
            "Wraith",
            "Minotaur",
            "Basilisk",
            "Demon Hound",
            "Cave Serpent",
            "Ancient Dragon"
        };
        
   private final int[] maxHealth = {
            100,
            150,
            75,
            150,
            200,
            50,
            25,
            50,
            75,
            150,
            200,
            125,
            150,
            200,
            250,
            200,
            175,
            200,
            500
        };
        
   private final int[] damage = {
            10,
            20,
            10,
            15,
            20,
            15,
            5,
            5,
            0,
            5,
            15,
            20,
            10,
            15,
            20,
            15,
            25,
            20,
            40
        };

   public Monster() {
      // TODO: Pick a random monster type from monsterTypes
      monsterType = monsterTypes[(int) (monsterTypes.length * Math.random())];
   }
   
   
   public String getMonsterType() {
      return this.monsterType;
   }
   
   public String toString() {
      // TODO: Build a descriptive String for use by other methods
      return "This monster is a " +monsterType+".";
   }
   public  int getID(String str) {
      for(int i = 0; i<monsterTypes.length; i++) {
            if (monsterTypes[i].toUpperCase().equals(str.toUpperCase())) return i;
            
      }
      
      return -1;
   }
   public int getMaxHealth(int i) {
      return maxHealth[i];
   }
   public int getHealth() {
      return health;
   }
   public void setHealth(int i) {
      health = i;
   }
   public void changeHealth(int i) {
      health+=i;
      if (health<=0) health=0;
   }
   
   public int getDamage(int i) {
      return damage[i];
   }
}