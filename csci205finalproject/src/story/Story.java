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
package story;

import java.util.ArrayList;

/**
 *
 * @author Claudia Shrefler
 * @version 0.1
 */
public class Story {

    private RoomContent rc1;
    private RoomContent rc2;
    private RoomContent rc3;
    private RoomContent rc4;
    private RoomContent rc5;
    private RoomContent rc6;
    private RoomContent rc7;
    private RoomContent rc8;
    private RoomContent rc9;

    private NPC npc1;
    private NPC npc2;
    private NPC npc3;
    private NPC npc4;
    private NPC npc5;
    private NPC npc6;
    private NPC npc7;
    private NPC npc8;
    private NPC npc9;

    private Equipment weapon1;
    private Equipment weapon2;
    private Equipment weapon3;
    private Equipment shield1;
    private Equipment shield2;
    private Equipment shield3;
    private Equipment armor1;
    private Equipment armor2;
    private Equipment armor3;

    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    private Room room5;
    private Room room6;
    private Room room7;
    private Room room8;
    private Room room9;

    private ArrayList<RoomContent> roomContents;

    public Story() {

        npc1 = new NPC("Dr. Dance", 20, null, null,
                       "Class dismissed. Work on your"
                       + " homework and DON’T PROCRASTINATE...and R E F A C T O R ! Your textbook and the "
                       + "Java API are your best friends",
                       "Don't forget to REFACTOR!", true);
        npc2 = new NPC("Muz", 20, null, null, "Yo homie! We got this!",
                       "Yo homie, don't give up! You can do this!", true);
        npc3 = new NPC("Reef", 20, null, null, "Come thru, bro",
                       "My name is not Reef", true);
        npc4 = new NPC("Izi", 25, 2, 3,
                       "Food insecurity is a major problem on Bucknell's campus!",
                       "I'm gonna email Bravman.", false);
        npc5 = new NPC("Angry Dance", 30, 8, 8,
                       "Karti B, stop slacking me so many questions!",
                       "I told you to R E F A C T O R !!!", false);
        npc6 = new NPC("Dr. King", 20, null, null, "Watch the videos!",
                       "Take Data Science!", true);
        npc7 = new NPC("Martin", 25, 4, 3, "I'll grade labs 9-12 by Monday!",
                       "I'll grade labs 9-12 by Friday!", false);
        npc8 = new NPC("Dustin", 25, 4, 3, "something", "something", false);
        npc9 = new NPC("someone", 20, null, null, "something", "something", true);

        weapon1 = new Equipment("weapon1", 0, 1, 0, 1, WEAPON);
        shield1 = new Equipment("shield1", 1, 0, 0, 1, SHIELD);
        armor1 = new Equipment("armor1", 0, 0, 1, 1, ARMOR);

        weapon2 = new Equipment("weapon2", 0, 2, 0, 1, WEAPON);
        shield2 = new Equipment("shield2", 2, 0, 0, 1, SHIELD);
        armor2 = new Equipment("armor2", 0, 0, 2, 1, ARMOR);

        weapon3 = new Equipment("weapon3", 0, 3, 0, 1, WEAPON);
        shield3 = new Equipment("shield3", 3, 0, 0, 1, SHIELD);
        armor3 = new Equipment("armor3", 0, 0, 3, 1, ARMOR);

        rc1 = new RoomContent("room1", npc1, weapon1);
        rc2 = new RoomContent("room2", npc2, shield1);
        rc3 = new RoomContent("room3", npc3, armor1);
        rc4 = new RoomContent("room4", npc4, weapon2);
        rc5 = new RoomContent("room5", npc5, shield2);
        rc6 = new RoomContent("room6", npc6, armor2);
        rc7 = new RoomContent("room7", npc7, weapon3);
        rc8 = new RoomContent("room8", npc8, shield3);
        rc9 = new RoomContent("room9", npc9, armor3);

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

}
