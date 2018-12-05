/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2018
 *
 * Name: Kartikeya Sharma
 * Date: Oct 19, 2018
 * Time: 7:19:08 PM
 *
 * Project: csci205
 * Package: controller
 * File: RPGController
 * Description: This file contains RPGController, which serves as the
 *              connector between the model and view in the RPG game
 *              application.
 * ****************************************
 */
package controller;

import controller.eventhandler.RPGDragEventHandler;
import controller.eventhandler.RPGMouseEventHandler;
import java.io.File;
import java.util.EnumMap;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JOptionPane;
import model.RPGModel;
import model.character.NPC;
import model.item.Item;
import utility.RPGUtility;
import view.ImageKey;
import view.RPGView;
import view.wrapper.ItemImageViewWrapper;
import view.wrapper.NPCImageViewWrapper;
import view.wrapper.PlayerImageViewWrapper;

/**
 * RPGController serves as the connector between the model and view in the RPG
 * game application.
 *
 * @author ks061
 */
public class RPGController {

    /**
     * Represents whether or not an attack is taking place
     */
    private boolean attackActive;
    /**
     * Model of the application
     */
    private final RPGModel theModel;
    /**
     * View of the application
     */
    private final RPGView theView;
    /**
     * Mouse event handler
     */
    private final RPGMouseEventHandler rpgMouseEventHandler;
    /**
     * Drag event handler
     */
    private final RPGDragEventHandler rpgDragEventHandler;
    /**
     * Maps an ImageView to an NPC
     */
    private final HashMap<ImageView, NPC> mapImageViewToNPC;
    /**
     * Adds information and methods necessary to render an image of the player
     */
    private final PlayerImageViewWrapper playerImageView;
    /**
     * Starting x-coordinate of the player
     */
    private static final int PLAYER_STARTING_X_COORDINATE = 800;
    /**
     * Starting y-coordinate of the player
     */
    private static final int PLAYER_STARTING_Y_COORDINATE = 350;

    /**
     * Constructor that initializes references to the model and view of the
     * application, sets the view node-model component map, and sets event
     * handlers for the view components; creates bindings to player properties
     *
     * @param theModel model of the application
     * @param theView view of the application
     *
     * @author ks061, lts010
     */
    public RPGController(RPGModel theModel,
                         RPGView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.attackActive = false;
        this.rpgMouseEventHandler = new RPGMouseEventHandler(this);
        this.rpgDragEventHandler = new RPGDragEventHandler(this);

        this.mapImageViewToNPC = new HashMap<>();

        Point2D location = new Point2D(PLAYER_STARTING_X_COORDINATE,
                                       PLAYER_STARTING_Y_COORDINATE);
        this.playerImageView = new PlayerImageViewWrapper(theModel.getPlayer(),
                                                          location,
                                                          theView.getImageViews());
        setMouseHandlerOfComponents();

        setDragHandlerOfComponents();

        refresh();
    }

    /**
     * Sets the mouse event handler called when the arrow is clicked, hovered
     * over, or is no longer hovered over.
     *
     * @author ks061, lts010
     */
    private void setEventHandlersForArrow(ImageKey key) {
        this.theView.getImageViews().get(key).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(key).setOnMouseEntered(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(key).setOnMouseExited(
                this.rpgMouseEventHandler);
    }

    /**
     * Sets the mouse event handler for when the action buttons are clicked
     *
     * @author ks061, lts010
     */
    private void setMouseEventHandlersForActionButtons() {
        this.theView.getImageViews().get(ImageKey.INVENTORY).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageKey.ATTACK).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageKey.SEARCH).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageKey.TRADE).setOnMouseClicked(
                this.rpgMouseEventHandler);
    }

    /**
     * Sets event handlers of GUI components for listening to click events
     *
     * @author ks061, lts010
     */
    private void setMouseHandlerOfComponents() {
        setEventHandlersForArrow(ImageKey.UPARROW);
        setEventHandlersForArrow(ImageKey.DOWNARROW);
        setEventHandlersForArrow(ImageKey.LEFTARROW);
        setEventHandlersForArrow(ImageKey.RIGHTARROW);

        setMouseEventHandlersForActionButtons();
    }

    /**
     * Sets event handlers of GUI components for listening to drag events
     *
     * @author ks061
     */
    private void setDragHandlerOfComponents() {
        EnumMap<ImageKey, ItemImageViewWrapper> imageViews = this.theView.getImageViews();
        this.theView.getInventoryFP().setOnDragOver(rpgDragEventHandler);
        this.theView.getInventoryFP().setOnDragDropped(rpgDragEventHandler);
        this.theView.getCenterPane().setOnDragOver(rpgDragEventHandler);
        this.theView.getCenterPane().setOnDragDropped(rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.INVENTORY).setOnDragOver(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.INVENTORY).setOnDragDropped(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.FACE).setOnDragOver(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.FACE).setOnDragDropped(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_ARMOR).setOnDragEntered(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_ARMOR).setOnDragExited(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_ARMOR).setOnDragOver(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_ARMOR).setOnDragDropped(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_SHIELD).setOnDragEntered(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_SHIELD).setOnDragExited(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_SHIELD).setOnDragOver(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_SHIELD).setOnDragDropped(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_WEAPON).setOnDragEntered(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_WEAPON).setOnDragExited(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_WEAPON).setOnDragOver(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageKey.BLANK_WEAPON).setOnDragDropped(
                rpgDragEventHandler);
        for (ImageKey key : ImageKey.values()) {
            if (key.ordinal() > ImageKey.DOWNARROW.ordinal()
                && this.theView.getImageViews().get(key) != null) {
                this.theView.getImageViews().get(key).setOnDragDetected(
                        rpgMouseEventHandler);
                if (key.ordinal() > ImageKey.RAMEN.ordinal()) {
                    imageViews.get(key).setOnDragOver(rpgDragEventHandler);
                    imageViews.get(key).setOnDragDropped(rpgDragEventHandler);
                }
            }
        }
    }

    /**
     * Refreshes the image view of items in the room
     *
     * @author ks061, lts010
     */
    private void refreshImageViewOfItem() {
        ItemImageViewWrapper imageViewOfImage;
        for (Item item : this.theModel.getCurrentRoom().getHiddenItems()) {
            imageViewOfImage = this.theView.getImageViews().get(
                    item.getImageViewKey());
            imageViewOfImage.setToRoomLocation();
            this.theView.getCenterPane().getChildren().add(imageViewOfImage);
        }
    }

    /**
     * Updates the travel buttons, NPCs in the room based on the current room,
     * room name, resets the story dialogue display, loads the center
     * background, refreshes image views of all the items in the room, and
     * refreshes the status bars for both the player and the current NPC in the
     * room
     *
     * @author ks061, lts010
     */
    public void refresh() {
        this.theView.getCenterPane().getChildren().clear();
        //Note the order of these refreshs determine which item is on top
        //InventoryFP should be first, ImageViewOfItems should be last so that
        //items are on top and an visible. StatusBars can go anywhere since
        //the aren't in the centerPane
        refreshInventoryFP();
        refreshTravelArrows();
        refreshNPCsInRoom();
        playerImageView.refresh(this.theView.getCenterPane());
        this.theView.refreshRoomName();
        this.theView.loadCenterBackground(
                this.theModel.getCurrentRoom().getBackgroundImagePath());

        refreshImageViewOfItem();
        this.theView.refreshStatusBars();
        theModel.updateGame();
    }

    /**
     * Updates the view to reflect the NPCs in the current room
     *
     * @author ks061
     */
    private void refreshNPCsInRoom() {
        NPCImageViewWrapper npcViewWrapper = this.theModel.getCurrentRoom().getNPCViewWrapper();
        npcViewWrapper.setOnMouseClicked(this.rpgMouseEventHandler);
        npcViewWrapper.setX(this.theView.getCenterPane().getWidth() / 2);
        npcViewWrapper.setY(this.theView.getCenterPane().getHeight() / 2);

        this.theView.getCenterPane().getChildren().add(npcViewWrapper);

        this.mapImageViewToNPC.put(npcViewWrapper, npcViewWrapper.getNPC());
    }

    /**
     * Loads the up arrow in the view
     *
     * @author ks061
     */
    private void loadUpArrow() {
        ImageView tempImageView;
        tempImageView = theView.getImageViews().get(ImageKey.UPARROW);
        tempImageView.setX(this.theView.getCenterPane().getWidth() * 0.46);
        tempImageView.setY(this.theView.getCenterPane().getHeight() * 0.15);
        tempImageView.setOpacity(0);
        this.theView.getCenterPane().getChildren().add(tempImageView);
    }

    /**
     * Loads the down arrow in the view
     *
     * @author ks061
     */
    private void loadDownArrow() {
        ImageView tempImageView;
        tempImageView = theView.getImageViews().get(ImageKey.DOWNARROW);
        tempImageView.setX(this.theView.getCenterPane().getWidth() * 0.46);
        tempImageView.setY(this.theView.getCenterPane().getHeight() * 0.82);
        tempImageView.setOpacity(0);
        this.theView.getCenterPane().getChildren().add(
                theView.getImageViews().get(ImageKey.DOWNARROW));
    }

    /**
     * Loads the left arrow in the view
     *
     * @author ks061
     */
    private void loadLeftArrow() {
        ImageView tempImageView;
        tempImageView = theView.getImageViews().get(ImageKey.LEFTARROW);
        tempImageView.setX(this.theView.getCenterPane().getWidth() * 0.09);
        tempImageView.setY(this.theView.getCenterPane().getHeight() * 0.48);
        tempImageView.setOpacity(0);
        this.theView.getCenterPane().getChildren().add(
                theView.getImageViews().get(ImageKey.LEFTARROW));
    }

    /**
     * Loads the right arrow in the view
     *
     * @author ks061
     */
    private void loadRightArrow() {
        ImageView tempImageView;
        tempImageView = theView.getImageViews().get(ImageKey.RIGHTARROW);
        tempImageView.setX(this.theView.getCenterPane().getWidth() * 0.87);
        tempImageView.setY(this.theView.getCenterPane().getHeight() * 0.48);
        tempImageView.setOpacity(0);
        this.theView.getCenterPane().getChildren().add(
                theView.getImageViews().get(ImageKey.RIGHTARROW));
    }

    /**
     * Loads arrows representing adjacent rooms that can be traveled to in the
     * view
     *
     * @author lts010, ks061
     */
    private void refreshTravelArrows() {
        if (this.theModel.getCurrentRoom().getNorth() != null) {
            loadUpArrow();
        }

        if (this.theModel.getCurrentRoom().getSouth() != null) {
            loadDownArrow();
        }

        if (this.theModel.getCurrentRoom().getWest() != null) {
            loadLeftArrow();
        }

        if (this.theModel.getCurrentRoom().getEast() != null) {
            loadRightArrow();
        }
    }

    /**
     * Refreshes the inventory display flow pane
     *
     * @author ks061, lts010
     */
    public void refreshInventoryFP() {
        FlowPane inventoryFP = theView.getInventoryFP();
        //The view's centerpane has likey been cleared, if so add our parent to it.
        if (!(theView.getCenterPane().getChildren().contains(
              inventoryFP.getParent()))) {
            theView.getCenterPane().getChildren().add(inventoryFP.getParent());
        }
        inventoryFP.getChildren().clear();
        for (Item item : theModel.getPlayer().getInventory()) {
            theView.getImageViews().get(item.getImageViewKey()).zeroLocation();
            inventoryFP.getChildren().add(theView.getImageViews().get(
                    item.getImageViewKey()));
        }
    }

    /**
     * Handles the defeat of a boss by displaying that the player has won in the
     * story text and exits the game
     *
     * @author ks061
     */
    private void handleBossDefeated() {
        this.attackActive = false;
        theView.setStoryText("You win!");
        JOptionPane.showMessageDialog(null, "You excelled in CSCI 205!",
                                      "Congratulations!",
                                      JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

    /**
     * Handles the defeat of an NPC by displaying that the NPC has deceased in
     * the story text
     *
     * @author ks061, lts010
     */
    private void handleNPCDefeated() {
        this.attackActive = false;
        theView.setStoryText(String.format("%s is dead!",
                                           theModel.getCurrentRoom().getNPCViewWrapper().getNPC().getName()));
    }

    /**
     * Handles interactions with the attack button Source for sound was
     * https://www.youtube.com/watch?v=ZOKu36x_9LQ
     *
     * @param event interaction with the attack button
     *
     * @author ks061, lts010
     */
    public void handleAttack(MouseEvent event) {
        if (theModel.getCurrentRoom().getNPCViewWrapper().getNPC().isFriendly()) {
            theView.setStoryText(
                    String.format("%s is a friend!",
                                  theModel.getCurrentRoom().getNPCViewWrapper().getNPC().getName()));
        }
        else if (!theModel.getCurrentRoom().getNPCViewWrapper().getNPC().isAlive()) {
            theView.setStoryText(
                    String.format("%s is already dead!",
                                  theModel.getCurrentRoom().getNPCViewWrapper().getNPC().getName()));
        }
        else {
            setAttackActive(true);
            String playerAttacksNPCMessage = theModel.getPlayer().attack(
                    theModel.getCurrentRoom().getNPCViewWrapper().getNPC());
            this.theView.refreshStatusBars();
            ImageView imageView = theView.handleAttackDisplay(ImageKey.POW,
                                                              playerAttacksNPCMessage);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String sfx = RPGUtility.ATTACK_SOUND_EFFECT_FILE_PATH;
                        Media hit = new Media(new File(sfx).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(hit);
                        mediaPlayer.play();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            continueAttack(imageView);
                        }
                    });
                }
            }).start();
        }
    }

    /**
     * Removes the attack ImageView given. Then, the NPC in the current room
     * attacks the player, and the appropriate attack ImageView and String are
     * displayed in the view. Finally, calls finishAttack after first pausing
     * for two seconds. Source for sound was
     * https://www.youtube.com/watch?v=ZOKu36x_9LQ
     *
     * @param imageView attack ImageView to be removed
     *
     * @author lts010, ks061
     */
    public void continueAttack(ImageView imageView) {
        theView.getCenterPane().getChildren().remove(imageView);
        System.out.println(
                theModel.getFinalBoss().getCharacterStats().getHealth());
        if (!theModel.getFinalBoss().isAlive()) {
            handleBossDefeated();
        }
        else if (!theModel.getCurrentRoom().getNPCViewWrapper().getNPC().isAlive()) {
            handleNPCDefeated();
        }
        else {
            String npcAttackingPlayerMessage = theModel.getCurrentRoom().getNPCViewWrapper().getNPC().attack(
                    theModel.getPlayer());
            this.theView.refreshStatusBars();
            ImageView continueImageView = theView.handleAttackDisplay(
                    ImageKey.BAM, npcAttackingPlayerMessage);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String sfx = "wav/batman2.wav";
                        Media hit = new Media(new File(sfx).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(hit);
                        mediaPlayer.play();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            finishAttack(continueImageView);
                        }
                    });
                }
            }).start();
        }
    }

    /**
     * Removes the given ImageView from the centerPane of theView
     *
     * @param imageView the ImageView to be removed
     *
     * @author lts010, ks061
     */
    public void finishAttack(ImageView imageView) {
        theView.getCenterPane().getChildren().remove(imageView);
        this.attackActive = false;
        this.theView.refreshStatusBars();
        if (!theModel.getPlayer().isAlive()) {
            theView.setStoryText("You have died!");
            JOptionPane.showMessageDialog(null,
                                          "Failure to refactor led to your dropping out of CSCI 205!",
                                          "Game Over",
                                          JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Gets the model
     *
     * @return model
     *
     * @author ks061
     */
    public RPGModel getTheModel() {
        return theModel;
    }

    /**
     * Gets the view
     *
     * @return view
     *
     * @author ks061
     */
    public RPGView getTheView() {
        return theView;
    }

    /**
     * Gets the map of ImageView objects to NPC objects
     *
     * @return map of ImageView objects to NPC objects
     *
     * @author ks061
     */
    public HashMap<ImageView, NPC> getMapImageViewToNPC() {
        return mapImageViewToNPC;
    }

    /**
     * Sees if an attack is happening
     *
     * @return boolean
     *
     * @author lts010
     */
    public boolean isAttackActive() {
        return attackActive;
    }

    /**
     * Sets the attackActive boolean
     *
     * @param attackActive a boolean
     *
     * @author lts010
     */
    public void setAttackActive(boolean attackActive) {
        this.attackActive = attackActive;
    }

    /**
     * Gets the image view wrapper of the player
     *
     * @return image view wrapper of the player
     *
     * @author ks061
     */
    public PlayerImageViewWrapper getPlayerImageView() {
        return playerImageView;
    }
}
