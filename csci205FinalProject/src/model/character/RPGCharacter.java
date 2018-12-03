/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2018
*
* Name: Logan Stiles, Kartikeya Sharma, Jason Kang, and Claudia Shrefler
* Date: Nov 3, 2018
* Time: 1:47:41 PM
*
* Project: csci205FinalProject
* Package: model.character
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
    private final RPGCharacterStats characterStats;
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
     * The factor by which damage from a critical hit differs from that of a
     * standard hit
     */
    public static final double CRITICAL_HIT_FACTOR = 1.5;
    /**
     * The minimum base damage before Modifiers
     */
    public static final double MIN_BASE_DAMAGE = 5;

    /**
     * Constructor for RPGCharacter initializing all its attributes
     *
     * @param name name of character
     * @param characterStats character stats
     * @param inventorySize size of the inventory
     *
     * @author lts010
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
     * @param item item to be used
     *
     * @author lts010
     */
    public void use(Item item) {
        if (item instanceof ConsumableItem) {
            ConsumableItem consumable = (ConsumableItem) item;
            this.consume(consumable);
        }
        else if (item instanceof Equipment) {
            if (this.isEquipped((Equipment) item)) {
                Equipment equipment = (Equipment) item;
                this.unequip(equipment);
            }
            else {
                Equipment equipment = (Equipment) item;
                this.equip(equipment);
            }
        }
    }

    /**
     * Determines the type of equipment and equips it
     *
     * @param equipment equipment to be equipped
     *
     * @return boolean representing equipment equipped
     *
     * @author ks061, lts010
     */
    public boolean isEquipped(Equipment equipment) {
        switch (equipment.getType()) {
            case WEAPON:
                return equipment == this.weapon;
            case SHIELD:
                return equipment == this.shield;
            default:
                return equipment == this.armor;
        }
    }

    /**
     * Returns the critical hit modifier if it will be applied to the damage
     * (based on the RPGCharacter's default critical chance); otherwise returns
     * 1.0
     *
     * @return critical hit modifier
     *
     * @author ks061
     */
    private double getCriticalHitModifier() {
        Random randomNumberGenerator = new Random();
        if (randomNumberGenerator.nextDouble() < RPGCharacter.DEFAULT_CRITICAL_CHANCE) {
            return CRITICAL_HIT_FACTOR;
        }
        else {
            return 1.0;
        }
    }

    /**
     * Returns 0.0 if the attack is unsuccessful (based on the RPGCharacter's
     * default miss chance); otherwise returns 1.0
     *
     * @return 0.0 if the attack is unsuccessful (based on the RPGCharacter's
     * default miss chance); otherwise returns 1.0
     *
     * @author ks061
     */
    private double getAccuracyModifier(RPGCharacter enemy) {
        Random randomNumberGenerator = new Random();
        double missChance = DEFAULT_MISS_CHANCE;
        if (this.characterStats.getAttack() < enemy.characterStats.getDefense()) {
            missChance += randomNumberGenerator.nextDouble() * 0.5;
        }
        if (randomNumberGenerator.nextDouble() < missChance) {
            return 0.0;
        }
        else {
            return 1.0;
        }
    }

    /**
     * Calculates the damage dealt to the enemy by the character based upon the
     * critical hit and accuracy modifiers
     *
     * @param enemy enemy that this RPGCharacter is attacking
     *
     * @return damage done to the enemy
     *
     * @author ks061
     */
    private int calculateDamage(RPGCharacter enemy, double criticalHitModifier,
                                double accuracyModifier) {
        Random randomNumberGenerator = new Random();
        double damage = MIN_BASE_DAMAGE;

        if (this.characterStats.getAttack() < enemy.characterStats.getDefense()) {
            damage += this.characterStats.getAttack() - enemy.characterStats.getDefense();
        }

        int roundedDamage = (int) Math.round(
                damage * criticalHitModifier * accuracyModifier);
        if (roundedDamage <= 0) {
            return 0;
        }
        if (enemy.getCharacterStats().getMaxHealth() == enemy.getCharacterStats().getHealth() && roundedDamage >= enemy.getCharacterStats().getMaxHealth()) {
            roundedDamage = enemy.getCharacterStats().getMaxHealth() - 1;
        }
        return roundedDamage;
    }

    /**
     * Returns message detailing the nature of damage done to character being
     * battled
     *
     * @param enemy enemy being attacked
     * @param damage damage dealt to the enemy from the attack
     * @param criticalHitModifier critical hit modifier of the attack
     *
     * @return message detailing the nature of damage done to character being
     * battled
     *
     * @author ks061
     */
    public String getAttackMessage(RPGCharacter enemy, int damage,
                                   double criticalHitModifier) {
        if (damage == 0) {
            return String.format("%s missed and did no damage to %s", this.name,
                                 enemy.getName());
        }
        if (criticalHitModifier == 1.5) {
            return String.format("Critical Hit! %s did %d damage to %s",
                                 this.name, damage, enemy.getName());
        }
        return String.format("%s did %d damage to %s", this.name, damage,
                             enemy.getName());
    }

    /**
     * Attack the given enemy lowering their health based
     *
     * @param enemy - RPGCharacter to attack
     *
     * @return message detailing nature of damage done to character being
     * battled
     *
     * @author lts010, ks061
     */
    public String attack(RPGCharacter enemy) {

        double criticalHitModifier = getCriticalHitModifier();
        double accuracyModifier = getAccuracyModifier(enemy);

        int damage = calculateDamage(enemy, criticalHitModifier,
                                     accuracyModifier);

        enemy.characterStats.setHealth(enemy.characterStats.getHealth() - damage);
        if (enemy.characterStats.getHealth() <= 0) {
            enemy.setIsAlive(false);
        }
        return getAttackMessage(enemy, damage, criticalHitModifier);
    }

    /**
     * Determines if inventory is full
     *
     * @return boolean representing if inventory is at max size
     *
     * @author ks061, lts010
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

    /**
     * Gets the name of the character
     *
     * @return String representing the character's name
     *
     * @author ks061, lts010
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the character
     *
     * @param name name to be set to
     *
     * @author ks061, lts010
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current size of the inventory
     *
     * @return integer for inventory size
     *
     * @author ks061, lts010
     */
    public int getInventorySize() {
        return inventorySize;
    }

    /**
     * Sets the current inventory size
     *
     * @param inventorySize integer representing size to be set to
     *
     * @author ks061, lts010
     */
    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    /**
     * Gets the inventory
     *
     * @return ArrayList of items representing the inventory
     *
     * @author ks061, lts010
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Sets the inventory
     *
     * @param inventory ArrayList of items to set the inventory
     *
     * @author ks061, lts010
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Gets the weapon equipment variable
     *
     * @return equipment object representing weapon
     *
     * @author ks061, lts010
     */
    public Equipment getWeapon() {
        return weapon;
    }

    /**
     * Sets the weapon variable
     *
     * @param weapon equipment object for weapon to be set to
     *
     * @author ks061, lts010
     */
    public void setWeapon(Equipment weapon) {
        this.weapon = weapon;
    }

    /**
     * Gets the shield variable
     *
     * @return equipment object representing shield
     *
     * @author ks061, lts010
     */
    public Equipment getShield() {
        return shield;
    }

    /**
     * Sets the shield variable
     *
     * @param shield equipment object for shield to be set to
     *
     * @author ks061, lts010
     */
    public void setShield(Equipment shield) {
        this.shield = shield;
    }

    /**
     * Gets the armor variable
     *
     * @return equipment object representing armor
     *
     * @author ks061, lts010
     */
    public Equipment getArmor() {
        return armor;
    }

    /**
     * Sets the armor variable
     *
     * @param armor equipment object for armor to be set to
     *
     * @author ks061, lts010
     */
    public void setArmor(Equipment armor) {
        this.armor = armor;
    }

    /**
     * Gets the current location of character
     *
     * @return Room object representing location
     *
     * @author ks061, lts010
     */
    public Room getLocation() {
        return location;
    }

    /**
     * Sets the current location of character
     *
     * @param location Room object for location to be set to
     *
     * @author ks061, lts010
     */
    public void setLocation(Room location) {
        this.location = location;
    }

    /**
     * Checks if character is alive
     *
     * @return boolean representing if character is alive
     *
     * @author ks061, lts010
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Sets whether the character is alive or not
     *
     * @param isAlive boolean representing if character is alive
     *
     * @author ks061, lts010
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * Adjusts the character statistics, such as maximum health, attack,
     * defense, and inventory size, based upon the statistics of the equipment
     * and current statistics of this RPGCharacter
     *
     * @param equipment equipment this RPGCharacter is being equipped with
     *
     * @author ks061, ishk001, lts010
     */
    private void adjustCharacterStatisticsFromEquip(Equipment equipment) {
        this.getCharacterStats().setMaxHealth(
                this.getCharacterStats().getMaxHealth() + equipment.getItemStatistics().getDeltaHealth());
        this.getCharacterStats().setAttack(
                this.getCharacterStats().getAttack() + equipment.getItemStatistics().getDeltaAttack());
        this.getCharacterStats().setDefense(
                this.getCharacterStats().getDefense() + equipment.getItemStatistics().getDeltaDefense());
        this.setInventorySize(
                this.getInventorySize() + equipment.getItemStatistics().getDeltaInventory());
    }

    /**
     * Equips the equipment based on its type
     *
     * @param equipment equipment to be equipped
     * @return string representing what was equipped
     *
     * @author ishk001, lts010, ks061
     */
    public String equip(Equipment equipment) {
        adjustCharacterStatisticsFromEquip(equipment);
        switch (equipment.getType()) {
            case WEAPON:
                if (this.getWeapon() != null) {
                    return this.swapEquipment(equipment);
                }
                else {
                    this.setWeapon(equipment);
                }
                break;
            case ARMOR:
                if (this.getArmor() != null) {
                    return this.swapEquipment(equipment);
                }
                else {
                    this.setArmor(equipment);
                }
                break;
            case SHIELD:
                if (this.getShield() != null) {
                    return this.swapEquipment(equipment);
                }
                else {
                    this.setShield(equipment);
                }
                break;
        }
        this.getInventory().remove(equipment);
        return String.format("Equipped the %s as a %s",
                             equipment.getName(), equipment.getType().name());
    }

    /**
     * Adjusts the character statistics, such as maximum health, attack,
     * defense, and inventory size, based upon the statistics of the equipment
     * and current statistics of this RPGCharacter
     *
     * @param equipment equipment being unequipped from this RPGCharacter
     *
     * @author ks061, ishk001, lts010
     */
    public void adjustCharacterStatisticsFromUnequip(Equipment equipment) {
        this.getCharacterStats().setMaxHealth(
                this.getCharacterStats().getMaxHealth() - equipment.getItemStatistics().getDeltaHealth());
        this.getCharacterStats().setAttack(
                this.getCharacterStats().getAttack() - equipment.getItemStatistics().getDeltaAttack());
        this.getCharacterStats().setDefense(
                this.getCharacterStats().getDefense() - equipment.getItemStatistics().getDeltaDefense());
        this.setInventorySize(
                this.getInventorySize() - equipment.getItemStatistics().getDeltaInventory());
    }

    /**
     * Unequips the equipment and adds it to inventory
     *
     * @param equipment the equipment you want to unequip
     * @return message detailing unequipment
     *
     * @author ishk001, lts010, ks061
     */
    public String unequip(Equipment equipment) {
        if (this.isInventoryFull()) {
            return String.format(
                    "Cannot unequip the %s because your inventory is full",
                    equipment.getName());
        }
        if (null != equipment.getType()) {
            switch (equipment.getType()) {
                case WEAPON:
                    this.setWeapon(null);
                    break;
                case ARMOR:
                    this.setArmor(null);
                    break;
                case SHIELD:
                    this.setShield(null);
                    break;
                default:
                    break;
            }
        }
        this.inventory.add(equipment);
        adjustCharacterStatisticsFromUnequip(equipment);
        return String.format("Unequipped the %s and added it to your inventory",
                             equipment.getName());
    }

    /**
     * Adds an item to the inventory if the inventory is not already full
     *
     * @param item The item to be added.
     * @return true if the item was added, false if the inventory was full
     *
     * @author ishk001, lts010, ks061
     */
    public boolean addToInventory(Item item) {
        if (isInventoryFull()) {
            return (false);
        }
        else {
            inventory.add(item);
            return (true);
        }
    }

    /**
     * Swaps the current equipment with something from the inventory
     *
     * @param equipment equipment that you want to equip
     * @return string representing what items were swapped
     *
     * @author ishk001, ks061
     */
    public String swapEquipment(Equipment equipment) {
        this.inventory.remove(equipment);
        Equipment oldEquipment = null;
        switch (equipment.getType()) {
            case WEAPON:
                oldEquipment = this.weapon;
                this.unequip(this.weapon);
                this.setWeapon(equipment);
                break;
            case ARMOR:
                oldEquipment = this.armor;
                this.unequip(this.armor);
                this.setArmor(equipment);
                break;
            case SHIELD:
                oldEquipment = this.shield;
                this.unequip(this.shield);
                this.setShield(equipment);
                break;
        }
        return String.format("Unequipped the %s and equipped the %s",
                             oldEquipment.getName(),
                             equipment.getName());
    }

    /**
     * Adjusts the character statistics, such as maximum health, attack,
     * defense, and inventory size, based upon the statistics of the consumable
     * item and current statistics of this RPGCharacter
     *
     * @param consumableItem equipment being consumed by this RPGCharacter
     *
     * @author ks061, ishk001, lts010
     */
    private void adjustCharacterStatisticsFromConsume(
            ConsumableItem consumableItem) {
        //For HEALTH potions
        int curHealth = this.getCharacterStats().getHealth();
        if (curHealth + consumableItem.getItemStatistics().getDeltaHealth() > this.getCharacterStats().getMaxHealth()) {
            //sets the health of the player to max health if health + potion turns
            //out to fill up the health bar of the player
            this.getCharacterStats().setHealth(
                    this.getCharacterStats().getMaxHealth());
        }
        else {
            this.getCharacterStats().setHealth(
                    curHealth + consumableItem.getItemStatistics().getDeltaHealth());
        }
        //For ATTACK potions
        this.getCharacterStats().setAttack(
                this.getCharacterStats().getAttack() + consumableItem.getItemStatistics().getDeltaAttack());
        //For DEFENSE potions
        this.getCharacterStats().setDefense(
                this.getCharacterStats().getDefense() + consumableItem.getItemStatistics().getDeltaDefense());
        //For items that permanantely increase inventory size
        this.setInventorySize(
                this.getInventorySize() + consumableItem.getItemStatistics().getDeltaInventory());
    }

    /**
     * Consumes the items and correspondingly change the health of item owner
     *
     * @param consumableItem consumable item being consumed by this character
     * @return String representing what was consumed
     *
     * @author ishk001, lts010, ks061
     */
    public String consume(ConsumableItem consumableItem) {
        adjustCharacterStatisticsFromConsume(consumableItem);
        this.getInventory().remove(consumableItem);
        return String.format("Consumed the %s", consumableItem.getName());
    }
}
