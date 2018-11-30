/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 3, 2018
  * Time: 3:28:12 PM
  *
  * Project: csci205FinalProject
  * Package: model.character
  * File: Player
  * Description: This file contains Player.
  * ****************************************
 */
package model.character;

import model.item.Item;

/**
 * Player class creates constructor and methods associated with the Players
 * throughout the RPG.
 *
 * @author lts010, ks061
 */
public class Player extends RPGCharacter {

    /**
     * Default maximum health attribute for a player
     */
    public static final int DEFAULT_MAX_HEALTH = 10;
    /**
     * Default attack attribute for a player
     */
    public static final int DEFAULT_ATTACK = 10;
    /**
     * Default defense attribute for a player
     */
    public static final int DEFAULT_DEFENSE = 10;
    /**
     * Default inventory size for a player
     */
    public static final int DEFAULT_INVENTORY_SIZE = 4;

    /**
     * Player constructor that instantiates the attributes of the player
     *
     * @param name - the name of the player
     *
     * @author ks061, lts010
     */
    public Player(String name) {
        super(name, new RPGCharacterStats(Player.DEFAULT_MAX_HEALTH,
                                          Player.DEFAULT_ATTACK,
                                          Player.DEFAULT_DEFENSE),
              Player.DEFAULT_INVENTORY_SIZE);
    }

    /**
     * Searches the body of a dead NPC to gather its items
     *
     * @param npc NPC to loot
     * @return string representing what items were found and added to player
     * inventory
     *
     * @author lts010, ks061
     */
    public String search(NPC npc) {
        if (npc.isAlive()) {
            return "Cannot search the bodies of characters who are alive";
        }
        else {
            if (npc.getInventory().isEmpty()) {
                return String.format("%s searched %s but found nothing",
                                     this.getName(), npc.getName());
            }
            Item item = npc.getInventory().get(0);
            if (this.isInventoryFull()) {
                return String.format(
                        "%s found %s on %s but their inventory is full",
                        this.getName(), item.getName(), npc.getName());
            }
            else {
                this.getInventory().add(item);
                npc.getInventory().remove(item);
                return String.format("%s took %s off the body of %s",
                                     this.getName(), item.getName(),
                                     npc.getName());
            }
        }
    }

    /**
     * Starts a battle with a hostile NPC
     *
     * @param npc NPC to battle
     * @return string describing who was killed in battle
     *
     * @author lts010, ks061
     */
    public String startBattle(NPC npc) {
        if (npc.isFriendly()) {
            return "Cannot start fights with friendly characters";
        }
        else {
            while (this.isAlive() & npc.isAlive()) {
                this.attack(npc);
                if (npc.isAlive()) {
                    npc.attack(this);
                }
            }
            if (this.isAlive()) {
                return String.format("%s have killed %s", this.getName(),
                                     npc.getName());
            }
            else {
                return String.format("%s has killed %s", npc.getName(),
                                     this.getName());
            }
        }
    }

}
