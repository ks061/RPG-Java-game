/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 14, 2018
  * Time: 12:40:06 PM
  *
  * Project: csci205FinalProject
  * Package: view
  * File: NPCImageViewWrapper
  * Description: This file contains NPCImageViewWrapper.
  * ****************************************
 */
package view.wrapper;

import java.io.File;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.character.NPC;
import view.RPGView;

/**
 * Wrapper class for NPC including NPC, image of the NPC, and location where the
 * image of the NPC should go in the center pane of the game
 *
 * @author ks061
 */
public class NPCImageViewWrapper extends ImageViewWrapper {

    public static final int NPC_VIEW_WIDTH = 50;
    public static final int NPC_VIEW_HEIGHT = 70;

    public static final String IMAGE_PATH_PARENT_DIR = "img/";

    /**
     * Explicit constructor that initializes an NPC character, image view of the
     * NPC at a location, image width, and image height.
     *
     * @param npc NPC character
     * @param imageFilename filename of the image of the NPC
     * @param location location where the image of the NPC should go in the
     * center pane of the game
     *
     * @author ks061
     */
    public NPCImageViewWrapper(NPC npc, String imageFilename, Point2D location) {
        super(npc, new Image(
              new File(IMAGE_PATH_PARENT_DIR + imageFilename).toURI().toString()),
              location, RPGView.ImageType.NPC, NPC_VIEW_WIDTH, NPC_VIEW_HEIGHT);
    }

    /**
     * Gets the NPC character
     *
     * @return NPC character
     *
     * @author ks061
     */
    public NPC getNpc() {
        return (NPC) super.getWrappedObject();
    }

}
