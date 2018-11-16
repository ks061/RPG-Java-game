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

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.character.NPC;

/**
 * Wrapper class for NPC including NPC, image of the NPC, and location where the
 * image of the NPC should go in the center pane of the game
 *
 * @author ks061
 */
public class NPCImageViewWrapper {

    private final NPC npc;
    private ImageView npcImageView;

    public static final int NPC_VIEW_WIDTH = 50;
    public static final int NPC_VIEW_HEIGHT = 50;

    public static final String IMAGE_PATH_PARENT_DIR = "img/";

    /**
     * NPCView is an explicit constructor that initializes an NPC character,
     * image of the NPC, and location of the NPC in the view
     *
     * @param npc NPC character
     * @param imageFilename filename of the image of the NPC
     * @param location location where the image of the NPC should go in the
     * center pane of the game
     *
     * @author ks061
     */
    public NPCImageViewWrapper(NPC npc, String imageFilename, Point2D location) {
        this.npc = npc;

        String imagePath = IMAGE_PATH_PARENT_DIR + imageFilename;
        Image npcImage = new Image(imagePath);

        this.npcImageView = new ImageView();
        this.npcImageView.setImage(npcImage);
        this.npcImageView.setFitWidth(NPC_VIEW_WIDTH);
        this.npcImageView.setFitHeight(NPC_VIEW_HEIGHT);
        this.npcImageView.setX(location.getX());
        this.npcImageView.setY(location.getY());
    }

    /**
     * Gets the NPC character
     *
     * @return NPC character
     *
     * @author ks061
     */
    public NPC getNpc() {
        return npc;
    }

    /**
     * Gets the image view of the NPC
     *
     * @return image view of the NPC
     *
     * @author ks061
     */
    public ImageView getNpcImageView() {
        return npcImageView;
    }

}
