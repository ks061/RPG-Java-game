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
import model.item.Item;

/**
 * Wrapper class for Item including Item, image of the Item, and location where
 * the image of the Item should go in the center pane of the game
 *
 * @author ks061
 */
public class ItemImageViewWrapper extends ImageViewWrapper {

    public static final int ITEM_VIEW_WIDTH = 30;
    public static final int ITEM_VIEW_HEIGHT = 30;

    public ItemImageViewWrapper(Item item, String imageFilename,
                                Point2D location) {
        super(item, imageFilename, location, ITEM_VIEW_WIDTH, ITEM_VIEW_HEIGHT);
    }

    /**
     * Gets the item
     *
     * @return item
     *
     * @author ks061
     */
    public Item getItem() {
        return (Item) super.getWrappedObject();
    }

}
