/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 8, 2018
  * Time: 12:46:54 PM
  *
  * Project: csci205FinalProject
  * Package: model.character
  * File: RPGCharacterTest
  * Description: This file contains RPGCharacterTest.
  * ****************************************
 */
package model.character;

import java.util.ArrayList;
import junit.framework.TestCase;
import model.item.ConsumableItem;
import model.item.Equipment;
import model.item.ItemStatistics;
import model.item.ItemType;
import org.junit.Test;
import view.ImageKey;

/**
 * JUnit tests for the class RPGCharacter
 *
 * @author lts010
 */
public class RPGCharacterTest extends TestCase {

    private Player theTester; //we'll test all our methods on this character

    @Override
    public void setUp() {
        theTester = new Player("Tester");
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of attack method, of class RPGCharacter.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        //create an NPC for the character to attack
        RPGCharacterStats testStats = new RPGCharacterStats(10, 10, 5);
        ArrayList<String> testDialogue = new ArrayList<String>();
        testDialogue.add("");
        testDialogue.add("");
        ArrayList<String> testHints = new ArrayList<String>();
        NPC theEnemy = new NPC("theEnemy", testStats, testDialogue, testHints,
                               false);
        //the player will attack the enemy
        String testString = theTester.attack(theEnemy);
        System.out.println(theEnemy.getCharacterStats().getHealth());
        assertTrue( //the attack should do 5, 0, or 8 damage based on luck
                theEnemy.getCharacterStats().getHealth() == 5 || theEnemy.getCharacterStats().getHealth() == 10 || theEnemy.getCharacterStats().getHealth() == 2);
        if (theEnemy.getCharacterStats().getHealth() == 5) { //if it did 5 damage the message should be this
            assertTrue(testString.contentEquals(
                    "Tester did 5 damage to theEnemy"));
        }
        else if (theEnemy.getCharacterStats().getHealth() == 10) { //this message when the damage is 0
            assertTrue(testString.contentEquals(
                    "Tester missed and did no damage to theEnemy"));
        }
        else { //this message when the damage is 8
            assertTrue(testString.contentEquals(
                    "Critical Hit! Tester did 8 damage to theEnemy"));
        }
        this.theTester.getCharacterStats().setAttack(100); //if we make the testers attack really high and hit the npc it should die
        this.theTester.attack(theEnemy);
        if (theEnemy.getCharacterStats().getHealth() <= 0) {
            assertTrue(!theEnemy.isAlive());
        }
    }

    /**
     * Test of equip method, of class RPGCharacter.
     */
    @Test
    public void testEquip() {
        System.out.println("equip");
        //make a weapon, shield, and armor
        ItemStatistics testStats1 = new ItemStatistics(3, 3, 3, 0);
        ItemStatistics testStats2 = new ItemStatistics(3, 3, 3, 3);
        Equipment weapon = new Equipment("TestWeapon", testStats1,
                                         ItemType.WEAPON, ImageKey.PEN_AND_PAPER);
        Equipment shield = new Equipment("TestShield", testStats2,
                                         ItemType.SHIELD, ImageKey.MACHINE_CODE);
        Equipment armor = new Equipment("TestArmor", testStats2,
                                        ItemType.ARMOR, ImageKey.API);
        theTester.getInventory().add(weapon); //give them to the tester
        theTester.getInventory().add(shield);
        theTester.getInventory().add(armor);
        assertTrue(theTester.getWeapon() == null); //the player should have nothing equipped
        assertTrue(theTester.getArmor() == null);
        assertTrue(theTester.getShield() == null);

        String stringOne = theTester.equip(weapon); //equip all the equipment
        String stringTwo = theTester.equip(shield);
        String stringThree = theTester.equip(armor);
        assertTrue(stringOne.contentEquals(
                "Equipped the TestWeapon as a WEAPON"));
        assertTrue(!theTester.getInventory().contains(weapon)); //the tester should have none of these items in their inventory now
        assertTrue(!theTester.getInventory().contains(armor));
        assertTrue(!theTester.getInventory().contains(shield));
        assertTrue(
                theTester.getCharacterStats().getAttack() == Player.DEFAULT_ATTACK + 9); //the player's inventory should be changed according to the equipment's stats
        assertTrue(
                theTester.getCharacterStats().getDefense() == Player.DEFAULT_DEFENSE + 9);
        assertTrue(
                theTester.getCharacterStats().getMaxHealth() == Player.DEFAULT_MAX_HEALTH + 9); //equipment changes max health and not current health
        assertTrue(
                theTester.getInventorySize() == Player.DEFAULT_INVENTORY_SIZE + 6);
        //create a second armor and give it to the tester
        ItemStatistics testStats3 = new ItemStatistics(1, 1, 1, 1);
        Equipment armor2 = new Equipment("TestArmor2", testStats3,
                                         ItemType.ARMOR, ImageKey.STACK_OVERFLOW);
        theTester.getInventory().add(armor2);

        String stringFour = theTester.equip(armor2); //if the tester equips the armor it will unequip the first armor
        assertTrue(stringFour.contentEquals(
                "Unequipped the TestArmor and equipped the TestArmor2"));
        assertTrue(theTester.getArmor() == armor2); //the two armors should have switched places
        assertTrue(theTester.getInventory().contains(armor));
        assertTrue(!theTester.getInventory().contains(armor2));
        assertTrue(
                theTester.getCharacterStats().getAttack() == Player.DEFAULT_ATTACK + 9 - 3 + 1); //the tester's stats should change accordingly
        assertTrue(
                theTester.getCharacterStats().getDefense() == Player.DEFAULT_DEFENSE + 9 - 3 + 1);
        assertTrue(
                theTester.getCharacterStats().getMaxHealth() == Player.DEFAULT_MAX_HEALTH + 9 - 3 + 1);
        assertTrue(
                theTester.getInventorySize() == Player.DEFAULT_INVENTORY_SIZE + 6 - 3 + 1);

    }

    /**
     * Test of unequip method, of class RPGCharacter.
     */
    @Test
    public void testUnequip() {
        System.out.println("unequip");
        //create a equipment item of type weapon
        ItemStatistics testStats1 = new ItemStatistics(3, 3, 3, 0);
        Equipment weapon = new Equipment("TestWeapon", testStats1,
                                         ItemType.WEAPON, ImageKey.PEN_AND_PAPER);
        theTester.getInventory().add(weapon); //give it to the tester and then equip it
        theTester.equip(weapon);
        assertTrue(theTester.getWeapon() == weapon); //we see that they currently have it equipped in the weapon slot and not in their inventory
        assertTrue(!this.theTester.getInventory().contains(weapon));
        String stringOne = this.theTester.unequip(weapon); //if we unequip it
        assertTrue(stringOne.contentEquals( //we'll get this string
                "Unequipped the TestWeapon and added it to your inventory"));
        assertTrue(this.theTester.getWeapon() == null); //the weapon will now be in the inventory instead of the weapon slot
        assertTrue(this.theTester.getInventory().contains(weapon));
        assertTrue( //all of the tester's stats will go back to their defaults
                this.theTester.getCharacterStats().getAttack() == Player.DEFAULT_ATTACK);
        assertTrue(
                this.theTester.getCharacterStats().getDefense() == Player.DEFAULT_DEFENSE);
        assertTrue(
                this.theTester.getCharacterStats().getMaxHealth() == Player.DEFAULT_MAX_HEALTH);
        assertTrue(
                this.theTester.getInventorySize() == Player.DEFAULT_INVENTORY_SIZE);
        this.theTester.equip(weapon); //requip the weapon for another test
        this.theTester.getInventory().add(weapon); //fill up inventory to be full
        this.theTester.getInventory().add(weapon);
        this.theTester.getInventory().add(weapon);
        this.theTester.getInventory().add(weapon);

        String stringTwo = this.theTester.unequip(weapon); //now if we try to unequip something we can't
        assertTrue(stringTwo.contentEquals(
                "Cannot unequip the TestWeapon because your inventory is full"));
        assertTrue(theTester.getWeapon() == weapon); //the tester will still have the weapon equipped
    }

    /**
     * Test of consume method, of class RPGCharacter.
     */
    @Test
    public void testConsume() {
        System.out.println("consume");
        //make two items
        ItemStatistics testStats = new ItemStatistics(3, 3, 3, 3);
        ConsumableItem testItem1 = new ConsumableItem("TestItem1", testStats,
                                                      ImageKey.JAVA);
        ConsumableItem testItem2 = new ConsumableItem("TestItem2", testStats,
                                                      ImageKey.JAVA);
        //give them to the tester
        theTester.getInventory().add(testItem1);
        theTester.getInventory().add(testItem2);
        theTester.getCharacterStats().setHealth(6); //lower the tester's health so that the items will change their health
        theTester.consume(testItem1); //use the first item
        assertTrue(!theTester.getInventory().contains(testItem1)); //it should be gone
        assertTrue(theTester.getCharacterStats().getAttack() == 13); //attack, defense, current health, and inventory size should all go up by the item's value
        assertTrue(theTester.getCharacterStats().getDefense() == 13);
        assertTrue(theTester.getInventorySize() == 7);
        assertTrue(theTester.getCharacterStats().getHealth() == 9);
        theTester.consume(testItem2); //use the second item
        assertTrue(!theTester.getInventory().contains(testItem2)); //it should be gone
        assertTrue(theTester.getCharacterStats().getAttack() == 16); //the item should have the same effect on attack, defense, and inventory size
        assertTrue(theTester.getCharacterStats().getDefense() == 16);
        assertTrue(theTester.getInventorySize() == 10);
        assertTrue(theTester.getCharacterStats().getHealth() == 10); //but it can only get a character to max health, not above
    }
}
