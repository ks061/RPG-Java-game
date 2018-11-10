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
 * An item that is for one-time-use only
 *
 * @author jkang
 */
public class ConsumableItem extends Item {

    public ConsumableItem(String name, int deltaHealth, int deltaAttack,
                          int deltaDefense, int deltaInventory) {
        super(name, deltaHealth, deltaAttack, deltaDefense, deltaInventory);
    }

    public String consume() {
        //For HEALTH potions
        int curHealth = super.getOwner().getHealth();
        if (curHealth + super.getDeltaHealth() > super.getOwner().getMaxHealth()) {
            //sets the health of the player to max health if health + potion turns
            //out to fill up the health bar of the player
            super.getOwner().setHealth(super.getOwner().getMaxHealth());
        }
        else {
            super.getOwner().setHealth(curHealth + super.getDeltaHealth());
        }

        //For ATTACK potions
        super.getOwner().setAttack(
                super.getOwner().getAttack() + super.getDeltaAttack());

        //For DEFENSE potions
        super.getOwner().setDefense(
                super.getOwner().getDefense() + super.getDeltaDefense());

        //For items that permanantely increase inventory size
        super.getOwner().setInventorySize(
                super.getOwner().getInventorySize() + super.getDeltaInventory());
        this.getOwner().getInventory().remove(this);
        return String.format("Consumed the %s", this.getName());
    }

}
