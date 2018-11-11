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
 * @version 0.1
 */
public class Equipment extends Item {

    /**
     * Type of equipment
     */
    private EquipmentType type;

    /**
     * Constructor that initializes attributes of equipment
     *
     * @param name - name of equipment
     * @param deltaHealth - delta health variable (how much it will change by)
     * @param deltaAttack - delta attack variable
     * @param deltaDefense - delta defense variable
     * @param deltaInventory - delta inventory variable
     * @param type - type of equipment
     */
    public Equipment(String name, int deltaHealth, int deltaAttack,
                     int deltaDefense, int deltaInventory, EquipmentType type) {
        super(name, deltaHealth, deltaAttack, deltaDefense, deltaInventory);
        this.type = type;
    }

    /**
     * Equips the equipment based on its type
     *
     * @return String representing what was equipped
     */
    public String equip() {
        this.getOwner().setMaxHealth(
                this.getOwner().getMaxHealth() + this.getDeltaHealth());
        this.getOwner().setAttack(
                this.getOwner().getAttack() + this.getDeltaAttack());
        this.getOwner().setDefense(
                this.getOwner().getDefense() + this.getDeltaDefense());
        this.getOwner().setInventorySize(
                this.getOwner().getInventorySize() + this.getDeltaInventory());
        switch (this.type) {
            case WEAPON:
                if (this.getOwner().getWeapon() != null) {
                    return this.swapEquipment(this.getOwner().getWeapon());
                }
                else {
                    this.getOwner().setWeapon(this);
                    this.getOwner().getInventory().remove(this);
                }
                break;
            case ARMOR:
                if (this.getOwner().getArmor() != null) {
                    return this.swapEquipment(this.getOwner().getArmor());
                }
                else {
                    this.getOwner().setArmor(this);
                    this.getOwner().getInventory().remove(this);
                }
                break;
            case SHIELD:
                if (this.getOwner().getShield() != null) {
                    return this.swapEquipment(this.getOwner().getShield());
                }
                else {
                    this.getOwner().setShield(this);
                    this.getOwner().getInventory().remove(this);
                }
                break;

        }
        return String.format("Equipped the %s as a %s", this.getName(),
                             this.type.name());
    }

    /**
     * Unequips the equipment and adds it to inventory
     *
     * @return String representing equipment unequipped
     */
    public String unequip() {
        if (this.getOwner().isInventoryFull()) {
            return String.format(
                    "Cannot unequip the %s because your inventory is full",
                    this.getName());
        }
        if (this.type == EquipmentType.WEAPON) {
            super.getOwner().setWeapon(null);
        }
        else if (this.type == EquipmentType.ARMOR) {
            super.getOwner().setArmor(null);
        }
        else if (this.type == EquipmentType.SHIELD) {
            super.getOwner().setShield(null);
        }
        this.getOwner().getInventory().add(this);
        this.getOwner().setMaxHealth(
                this.getOwner().getMaxHealth() - this.getDeltaHealth());
        this.getOwner().setAttack(
                this.getOwner().getAttack() - this.getDeltaAttack());
        this.getOwner().setDefense(
                this.getOwner().getDefense() - this.getDeltaDefense());
        this.getOwner().setInventorySize(
                this.getOwner().getInventorySize() - this.getDeltaInventory());
        return String.format("Unequipped the %s and added it to your inventory",
                             this.getName());
    }

    /**
     * Swaps the current equipment with something from the inventory
     *
     * @param currentlyEquippedItem - current Equipment on body
     * @return String representing what items were swapped
     */
    public String swapEquipment(Equipment currentlyEquippedItem) {
        currentlyEquippedItem.unequip();
        this.getOwner().getInventory().remove(this);
        switch (this.type) {
            case WEAPON:
                this.getOwner().setWeapon(this);
                break;
            case ARMOR:
                this.getOwner().setArmor(this);
                break;
            case SHIELD:
                this.getOwner().setShield(this);
                break;
        }
        return String.format("Unequipped the %s and equipped the %s",
                             currentlyEquippedItem.getName(), this.getName());
    }

    /**
     * Gets the type of equipment
     *
     * @return type of equipment
     */
    public EquipmentType getType() {
        return type;
    }

    /**
     * Sets the type of the equipment
     *
     * @param type - type for equipment type to be set to
     */
    public void setType(EquipmentType type) {
        this.type = type;
    }

}
