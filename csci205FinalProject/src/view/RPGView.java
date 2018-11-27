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
import java.util.Random;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.RPGModel;
import model.item.ItemType;
import view.wrapper.ItemImageViewWrapper;

/**
 * RPGView serves as the view for the RPG game application.
 *
 * @author ks061
 */
public class RPGView {

    /**
     * ImageType represents the buttons and action bubbles that appear 
     * on the screen. Image types representing game controls are listed 
     * in the first group of enumeration elements.
     * 
     * @author lts010, ks061
     */
    public enum ImageType {
        INVENTORY, ATTACK, SEARCH, TRADE,
        POW, BAM, WHIFF, CRUNCH,
        UPARROW, DOWNARROW, LEFTARROW, RIGHTARROW,
        
        WEAPON1, WEAPON2, WEAPON3,
        SHIELD1, SHIELD2, SHIELD3,
        ARMOR1, ARMOR2, ARMOR3;
    };

    /*
     * Models for the application
     */
    private RPGModel theModel;
    /*
     * Root node on the stage in the GUI
     */
    private BorderPane root;

    /*
     * topPane
     * -Variables involved in the top pane of the border pane root
     * -Contains the name of the current room the player is in (i.e. BRKI 164)
     */
    //private FlowPane topPane;
    //private Label roomNameLabel;
    //private Label roomName;

    /*
     * leftPane
     * -Variables involved in the left pane of the border pane root
     * -Contains the player's stats (health, attack, and armor)
     * -Also contains the player's current equipment (weapon, armor, shield)
     */
//    private VBox leftPane;
//    private Label playerStatLabel;
//    private Label playerHealthLabel;
//    private Text playerHealth;
//    private Label playerAttackLabel;
//    private Text playerAttack;
//    private Label playerDefenseLabel;
//    private Text playerDefense;
//    public Text playerShield;
//    public Text playerArmor;
//    public Text playerWeapon;
//    public Label ShieldLabel;
//    public Label ArmorLabel;
//    public Label WeaponLabel;
//    public Label EquipmentLabel;

    /*
     * rightPane
     * -Variables involved in the left pane of the border pane root
     * -Contains buttons that the player can click to perform different actions
     */
    private VBox rightPane;
    private Button buttonEquipment;
    private Button buttonItem;
    /*
     * Buttons are stored in a BorderPane for organizational purposes.
     * These buttons make the player go to different rooms in the north, east,
     * west, and south of its current location, so we chose this instead of
     * putting everything in, for example, an HBox.
     */
    private BorderPane toRoomButtons;
    /*
     * Buttons starting with "to" moves the player to another adjacent room.
     * Buttons starting with null are there as a filler for the "to" buttons
     * because we don't want players moving to the left if they are already on
     * the left edge of the map.
     */
//    private Button toRoomAbove;
//    private Button toRoomToLeft;
//    private Button toRoomToRight;
//    private Button toRoomBelow;


    /*
     * bottomHBox
     * -Has ImageViews to let the user control the action.
     * -Contains the reference point for the model to output the plot line
     */
    private HBox bottomHBox;
    private Text storyText;
    private FlowPane storyTextFP;

    /*
     * This pane is in the center and contain only items that will change when
     * the player moves between rooms.
     */
    private Pane centerPane;

    /*
     * This pane is in the center behind the center pane. It will  only items
     * that don't change when the player moves between rooms.
     */
    private StackPane centerBackPane;

    /*
     * This GridPane will contain the healh, strength and status bars on the
     * left for the player and will have the same bars on the right for the NPC
     * Should be invisible if there is no NPC to fight in the room.
     */
    private GridPane statusGridPane;

    /*
     * Create the status bars to put into the statusGridPane
     */
    private StatusBar playerHealthBar;
    private StatusBar playerStrengthBar;
    private StatusBar playerDefenseBar;
    private StatusBar nPCHealthBar;
    private StatusBar nPCStrengthBar;
    private StatusBar nPCDefenseBar;
    private Text statusRoomName;

    /**
     * The map that stores all the ItemImageViewWrapper objects using the
     * ImageType enum as a key
     */
    private EnumMap<ImageType, ItemImageViewWrapper> imageViews;

    /*
     * Random number generator so we can place objects at random place in room.
     */
    private static Random random;

    /*
     * Static finals for the view
     * Recommended by Prof. Dancy :)
     */
    private static final int PREF_WINDOW_WIDTH = 1600;
    private static final int PREF_WINDOW_HEIGHT = 1000;
    private static final int PREF_PADDING = 15;
    //  private static final double TRAVEL_BUTTON_WIDTH = 57.0;
    //  private static final double RIGHT_PANE_MIN_WIDTH = 2 * TRAVEL_BUTTON_WIDTH;
    private static final int PREF_CENTER_PANE_WIDTH = 1400;
    private static final int PREF_CENTER_PANE_HEIGHT = 800;
    // private static final int PREF_SIDE_PANES_WIDTH = 100;
    private static final int ROOM_NAME_FONT_SIZE = 26;
    private static final int STORY_TEXT_FONT_SIZE = 16;
    private static final int PREF_GAP = 7;

    /**
     * Constructor that initializes a pointer to the model of the application
     * and initializes components of the GUI
     *
     * @param theModel pointer to the model of the application
     *
     * @author ks061, ishk001, lts010
     */
    public RPGView(RPGModel theModel) {
        this.theModel = theModel;
        this.random = new Random();
        /*
         * BorderPane created
         * -set preferred height and width as well as padding
         */
        this.root = new BorderPane();
        this.root.setPrefWidth(PREF_WINDOW_WIDTH);
        this.root.setPrefHeight(PREF_WINDOW_HEIGHT);
        this.root.setPadding(new Insets(PREF_PADDING));

        /*
         * TopPane created
         * -Labels to display the room name has been added
         * -Alignment has been set to center
         */
        //this.topPane = new FlowPane(Orientation.HORIZONTAL);
        //this.roomNameLabel = new Label(String.format("%s is in ",
        //                                           this.theModel.getPlayer().getName()));
        //this.roomName = new Label(theModel.getCurrentRoom().getName());
        //this.topPane.getChildren().add(roomNameLabel);
        //this.topPane.getChildren().add(roomName);
        //this.topPane.setAlignment(Pos.CENTER);

        /*
         * LeftPane created
         * -Labels for the player's stats have been added
         * -Alignment has been set to center
         */
//        this.leftPane = new VBox();
//        this.leftPane.setSpacing(PREF_PADDING);
//        this.leftPane.setPrefWidth(PREF_SIDE_PANES_WIDTH);
//        this.playerStatLabel = new Label("Player Status");
//        this.playerHealthLabel = new Label("Health: ");
//        this.playerHealth = new Text();
//        this.playerAttackLabel = new Label("Attack: ");
//        this.playerAttack = new Text();
//        this.playerDefenseLabel = new Label("Defense: ");
//        this.playerDefense = new Text();
//
//        EquipmentLabel = new Label("Equipment");
//        WeaponLabel = new Label("Your Weapon:");
//        ArmorLabel = new Label("Your Armor:");
//        ShieldLabel = new Label("Your Shield:");
//        playerWeapon = new Text();
//        playerArmor = new Text();
//        playerShield = new Text();
//        this.leftPane.getChildren().add(new FlowPane(playerStatLabel));
//        this.leftPane.getChildren().add(new FlowPane(playerHealthLabel,
//                                                     playerHealth));
//        this.leftPane.getChildren().add(new FlowPane(playerAttackLabel,
//                                                     playerAttack));
//        this.leftPane.getChildren().add(new FlowPane(playerDefenseLabel,
//                                                     playerDefense));
//        this.leftPane.getChildren().add(new FlowPane());
//        this.leftPane.getChildren().add(new FlowPane(EquipmentLabel));
//        this.leftPane.getChildren().add(new FlowPane(WeaponLabel));
//        this.leftPane.getChildren().add(new FlowPane(playerWeapon));
//        this.leftPane.getChildren().add(new FlowPane(ArmorLabel));
//        this.leftPane.getChildren().add(new FlowPane(playerArmor));
//        this.leftPane.getChildren().add(new FlowPane(ShieldLabel));
//        this.leftPane.getChildren().add(new FlowPane(playerShield));
//        this.leftPane.setAlignment(Pos.CENTER_LEFT);

        /*
         * RightPane created
         * -Alignment has been set to center
         */
//        this.rightPane = new VBox();
//        this.rightPane.setSpacing(PREF_PADDING);
//        this.rightPane.setPrefWidth(PREF_SIDE_PANES_WIDTH);
//        this.buttonEquipment = new Button("Equipment");
//        this.buttonItem = new Button("Item");
//        this.rightPane.getChildren().add(this.buttonEquipment);
//        this.rightPane.getChildren().add(this.buttonItem);

        /*
         * Button settings
         * -Width of the buttons have been set to constants because RightPane would
         * automatically update the width and be bothersome to the players
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
//        this.rightPane.setAlignment(Pos.CENTER);
//        this.rightPane.setMinWidth(RIGHT_PANE_MIN_WIDTH);
        /*
         * BottomHBox created
         * -Spaces to dispaly the story in text form
         *  and ImageView controls
         *
         */
//  HBox.setHgrow(storyTextFP, Priority.ALWAYS);
        this.bottomHBox = new HBox();
        bottomHBox.setPrefWidth(PREF_CENTER_PANE_WIDTH);
        bottomHBox.setAlignment(Pos.CENTER_RIGHT);
        bottomHBox.setSpacing(PREF_GAP);
        //bottomHBox.setPadding(new INSERT);
        this.storyTextFP = new FlowPane();
        this.storyText = new Text(" ");
        storyText.setFont(Font.font(STORY_TEXT_FONT_SIZE));
        storyTextFP.setAlignment(Pos.CENTER_LEFT);
        storyTextFP.getChildren().add(this.storyText);
        storyTextFP.setAlignment(Pos.CENTER);
        this.bottomHBox.getChildren().add(this.storyTextFP);
         
        // Creates status bars and labels
        playerHealthBar = new StatusBar("img/redBall25.png", "img/redBall15.png", true);
        playerStrengthBar = new StatusBar("img/attack25.png", "img/attack15.png", true);
        playerDefenseBar = new StatusBar("img/shield25.png", "img/shield15.png", true);
        nPCHealthBar = new StatusBar("img/redBall25.png", "img/redBall15.png", false);
        nPCStrengthBar = new StatusBar("img/attack25.png", "img/attack15.png", false);
        nPCDefenseBar = new StatusBar("img/shield25.png", "img/shield15.png", false);
        statusRoomName = new Text("Room Name");
        statusRoomName.setFont(Font.font(ROOM_NAME_FONT_SIZE));

        // Creates and populates the grid panes containing status bars for both
        // the player and NPC
        GridPane.setHgrow(statusRoomName, Priority.ALWAYS);
        GridPane.setHalignment(statusRoomName, HPos.CENTER);
        this.statusGridPane = new GridPane();
        statusGridPane.setVgap(PREF_GAP);
        statusGridPane.add(playerHealthBar, 0, 0);
        statusGridPane.add(playerStrengthBar, 0, 1);
        statusGridPane.add(playerDefenseBar, 0, 2);
        statusGridPane.add(statusRoomName, 1, 0, 1, 2);
        statusGridPane.add(nPCHealthBar, 2, 0);
        statusGridPane.add(nPCStrengthBar, 2, 1);
        statusGridPane.add(nPCDefenseBar, 2, 2);
        
        // Center panes and back center panes created
        this.centerPane = new Pane();
        this.centerPane.setMinHeight(PREF_CENTER_PANE_HEIGHT);
        this.centerPane.setMinWidth(PREF_CENTER_PANE_WIDTH);
        this.centerBackPane = new StackPane();
        this.centerBackPane.setMinHeight(PREF_CENTER_PANE_HEIGHT);
        this.centerBackPane.setMinWidth(PREF_CENTER_PANE_WIDTH);
        this.loadImages();
        
        centerBackPane.getChildren().add(this.statusGridPane);
        this.centerBackPane.getChildren().add(centerPane);


        // All elements added to the root node
        this.root.setCenter(this.centerBackPane);
        //this.root.setTop(this.topPane);
        //this.root.setLeft(this.leftPane);
        this.root.setRight(this.rightPane);
        this.root.setBottom(this.bottomHBox);
        BorderPane.setAlignment(this.centerBackPane, Pos.CENTER_LEFT);
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

    /*
     *  Makes the StatusBars for the NPC visable
     *
     * @author lts010
     */
    public void makeNPCStatusVisable() {
        this.nPCHealthBar.setVisible(true);
        this.nPCStrengthBar.setVisible(true);
        this.nPCDefenseBar.setVisible(true);
    }

    /*
     *  Makes the StatusBars for the NPC invisable
     *  The StatusBars should be invisible when there is no NPC to fight
     *  in the room.
     *
     * @author lts010
     */
    public void makeNPCStatusInvisable() {
        this.nPCHealthBar.setVisible(false);
        this.nPCStrengthBar.setVisible(false);
        this.nPCDefenseBar.setVisible(false);
    }

    /*
     *  Set the name of the roow will be displayed in the StatusGrid
     *
     * @author lts010
     */
    public void setStatusRoomNameText(String roomName) {
        this.statusRoomName.setText(roomName);
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
//    public Button getToRoomAbove() {
//        return toRoomAbove;
//    }
    /**
     * Gets the button to go to the room below the current one
     *
     * @return button to go to the room below the current one
     *
     * @author ks061
     */
//    public Button getToRoomBelow() {
//        return toRoomBelow;
//    }
    /**
     * Gets the button to go to the room to the left of the current one
     *
     * @return button to go to the room to the left of the current one
     *
     * @author ks061
     */
//    public Button getToRoomToLeft() {
//        return toRoomToLeft;
//    }
    /**
     * Gets the button to go to the room to the right of the current one
     *
     * @return button to go to the room to the right of the current one
     *
     * @author ks061
     */
//    public Button getToRoomToRight() {
//        return toRoomToRight;
//    }
    /**
     * Gets the pane of travel buttons
     *
     * @return pane of travel buttons
     *
     * @author ks061, ishk001
     */
//    public BorderPane getToRoomButtonsDisplay() {
//        return toRoomButtons;
//    }
    /**
     * Creates and returns an empty button to replace a travel button
     *
     * @return empty button to replace a travel button
     *
     * @author ks061
     */
//    public Button getNewNullButton() {
//        Button newNullButton = new Button("     ");
//        newNullButton.setMinWidth(TRAVEL_BUTTON_WIDTH);
//        return newNullButton;
//    }
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
    public void updateStoryText(String dialog) {
        this.storyText.setText(dialog);
    }

    /**
     * Updates the room name based on the current room
     *
     * @author ks061
     */
    public void refreshRoomName() {
        this.setStatusRoomNameText(this.theModel.getCurrentRoom().getName());
    }

//    /**
//     * Gets the text representing the player's current health
//     *
//     * @return the text representing the player's current health
//     *
//     * @author lts010
//     */
//    public Text getPlayerHealth() {
//        return playerHealth;
//    }
//
//    /**
//     * Gets the text representing the player's attack
//     *
//     * @return the text representing the player's attack
//     *
//     * @author lts010
//     */
//    public Text getPlayerAttack() {
//        return playerAttack;
//    }
//
//    /**
//     * Gets the text representing the player's defense
//     *
//     * @return the text representing the player's defense
//     *
//     * @author lts010
//     */
//    public Text getPlayerDefense() {
//        return playerDefense;
//    }
//
//    /**
//     * Gets the text representing the player's shield
//     *
//     * @return the text representing the player's defense
//     *
//     * @author lts010
//     */
//    public Text getPlayerShield() {
//        return playerShield;
//    }
//
//    /**
//     * Gets the text representing the player's armor
//     *
//     * @return the text representing the player's defense
//     *
//     * @author lts010
//     */
//    public Text getPlayerArmor() {
//        return playerArmor;
//    }
//
//    /**
//     * Gets the text representing the player's weapon
//     *
//     * @return the text representing the player's defense
//     *
//     * @author lts010
//     */
//    public Text getPlayerWeapon() {
//        return playerWeapon;
//    }
    /**
     * Gets the image views
     *
     * @return the image views
     *
     * @author lts010
     */
    public EnumMap<ImageType, ItemImageViewWrapper> getImageViews() {
        return imageViews;
    }

    /**
     * Gets the bottom pane
     *
     * @return the bottom pane
     *
     * @author lts010
     */
    public HBox getBottomHBox() {
        return bottomHBox;
    }

    /**
     * Adds many imageView objects to the EnumMap imageViews
     *
     * @author lts010
     */
    public void loadImages() {
        this.imageViews = new EnumMap<ImageType, ItemImageViewWrapper>(
                ImageType.class);
        this.imageViews.put(ImageType.UPARROW, loadImage("img/arrow-up.png",
                                                         ItemType.CONTROL,
                                                         ImageType.UPARROW));
        this.imageViews.put(ImageType.DOWNARROW, loadImage("img/arrow-down.png",
                                                           ItemType.CONTROL,
                                                           ImageType.DOWNARROW));
        this.imageViews.put(ImageType.LEFTARROW, loadImage("img/arrow-left.png",
                                                           ItemType.CONTROL,
                                                           ImageType.LEFTARROW));
        this.imageViews.put(ImageType.RIGHTARROW, loadImage(
                            "img/arrow-right.png", ItemType.CONTROL,
                            ImageType.RIGHTARROW));
        this.imageViews.put(ImageType.INVENTORY, loadImage("img/inventory.png",
                                                           ItemType.CONTROL,
                                                           ImageType.INVENTORY));
        this.imageViews.put(ImageType.ATTACK, loadImage("img/attack.png",
                                                        ItemType.CONTROL,
                                                        ImageType.ATTACK));
        this.imageViews.put(ImageType.SEARCH, loadImage("img/search.png",
                                                        ItemType.CONTROL,
                                                        ImageType.SEARCH));
        this.imageViews.put(ImageType.TRADE, loadImage("img/trade.png",
                                                       ItemType.CONTROL,
                                                       ImageType.TRADE));
        this.imageViews.put(ImageType.POW, loadImage("img/POW.png",
                                                     ItemType.CONTROL,
                                                     ImageType.POW));
        this.imageViews.put(ImageType.BAM, loadImage("img/BAM.png",
                                                     ItemType.CONTROL,
                                                     ImageType.BAM));
        this.imageViews.put(ImageType.WHIFF, loadImage("img/WHIFF.png",
                                                       ItemType.CONTROL,
                                                       ImageType.WHIFF));
        this.imageViews.put(ImageType.CRUNCH, loadImage("img/CRUNCH.png",
                                                        ItemType.CONTROL,
                                                        ImageType.CRUNCH));
        this.imageViews.put(ImageType.SWORD, loadImage("img/SWORD.png",
                                                       ItemType.WEAPON,
                                                       ImageType.SWORD));
        this.imageViews.put(ImageType.SHIELD, loadImage("img/SHIELD.png",
                                                        ItemType.SHIELD,
                                                        ImageType.SHIELD));
    }

    /**
     * Takes a file path leading to an image and converts it to an ImageView
     * object
     *
     * @param imagePath file path leading to an object
     * @param itemType type of item
     * @param imageType type of image
     * @return an ImageView wrapper object
     *
     * @author lts010, ks061
     */
    public static ItemImageViewWrapper loadImage(String imagePath,
                                                 ItemType itemType,
                                                 ImageType imageType) {
        imagePath = new File(imagePath).toURI().toString();
        Image image = new Image(imagePath);
        Point2D location = new Point2D(random.nextInt(1000) + 200,
                                       random.nextInt(400) + 250);
        return new ItemImageViewWrapper(image, location, itemType, imageType);
    }

    /**
     * Loads an action bubble on the screen and displays a string
     *
     * @param defaultBubble the bubble that will be displayed if the string
     * doesn't meet the criteria for other bubbles
     * @param action the string to be displayed
     * @return an ImageView object of the bubble
     *
     * @author lts010, ks061
     */
    public ImageView handleActionBubble(ImageType defaultBubble, String action) {
        ImageView imageView;
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
        this.storyText.setText(action);
        return (imageView);
    }
}
