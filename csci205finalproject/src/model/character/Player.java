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
 *
 * @author lts010
 */
public class Player extends RPGCharacter {

    public static final int DEFAULT_MAX_HEALTH = 10;
    public static final int DEFAULT_ATTACK = 10;
    public static final int DEFAULT_DEFENSE = 10;
    public static final int DEFAULT_INVENTORY_SIZE = 4;

    public Player(String name) {
        super(name, Player.DEFAULT_MAX_HEALTH, Player.DEFAULT_ATTACK,
              Player.DEFAULT_DEFENSE, Player.DEFAULT_INVENTORY_SIZE);
    }

    @Override
    public String moveTo(Room room) {
        this.getLocation().setPlayer(null);
        this.setLocation(room);
        this.getLocation().setPlayer(this);
        return String.format("You moved to the %s",
                             this.getLocation().getName());
    }

    public String talk(NPC npc) {
        String dialogue = npc.getCurrentDialogue();
        npc.toggleCurrentDialogue();
        return dialogue;
    }

    public String trade(NPC npc) {
        Item desiredItemOfNPC = npc.getDesiredItem();
        if (this.getInventory().contains(desiredItemOfNPC)) {
            Item desiredItemOfPlayer = npc.getInventory().get(0);
            npc.getInventory().remove(desiredItemOfPlayer);
            this.getInventory().remove(desiredItemOfNPC);
            npc.getInventory().add(desiredItemOfNPC);
            this.getInventory().add(desiredItemOfPlayer);
            return String.format("You have traded the %s for the %s",
                                 desiredItemOfNPC.getName(),
                                 desiredItemOfPlayer.getName());
        }
        else {
            return String.format("You do not have the item that %s wants",
                                 npc.getName());
        }
    }

    public String search(Room room) {
        if (room.getHiddenItems().isEmpty()) {
            return String.format("Searched %s and found nothing", room.getName());
        }
        else if (this.isInventoryFull()) {
            return String.format("Found %s but your inventory is full",
                                 room.getHiddenItems().get(0).getName());
        }
        else {
            Item hiddenItem = room.getHiddenItems().get(0);
            this.getInventory().add(hiddenItem);
            room.getHiddenItems().remove(hiddenItem);
            return String.format("Found %s an added it to your inventory",
                                 hiddenItem.getName());
        }
    }

    public String search(NPC npc) {
        if (npc.isIsAlive()) {
            return "Cannot search the bodies of characters who are alive";
        }
        else {
            Item item = npc.getInventory().get(0);
            if (this.isInventoryFull()) {
                return String.format("Found %s on %s but your inventory is full",
                                     item.getName(), npc.getName());
            }
            else {
                this.getInventory().add(item);
                npc.getInventory().remove(item);
                return String.format("Took %s off the body of %s",
                                     item.getName(),
                                     npc.getName());
            }
        }
    }

    public String startBattle(NPC npc) {
        if (npc.isIsFriendly()) {
            return "Cannot starts fights with friendly characters";
        }
        else {
            while (this.isIsAlive() || npc.isIsAlive()) {
                this.attack(npc);
                if (npc.isIsAlive()) {
                    npc.attack(this);
                }
            }
            if (this.isIsAlive()) {
                return String.format("You have killed %s", npc.getName());
            }
            else {
                return String.format("%s has killed you", npc.getName());
            }
        }
    }

}
