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
import java.io.File;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.character.NPC;
import view.RPGView;
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
    private RPGController theController;

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
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String sfx = "wav/footsteps.wav";
                        Media walk = new Media(new File(sfx).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(walk);
                        mediaPlayer.play();
                    } catch (Exception e) {
                    }
                }

            }
            ).start();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(
                    RPGView.ImageType.UPARROW).setOpacity(
                            100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(
                    RPGView.ImageType.UPARROW).setOpacity(
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
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String sfx = "wav/footsteps.wav";
                        Media walk = new Media(new File(sfx).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(walk);
                        mediaPlayer.play();
                    } catch (Exception e) {
                    }
                }

            }
            ).start();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(
                    RPGView.ImageType.DOWNARROW).setOpacity(
                            100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(
                    RPGView.ImageType.DOWNARROW).setOpacity(
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
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String sfx = "wav/footsteps.wav";
                        Media walk = new Media(new File(sfx).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(walk);
                        mediaPlayer.play();
                    } catch (Exception e) {
                    }
                }

            }
            ).start();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(
                    RPGView.ImageType.LEFTARROW).setOpacity(
                            100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(
                    RPGView.ImageType.LEFTARROW).setOpacity(
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
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String sfx = "wav/footsteps.wav";
                        Media walk = new Media(new File(sfx).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(walk);
                        mediaPlayer.play();
                    } catch (Exception e) {
                    }
                }

            }
            ).start();
        }
        else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            theController.getTheView().getImageViews().get(
                    RPGView.ImageType.RIGHTARROW).setOpacity(
                            100);
        }
        else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            theController.getTheView().getImageViews().get(
                    RPGView.ImageType.RIGHTARROW).setOpacity(
                            0);
        }
    }

    /**
     * Handles interactions with the attack button
     *
     * @param event interaction with the attack button
     *
     * @author ks061, lts010
     */
    private void handleAttack(MouseEvent event) {
        String playerAttacksNPC = theController.getTheModel().getPlayer().attack(
                theController.getTheModel().getCurrentRoom().getNPCViewWrappers().get(
                        0).getNpc());
        ImageView imageView = theController.getTheView().handleActionBubble(
                RPGView.ImageType.POW,
                playerAttacksNPC);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        theController.continueAttack(imageView);
                    }
                });
            }
        }).start();
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
                if (npcInCurrentRoom.isIsAlive()) {
                    String dialog = theController.getTheModel().getPlayer().talk(
                            npcInCurrentRoom);
                    theController.getTheView().updateStoryText(
                            npcInCurrentRoom.getName() + ": " + dialog);
                }
                else {
                    theController.getTheView().updateStoryText(
                            theController.getTheModel().getPlayer().search(
                                    npcInCurrentRoom));
                }
            } catch (NullPointerException ex) {
            }
        }
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
                RPGView.ImageType.UPARROW)) {
            handleUpArrow(event);
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                RPGView.ImageType.DOWNARROW)) {
            handleDownArrow(event);
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                RPGView.ImageType.LEFTARROW)) {
            handleLeftArrow(event);
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                RPGView.ImageType.RIGHTARROW)) {
            handleRightArrow(event);
        }
        else if (event.getSource() == theController.getTheView().getImageViews().get(
                RPGView.ImageType.ATTACK)) {
            handleAttack(event);
        }
        else if (event.getEventType().equals(MouseEvent.DRAG_DETECTED)) {
            handleDrag(event);
        }
        else {
            handleNPC(event);
        }

    }
}
