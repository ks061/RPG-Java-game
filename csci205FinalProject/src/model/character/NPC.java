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

import java.util.ArrayList;
import java.util.Iterator;
import model.item.Item;

/**
 * NPC Class creates constructor and methods associated with the NPCs throughout
 * the RPG.
 *
 * @author lts010, ks061
 */
public class NPC extends RPGCharacter {

    /**
     * Lines of dialogue
     */
    private final ArrayList<String> dialogues;
    /**
     * Iterator for lines of dialogue
     */
    private Iterator<String> dialogueIterator;
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
     * @param name name of NPC
     * @param npcStats statistics of the NPC
     * @param dialogues series of dialogues
     * @param isFriendly friendly or hostile attribute
     *
     * @author lts010, ks061
     */
    public NPC(String name, RPGCharacterStats npcStats,
               ArrayList<String> dialogues, boolean isFriendly) {
        super(name, npcStats, NPC.DEFAULT_INVENTORY_SIZE);

        this.dialogues = dialogues;
        setupDialogues(dialogues);

        this.isFriendly = isFriendly;
    }

    /**
     * Sets up the dialogue iterator
     *
     * @param dialogues dialogues of the NPC
     *
     * @author ks061
     */
    private void setupDialogues(ArrayList<String> dialogues) {
        if (this.dialogues.isEmpty()) {
            this.dialogueIterator = null;
        }
        else {
            dialogueIterator = dialogues.iterator();
        }
    }

    /**
     * Returns the next dialogue that the NPC speaks
     *
     * @return next dialogue that the NPC speaks
     *
     * @author ks061
     */
    public String speak() {
        if (this.dialogueIterator == null) {
            return "";
        }

        if (dialogueIterator.hasNext()) {
            return dialogueIterator.next();
        }

        this.dialogueIterator = this.dialogues.iterator();
        return speak();

    }

    /**
     * Gets the desired item
     *
     * @return item representing the desired item
     *
     * @author lts010
     */
    public Item getDesiredItem() {
        return desiredItem;
    }

    /**
     * Sets the desired item
     *
     * @param desiredItem item to set desiredItem to
     *
     * @author lts010
     */
    public void setDesiredItem(Item desiredItem) {
        this.desiredItem = desiredItem;
    }

    /**
     * Gets the friendly/hostile attribute of the NPC
     *
     * @return boolean representing if the NPC is friendly
     *
     * @author lts010, ks061
     */
    public boolean isFriendly() {
        return isFriendly;
    }

    /**
     * Sets the friendly/hostile attribute of the NPC
     *
     * @param isFriendly true for is friendly and false otherwise
     *
     * @author lts010, ks061
     */
    public void setIsFriendly(boolean isFriendly) {
        this.isFriendly = isFriendly;
    }
}
