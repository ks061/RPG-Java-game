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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import model.RPGModel;
import model.character.NPC;
import view.RPGView;

/**
 * RPGController serves as the connector between the model and view in the RPG
 * game application.
 *
 * @author ks061
 */
public class RPGController implements EventHandler<ActionEvent> {

    /**
     * Model of the application
     */
    private RPGModel theModel;
    /**
     * View of the application
     */
    private RPGView theView;

    /**
     * Constructor that initializes references to the model and view of the
     * application
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

        setEventHandlerOfComponents();
    }

    /**
     * Sets the event handlers of components in the GUI
     *
     * @author ks061
     */
    private void setEventHandlerOfComponents() {
        this.theView.getToRoomAbove().setOnAction(this);
        this.theView.getToRoomBelow().setOnAction(this);
        this.theView.getToRoomToLeft().setOnAction(this);
        this.theView.getToRoomToRight().setOnAction(this);

        this.theView.getNpcImageView().setOnMouseClicked((MouseEvent event) -> {
            handleNpcClick(event);
        });
    }

    /**
     * Handles an action event that occurs in the application
     *
     * @param event action event that occurs in the application
     *
     * @author ks061
     */
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Node) {
            if (this.theView.getToRoomButtons().getChildren().contains(
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
        if (event.getSource() == this.theView.getToRoomAbove()) {
            this.theModel.setCurrentRoom(
                    this.theModel.getCurrentRoom().getNorth());
        }
        if (event.getSource() == this.theView.getToRoomBelow()) {
            this.theModel.setCurrentRoom(
                    this.theModel.getCurrentRoom().getSouth());
        }
        if (event.getSource() == this.theView.getToRoomToLeft()) {
            this.theModel.setCurrentRoom(
                    this.theModel.getCurrentRoom().getWest());
        }
        if (event.getSource() == this.theView.getToRoomToRight()) {
            this.theModel.setCurrentRoom(
                    this.theModel.getCurrentRoom().getEast());
        }
        theView.updateTravelButtons();
        theView.updateRoomName(theModel.getCurrentRoom().getName());
        theView.updateStoryTextOutput("");
    }

    /**
     * Handles events when the player clicks on an NPC
     *
     * @param event event of a player clicking on an NPC
     *
     * @author ks061
     */
    private void handleNpcClick(MouseEvent event) {
        NPC npcInCurrentRoom = this.theModel.getCurrentRoom().getNpc();
        String dialog = this.theModel.getPlayer().talk(npcInCurrentRoom);
        this.theView.updateStoryTextOutput(
                npcInCurrentRoom.getName() + ": " + dialog);
    }
}
