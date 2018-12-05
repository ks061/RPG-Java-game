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
     * Initializes the application
     *
     * @throws Exception if an unexpected issue arises
     *
     * @author ks061
     */
    @Override
    public void init() throws Exception {
        super.init();
        String playerName = gameIntro();
        theModel = new RPGModel(playerName);
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
        theCtrl = new RPGController(theModel, theView);

        Scene scene = new Scene(this.theView.getRootNode());

        primaryStage.setTitle("RPG Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        theCtrl.refresh();
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

    /**
     * The intro of the game, the first thing the user sees
     *
     * @return the player's name
     *
     * @author cjs051
     */
    private String gameIntro() {
        String introMessage = "Welcome to Bibbin's Adventures (CSCI 205 Edition)! Prepare yourself for the most challenging quest of all time : surviving the class of Computer Science 205 : Software Engineering and Design at Bucknell University.\n"
                              + " As a student, you will navigate Bucknell's campus collecting items and talking with people to survive the most difficult course in Bucknell history. But beware, not all people you encounter will be friendly or willing to assist you. \n"
                              + "You must interact with people you should listen to and people you must fight. You will be able to view your inventory containing your collected items and weapons at all times. Pay close attention to your health; if it reaches zero, the game is over.\n"
                              + "You pass the class when you defeat the most dangerous character: your angry professor."
                              + "\nFIRST TASK: Navigate the rooms to find Dr. Dance. Good luck!";
        int choice = JOptionPane.showOptionDialog(null, //asks user what they want to do
                                                  introMessage,
                                                  "Choose your character",
                                                  JOptionPane.DEFAULT_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE,
                                                  null,
                                                  PlayerChoices.values(),
                                                  null);
        if (choice == JOptionPane.CLOSED_OPTION) { //if statement for if user exits
            System.exit(0);
        }
        else if (choice == PlayerChoices.LOGAN.ordinal()) {
            return PlayerChoices.LOGAN.getName();
        }
        else if (choice == PlayerChoices.KARTIKEYA.ordinal()) {
            return PlayerChoices.KARTIKEYA.getName();
        }
        else if (choice == PlayerChoices.CLAUDIA.ordinal()) {
            return PlayerChoices.CLAUDIA.getName();
        }
        return PlayerChoices.JASON.getName();

    }
}
