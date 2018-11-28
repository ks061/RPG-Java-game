/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 24, 2018
  * Time: 11:44:01 PM
  *
  * Project: csci205FinalProject
  * Package: view
  * File: StatusBar
  * Description: This file contains StatusBar.
  * ****************************************
 */
package view;

import java.io.File;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * StatusBar represents a status bar of a player statistic
 *
 * @author lts010, ks061
 */
public class StatusBar extends HBox {

    private final boolean leftJustified;
    private final ArrayList<ImageView> tensImageViews;
    private final ArrayList<ImageView> unitsImageViews;
    private int value;
    private static final int PREF_SPACING = 5;

    /**
     * Represents a status bar of a player statistic
     *
     * @param tensImagePath image representing ten units of the player statistic
     * this status bar represents
     * @param unitsImagePath image representing one unit of the player statistic
     * this status bar represents
     * @param leftJustified whether or not the status bar should be left
     * justified
     *
     * @author ks061, lts010
     */
    public StatusBar(String tensImagePath, String unitsImagePath,
                     boolean leftJustified) {
        super();
        this.unitsImageViews = new ArrayList<>();
        this.tensImageViews = new ArrayList<>();
        this.leftJustified = leftJustified;

        String imageURI = new File(unitsImagePath).toURI().toString();
        Image unitsImage = new Image(imageURI);

        imageURI = new File(tensImagePath).toURI().toString();
        Image tensImage = new Image(imageURI);
        ImageView tempImageView;
        for (int i = 0; i < 10; i++) {
            tempImageView = new ImageView(unitsImage);

            this.unitsImageViews.add(tempImageView);
            this.tensImageViews.add(new ImageView(tensImage));
        }
        this.setPrefHeight(tensImage.getHeight());
        this.setMaxHeight(tensImage.getHeight());
        this.setSpacing(PREF_SPACING);
        this.setAlignment(Pos.CENTER);
        this.value = 37;
        this.refresh();
    }

    /**
     * Refreshes the status bar
     *
     * @author lts010, ks061
     */
    private void refresh() {
        int numberOfTens = value / 10;
        if (numberOfTens > 9) {
            numberOfTens = 9;
        }
        int numberOfUnits = value % 10;
        this.getChildren().clear();
        if (leftJustified) {
            this.setAlignment(Pos.CENTER_LEFT);
            for (int i = 0; i < numberOfTens; i++) {
                this.getChildren().add(tensImageViews.get(i));
            }
        }

        for (int i = 0; i < numberOfUnits; i++) {
            this.getChildren().add(unitsImageViews.get(i));
        }

        if (!leftJustified) {
            this.setAlignment(Pos.CENTER_RIGHT);
            for (int i = 0; i < numberOfTens; i++) {
                this.getChildren().add(tensImageViews.get(i));
            }
        }
    }

    /**
     * Sets the value of the player statistic that this status bar represents
     *
     * @param value value of the player statistic that this status bar
     * represents
     *
     * @author ks061, lts010
     */
    public void setValue(int value) {
        this.value = value;
        this.refresh();
    }

}
