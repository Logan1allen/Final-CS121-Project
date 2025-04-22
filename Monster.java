public class Monster {
    private String name;
    private String description;
    private int health;
    private int attackPower;
    
    public Monster(String name, String description, int health, int attackPower) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.attackPower = attackPower;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getHealth() {
        return health;
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
}