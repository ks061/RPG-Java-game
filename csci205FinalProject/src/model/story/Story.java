/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 10, 2018
  * Time: 8:53:32 PM
  *
  * Project: csci205FinalProject
  * Package: story
  * File: Story
  * Description: This file contains Story.
  * ****************************************
 */
package model.story;

import java.util.ArrayList;
import java.util.Random;
import model.character.NPC;
import model.item.Equipment;
import static model.item.EquipmentType.*;

/**
 * Story contains the starting data and/or story-line behind the game map and
 * the game itself
 *
 * @author cjs051, ks061
 */
public class Story {

    private static Story instance;

    private ArrayList<RoomContent> roomContents;

    /**
     * Constructs Story, containing the starting data and/or story-line behind
     * the game map and game itself
     *
     * @author cjs051, ks061
     */
    private Story() {
        roomContents = new ArrayList<>();

        NPC npc1 = new NPC("Dr. Dance", 20, 0, 0, new ArrayList<String>() {
                       {
                           add("Class dismissed. Work on your"
                               + " homework and DON’T PROCRASTINATE...and R E F A C T O R ! Your textbook and the "
                               + "Java API are your best friends");
                           add("Don't forget to REFACTOR!");
                       }
                   },
                           true);
        NPC npc2 = new NPC("Muz", 20, 0, 0, new ArrayList<String>() {
                       {
                           add("Yo homie! We got this!");
                           add("Yo homie, don't give up! You can do this!");
                       }
                   }, true);
        NPC npc3 = new NPC("Reef", 20, 0, 0, new ArrayList<String>() {
                       {
                           add("Come thru, bro");
                           add("My name is not Reef");
                       }
                   }, true);
        NPC npc4 = new NPC("Izi", 25, 2, 3, new ArrayList<String>() {
                       {
                           add("Food insecurity is a major problem on Bucknell's campus!");
                           add("I'm gonna email Bravman.");
                       }
                   }, false);
        NPC npc5 = new NPC("Angry Dance", 30, 8, 8,
                           new ArrayList<String>() {
                       {
                           add("Karti B, stop slacking me so many questions!");
                           add("I told you to R E F A C T O R !!!");
                       }
                   }, false);
        NPC npc6 = new NPC("Dr. King", 20, 0, 0, new ArrayList<String>() {
                       {
                           add("Watch the videos!");
                           add("Take Data Science!");
                       }
                   }, true);
        NPC npc7 = new NPC("Martin", 25, 4, 3,
                           new ArrayList<String>() {
                       {
                           add("I'll grade labs 9-12 by Monday!");
                           add("I'll grade labs 9-12 by Friday!");
                       }
                   }, false);
        NPC npc8 = new NPC("Dustin", 25, 4, 3, new ArrayList<String>() {
                       {
                           add("something");
                           add("something");
                       }
                   }, false);
        NPC npc9 = new NPC("someone", 20, 0, 0, new ArrayList<String>() {
                       {
                           add("something");
                           add("something");
                       }
                   }, true);

        Equipment weapon1 = new Equipment("weapon1", 0, 1, 0, 1, WEAPON);
        Equipment shield1 = new Equipment("shield1", 1, 0, 0, 1, SHIELD);
        Equipment armor1 = new Equipment("armor1", 0, 0, 1, 1, ARMOR);

        Equipment weapon2 = new Equipment("weapon2", 0, 2, 0, 1, WEAPON);
        Equipment shield2 = new Equipment("shield2", 2, 0, 0, 1, SHIELD);
        Equipment armor2 = new Equipment("armor2", 0, 0, 2, 1, ARMOR);

        Equipment weapon3 = new Equipment("weapon3", 0, 3, 0, 1, WEAPON);
        Equipment shield3 = new Equipment("shield3", 3, 0, 0, 1, SHIELD);
        Equipment armor3 = new Equipment("armor3", 0, 0, 3, 1, ARMOR);

        RoomContent rc1 = new RoomContent("room1", npc1, weapon1);
        RoomContent rc2 = new RoomContent("room2", npc2, shield1);
        RoomContent rc3 = new RoomContent("room3", npc3, armor1);
        RoomContent rc4 = new RoomContent("room4", npc4, weapon2);
        RoomContent rc5 = new RoomContent("room5", npc5, shield2);
        RoomContent rc6 = new RoomContent("room6", npc6, armor2);
        RoomContent rc7 = new RoomContent("room7", npc7, weapon3);
        RoomContent rc8 = new RoomContent("room8", npc8, shield3);
        RoomContent rc9 = new RoomContent("room9", npc9, armor3);

        roomContents.add(rc1);
        roomContents.add(rc2);
        roomContents.add(rc3);
        roomContents.add(rc4);
        roomContents.add(rc5);
        roomContents.add(rc6);
        roomContents.add(rc7);
        roomContents.add(rc8);
        roomContents.add(rc9);
    }

    /**
     * Gets the singular instance of Story available to the process; initializes
     * a new instance if none already exists
     *
     * @return the instance of Story
     *
     * @author ks061
     */
    public static Story getInstance() {
        if (instance == null) {
            instance = new Story();
        }
        return instance;
    }

    /**
     * Returns a random room content object
     *
     * @return random room content object
     *
     * @author ks061
     */
    public RoomContent getRandomRoomContent() {
        return roomContents.remove(new Random().nextInt(roomContents.size()));
    }

}
