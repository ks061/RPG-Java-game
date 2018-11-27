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
 * the RPG. It's a child of RPGCharacter.
 *
 * @author lts010, ks061
 */
public class NPC extends RPGCharacter {

    /**
     * Current dialogue for the NPC
     */
    private String dialogueToSpeak;
    /**
     * Lines of dialogue
     */
    private ArrayList<String> dialogues;
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
     * @param name - name of NPC
     * @param npcStats - statistics of the NPC
     * @param dialogues - series of dialogues
     * @param isFriendly - friendly or hostile attribute
     *
     * @author lts010, ks061
     */
    public NPC(String name, RPGCharacterStats npcStats,
               ArrayList<String> dialogues, boolean isFriendly) {
        super(name, npcStats, NPC.DEFAULT_INVENTORY_SIZE);

        this.dialogues = dialogues;
        if (this.dialogues.isEmpty()) {
            this.dialogueIterator = null;
        }
        else {
            dialogueIterator = dialogues.iterator();
            if (dialogueIterator.hasNext()) {
                this.dialogueToSpeak = dialogueIterator.next();
            }
            else {
                this.dialogueToSpeak = null;
            }
        }

        this.isFriendly = isFriendly;
    }

    /**
     * Returns whether the dialogue iterator is null
     *
     * @return true if dialogue iterator is null; otherwise false
     *
     * @author ks061
     */
    public boolean isDialogueIteratorNull() {
        if (this.dialogueIterator == null) {
            return true;
        }
        return false;
    }

    /**
     * Sets up the next dialogue for the NPC to communicate
     *
     * @author ks061
     */
    public void nextDialogueToSpeak() {
        if (isDialogueIteratorNull()) {
            return;
        }

        if (dialogueIterator.hasNext()) {
            this.dialogueToSpeak = dialogueIterator.next();
        }
        else {
            this.dialogueIterator = this.dialogues.iterator();
            nextDialogueToSpeak();
        }
    }

    /**
     * Gets the current dialog
     *
     * @return String of dialogueToSpeak
     *
     * @author lts010, ks061
     */
    public String getCurrentDialogue() {
        return dialogueToSpeak;
    }

    /**
     * Sets the current dialogue
     *
     * @param currentDialogue - String representing what dialogueToSpeak should
     * be set to
     *
     * @author lts010, ks061
     */
    public void setCurrentDialogue(String currentDialogue) {
        this.dialogueToSpeak = currentDialogue;
    }

    /**
     * Gets the desired item
     *
     * @return Item representing the desired item
     *
     * @author lts010
     */
    public Item getDesiredItem() {
        return desiredItem;
    }

    /**
     * Sets the desired item
     *
     * @param desiredItem - Item to set desiredItem to
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
