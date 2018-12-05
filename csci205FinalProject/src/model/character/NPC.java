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
 * @author lts010, ks061, ishk001
 */
public class NPC extends RPGCharacter {

    /**
     * Lines of dialogue currently in use
     */
    private ArrayList<String> dialogues;
    /**
     * Lines of dialogue for laughs
     */
    private final ArrayList<String> regDialogues;
    /**
     * Lines of dialogue for the progression of the game
     */
    private final ArrayList<String> hintDialogues;
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
    public static final int DEFAULT_INVENTORY_SIZE = 5;

    /**
     * Constructor for the NPC which initializes its values
     *
     * @param name name of NPC
     * @param npcStats statistics of the NPC
     * @param regDialogues series of dialogues
     * @param isFriendly friendly or hostile attribute
     *
     * @author lts010, ks061
     */
    public NPC(String name, RPGCharacterStats npcStats,
               ArrayList<String> regDialogues, ArrayList<String> hintDialogues,
               boolean isFriendly) {
        super(name, npcStats, NPC.DEFAULT_INVENTORY_SIZE);
        this.regDialogues = regDialogues;
        this.hintDialogues = hintDialogues;
        this.dialogues = regDialogues;
        setupDialogues(regDialogues);

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

    /**
     * Gets the current set of dialogues on display
     *
     * @return current set of dialogues
     *
     * @author ishk001
     */
    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    /**
     * Sets the current NPC dialogue to either regular or hint
     *
     * @param dialogues either regular or hint quotes
     *
     * @author ishk001
     */
    public void setDialogues(ArrayList<String> dialogues) {
        this.dialogues = dialogues;
        this.dialogueIterator = this.dialogues.iterator();
    }

    /**
     * Gets the funny quotes
     *
     * @return the funny set of quotes
     *
     * @author ishk001
     */
    public ArrayList<String> getRegDialogues() {
        return regDialogues;
    }

    /**
     * Gets the quotes that progresses through the story
     *
     * @return hint quotes
     *
     * @author ishk001
     */
    public ArrayList<String> getHintDialogues() {
        return hintDialogues;
    }
}
