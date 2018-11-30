/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 16, 2018
  * Time: 12:28:00 PM
  *
  * Project: csci205FinalProject
  * Package: view.wrapper
  * File: ImageViewWrapper
  * Description: This file contains ImageViewWrapper.
  * ****************************************
 */
package view.wrapper;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.ImageKey;

/**
 * Wrapper class for an object, image of the object, and location where the
 * object should go in the center pane of the game
 *
 * @author ks061, lts010
 */
public abstract class ImageViewWrapper extends ImageView {

    /**
     * Model object encapsulated by this wrapper
     */
    private Object wrappedObject;
    /**
     * Type of image
     */
    private ImageKey imageKey;

    /**
     * Explicit constructor initializes the ImageView
     *
     * @param wrappedObject model object wrapper encapsulates
     * @param image image representing the model object encapsulated by this
     * wrapper
     * @param location location of the image view on the screen
     * @param imageType type of the image
     *
     * @author ks061, lts010
     *
     */
    public ImageViewWrapper(Object wrappedObject, Image image, Point2D location,
                            ImageKey imageType) {
        super(image);
        this.wrappedObject = wrappedObject;
        this.setX(location.getX());
        this.setY(location.getY());
        this.imageKey = imageType;
    }

    /**
     * Explicit constructor initializes the ImageView with the width and height
     * of the view
     *
     * @param wrappedObject model object wrapper encapsulates
     * @param image image representing the model object encapsulated by this
     * wrapper
     * @param location location of the image view on the screen
     * @param imageType type of the image
     * @param viewWidth width of the view
     * @param viewHeight width of the height
     *
     * @author ks061, lts010
     */
    public ImageViewWrapper(Object wrappedObject, Image image, Point2D location,
                            ImageKey imageType,
                            int viewWidth, int viewHeight) {
        this(wrappedObject, image, location, imageType);
        this.setFitWidth(viewWidth);
        this.setFitHeight(viewHeight);
    }

    /**
     * Returns the wrapped object
     *
     * @return wrapped object
     *
     * @author ks061, lts010
     */
    protected Object getWrappedObject() {
        return this.wrappedObject;
    }

    /**
     * Gets the type of the image
     *
     * @return type of the image
     *
     * @author ks061, lts010
     */
    public ImageKey getImageType() {
        return imageKey;
    }

}
