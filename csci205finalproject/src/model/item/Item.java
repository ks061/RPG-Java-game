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
 */
public abstract class Item {

    private String name;
    private int deltaHealth;
    private int deltaAttack;
    private int deltaDefense;
    private int deltaInventory;
    private RPGCharacter owner;

    public Item(String name, int deltaHealth, int deltaAttack, int deltaDefense,
                int deltaInventory) {
        this.name = name;
        this.deltaHealth = deltaHealth;
        this.deltaAttack = deltaAttack;
        this.deltaDefense = deltaDefense;
        this.deltaInventory = deltaInventory;
    }

    public String getName() {
        return name;
    }

    public int getDeltaHealth() {
        return deltaHealth;
    }

    public int getDeltaAttack() {
        return deltaAttack;
    }

    public int getDeltaDefense() {
        return deltaDefense;
    }

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

    public void setOwner(RPGCharacter owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeltaHealth(int deltaHealth) {
        this.deltaHealth = deltaHealth;
    }

    public void setDeltaAttack(int deltaAttack) {
        this.deltaAttack = deltaAttack;
    }

    public void setDeltaDefense(int deltaDefense) {
        this.deltaDefense = deltaDefense;
    }

    public void setDeltaInventory(int deltaInventory) {
        this.deltaInventory = deltaInventory;
    }

}
