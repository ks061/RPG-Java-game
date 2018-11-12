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
  * File: SimpleRoom
  * Description: This file contains SimpleRoom, which is a simplified
  *              version of a room in the map.
  * ****************************************
 */
package map;

/**
 * SimpleRoom is a simplified version of room in the map.
 *
 * @author ks061
 */
public class SimpleRoom {

    /**
     * Name of the room
     */
    private String name;
    /**
     * SimpleRoom above this room
     */
    private SimpleRoom north;
    /**
     * SimpleRoom below this room
     */
    private SimpleRoom south;
    /**
     * SimpleRoom to the right of this room
     */
    private SimpleRoom east;
    /**
     * SimpleRoom to the left of this room
     */
    private SimpleRoom west;

    /**
     * Constructor that assigns a name to the room, initializes empty lists for
     * NPCs and hidden items, and sets the player and adjacent room pointers to
     * null.
     *
     * @param name name of the room
     *
     * @author ks061
     */
    public SimpleRoom(String name) {
        this.name = name;

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
     * Gets the room above this room
     *
     * @return room above this room
     *
     * @author ks061
     */
    public SimpleRoom getNorth() {
        return north;
    }

    /**
     * Gets the room below this room
     *
     * @return room below this room
     *
     * @author ks061
     */
    public SimpleRoom getSouth() {
        return south;
    }

    /**
     * Gets the room to the right of this room
     *
     * @return room to the right of this room
     *
     * @author ks061
     */
    public SimpleRoom getEast() {
        return east;
    }

    /**
     * Gets the room to the left of this room
     *
     * @return room to the left of this room
     *
     * @author ks061
     */
    public SimpleRoom getWest() {
        return west;
    }

    /**
     * Sets the room to be above this room
     *
     * @param north room to be above this room
     *
     * @author ks061
     */
    public void setNorth(SimpleRoom north) {
        this.north = north;
    }

    /**
     * Sets the room to be below this room
     *
     * @param south room to be below this room
     *
     * @author ks061
     */
    public void setSouth(SimpleRoom south) {
        this.south = south;
    }

    /**
     * Sets the room to be to the right of this room
     *
     * @param east room to be to the right of this room
     *
     * @author ks061
     */
    public void setEast(SimpleRoom east) {
        this.east = east;
    }

    /**
     * Sets the room to be to the left of this room
     *
     * @param west room to be to the left of this room
     *
     * @author ks061
     */
    public void setWest(SimpleRoom west) {
        this.west = west;
    }

}
