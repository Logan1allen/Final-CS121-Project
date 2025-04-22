import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameEngine {
    private Scanner scanner;
    private Player player;
    private Location currentLocation;
    private List<Location> gameLocations;
    
    public GameEngine() {
        scanner = new Scanner(System.in);
        player = new Player();
        initializeGame();
    }
    
    private void initializeGame() {
        // Create all locations
        gameLocations = new ArrayList<>();
        
        // Create locations
        Location caveEntrance = new Location(
            "Cave Entrance",
            "You stand at the entrance of a mysterious cave. The dark opening looms before you, while a small path circles around the outside."
        );
        
        Location insideCave = new Location(
            "Inside Cave",
            "You're inside the dimly lit cave. Water drips from stalactites overhead. The path splits in two directions."
        );
        
        Location outsidePath = new Location(
            "Forest Path",
            "A narrow path winds through thick trees. In the distance, you spot what appears to be an old cabin."
        );
        
        Location leftCavePath = new Location(
            "Left Tunnel",
            "The left path narrows as you proceed. You start to hear the sound of rushing water."
        );
        
        Location rightCavePath = new Location(
            "Right Tunnel",
            "The right path leads deeper into the cave. The air becomes cooler, and you hear strange noises echoing in the distance."
        );
        
        Location undergroundRiver = new Location(
            "Underground River",
            "You've discovered an underground river with a small wooden boat tied to a rock."
        );
        
        Location trollChamber = new Location(
            "Troll's Chamber",
            "This large chamber is home to a fearsome cave troll, currently sleeping in the corner."
        );
        
        Location cabin = new Location(
            "Abandoned Cabin",
            "An old wooden cabin stands in a small clearing. Its windows are grimy and the door is slightly ajar."
        );
        
        Location treasureChamber = new Location(
            "Treasure Chamber",
            "You've found a hidden chamber filled with ancient gold coins and jewels! You're rich!"
        );
        
        Location crystalCavern = new Location(
            "Crystal Cavern",
            "This breathtaking cavern is filled with glittering crystals of various colors that catch and reflect light in mesmerizing patterns."
        );
        
        // Add choices to locations
        caveEntrance.addChoice(new Choice("Enter the cave", insideCave));
        caveEntrance.addChoice(new Choice("Walk around the outside", outsidePath));
        caveEntrance.addChoice(new Choice("Go back home", null, "ending_go_home"));
        
        insideCave.addChoice(new Choice("Take the left path", leftCavePath));
        insideCave.addChoice(new Choice("Take the right path", rightCavePath));
        insideCave.addChoice(new Choice("Go back outside", caveEntrance));
        
        leftCavePath.addChoice(new Choice("Investigate the sound of water", undergroundRiver));
        leftCavePath.addChoice(new Choice("Go back", insideCave));
        
        rightCavePath.addChoice(new Choice("Continue forward", trollChamber));
        rightCavePath.addChoice(new Choice("Go back", insideCave));
        
        undergroundRiver.addChoice(new Choice("Get in the boat and sail downstream", treasureChamber, "ending_treasure"));
        undergroundRiver.addChoice(new Choice("Wade across the river", leftCavePath, "event_swept_away"));
        undergroundRiver.addChoice(new Choice("Go back", leftCavePath));
        
        trollChamber.addChoice(new Choice("Fight the troll", null, "event_troll_wakes"));
        trollChamber.addChoice(new Choice("Look for another way around", crystalCavern));
        trollChamber.addChoice(new Choice("Go back", rightCavePath));
        
        outsidePath.addChoice(new Choice("Keep following the path", cabin));
        outsidePath.addChoice(new Choice("Return to the cave entrance", caveEntrance));
        
        cabin.addChoice(new Choice("Enter the cabin", treasureChamber, "ending_map_treasure"));
        cabin.addChoice(new Choice("Look through the windows", outsidePath, "event_movement_inside"));
        cabin.addChoice(new Choice("Return to the cave entrance", caveEntrance));
        
        crystalCavern.addChoice(new Choice("Collect crystals and leave", caveEntrance, "ending_crystal_treasure"));
        
        // Add all locations to game locations list
        gameLocations.add(caveEntrance);
        gameLocations.add(insideCave);
        gameLocations.add(outsidePath);
        gameLocations.add(leftCavePath);
        gameLocations.add(rightCavePath);
        gameLocations.add(undergroundRiver);
        gameLocations.add(trollChamber);
        gameLocations.add(cabin);
        gameLocations.add(treasureChamber);
        gameLocations.add(crystalCavern);

        // Set starting location
        currentLocation = caveEntrance;
    }
    public void start() {
        System.out.println("Welcome to the Adventure Game!");
        System.out.println("What is your name, adventurer?");
        String playerName = scanner.nextLine();
        player.setName(playerName);
        System.out.println("Good luck on your journey, " + player.getName() + "!");
        System.out.println("You start with " + player.getHealth() + " health and " + player.getAttackPower() + " attack power.");
        System.out.println(player.displayInventory());
        System.out.println();
        
        // Main game loop
        boolean gameRunning = true;
        while (gameRunning) {
            displayCurrentLocation();
            int choice = getPlayerChoice();
            gameRunning = processChoice(choice);
        }
        
        scanner.close();
        System.out.println("Thanks for playing!");
    }
    
    private void displayCurrentLocation() {
        System.out.println("\n=== " + currentLocation.getName() + " ===");
        System.out.println(currentLocation.getDescription());
        System.out.println("\nWhat would you like to do?");
        
        List<Choice> choices = currentLocation.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i).getDescription());
        }
    }
    
    private int getPlayerChoice() {
        int numChoices = currentLocation.getChoices().size();
        int choice = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("Enter your choice (1-" + numChoices + "): ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                if (choice >= 1 && choice <= numChoices) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between 1 and " + numChoices + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        
        return choice;
    }
    
    private boolean processChoice(int choiceNum) {
        Choice selectedChoice = currentLocation.getChoices().get(choiceNum - 1);
        String event = selectedChoice.getEvent();
        
        // Process special events
        if (event != null) {
            switch (event) {
                case "ending_go_home":
                    System.out.println("\nDeciding that adventure isn't for you today, you turn around and head back home.");
                    System.out.println("Perhaps another day you'll be brave enough to explore the cave.");
                    System.out.println("\nTHE END");
                    return false;
                    
                case "ending_treasure":
                    System.out.println("\nYou get in the boat and push off from the shore.");
                    System.out.println("The current carries you swiftly downstream.");
                    System.out.println("After a thrilling ride, you arrive at a hidden treasure chamber!");
                    System.out.println("You've found ancient gold coins and jewels! You're rich!");
                    System.out.println("\nTHE END - You win!");
                    return false;
                    
                case "ending_map_treasure":
                    System.out.println("\nYou push open the creaky door and step inside the cabin.");
                    System.out.println("It's dusty and appears to be quite worn, an old man appears to be making some tea.");
                    System.out.println("The old man greets you and offers you a cup of his delicous Jasmine Tea.");
                    System.out.println("He hands you a map the leads to treasure.");
                    System.out.println("OLD MAN: In my youth, I searched long for this treasure but I am too Old now.");
                    System.out.println("Following the map, you locate the hidden entrance and find the treasure!");
                    System.out.println("\nTHE END - You win!");
                    return false;
                    
                case "ending_crystal_treasure":
                    System.out.println("\nYou start hand picking tons of these colorful cyrstals and putting them in your pouch");
                    System.out.println("This cave is so beautiful that you start to think,");
                    System.out.println("Man this is the perfect place to bleed out while waiting to give instructions to another adventurer...");
                    System.out.println("But I mean you got some sick treasures so lets go home and become rich!");
                    System.out.println("\nTHE END - You win!");
                    return false;
                    
                case "event_swept_away":
                    System.out.println("\nYou attempt to wade across the river, but the current is stronger than it appeared.");
                    System.out.println("You're swept off your feet and carried downstream.");
                    System.out.println("Luckily, you manage to grab onto a rock and climb back to shore.");
                    System.out.println("Soaking wet, you decide to go back the way you came.");
                    break;
                    
                case "event_troll_wakes":
                    System.out.println("\nYou attempt to tiptoe past the troll...");
                    System.out.println("But you accidentally kick a small stone, which clatters loudly!");
                    System.out.println("The troll wakes up with a roar and spots you!");
                    
                    // Create troll monster and start battle
                    Monster troll = new Monster(
                        "Cave Troll", 
                        "A massive, green-skinned troll with bulging muscles and a terrible smell.",
                        40, // health
                        8   // attack power
                    );
                    
                    Battle trollBattle = new Battle(player, troll, scanner);
                    boolean battleWon = trollBattle.startBattle();
                    
                    if (!battleWon) {
                        // Player lost battle and died
                        return false;
                    }
                    
                    // Player won battle
                    System.out.println("\nWith the troll defeated, you can safely explore the chamber.");
                    System.out.println("You notice a passage behind where the troll was standing.");
                    System.out.println("You find a shield on the ground and add it to your inventory!");
                    player.addItemToInventory("Shield");
                    
                    // Instead of running away, now the player defeated the troll
                    // Let's add a special reward - a passage to the crystal cavern
                    Location nextLocation = selectedChoice.getNextLocation();
                    for (Location location : gameLocations) {
                        if (location.getName().equals("Crystal Cavern")) {
                            currentLocation = location;
                            return true;
                        }
                    }
                    break;
                    
                case "event_movement_inside":
                    System.out.println("\nPeering through the grimy windows, you can make out shapes inside.");
                    System.out.println("You notice movement! Someone or something is in there!");
                    System.out.println("Startled, you back away from the cabin and decide to return to the cave entrance.");
                    break;
            }
        }
        
        // Move to next location if there is one
        Location nextLocation = selectedChoice.getNextLocation();
        if (nextLocation != null) {
            currentLocation = nextLocation;
        }
        
        return true;
    }
}
