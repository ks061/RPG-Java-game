/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 28, 2018
  * Time: 1:29:59 PM
  *
  * Project: csci205FinalProject
  * Package: view.wrapper
  * File: PlayerImagpeViewWraper
  * Description: This file contains PlayerImagpeViewWraper.
  * ****************************************
 */
package view.wrapper;

import java.io.File;
import java.util.EnumMap;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import model.character.Player;
import model.data.Properties;
import model.item.ItemType;
import view.ImageKey;

/**
 * Wrapper class for Player which stores where the player is on the screen and
 * provides support for equipping weapons armor and shields.
 *
 * @author ks061 lts010
 */
public final class PlayerImageViewWrapper extends ImageViewWrapper {

    private final Point2D location;
    private final Pane playerPane;

    public static final Point2D PLAYER_PANEL_FACE_LOCATION = new Point2D(55, 0);
    public static final Point2D PLAYER_PANEL_BODY_LOCATION = new Point2D(24, 55);
    public static final Point2D PLAYER_PANEL_ARMOR_LOCATION = new Point2D(47, 77);
    public static final Point2D PLAYER_PANEL_SHIELD_LOCATION = new Point2D(80,
                                                                           90);
    public static final Point2D PLAYER_PANEL_WEAPON_LOCATION = new Point2D(0, 50);

    private final EnumMap<ImageKey, ItemImageViewWrapper> imageViews;

    /**
     * Explicit constructor that initializes an Player, image view of the Player
     * at a location, image width, and image height.
     *
     * @param player Player character
     * @param imageViews list of image views for the player
     * @param location location where the image of the Player should go in the
     * center pane of the game
     *
     * @author ks061 lts010
     */
    public PlayerImageViewWrapper(Player player, Point2D location,
                                  EnumMap<ImageKey, ItemImageViewWrapper> imageViews) {
        super(player, new Image(
              new File(Properties.PLAYER_FACE_FILE_PATH).toURI().toString()),
              location, ImageKey.FACE);

        this.location = location;
        this.imageViews = imageViews;

        createImageViews();

        playerPane = new Pane();
        playerPane.setLayoutX(location.getX());
        playerPane.setLayoutY(location.getY());
        playerPane.getChildren().add(imageViews.get(ImageKey.FACE));
        playerPane.getChildren().add(imageViews.get(ImageKey.BODY));
        playerPane.getChildren().add(imageViews.get(ImageKey.BLANK_ARMOR));
        playerPane.getChildren().add(imageViews.get(ImageKey.BLANK_SHIELD));
        playerPane.getChildren().add(imageViews.get(ImageKey.BLANK_WEAPON));

    }

    /**
     * Refreshes the player view
     *
     * @param parent parent pane containing the player view
     *
     * @author ks061
     */
    public void refresh(Pane parent) {
        if (!(parent.getChildren().contains(playerPane))) {
            parent.getChildren().add(playerPane);
        }

        playerPane.getChildren().clear();
        playerPane.getChildren().add(imageViews.get(ImageKey.FACE));
        playerPane.getChildren().add(imageViews.get(ImageKey.BODY));

        ItemImageViewWrapper tempImageView;
        if (getPlayer().getArmor() != null) {
            tempImageView = imageViews.get(
                    getPlayer().getArmor().getImageViewKey());
            tempImageView.setLayoutX(
                    PLAYER_PANEL_ARMOR_LOCATION.getX() - tempImageView.getLayoutBounds().getMinX());
            tempImageView.setLayoutY(
                    PLAYER_PANEL_ARMOR_LOCATION.getY() - tempImageView.getLayoutBounds().getMinY());
            playerPane.getChildren().add(tempImageView);
        }
        else {
            playerPane.getChildren().add(imageViews.get(ImageKey.BLANK_ARMOR));
        }

        if (getPlayer().getShield() != null) {
            tempImageView = imageViews.get(
                    getPlayer().getShield().getImageViewKey());
            tempImageView.setLayoutX(
                    PLAYER_PANEL_SHIELD_LOCATION.getX() - tempImageView.getLayoutBounds().getMinX());
            tempImageView.setLayoutY(
                    PLAYER_PANEL_SHIELD_LOCATION.getY() - tempImageView.getLayoutBounds().getMinY());
            playerPane.getChildren().add(tempImageView);;
        }
        else {
            playerPane.getChildren().add(imageViews.get(ImageKey.BLANK_SHIELD));
        }

        if (getPlayer().getWeapon() != null) {
            tempImageView = imageViews.get(
                    getPlayer().getWeapon().getImageViewKey());
            tempImageView.setLayoutX(
                    PLAYER_PANEL_WEAPON_LOCATION.getX() - tempImageView.getLayoutBounds().getMinX());
            tempImageView.setLayoutY(
                    PLAYER_PANEL_WEAPON_LOCATION.getY() - tempImageView.getLayoutBounds().getMinY());
            playerPane.getChildren().add(tempImageView);
        }
        else {
            playerPane.getChildren().add(imageViews.get(ImageKey.BLANK_WEAPON));
        }

    }

    /**
     * Creates BLANK_ARMOR, BLANK_WEAPON, and BLANK_SHIELD this items are place
     * holders for equipment and service as the target for drag and drop to
     * equip the player with actual equipment.
     *
     * @author lts010
     */
    public void createImageViews() {
        // If equipment exists, exit method
        if (imageViews.get(ImageKey.BLANK_ARMOR) != null) {
            return;
        }

        // Creates face
        ItemImageViewWrapper itemImageViewWrapper;
        String imagePath = new File(Properties.PLAYER_FACE_FILE_PATH).toURI().toString();
        Image image = new Image(imagePath);
        itemImageViewWrapper = new ItemImageViewWrapper(image,
                                                        PLAYER_PANEL_BODY_LOCATION,
                                                        ItemType.CONSUMABLE,
                                                        ImageKey.FACE);
        itemImageViewWrapper.setX(PLAYER_PANEL_FACE_LOCATION.getX());
        itemImageViewWrapper.setY(PLAYER_PANEL_FACE_LOCATION.getY());
        imageViews.put(ImageKey.FACE, itemImageViewWrapper);

        // Creates body
        imagePath = new File(Properties.PLAYER_BODY_FILE_PATH).toURI().toString();
        image = new Image(imagePath);
        itemImageViewWrapper = new ItemImageViewWrapper(image,
                                                        PLAYER_PANEL_BODY_LOCATION,
                                                        ItemType.CONTROL,
                                                        ImageKey.BODY);
        itemImageViewWrapper.setY(55);
        imageViews.put(ImageKey.BODY, itemImageViewWrapper);

        // Creates BLANK_ARMOR
        imagePath = new File(Properties.BLANK_ARMOR_FILE_PATH).toURI().toString();
        image = new Image(imagePath);
        itemImageViewWrapper = new ItemImageViewWrapper(image,
                                                        PLAYER_PANEL_ARMOR_LOCATION,
                                                        ItemType.ARMOR,
                                                        ImageKey.BLANK_ARMOR);
        itemImageViewWrapper.setX(PLAYER_PANEL_ARMOR_LOCATION.getX());
        itemImageViewWrapper.setY(PLAYER_PANEL_ARMOR_LOCATION.getY());
        itemImageViewWrapper.setOpacity(0);
        imageViews.put(ImageKey.BLANK_ARMOR, itemImageViewWrapper);

        // Creates BLANK_SHIELD
        imagePath = new File(Properties.BLANK_SHIELD_FILE_PATH).toURI().toString();
        image = new Image(imagePath);
        itemImageViewWrapper = new ItemImageViewWrapper(image,
                                                        PLAYER_PANEL_SHIELD_LOCATION,
                                                        ItemType.SHIELD,
                                                        ImageKey.BLANK_SHIELD);
        itemImageViewWrapper.setX(PLAYER_PANEL_SHIELD_LOCATION.getX());
        itemImageViewWrapper.setY(PLAYER_PANEL_SHIELD_LOCATION.getY());
        itemImageViewWrapper.setOpacity(0);
        imageViews.put(ImageKey.BLANK_SHIELD, itemImageViewWrapper);

        // Creates BLANK_WEAPON
        imagePath = new File(Properties.BLANK_WEAPON_FILE_PATH).toURI().toString();
        image = new Image(imagePath);
        itemImageViewWrapper = new ItemImageViewWrapper(image,
                                                        PLAYER_PANEL_WEAPON_LOCATION,
                                                        ItemType.WEAPON,
                                                        ImageKey.BLANK_WEAPON);
        itemImageViewWrapper.setX(PLAYER_PANEL_WEAPON_LOCATION.getX());
        itemImageViewWrapper.setY(PLAYER_PANEL_WEAPON_LOCATION.getY());
        itemImageViewWrapper.setOpacity(0);
        imageViews.put(ImageKey.BLANK_WEAPON, itemImageViewWrapper);

    }

    /**
     * Gets the Player character
     *
     * @return Player character
     *
     * @author ks061
     */
    public Player getPlayer() {
        return (Player) super.getWrappedObject();
    }

    /**
     * Gets the player display pane
     *
     * @return player display pane
     *
     * @author ks061
     */
    public Pane getPlayerPane() {
        return playerPane;
    }

}
