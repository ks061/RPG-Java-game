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
    private final Image onesImage;
    private final Image tensImage;
    private int value;
    private static final int PREF_SPACING = 5;
    private final int DEFAULT_VALUE = 37;

    /**
     * Creates a status bar of a player statistic
     *
     * @param onesImagePath image representing one unit of the player statistic
     * this status bar represents
     * @param tensImagePath image representing ten units of the player statistic
     * this status bar represents
     * @param leftJustified whether or not the status bar should be left
     * justified
     *
     * @author ks061, lts010
     */
    public StatusBar(String onesImagePath, String tensImagePath,
                     boolean leftJustified) {
        super();
        this.onesImage = getUnitsImage(onesImagePath);
        this.tensImage = getUnitsImage(tensImagePath);
        this.leftJustified = leftJustified;
        this.value = DEFAULT_VALUE;

        formatStatusBar();

        this.refresh();
    }

    /**
     * Formats and aligns the status bar
     *
     * @author ks061, lts010
     */
    private void formatStatusBar() {
        this.setPrefHeight(this.tensImage.getHeight());
        this.setMaxHeight(this.tensImage.getHeight());
        this.setSpacing(PREF_SPACING);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Gets the icon representing
     *
     * @param unitsImagePath file path of the units image
     * @return icon representing
     *
     * @author ks061
     */
    private Image getUnitsImage(String unitsImagePath) {
        String imageURI = new File(unitsImagePath).toURI().toString();
        return new Image(imageURI);
    }

    /**
     * Refreshes the status bar
     *
     * @author lts010, ks061
     */
    public void refresh() {
        int numberOfTens = value / 10;
        if (numberOfTens > 9) {
            numberOfTens = 9;
        }
        int numberOfOnes = value % 10;
        this.getChildren().clear();
        if (leftJustified) {
            this.setAlignment(Pos.CENTER_LEFT);
            for (int i = 0; i < numberOfTens; i++) {
                this.getChildren().add(new ImageView(tensImage));
            }
        }

        for (int i = 0; i < numberOfOnes; i++) {
            this.getChildren().add(new ImageView(onesImage));
        }

        if (!leftJustified) {
            this.setAlignment(Pos.CENTER_RIGHT);
            for (int i = 0; i < numberOfTens; i++) {
                this.getChildren().add(new ImageView(tensImage));
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
