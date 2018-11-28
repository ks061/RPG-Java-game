/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 16, 2018
  * Time: 12:19:55 PM
  *
  * Project: csci205FinalProject
  * Package: view.wrapper
  * File: ItemImageViewWrapper
  * Description: This file contains ItemImageViewWrapper.
  * ****************************************
 */
package view.wrapper;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.item.ItemType;
import view.RPGView;

/**
 * Wrapper class for Item including Item, image of the Item, and location where
 * the image of the Item should go in the center pane of the game
 *
 * @author ks061
 */
public class ItemImageViewWrapper extends ImageViewWrapper {

    /**
     * Type of item wrapped
     */
    private final ItemType itemType;

    private String tooltip;

    //public static final int ITEM_VIEW_WIDTH = 30;
    // public static final int ITEM_VIEW_HEIGHT = 30;
    /**
     * Explicit constructor that initializes the image view with an image and
     * location, the item type, and the image type
     *
     * @param image image to be contained within the image view
     * @param location location of the image view in the view
     * @param itemType type of the item
     * @param imageType type of the image
     *
     * @author ks061, lts010
     */
    public ItemImageViewWrapper(Image image, Point2D location, ItemType itemType,
                                RPGView.ImageType imageType) {
        super(image, image, location, imageType);
        this.itemType = itemType;
        if (imageType.toString() != imageType.name()) {
            tooltip = new String(imageType.toString());
            this.setTooltip(tooltip);
        }
    }

    /**
     * Gets the type of the encapsulated item
     *
     * @return type of the encapsulated item
     *
     * @author ks061, lts010
     */
    public ItemType getItemType() {
        return itemType;
    }

    private void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    private String getTooltip() {
        return this.tooltip;
    }
}
