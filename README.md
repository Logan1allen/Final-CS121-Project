Main Classes:

AdventureGame

The entry point of the application
Creates the GameEngine to start the game


GameEngine

Core class that manages the game flow
Contains references to the player, locations, and current location
Handles user input and navigation between locations
Creates battle instances when needed


Location

Represents a place in the game world
Has a name, description, and a list of possible choices


Choice

Represents an option that a player can select
Links to the next location
May trigger special events


Player

Stores player information (name, inventory)
Manages battle attributes (health, attack power)
Handles inventory management


Monster

Represents enemies that can be battled
Has attributes like health and attack power


Battle

Manages the combat system
Handles turn-based combat between player and monster
Processes battle choices and outcomes