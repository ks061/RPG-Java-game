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

//    /**
//     * The properties that represent the current statistics of the player
//     */
//    private StringProperty propPlayerCurrentHealth;
//    private StringProperty propPlayerAttack;
//    private StringProperty propPlayerDefense;
//
//    /**
//     * The properties that represent the player's current equipment
//     */
//    private StringProperty propPlayerWeapon;
//    private StringProperty propPlayerArmor;
//    private StringProperty propPlayerShield;
    /**
     * Constructor that initializes the model of the application
     *
     * @author ks061
     */
    public RPGModel() {
//        this.propPlayerCurrentHealth = new SimpleStringProperty("");
//        this.propPlayerAttack = new SimpleStringProperty("");
//        this.propPlayerDefense = new SimpleStringProperty("");
//        this.propPlayerWeapon = new SimpleStringProperty("");
//        this.propPlayerArmor = new SimpleStringProperty("");
//        this.propPlayerShield = new SimpleStringProperty("");
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
     * @author ks061 lts010
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
                        "img/room" + roomNumber + ".png");
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

//    /**
//     * Updates the current health property of the player
//     *
//     * @author ks061, lts010
//     */
//    private void updateCurrentHealthProperty() {
//        String currentHealthString = String.format("%d",
//                                                   this.player.getCharacterStats().getHealth());
//        this.propPlayerCurrentHealth.setValue(currentHealthString);
//    }
//
//    /**
//     * Updates the attack property of the player
//     *
//     * @author ks061, lts010
//     */
//    private void updateAttackProperty() {
//        String attackString = String.format("%d",
//                                            this.player.getCharacterStats().getAttack());
//        this.propPlayerAttack.setValue(attackString);
//    }
//
//    /**
//     * Updates the defense property of the player
//     *
//     * @author ks061, lts010
//     */
//    private void updateDefenseProperty() {
//        String defenseString = String.format("%d",
//                                             this.player.getCharacterStats().getDefense());
//        this.propPlayerDefense.setValue(defenseString);
//    }
//
//    /**
//     * Updates the weapon name property of the player
//     *
//     * @author ks061, lts010
//     */
//    private void updateWeaponProperty() {
//        if (this.player.getWeapon() == null) {
//            this.propPlayerWeapon.setValue("None");
//        }
//        else {
//            this.propPlayerWeapon.setValue(this.player.getWeapon().getName());
//        }
//    }
//
//    /**
//     * Updates the armor name property of the player
//     *
//     * @author ks061, lts010
//     */
//    private void updateArmorProperty() {
//        if (this.player.getArmor() == null) {
//            this.propPlayerArmor.setValue("None");
//        }
//        else {
//            this.propPlayerArmor.setValue(this.player.getArmor().getName());
//        }
//    }
//
//    /**
//     * Updates the shield name property of the player
//     *
//     * @author ks061, lts010
//     */
//    private void updateShieldProperty() {
//        if (this.player.getShield() == null) {
//            this.propPlayerShield.setValue("None");
//        }
//        else {
//            this.propPlayerShield.setValue(this.player.getShield().getName());
//        }
//    }
//    /**
//     * Updates the properties representing the player's statistics and equipment
//     * to keep them current with the player
//     *
//     * @author lts010, ks061
//     */
//    public void updateProperties() {
//        updateCurrentHealthProperty();
//        updateAttackProperty();
//        updateDefenseProperty();
//
//        updateWeaponProperty();
//        updateArmorProperty();
//        updateShieldProperty();
//    }
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
                r.setNPCViewWrappers(roomContent.getNPCWrappers());
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

//
//    /**
//     * Gets the string property representing the player's current health
//     *
//     * @return the string property representing the player's current health
//     *
//     * @author lts010
//     */
//    public StringProperty getPropPlayerCurrentHealth() {
//        return propPlayerCurrentHealth;
//    }
//
//    /**
//     * Gets the string property representing the player's current attack
//     *
//     * @return the string property representing the player's current attack
//     *
//     * @author lts010
//     */
//    public StringProperty getPropPlayerAttack() {
//        return propPlayerAttack;
//    }
//
//    /**
//     * Gets the string property representing the player's current defense
//     *
//     * @return the string property representing the player's current defense
//     *
//     * @author lts010
//     */
//    public StringProperty getPropPlayerDefense() {
//        return propPlayerDefense;
//    }
//
//    /**
//     * Gets the string property representing the player's current weapon
//     *
//     * @return the string property representing the player's current weapon
//     *
//     * @author lts010
//     */
//    public StringProperty getPropPlayerWeapon() {
//        return propPlayerWeapon;
//    }
//
//    /**
//     * Gets the string property representing the player's current armor
//     *
//     * @return the string property representing the player's current armor
//     *
//     * @author lts010
//     */
//    public StringProperty getPropPlayerArmor() {
//        return propPlayerArmor;
//    }
//
//    /**
//     * Gets the string property representing the player's current armor
//     *
//     * @return the string property representing the player's current armor
//     *
//     * @author lts010
//     */
//    public StringProperty getPropPlayerShield() {
//        return propPlayerShield;
//    }
}
