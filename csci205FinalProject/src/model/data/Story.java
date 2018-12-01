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
  * Package: model.data
  * File: Story
  * Description: This file contains Story.
  * ****************************************
 */
package model.data;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Point2D;
import model.character.NPC;
import model.character.RPGCharacterStats;
import model.item.Equipment;
import model.item.Item;
import model.item.ItemStatistics;
import static model.item.ItemType.*;
import model.map.RoomContent;
import static view.ImageKey.*;
import view.wrapper.NPCImageViewWrapper;

/**
 * Story contains the starting data and/or story-line behind the game map and
 * the game itself
 *
 * @author cjs051, ks061
 */
public class Story {

    private static Story instance;

    private ArrayList<RoomContent> roomContents;

    private ArrayList<Objective> objectives;

    /**
     * Constructs Story, containing the starting data and/or story-line behind
     * the game map and game itself
     *
     * @author cjs051, ks061
     */
    private Story() {
        roomContents = new ArrayList<>();

        NPC npc1 = new NPC("Dr. Dance", new RPGCharacterStats(20, 0, 0),
                           new ArrayList<String>() {
                       {
                           add("Class dismissed. Work on your"
                               + " homework and DON’T PROCRASTINATE...and R E F A C T O R ! Your textbook and the "
                               + "Java API are your best friends");
                           add("Don't forget to REFACTOR!");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
        NPC npc2 = new NPC("Muz", new RPGCharacterStats(20, 0, 0),
                           new ArrayList<String>() {
                       {
                           add("Yo Dancee, lookin' sharp!");
                           add("Yo homie, don't give up! You can do this!");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
        NPC npc3 = new NPC("Reef", new RPGCharacterStats(20, 0, 0),
                           new ArrayList<String>() {
                       {
                           add("*watching fork-knife*");
                           add("My name is not Reef");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
        NPC npc4 = new NPC("Izi", new RPGCharacterStats(25, 2, 3),
                           new ArrayList<String>() {
                       {
                           add("Food insecurity is a major problem on Bucknell's campus!");
                           add("I'm gonna email Bravman.");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
        NPC npc5 = new NPC("Angry Dance", new RPGCharacterStats(30, 8, 8),
                           new ArrayList<String>() {
                       {
                           add("Karti B, stop slacking me so many questions!");
                           add("I told you to R E F A C T O R !!!");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
        NPC npc6 = new NPC("Dr. Queen", new RPGCharacterStats(20, 0, 0),
                           new ArrayList<String>() {
                       {
                           add("Watch the videos!");
                           add("Take Data Science!");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
        NPC npc7 = new NPC("Martin", new RPGCharacterStats(25, 4, 3),
                           new ArrayList<String>() {
                       {
                           add("I'll grade labs 9-12 by Monday!");
                           add("I'll grade labs 9-12 by Friday!");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
        NPC npc8 = new NPC("Dustin", new RPGCharacterStats(25, 4, 3),
                           new ArrayList<String>() {
                       {
                           add("I recoded Java last night. Took me a couple hours...");
                           add("Are you a double? The thought of you always floats inside my head. B)");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
        NPC npc9 = new NPC("Beck", new RPGCharacterStats(20, 0, 0),
                           new ArrayList<String>() {
                       {
                           add("I'm gonna grade lab13 while skydiving.");
                           add("Ow, my head.");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Hey");
                       }
                   }, true);
//        NPC npc10 = new NPC("Robo-Dustin", new RPGCharacterStats(20, 0, 0),
//                            new ArrayList<String>() {
//                        {
//                            add("I... AM... Hello World... DUSTIN");
//                            add("Error Error Error Error Error Error!!!");
//                        }
//                    }, true);
//        NPC npc11 = new NPC("Dill", new RPGCharacterStats(20, 0, 0),
//                            new ArrayList<String>() {
//                        {
//                            add("I will rule over the world!");
//                            add("Pickles are the best.");
//                        }
//                    }, true);

        //Set 1
        Equipment weapon1 = new Equipment("Pen & Paper",
                                          new ItemStatistics(0, 1, 0, 1),
                                          WEAPON, PEN_AND_PAPER);
        Equipment shield1 = new Equipment("Machine Code",
                                          new ItemStatistics(1, 0, 0, 1),
                                          SHIELD, MACHINE_CODE);
        Equipment armor1 = new Equipment("API",
                                         new ItemStatistics(0, 0, 1, 1),
                                         ARMOR, API);

        //Set 2
        Equipment weapon2 = new Equipment("Notepad++",
                                          new ItemStatistics(0, 2, 0, 1),
                                          WEAPON, NOTEPAD);
        Equipment shield2 = new Equipment("HTML",
                                          new ItemStatistics(2, 0, 0, 1),
                                          SHIELD, HTML);
        Equipment armor2 = new Equipment("Stack Overflow",
                                         new ItemStatistics(0, 0, 2, 1),
                                         ARMOR, STACK_OVERFLOW);

        //Set 3
        Equipment weapon3 = new Equipment("Netbeans",
                                          new ItemStatistics(0, 3, 0, 1),
                                          WEAPON, NETBEANS);
        Equipment shield3 = new Equipment("Java",
                                          new ItemStatistics(3, 0, 0, 1),
                                          SHIELD, JAVA);
        Equipment armor3 = new Equipment("The Winklevoss Twins",
                                         new ItemStatistics(0, 0, 3, 1),
                                         ARMOR, WINKLEVOSS_TWINS);

        Point2D npcLocation = new Point2D(0, 200);
        RoomContent rc1 = new RoomContent("Bana 340",
                                          new NPCImageViewWrapper(npc1,
                                                                  "Dancee.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(weapon1);
                                      }
                                  });
        RoomContent rc2 = new RoomContent("SAE",
                                          new NPCImageViewWrapper(npc2,
                                                                  "Muz.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(shield1);
                                      }
                                  });
        RoomContent rc3 = new RoomContent("Bana 213",
                                          new NPCImageViewWrapper(npc3,
                                                                  "Reef.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(armor1);
                                      }
                                  });
        RoomContent rc4 = new RoomContent("Out and about",
                                          new NPCImageViewWrapper(npc4,
                                                                  "Izi.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(weapon2);
                                      }
                                  });
        RoomContent rc5 = new RoomContent("Penn State University",
                                          new NPCImageViewWrapper(npc5,
                                                                  "AngryDancee.jpg",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(shield2);
                                      }
                                  });
        RoomContent rc6 = new RoomContent("Crackmetal 169",
                                          new NPCImageViewWrapper(npc6,
                                                                  "King.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(armor2);
                                      }
                                  });
        RoomContent rc7 = new RoomContent("Crackmetal 164",
                                          new NPCImageViewWrapper(npc7,
                                                                  "Martin.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(weapon3);
                                      }
                                  });
        RoomContent rc8 = new RoomContent("Secret Lab in Bana",
                                          new NPCImageViewWrapper(npc8,
                                                                  "Dustin.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(shield3);
                                      }
                                  });
        RoomContent rc9 = new RoomContent("In the sky",
                                          new NPCImageViewWrapper(npc9,
                                                                  "Beck.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(armor3);
                                      }
                                  });
//        RoomContent rc10 = new RoomContent("Rooke Chapel",
//                                           new ArrayList<NPCImageViewWrapper>() {
//                                       {
//                                           add(new NPCImageViewWrapper(npc10,
//                                                                       "RoboDustin.png",
//                                                                       npcLocation));
//                                       }
//                                   },
//                                           new ArrayList<Item>() {
//                                       {
//                                           add(armor3);
//                                       }
//                                   });
//        RoomContent rc11 = new RoomContent("Outside of Dustin's Secret Lab",
//                                           new ArrayList<NPCImageViewWrapper>() {
//                                       {
//                                           add(new NPCImageViewWrapper(npc11,
//                                                                       "Dill.png",
//                                                                       npcLocation));
//                                       }
//                                   },
//                                           new ArrayList<Item>() {
//                                       {
//                                           add(armor3);
//                                       }
//                                   });

        roomContents.add(rc1);

        roomContents.add(rc2);

        roomContents.add(rc3);

        roomContents.add(rc4);

        roomContents.add(rc5);

        roomContents.add(rc6);

        roomContents.add(rc7);

        roomContents.add(rc8);

        roomContents.add(rc9);

        ///roomContents.add(rc10);
        //roomContents.add(rc11);
        Objective obj1 = new Objective(false);

        Objective obj2 = new Objective(false);

        Objective obj3 = new Objective(false);

        Objective obj4 = new Objective(false);

        Objective obj5 = new Objective(false);

        Objective obj6 = new Objective(false);

        Objective obj7 = new Objective(false);

        Objective obj8 = new Objective(false);

        Objective obj9 = new Objective(false);

        objectives.add(obj1);

        objectives.add(obj2);

        objectives.add(obj3);

        objectives.add(obj4);

        objectives.add(obj5);

        objectives.add(obj6);

        objectives.add(obj7);

        objectives.add(obj8);

        objectives.add(obj9);

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
        return roomContents.remove(
                new Random().nextInt(
                        roomContents.size()));
    }

    public ArrayList<Objective> getObjectives() {
        return objectives;
    }

}
