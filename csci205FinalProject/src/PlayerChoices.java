/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Dec 4, 2018
  * Time: 5:03:52 PM
  *
  * Project: csci205FinalProject
  * Package:
  * File: PlayerChoices
  * Description: This file contains PlayerChoices.
  * ****************************************
 */

/**
 * The choices for the Player's name at the beginning of the game
 *
 * @author lts010
 */
public enum PlayerChoices {

    LOGAN("Logan"), KARTIKEYA("Kartikeya"), CLAUDIA("Claudia"), JASON("Jason");

    /**
     * Name associated with the enum
     */
    private final String name;

    /**
     * Constructor for the enum
     *
     * @param name the name associated with the enum
     *
     * @author lts010
     */
    PlayerChoices(String name) {
        this.name = name;
    }

    /**
     * Gets the name associated with the enum
     *
     * @return the name associated with the enum
     *
     * @author lts010
     */
    public String getName() {
        return this.name;
    }
}
