/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 26, 2018
  * Time: 8:50:58 PM
  *
  * Project: csci205FinalProject
  * Package: controller.eventhandler
  * File: RPGMouseEventHandler
  * Description: This file contains RPGMouseEventHandler.
  * ****************************************
 */
package controller.eventhandler;

import controller.RPGController;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import model.character.NPC;
import view.ImageKey;
import view.wrapper.ItemImageViewWrapper;

/**
 * Mouse event handler for the controller
 *
 * @author ks061, lts010
 */
public class RPGMouseEventHandler implements EventHandler<MouseEvent> {

    /**
     * Controller of the RPG game
     */
    private final RPGController theController;

    /**
     * Explicit constructor that initializes the controller of the RPG game
     *
     * @param theController controller of the RPG game
     *
     * @author ks061
     */
    public RPGMouseEventHandler(RPGController theController) {
        this.theController = theController;
    }

    /**
     * Handles mouse events generated from interactions with the GUI
     *
     * @param event mouse event
     *
     * @author ks061, lts010
     */
    @Override
    public void handle(MouseEvent event) {
        System.out.println("mouse event = " + event.toString());
        if (event.getSource() == theController.getTheView().getImageViews().get(
                ImageKey.UPARROW)) {
            handleUpArrow(event);
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                ImageKey.DOWNARROW)) {
            handleDownArrow(event);
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                ImageKey.LEFTARROW)) {
            handleLeftArrow(event);
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                ImageKey.RIGHTARROW)) {
            handleRightArrow(event);
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                ImageKey.ATTACK)) {
            if (!theController.isAttackActive()) {
                theController.handleAttack(event);
            }
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                ImageKey.TRADE)) {
            theController.getTheView().setStoryText(
                    theController.getTheModel().getPlayer().trade(
                            theController.getTheModel().getCurrentRoom().getNPCViewWrapper().getNPC()));
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                ImageKey.SEARCH)) {
            theController.getTheView().setStoryText(
                    theController.getTheModel().getPlayer().search(
                            theController.getTheModel().getCurrentRoom().getNPCViewWrapper().getNPC()));
        }
        else if (event.getEventType().equals(MouseEvent.DRAG_DETECTED)) {
            handleDrag(event);
        }
        else {
            handleNPC(event);
        }
        theController.updateObjectives(
                theController.getTheModel().getObjectives());
        theController.updateStoryline(
                theController.getTheModel().getObjectives());
    }

    /**
     * Handles interactions with the up arrow
     *
     * @param event interaction with the up arrow
     *
     * @author ks061, lts010
     */
    private void handleUpArrow(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            theController.getTheModel().setCurrentRoom(
                    theController.getTheModel().getCurrentRoom().getNorth());
            theController.refresh();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(
                    ImageKey.UPARROW).setOpacity(
                            100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(
                    ImageKey.UPARROW).setOpacity(
                            0);
        }
    }

    /**
     * Handles interactions with the down arrow
     *
     * @param event interaction with the down arrow
     *
     * @author ks061, lts010
     */
    private void handleDownArrow(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            theController.getTheModel().setCurrentRoom(
                    theController.getTheModel().getCurrentRoom().getSouth());
            theController.refresh();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(
                    ImageKey.DOWNARROW).setOpacity(
                            100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(
                    ImageKey.DOWNARROW).setOpacity(
                            0);
        }
    }

    /**
     * Handles interactions with the left arrow
     *
     * @param event interaction with the left arrow
     *
     * @author ks061, lts010
     */
    private void handleLeftArrow(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            theController.getTheModel().setCurrentRoom(
                    theController.getTheModel().getCurrentRoom().getWest());
            theController.refresh();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(
                    ImageKey.LEFTARROW).setOpacity(
                            100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(
                    ImageKey.LEFTARROW).setOpacity(
                            0);
        }
    }

    /**
     * Handles interactions with the right arrow
     *
     * @param event interaction with the right arrow
     *
     * @author ks061, lts010
     */
    private void handleRightArrow(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            theController.getTheModel().setCurrentRoom(
                    theController.getTheModel().getCurrentRoom().getEast());
            theController.refresh();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(
                    ImageKey.RIGHTARROW).setOpacity(
                            100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(
                    ImageKey.RIGHTARROW).setOpacity(
                            0);
        }
    }

    /**
     * Handles dragging of an item
     *
     * @param event interaction with the item
     *
     * @author ks061, lts010
     */
    private void handleDrag(MouseEvent event) {
        System.out.println("start drag");
        ItemImageViewWrapper source = (ItemImageViewWrapper) event.getSource();
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
        db.setDragView(source.getImage());
        ClipboardContent content = new ClipboardContent();
        content.putString(source.getImageType().name());
        db.setContent(content);
    }

    /**
     * Handles a click on the NPC representing an ImageView
     *
     * @param event interaction with an NPC representing an ImageView
     *
     * @author ks061, lts010
     */
    private void handleNPC(MouseEvent event) {
        NPC npcInCurrentRoom = null;

        if (event.getSource() instanceof ImageView) {
            npcInCurrentRoom = theController.getMapImageViewToNPC().get(
                    (ImageView) event.getSource());
            try {
                if (npcInCurrentRoom.isAlive()) {
                    String dialog = npcInCurrentRoom.speak();
                    theController.getTheView().setStoryText(
                            npcInCurrentRoom.getName() + ": " + dialog);
                }
                else {
                    theController.getTheView().setStoryText(
                            theController.getTheModel().getPlayer().search(
                                    npcInCurrentRoom));
                }
            } catch (NullPointerException ex) {
            }
        }
    }
}
