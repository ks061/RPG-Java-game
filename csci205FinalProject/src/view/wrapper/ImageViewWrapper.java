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

import java.io.File;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Wrapper class for an object, image of the object, and location where the
 * object should go in the center pane of the game
 *
 * @author ks061
 */
public abstract class ImageViewWrapper {

    private Object wrappedObject;
    private ImageView imageView;

    public static final String IMAGE_PATH_PARENT_DIR = "img/";

    /**
     * Explicit constructor that initializes the NPC character, image view of
     * the NPC at a location,
     *
     * @param wrappedObject
     * @param imageFilename
     * @param location
     * @param viewWidth
     * @param viewHeight
     */
    public ImageViewWrapper(Object wrappedObject, String imageFilename,
                            Point2D location,
                            int viewWidth, int viewHeight) {
        this.wrappedObject = wrappedObject;

        String imagePath = IMAGE_PATH_PARENT_DIR + imageFilename;
        imagePath = new File(imagePath).toURI().toString();
        Image image = new Image(imagePath);

        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(viewWidth);
        this.imageView.setFitHeight(viewHeight);
        this.imageView.setX(location.getX());
        this.imageView.setY(location.getY());
    }

    protected Object getWrappedObject() {
        return this.wrappedObject;
    }

    /**
     * Gets the image view
     *
     * @return image view
     *
     * @author ks061
     */
    public ImageView getImageView() {
        return this.imageView;
    }
}
