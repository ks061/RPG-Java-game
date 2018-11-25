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

import java.io.File;
import java.util.EnumMap;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.RPGModel;

/**
 * RPGView serves as the view for the RPG game application.
 *
 * @author ks061
 */
public class RPGView {

    /**
     * The buttons and action bubbles that appear on the screen
     *
     * @author lts010
     */
    public enum ImageType {
        UPARROW, DOWNARROW, LEFTARROW, RIGHTARROW, INVENTORY, ATTACK, SEARCH, TRADE, POW, BAM, WHIFF, CRUNCH;
    }

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
    -Also contains the player's current equipment (weapon, armor, shield)
     */
    private VBox leftPane;
    private Label playerStatLabel;
    private Label playerHealthLabel;
    private Text playerHealth;
    private Label playerAttackLabel;
    private Text playerAttack;
    private Label playerDefenseLabel;
    private Text playerDefense;
    public Text playerShield;
    public Text playerArmor;
    public Text playerWeapon;
    public Label ShieldLabel;
    public Label ArmorLabel;
    public Label WeaponLabel;
    public Label EquipmentLabel;

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

    /**
     * The map that stores all the ImageView objects using the ImageType enum as
     * a key
     */
    private EnumMap<ImageType, ImageView> imageViews;

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
        this.roomNameLabel = new Label(String.format("%s is in ",
                                                     this.theModel.getPlayer().getName()));
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
        this.playerStatLabel = new Label("Player Status");
        this.playerHealthLabel = new Label("Health: ");
        this.playerHealth = new Text();
        this.playerAttackLabel = new Label("Attack: ");
        this.playerAttack = new Text();
        this.playerDefenseLabel = new Label("Defense: ");
        this.playerDefense = new Text();

        EquipmentLabel = new Label("Equipment");
        WeaponLabel = new Label("Your Weapon:");
        ArmorLabel = new Label("Your Armor:");
        ShieldLabel = new Label("Your Shield:");
        playerWeapon = new Text();
        playerArmor = new Text();
        playerShield = new Text();
        this.leftPane.getChildren().add(new FlowPane(playerStatLabel));
        this.leftPane.getChildren().add(new FlowPane(playerHealthLabel,
                                                     playerHealth));
        this.leftPane.getChildren().add(new FlowPane(playerAttackLabel,
                                                     playerAttack));
        this.leftPane.getChildren().add(new FlowPane(playerDefenseLabel,
                                                     playerDefense));
        this.leftPane.getChildren().add(new FlowPane());
        this.leftPane.getChildren().add(new FlowPane(EquipmentLabel));
        this.leftPane.getChildren().add(new FlowPane(WeaponLabel));
        this.leftPane.getChildren().add(new FlowPane(playerWeapon));
        this.leftPane.getChildren().add(new FlowPane(ArmorLabel));
        this.leftPane.getChildren().add(new FlowPane(playerArmor));
        this.leftPane.getChildren().add(new FlowPane(ShieldLabel));
        this.leftPane.getChildren().add(new FlowPane(playerShield));
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
//        this.toRoomButtons = new BorderPane();
//        this.toRoomAbove = new Button("Above");
//        this.toRoomAbove.setMinWidth(TRAVEL_BUTTON_WIDTH);
//        this.toRoomBelow = new Button("Below");
//        this.toRoomBelow.setMinWidth(TRAVEL_BUTTON_WIDTH);
//        this.toRoomToLeft = new Button("Left");
//        this.toRoomToLeft.setMinWidth(TRAVEL_BUTTON_WIDTH);
//        this.toRoomToRight = new Button("Right");
//        this.toRoomToRight.setMinWidth(TRAVEL_BUTTON_WIDTH);
//        this.rightPane.getChildren().add(this.toRoomButtons);
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
        //this.centerPane.setPrefSize(200, 200);
        this.centerPane.setMinHeight(400);
        this.centerPane.setMinWidth(700);
        this.loadImages();

        /*
        Everything is now added to the root node
         */
        this.root.setCenter(this.centerPane);
        this.root.setTop(this.topPane);
        this.root.setLeft(this.leftPane);
        this.root.setRight(this.rightPane);
        this.root.setBottom(this.bottomPane);
        BorderPane.setAlignment(this.centerPane, Pos.CENTER_LEFT);
    }

    /**
     * Creates a background for the centerPane by using the file path given
     *
     * @param backgroundImagePath - the file path that should lead to an image
     * that will be used as the background of the centerPane
     *
     * @author lts010
     */
    public void loadCenterBackground(String backgroundImagePath) {
        backgroundImagePath = new File(backgroundImagePath).toURI().toString();
        Image image = new Image(backgroundImagePath);
        BackgroundSize backgroundSize = new BackgroundSize(
                this.centerPane.getPrefWidth(), this.centerPane.getPrefHeight(),
                false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundPosition.CENTER,
                                                              backgroundSize);
        Background centerBackground = new Background(backgroundImage);
        this.centerPane.setBackground(centerBackground);
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

    /**
     * Gets the text representing the player's current health
     *
     * @return the text representing the player's current health
     *
     * @author lts010
     */
    public Text getPlayerHealth() {
        return playerHealth;
    }

    /**
     * Gets the text representing the player's attack
     *
     * @return the text representing the player's attack
     *
     * @author lts010
     */
    public Text getPlayerAttack() {
        return playerAttack;
    }

    /**
     * Gets the text representing the player's defense
     *
     * @return the text representing the player's defense
     *
     * @author lts010
     */
    public Text getPlayerDefense() {
        return playerDefense;
    }

    /**
     * Gets the text representing the player's shield
     *
     * @return the text representing the player's defense
     *
     * @author lts010
     */
    public Text getPlayerShield() {
        return playerShield;
    }

    /**
     * Gets the text representing the player's armor
     *
     * @return the text representing the player's defense
     *
     * @author lts010
     */
    public Text getPlayerArmor() {
        return playerArmor;
    }

    /**
     * Gets the text representing the player's weapon
     *
     * @return the text representing the player's defense
     *
     * @author lts010
     */
    public Text getPlayerWeapon() {
        return playerWeapon;
    }

    /**
     * Gets the image views
     *
     * @return the image views
     *
     * @author lts010
     */
    public EnumMap<ImageType, ImageView> getImageViews() {
        return imageViews;
    }

    /**
     * Gets the right pane
     *
     * @return the right pane
     *
     * @author lts010
     */
    public VBox getRightPane() {
        return rightPane;
    }

    /**
     * Adds many imageView objects to the EnumMap imageViews
     *
     * @author lts010
     */
    public void loadImages() {
        this.imageViews = new EnumMap<ImageType, ImageView>(ImageType.class);
        this.imageViews.put(ImageType.UPARROW, loadImage("img/arrow-up.png"));
        this.imageViews.put(ImageType.DOWNARROW, loadImage("img/arrow-down.png"));
        this.imageViews.put(ImageType.LEFTARROW, loadImage("img/arrow-left.png"));
        this.imageViews.put(ImageType.RIGHTARROW, loadImage(
                            "img/arrow-right.png"));
        this.imageViews.put(ImageType.INVENTORY, loadImage("img/inventory.png"));
        this.imageViews.put(ImageType.ATTACK, loadImage("img/attack.png"));
        this.imageViews.put(ImageType.SEARCH, loadImage("img/search.png"));
        this.imageViews.put(ImageType.TRADE, loadImage("img/trade.png"));
        this.imageViews.put(ImageType.POW, loadImage("img/POW.png"));
        this.imageViews.put(ImageType.BAM, loadImage("img/BAM.png"));
        this.imageViews.put(ImageType.WHIFF, loadImage("img/WHIFF.png"));
        this.imageViews.put(ImageType.CRUNCH, loadImage("img/CRUNCH.png"));
    }

    /**
     * Takes a file path leading to an image and converts it to an ImageView
     * object
     *
     * @param imagePath file path leading to an object
     * @return an ImageView object
     *
     * @author lts010
     */
    public static ImageView loadImage(String imagePath) {
        imagePath = new File(imagePath).toURI().toString();
        Image image = new Image(imagePath);
        return new ImageView(image);
    }

    /**
     * Loads an action bubble on the screen and displays a string
     *
     * @param defaultBubble the bubble that will be displayed if the string
     * doesn't meet the criteria for other bubbles
     * @param action the string to be displayed
     * @return an ImageView object of the bubble
     *
     * @author lts010
     */
    public ImageView handleActionBubble(ImageType defaultBubble, String action) {
        ImageView imageView;
        System.out.println("epic");
        if (action.contains("missed and did no damage") || action.contains(
                "did 0 damage")) {
            imageView = this.imageViews.get(RPGView.ImageType.WHIFF);
        }
        else if (action.contains("Critical Hit!")) {
            imageView = this.imageViews.get(RPGView.ImageType.CRUNCH);
        }
        else {
            imageView = this.imageViews.get(defaultBubble);
        }
        this.centerPane.getChildren().add(imageView);
        imageView.setX(this.centerPane.getWidth() / 4);
        imageView.setY(this.centerPane.getHeight() / 6);
        this.storyTextOutput.setText(action);
        return (imageView);
    }
}
