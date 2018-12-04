/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 29, 2018
  * Time: 12:52:18 PM
  *
  * Project: csci205FinalProject
  * Package: view
  * File: ImageKey
  * Description: This file contains ImageKey.
  * ****************************************
 */
package view;

/**
 * ImageKey represents the buttons and action bubbles that appear on the screen.
 * Image types representing game controls are listed in the first group of
 * enumeration elements and game entities in the latter group of enumeration
 * elements.
 *
 * @author ks061, lts010, cjs051
 */
public enum ImageKey {
    // Game controls
    INVENTORY(
            "Backpack: Click to view your inventory\n" + "Drag items here to store in your inventory"), ATTACK(
            "Attacks the current NPC in the room"), SEARCH(
            "Searches deceased NPCs for items"), TRADE(
            "Give NPC items for more desirable items"),
    POW, BAM, WHIFF, CRUNCH,
    UPARROW("Up arrow: Click to move to room above"),
    DOWNARROW("Down arrow: Click to move to room below"),
    LEFTARROW("Left arrow: Click to move to room on left"),
    RIGHTARROW("Right arror: Click to move to room on right"),
    // Game entities
    NPC("Character: Click to talk to NPC"),
    PEN_AND_PAPER("Code using pen and paper: attack +5"), NOTEPAD(
            "Code using Notepad: attack +10"),
    NETBEANS("Code using Netbeans: attack +20"),
    MACHINE_CODE("Learned Assembly language: defense +5"), HTML(
            "Learned HTML: defense +10"), JAVA("Learned Java: defense +20"),
    API("Able to use the API: max health +5"), STACK_OVERFLOW(
            "Able to use Stack Overflow: max health +10"),
    WINKLEVOSS_TWINS(
            "Able to use the Winklevoss Twins: max health +20"),
    BOOGER, GOLD_NUGGET, TIME,
    NACHO_TOTS("Restores 20 health, and gives you +3 attack"),
    MILKSHAKE("Restores 20 health, and gives you +3 defense"),
    RAMEN_NOODLES("Restores 20 health, and gives you +3 inventory size");

    /**
     * String representing tooltip associated with each tool
     */
    private final String tooltip;

    /**
     * Initializes the tooltip for a view component with this image key
     *
     * @param tooltip tool tip
     *
     * @author cjs051, ks061
     */
    private ImageKey(String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * Initializes the tooltip to the name of this enum element
     *
     * @author cjs051, ks061
     */
    private ImageKey() {
        this.tooltip = this.name();
    }

    /**
     * Gets the tooltip
     *
     * @return tooltip
     *
     * @author cjs051, ks061
     */
    public String getTooltip() {
        return this.tooltip;
    }

}
