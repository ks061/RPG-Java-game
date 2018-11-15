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
import model.map.Room;

/**
 * Player class creates constructor and methods associated with the Players
 * throughout the RPG. It's a child of RPCCharacter.
 *
 * @author Logan Stiles
 * @version o.1
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
     */
    public Player(String name) {
        super(name, Player.DEFAULT_MAX_HEALTH, Player.DEFAULT_ATTACK,
              Player.DEFAULT_DEFENSE, Player.DEFAULT_INVENTORY_SIZE);
    }

    /**
     * Overrides moveTo in the RPGCharacter class
     *
     * @param room - room to move to
     * @return String representing the room the player moved to
     */
    public String moveTo(Room room) {
        this.getLocation().setPlayer(null);
        this.setLocation(room);
        this.getLocation().setPlayer(this);
        return String.format("%s moved to the %s",
                             this.getName(), this.getLocation().getName());
    }

    /**
     * Toggles the current dialogue of the NPC and returns it (has the NPC talk)
     *
     * @param npc - NPC to be talking
     * @return String representing current dialogue of the NPC
     */
    public String talk(NPC npc) {
        String dialogue = npc.getCurrentDialogue();
        npc.nextDialogueToSpeak();
        return dialogue;
    }

    /**
     * Trades desired item of player with desired item of NPC and adds these to
     * inventory
     *
     * @param npc - NPC to trade with
     * @return String represented what items have been traded
     */
    public String trade(NPC npc) {
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
            desiredItemOfPlayer.setOwner(this);
            desiredItemOfNPC.setOwner(npc);
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
     * Searches a room for items to add to player inventory
     *
     * @param room - room to search
     * @return String representing if an item was found and added to your
     * inventory
     */
    public String search(Room room) {
        if (room.getHiddenItem() == null) {
            return String.format("%s searched %s but found nothing",
                                 this.getName(), room.getName());
        }
        else if (this.isInventoryFull()) {
            return String.format("%s found %s but their inventory is full",
                                 this.getName(),
                                 room.getHiddenItem().getName());
        }
        else {
            Item hiddenItem = room.getHiddenItem();
            this.getInventory().add(hiddenItem);
            hiddenItem.setOwner(this);
            // room.getHiddenItem().remove(hiddenItem);
            room.setHiddenItem(null);
            return String.format("%s found %s and added it to their inventory",
                                 this.getName(), hiddenItem.getName());
        }
    }

    /**
     * Searches the body of a dead NPC to gather its items
     *
     * @param npc - NPC to loot
     * @return String representing what items were found and added to player
     * inventory
     */
    public String search(NPC npc) {
        if (npc.isIsAlive()) {
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
                item.setOwner(this);
                return String.format("%s took %s off the body of %s",
                                     this.getName(), item.getName(),
                                     npc.getName());
            }
        }
    }

    /**
     * Starts a battle with a hostile NPC
     *
     * @param npc - NPC to battle
     * @return String describing who was killed in battle
     */
    public String startBattle(NPC npc) {
        if (npc.isFriendly()) {
            return "Cannot start fights with friendly characters";
        }
        else {
            while (this.isIsAlive() & npc.isIsAlive()) {
                this.attack(npc);
                if (npc.isIsAlive()) {
                    npc.attack(this);
                }
            }
            if (this.isIsAlive()) {
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
