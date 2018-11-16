/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles, Kartikeya Sharma, Jason Kang, and Claudia Shrefler
* Date: Nov 3, 2018
* Time: 1:47:41 PM
*
* Project: csci205FinalProject
* Package: RPGCharacter
* File: RPGCharacter
* Description: This file contains RPGCharacter
*
* ****************************************
 */
package model.character;

import java.util.ArrayList;
import java.util.Random;
import model.item.ConsumableItem;
import model.item.Equipment;
import model.item.EquipmentType;
import model.item.Item;
import model.map.Room;

/**
 * Abstract class for all characters
 *
 * @author lts010, ks061
 */
public abstract class RPGCharacter {

    /**
     * Name of character
     */
    private String name;
    /**
     * Character statistics
     */
    private RPGCharacterStats characterStats;
    /**
     * Current size of inventory
     */
    private int inventorySize;
    /**
     * ArrayList of inventory items
     */
    private ArrayList<Item> inventory;
    /**
     * Type of equipment to be used as weapon
     */
    private Equipment weapon;
    /**
     * Type of equipment used as shield
     */
    private Equipment shield;
    /**
     * Type of equipment used as armor
     */
    private Equipment armor;
    /**
     * Room representing a location
     */
    private Room location;
    /**
     * Boolean representing if character is alive
     */
    private boolean isAlive;
    /**
     * Default chance of missing while using a weapon during attack
     */
    public static final double DEFAULT_MISS_CHANCE = 0.2;
    /**
     * Default chance of a critical hit while using a weapon during attack
     */
    public static final double DEFAULT_CRITICAL_CHANCE = 0.0625;

    /**
     * Constructor for RPGCharacter initializing all its attributes
     *
     * @param name name of character
     * @param characterStats character stats
     * @param inventorySize size of the inventory
     */
    public RPGCharacter(String name, RPGCharacterStats characterStats,
                        int inventorySize) {
        this.name = name;
        this.characterStats = characterStats;
        this.inventorySize = inventorySize;
        this.inventory = new ArrayList<>();
        this.isAlive = true;
    }

    /**
     * Uses a consumable item or equips/unequips an equipment item
     *
     * @param item - item to be used
     */
    public void use(Item item) {
        if (item instanceof ConsumableItem) {
            ConsumableItem consumable = (ConsumableItem) item;
            consumable.consume();
        }
        else if (item instanceof Equipment && this.isEquipped((Equipment) item)) {
            Equipment equipment = (Equipment) item;
            equipment.unequip();
        }
        else {
            Equipment equipment = (Equipment) item;
            equipment.equip();
        }
    }

    /**
     * Determines the type of equipment and equips it
     *
     * @param equipment - equipment to be equipped
     * @return boolean representing equipment equipped
     */
    public boolean isEquipped(Equipment equipment) {
        if (equipment.getType() == EquipmentType.WEAPON) {
            return equipment == this.weapon;
        }
        else if (equipment.getType() == EquipmentType.SHIELD) {
            return equipment == this.shield;
        }
        else {
            return equipment == this.armor;
        }
    }

    /**
     * Attacks an enemy RPG character Damage calculations based on formulas
     * taken from bulbapedia
     *
     * @param enemy - RPGCharacter to attack
     */
    public String attack(RPGCharacter enemy) {
        Random RandomModifiers = new Random();
        double criticalHitModifier;
        double accuracyModifier;

        if (RandomModifiers.nextDouble() < RPGCharacter.DEFAULT_MISS_CHANCE) {
            accuracyModifier = 0.0;
        }
        else {
            accuracyModifier = 1.0;
        }

        if (RandomModifiers.nextDouble() < RPGCharacter.DEFAULT_CRITICAL_CHANCE) {
            criticalHitModifier = 1.5;
        }
        else {
            criticalHitModifier = 1.0;
        }

        double damageCalculation = this.characterStats.getAttack() - enemy.characterStats.getDefense();
        int trueDamage = (int) Math.round(
                damageCalculation * criticalHitModifier * accuracyModifier);
        enemy.characterStats.setHealth(
                enemy.characterStats.getHealth() - trueDamage);
        if (enemy.characterStats.getHealth() <= 0) {
            enemy.setIsAlive(false);
        }
        if (accuracyModifier == 0) {
            return String.format("%s missed and did no damage to %s", this.name,
                                 enemy.getName());
        }
        if (criticalHitModifier == 1.5) {
            return String.format("Critical Hit! %s did %d damage to %s",
                                 this.name, trueDamage, enemy.getName());
        }
        return String.format("%s did %d damage to %s", this.name, trueDamage,
                             enemy.getName());
    }

    /**
     * Determines if inventory is full
     *
     * @return boolean representing if inventory is at max size
     */
    public boolean isInventoryFull() {
        return this.inventory.size() >= this.inventorySize;
    }

    /**
     * Gets the character statistics of this character
     *
     * @return character statistics of this character
     *
     * @author ks061
     */
    public RPGCharacterStats getCharacterStats() {
        return characterStats;
    }

//    /**
//     * Abstract moveTo method to be implemented by other classes
//     *
//     * @param room - Room to move to
//     * @return String representing who moved where
//     */
//    public abstract String moveTo(Room room);
    /**
     * Gets the name of the character
     *
     * @return String representing the character's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the character
     *
     * @param name - name to be set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current size of the inventory
     *
     * @return integer for inventory size
     */
    public int getInventorySize() {
        return inventorySize;
    }

    /**
     * Sets the current inventory size
     *
     * @param inventorySize - integer representing size to be set to
     */
    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    /**
     * Gets the inventory
     *
     * @return ArrayList of items representing the inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Sets the inventory
     *
     * @param inventory - ArrayList of items to set the inventory
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Gets the weapon equipment variable
     *
     * @return Equipment object representing weapon
     */
    public Equipment getWeapon() {
        return weapon;
    }

    /**
     * Sets the weapon variable
     *
     * @param weapon - equipment object for weapon to be set to
     */
    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
    }

    /**
     * Gets the shield variable
     *
     * @return Equipment object representing shield
     */
    public Equipment getShield() {
        return shield;
    }

    /**
     * Sets the shield variable
     *
     * @param shield - equipment object for shield to be set to
     */
    public void setShield(Equipment shield) {
        this.shield = shield;
    }

    /**
     * Gets the armor variable
     *
     * @return Equipment object representing armor
     */
    public Equipment getArmor() {
        return armor;
    }

    /**
     * Sets the armor variable
     *
     * @param armor - equipment object for armor to be set to
     */
    public void setArmor(Equipment armor) {
        this.armor = armor;
    }

    /**
     * Gets the current location of character
     *
     * @return Room object representing location
     */
    public Room getLocation() {
        return location;
    }

    /**
     * Sets the current location of character
     *
     * @param location - Room object for location to be set to
     */
    public void setLocation(Room location) {
        this.location = location;
    }

    /**
     * Checks if character is alive
     *
     * @return boolean representing if character is alive
     */
    public boolean isIsAlive() {
        return isAlive;
    }

    /**
     * Sets the character's aliveness
     *
     * @param isAlive - boolean representing if character is alive
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
