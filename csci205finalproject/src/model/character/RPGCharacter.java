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
 * @author lts010
 */
public abstract class RPGCharacter {

    private String name;
    private int maxHealth;
    private int health;
    private int attack;
    private int defense;
    private int inventorySize;
    private ArrayList<Item> inventory;
    private Equipment weapon;
    private Equipment shield;
    private Equipment armor;
    private Room location;
    private boolean isAlive;
    public static final double DEFAULT_MISS_CHANCE = 0.2;
    public static final double DEFAULT_CRITICAL_CHANCE = 0.0625;

    public RPGCharacter(String name, int maxHealth, int attack,
                        int defense,
                        int inventorySize) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.inventorySize = inventorySize;
        this.inventory = new ArrayList<>();
        this.isAlive = true;
    }

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

        double damageCalculation = this.attack - enemy.getDefense();
        int trueDamage = (int) Math.round(
                damageCalculation * criticalHitModifier * accuracyModifier);
        enemy.setHealth(enemy.getHealth() - trueDamage);
        if (enemy.getHealth() <= 0) {
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

    public boolean isInventoryFull() {
        return this.inventory.size() >= this.inventorySize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public Equipment getWeapon() {
        return weapon;
    }

    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
    }

    public Equipment getShield() {
        return shield;
    }

    public void setShield(Equipment shield) {
        this.shield = shield;
    }

    public Equipment getArmor() {
        return armor;
    }

    public void setArmor(Equipment armor) {
        this.armor = armor;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
