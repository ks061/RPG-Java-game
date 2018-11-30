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
 * @author ks061, lts010
 */
public enum ImageKey {
    // Game controls
    INVENTORY, ATTACK, SEARCH, TRADE,
    POW, BAM, WHIFF, CRUNCH,
    UPARROW, DOWNARROW, LEFTARROW, RIGHTARROW,
    // Game entities
    NPC,
    WEAPON1, WEAPON2, WEAPON3,
    SHIELD1, SHIELD2, SHIELD3,
    ARMOR1, ARMOR2, ARMOR3;
}
