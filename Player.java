import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<String> inventory;
    
    public Player() {
        inventory = new ArrayList<>();
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
}
