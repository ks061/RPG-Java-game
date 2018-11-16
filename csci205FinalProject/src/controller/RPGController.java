/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Kartikeya Sharma
 * Date: Oct 19, 2018
 * Time: 7:19:08 PM
 *
 * Project: csci205
 * Package: lab13.tempconvertmvc
 * File: RPGController
 * Description: This file contains RPGController, which serves as the
 *              connector between the model and view in the RPG game
 *              application.
 * ****************************************
 */
package controller;

import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.RPGModel;
import model.character.NPC;
import view.RPGView;
import view.wrapper.NPCImageViewWrapper;

/**
 * RPGController serves as the connector between the model and view in the RPG
 * game application.
 *
 * @author ks061
 */
public class RPGController {

    /**
     * Action event handler for the class
     *
     * @author ks061
     */
    private class RPGActionEventHandler implements EventHandler<ActionEvent> {

        /**
         * Handles an action event that occurs in the application
         *
         * @param event action event that occurs in the application
         *
         * @author ks061
         */
        public void handle(ActionEvent event) {
            if (event.getSource() instanceof Node) {
                if (theView.getToRoomButtonsDisplay().getChildren().contains(
                        (Node) event.getSource())) {
                    handleTravelButtonClick(event);
                }
            }
        }

        /**
         * Handles events when the player clicks on a travel button
         *
         * @param event event of a player clicking on a travel button
         *
         * @author ks061
         */
        private void handleTravelButtonClick(ActionEvent event) {
            if (event.getSource() == theView.getToRoomAbove()) {
                theModel.setCurrentRoom(
                        theModel.getCurrentRoom().getNorth());
            }
            if (event.getSource() == theView.getToRoomBelow()) {
                theModel.setCurrentRoom(
                        theModel.getCurrentRoom().getSouth());
            }
            if (event.getSource() == theView.getToRoomToLeft()) {
                theModel.setCurrentRoom(
                        theModel.getCurrentRoom().getWest());
            }
            if (event.getSource() == theView.getToRoomToRight()) {
                theModel.setCurrentRoom(
                        theModel.getCurrentRoom().getEast());
            }
            refresh();
        }
    }

    /**
     * Mouse event handler for the controller
     *
     * @author ks061
     */
    private class RPGMouseEventHandler implements EventHandler<MouseEvent> {

        /**
         * Handles events when the player clicks on an NPC
         *
         * @param event event of a player clicking on an NPC
         *
         * @author ks061
         */
        public void handle(MouseEvent event) {
            NPC npcInCurrentRoom = null;
            if (event.getSource() instanceof ImageView) {
                npcInCurrentRoom = mapImageViewToNPC.get(event.getSource());
            }
            try {
                String dialog = theModel.getPlayer().talk(npcInCurrentRoom);
                theView.updateStoryTextOutput(
                        npcInCurrentRoom.getName() + ": " + dialog);
            } catch (NullPointerException ex) {
            }
        }
    }

    /**
     * Model of the application
     */
    private RPGModel theModel;
    /**
     * View of the application
     */
    private RPGView theView;
    /**
     * Action event handler
     */
    private RPGActionEventHandler rpgActionEventHandler;
    /**
     * Mouse event handler
     */
    private RPGMouseEventHandler rpgMouseEventHandler;
    /**
     * Maps an image view to an NPC
     */
    private HashMap<ImageView, NPC> mapImageViewToNPC;

    /**
     * Constructor that initializes references to the model and view of the
     * application, sets the view node-model component map, and sets event
     * handlers for the view components
     *
     * @param theModel model of the application
     * @param theView view of the application
     *
     * @author ks061
     */
    public RPGController(RPGModel theModel,
                         RPGView theView) {
        this.theModel = theModel;
        this.theView = theView;

        this.rpgActionEventHandler = new RPGActionEventHandler();
        this.rpgMouseEventHandler = new RPGMouseEventHandler();

        this.mapImageViewToNPC = new HashMap<>();

        setEventHandlerOfComponents();

        refresh();
    }

    /**
     * Sets the event handlers of components in the GUI
     *
     * @author ks061
     */
    private void setEventHandlerOfComponents() {
        this.theView.getToRoomAbove().setOnAction(this.rpgActionEventHandler);
        this.theView.getToRoomBelow().setOnAction(this.rpgActionEventHandler);
        this.theView.getToRoomToLeft().setOnAction(this.rpgActionEventHandler);
        this.theView.getToRoomToRight().setOnAction(this.rpgActionEventHandler);

    }

    /**
     * Updates the travel buttons and NPCs in the room based on the current room
     *
     * @author ks061
     */
    public void refresh() {
        updateTravelButtons();
        updateNPCsInRoom();
        this.theView.refreshRoomName();
        this.theView.updateStoryTextOutput("");
    }

    /**
     * Updates the view of the buttons to travel from one room to another based
     * upon whether or not that room has a room adjacent to it (on each of its
     * four sides)
     *
     * @author ks061, ishk001
     */
    public void updateTravelButtons() {
        Button nullButton;
        if (this.theModel.getCurrentRoom().getNorth() != null) {
            this.theView.getToRoomButtonsDisplay().setTop(
                    this.theView.getToRoomAbove());
            BorderPane.setAlignment(this.theView.getToRoomAbove(), Pos.CENTER);
        }
        else {
            nullButton = this.theView.getNewNullButton();
            this.theView.getToRoomButtonsDisplay().setTop(nullButton);
            BorderPane.setAlignment(nullButton, Pos.CENTER);
        }
        if (this.theModel.getCurrentRoom().getSouth() != null) {
            this.theView.getToRoomButtonsDisplay().setBottom(
                    this.theView.getToRoomBelow());
            BorderPane.setAlignment(this.theView.getToRoomBelow(), Pos.CENTER);
        }
        else {
            nullButton = this.theView.getNewNullButton();
            this.theView.getToRoomButtonsDisplay().setBottom(nullButton);
            BorderPane.setAlignment(this.theView.getNewNullButton(), Pos.CENTER);
        }
        if (this.theModel.getCurrentRoom().getWest() != null) {
            this.theView.getToRoomButtonsDisplay().setLeft(
                    this.theView.getToRoomToLeft());
        }
        else {
            nullButton = this.theView.getNewNullButton();
            this.theView.getToRoomButtonsDisplay().setLeft(nullButton);
        }
        if (this.theModel.getCurrentRoom().getEast() != null) {
            this.theView.getToRoomButtonsDisplay().setRight(
                    this.theView.getToRoomToRight());
        }
        else {
            nullButton = this.theView.getNewNullButton();
            this.theView.getToRoomButtonsDisplay().setRight(nullButton);
        }
    }

    /**
     * Updates the view to reflect the NPCs in the current room
     *
     * @author ks061
     */
    public void updateNPCsInRoom() {
        this.theView.getCenterPane().getChildren().clear();
        for (NPCImageViewWrapper npcViewWrapper : this.theModel.getCurrentRoom().getNPCViewWrappers()) {
            this.theView.getCenterPane().getChildren().add(
                    npcViewWrapper.getNpcImageView());
            npcViewWrapper.getNpcImageView().setOnMouseClicked(
                    this.rpgMouseEventHandler);
            mapImageViewToNPC.put(npcViewWrapper.getNpcImageView(),
                                  npcViewWrapper.getNpc());
        }
    }

}
