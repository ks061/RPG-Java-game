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

import java.util.ArrayList;
import model.item.Item;
import view.wrapper.NPCImageViewWrapper;

/**
 * Contains the room name, the NPCs, and the items in each room. Used when
 * initializing map to randomize the content of each room.
 *
 * @author cjs051, ks061
 */
public class RoomContent {

    private String name;

    private ArrayList<NPCImageViewWrapper> npcWrappers;

    private ArrayList<Item> items;

    /**
     * Constructor that initializes the content of a room
     *
     * @param name name of room
     * @param npcWrappers list of NPC Wrapper objects containing the NPC and the
     * image view of the NPC
     * @param items list of items in a room
     *
     * @author cjs051, ks061
     */
    public RoomContent(String name, ArrayList<NPCImageViewWrapper> npcWrappers,
                       ArrayList<Item> items) {
        this.name = name;
        this.npcWrappers = npcWrappers;
        this.items = items;
    }

    /**
     * Gets the name of the room
     *
     * @return String for room name
     *
     * @author cjs051
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the list of NPC wrapper objects of NPCs in the room
     *
     * @return list of NPC wrapper objects of NPCs in the room
     *
     * @author cjs051, ks061
     */
    public ArrayList<NPCImageViewWrapper> getNPCWrappers() {
        return this.npcWrappers;
    }

    /**
     * Gets the list of equipment items in a room
     *
     * @return list of equipment items
     *
     * @author cjs051, ks061
     */
    public ArrayList<Item> getItems() {
        return this.items;
    }

}
