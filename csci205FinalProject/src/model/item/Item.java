/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 3, 2018
  * Time: 2:10:09 PM
  *
  * Project: csci205FinalProject
  * Package: model.item
  * File: Item
  * Description: This file contains Item.
  * ****************************************
 */
package model.item;

import model.character.RPGCharacter;

/**
 * An abstract class that can be extended to either a consumable or an equipable
 * item
 *
 * @author Jason Kang
 * @version 0.1
 */
public abstract class Item {

    /**
     * Name of item
     */
    private String name;
    /**
     * Delta health variable associated with item
     */
    private int deltaHealth;
    /**
     * Delta attack variable associated with item
     */
    private int deltaAttack;
    /**
     * Delta defense variable associated with item
     */
    private int deltaDefense;
    /**
     * Delta inventory variable associated with item
     */
    private int deltaInventory;
    /**
     * Player representing item's owner
     */
    private RPGCharacter owner;

    /**
     * Constructor that instantiates all attributes of an item
     *
     * @param name - name of item
     * @param deltaHealth - delta health variable
     * @param deltaAttack - delta attack variable
     * @param deltaDefense - delta defense variable
     * @param deltaInventory - delta inventory variable
     */
    public Item(String name, int deltaHealth, int deltaAttack, int deltaDefense,
                int deltaInventory) {
        this.name = name;
        this.deltaHealth = deltaHealth;
        this.deltaAttack = deltaAttack;
        this.deltaDefense = deltaDefense;
        this.deltaInventory = deltaInventory;
    }

    /**
     * Gets the name of item
     *
     * @return String of item name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the delta health variable
     *
     * @return integer of delta health
     */
    public int getDeltaHealth() {
        return deltaHealth;
    }

    /**
     * Gets the delta attack variable
     *
     * @return integer of delta attack
     */
    public int getDeltaAttack() {
        return deltaAttack;
    }

    /**
     * Gets the delta defense variable
     *
     * @return integer of delta defense
     */
    public int getDeltaDefense() {
        return deltaDefense;
    }

    /**
     * Gets the delta inventory variable
     *
     * @return integer of delta inventory
     */
    public int getDeltaInventory() {
        return deltaInventory;
    }

    /**
     * This is so that we can establish a link between the item and the owner of
     * the item. We'll use the delta values in this class to update the stats of
     * the character.
     *
     * @return the owner
     *
     * @author Jason Kang
     */
    public RPGCharacter getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the item
     *
     * @param owner - Player to own the item
     */
    public void setOwner(RPGCharacter owner) {
        this.owner = owner;
    }

    /**
     * Sets the name of the item
     *
     * @param name - String for item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the delta health variable associated with item
     *
     * @param deltaHealth - integer for delta health to be set to
     */
    public void setDeltaHealth(int deltaHealth) {
        this.deltaHealth = deltaHealth;
    }

    /**
     * Sets the delta attack variable
     *
     * @param deltaAttack - integer for delta attack to be set to
     */
    public void setDeltaAttack(int deltaAttack) {
        this.deltaAttack = deltaAttack;
    }

    /**
     * Sets the delta defense variable
     *
     * @param deltaDefense - integer for delta defense to be set to
     */
    public void setDeltaDefense(int deltaDefense) {
        this.deltaDefense = deltaDefense;
    }

    /**
     * Sets the delta inventory variable
     *
     * @param deltaInventory - integer for delta inventory to be set to
     */
    public void setDeltaInventory(int deltaInventory) {
        this.deltaInventory = deltaInventory;
    }

}
