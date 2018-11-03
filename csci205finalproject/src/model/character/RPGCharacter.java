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

    public abstract RPGCharacter(String name, int maxHealth, int attack,
                                 int defense,
                                 int inventorySize) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attack = attack;
        this.inventorySize = inventorySize;
        this.inventory = new ArrayList<>();
        this.isAlive = true;
    }

    public void use(Item item) {
        if (item instanceof Consumable) {
            item.use();
        }
        else if (item instanceof Equipment && this.isEquipped(item)) {
            item.unequip();
        }
        else {
            item.equip();
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

    /**
     * Damage calculations based on formulas taken from bulbapedia
     *
     * @param enemy
     */
    public void attack(RPGCharacter enemy) {
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

        double damageCalculation = (10 * (this.attack / enemy.getDefense())) / 50 + 2;
        int trueDamage = (int) Math.round(
                damageCalculation * criticalHitModifier * accuracyModifier);
        enemy.setHealth(enemy.getHealth() - trueDamage);
        System.out.printf("%s did %d damage to %s", this.name, trueDamage,
                          enemy.getName());

        if (enemy.getHealth() == 0) {
            enemy.setIsAlive(false);
            System.out.printf("%s has died", enemy.getName());
        }
    }

    public boolean isInventoryFull() {
        return this.inventory.size() == this.inventorySize;
    }

    public abstract void moveTo(Room room);

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

    public Equipment getSword() {
        return sword;
    }

    public void setSword(Equipment sword) {
        this.sword = sword;
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
