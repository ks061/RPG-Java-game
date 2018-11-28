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

import view.RPGView;

/**
 * A durable, reusable item
 *
 * @author ishk001, ks061
 */
public class Equipment extends Item {

    /**
     * Type of equipment
     */
    private ItemType type;

    /**
     * Constructor that initializes attributes of equipment
     *
     * @param name name of equipment
     * @param itemStatistics statistics of the item
     * @param type type of equipment
     * @param imageViewKey Key for RPGView EnumMap to get the imageView of
     * equipment
     *
     * @author ishk001, ks061, lts010
     *
     */
    public Equipment(String name, ItemStatistics itemStatistics,
                     ItemType type, RPGView.ImageType imageViewKey) {
        super(name, itemStatistics, imageViewKey);
        this.type = type;

    }

    /**
     * Gets the type of equipment
     *
     * @return type of equipment
     *
     * @author ishk001
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Sets the type of the equipment
     *
     * @param type - type for equipment type to be set to
     *
     * @author ishk001
     */
    public void setType(ItemType type) {
        this.type = type;
    }

}
