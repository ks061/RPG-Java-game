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
import javafx.scene.image.Image;
import model.RPGModel;
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

        this.theView.getToRoomAbove().setOnAction(this);
        this.theView.getToRoomBelow().setOnAction(this);
        this.theView.getToRoomToLeft().setOnAction(this);
        this.theView.getToRoomToRight().setOnAction(this);

        this.theView.getNpcImage().setOnMouseClicked(MouseEvent e


        ) -> {
                         };
    }

    /**
     * Handles events that occur in the application
     *
     * @param event event that occurs in the application
     *
     * @author ks061
     */
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Node) {
            if (this.theView.getToRoomButtons().getChildren().contains(
                    (Node) event.getSource())) {
                handleTravelButtonClick(event);
            }
        }
        if (event.getSource() instanceof Image) {
            if (event.getSource() == this.theView.getNpcImage()) {
                handleNpcClick(event);
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
    }

    /**
     * Handles events when the player clicks on an NPC
     *
     * @param event event of a player clicking on an NPC
     *
     * @author ks061
     */
    private void handleNpcClick(ActionEvent event) {
        if (event.getSource() == this.theView.getNpcImage()) {
            NPC npcInCurrentRoom = this.theModel.getCurrentRoom().getNpc();
            this.theModel.getPlayer().talk(npcInCurrentRoom);
        }
    }
}
