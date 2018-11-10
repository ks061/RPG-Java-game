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

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    //NEW THINGS ADDED TO BORDERPANE
    //topPane
    private FlowPane topPane;
    private TextField roomName;
    private Label roomNameLabel;

    //leftPane
    private VBox leftPane;
    private Label playerStatLabel;
    private Label playerHealthLabel;
    private TextField playerHealth;
    private Label playerAttackLabel;
    private TextField playerAttack;
    private Label playerArmorLabel;
    private TextField playerArmor;

    //rightPane
    private VBox rightPane;
    private Button buttonEquipment;
    private Button buttonItem;
    private BorderPane toRoomButtons;
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
    private Button nullButton1;
    private Button nullButton2;
    private Button nullButton3;
    private Button nullButton4;

    //bottomPane
    private FlowPane bottomPane;
    private TextField storyTextOutput;

    private static final int PREF_WINDOW_WIDTH = 1000;
    private static final int PREF_WINDOW_HEIGHT = 800;
    private static final int PREF_PADDING = 15;

    private static final double TRAVEL_BUTTON_WIDTH = 57.0;

    private static final double RIGHT_PANE_MIN_WIDTH = 2 * TRAVEL_BUTTON_WIDTH;

    /**
     * Constructor that initializes a pointer to the model of the application
     * and initializes components of the GUI
     *
     * @param theModel pointer to the model of the application
     *
     * @author ks061, ishk001
     */
    public RPGView(RPGModel theModel) {
        this.theModel = theModel;

        //Creation of the BorderPane
        this.root = new BorderPane();
        this.root.setPrefWidth(PREF_WINDOW_WIDTH);
        this.root.setPrefHeight(PREF_WINDOW_HEIGHT);
        this.root.setPadding(new Insets(PREF_PADDING));

        //topPane
        this.topPane = new FlowPane(Orientation.HORIZONTAL);
        this.roomNameLabel = new Label("Room Name:");
        this.roomName = new TextField("TEST TEXT ROOM NAME");
        this.topPane.getChildren().add(roomNameLabel);
        this.topPane.getChildren().add(roomName);

        //leftPane
        //VBox containing Flowpanes
        this.leftPane = new VBox();
        this.playerStatLabel = new Label("Player Stats");
        this.playerHealthLabel = new Label("Health");
        this.playerHealth = new TextField("100");
        this.playerAttackLabel = new Label("Attack");
        this.playerAttack = new TextField("100");
        this.playerArmorLabel = new Label("Armor");
        this.playerArmor = new TextField("100");

        //Should I create a flowpane for each of these, or is this format fine?
        this.leftPane.getChildren().add(new FlowPane(playerStatLabel));
        this.leftPane.getChildren().add(new FlowPane(playerHealthLabel,
                                                     playerHealth));
        this.leftPane.getChildren().add(new FlowPane(playerAttackLabel,
                                                     playerAttack));
        this.leftPane.getChildren().add(new FlowPane(playerArmorLabel,
                                                     playerArmor));
        this.leftPane.setAlignment(Pos.CENTER);

        //rightPane
        //Vbox containing buttons
        this.rightPane = new VBox();
        this.buttonEquipment = new Button("Equipment");
        this.buttonItem = new Button("Item");
        this.rightPane.getChildren().add(this.buttonEquipment);
        this.rightPane.getChildren().add(this.buttonItem);

        //Buttons to change rooms
        //Contained in VBox to keep directional orientation for room changes
        this.toRoomButtons = new BorderPane();
        this.toRoomAbove = new Button("Above");
        this.toRoomAbove.setMinWidth(TRAVEL_BUTTON_WIDTH);
        this.toRoomBelow = new Button("Below");
        this.toRoomBelow.setMinWidth(TRAVEL_BUTTON_WIDTH);
        this.toRoomToLeft = new Button("Left");
        this.toRoomToLeft.setMinWidth(TRAVEL_BUTTON_WIDTH);
        this.toRoomToRight = new Button("Right");
        this.toRoomToRight.setMinWidth(TRAVEL_BUTTON_WIDTH);
        this.nullButton1 = new Button("     ");
        this.nullButton1.setMinWidth(TRAVEL_BUTTON_WIDTH);
        this.nullButton2 = new Button("     ");
        this.nullButton2.setMinWidth(TRAVEL_BUTTON_WIDTH);
        this.nullButton3 = new Button("     ");
        this.nullButton3.setMinWidth(TRAVEL_BUTTON_WIDTH);
        this.nullButton4 = new Button("     ");
        this.nullButton4.setMinWidth(TRAVEL_BUTTON_WIDTH);
        this.rightPane.getChildren().add(this.toRoomButtons);

        this.rightPane.setAlignment(Pos.CENTER);
        this.rightPane.setMinWidth(RIGHT_PANE_MIN_WIDTH);

        //bottomPane
        this.bottomPane = new FlowPane(Orientation.HORIZONTAL);
        this.storyTextOutput = new TextField("TEST TEXT STORY");
        this.bottomPane.getChildren().add(this.storyTextOutput);

        //centerPane (NOT YET ADDED) What should we add?
        //Combining everything
        this.root.setTop(this.topPane);
        this.root.setLeft(this.leftPane);
        this.root.setRight(this.rightPane);
        this.root.setBottom(this.bottomPane);

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
            this.toRoomButtons.setTop(this.toRoomAbove);
            BorderPane.setAlignment(this.toRoomAbove, Pos.CENTER);
        }
        else {
            this.toRoomButtons.setTop(this.nullButton1);
            BorderPane.setAlignment(this.nullButton1, Pos.CENTER);
        }
        if (theModel.getCurrentRoom().getSouth() != null) {
            this.toRoomButtons.setBottom(this.toRoomBelow);
            BorderPane.setAlignment(this.toRoomBelow, Pos.CENTER);
        }
        else {
            this.toRoomButtons.setBottom(this.nullButton2);
            BorderPane.setAlignment(this.nullButton2, Pos.CENTER);
        }
        if (theModel.getCurrentRoom().getWest() != null) {
            this.toRoomButtons.setLeft(this.toRoomToLeft);
        }
        else {
            this.toRoomButtons.setLeft(this.nullButton3);
        }
        if (theModel.getCurrentRoom().getEast() != null) {
            this.toRoomButtons.setRight(this.toRoomToRight);
        }
        else {
            this.toRoomButtons.setRight(this.nullButton4);
        }
    }

    /**
     * Jason's Version with updated view
     *
     * @author ishk001
     */
    public void newUpdateTravelButtons() {
        ArrayList<Node> aList = new ArrayList<>();
        HBox leftRight = new HBox();

        //resetting the VBox so we don't get duplicate nodes error
        this.toRoomButtons.getChildren().clear();

        if (theModel.getCurrentRoom().getNorth() != null) {
            aList.add(this.toRoomAbove);
        }
        if (theModel.getCurrentRoom().getWest() != null && theModel.getCurrentRoom().getEast() == null) {
            aList.add(this.toRoomToLeft);
        }
        if (theModel.getCurrentRoom().getWest() == null && theModel.getCurrentRoom().getEast() != null) {
            aList.add(this.toRoomToRight);
        }
        if (theModel.getCurrentRoom().getWest() != null && theModel.getCurrentRoom().getEast() != null) {
            leftRight.getChildren().add(this.toRoomToLeft);
            leftRight.getChildren().add(this.toRoomToRight);
            aList.add(leftRight);
        }
        if (theModel.getCurrentRoom().getSouth() != null) {
            aList.add(this.toRoomBelow);
        }
        for (Node node : aList) {
            this.toRoomButtons.getChildren().add(node);
        }
    }
}
