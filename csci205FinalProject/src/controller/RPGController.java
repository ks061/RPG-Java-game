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

import controller.eventhandler.RPGDragEventHandler;
import controller.eventhandler.RPGMouseEventHandler;
import java.io.File;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JOptionPane;
import model.RPGModel;
import model.character.NPC;
import model.item.Item;
import view.RPGView;
import view.RPGView.ImageType;
import view.wrapper.NPCImageViewWrapper;

/**
 * RPGController serves as the connector between the model and view in the RPG
 * game application.
 *
 * @author ks061
 */
public class RPGController {

    /*
     * attackActive represents whether or not there is an attack taking place; true if this is the case otherwise false
     */
    private boolean attackActive;

    /**
     * Action event handler for the class
     *
     * @author ks061
     */
//    private class RPGActionEventHandler implements EventHandler<ActionEvent> {
    /**
     * Handles an action event that occurs in the application
     *
     * @param event action event that occurs in the application
     *
     * @author ks061
     */
//        @Override
//        public void handle(ActionEvent event) {
//            if (event.getSource() instanceof Node) {
//                if (theView.getToRoomButtonsDisplay().getChildren().contains(
//                        (Node) event.getSource())) {
//                    handleTravelButtonClick(event);
//                }
//
//            }
//
//        }
    /**
     * Handles events when the player clicks on a travel button
     *
     * @param event event of a player clicking on a travel button
     *
     * @author ks061
     */
//        private void handleTravelButtonClick(ActionEvent event) {
//            if (event.getSource() == theView.getToRoomAbove()) {
//                theModel.setCurrentRoom(
//                        theModel.getCurrentRoom().getNorth());
//            }
//            if (event.getSource() == theView.getToRoomBelow()) {
//                theModel.setCurrentRoom(
//                        theModel.getCurrentRoom().getSouth());
//            }
//            if (event.getSource() == theView.getToRoomToLeft()) {
//                theModel.setCurrentRoom(
//                        theModel.getCurrentRoom().getWest());
//            }
//            if (event.getSource() == theView.getToRoomToRight()) {
//                theModel.setCurrentRoom(
//                        theModel.getCurrentRoom().getEast());
//            }
//            refresh();
//
//        }
//    }
    /**
     * Model of the application
     */
    private RPGModel theModel;
    /**
     * View of the application
     */
    private RPGView theView;
    /**
     * Action event handler
     */
//        private RPGActionEventHandler rpgActionEventHandler;
    /**
     * Mouse event handler
     */
    private RPGMouseEventHandler rpgMouseEventHandler;
    /**
     * Drag event handler
     */
    private RPGDragEventHandler rpgDragEventHandler;
    /**
     * Maps an image view to an NPC
     */
    private HashMap<ImageView, NPC> mapImageViewToNPC;

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
//            this.rpgActionEventHandler = new RPGActionEventHandler();
        this.rpgMouseEventHandler = new RPGMouseEventHandler(this);
        this.rpgDragEventHandler = new RPGDragEventHandler();

        this.mapImageViewToNPC = new HashMap<>();

        setEventHandlerOfComponents();

        this.theView.getImageViews().get(ImageType.INVENTORY).setOnDragOver(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageType.INVENTORY).setOnDragDropped(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageType.INVENTORY).setOnDragEntered(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageType.INVENTORY).setOnDragExited(
                rpgDragEventHandler);
        this.theView.getImageViews().get(ImageType.WEAPON1).setOnDragDetected(
                rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageType.WEAPON2).setOnDragDetected(
                rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageType.WEAPON3).setOnDragDetected(
                rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageType.SHIELD1).setOnDragDetected(
                rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageType.SHIELD2).setOnDragDetected(
                rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageType.SHIELD3).setOnDragDetected(
                rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageType.ARMOR1).setOnDragDetected(
                rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageType.ARMOR2).setOnDragDetected(
                rpgMouseEventHandler);
        this.theView.getImageViews().get(ImageType.ARMOR3).setOnDragDetected(
                rpgMouseEventHandler);

        this.theView.getBottomHBox().getChildren().add(
                this.theView.getImageViews().get(ImageType.INVENTORY));
        this.theView.getBottomHBox().getChildren().add(
                this.theView.getImageViews().get(ImageType.SEARCH));
        this.theView.getBottomHBox().getChildren().add(
                this.theView.getImageViews().get(ImageType.TRADE));
        this.theView.getBottomHBox().getChildren().add(
                this.theView.getImageViews().get(ImageType.ATTACK));
        refresh();
    }

    /**
     * Sets the mouse event handler called when the up arrow is clicked, hovered
     * over, or is no longer hovered over.
     *
     * @author ks061, lts010
     */
    private void setEventHandlersForUpArrow() {
        this.theView.getImageViews().get(RPGView.ImageType.UPARROW).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.UPARROW).setOnMouseEntered(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.UPARROW).setOnMouseExited(
                this.rpgMouseEventHandler);
    }

    /**
     * Sets the mouse event handler called when the down arrow is clicked,
     * hovered over, or is no longer hovered over.
     *
     * @author ks061, lts010
     */
    private void setEventHandlersForDownArrow() {
        this.theView.getImageViews().get(RPGView.ImageType.DOWNARROW).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.DOWNARROW).setOnMouseEntered(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.DOWNARROW).setOnMouseExited(
                this.rpgMouseEventHandler);
    }

    /**
     * Sets the mouse event handler called when the left arrow is clicked,
     * hovered over, or is no longer hovered over.
     *
     * @author ks061, lts010
     */
    private void setEventHandlersForLeftArrow() {
        this.theView.getImageViews().get(RPGView.ImageType.LEFTARROW).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.LEFTARROW).setOnMouseEntered(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.LEFTARROW).setOnMouseExited(
                this.rpgMouseEventHandler);
    }

    /**
     * Sets the mouse event handler called when the right arrow is clicked,
     * hovered over, or is no longer hovered over.
     *
     * @author ks061, lts010
     */
    private void setEventHandlersForRightArrow() {
        this.theView.getImageViews().get(RPGView.ImageType.RIGHTARROW).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.RIGHTARROW).setOnMouseEntered(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.RIGHTARROW).setOnMouseExited(
                this.rpgMouseEventHandler);
    }

    /**
     * Sets the mouse event handler for when the action buttons are clicked
     *
     * @author ks061, lts010
     */
    private void setEventHandlersForActionButtons() {
        this.theView.getImageViews().get(RPGView.ImageType.INVENTORY).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.ATTACK).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.SEARCH).setOnMouseClicked(
                this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.TRADE).setOnMouseClicked(
                this.rpgMouseEventHandler);
    }

    /**
     * Sets the event handlers of components in the GUI
     *
     * @author ks061, lts010
     */
    private void setEventHandlerOfComponents() {
//        this.theView.getToRoomAbove().setOnAction(this.rpgActionEventHandler);
//        this.theView.getToRoomBelow().setOnAction(this.rpgActionEventHandler);
//        this.theView.getToRoomToLeft().setOnAction(this.rpgActionEventHandler);
//        this.theView.getToRoomToRight().setOnAction(this.rpgActionEventHandler);

        setEventHandlersForUpArrow();
        setEventHandlersForDownArrow();
        setEventHandlersForLeftArrow();
        setEventHandlersForRightArrow();

        setEventHandlersForActionButtons();
    }

    /**
     * Refreshes the image view of items in the room
     *
     * @author ks061, lts010
     */
    private void refreshImageViewOfItem() {
        ImageView imageViewOfImage;
        for (Item item : theModel.getCurrentRoom().getHiddenItems()) {
            imageViewOfImage = theView.getImageViews().get(
                    item.getImageViewKey());
            theView.getCenterPane().getChildren().add(imageViewOfImage);
        }
    }

    /**
     * Updates the travel buttons, NPCs in the room based on the current room,
     * properties of the player, including player statistics and equipment, room
     * name, and story dialogue
     *
     * @author ks061, lts010
     */
    public void refresh() {
        this.theView.getCenterPane().getChildren().clear();
        // updateTravelButtons();
        refreshTravelArrows();
        refreshNPCsInRoom();
//        this.theModel.updateProperties();
        this.theView.refreshRoomName();
        this.theView.updateStoryText("");
        this.theView.loadCenterBackground(
                theModel.getCurrentRoom().getBackgroundImagePath());
        refreshImageViewOfItem();
    }

//    /**
//     * Updates the view of the buttons to travel from one room to another based
//     * upon whether or not that room has a room adjacent to it (on each of its
//     * four sides)
//     *
//     * @author ks061, ishk001
//     */
//    public void updateTravelButtons() {
//        Button nullButton;
//        if (this.theModel.getCurrentRoom().getNorth() != null) {
//            this.theView.getToRoomButtonsDisplay().setTop(
//                    this.theView.getToRoomAbove());
//            BorderPane.setAlignment(this.theView.getToRoomAbove(), Pos.CENTER);
//        }
//        else {
//            nullButton = this.theView.getNewNullButton();
//            this.theView.getToRoomButtonsDisplay().setTop(nullButton);
//            BorderPane.setAlignment(nullButton, Pos.CENTER);
//        }
//        if (this.theModel.getCurrentRoom().getSouth() != null) {
//            this.theView.getToRoomButtonsDisplay().setBottom(
//                    this.theView.getToRoomBelow());
//            BorderPane.setAlignment(this.theView.getToRoomBelow(), Pos.CENTER);
//        }
//        else {
//            nullButton = this.theView.getNewNullButton();
//            this.theView.getToRoomButtonsDisplay().setBottom(nullButton);
//            BorderPane.setAlignment(nullButton, Pos.CENTER);
//        }
//        if (this.theModel.getCurrentRoom().getWest() != null) {
//            this.theView.getToRoomButtonsDisplay().setLeft(
//                    this.theView.getToRoomToLeft());
//        }
//        else {
//            nullButton = this.theView.getNewNullButton();
//            this.theView.getToRoomButtonsDisplay().setLeft(nullButton);
//        }
//        if (this.theModel.getCurrentRoom().getEast() != null) {
//            this.theView.getToRoomButtonsDisplay().setRight(
//                    this.theView.getToRoomToRight());
//        }
//        else {
//            nullButton = this.theView.getNewNullButton();
//            this.theView.getToRoomButtonsDisplay().setRight(nullButton);
//        }
//    }
    /**
     * Updates the view to reflect the NPCs in the current room
     *
     * @author ks061
     */
    public void refreshNPCsInRoom() {
        for (NPCImageViewWrapper npcViewWrapper : this.theModel.getCurrentRoom().getNPCViewWrappers()) {
            this.theView.getCenterPane().getChildren().add(npcViewWrapper);
            npcViewWrapper.setOnMouseClicked(this.rpgMouseEventHandler);
            mapImageViewToNPC.put(npcViewWrapper, npcViewWrapper.getNpc());
            npcViewWrapper.setX(this.theView.getCenterPane().getWidth() / 2);
            npcViewWrapper.setY(this.theView.getCenterPane().getHeight() / 2);
        }
    }

    /**
     * Loads the up arrow to the view
     *
     * @author ks061
     */
    private void loadUpArrow() {
        ImageView tempImageView;
        tempImageView = theView.getImageViews().get(
                RPGView.ImageType.UPARROW);
        tempImageView.setX(this.theView.getCenterPane().getWidth() / 2 - 50);
        tempImageView.setY(150);
        tempImageView.setOpacity(0);
        this.theView.getCenterPane().getChildren().add(tempImageView);
    }

    /**
     * Loads the down arrow to the view
     *
     * @author ks061
     */
    private void loadDownArrow() {
        ImageView tempImageView;
        tempImageView = theView.getImageViews().get(
                RPGView.ImageType.DOWNARROW);
        tempImageView.setX(this.theView.getCenterPane().getWidth() / 2 - 50);
        tempImageView.setY(this.theView.getCenterPane().getHeight() - 170);
        tempImageView.setOpacity(0);
        this.theView.getCenterPane().getChildren().add(
                theView.getImageViews().get(RPGView.ImageType.DOWNARROW));
    }

    /**
     * Loads the left arrow to the view
     *
     * @author ks061
     */
    private void loadLeftArrow() {
        ImageView tempImageView;
        tempImageView = theView.getImageViews().get(
                RPGView.ImageType.LEFTARROW);
        tempImageView.setX(100);
        tempImageView.setY(this.theView.getCenterPane().getHeight() / 2 - 30);
        tempImageView.setOpacity(0);
        this.theView.getCenterPane().getChildren().add(
                theView.getImageViews().get(RPGView.ImageType.LEFTARROW));
    }

    /**
     * Loads the right arrow to the view
     *
     * @author ks061
     */
    private void loadRightArrow() {
        ImageView tempImageView;
        tempImageView = theView.getImageViews().get(
                RPGView.ImageType.RIGHTARROW);
        tempImageView.setX(this.theView.getCenterPane().getWidth() - 160);
        tempImageView.setY(this.theView.getCenterPane().getHeight() / 2 - 30);
        tempImageView.setOpacity(0);
        this.theView.getCenterPane().getChildren().add(
                theView.getImageViews().get(RPGView.ImageType.RIGHTARROW));
    }

    /**
     * Updates the view with the travel arrows representing which rooms you can
     * go to
     *
     * @author lts010, ks061
     */
    public void refreshTravelArrows() {
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
     * Removes the ImageView given, then the NPC in the current room attacks the
     * player, and the appropriate ImageView and string are displayed in
     * theView, then the program pauses for 2 seconds, and then calls
     * finishAttack
     *
     * @param imageView the ImageView to be removed
     *
     * @author lts010
     */
    public void continueAttack(ImageView imageView) {
        theView.getCenterPane().getChildren().remove(imageView);
        System.out.println(
                theModel.getFinalBoss().getCharacterStats().getHealth());
        if (!theModel.getFinalBoss().isIsAlive()) {
            theView.updateStoryText("You win!");
            JOptionPane.showMessageDialog(null,
                                          "Attention all Fortnite gamers: John Wick is in great danger and he needs your help to wipe out the squads in the Tilted Towers, but to do this he needs a golden scar and a couple of chug jugs. To help him, all he needs is your credit card number, the three digits on the back, and the expiration month and year. But, you gotta be quick, so John Wick can secure the bag and achieve the epic Victory Royal!",
                                          "The End", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        else if (!theModel.getCurrentRoom().getNPCViewWrappers().get(0).getNpc().isIsAlive()) {
            theView.updateStoryText(String.format("%s is dead!",
                                                  theModel.getCurrentRoom().getNPCViewWrappers().get(
                                                          0).getNpc().getName()));
        }
        else {
            String NPCAttacksPlayer = theModel.getCurrentRoom().getNPCViewWrappers().get(
                    0).getNpc().attack(theModel.getPlayer());
            ImageView newImageView = theView.handleActionBubble(ImageType.BAM,
                                                                NPCAttacksPlayer);
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
                            finishAttack(newImageView);
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
     * @author lts010
     */
    public void finishAttack(ImageView imageView) {
        theView.getCenterPane().getChildren().remove(imageView);
        attackActive = false;
        if (!theModel.getPlayer().isIsAlive()) {
            theView.updateStoryText("You have died!");
            JOptionPane.showMessageDialog(null,
                                          "Attention all Fortnite gamers: John Wick is in great danger and he needs your help to wipe out the squads in the Tilted Towers, but to do this he needs a golden scar and a couple of chug jugs. To help him, all he needs is your credit card number, the three digits on the back, and the expiration month and year. But, you gotta be quick, so John Wick can secure the bag and achieve the epic Victory Royal!",
                                          "Game Over", JOptionPane.PLAIN_MESSAGE);
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
     * #author lts010
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
}
