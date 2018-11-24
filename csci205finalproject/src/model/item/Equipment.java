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
 * A durable, reusable item
 *
 * @author ishk001, ks061
 */
public class Equipment extends Item {

    /**
     * Type of equipment
     */
    private EquipmentType type;

    /**
     * Constructor that initializes attributes of equipment
     *
     * @param name name of equipment
     * @param itemStatistics statistics of the item
     * @param type type of equipment
     *
     * @author ishk001, ks061
     */
    public Equipment(String name, ItemStatistics itemStatistics,
                     EquipmentType type) {
        super(name, itemStatistics);
        this.type = type;
    }

    /**
     * Gets the type of equipment
     *
     * @return type of equipment
     *
     * @author ishk001
     */
    public EquipmentType getType() {
        return type;
    }

    /**
     * Sets the type of the equipment
     *
     * @param type - type for equipment type to be set to
     *
     * @author ishk001
     */
    public void setType(EquipmentType type) {
        this.type = type;
    }

}
