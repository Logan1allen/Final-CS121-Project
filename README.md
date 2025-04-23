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

Currently only 1 use of this feature, used when choosing the right path in the cave.


Battle

Manages the combat system
Handles turn-based combat between player and monster
Processes battle choices and outcomes

Main Game
function main():
    1. Create a new GameEngine instance
    2. Call the start() method on the GameEngine

GameEngine
function GameEngine():
    1. Initialize a Scanner for user input
    2. Create a new Player object
    3. Initialize game by creating locations and choices

function start():
    1. Display welcome message
    2. Ask for and set player name
    3. Display initial player stats
    4. LOOP until game ends:
        a. Display current location name and description
        b. Show available choices
        c. Get player choice as input
        d. Process choice and determine if game continues
    5. Close scanner and display end message

function processChoice(choiceNum):
    1. Get the selected choice from current location
    2. Check if choice has an associated event
    3. If event exists:
        a. Process special events (battles, endings, etc.)
        b. Return false if game should end, true otherwise
    4. Update current location if there is a next location
    5. Return true to continue game

Battle
function Battle(player, monster, scanner):
    1. Initialize player, monster, scanner, and random number generator

function startBattle():
    1. Display battle start message with combatant info
    2. LOOP while both player and monster have health > 0:
        a. Display battle menu with current health stats
        b. Get player's battle choice
        c. Process player's action (attack, use item, run)
        d. If monster defeated:
            i. Display victory message
            ii. Return true
        e. Process monster's attack
        f. If player defeated:
            i. Display defeat message
            ii. Return false
    3. Return battle outcome (true if won, false if lost)

function processBattleChoice(choice):
    1. If choice is ATTACK:
        a. Calculate player damage
        b. Apply damage to monster
        c. Display attack message
    2. If choice is USE POTION:
        a. Check if player has health potion
        b. If yes, use potion to heal player
        c. If no, display error message
    3. If choice is RUN AWAY:
        a. Generate random number for escape chance
        b. If successful, end battle with escape
        c. If failed, monster gets free attack

Choice creation in GameEngine's initializeGame
function initializeGame():
    1. Create location objects
    2. For each location:
        a. Create choice objects linking to other locations
        b. Add choices to the location
    3. Add all locations to game locations list
    4. Set starting location


