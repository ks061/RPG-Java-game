/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 15, 2018
  * Time: 9:45:58 PM
  *
  * Project: csci205FinalProject
  * Package: model.item
  * File: ItemStatistics
  * Description: This file contains ItemStatistics.
  * ****************************************
 */
package model.item;

/**
 * ItemStatistics encapsulates the statistics of an item, including the change
 * in the health player statistic, attack player statistic, defense player
 * statistic, size of the inventory of the player using the item
 *
 * @author ks061, lts010
 */
public class ItemStatistics {

    /**
     * Change in the health player statistic as a result of obtaining the item
     */
    private final int deltaHealth;
    /**
     * Change in the attack player statistic as a result of obtaining the item
     */
    private final int deltaAttack;
    /**
     * Change in the defense player statistic as a result of obtaining the item
     */
    private final int deltaDefense;
    /**
     * Change in the size of the player inventory as a result of obtaining the
     * item
     */
    private final int deltaInventory;

    /**
     * Explicit constructor for ItemStatistics
     *
     * @param deltaHealth change in the player health statistic as a result of
     * obtaining the item
     * @param deltaAttack change in the attack player statistic as a result of
     * obtaining the item
     * @param deltaDefense change in the player defense statistic as a result of
     * obtaining the item
     * @param deltaInventory change in the size of the player inventory as a
     * result of obtaining the item
     *
     * @author ks061, lts010
     */
    public ItemStatistics(int deltaHealth, int deltaAttack, int deltaDefense,
                          int deltaInventory) {
        this.deltaAttack = deltaAttack;
        this.deltaHealth = deltaHealth;
        this.deltaDefense = deltaDefense;
        this.deltaInventory = deltaInventory;
    }

    /**
     * Gets the change in the player health statistic as a result of obtaining
     * the item
     *
     * @return change in the player health statistic as a result of obtaining
     * the item
     *
     * @author lts010, ks061
     */
    public int getDeltaHealth() {
        return deltaHealth;
    }

    /**
     * Gets the change in the player attack statistic as a result of obtaining
     * the item
     *
     * @return change in the player attack statistic as a result of obtaining
     * the item
     *
     * @author lts010, ks061
     */
    public int getDeltaAttack() {
        return deltaAttack;
    }

    /**
     * Gets the change in the player defense statistic as a result of obtaining
     * the item
     *
     * @return change in the player defense statistic as a result of obtaining
     * the item
     *
     * @author lts010, ks061
     */
    public int getDeltaDefense() {
        return deltaDefense;
    }

    /**
     * Gets the change in the size of the player inventory as a result of
     * obtaining the item
     *
     * @return change in the size of the player inventory as a result of
     * obtaining the item
     *
     * @author lts010, ks061
     */
    public int getDeltaInventory() {
        return deltaInventory;
    }
}
