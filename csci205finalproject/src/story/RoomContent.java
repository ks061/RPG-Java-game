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
package story;

/**
 * Contains the room name, the NPCs, and the items in each room. Used when
 * initializing map to randomize the content of each room.
 *
 * @author Claudia Shrefler
 * @version 0.1
 */
public class RoomContent {

    private String roomName;
    //private Equipment[] roomItems;
    //private NPC[] npcs;
    private Equipment roomItem;
    private NPC npc;

    /**
     * Constructor that initializes the content of a room
     *
     * @param roomName - name of room
     * @param npc - list of NPCs in a room
     * @param roomItem - list of items in a room
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
     */
    public String getRoomName() {
        return this.roomName;
    }

    /**
     * Gets the NPCs in a room
     *
     * @return List of NPCs
     */
    public NPC[] getNPCs() {
        return this.npcs;
    }

    /**
     * Gets the items in a room
     *
     * @return List of items
     */
    public Equipment[] getRoomItems() {
        return this.roomItems;
    }

}
