/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 15, 2018
  * Time: 9:15:56 AM
  *
  * Project: csci205FinalProject
  * Package: model.character
  * File: RPGCharacterStats
  * Description: This file contains RPGCharacterStats.
  * ****************************************
 */
package model.character;

/**
 * RPGCharacterStats contains the game statistics of the NPC, including
 * maxHealth, attack, and defense.
 *
 * @author ks061
 */
public class RPGCharacterStats {

    private int maxHealth;
    private int attack;
    private int defense;

    private int health;

    /**
     * Explicit constructor that takes in the maximum health, initial attack
     * attribute, and initial defense attribute of the character and sets their
     * health to the maximum health.
     *
     * @param maxHealth maximum health of the character
     * @param attack attack attribute of the character
     * @param defense defense attribute of the character
     *
     * @author ks061
     */
    public RPGCharacterStats(int maxHealth, int attack, int defense) {
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;

        this.health = maxHealth;
    }

    /**
     * Gets the maximum health of the character
     *
     * @return maximum health of the character
     *
     * @author ks061
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the maximum health of the character
     *
     * @param maxHealth maximum health of the character
     *
     * @author ks061
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Gets the attack attribute of the character
     *
     * @return attack attribute of the character
     *
     * @author ks061
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Sets the attack attribute of the character
     *
     * @param attack attribute of the character
     *
     * @author ks061
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Gets the defense attribute of the character
     *
     * @return defense attribute of the character
     *
     * @author ks061
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the defense attribute of the character
     *
     * @param defense attribute of the character
     *
     * @author ks061
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Gets the health attribute of the character
     *
     * @return health attribute of the character
     *
     * @author ks061
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health attribute of the character
     *
     * @param health health attribute of the character
     *
     * @author ks061
     */
    public void setHealth(int health) {
        this.health = health;
    }

}
