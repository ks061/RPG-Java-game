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

/**
 * An abstract class that can be extended to either a consumable or an equipable
 * item
 *
 * @author ishk001, ks061
 */
public abstract class Item {

    /**
     * Name of item
     */
    private String name;
    /**
     * Statistics of the item
     */
    private ItemStatistics itemStatistics;

    /**
     * Constructor that instantiates all attributes of an item
     *
     * @param name name of item
     * @param itemStatistics statistics of the item
     *
     * @author ishk001, ks061
     */
    public Item(String name, ItemStatistics itemStatistics) {
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

}
