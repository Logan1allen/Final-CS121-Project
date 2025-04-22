import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<String> inventory;
    private int health;
    private int maxHealth;
    private int attackPower;
    
    public Player() {
        this.inventory = new ArrayList<>();
        this.health = 50;
        this.maxHealth = 50;
        this.attackPower = 10;
        
        // Start with some basic items
        this.inventory.add("Health Potion");
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getInventory() {
        return inventory;
    }
    
    public void addItemToInventory(String item) {
        inventory.add(item);
    }
    
    public boolean hasItem(String item) {
        return inventory.contains(item);
    }
    
    public void removeItemFromInventory(String item) {
        inventory.remove(item);
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public int getAttackPower() {
        return attackPower;
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
    
    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }
    
    public String displayInventory() {
        if (inventory.isEmpty()) {
            return "Your inventory is empty.";
        }
        
        StringBuilder sb = new StringBuilder("Inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            sb.append(inventory.get(i));
            if (i < inventory.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}