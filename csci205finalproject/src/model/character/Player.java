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

    public String moveTo(Room room) {
        this.getLocation().setPlayer(null);
        this.setLocation(room);
        this.getLocation().setPlayer(this);
        return String.format("%s moved to the %s",
                             this.getName(), this.getLocation().getName());
    }

    public String talk(NPC npc) {
        String dialogue = npc.getCurrentDialogue();
        npc.toggleCurrentDialogue();
        return dialogue;
    }

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

    public String search(Room room) {
        if (room.getHiddenItems().isEmpty()) {
            return String.format("%s searched %s but found nothing",
                                 this.getName(), room.getName());
        }
        else if (this.isInventoryFull()) {
            return String.format("%s found %s but their inventory is full",
                                 this.getName(),
                                 room.getHiddenItems().get(0).getName());
        }
        else {
            Item hiddenItem = room.getHiddenItems().get(0);
            this.getInventory().add(hiddenItem);
            hiddenItem.setOwner(this);
            room.getHiddenItems().remove(hiddenItem);
            return String.format("%s found %s and added it to their inventory",
                                 this.getName(), hiddenItem.getName());
        }
    }

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

    public String startBattle(NPC npc) {
        if (npc.isIsFriendly()) {
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
