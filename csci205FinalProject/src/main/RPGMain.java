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
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
     * Frame for the JOPtionPane intro screen
     */
    private JOptionPane frame;
    /**
     * Intro message
     */
    private String introMessage;
    /**
     * Player the user chooses
     */
    private String chosenPlayer;

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

        frame = new JOptionPane();
        introMessage = "Welcome to Bibbin's Adventures (CSCI 205 Edition)! Prepare yourself for the most challenging quest of all time : surviving the"
                       + " class of Computer Science 205 : Software Engineering and Design at Bucknell University. As a student, you"
                       + " will navigate Bucknell's campus collecting items and talking with people to survive the most"
                       + " difficult course in Bucknell history. But beware, not all people you encounter will be friendly or willing"
                       + " to assist you. You must decide for yourself who you should listen to and who you must fight. You will be able"
                       + " to view your inventory containing your collected items and weapons at all times. Pay close attention to"
                       + " your health; if it reaches zero, the game is over. You pass the class when you defeat "
                       + "the most dangerous character: your angry professor. \n"
                       + "FIRST TASK: Navigate the rooms to find Dr. Dance. Good luck!";

        theModel = new RPGModel();
        theView = new RPGView(theModel);
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
        introScreen();
        this.theView.loadImages();
        theCtrl = new RPGController(theModel, theView);

        Scene scene = new Scene(this.theView.getRootNode());

        primaryStage.setTitle("RPG Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        theCtrl.refresh();
    }

    /**
     * Displays the intro screen of the game
     *
     */
    public void introScreen() {
        JOptionPane.showMessageDialog(frame, introMessage);
        //Object[] characterChoices = {"Kartikeya", "Logan", "Claudia", "Jason"};
        //this.chosenPlayer = (String) JOptionPane.showInputDialog(frame,
        //"Choose your player: ",
        //"Player Options",
        //JOptionPane.PLAIN_MESSAGE,
        //null,
        //characterChoices,
        //"Kartikeya");
        //setChosenPlayer(this.chosenPlayer);
    }

    /**
     * Sets the player the user chose
     *
     * @param chosenPlayer - String for name of chosen player
     */
    public void setChosenPlayer(String chosenPlayer) {
        this.chosenPlayer = chosenPlayer;
    }

    /**
     * Returns the chosen player
     *
     * @return String for name of player
     */
    public String getChosenPlayer() {
        return this.chosenPlayer;
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
