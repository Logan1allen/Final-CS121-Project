Originally this project was just going to be a simple text based adventure until I had realized that it needed to be more than just that. So to make up for it, I added in a fight that can be found by user choice. 

This project is designed to be a short and fun text based choose your own adventure with a battle down one of the paths.




USE-CASE
This Project was designed be used by anyone seeking shortform text based entertainment. The User is trying to win the game simply by their choosen provided choices.

TEXT UML (This was easier to do than the diagram)

Text Adventure Game UML Class Diagram
Classes
AdventureGame
Attributes:

None

Methods:

+main(args: String[]): void

GameEngine
Attributes:

-scanner: Scanner
-player: Player
-currentLocation: Location
-gameLocations: List<Location>

Methods:

+GameEngine()
+start(): void
-initializeGame(): void
-displayCurrentLocation(): void
-getPlayerChoice(): int
-processChoice(choiceNum: int): boolean

Player
Attributes:

-name: String
-inventory: List<String>
-health: int
-maxHealth: int
-attackPower: int

Methods:

+Player()
+getName(): String
+setName(name: String): void
+getInventory(): List<String>
+addItemToInventory(item: String): void
+hasItem(item: String): boolean
+removeItemFromInventory(item: String): void
+getHealth(): int
+getAttackPower(): int
+takeDamage(damage: int): void
+heal(amount: int): void
+displayInventory(): String

Location
Attributes:

-name: String
-description: String
-choices: List<Choice>

Methods:

+Location(name: String, description: String)
+getName(): String
+getDescription(): String
+getChoices(): List<Choice>
+addChoice(choice: Choice): void

Choice
Attributes:

-description: String
-nextLocation: Location
-event: String

Methods:

+Choice(description: String, nextLocation: Location)
+Choice(description: String, nextLocation: Location, event: String)
+getDescription(): String
+getNextLocation(): Location
+getEvent(): String

Monster
Attributes:

-name: String
-description: String
-health: int
-attackPower: int

Methods:

+Monster(name: String, description: String, health: int, attackPower: int)
+getName(): String
+getDescription(): String
+getHealth(): int
+getAttackPower(): int
+takeDamage(damage: int): void

Battle
Attributes:

-player: Player
-monster: Monster
-scanner: Scanner
-random: Random

Methods:

+Battle(player: Player, monster: Monster, scanner: Scanner)
+startBattle(): boolean
-displayBattleMenu(): void
-getBattleChoice(): int
-processBattleChoice(choice: int): void
-calculatePlayerDamage(): int
-monsterAttack(): void