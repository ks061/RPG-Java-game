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

import model.item.ConsumableItem;
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
     * Trades desired item of player with desired item of NPC and adds these to
     * inventory
     *
     * @param npc - NPC to trade with
     * @return String represented what items have been traded
     */
    public String trade(NPC npc) {
        if (!npc.isAlive()) {
            return "Cannot trade with dead people";
        }
        if (npc.getDesiredItem() == null) {
            return String.format("%s does not want to trade", npc.getName());
        }
        Item desiredItemOfNPC = npc.getDesiredItem();
        if (this.getInventory().contains(desiredItemOfNPC)) {
            Item desiredItemOfPlayer = npc.getInventory().get(0);
            npc.getInventory().remove(desiredItemOfPlayer);
            this.getInventory().remove(desiredItemOfNPC);
            npc.getInventory().add(desiredItemOfNPC);
            this.getInventory().add(desiredItemOfPlayer);
            npc.setDesiredItem(desiredItemOfPlayer);
            return String.format("%s traded the %s for the %s",
                                 this.getName(),
                                 desiredItemOfNPC.getName(),
                                 desiredItemOfPlayer.getName());
        }
        else {
            return String.format("%s does not have the item that %s wants",
                                 this.getName(), npc.getName());
        }
    }

    /**
     * Adjusts the character statistics, such as maximum health, attack,
     * defense, and inventory size, based upon the statistics of the consumable
     * item and current statistics of this RPGCharacter
     *
     * @param consumableItem equipment being consumed by this RPGCharacter
     *
     * @author ks061, ishk001, lts010
     */
    private void adjustCharacterStatisticsFromConsume(
            ConsumableItem consumableItem) {
        //For HEALTH potions
        int curHealth = this.getCharacterStats().getHealth();
        if (curHealth + consumableItem.getItemStatistics().getDeltaHealth() > this.getCharacterStats().getMaxHealth()) {
            //sets the health of the player to max health if health + potion turns
            //out to fill up the health bar of the player
            this.getCharacterStats().setHealth(
                    this.getCharacterStats().getMaxHealth());
        }
        else {
            this.getCharacterStats().setHealth(
                    curHealth + consumableItem.getItemStatistics().getDeltaHealth());
        }
        //For ATTACK potions
        this.getCharacterStats().setAttack(
                this.getCharacterStats().getAttack() + consumableItem.getItemStatistics().getDeltaAttack());
        //For DEFENSE potions
        this.getCharacterStats().setDefense(
                this.getCharacterStats().getDefense() + consumableItem.getItemStatistics().getDeltaDefense());
        //For items that permanantely increase inventory size
        this.setInventorySize(
                this.getInventorySize() + consumableItem.getItemStatistics().getDeltaInventory());
    }

    /**
     * Consumes the items and correspondingly change the health of item owner
     *
     * @param consumableItem consumable item being consumed by this character
     * @return String representing what was consumed
     *
     * @author ishk001, lts010, ks061
     */
    public String consume(ConsumableItem consumableItem) {
        adjustCharacterStatisticsFromConsume(consumableItem);
        this.getInventory().remove(consumableItem);
        return String.format("Consumed the %s", consumableItem.getName());
    }
}
