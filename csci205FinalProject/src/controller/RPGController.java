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

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.RPGModel;
import model.character.NPC;
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

    /**
     * Action event handler for the class
     *
     * @author ks061
     */
    private class RPGActionEventHandler implements EventHandler<ActionEvent> {

        /**
         * Handles an action event that occurs in the application
         *
         * @param event action event that occurs in the application
         *
         * @author ks061
         */
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() instanceof Node) {
                if (theView.getToRoomButtonsDisplay().getChildren().contains(
                        (Node) event.getSource())) {
                    handleTravelButtonClick(event);
                }

            }

        }

        /**
         * Handles events when the player clicks on a travel button
         *
         * @param event event of a player clicking on a travel button
         *
         * @author ks061
         */
        private void handleTravelButtonClick(ActionEvent event) {
            if (event.getSource() == theView.getToRoomAbove()) {
                theModel.setCurrentRoom(
                        theModel.getCurrentRoom().getNorth());
            }
            if (event.getSource() == theView.getToRoomBelow()) {
                theModel.setCurrentRoom(
                        theModel.getCurrentRoom().getSouth());
            }
            if (event.getSource() == theView.getToRoomToLeft()) {
                theModel.setCurrentRoom(
                        theModel.getCurrentRoom().getWest());
            }
            if (event.getSource() == theView.getToRoomToRight()) {
                theModel.setCurrentRoom(
                        theModel.getCurrentRoom().getEast());
            }
            refresh();

        }
    }

    /**
     * Mouse event handler for the controller
     *
     * @author ks061
     */
    private class RPGMouseEventHandler implements EventHandler<MouseEvent> {

        /**
         * Handles events when the player clicks on an NPC
         *
         * @param event event of a player clicking on an NPC
         *
         * @author ks061 lts010
         */
        @Override
        public void handle(MouseEvent event) {

            System.out.println("mouse event = " + event.toString());
            if (event.getSource() == theView.getImageViews().get(RPGView.ImageType.UPARROW)) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    theModel.setCurrentRoom(theModel.getCurrentRoom().getNorth());
                    refresh();
                } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    theView.getImageViews().get(RPGView.ImageType.UPARROW).setOpacity(100);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    theView.getImageViews().get(RPGView.ImageType.UPARROW).setOpacity(0);
                }
            }
            if (event.getSource() == theView.getImageViews().get(RPGView.ImageType.DOWNARROW)) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    theModel.setCurrentRoom(theModel.getCurrentRoom().getSouth());
                    refresh();
                } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    theView.getImageViews().get(RPGView.ImageType.DOWNARROW).setOpacity(100);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    theView.getImageViews().get(RPGView.ImageType.DOWNARROW).setOpacity(0);
                }
            }
            if (event.getSource() == theView.getImageViews().get(RPGView.ImageType.LEFTARROW)) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    theModel.setCurrentRoom(theModel.getCurrentRoom().getWest());
                    refresh();
                } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    theView.getImageViews().get(RPGView.ImageType.LEFTARROW).setOpacity(100);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    theView.getImageViews().get(RPGView.ImageType.LEFTARROW).setOpacity(0);
                }
            }
            if (event.getSource() == theView.getImageViews().get(RPGView.ImageType.RIGHTARROW)) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    theModel.setCurrentRoom(theModel.getCurrentRoom().getEast());
                    refresh();
                } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    theView.getImageViews().get(RPGView.ImageType.RIGHTARROW).setOpacity(100);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    theView.getImageViews().get(RPGView.ImageType.RIGHTARROW).setOpacity(0);
                }
            }
            if (event.getSource() == theView.getImageViews().get(RPGView.ImageType.ATTACK)) {
                String playerAttacksNPC = theModel.getPlayer().attack(theModel.getCurrentRoom().getNPCViewWrappers().get(0).getNpc());
                ImageView imageView = theView.handleActionBubble(ImageType.POW, playerAttacksNPC);
                new Thread(new Runnable() {
                    public void run() {
                        try {
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
            NPC npcInCurrentRoom = null;

            if (event.getSource() instanceof ImageView) {
                npcInCurrentRoom = mapImageViewToNPC.get(
                        (ImageView) event.getSource());
            }

            try {
                String dialog = theModel.getPlayer().talk(npcInCurrentRoom);
                theView.updateStoryTextOutput(
                        npcInCurrentRoom.getName() + ": " + dialog);
            } catch (NullPointerException ex) {
            }
        }
    }

    /**
     * Removes the ImageView given, then the NPC in the current room attacks the player, 
     * and the appropriate ImageView and string are displayed in theView, then the program
     * pauses for 2 seconds, and then calls finishAttack
     * 
     * @param imageView the ImageView to be removed
     * 
     * @author lts010
     */
    public void continueAttack(ImageView imageView) {
        theView.getCenterPane().getChildren().remove(imageView);
        String NPCAttacksPlayer = theModel.getCurrentRoom().getNPCViewWrappers().get(0).getNpc().attack(theModel.getPlayer());
        ImageView newImageView = theView.handleActionBubble(ImageType.BAM, NPCAttacksPlayer);
        new Thread(new Runnable() {
            public void run() {
                try {
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

    /**
     * Removes the given ImageView from the centerPane of theView
     * @param imageView the ImageView to be removed
     * 
     * @author lts010
     */
    public void finishAttack(ImageView imageView) {
        theView.getCenterPane().getChildren().remove(imageView);
    }
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
    private RPGActionEventHandler rpgActionEventHandler;
    /**
     * Mouse event handler
     */
    private RPGMouseEventHandler rpgMouseEventHandler;
    /**
     * Maps an image view to an NPC
     */
    private HashMap<ImageView, NPC> mapImageViewToNPC;

    /**
     * Constructor that initializes references to the model and view of the
     * application, sets the view node-model component map, and sets event
     * handlers for the view components
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

        this.rpgActionEventHandler = new RPGActionEventHandler();
        this.rpgMouseEventHandler = new RPGMouseEventHandler();

        this.mapImageViewToNPC = new HashMap<>();

        setEventHandlerOfComponents();

        this.theView.getRightPane().getChildren().add(this.theView.getImageViews().get(ImageType.INVENTORY));
        this.theView.getRightPane().getChildren().add(this.theView.getImageViews().get(ImageType.SEARCH));
        this.theView.getRightPane().getChildren().add(this.theView.getImageViews().get(ImageType.TRADE));
        this.theView.getRightPane().getChildren().add(this.theView.getImageViews().get(ImageType.ATTACK));
        refresh();

    }

    /**
     * Sets the event handlers of components in the GUI
     *
     * @author ks061
     */
    private void setEventHandlerOfComponents() {
//        this.theView.getToRoomAbove().setOnAction(this.rpgActionEventHandler);
//        this.theView.getToRoomBelow().setOnAction(this.rpgActionEventHandler);
//        this.theView.getToRoomToLeft().setOnAction(this.rpgActionEventHandler);
//        this.theView.getToRoomToRight().setOnAction(this.rpgActionEventHandler);

        this.theView.getImageViews().get(RPGView.ImageType.UPARROW).setOnMouseClicked(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.DOWNARROW).setOnMouseClicked(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.LEFTARROW).setOnMouseClicked(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.RIGHTARROW).setOnMouseClicked(this.rpgMouseEventHandler);

        this.theView.getImageViews().get(RPGView.ImageType.UPARROW).setOnMouseEntered(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.DOWNARROW).setOnMouseEntered(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.LEFTARROW).setOnMouseEntered(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.RIGHTARROW).setOnMouseEntered(this.rpgMouseEventHandler);

        this.theView.getImageViews().get(RPGView.ImageType.UPARROW).setOnMouseExited(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.DOWNARROW).setOnMouseExited(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.LEFTARROW).setOnMouseExited(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.RIGHTARROW).setOnMouseExited(this.rpgMouseEventHandler);

        this.theView.getImageViews().get(RPGView.ImageType.INVENTORY).setOnMouseClicked(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.ATTACK).setOnMouseClicked(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.SEARCH).setOnMouseClicked(this.rpgMouseEventHandler);
        this.theView.getImageViews().get(RPGView.ImageType.TRADE).setOnMouseClicked(this.rpgMouseEventHandler);
    }

    /**
     * Updates the travel buttons and NPCs in the room based on the current
     * room, as well as the players stats and equipment
     *
     * @author ks061 lts010
     */
    public void refresh() {
        this.theView.getCenterPane().getChildren().clear();
        // updateTravelButtons();
        updateTravelArrows();
        updateNPCsInRoom();
        this.theView.refreshRoomName();
        this.theView.updateStoryTextOutput("");
        this.theView.loadCenterBackground(theModel.getCurrentRoom().getBackgroundImagePath());
    }

    /**
     * Updates the view of the buttons to travel from one room to another based
     * upon whether or not that room has a room adjacent to it (on each of its
     * four sides)
     *
     * @author ks061, ishk001
     */
    public void updateTravelButtons() {
        Button nullButton;
        if (this.theModel.getCurrentRoom().getNorth() != null) {
            this.theView.getToRoomButtonsDisplay().setTop(
                    this.theView.getToRoomAbove());
            BorderPane.setAlignment(this.theView.getToRoomAbove(), Pos.CENTER);
        } else {
            nullButton = this.theView.getNewNullButton();
            this.theView.getToRoomButtonsDisplay().setTop(nullButton);
            BorderPane.setAlignment(nullButton, Pos.CENTER);
        }
        if (this.theModel.getCurrentRoom().getSouth() != null) {
            this.theView.getToRoomButtonsDisplay().setBottom(
                    this.theView.getToRoomBelow());
            BorderPane.setAlignment(this.theView.getToRoomBelow(), Pos.CENTER);
        } else {
            nullButton = this.theView.getNewNullButton();
            this.theView.getToRoomButtonsDisplay().setBottom(nullButton);
            BorderPane.setAlignment(nullButton, Pos.CENTER);
        }
        if (this.theModel.getCurrentRoom().getWest() != null) {
            this.theView.getToRoomButtonsDisplay().setLeft(
                    this.theView.getToRoomToLeft());
        } else {
            nullButton = this.theView.getNewNullButton();
            this.theView.getToRoomButtonsDisplay().setLeft(nullButton);
        }
        if (this.theModel.getCurrentRoom().getEast() != null) {
            this.theView.getToRoomButtonsDisplay().setRight(
                    this.theView.getToRoomToRight());
        } else {
            nullButton = this.theView.getNewNullButton();
            this.theView.getToRoomButtonsDisplay().setRight(nullButton);
        }
    }

    /**
     * Updates the view to reflect the NPCs in the current room
     *
     * @author ks061
     */
    public void updateNPCsInRoom() {
        for (NPCImageViewWrapper npcViewWrapper : this.theModel.getCurrentRoom().getNPCViewWrappers()) {
            this.theView.getCenterPane().getChildren().add(
                    npcViewWrapper.getNpcImageView());
            npcViewWrapper.getNpcImageView().setOnMouseClicked(
                    this.rpgMouseEventHandler);
            mapImageViewToNPC.put(npcViewWrapper.getNpcImageView(),
                    npcViewWrapper.getNpc());
            npcViewWrapper.setLocation(this.theView.getCenterPane().getWidth() / 2, this.theView.getCenterPane().getHeight() / 2);
        }
    }

    /**
     * Updates the view with the travel arrows representing which rooms you can go to
     * 
     * @author lts010
     */
    public void updateTravelArrows() {
        ImageView tempImageView;

        if (this.theModel.getCurrentRoom().getNorth() != null) {
            tempImageView = theView.getImageViews().get(RPGView.ImageType.UPARROW);
            tempImageView.setX(this.theView.getCenterPane().getWidth() / 2 - 50);
            tempImageView.setY(150);
            tempImageView.setOpacity(0);
            this.theView.getCenterPane().getChildren().add(tempImageView);
        }

        if (this.theModel.getCurrentRoom().getSouth() != null) {
            tempImageView = theView.getImageViews().get(RPGView.ImageType.DOWNARROW);
            tempImageView.setX(this.theView.getCenterPane().getWidth() / 2 - 50);
            tempImageView.setY(this.theView.getCenterPane().getHeight() - 170);
            tempImageView.setOpacity(0);
            this.theView.getCenterPane().getChildren().add(theView.getImageViews().get(RPGView.ImageType.DOWNARROW));
        }

        if (this.theModel.getCurrentRoom().getWest() != null) {
            tempImageView = theView.getImageViews().get(RPGView.ImageType.LEFTARROW);
            tempImageView.setX(100);
            tempImageView.setY(this.theView.getCenterPane().getHeight() / 2 - 30);
            tempImageView.setOpacity(0);
            this.theView.getCenterPane().getChildren().add(theView.getImageViews().get(RPGView.ImageType.LEFTARROW));
        }

        if (this.theModel.getCurrentRoom().getEast() != null) {
            tempImageView = theView.getImageViews().get(RPGView.ImageType.RIGHTARROW);
            tempImageView.setX(this.theView.getCenterPane().getWidth() - 160);
            tempImageView.setY(this.theView.getCenterPane().getHeight() / 2 - 30);
            tempImageView.setOpacity(0);
            this.theView.getCenterPane().getChildren().add(theView.getImageViews().get(RPGView.ImageType.RIGHTARROW));
        }
    }
}
