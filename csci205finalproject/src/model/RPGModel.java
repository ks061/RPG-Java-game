/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Kartikeya Sharma
 * Date: Oct 19, 2018
 * Time: 7:17:53 PM
 *
 * Project: csci205
 * Package: lab13.tempconvertmvc
 * File: RPGModel
 * Description: This file contains RPGModel, which serves as the
 *              model for the RPG game application.
 * ****************************************
 */
package model;

import java.util.ArrayList;
import map.SimpleRoom;

/**
 * RPGModel serves as the model for the RPG game application.
 *
 * @author ks061
 */
public class RPGModel {

    /**
     * Current room that the player is in
     */
    private SimpleRoom currentRoom;

    /**
     * Row index of the room that the player will start in
     */
    private static final int ROW_INDEX_OF_STARTING_ROOM = 0;

    /**
     * Column index of the room that the player will start in
     */
    private static final int COL_INDEX_OF_STARTING_ROOM = 0;

    /**
     * Number of rooms per row in the map
     */
    private static final int NUM_ROOMS_PER_ROW = 3;

    /**
     * Number of rows of rooms in the map
     */
    private static final int NUM_ROWS = 3;

    /**
     * Constructor that initializes the model of the application
     *
     * @author ks061
     */
    public RPGModel() {
        currentRoom = initGridOfRooms();
    }

    /**
     * Initializes the grid of rooms in the map
     *
     * @return room that the player starts in
     *
     * @author ks061
     */
    private SimpleRoom initGridOfRooms() {
        ArrayList<ArrayList<SimpleRoom>> map = new ArrayList<>();

        String roomName;

        for (int rowIndex = 0; rowIndex < NUM_ROWS; rowIndex++) {
            map.add(new ArrayList<>());
            for (int colIndex = 0; colIndex < NUM_ROOMS_PER_ROW; colIndex++) {
                roomName = "Room " + Integer.toString(
                        rowIndex * NUM_ROOMS_PER_ROW + colIndex);
                map.get(rowIndex).add(new SimpleRoom(roomName));
            }
        }

        connectGridOfRooms(map);

        if (ROW_INDEX_OF_STARTING_ROOM < map.size()) {
            if (COL_INDEX_OF_STARTING_ROOM < map.get(ROW_INDEX_OF_STARTING_ROOM).size()) {
                return map.get(ROW_INDEX_OF_STARTING_ROOM).get(
                        COL_INDEX_OF_STARTING_ROOM);
            }
        }

        return null;
    }

    /**
     * Links a grid of rooms to one another
     *
     * @author ks061
     */
    private void connectGridOfRooms(ArrayList<ArrayList<SimpleRoom>> map) {
        SimpleRoom roomAbove;
        SimpleRoom roomBelow;
        SimpleRoom roomToLeft;
        SimpleRoom roomToRight;

        for (int rowIndex = 0; rowIndex < NUM_ROWS; rowIndex++) {
            for (int colIndex = 0; colIndex < NUM_ROOMS_PER_ROW; colIndex++) {
                try {
                    roomAbove = map.get(rowIndex - 1).get(colIndex);
                    map.get(rowIndex).get(colIndex).setNorth(roomAbove);
                } catch (IndexOutOfBoundsException ex) {
                }
                try {
                    roomBelow = map.get(rowIndex + 1).get(colIndex);
                    map.get(rowIndex).get(colIndex).setSouth(roomBelow);
                } catch (IndexOutOfBoundsException ex) {
                }
                try {
                    roomToLeft = map.get(rowIndex).get(colIndex - 1);
                    map.get(rowIndex).get(colIndex).setWest(roomToLeft);
                } catch (IndexOutOfBoundsException ex) {
                }
                try {
                    roomToRight = map.get(rowIndex).get(colIndex + 1);
                    map.get(rowIndex).get(colIndex).setEast(roomToRight);
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        }
    }

    /**
     * Gets the current room that the player is in
     *
     * @return current room that the player is in
     *
     * @author ks061
     */
    public SimpleRoom getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Sets the room that the player is to be in
     *
     * @param room room that the player is to be in
     *
     * @author ks061
     */
    public void setCurrentRoom(SimpleRoom room) {
        this.currentRoom = room;
    }

}
