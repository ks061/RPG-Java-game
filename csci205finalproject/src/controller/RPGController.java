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

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.RPGModel;
import view.RPGView;

/**
 * RPGController serves as the connector between the model and view in the RPG
 * game application.
 *
 * @author ks061
 */
public class RPGController implements EventHandler<MouseEvent> {

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
    }

    /**
     * Handles events that occur in the application
     *
     * @param event event that occurs in the application
     *
     * @author ks061
     */
    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            handleButtonClick(event);
        }
    }

    /**
     * Handles events when the player clicks a button
     *
     * @param event event of a player clicking a button
     *
     * @author ks061
     */
    private void handleButtonClick(MouseEvent event) {
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
}
