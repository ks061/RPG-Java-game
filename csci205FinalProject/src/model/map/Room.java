/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 3, 2018
  * Time: 2:15:27 PM
  *
  * Project: csci205FinalProject
  * Package: model.map
  * File: Room
  * Description: This file contains Room, which is a room in the map.
  * ****************************************
 */
package model.map;

import java.util.ArrayList;
import model.item.Item;
import view.wrapper.NPCImageViewWrapper;

/**
 * Room is a room in the map.
 *
 * @author ks061
 */
public class Room {

    /**
     * Name of the room
     */
    private String name;
    /**
     * NPCs in the room
     */
    private ArrayList<NPCImageViewWrapper> npcWrappers;
    /**
     * Items hidden in the room
     */
    private ArrayList<Item> hiddenItems;

    /**
     * Room above this room
     */
    private Room north;
    /**
     * Room below this room
     */
    private Room south;
    /**
     * Room to the right of this room
     */
    private Room east;
    /**
     * Room to the left of this room
     */
    private Room west;
    /**
     * Background image path of this room
     */
    private String backgroundImagePath;

    /**
     * Constructor that assigns a name to the room, initializes empty lists for
     * NPCs and hidden items, and sets the player and adjacent room pointers to
     * null.
     *
     * @param name name of the room
     *
     * @author ks061
     */
    public Room(String name) {
        this.name = name;
        this.npcWrappers = null;
        this.hiddenItems = null;

        this.north = null;
        this.south = null;
        this.east = null;
        this.west = null;
    }

    /**
     * Adds a list of NPC wrapper objects containing the NPCs to the room
     *
     * @param npcWrappers list of NPC wrapper objects containing the NPCs to be
     * added to the room
     *
     * @author ks061
     */
    public void setNPCViewWrappers(ArrayList<NPCImageViewWrapper> npcWrappers) {
        this.npcWrappers = npcWrappers;
    }

    /**
     * Gets a list of NPC wrapper objects containing the NPCs in the room
     *
     * @return list of NPC wrapper objects containing the NPCs in the room
     *
     * @author ks061
     */
    public ArrayList<NPCImageViewWrapper> getNPCViewWrappers() {
        return npcWrappers;
    }

    /**
     * Sets the list of hidden items in the room
     *
     * @param hiddenItems list of hidden items to be added to the room
     *
     * @author ks061
     */
    public void setHiddenItems(ArrayList<Item> hiddenItems) {
        this.hiddenItems = hiddenItems;
    }

    /**
     * Gets the list of hidden items in the room
     *
     * @return list of hidden items in the room
     *
     * @author ks061
     */
    public ArrayList<Item> getHiddenItems() {
        return this.hiddenItems;
    }

    /**
     * Adds a hidden item to the room
     *
     * @param hiddenItem hidden item to be added to the room
     *
     * @return true if inputted element removed as a result of calling the
     * method
     *
     * @see
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#add-E-">Java
     * 8 API Collections</a>
     *
     * @author ks061
     */
    public boolean addHiddenItem(Item hiddenItem) {
        return this.hiddenItems.add(hiddenItem);
    }

    /**
     * Removes a hidden item from the room
     *
     * @param hiddenItem hidden item to be removed from the room
     *
     * @return true if collection changes from calling this method
     *
     * @see
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#remove-E-">Java
     * 8 API Collections</a>
     *
     * @author ks061
     */
    public boolean removeHiddenItem(Item hiddenItem) {
        return this.hiddenItems.remove(hiddenItem);
    }

    /**
     * Sets the name of the room
     *
     * @param name name of the room
     *
     * @author ks061
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the room
     *
     * @return name of the room
     *
     * @author ks061
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the room above this room
     *
     * @return room above this room
     *
     * @author ks061
     */
    public Room getNorth() {
        return north;
    }

    /**
     * Gets the room below this room
     *
     * @return room below this room
     *
     * @author ks061
     */
    public Room getSouth() {
        return south;
    }

    /**
     * Gets the room to the right of this room
     *
     * @return room to the right of this room
     *
     * @author ks061
     */
    public Room getEast() {
        return east;
    }

    /**
     * Gets the room to the left of this room
     *
     * @return room to the left of this room
     *
     * @author ks061
     */
    public Room getWest() {
        return west;
    }

    /**
     * Sets the room to be above this room
     *
     * @param north room to be above this room
     *
     * @author ks061
     */
    public void setNorth(Room north) {
        this.north = north;
    }

    /**
     * Sets the room to be below this room
     *
     * @param south room to be below this room
     *
     * @author ks061
     */
    public void setSouth(Room south) {
        this.south = south;
    }

    /**
     * Sets the room to be to the right of this room
     *
     * @param east room to be to the right of this room
     *
     * @author ks061
     */
    public void setEast(Room east) {
        this.east = east;
    }

    /**
     * Sets the room to be to the left of this room
     *
     * @param west room to be to the left of this room
     *
     * @author ks061
     */
    public void setWest(Room west) {
        this.west = west;
    }

    /**
     * Gets the path for the Room Background Image
     *
     * @author lts010
     */
    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    /**
     * Sets the path for the Room Background Image
     *
     * @param backgroundImagePath is the path name for the background image
     * file.
     *
     * @author lts010, ks061
     */
    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

}
