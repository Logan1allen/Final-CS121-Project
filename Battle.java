import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Player player;
    private Monster monster;
    private Scanner scanner;
    private Random random;
    
    public Battle(Player player, Monster monster, Scanner scanner) {
        this.player = player;
        this.monster = monster;
        this.scanner = scanner;
        this.random = new Random();
    }
    
    public boolean startBattle() {
        System.out.println("\n=== BATTLE START: " + player.getName() + " vs " + monster.getName() + " ===");
        System.out.println(monster.getDescription());
        System.out.println("\nThe " + monster.getName() + " has " + monster.getHealth() + " health.");
        System.out.println("You have " + player.getHealth() + " health and " + player.getAttackPower() + " attack power.");
        
        if (player.hasItem("Sword")) {
            System.out.println("Your sword gives you +5 attack power!");
        }
        if (player.hasItem("Shield")) {
            System.out.println("Your shield gives you additional protection!");
        }
        
        // Battle loop
        while (player.getHealth() > 0 && monster.getHealth() > 0) {
            displayBattleMenu();
            int choice = getBattleChoice();
            
            // Player's turn
            processBattleChoice(choice);
            
            // Check if monster is defeated
            if (monster.getHealth() <= 0) {
                System.out.println("\nYou defeated the " + monster.getName() + "!");
                return true;
            }
            
            // Monster's turn
            monsterAttack();
            
            // Check if player is defeated
            if (player.getHealth() <= 0) {
                System.out.println("\nYou have been defeated by the " + monster.getName() + "!");
                System.out.println("GAME OVER");
                return false;
            }
        }
        
        return false;
    }
    
    private void displayBattleMenu() {
        System.out.println("\n--- Your Turn ---");
        System.out.println("Your Health: " + player.getHealth() + " | " + monster.getName() + "'s Health: " + monster.getHealth());
        System.out.println("\nWhat will you do?");
        System.out.println("1. Attack");
        System.out.println("2. Use potion (if available)");
        System.out.println("3. Try to run away");
    }
    
    private int getBattleChoice() {
        int choice = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("Enter your choice (1-3): ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                if (choice >= 1 && choice <= 3) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        return choice;
    }
    
    private void processBattleChoice(int choice) {
        switch (choice) {
            case 1: // Attack
                int damageDealt = calculatePlayerDamage();
                monster.takeDamage(damageDealt);
                System.out.println("You attack the " + monster.getName() + " for " + damageDealt + " damage!");
                break;
                
            case 2: // Use potion
                if (player.hasItem("Health Potion")) {
                    player.removeItemFromInventory("Health Potion");
                    int healthRestored = 20;
                    player.heal(healthRestored);
                    System.out.println("You drink a health potion and restore " + healthRestored + " health!");
                } else {
                    System.out.println("You don't have any health potions!");
                    // Player loses their turn
                }
                break;
                
            case 3: // Run away
                int escapeChance = random.nextInt(100);
                if (escapeChance < 40) { // 40% chance to escape
                    System.out.println("You successfully escaped from the " + monster.getName() + "!");
                    player.takeDamage(0); // Just to make sure the battle loop exits
                    monster.takeDamage(monster.getHealth()); // Set monster health to 0 to end battle
                } else {
                    System.out.println("You failed to escape!");
                    // Monster gets a free attack
                    monsterAttack();
                }
                break;
        }
    }
    
    private int calculatePlayerDamage() {
        int baseDamage = player.getAttackPower();
        int damageVariation = random.nextInt(5) - 2; // -2 to +2 damage variation
        
        int totalDamage = baseDamage + damageVariation;
        
        // Bonus damage for sword
        if (player.hasItem("Sword")) {
            totalDamage += 5;
        }
        
        return Math.max(1, totalDamage); // Minimum 1 damage
    }
    
    private void monsterAttack() {
        System.out.println("\n--- " + monster.getName() + "'s Turn ---");
        
        int damageDealt = monster.getAttackPower() + random.nextInt(5) - 2; // -2 to +2 damage variation
        
        // Damage reduction if player has shield
        if (player.hasItem("Shield")) {
            damageDealt = Math.max(1, damageDealt - 3); // Shield reduces damage by 3, minimum 1
            System.out.println("Your shield blocks some of the damage!");
        }
        
        damageDealt = Math.max(1, damageDealt); // Minimum 1 damage
        player.takeDamage(damageDealt);
        
        System.out.println("The " + monster.getName() + " attacks you for " + damageDealt + " damage!");
        System.out.println("You have " + player.getHealth() + " health remaining.");
    }
}