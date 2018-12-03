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
import java.util.EnumMap;
import java.util.Random;
import javafx.geometry.Point2D;
import model.character.NPC;
import model.character.RPGCharacterStats;
import model.item.ConsumableItem;
import model.item.Equipment;
import model.item.Item;
import model.item.ItemStatistics;
import model.item.ItemType;
import model.map.RoomContent;
import view.ImageKey;
import static view.ImageKey.*;
import view.wrapper.NPCImageViewWrapper;

/**
 * Story contains the starting data and/or story-line behind the game map and
 * the game itself
 *
 * @author cjs051, ks061
 */
public class Story {

    /**
     * The instance of Story
     */
    private static Story instance;
    /**
     * List of contents to put in the rooms
     */
    private final ArrayList<RoomContent> roomContents;

    /**
     * The map that stores all the ItemImageViewWrapper objects using the
     * ImageType enum as a key
     */
    private EnumMap<ImageKey, Item> items;

    /**
     * The final boss of the game
     */
    private NPCImageViewWrapper finalBoss;

    /**
     * Constructs Story, containing the starting data and/or story-line behind
     * the game map and game itself
     *
     * @author cjs051, ks061
     */
    private Story() {
        this.roomContents = new ArrayList<>();
        this.items = new EnumMap<>(ImageKey.class);

        NPC drDance = new NPC("Dr. Dance", new RPGCharacterStats(20, 0, 0),
                              new ArrayList<String>() {
                          {
                              add("Class dismissed. Work on your"
                                  + " homework and DON’T PROCRASTINATE...and R E F A C T O R ! Your textbook and the "
                                  + "Java API are your best friends");
                              add("Don't forget to REFACTOR!");
                          }
                      }, new ArrayList<String>() {
                          {
                              add("Greetings! Equip the items you see around you to become stronger.");
                              add("Seek the council of Muz, he has something very important to tell you.");
                          }
                      }, true);
        drDance.setDialogues(drDance.getHintDialogues());
        NPC muz = new NPC("Muz", new RPGCharacterStats(20, 0, 0),
                          new ArrayList<String>() {
                      {
                          add("Yo Dancee, lookin' sharp!");
                          add("Yo homie, don't give up! You can do this!");
                      }
                  }, new ArrayList<String>() {
                      {
                          add("In order to survive here you must get the three sacred items");
                          add("Seek the ways of Java, Netbeans, and the Winklevoss Twins");
                      }
                  }, true);
        muz.setDialogues(muz.getHintDialogues());
        NPC izi = new NPC("Izi", new RPGCharacterStats(25, 2, 3),
                          new ArrayList<String>() {
                      {
                          add("Food insecurity is a major problem on Bucknell's campus!");
                          add("I'm gonna email Bravman.");
                      }
                  }, new ArrayList<String>() {
                      {
                          add("I'm looking for something to add to my mineral collection");
                          add("I'll make it worth your time if you find me something shiny");
                      }
                  }, true);
        NPC angryDance = new NPC("Angry Dance", new RPGCharacterStats(30, 8, 8),
                                 new ArrayList<String>() {
                             {
                                 add("Karti B, stop slacking me so many questions!");
                                 add("I told you to R E F A C T O R !!!");
                             }
                         }, new ArrayList<String>() {
                             {
                                 add("Should not be displayed");
                             }
                         }, true);
        NPC drQueen = new NPC("Dr. Queen", new RPGCharacterStats(20, 0, 0),
                              new ArrayList<String>() {
                          {
                              add("Watch the videos!");
                              add("Take Data Science!");
                          }
                      }, new ArrayList<String>() {
                          {
                              add("I've learned how to enchant precious metals");
                              add("I'll show you how in my next lecture");
                          }
                      }, true);
        NPC martin = new NPC("Martin", new RPGCharacterStats(25, 4, 3),
                             new ArrayList<String>() {
                         {
                             add("I'll grade labs 9-12 by Monday!");
                             add("I'll grade labs 9-12 by Friday!");
                         }
                     }, new ArrayList<String>() {
                         {
                             add("I FORGOT TO GRADE THE LABS");
                             add("I NEED MORE TIME TO GRADE THE LABS");
                         }
                     }, true);
        NPC dustin = new NPC("Dustin", new RPGCharacterStats(25, 4, 3),
                             new ArrayList<String>() {
                         {
                             add("I recoded Java last night. Took me a couple hours...");
                             add("Are you a double? The thought of you always floats inside my head. B)");
                         }
                     }, new ArrayList<String>() {
                         {
                             add("Mind the snot on the floor.");
                             add("The boogers on the floor are hard as rocks.");
                         }
                     }, true);
        NPC beck = new NPC("Beck", new RPGCharacterStats(20, 0, 0),
                           new ArrayList<String>() {
                       {
                           add("I'm gonna grade lab13 while skydiving.");
                           add("Ow, my head.");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Should not be displayed");
                       }
                   }, true);
        NPC roboDustin = new NPC("Robo-Dustin", new RPGCharacterStats(20, 0, 0),
                                 new ArrayList<String>() {
                             {
                                 add("I... AM... Hello World... DUSTIN");
                                 add("Error Error Error Error Error Error!!!");
                             }
                         }, new ArrayList<String>() {
                             {
                                 add("Should not be displayed");
                             }
                         }, true);
        NPC dill = new NPC("Dill", new RPGCharacterStats(20, 0, 0),
                           new ArrayList<String>() {
                       {
                           add("I will rule over the world!");
                           add("Pickles are the best.");
                       }
                   }, new ArrayList<String>() {
                       {
                           add("Should not be displayed");
                       }
                   }, true);

        //Set 1
        Equipment weapon1 = new Equipment("Pen & Paper",
                                          new ItemStatistics(0, 1, 0, 1),
                                          ItemType.WEAPON, PEN_AND_PAPER);
        Equipment shield1 = new Equipment("Machine Code",
                                          new ItemStatistics(1, 0, 0, 1),
                                          ItemType.SHIELD, MACHINE_CODE);
        Equipment armor1 = new Equipment("API",
                                         new ItemStatistics(0, 0, 1, 1),
                                         ItemType.ARMOR, API);

        //Set 2
        Equipment weapon2 = new Equipment("Notepad++",
                                          new ItemStatistics(0, 2, 0, 1),
                                          ItemType.WEAPON, NOTEPAD);
        Equipment shield2 = new Equipment("HTML",
                                          new ItemStatistics(2, 0, 0, 1),
                                          ItemType.SHIELD, HTML);
        Equipment armor2 = new Equipment("Stack Overflow",
                                         new ItemStatistics(0, 0, 2, 1),
                                         ItemType.ARMOR, STACK_OVERFLOW);

        //Set 3
        Equipment weapon3 = new Equipment("Netbeans",
                                          new ItemStatistics(0, 3, 0, 1),
                                          ItemType.WEAPON, NETBEANS);
        Equipment shield3 = new Equipment("Java",
                                          new ItemStatistics(3, 0, 0, 1),
                                          ItemType.SHIELD, JAVA);
        Equipment armor3 = new Equipment("The Winklevoss Twins",
                                         new ItemStatistics(0, 0, 3, 1),
                                         ItemType.ARMOR, WINKLEVOSS_TWINS);

        Item booger = new Item("Crystalized Booger", new ItemStatistics(0, 0, 0,
                                                                        0),
                               BOOGER);
        Item nugget = new Item("Gold Nugget", new ItemStatistics(0, 0, 0, 0),
                               NUGGET);
        Item time = new Item("Time", new ItemStatistics(0, 0, 0, 0), TIME);
        ConsumableItem tots = new ConsumableItem("Nacho Tots",
                                                 new ItemStatistics(20, 3, 0, 0),
                                                 NACHO_TOTS);
        ConsumableItem shake = new ConsumableItem("Oreo Milkshake",
                                                  new ItemStatistics(20, 0, 3, 0),
                                                  OREO_MILKSHAKE);
        ConsumableItem ramen = new ConsumableItem("Ramen Noodles",
                                                  new ItemStatistics(20, 0, 0, 3),
                                                  RAMEN);

        items.put(PEN_AND_PAPER, weapon1);
        items.put(MACHINE_CODE, shield2);
        items.put(API, armor1);
        items.put(NOTEPAD, weapon2);
        items.put(HTML, shield2);
        items.put(STACK_OVERFLOW, armor2);
        items.put(NETBEANS, weapon3);
        items.put(JAVA, shield3);
        items.put(WINKLEVOSS_TWINS, armor3);
        items.put(BOOGER, booger);
        items.put(NUGGET, nugget);
        items.put(TIME, time);
        items.put(NACHO_TOTS, tots);
        items.put(OREO_MILKSHAKE, shake);
        items.put(RAMEN, ramen);

        izi.getInventory().add(shield2);
        izi.setDesiredItem(booger);
        martin.getInventory().add(armor2);
        martin.setDesiredItem(time);
        roboDustin.getInventory().add(armor3);
        dill.getInventory().add(nugget);
        drQueen.getInventory().add(shield3);
        drQueen.setDesiredItem(nugget);
        beck.getInventory().add(weapon3);

        Point2D npcLocation = new Point2D(0, 200);

        this.finalBoss = new NPCImageViewWrapper(angryDance,
                                                 "AngryDancee.png",
                                                 npcLocation);

        RoomContent rc1 = new RoomContent("Bana 340", new NPCImageViewWrapper(
                                          drDance,
                                          "Dancee.png",
                                          npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(weapon1);
                                          add(shield1);
                                          add(armor1);
                                      }
                                  });
        RoomContent rc2 = new RoomContent("SAE",
                                          new NPCImageViewWrapper(muz,
                                                                  "Muz.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(weapon2);
                                      }
                                  });
        RoomContent rc3 = new RoomContent("Outside of Dill's Secret Lab",
                                          new NPCImageViewWrapper(dill,
                                                                  "Dill.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(ramen);
                                      }
                                  });
        RoomContent rc4 = new RoomContent("Out and about",
                                          new NPCImageViewWrapper(izi,
                                                                  "Izi.png",
                                                                  npcLocation),
                                          new ArrayList<Item>());
        RoomContent rc5 = new RoomContent("Rooke Chapel",
                                          new NPCImageViewWrapper(roboDustin,
                                                                  "Robo-Dustin.png",
                                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(shake);
                                      }
                                  });
        RoomContent rc6 = new RoomContent("Crackmetal 169",
                                          new NPCImageViewWrapper(
                                                  drQueen,
                                                  "King.png",
                                                  npcLocation),
                                          new ArrayList<Item>());
        RoomContent rc7 = new RoomContent("Crackmetal 164",
                                          new NPCImageViewWrapper(
                                                  martin,
                                                  "Martin.png",
                                                  npcLocation),
                                          new ArrayList<Item>());
        RoomContent rc8 = new RoomContent("Secret Lab in Bana",
                                          new NPCImageViewWrapper(
                                                  dustin,
                                                  "Dustin.png",
                                                  npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(booger);
                                      }
                                  });
        RoomContent rc9 = new RoomContent("In the sky", new NPCImageViewWrapper(
                                          beck,
                                          "Beck.png",
                                          npcLocation),
                                          new ArrayList<Item>() {
                                      {
                                          add(tots);
                                      }
                                  });

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
        return roomContents.remove(
                new Random().nextInt(
                        roomContents.size()));
    }

    /**
     * Gets the final boss of the game
     *
     * @return the final boss of the game
     *
     * @author lts010
     */
    public NPCImageViewWrapper getFinalBoss() {
        return finalBoss;
    }

    /**
     * Gets an Item for the given ImageKey
     *
     * @return the item matching the Key
     *
     * @author ks061
     */
    public EnumMap<ImageKey, Item> getItems() {
        return items;
    }

}
