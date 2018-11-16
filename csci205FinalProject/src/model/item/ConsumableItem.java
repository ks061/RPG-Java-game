/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 3, 2018
  * Time: 2:21:02 PM
  *
  * Project: csci205FinalProject
  * Package: model.item
  * File: Consumable
  * Description: This file contains Consumable.
  * ****************************************
 */
package model.item;

/**
 * An item that is for one-time-use only. Child of Item class.
 *
 * @author Jason Kang
 * @version 0.1
 */
public class ConsumableItem extends Item {

    /**
     * Constructor for ConsumableItem that initiates all attributes of a
     * consumable item
     *
     * @param name - name of item owner
     * @param deltaHealth - delta health of owner
     * @param deltaAttack - delta attack of owner
     * @param deltaDefense - delta defense of owner
     * @param deltaInventory - delta inventory of owner
     */
    public ConsumableItem(String name, int deltaHealth, int deltaAttack,
                          int deltaDefense, int deltaInventory) {
        super(name, deltaHealth, deltaAttack, deltaDefense, deltaInventory);
    }

    /**
     * Consumes the items and correspondingly change the health of item owner
     *
     * @return String representing what was consumed
     */
    public String consume() {
        //For HEALTH potions
        int curHealth = super.getOwner().getCharacterStats().getHealth();
        if (curHealth + super.getDeltaHealth() > super.getOwner().getCharacterStats().getMaxHealth()) {
            //sets the health of the player to max health if health + potion turns
            //out to fill up the health bar of the player
            super.getOwner().getCharacterStats().setHealth(
                    super.getOwner().getCharacterStats().getMaxHealth());
        }
        else {
            super.getOwner().getCharacterStats().setHealth(
                    curHealth + super.getDeltaHealth());
        }

        //For ATTACK potions
        super.getOwner().getCharacterStats().setAttack(
                super.getOwner().getCharacterStats().getAttack() + super.getDeltaAttack());

        //For DEFENSE potions
        super.getOwner().getCharacterStats().setDefense(
                super.getOwner().getCharacterStats().getDefense() + super.getDeltaDefense());

        //For items that permanantely increase inventory size
        super.getOwner().setInventorySize(
                super.getOwner().getInventorySize() + super.getDeltaInventory());
        this.getOwner().getInventory().remove(this);
        return String.format("Consumed the %s", this.getName());
    }

}
