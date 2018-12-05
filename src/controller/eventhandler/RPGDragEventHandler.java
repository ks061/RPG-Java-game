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

import controller.RPGController;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import model.item.ConsumableItem;
import model.item.Equipment;
import model.item.ItemType;
import view.ImageKey;
import view.wrapper.ItemImageViewWrapper;

/**
 * Drag event handler for the controller
 *
 * @author lts010, ks061
 */
public class RPGDragEventHandler implements EventHandler<DragEvent> {

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
    public RPGDragEventHandler(RPGController theController) {
        this.theController = theController;
    }

    /**
     * Handles drag events
     *
     * @param event drag event
     *
     * @author ks061, lts010
     */
    @Override
    public void handle(DragEvent event) {
        if (!(event.getGestureSource() instanceof ItemImageViewWrapper)) {
            System.out.println("received unexpected event:" + event.toString());
            return;
        }

        if (event.getEventType().equals(DragEvent.DRAG_OVER)) {
            handleDragOver(event);

        }
        else if (event.getEventType().equals(DragEvent.DRAG_ENTERED)) {
            if (isBlankMatch(event)) {
                ((ItemImageViewWrapper) event.getSource()).setOpacity(100);
            }

        }
        else if (event.getEventType().equals(DragEvent.DRAG_EXITED)) {
            if (isBlankMatch(event)) {
                ((ItemImageViewWrapper) event.getSource()).setOpacity(0);;
            }

        }
        else if (event.getEventType().equals(DragEvent.DRAG_DROPPED)) {
            handleDragDropped(event);
        }
    }

    /**
     * Handles DragOver events
     *
     * @param event drag event
     *
     * @author ks061, lts010
     */
    private void handleDragOver(DragEvent event) {
        ItemImageViewWrapper source = (ItemImageViewWrapper) event.getGestureSource();
        if (source == event.getTarget()) {
            return;
        }
        if (event.getTarget() instanceof ItemImageViewWrapper) {
            ItemImageViewWrapper target = (ItemImageViewWrapper) event.getTarget();

            // Checks to see if the player is being equiped or get food
            if (target.getParent() == theController.getPlayerImageView().getPlayerPane()
                && target.getItemType() == source.getItemType()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

            }
            // Checks to see if it is being transfered to inventory.
            else if (target == theController.getTheView().getImageViews().get(
                    ImageKey.INVENTORY)) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
        }
        // Otherwise, it's being dragged over a pane
        else {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }

    /**
     * Handles DragDropped events
     *
     * @param event drag event
     *
     * @author ks061, lts010
     */
    private void handleDragDropped(DragEvent event) {
        ItemImageViewWrapper source = (ItemImageViewWrapper) event.getGestureSource();
        boolean returnVal = false;

        // If transfering to inventory
        if (event.getTarget() == theController.getTheView().getImageViews().get(
                ImageKey.INVENTORY)
            || event.getTarget() == theController.getTheView().getInventoryFP()) {
            returnVal = transferToInventory(event, source);
        } // If giving it to the player, eat it or equip it
        else if (event.getTarget() instanceof ItemImageViewWrapper
                 && ((ItemImageViewWrapper) event.getTarget()).getParent()
                    == theController.getPlayerImageView().getPlayerPane()) {
            returnVal = transferToPlayer(event, source);
        } // Else put the item in the room
        else if (event.getTarget() == theController.getTheView().getCenterPane()) {
            returnVal = transferToRoom(event, source);
        }
        event.setDropCompleted(returnVal);
        theController.refresh();
    }

    /**
     * Transfers the source item to the and drops it where the player puts it.
     *
     * @param event drag event
     * @param source the source of the drag gesture.
     *
     * @author ks061, lts010
     */
    private boolean transferToRoom(DragEvent event, ItemImageViewWrapper source) {

        // Sets the new room location accounting for the cursor being in the center of the image
        source.setLocation(new Point2D(
                event.getX() - source.getImage().getWidth() / 2,
                event.getY() - source.getImage().getHeight() / 2));
        // If equiped, add to the room and unequip
        if (source.getParent() == theController.getPlayerImageView().getPlayerPane()) {
            theController.getTheModel().getCurrentRoom().getHiddenItems().add(
                    source.getItem());
            theController.getTheModel().getPlayer().adjustCharacterStatisticsFromUnequip(
                    (Equipment) source.getItem());
            switch (source.getItemType()) {
                case ARMOR:
                    theController.getPlayerImageView().getPlayer().setArmor(null);
                    break;
                case SHIELD:
                    theController.getPlayerImageView().getPlayer().setShield(
                            null);
                    break;
                case WEAPON:
                    theController.getPlayerImageView().getPlayer().setWeapon(
                            null);
                    break;
            }

        } // If in players inventory, add to room and remove from inventory
        else if (source.getParent() == theController.getTheView().getInventoryFP()) {
            theController.getTheModel().getCurrentRoom().getHiddenItems().add(
                    source.getItem());
            theController.getTheModel().getPlayer().getInventory().remove(
                    source.getItem());
        }
        return true;
    }

    /**
     * Transfers the source item to the player who will equip it, if its a piece
     * of Armor, Shield or Weapon, or eat it if it's consumable
     *
     * @param event drag event
     * @param source the source of the drag gesture.
     *
     * @author ks061, lts010
     */
    private boolean transferToPlayer(DragEvent event,
                                     ItemImageViewWrapper source) {
        // If its food, consume it, which will remove it if it's in inventory.
        // Remove it if it's in the room
        if (((ItemImageViewWrapper) event.getTarget()).getItemType() == ItemType.CONSUMABLE) {
            theController.getTheModel().getCurrentRoom().getHiddenItems().remove(
                    source.getItem());
            theController.getPlayerImageView().getPlayer().consume(
                    (ConsumableItem) source.getItem());
            theController.getTheModel().getRandomOtherRoom().getHiddenItems().add(
                    source.getItem());
        } // If already in inventory, just equip it
        else if (theController.getPlayerImageView().getPlayer().getInventory().contains(
                source.getItem())) {
            theController.getPlayerImageView().getPlayer().equip(
                    (Equipment) source.getItem());
        } // If the inventory is full, the previously equiped item will be add to the room.
        else if (theController.getPlayerImageView().getPlayer().isInventoryFull()) {
            switch (source.getItemType()) {
                case ARMOR:
                    Equipment armor = theController.getPlayerImageView().getPlayer().getArmor();
                    if (armor != null) {
                        theController.getTheModel().getCurrentRoom().getHiddenItems().add(
                                armor);
                    }
                    theController.getPlayerImageView().getPlayer().setArmor(
                            (Equipment) source.getItem());
                    break;
                case SHIELD:
                    Equipment shield = theController.getPlayerImageView().getPlayer().getShield();
                    if (shield != null) {
                        theController.getTheModel().getCurrentRoom().getHiddenItems().add(
                                shield);
                    }
                    theController.getPlayerImageView().getPlayer().setShield(
                            (Equipment) source.getItem());
                    break;
                case WEAPON:
                    Equipment weapon = theController.getPlayerImageView().getPlayer().getWeapon();
                    if (weapon != null) {
                        theController.getTheModel().getCurrentRoom().getHiddenItems().add(
                                weapon);
                    }
                    theController.getPlayerImageView().getPlayer().setWeapon(
                            (Equipment) source.getItem());
                    break;
            }
            theController.getTheModel().getCurrentRoom().getHiddenItems().remove(
                    source.getItem());
            // Else add it to inventory and equip, which removes it from inventory
            // Removes it from room
        }
        else {
            theController.getPlayerImageView().getPlayer().addToInventory(
                    source.getItem());
            String result = theController.getPlayerImageView().getPlayer().equip(
                    (Equipment) source.getItem());
            theController.getTheModel().getCurrentRoom().getHiddenItems().remove(
                    source.getItem());
        }
        return (true);
    }

    /**
     * Transfers the source item to the player's inventory
     *
     * @param event drag event
     * @param source the source of the drag gesture.
     *
     * @author ks061, lts010
     */
    private boolean transferToInventory(DragEvent event,
                                        ItemImageViewWrapper source) {
        boolean returnVal = false;
        System.out.println("transfer to inventory");
        // If already in inventory, can't do the transfer
        if (theController.getPlayerImageView().getPlayer().getInventory().contains(
                source.getItem())) {
            theController.getTheView().setStoryText(
                    "Item is already in inventory");
            // If inventory is full, can't do the transfer
        }
        else if (theController.getPlayerImageView().getPlayer().isInventoryFull()) {
            theController.getTheView().setStoryText(
                    "Your inventory is already full");
        } // If equipped, unequip it
        else if (source.getItem() instanceof Equipment
                 && theController.getPlayerImageView().getPlayer().isEquipped(
                        (Equipment) source.getItem())) {
            theController.getPlayerImageView().getPlayer().unequip(
                    (Equipment) source.getItem());
            returnVal = true;
        } // If it's in the room, add to inventory and remove from room
        else {
            System.out.println("--------add to inventory-----");
            theController.getPlayerImageView().getPlayer().addToInventory(
                    source.getItem());
            theController.getTheModel().getCurrentRoom().getHiddenItems().remove(
                    source.getItem());
            returnVal = true;
        }

        return (returnVal);
    }

    /**
     * Checks a DragEvent to see if the gestureSource is a blank item, i.e.
     * BLANK_ARMOR, BLANK_SHIELD, or BLANK_WEAPON, and the target is equipment
     * of the corresponding ItemType, ie ARMOR, SHEILD, WEAPON
     *
     * @author ks061, lts010
     * @return true if the items match, false otherwise
     */
    private boolean isBlankMatch(DragEvent event) {

        if (!(event.getSource() instanceof ItemImageViewWrapper)
            || !(event.getGestureSource() instanceof ItemImageViewWrapper)) {
            return (false);
        }
        ItemImageViewWrapper gestureSource = (ItemImageViewWrapper) event.getGestureSource();
        ItemImageViewWrapper target = (ItemImageViewWrapper) event.getTarget();

        return ((target == theController.getTheView().
                 getImageViews().get(ImageKey.BLANK_ARMOR)
                 && gestureSource.getItemType() == ItemType.ARMOR))
               || ((target == theController.getTheView().
                            getImageViews().get(ImageKey.BLANK_SHIELD)
                    && gestureSource.getItemType() == ItemType.SHIELD))
               || ((target == theController.getTheView().
                            getImageViews().get(ImageKey.BLANK_WEAPON)
                    && gestureSource.getItemType() == ItemType.WEAPON));
    }

}
