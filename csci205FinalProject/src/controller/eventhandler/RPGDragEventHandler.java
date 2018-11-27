/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 26, 2018
  * Time: 8:47:40 PM
  *
  * Project: csci205FinalProject
  * Package: controller.eventhandler
  * File: RPGDragEventHandler
  * Description: This file contains RPGDragEventHandler.
  * ****************************************
 */
package controller.eventhandler;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

/**
 * Drag event handler for the controller
 *
 * @author lts010, ks061
 */
public class RPGDragEventHandler implements EventHandler<DragEvent> {

    /**
     * Handles drag events
     *
     * @param event drag event
     *
     * @author ks061, lts010
     */
    @Override
    public void handle(DragEvent event) {
        System.out.println("drag event = " + event.toString());
        if (event.equals(DragEvent.DRAG_OVER)) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }
}
