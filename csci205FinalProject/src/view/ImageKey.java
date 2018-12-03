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
     //game items that don't have registered handlers at the start of the
     //game. This includes items equipped to any NPC.  Items in the inventory
     // of an NPC should be later 
    BODY("Looking good"), POW, BAM, WHIFF, CRUNCH,
    
    // Next are game controls, these register for various handlers at the
    // beginnig of the game.  RIGHTARROW must remain last
    FACE("Pie Hole: Put food here when health is getting low"),
    BLANK_WEAPON("Here's where you put the weapon that you want to use"),
    BLANK_SHIELD("Here's where you put the shield that you want to use"),
    BLANK_ARMOR("A great place for your best armor"),
    NPC("Character: Click to talk to NPC"),
    INVENTORY("Backpack: Click to view your inventory\n" + 
            "Drag items here to store in your inventory"),
    ATTACK("Attacks the current NPC in the room"),
    SEARCH("Searches deceased NPCs for items"),
    TRADE("Give NPC items for more desirable items"),
    
    UPARROW("Up arrow: Click to move to room above"),
    DOWNARROW("Down arrow: Click to move to room below"),
    LEFTARROW("Left arrow: Click to move to room on left"),
    RIGHTARROW("Right arror: Click to move to room on right"),
   //The next items are all consumables, RAMEN must remain last
    //From here down are items that can be dragged at the start of the game.
    NACHO_TOTS("Nacho Tots: the perfect midnight snake: health +7"),
    OREO_MILKSHAKE("Oreo Milkshake: A tasty dessert: health +5"),
    RAMEN("Ramen: A legendary student super food: health +10"),
    
    //Next is all eqipment that isn't equiped to an NPC  These items are 
    //valid targets of drags if they are in the player pane
    PEN_AND_PAPER("Code using pen and paper: attack +10"),
    NOTEPAD("Code using Notepad: attack +20"),
    NETBEANS("Code using Netbeans: attack +30"),
    MACHINE_CODE("Learned Assembly language: health +5"),
    HTML("Learned HTML: health +7"),
    JAVA("Learned Java: health +10"),
    API("Able to use the API: defense +10"),
    STACK_OVERFLOW("Able to use Stack Overflow: defense +20"),
    WINKLEVOSS_TWINS( "Able to use the Winklevoss Twins: defense +30");

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
