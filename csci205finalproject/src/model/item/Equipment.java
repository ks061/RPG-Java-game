/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 3, 2018
  * Time: 2:41:44 PM
  *
  * Project: csci205FinalProject
  * Package: model.item
  * File: Equipment
  * Description: This file contains Equipment.
  * ****************************************
 */
package model.item;

/**
 * An item that can be used multiple times
 *
 * @author Jason Kang
 */
public class Equipment extends Item {

    private EquipmentType type;

    public Equipment(String name, int deltaHealth, int deltaAttack,
                     int deltaDefense, int deltaInventory, EquipmentType type) {
        super(name, deltaHealth, deltaAttack, deltaDefense, deltaInventory);
        this.type = type;
    }

    public void equip() {
        if (this.type == EquipmentType.WEAPON) {
            super.getOwner().setWeapon(this);
        }
        else if (this.type == EquipmentType.ARMOR) {
            super.getOwner().setArmor(this);
        }
        else if (this.type == EquipmentType.SHIELD) {
            super.getOwner().setShield(this);
        }
    }

    //Unequip method may be necessary later on
    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

}
