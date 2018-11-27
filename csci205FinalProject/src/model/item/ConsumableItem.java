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

import view.RPGView;

/**
 * An item that is for one-time-use only. Child of Item class.
 *
 * @author ishk001
 */
public class ConsumableItem extends Item {

    /**
     * Constructor for ConsumableItem that initiates all attributes of a
     * consumable item
     *
     * @param name name of item owner
     * @param itemStatistics statistics of the item
     * @param imageViewKey Key for RPGView EnumMap to get the imageView of equipment
     *
     * @author ks061
     */
    public ConsumableItem(String name, ItemStatistics itemStatistics, RPGView.ImageType imageViewKey) {
        super(name, itemStatistics, imageViewKey);
    }

}
