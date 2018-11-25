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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private Label roomNameLabel;
    private Label roomName;

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
    private Button buttonInventory;
    private Button backButtonInventory;
    //private Button buttonEquipment;
    //private Button buttonItem;

    private VBox rightPaneInventory;
    private HBox mainInventory;
    private VBox itemList;
    private VBox equipmentList;
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

    /*
    bottomPane
    -Variables involved in the bottom pane of the border pane root
    -Contains the reference point for the model to output the plot line
     */
    private FlowPane bottomPane;
    private Label storyTextOutput;

    /*
    centerPane
     */
    private Pane centerPane;

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
        this.roomNameLabel = new Label("Room Name: ");
        this.roomName = new Label(theModel.getCurrentRoom().getName());
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
        this.buttonInventory = new Button("Inventory");
        this.backButtonInventory = new Button("Back");
        this.rightPane.getChildren().add(this.buttonInventory);
        //this.buttonEquipment = new Button("Equipment");
        //this.buttonItem = new Button("Item");
        //this.rightPane.getChildren().add(this.buttonEquipment);
        //this.rightPane.getChildren().add(this.buttonItem);

        /*
        RightPane when Inventory button is clicked
         */
        this.rightPaneInventory = new VBox();
        this.mainInventory = new HBox();
        this.itemList = new VBox();
        this.equipmentList = new VBox();
        this.rightPaneInventory.getChildren().add(this.itemList);
        this.rightPaneInventory.getChildren().add(this.equipmentList);
        this.rightPaneInventory.getChildren().add(this.backButtonInventory);
        this.rightPaneInventory.setAlignment(Pos.CENTER);
        this.rightPaneInventory.setMinWidth(RIGHT_PANE_MIN_WIDTH);

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
        this.rightPane.getChildren().add(this.toRoomButtons);

        this.rightPane.setAlignment(Pos.CENTER);
        this.rightPane.setMinWidth(RIGHT_PANE_MIN_WIDTH);

        /*
        BottomPane created
        -Spaces to dispaly the story in text form
        -Position has been set to center
         */
        this.bottomPane = new FlowPane(Orientation.HORIZONTAL);
        this.storyTextOutput = new Label("");
        this.bottomPane.getChildren().add(this.storyTextOutput);
        this.bottomPane.setAlignment(Pos.CENTER);

        /*
        CenterPane created
         */
        this.centerPane = new Pane();
        this.centerPane.setPrefSize(200, 200);

        /*
        Everything is now added to the root node
         */
        this.root.setTop(this.topPane);
        this.root.setLeft(this.leftPane);
        this.root.setRight(this.rightPane);
        this.root.setBottom(this.bottomPane);
        this.root.setCenter(this.centerPane);
        BorderPane.setAlignment(this.centerPane, Pos.CENTER_LEFT);
    }

    /**
     * Gets the original right pane containing the directional buttons and the
     * menu for player actions
     *
     * @return original right pane
     *
     * @author ishk001
     */
    public VBox getRightPane() {
        return rightPane;
    }

    /**
     * Gets the back button which will take the player from the inventory back
     * to the original right pane
     *
     * @return back button
     */
    public Button getBackButtonInventory() {
        return backButtonInventory;
    }

    /**
     * Gets the new right pane which will display the consumables and the
     * equipments that the player currently holds
     *
     * @return inventory of consumables and equipments
     *
     * @author ishl001
     */
    public VBox getRightPaneInventory() {
        return rightPaneInventory;
    }

    /**
     * Gets the left side of the inventory list which will display consumables
     *
     * @return vertical list of consumable items
     *
     * @author ishk001
     */
    public VBox getItemList() {
        return itemList;
    }

    /**
     * Gets the right side of the inventory list which will display equipments
     *
     * @return vertical list of equipments
     *
     * @author ishk001
     */
    public VBox getEquipmentList() {
        return equipmentList;
    }

    /**
     * Gets the right pane of the root node (border pane)
     *
     * @return right pane of the border pane root node
     *
     * @author ishk001
     */
    public Button getButtonInventory() {
        return buttonInventory;
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
     * Gets the pane of travel buttons
     *
     * @return pane of travel buttons
     *
     * @author ks061, ishk001
     */
    public BorderPane getToRoomButtonsDisplay() {
        return toRoomButtons;
    }

    /**
     * Creates and returns an empty button to replace a travel button
     *
     * @return empty button to replace a travel button
     *
     * @author ks061
     */
    public Button getNewNullButton() {
        Button newNullButton = new Button("     ");
        newNullButton.setMinWidth(TRAVEL_BUTTON_WIDTH);
        return newNullButton;
    }

    /**
     * Gets the center pane of the view
     *
     * @return center pane of the view
     *
     * @author ks061
     */
    public Pane getCenterPane() {
        return centerPane;
    }

    /**
     * Updates the dialog displayed in the story text output
     *
     * @param dialog dialog displayed in the story text output
     *
     * @author ks061
     */
    public void updateStoryTextOutput(String dialog) {
        this.storyTextOutput.setText(dialog);
    }

    /**
     * Updates the room name based on the current room
     *
     * @author ks061
     */
    public void refreshRoomName() {
        this.roomName.setText(this.theModel.getCurrentRoom().getName());
    }

}
