/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Kartikeya Sharma
 * Date: Oct 19, 2018
 * Time: 7:17:53 PM
 *
 * Project: csci205
 * Package: model
 * File: RPGModel
 * Description: This file contains RPGModel, which serves as the
 *              model for the RPG game application.
 * ****************************************
 */
package model;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.character.NPC;
import model.character.Player;
import model.data.Story;
import model.item.Item;
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
     * Constructor that initializes the model of the application
     *
     * @author ks061
     */
    public RPGModel() {
        this.player = new Player("Student");
        this.currentRoom = initGridOfRooms();
        this.finalBoss = Story.getInstance().getFinalBoss().getNPC();
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
                r.setHiddenItems(roomContent.getItems());
            }
        }
    }

    public void updateGame() {
        if (playerHasItem("Netbeans")) {
            JOptionPane.showMessageDialog(null,
                                          "You forgot to refactor and Dr. Dance is now very A N G R Y !\nHe's gonna fail you unless you do something!",
                                          "Oh no!", JOptionPane.PLAIN_MESSAGE);
            Room bana340 = getRoom("Bana 340");
            bana340.setNPCViewWrapper(Story.getInstance().getFinalBoss());
        }
        else if (playerHasItem("Java")) {
            JOptionPane.showMessageDialog(null,
                                          "Beck concussed himself and couldn't grade the lab in time.\nHe's furious and you have to calm him down!",
                                          "Oh no!", JOptionPane.PLAIN_MESSAGE);
            NPC drQueen = getNPC("Dr. Queen");
            drQueen.setDialogues(drQueen.getRegDialogues());
        }
        else if (!getNPC("Dill").isAlive()) {
            NPC drQueen = getNPC("Dr. Queen");
            drQueen.setDialogues(drQueen.getHintDialogues());
        }
        else if (!getNPC("Robo-Dustin").isAlive()) {
            JOptionPane.showMessageDialog(null,
                                          "Evil Scientist Dill hacked Dustin's AI machine to create Robo-Dustin.\nYou need to stop him!",
                                          "Oh no!", JOptionPane.PLAIN_MESSAGE);
        }
        else if (playerHasItem("Stack Overflow")) {
            NPC martin = getNPC("Martin");
            martin.setDialogues(martin.getRegDialogues());
            JOptionPane.showMessageDialog(null,
                                          "D...Dustin? No... That's Robo-Dustin!\nHe's on a rampage! Someone has to stop him!",
                                          "Oh no!", JOptionPane.PLAIN_MESSAGE);
        }
        else if (playerHasItem("HTML")) {
            NPC izi = getNPC("Izi");
            NPC dustin = getNPC("Dustin");
            NPC martin = getNPC("Martin");
            izi.setDialogues(izi.getRegDialogues());
            dustin.setDialogues(dustin.getRegDialogues());
            martin.setDialogues(martin.getHintDialogues());
        }
        else if (playerHasItem("Notepad")) {
            NPC muz = getNPC("Muz");
            NPC drDance = getNPC("Dr. Dance");
            NPC izi = getNPC("Izi");
            NPC dustin = getNPC("Dustin");
            muz.setDialogues(muz.getRegDialogues());
            drDance.setDialogues(drDance.getRegDialogues());
            izi.setDialogues(izi.getHintDialogues());
            dustin.setDialogues((dustin.getHintDialogues()));

        }
    }

    /**
     * Sees if the player has a specific item in their inventory
     *
     * @param itemName - the name of the item being searched for (a String)
     * @return a boolean, true if the player has the specified item
     *
     * @author lts010
     */
    public boolean playerHasItem(String itemName) {
        for (Item item : player.getInventory()) {
            if (item.getName().contentEquals(itemName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds an NPC in the map based on the given NPC name
     *
     * @param npcName - the name of the desired NPC (a String)
     * @return the NPC requested if it can be found in map, returns null
     * otherwise
     *
     * @author lts010
     */
    public NPC getNPC(String npcName) {
        for (ArrayList<Room> row : map) {
            for (Room room : row) {
                if (room.getNPCViewWrapper().getNPC().getName().contentEquals(
                        npcName)) {
                    return room.getNPCViewWrapper().getNPC();
                }
            }
        }
        return null;
    }

    /**
     * Finds a room in the map based on the given room name
     *
     * @param roomName - the name of the desired room (a String)
     * @return the room requested if it can be found in map, returns null
     * otherwise
     *
     * @author lts010
     */
    public Room getRoom(String roomName) {
        for (ArrayList<Room> row : map) {
            for (Room room : row) {
                if (room.getName().contentEquals(
                        roomName)) {
                    return room;
                }
            }
        }
        return null;
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
