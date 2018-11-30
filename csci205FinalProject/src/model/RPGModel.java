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
import model.character.NPC;
import model.character.Player;
import model.data.Story;
import model.map.Room;
import model.map.RoomContent;

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
     * Current room player is in
     */
    private Room currentRoom;
    /**
     * Player playing the game
     */
    private final Player player;
    /**
     * Final NPC boss that player will defeat in order to complete the game
     */
    private NPC finalBoss;

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
     * Prefix of the room background image file
     */
    private static final String ROOM_IMAGE_FILE_PATH_PREFIX = "img/room";
    /**
     * Extension of the room background image file
     */
    private static final String ROOM_IMAGE_FILE_PATH_EXT = ".png";
    /**
     * Name of the NPC boss
     */
    private static final String FINAL_BOSS_NAME = "Angry Dance";

    /**
     * Constructor that initializes the model of the application
     *
     * @author ks061
     */
    public RPGModel() {
        this.player = new Player("Student");
        this.currentRoom = initGridOfRooms();
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
     * Links a grid of rooms to one another and sets the background image to one
     * with the correct doors for the connections.
     *
     * @author ks061, lts010
     */
    private void connectGridOfRooms() {
        Room roomAbove;
        Room roomBelow;
        Room roomToLeft;
        Room roomToRight;
        int roomNumber;

        for (int rowIndex = 0; rowIndex < NUM_ROWS; rowIndex++) {
            for (int colIndex = 0; colIndex < NUM_ROOMS_PER_ROW; colIndex++) {
                roomNumber = rowIndex * NUM_ROOMS_PER_ROW + colIndex;
                this.map.get(rowIndex).get(colIndex).setBackgroundImagePath(
                        ROOM_IMAGE_FILE_PATH_PREFIX + roomNumber + ROOM_IMAGE_FILE_PATH_EXT);
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

        RoomContent roomContent = null;
        for (ArrayList<Room> mapRow : map) {
            for (Room r : mapRow) {
                roomContent = Story.getInstance().getRandomRoomContent();
                r.setName(roomContent.getName());
                r.setNPCViewWrapper(roomContent.getNPCWrapper());
                if (roomContent.getNPCWrapper().getNPC().getName().equals(
                        FINAL_BOSS_NAME)) {
                    this.finalBoss = roomContent.getNPCWrapper().getNPC();
                }
                r.setHiddenItems(roomContent.getItems());
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

    /**
     * Gets the final boss NPC
     *
     * @return final boss NPC
     *
     * @author ks061
     */
    public NPC getFinalBoss() {
        return this.finalBoss;
    }
}
