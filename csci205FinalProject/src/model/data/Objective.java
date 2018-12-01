/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 30, 2018
  * Time: 11:19:01 PM
  *
  * Project: csci205FinalProject
  * Package: model.data
  * File: Objective
  * Description: This file contains Objective.
  * ****************************************
 */
package model.data;

/**
 *
 * @author ishk001
 */
public class Objective {

    /**
     * Value to determine if an objective is complete
     */
    private boolean done;

    public Objective(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
