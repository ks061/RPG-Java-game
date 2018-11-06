/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 3, 2018
  * Time: 2:03:23 PM
  *
  * Project: csci205FinalProject
  * Package: main
  * File: RPGMain
  * Description: This file contains RPGMain, which runs the RPG game application.
  * ****************************************
 */
package main;

import controller.RPGController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.RPGModel;
import view.RPGView;

/**
 * RPGMain is the runner for the RPG game application.
 *
 * @author ks061
 */
public class RPGMain extends Application {

    /**
     * Model of the application
     */
    private RPGModel theModel;
    /**
     * View of the application
     */
    private RPGView theView;
    /**
     * Control of the application
     */
    private RPGController theCtrl;

    /**
     * Initializes the application
     *
     * @throws Exception if an unexpected issue arises
     *
     * @author ks061
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * Starts the application
     *
     * @param primaryStage stage for the application
     *
     * @author ks061
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(this.theView.getRootNode());

        primaryStage.setTitle("RPG Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Runner for the application
     *
     * @param args the command line arguments
     *
     * @author ks061
     */
    public static void main(String[] args) {
        launch(args);
    }

}
