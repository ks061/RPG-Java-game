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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import model.data.Story;
import model.item.Item;
import model.item.ItemType;
import view.ImageKey;

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
    /**
     * Tooltip when user hovers over this ImageView
     */
    private final Tooltip tooltip;

    /**
     * Explicit constructor that initializes the image view with an image and
     * location, the item type, and the image type
     *
     * @param image image to be contained within the image view
     * @param location location of the image view in the view
     * @param itemType type of the item
     * @param imageKey type of the image
     *
     * @author ks061, lts010, cjs051
     */
    public ItemImageViewWrapper(Image image, Point2D location, ItemType itemType,
                                ImageKey imageKey) {
        super(Story.getInstance().getItems().get(imageKey), image, location,
              imageKey);
        this.itemType = itemType;
        this.tooltip = new Tooltip(imageKey.getTooltip());
        Tooltip.install(this, this.tooltip);
    }

    /**
     * Gets the item that this image represents.
     *
     * @return the item that this image represents
     *
     * @author ks061, lts010
     */
    public Item getItem() {
        return ((Item) this.getWrappedObject());
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
}
