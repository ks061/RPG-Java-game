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

import model.character.NPC;
import model.character.Player;
import model.item.Item;

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
     * Player in this room; null if no player is in this room
     */
    private Player player;
    /**
     * NPCs in the room
     */
    private NPC npc;
    /**
     * Items hidden in the room
     */
    private Item hiddenItem;

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
        this.player = null;
        this.npc = null;
        this.hiddenItem = null;

        this.north = null;
        this.south = null;
        this.east = null;
        this.west = null;
    }

    /**
     * Gets the name of the room
     *
     * @return name of the room
     *
     * @author ks061
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player currently in the room
     *
     * @return player currently in the room
     *
     * @author ks061
     */
    public Player getPlayer() {
        return player;
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
     * Sets the player currently in this room
     *
     * @param player player currently in this room
     *
     * @author ks061
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Adds an NPC to the room
     *
     * @param npc NPC to be added to the room
     *
     * @author ks061
     */
    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    /**
     * Gets the NPC in the room
     *
     * @return NPC in the room
     *
     * @author ks061
     */
    public NPC getNpc() {
        return npc;
    }

    /**
     * Sets a hidden item in the room
     *
     * @param hiddenItem hidden item set to the room
     *
     * @author ks061
     */
    public void setHiddenItem(Item hiddenItem) {
        this.hiddenItem = hiddenItem;
    }

    /**
     * Gets the item hidden in the room
     *
     * @return item hidden in the room
     *
     * @author ks061
     */
    public Item getHiddenItem() {
        return hiddenItem;
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
     * Sets the name of the room
     *
     * @param name name of the room
     *
     * @author ks061
     */
    public void setName(String name) {
        this.name = name;
    }
}
