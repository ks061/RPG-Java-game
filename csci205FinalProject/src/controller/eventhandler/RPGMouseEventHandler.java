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
        if ((event.getSource() instanceof ItemImageViewWrapper)) {

            ItemImageViewWrapper source = (ItemImageViewWrapper) event.getSource();
            switch (source.getImageType()) {
                case UPARROW:
                case DOWNARROW:
                case LEFTARROW:
                case RIGHTARROW:
                    handleArrow(event, source.getImageType());
                    break;
                case ATTACK:
                    if (!theController.isAttackActive()) {
                        theController.handleAttack(event);
                    }
                    break;
                case INVENTORY:
                    theController.getTheView().toggleInventoryFP();
                    break;
                case TRADE:
                    theController.getTheView().setStoryText(
                            theController.getTheModel().getPlayer().trade(
                                    theController.getTheModel().getCurrentRoom().getNPCViewWrapper().getNPC()));
                    break;
                case SEARCH:
                    theController.getTheView().setStoryText(
                            theController.getTheModel().getPlayer().search(
                                    theController.getTheModel().getCurrentRoom().getNPCViewWrapper().getNPC()));
                    break;
            }
        }
        if (event.getEventType().equals(MouseEvent.DRAG_DETECTED)) {
            handleDrag(event);
        }
        else {
            handleNPC(event);
        }
    }

    /**
     * Handles interactions with the arrows
     *
     * @param source the key for arrow under the cursor
     *
     * @author ks061, lts010
     */
    private void handleArrow(MouseEvent event, ImageKey key) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {

            if (null != key) {
                switch (key) {
                    case UPARROW:
                        theController.getTheModel().setCurrentRoom(
                                theController.getTheModel().getCurrentRoom().getNorth());
                        break;
                    case DOWNARROW:
                        theController.getTheModel().setCurrentRoom(
                                theController.getTheModel().getCurrentRoom().getSouth());
                        break;
                    case LEFTARROW:
                        theController.getTheModel().setCurrentRoom(
                                theController.getTheModel().getCurrentRoom().getWest());
                        break;
                    case RIGHTARROW:
                        theController.getTheModel().setCurrentRoom(
                                theController.getTheModel().getCurrentRoom().getEast());
                        break;
                    default:
                        break;
                }
            }
            theController.getTheView().setStoryText("");
            theController.refresh();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(key).
                    setOpacity(100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(key).
                    setOpacity(0);
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
        db.setDragViewOffsetX(source.getImage().getWidth() / 2);
        db.setDragViewOffsetY(source.getImage().getHeight() / 2);
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
