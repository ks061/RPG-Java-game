/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 3, 2018
  * Time: 3:28:24 PM
  *
  * Project: csci205FinalProject
  * Package: model.character
  * File: NPC
  * Description: This file contains NPC.
  * ****************************************
 */
package model.character;

import model.item.Item;

/**
 * NPC Class creates constructor and methods associated with the NPCs throughout
 * the RPG. It's a child of RPGCharacter.
 *
 * @author Logan Stiles
 * @version 0.1
 */
public class NPC extends RPGCharacter {

    /**
     * Current dialogue for the NPC
     */
    private String currentDialogue;
    /**
     * First line of dialogue
     */
    private String dialogue1;
    /**
     * Second line of dialogue
     */
    private String dialogue2;
    /**
     * Item desired
     */
    private Item desiredItem;
    /**
     * Attribute of NPC describing if it's friendly or hostile
     */
    private boolean isFriendly;
    /**
     * Default inventory size for the character
     */
    public static final int DEFAULT_INVENTORY_SIZE = 1;

    /**
     * Constructor for the NPC which initializes its values
     *
     * @param name - name of NPC
     * @param maxHealth - maximum health NPC can achieve
     * @param attack - attack attribute of NPC
     * @param defense - defense attribute of NPC
     * @param dialogue1 - first dialogue
     * @param dialogue2 - second dialogue
     * @param isFriendly - friendly or hostile attribute
     */
    public NPC(String name, int maxHealth, int attack, int defense,
               String dialogue1, String dialogue2, boolean isFriendly) {
        super(name, maxHealth, attack, defense, NPC.DEFAULT_INVENTORY_SIZE);
        this.dialogue1 = dialogue1;
        this.currentDialogue = dialogue1;
        this.dialogue2 = dialogue2;
        this.isFriendly = isFriendly;
    }

//    /**
//     * Overrides moveTo in the RPGCharacter class.
//     *
//     * @param room - room to move to
//     * @return String describing which NPC moved to what room
//     */
//    @Override
//    public String moveTo(Room room) {
//        this.getLocation().getNpc().remove(this);
//        this.setLocation(room);
//        this.getLocation().getNpc().add(this);
//        return String.format("%s moved to the %s", this.getName(),
//                             this.getLocation().getName());
//    }
    /**
     * Toggles the current dialogue between dialogue 1 and 2
     */
    public void toggleCurrentDialogue() {
        if (this.currentDialogue.contentEquals(dialogue1)) {
            this.currentDialogue = this.dialogue2;
        }
        else {
            this.currentDialogue = this.dialogue1;
        }
    }

    /**
     * Gets the current dialog
     *
     * @return String of currentDialogue
     */
    public String getCurrentDialogue() {
        return currentDialogue;
    }

    /**
     * Sets the current dialogue
     *
     * @param currentDialogue - String representing what currentDialogue should
     * be set to
     */
    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    /**
     * Gets the first dialogue
     *
     * @return String for dialogue1
     */
    public String getDialogue1() {
        return dialogue1;
    }

    /**
     * Sets the first dialogue
     *
     * @param dialogue1 - String to set dialogue1 to
     */
    public void setDialogue1(String dialogue1) {
        this.dialogue1 = dialogue1;
    }

    /**
     * Gets the second dialogue
     *
     * @return String for dialogue2
     */
    public String getDialogue2() {
        return dialogue2;
    }

    /**
     * Sets the second dialogue
     *
     * @param dialogue2 - String to set dialogue2 to
     */
    public void setDialogue2(String dialogue2) {
        this.dialogue2 = dialogue2;
    }

    /**
     * Gets the desired item
     *
     * @return Item representing the desired item
     */
    public Item getDesiredItem() {
        return desiredItem;
    }

    /**
     * Sets the desired item
     *
     * @param desiredItem - Item to set desiredItem to
     */
    public void setDesiredItem(Item desiredItem) {
        this.desiredItem = desiredItem;
    }

    /**
     * Gets the friendly/hostile attribute of the NPC
     *
     * @return boolean representing if the NPC is friendly
     */
    public boolean isIsFriendly() {
        return isFriendly;
    }

    /**
     * Sets the friendly/hostile attribute of the NPC
     *
     * @param isFriendly - true for is friendly and false otherwise
     */
    public void setIsFriendly(boolean isFriendly) {
        this.isFriendly = isFriendly;
    }

}
