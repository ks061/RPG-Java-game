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
import model.character.Player;
import model.map.Room;
import model.story.RoomContent;
import model.story.Story;

/**
 * RPGModel serves as the model for the RPG game application.
 *
 * @author ks061
 */
public class RPGModel {

    /**
     * Map of rooms
     */
    private ArrayList<ArrayList<Room>> map;
    /**
     * Current room that the player is in
     */
    private Room currentRoom;
    /**
     * Selected player
     */
    private Player player;

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
        this.currentRoom = initGridOfRooms();

        this.player = new Player("Student");
    }

    /**
     * Initializes the grid of rooms in the map
     *
     * @return room that the player starts in
     *
     * @author ks061
     */
    private Room initGridOfRooms() {
        this.map = new ArrayList<>();

        String roomName;

        for (int rowIndex = 0; rowIndex < NUM_ROWS; rowIndex++) {
            this.map.add(new ArrayList<>());
            for (int colIndex = 0; colIndex < NUM_ROOMS_PER_ROW; colIndex++) {
                roomName = "Room " + Integer.toString(
                        rowIndex * NUM_ROOMS_PER_ROW + colIndex);
                this.map.get(rowIndex).add(new Room(roomName));
            }
        }

        connectGridOfRooms();

        initializeMapContent();

        if (ROW_INDEX_OF_STARTING_ROOM < this.map.size()) {
            if (COL_INDEX_OF_STARTING_ROOM < this.map.get(
                    ROW_INDEX_OF_STARTING_ROOM).size()) {
                return this.map.get(ROW_INDEX_OF_STARTING_ROOM).get(
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
    private void connectGridOfRooms() {
        Room roomAbove;
        Room roomBelow;
        Room roomToLeft;
        Room roomToRight;

        for (int rowIndex = 0; rowIndex < NUM_ROWS; rowIndex++) {
            for (int colIndex = 0; colIndex < NUM_ROOMS_PER_ROW; colIndex++) {
                try {
                    roomAbove = this.map.get(rowIndex - 1).get(colIndex);
                    this.map.get(rowIndex).get(colIndex).setNorth(roomAbove);
                } catch (IndexOutOfBoundsException ex) {
                }
                try {
                    roomBelow = this.map.get(rowIndex + 1).get(colIndex);
                    this.map.get(rowIndex).get(colIndex).setSouth(roomBelow);
                } catch (IndexOutOfBoundsException ex) {
                }
                try {
                    roomToLeft = this.map.get(rowIndex).get(colIndex - 1);
                    this.map.get(rowIndex).get(colIndex).setWest(roomToLeft);
                } catch (IndexOutOfBoundsException ex) {
                }
                try {
                    roomToRight = this.map.get(rowIndex).get(colIndex + 1);
                    this.map.get(rowIndex).get(colIndex).setEast(roomToRight);
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        }
    }

    /**
     * Initializes each room in the map with the content coded into the story
     * within the model
     *
     * @author ks061
     */
    private void initializeMapContent() {
        Story.initStory();

        RoomContent roomContent = null;
        for (ArrayList<Room> mapRow : map) {
            for (Room r : mapRow) {
                roomContent = Story.getRandomRoomContent();
                r.setName(roomContent.getRoomName());
                r.setNpc(roomContent.getNPC());
                r.setHiddenItem(roomContent.getRoomItem());
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
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Sets the room that the player is to be in
     *
     * @param room room that the player is to be in
     *
     * @author ks061
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Gets the selected player
     *
     * @return selected player
     *
     * @author ks061
     */
    public Player getPlayer() {
        return player;
    }

}
