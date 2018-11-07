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

/**
 *
 * @author lts010
 */
public class NPC extends RPGCharacter {

    private String currentDialogue;
    private String dialogue1;
    private String dialogue2;
    private Item desiredItem;
    private boolean isFriendly;
    public static final int DEFAULT_INVENTORY_SIZE = 1;

    public NPC(String name, int maxHealth, int attack, int defense,
               String dialogue1, String dialogue2, boolean isFriendly) {
        super(name, maxHealth, attack, defense, NPC.DEFAULT_INVENTORY_SIZE);
        this.dialogue1 = dialogue1;
        this.currentDialogue = dialogue1;
        this.dialogue2 = dialogue2;
        this.isFriendly = isFriendly;
    }

    @Override
    public String moveTo(Room room) {
        this.getLocation().getNpcs().remove(this);
        this.setLocation(room);
        this.getLocation().getNpcs().add(this);
        return String.format("%s moved to the %s", this.getName(),
                             this.getLocation().getName());
    }

    public void toggleCurrentDialogue() {
        if (this.currentDialogue.contentEquals(dialogue1)) {
            this.currentDialogue = this.dialogue2;
        }
        else {
            this.currentDialogue = this.dialogue1;
        }
    }

    public String getCurrentDialogue() {
        return currentDialogue;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    public String getDialogue1() {
        return dialogue1;
    }

    public void setDialogue1(String dialogue1) {
        this.dialogue1 = dialogue1;
    }

    public String getDialogue2() {
        return dialogue2;
    }

    public void setDialogue2(String dialogue2) {
        this.dialogue2 = dialogue2;
    }

    public Item getDesiredItem() {
        return desiredItem;
    }

    public void setDesiredItem(Item desiredItem) {
        this.desiredItem = desiredItem;
    }

    public boolean isIsFriendly() {
        return isFriendly;
    }

    public void setIsFriendly(boolean isFriendly) {
        this.isFriendly = isFriendly;
    }

}
