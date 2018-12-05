/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Kartikeya Sharma
 * Date: Oct 19, 2018
 * Time: 7:18:02 PM
 *
 * Project: csci205
 * Package: view
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
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.RPGModel;
import model.data.Properties;
import model.item.ItemType;
import utility.RPGUtility;
import view.wrapper.ItemImageViewWrapper;

/**
 * RPGView serves as the view for the RPG game application.
 *
 * @author ks061
 */
public class RPGView {

    /**
     * Model for the application
     */
    private final RPGModel theModel;
    /**
     * Root node on the stage in the GUI
     */
    private final BorderPane root;
    /**
     * Room name display
     */
    private final Text roomNameDisplay;
    /**
     * Associates an ImageKey enum element as a key to a ItemImageViewWrapper
     * value
     */
    private final EnumMap<ImageKey, ItemImageViewWrapper> imageKeytoItemImageMap;

    /**
     * Contains items that will change in the game scene view when the player
     * moves between rooms.
     */
    private final Pane centerPane;
    /**
     * Contains items that will not change in the game scene view when the
     * player moves between rooms.
     */
    private final StackPane centerBackPane;

    /**
     * This "status grid pane" contains the health, strength, and defense status
     * bars on the left for the player and another set on the right for the NPC.
     */
    private final GridPane statusGridPane;
    /**
     * Player health bar within statusGridPane
     */
    private final StatusBar playerHealthBar;
    /**
     * Player strength bar within statusGridPane
     */
    private final StatusBar playerStrengthBar;
    /**
     * Player defense bar within statusGridPane
     */
    private final StatusBar playerDefenseBar;
    /**
     * NPC health bar within statusGridPane
     */
    private final StatusBar npcHealthBar;
    /**
     * NPC strength bar within statusGridPane
     */
    private final StatusBar npcStrengthBar;
    /**
     * NPC defense bar within statusGridPane
     */
    private final StatusBar npcDefenseBar;

    /**
     * Contains clickable icons for game actions and an area where the game plot
     * line will be displayed
     */
    private final HBox bottomHBox;
    /**
     * Contains game plot line
     */
    private final FlowPane storyTextFP;
    /**
     * Contains text of game plot line
     */
    private final Text storyText;
    /**
     * Contains images of the items in the player's inventory
     */
    private FlowPane inventoryFP;

    /*
     * Random number generator so we can place objects at random place in room.
     */
    private final Random random;

    /**
     * Preferred window width
     */
    private static final int PREF_WINDOW_WIDTH = 1200;
    /**
     * Preferred window height
     */
    private static final int PREF_WINDOW_HEIGHT = 750;
    /**
     * Preferred window padding
     */
    private static final int PREF_PADDING = 15;
    /**
     * Preferred width for the center pane
     */
    private static final int PREF_CENTER_PANE_WIDTH = 1400;
    /**
     * Preferred height for the center pane
     */
    private static final int PREF_CENTER_PANE_HEIGHT = 800;
    /**
     * Preferred gap between adjacent nodes
     */
    private static final int PREF_GAP = 7;
    /**
     * Font size for the room name display
     */
    private static final int ROOM_NAME_FONT_SIZE = 26;
    /**
     * Font size for the game plot line display
     */
    private static final int STORY_TEXT_FONT_SIZE = 16;
    /**
     * Preferred width for the inventory
     */
    private static final int INVENTORY_PREF_WIDTH = 400;
    /**
     * Preferred height for the inventory
     */
    private static final int INVENTORY_PREF_HEIGHT = 20;

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
        // Importing the images to use in the view
        this.imageKeytoItemImageMap = new EnumMap<>(
                ImageKey.class);
        initImageKeyToItemImageMap();

        // Initializing the root
        this.root = new BorderPane();
        this.root.setPrefWidth(PREF_WINDOW_WIDTH);
        this.root.setPrefHeight(PREF_WINDOW_HEIGHT);
        this.root.setPadding(new Insets(PREF_PADDING));

        // Story and action icons bar
        this.bottomHBox = new HBox();
        initBottomHBox();
        this.storyTextFP = new FlowPane();
        this.bottomHBox.getChildren().add(this.storyTextFP);
        this.storyText = new Text(" ");
        initStoryText();
        this.storyTextFP.getChildren().add(this.storyText);
        initActionIcons();

        // Status bar
        this.playerHealthBar = new StatusBar(
                Properties.ONE_HEALTH_ICON_FILE_PATH,
                Properties.TEN_HEALTH_ICON_FILE_PATH,
                true);
        this.playerStrengthBar = new StatusBar(
                Properties.ONE_STRENGTH_ICON_FILE_PATH,
                Properties.TEN_STRENGTH_ICON_FILE_PATH,
                true);
        this.playerDefenseBar = new StatusBar(
                Properties.ONE_DEFENSE_ICON_FILE_PATH,
                Properties.TEN_DEFENSE_ICON_FILE_PATH,
                true);
        this.npcHealthBar = new StatusBar(Properties.ONE_HEALTH_ICON_FILE_PATH,
                                          Properties.TEN_HEALTH_ICON_FILE_PATH,
                                          false);
        this.npcStrengthBar = new StatusBar(
                Properties.ONE_STRENGTH_ICON_FILE_PATH,
                Properties.TEN_STRENGTH_ICON_FILE_PATH,
                false);
        this.npcDefenseBar = new StatusBar(Properties.ONE_DEFENSE_ICON_FILE_PATH,
                                           Properties.TEN_DEFENSE_ICON_FILE_PATH,
                                           false);

        this.roomNameDisplay = new Text(theModel.getCurrentRoom().getName());
        this.roomNameDisplay.setFont(Font.font(ROOM_NAME_FONT_SIZE));

        // Center pane
        this.centerPane = new Pane();

        this.statusGridPane = new GridPane();
        initStatusGridPane();
        this.inventoryFP = new FlowPane();

        this.inventoryFP.setPrefWidth(INVENTORY_PREF_WIDTH);
        this.inventoryFP.setPrefHeight(INVENTORY_PREF_HEIGHT);
        inventoryFP.setBorder(new Border(new BorderStroke(Color.BLACK,
                                                          BorderStrokeStyle.SOLID,
                                                          CornerRadii.EMPTY,
                                                          BorderWidths.DEFAULT)));
        inventoryFP.setBackground(new Background(new BackgroundFill(Color.WHITE,
                                                                    CornerRadii.EMPTY,
                                                                    Insets.EMPTY)));
        this.inventoryFP.setPadding(new Insets(PREF_PADDING));
        toggleInventoryFP();
        VBox vBox = new VBox();
        Region growRegion = new Region();
        vBox.setPrefHeight(PREF_CENTER_PANE_HEIGHT);
        vBox.alignmentProperty().setValue(Pos.BOTTOM_LEFT);
        vBox.getChildren().add(growRegion);
        vBox.getChildren().add(inventoryFP);
        VBox.setVgrow(growRegion, Priority.ALWAYS);

        this.centerPane.getChildren().add(vBox);

        this.centerBackPane = new StackPane();
        formatCenterPane();

        this.centerBackPane.getChildren().add(this.statusGridPane);
        this.centerBackPane.getChildren().add(this.centerPane);

        // All elements added to the root node
        this.root.setCenter(this.centerBackPane);
        this.root.setBottom(this.bottomHBox);
        BorderPane.setAlignment(this.centerBackPane, Pos.CENTER_LEFT);
    }

    /**
     * Initializes the story text
     *
     * @author ks061
     */
    private void initStoryText() {
        this.storyText.setFont(Font.font(STORY_TEXT_FONT_SIZE));
        this.storyTextFP.setAlignment(Pos.CENTER);
    }

    /**
     * Initializes the bottom HBox
     *
     * @author ks061
     */
    private void initBottomHBox() {
        bottomHBox.setPrefWidth(PREF_CENTER_PANE_WIDTH);
        bottomHBox.setAlignment(Pos.CENTER_RIGHT);
        bottomHBox.setSpacing(PREF_GAP);
    }

    /**
     * Initializes the game action icons
     *
     * @author ks061
     */
    private void initActionIcons() {
        this.bottomHBox.getChildren().add(this.imageKeytoItemImageMap.get(
                ImageKey.INVENTORY));
        this.bottomHBox.getChildren().add(this.imageKeytoItemImageMap.get(
                ImageKey.SEARCH));
        this.bottomHBox.getChildren().add(this.imageKeytoItemImageMap.get(
                ImageKey.TRADE));
        this.bottomHBox.getChildren().add(this.imageKeytoItemImageMap.get(
                ImageKey.ATTACK));
    }

    /**
     * Initializes the status grid pane
     *
     * @author ks061
     */
    private void initStatusGridPane() {
        GridPane.setHgrow(this.roomNameDisplay, Priority.ALWAYS);
        GridPane.setHalignment(this.roomNameDisplay, HPos.CENTER);
        this.statusGridPane.setVgap(PREF_GAP);
        this.statusGridPane.add(this.playerHealthBar, 0, 0);
        this.statusGridPane.add(this.playerStrengthBar, 0, 1);
        this.statusGridPane.add(this.playerDefenseBar, 0, 2);
        this.statusGridPane.add(this.roomNameDisplay, 1, 0, 1, 2);
        this.statusGridPane.add(this.npcHealthBar, 2, 0);
        this.statusGridPane.add(this.npcStrengthBar, 2, 1);
        this.statusGridPane.add(this.npcDefenseBar, 2, 2);
    }

    /**
     * Formats and aligns the center panes
     *
     * @author ks061
     */
    private void formatCenterPane() {
        this.centerPane.setMinHeight(PREF_CENTER_PANE_HEIGHT);
        this.centerPane.setMinWidth(PREF_CENTER_PANE_WIDTH);
        this.centerBackPane.setMinHeight(PREF_CENTER_PANE_HEIGHT);
        this.centerBackPane.setMinWidth(PREF_CENTER_PANE_WIDTH);
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
     * Makes the status bars for the NPC visible
     *
     * @author lts010, ks061
     */
    public void makeNPCStatusVisible() {
        this.npcHealthBar.setVisible(true);
        this.npcStrengthBar.setVisible(true);
        this.npcDefenseBar.setVisible(true);
    }

    /**
     * Makes the status bars for the NPC invisible
     *
     * @author lts010, ks061
     */
    public void makeNPCStatusInvisible() {
        this.npcHealthBar.setVisible(false);
        this.npcStrengthBar.setVisible(false);
        this.npcDefenseBar.setVisible(false);
    }

    /**
     * Set the name of the room will be displayed in the StatusGrid
     *
     * @param roomName room name
     *
     * @author lts010, ks061
     */
    public void setStatusRoomNameText(String roomName) {
        this.roomNameDisplay.setText(roomName);
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
    public void setStoryText(String dialog) {
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

    /**
     * Gets the image views
     *
     * @return the image views
     *
     * @author lts010
     */
    public EnumMap<ImageKey, ItemImageViewWrapper> getImageViews() {
        return imageKeytoItemImageMap;
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
     * Adds many imageView objects to the EnumMap imageKeytoItemImageMap
     *
     * @author lts010, ks061
     */
    private void initImageKeyToItemImageMap() {
        this.imageKeytoItemImageMap.put(ImageKey.UPARROW,
                                        RPGUtility.loadImage(
                                                Properties.UP_ARROW_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.UPARROW));
        this.imageKeytoItemImageMap.put(ImageKey.DOWNARROW,
                                        RPGUtility.loadImage(
                                                Properties.DOWN_ARROW_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.DOWNARROW));
        this.imageKeytoItemImageMap.put(ImageKey.LEFTARROW,
                                        RPGUtility.loadImage(
                                                Properties.LEFT_ARROW_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.LEFTARROW));
        this.imageKeytoItemImageMap.put(ImageKey.RIGHTARROW,
                                        RPGUtility.loadImage(
                                                Properties.RIGHT_ARROW_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.RIGHTARROW));
        this.imageKeytoItemImageMap.put(ImageKey.INVENTORY,
                                        RPGUtility.loadImage(
                                                Properties.INVENTORY_ICON_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.INVENTORY));
        this.imageKeytoItemImageMap.put(ImageKey.ATTACK,
                                        RPGUtility.loadImage(
                                                Properties.ATTACK_ICON_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.ATTACK));
        this.imageKeytoItemImageMap.put(ImageKey.SEARCH,
                                        RPGUtility.loadImage(
                                                Properties.SEARCH_ICON_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.SEARCH));
        this.imageKeytoItemImageMap.put(ImageKey.TRADE,
                                        RPGUtility.loadImage(
                                                Properties.TRADE_ICON_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.TRADE));
        this.imageKeytoItemImageMap.put(ImageKey.POW, RPGUtility.loadImage(
                                        Properties.POW_IMG_FILE_PATH,
                                        ItemType.CONTROL,
                                        ImageKey.POW));
        this.imageKeytoItemImageMap.put(ImageKey.BAM, RPGUtility.loadImage(
                                        Properties.BAM_IMG_FILE_PATH,
                                        ItemType.CONTROL,
                                        ImageKey.BAM));
        this.imageKeytoItemImageMap.put(ImageKey.WHIFF,
                                        RPGUtility.loadImage(
                                                Properties.WHIFF_IMG_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.WHIFF));
        this.imageKeytoItemImageMap.put(ImageKey.CRUNCH,
                                        RPGUtility.loadImage(
                                                Properties.CRUNCH_IMG_FILE_PATH,
                                                ItemType.CONTROL,
                                                ImageKey.CRUNCH));
        this.imageKeytoItemImageMap.put(ImageKey.NACHO_TOTS,
                                        RPGUtility.loadImage(
                                                Properties.NACHO_TOTS_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.NACHO_TOTS));
        this.imageKeytoItemImageMap.put(ImageKey.OREO_MILKSHAKE,
                                        RPGUtility.loadImage(
                                                Properties.OREO_MILKSHAKE_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.OREO_MILKSHAKE));
        this.imageKeytoItemImageMap.put(ImageKey.RAMEN,
                                        RPGUtility.loadImage(
                                                Properties.RAMEN_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.RAMEN));
        this.imageKeytoItemImageMap.put(ImageKey.PEN_AND_PAPER,
                                        RPGUtility.loadImage(
                                                Properties.WEAPON1_IMG_FILE_PATH,
                                                ItemType.WEAPON,
                                                ImageKey.PEN_AND_PAPER));
        this.imageKeytoItemImageMap.put(ImageKey.NOTEPAD,
                                        RPGUtility.loadImage(
                                                Properties.WEAPON2_IMG_FILE_PATH,
                                                ItemType.WEAPON,
                                                ImageKey.NOTEPAD));
        this.imageKeytoItemImageMap.put(ImageKey.NETBEANS,
                                        RPGUtility.loadImage(
                                                Properties.WEAPON3_IMG_FILE_PATH,
                                                ItemType.WEAPON,
                                                ImageKey.NETBEANS));
        this.imageKeytoItemImageMap.put(ImageKey.API,
                                        RPGUtility.loadImage(
                                                Properties.ARMOR1_IMG_FILE_PATH,
                                                ItemType.ARMOR,
                                                ImageKey.API));
        this.imageKeytoItemImageMap.put(ImageKey.STACK_OVERFLOW,
                                        RPGUtility.loadImage(
                                                Properties.ARMOR2_IMG_FILE_PATH,
                                                ItemType.ARMOR,
                                                ImageKey.STACK_OVERFLOW));
        this.imageKeytoItemImageMap.put(ImageKey.WINKLEVOSS_TWINS,
                                        RPGUtility.loadImage(
                                                Properties.ARMOR3_IMG_FILE_PATH,
                                                ItemType.ARMOR,
                                                ImageKey.WINKLEVOSS_TWINS));
        this.imageKeytoItemImageMap.put(ImageKey.MACHINE_CODE,
                                        RPGUtility.loadImage(
                                                Properties.SHIELD1_IMG_FILE_PATH,
                                                ItemType.SHIELD,
                                                ImageKey.MACHINE_CODE));
        this.imageKeytoItemImageMap.put(ImageKey.HTML,
                                        RPGUtility.loadImage(
                                                Properties.SHIELD2_IMG_FILE_PATH,
                                                ItemType.SHIELD,
                                                ImageKey.HTML));
        this.imageKeytoItemImageMap.put(ImageKey.JAVA,
                                        RPGUtility.loadImage(
                                                Properties.SHIELD3_IMG_FILE_PATH,
                                                ItemType.SHIELD,
                                                ImageKey.JAVA));

        this.imageKeytoItemImageMap.put(ImageKey.BOOGER,
                                        RPGUtility.loadImage(
                                                Properties.BOOGER_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.BOOGER));
        this.imageKeytoItemImageMap.put(ImageKey.NUGGET,
                                        RPGUtility.loadImage(
                                                Properties.GOLD_NUGGET_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.NUGGET));
        this.imageKeytoItemImageMap.put(ImageKey.TIME,
                                        RPGUtility.loadImage(
                                                Properties.TIME_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.TIME));
        this.imageKeytoItemImageMap.put(ImageKey.NACHO_TOTS,
                                        RPGUtility.loadImage(
                                                Properties.NACHO_TOTS_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.NACHO_TOTS));
        this.imageKeytoItemImageMap.put(ImageKey.OREO_MILKSHAKE,
                                        RPGUtility.loadImage(
                                                Properties.OREO_MILKSHAKE_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.OREO_MILKSHAKE));
        this.imageKeytoItemImageMap.put(ImageKey.RAMEN,
                                        RPGUtility.loadImage(
                                                Properties.RAMEN_IMG_FILE_PATH,
                                                ItemType.CONSUMABLE,
                                                ImageKey.RAMEN));

    }

    /**
     * Loads an action bubble on the screen and displays a string
     *
     * @param defaultBubble the bubble that will be displayed if the string does
     * not meet the criteria for other bubbles
     * @param action the string to be displayed
     * @return an ImageView object of the bubble
     *
     * @author lts010, ks061
     */
    public ImageView handleAttackDisplay(ImageKey defaultBubble, String action) {
        ImageView imageView;
        if (action.contains("missed and did no damage") || action.contains(
                "did 0 damage")) {
            imageView = this.imageKeytoItemImageMap.get(ImageKey.WHIFF);
        }
        else if (action.contains("Critical Hit!")) {
            imageView = this.imageKeytoItemImageMap.get(ImageKey.CRUNCH);
        }
        else {
            imageView = this.imageKeytoItemImageMap.get(defaultBubble);
        }
        this.centerPane.getChildren().add(imageView);
        imageView.setX(this.centerPane.getWidth() / 4);
        imageView.setY(this.centerPane.getHeight() / 6);
        this.storyText.setText(action);
        return imageView;
    }

    /**
     * Updates the health, strength, and defense status bar values for both the
     * player and NPC in the current room
     *
     * @author ks061
     */
    private void updateStatusBarValues() {
        this.playerHealthBar.setValue(
                theModel.getPlayer().getCharacterStats().getHealth());
        this.playerStrengthBar.setValue(
                theModel.getPlayer().getCharacterStats().getAttack());
        this.playerDefenseBar.setValue(
                theModel.getPlayer().getCharacterStats().getDefense());

        this.npcHealthBar.setValue(
                theModel.getCurrentRoom().getNPCViewWrapper().getNPC().getCharacterStats().getHealth());
        this.npcStrengthBar.setValue(
                theModel.getCurrentRoom().getNPCViewWrapper().getNPC().getCharacterStats().getAttack());
        this.npcDefenseBar.setValue(
                theModel.getCurrentRoom().getNPCViewWrapper().getNPC().getCharacterStats().getDefense());
    }

    /**
     * Refreshes the health, strength, and defense status bars for both the
     * player and the NPC in the current room
     *
     * @author ks061
     */
    public void refreshStatusBars() {
        updateStatusBarValues();

        this.playerHealthBar.refresh();
        this.playerStrengthBar.refresh();
        this.playerDefenseBar.refresh();
        this.npcHealthBar.refresh();
        this.npcStrengthBar.refresh();
        this.npcDefenseBar.refresh();
    }

    /**
     * Toggles the inventory display flow pane
     *
     * @author ks061
     */
    public void toggleInventoryFP() {
        if (inventoryFP.isVisible()) {
            inventoryFP.setVisible(false);
        }
        else {
            inventoryFP.setVisible(true);
        }
    }

    /**
     * Gets the inventory display flow pane
     *
     * @return inventory display flow pane
     *
     * @author ks061
     */
    public FlowPane getInventoryFP() {
        return inventoryFP;
    }

}
