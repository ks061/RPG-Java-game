/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Kartikeya Sharma
 * Date: Oct 19, 2018
 * Time: 7:18:02 PM
 *
 * Project: csci205
 * Package: lab13.tempconvertmvc
 * File: RPGView
 * Description: This file contains RPGView, which serves as the
 *              view for the RPG game application.
 * ****************************************
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import model.RPGModel;

/**
 * RPGView serves as the view for the RPG game application.
 *
 * @author ks061
 */
public class RPGView {

    /**
     * Models for the application
     */
    private RPGModel theModel;
    /**
     * Root node on the stage in the GUI
     */
    private BorderPane root;
    /**
     * Button that allows player to move to the room above the current room
     */
    private Button toRoomAbove;
    /**
     * Button that allows player to move to the room below the current room
     */
    private Button toRoomBelow;
    /**
     * Button that allows player to move to the room to the left of the current
     * room
     */
    private Button toRoomToLeft;
    /**
     * Button that allows player to move to the room to the right of the current
     * room
     */
    private Button toRoomToRight;

    private static final int PREF_WINDOW_WIDTH = 1000;
    private static final int PREF_WINDOW_HEIGHT = 1000;
    private static final int PREF_PADDING = 15;

    /**
     * Constructor that initializes a pointer to the model of the application
     * and initializes components of the GUI
     *
     * @param theModel pointer to the model of the application
     *
     * @author ks061
     */
    public RPGView(RPGModel theModel) {
        this.theModel = theModel;

        this.root = new BorderPane();
        this.root.setPrefWidth(PREF_WINDOW_WIDTH);
        this.root.setPrefHeight(PREF_WINDOW_HEIGHT);
        this.root.setPadding(new Insets(PREF_PADDING));

        this.toRoomAbove = new Button("Above");
        this.toRoomBelow = new Button("Below");
        this.toRoomToLeft = new Button("Left");
        this.toRoomToRight = new Button("Right");

        updateTravelButtons();
    }

    /**
     * Gets the root node of the stage
     *
     * @return root node of the stage
     *
     * @author ks061
     */
    public BorderPane getRootNode() {
        return root;
    }

    /**
     * Gets the button to go to the room above the current one
     *
     * @return button to go to the room above the current one
     *
     * @author ks061
     */
    public Button getToRoomAbove() {
        return toRoomAbove;
    }

    /**
     * Gets the button to go to the room below the current one
     *
     * @return button to go to the room below the current one
     *
     * @author ks061
     */
    public Button getToRoomBelow() {
        return toRoomBelow;
    }

    /**
     * Gets the button to go to the room to the left of the current one
     *
     * @return button to go to the room to the left of the current one
     *
     * @author ks061
     */
    public Button getToRoomToLeft() {
        return toRoomToLeft;
    }

    /**
     * Gets the button to go to the room to the right of the current one
     *
     * @return button to go to the room to the right of the current one
     *
     * @author ks061
     */
    public Button getToRoomToRight() {
        return toRoomToRight;
    }

    /**
     * Updates the view of the buttons to travel from one room to another based
     * upon whether or not that room has a room adjacent to it (on each of its
     * four sides)
     */
    public void updateTravelButtons() {
        if (theModel.getCurrentRoom().getNorth() != null) {
            this.root.setTop(this.toRoomAbove);
        }
        else {
            this.root.setTop(null);
        }
        if (theModel.getCurrentRoom().getSouth() != null) {
            this.root.setBottom(this.toRoomBelow);
        }
        else {
            this.root.setBottom(null);
        }
        if (theModel.getCurrentRoom().getWest() != null) {
            this.root.setLeft(this.toRoomToLeft);
        }
        else {
            this.root.setLeft(null);
        }
        if (theModel.getCurrentRoom().getEast() != null) {
            this.root.setRight(this.toRoomToRight);
        }
        else {
            this.root.setRight(null);
        }
    }
}
