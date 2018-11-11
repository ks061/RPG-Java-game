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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.RPGModel;

/**
 * RPGView serves as the view for the RPG game application.
 *
 * @author ks061
 */
public class RPGView {

    /*
    Models for the application
     */
    private RPGModel theModel;
    /*
    Root node on the stage in the GUI
     */
    private BorderPane root;

    /*
    topPane
    -Variables involved in the top pane of the border pane root
    -Contains the name of the current room the player is in (i.e. BRKI 164)
     */
    private FlowPane topPane;
    private Label roomName;
    private Label roomNameLabel;

    /*
    leftPane
    -Variables involved in the left pane of the border pane root
    -Contains the player's stats (health, attack, and armor)
     */
    private VBox leftPane;
    private Label playerStatLabel;
    private Label playerHealthLabel;
    private Label playerHealth;
    private Label playerAttackLabel;
    private Label playerAttack;
    private Label playerArmorLabel;
    private Label playerArmor;

    /*
    rightPane
    -Variables involved in the left pane of the border pane root
    -Contains buttons that the player can click to perform different actions
     */
    private VBox rightPane;
    private Button buttonEquipment;
    private Button buttonItem;
    /*
    Buttons are stored in a BorderPane for organizational purposes.
    These buttons make the player go to different rooms in the north, east,
    west, and south of its current location, so we chose this instead of
    putting everything in, for example, an HBox.
     */
    private BorderPane toRoomButtons;
    /*
    Buttons starting with "to" moves the player to another adjacent room.
    Buttons starting with null are there as a filler for the "to" buttons
    because we don't want players moving to the left if they are already on
    the left edge of the map.
     */
    private Button toRoomAbove;
    private Button toRoomToLeft;
    private Button toRoomToRight;
    private Button toRoomBelow;
    private Button nullButton1;
    private Button nullButton2;
    private Button nullButton3;
    private Button nullButton4;

    /*
    bottomPane
    -Variables involved in the bottom pane of the border pane root
    -Contains the reference point for the model to output the plot line
     */
    private FlowPane bottomPane;
    private Label storyTextOutput;

    /*
    Static finals for the view
    Recommended by Prof. Dancy :)
     */
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

        /*
        BorderPane created
        -set preferred height and width as well as padding
         */
        this.root = new BorderPane();
        this.root.setPrefWidth(PREF_WINDOW_WIDTH);
        this.root.setPrefHeight(PREF_WINDOW_HEIGHT);
        this.root.setPadding(new Insets(PREF_PADDING));

        /*
        TopPane created
        -Labels to display the room name has been added
        -Alignment has been set to center
         */
        this.topPane = new FlowPane(Orientation.HORIZONTAL);
        this.roomNameLabel = new Label("Room Name:");
        this.roomName = new Label(" TEST TEXT ROOM NAME");
        this.topPane.getChildren().add(roomNameLabel);
        this.topPane.getChildren().add(roomName);
        this.topPane.setAlignment(Pos.CENTER);

        /*
        LeftPane created
        -Labels for the player's stats have been added
        -Alignment has been set to center
         */
        this.leftPane = new VBox();
        this.leftPane.setSpacing(PREF_PADDING);
        this.playerStatLabel = new Label("Player Stats");
        this.playerHealthLabel = new Label("Health:");
        this.playerHealth = new Label(" 100");
        this.playerAttackLabel = new Label("Attack:");
        this.playerAttack = new Label(" 100");
        this.playerArmorLabel = new Label("Armor:");
        this.playerArmor = new Label(" 100");
        this.leftPane.getChildren().add(new FlowPane(playerStatLabel));
        this.leftPane.getChildren().add(new FlowPane(playerHealthLabel,
                                                     playerHealth));
        this.leftPane.getChildren().add(new FlowPane(playerAttackLabel,
                                                     playerAttack));
        this.leftPane.getChildren().add(new FlowPane(playerArmorLabel,
                                                     playerArmor));
        this.leftPane.setAlignment(Pos.CENTER);

        /*
        RightPane created
        -Buttons have been created and added for player's action choices
        -Alignment has been set to center
         */
        this.rightPane = new VBox();
        this.rightPane.setSpacing(PREF_PADDING);
        this.buttonEquipment = new Button("Equipment");
        this.buttonItem = new Button("Item");
        this.rightPane.getChildren().add(this.buttonEquipment);
        this.rightPane.getChildren().add(this.buttonItem);

        /*
        Button settings
        -Width of the buttons have been set to constants because RightPane would
        automatically update the width and be bothersome to the players
         */
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

        /*
        BottomPane created
        -Spaces to dispaly the story in text form
        -Position has been set to center
         */
        this.bottomPane = new FlowPane(Orientation.HORIZONTAL);
        this.storyTextOutput = new Label("TEST TEXT STORY");
        this.bottomPane.getChildren().add(this.storyTextOutput);
        this.bottomPane.setAlignment(Pos.CENTER);

        /*
        Everything is now added to the root node
         */
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
     *
     * @author ks061, ishk001
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
}
