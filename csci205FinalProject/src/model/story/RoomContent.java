/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 10, 2018
  * Time: 8:37:47 PM
  *
  * Project: csci205FinalProject
  * Package: story
  * File: RoomContent
  * Description: This file contains RoomContent.
  * ****************************************
 */
package model.story;

import model.character.NPC;
import model.item.Equipment;

/**
 * Contains the room name, the NPCs, and the items in each room. Used when
 * initializing map to randomize the content of each room.
 *
 * @author cjs051
 */
public class RoomContent {

    private String roomName;

    private Equipment roomItem;
    private NPC npc;

    /**
     * Constructor that initializes the content of a room
     *
     * @param roomName - name of room
     * @param npc - list of NPCs in a room
     * @param roomItem - list of items in a room
     *
     * @author cjs051
     */
    public RoomContent(String roomName, NPC npc, Equipment roomItem) {
        this.roomName = roomName;
        this.npc = npc;
        this.roomItem = roomItem;
    }

    /**
     * Gets the name of the room
     *
     * @return String for room name
     *
     * @author cjs051
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * Gets the NPCs in a room
     *
     * @return List of NPCs
     *
     * @author cjs051, ks061
     */
    public NPC getNPC() {
        return this.npc;
    }

    /**
     * Gets the items in a room
     *
     * @return List of items
     *
     * @author cjs051, ks061
     */
    public Equipment getRoomItem() {
        return this.roomItem;
    }

}
