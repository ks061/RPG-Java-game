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
    public void moveTo(Room room) {
        super.getLocation().setPlayer(null);
        super.setLocation(room);
        super.getLocation().setPlayer(this);
    }

    public void talk(NPC npc) {
        System.out.println(npc.getCurrentDialogue());
        npc.toggleCurrentDialogue();
    }

    public void trade(NPC npc) {
        Item desiredItemOfNPC = npc.getDesiredItem();
        if (this.getInventory().contains(desiredItemOfNPC)) {
            Item desiredItemOfPlayer = npc.getInventory().get(0);
            npc.getInventory().remove(desiredItemOfPlayer);
            this.getInventory().remove(desiredItemOfNPC);
            npc.getInventory().add(desiredItemOfNPC);
            this.getInventory().add(desiredItemOfPlayer);
            System.out.println("You have traded the %s for the %s",
                               desiredItemOfNPC.getName(),
                               desiredItemOfPlayer.getName());
        }
        else {
            System.out.printf("You do not have the item that %s wants",
                              npc.getName());
        }
    }

    public void search(Room room) {
        if (room.getHiddenItems().isEmpty()) {
            System.out.println("Searched %s and found nothing", room.getName());
        }
        else if (this.isInventoryFull()) {
            System.out.println("Found %s but your inventory is full",
                               room.getHiddenItems().get(0).getName());
        }
        else {
            Item hiddenItem = room.getHiddenItems.get(0);
            this.getInventory().add(hiddenItem);
            room.getHiddenItems().remove(hiddenItem);
            System.out.println("Found %s an added it to your inventory",
                               hiddenItem.getName());
        }
    }

    public void search(NPC npc) {
        if (npc.isIsAlive()) {
            System.out.println(
                    "Cannot search the bodies of characters who are alive");
        }
        else {
            Item item = npc.getInventory().get();
            if (this.isInventoryFull()) {
                System.out.printf("Found %s on %s but your inventory is full",
                                  item.name, npc.name)
            }
            else {
                this.getInventory().add(item);
                npc.getInventory().remove(item);
                System.out.println("Took %s off the body of %s", item.getName(),
                                   npc.getName());
            }
        }
    }

    public void startBattle(NPC npc) {
        if (npc.isIsFriendly()) {
            System.out.println("Cannot starts fights with friendly characters");
        }
        else {
            while (this.isIsAlive() || npc.isIsAlive()) {
                this.attack(npc);
                if (npc.isIsAlive()) {
                    npc.attack(this);
                }
            }
        }
    }

}
