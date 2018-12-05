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

import view.ImageKey;

/**
 * An abstract class that can be extended to either a consumable or an equipable
 * item
 *
 * @author ishk001, ks061
 */
public class Item {

    /**
     * Name of item
     */
    private final String name;
    /**
     * Statistics of the item
     */
    private final ItemStatistics itemStatistics;
    /**
     * Key for locating the ImageView in RPGVIEW
     */
    private final ImageKey imageViewKey;

    /**
     * Constructor that instantiates all attributes of an item
     *
     * @param name name of item
     * @param itemStatistics statistics of the item
     * @param imageViewKey key for RPGView EnumMap to get the imageView of
     * equipment
     *
     * @author ishk001, ks061, lts010
     */
    public Item(String name, ItemStatistics itemStatistics,
                ImageKey imageViewKey) {
        this.imageViewKey = imageViewKey;
        this.name = name;
        this.itemStatistics = itemStatistics;
    }

    /**
     * Gets the name of the item
     *
     * @return the name of the item
     *
     * @author lts010
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the statistics of the item
     *
     * @return the statistics of the item
     *
     * @author lts010
     */
    public ItemStatistics getItemStatistics() {
        return itemStatistics;
    }

    /**
     * Gets the key to get the ImageView
     *
     * @return Key to find imageView store in RPGView
     *
     * @author lts010, ks061
     */
    public ImageKey getImageViewKey() {
        return imageViewKey;
    }

}
