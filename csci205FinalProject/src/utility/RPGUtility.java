/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 29, 2018
  * Time: 12:58:08 AM
  *
  * Project: csci205FinalProject
  * Package: utility
  * File: RPGUtility
  * Description: This file contains RPGUtility.
  * ****************************************
 */
package utility;

import java.io.File;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.item.ItemType;
import view.ImageKey;
import view.wrapper.ItemImageViewWrapper;

/**
 * RPGUtility contains utilities related to playing sounds
 *
 * @author ks061
 */
public class RPGUtility {

    /**
     * File path of the attack sound effect
     */
    public static final String ATTACK_SOUND_EFFECT_FILE_PATH = "wav/batman1.wav";
    /**
     * Amount of time that the thread is paused for while the sound effect has
     * been played just prior
     */
    public static final int THREAD_PAUSE_TIME = 3000;

    /**
     * Takes a file path leading to an image and converts it to an ImageView
     * object
     *
     * @param imagePath file path leading to an object
     * @param itemType type of item
     * @param imageType type of image
     * @return an ImageView wrapper object
     *
     * @author lts010, ks061
     */
    public static ItemImageViewWrapper loadImage(String imagePath,
                                                 ItemType itemType,
                                                 ImageKey imageType) {
        imagePath = new File(imagePath).toURI().toString();
        Image image = new Image(imagePath);
        Point2D location = new Point2D(new Random().nextInt(1000) + 200,
                                       new Random().nextInt(400) + 250);
        return new ItemImageViewWrapper(image, location, itemType, imageType);
    }
}
