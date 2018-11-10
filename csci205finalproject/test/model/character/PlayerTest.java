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
  * File: PlayerTest
  * Description: This file contains PlayerTest.
  * ****************************************
 */
package model.character;

import junit.framework.TestCase;
import model.item.ConsumableItem;
import model.map.Room;
import org.junit.Test;

/**
 * Tests for the class RPGCharacters
 *
 * @author lts010
 */
public class PlayerTest extends TestCase {

    private Player theTester;
    private NPC theFriend;
    private NPC theEnemy;
    private Room theRoom;

    @Override
    public void setUp() {
        theTester = new Player("Tester");
        theFriend = new NPC("Friend", 1, 1, 1, "testDialogue1", "testDialogue2",
                            true);
        theEnemy = new NPC("Enemy", 100, 100, 100, "", "", false);
        theRoom = new Room("Room1");
        theTester.setLocation(theRoom);
        theRoom.setPlayer(theTester);
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of moveTo method, of class Player.
     */
    @Test
    public void testMoveTo() {
        System.out.println("moveTo");
        Room theRoom2 = new Room("Room2");
        String testString = theTester.moveTo(theRoom2);
        assertTrue(theTester.getLocation() == theRoom2);
        assertTrue(theRoom.getPlayer() == null);
        assertTrue(theRoom2.getPlayer() == theTester);
        assertTrue(testString.contentEquals("Tester moved to the Room2"));
    }

    /**
     * Test of talk method, of class Player.
     */
    @Test
    public void testTalk() {
        System.out.println("talk");
        assertTrue(theTester.talk(theFriend).contentEquals("testDialogue1"));
        assertTrue(theTester.talk(theFriend).contentEquals("testDialogue2"));
    }

    /**
     * Test of trade method, of class Player.
     */
    @Test
    public void testTrade() {
        System.out.println("trade");
        String testString = theTester.trade(theEnemy);
        assertTrue(testString.contentEquals("Enemy does not want to trade"));
        ConsumableItem testItem1 = new ConsumableItem("TestItem1", 3, 3, 3, 3);
        theTester.getInventory().add(testItem1);
        testItem1.setOwner(theTester);
        ConsumableItem testItem2 = new ConsumableItem("TestItem2", 3, 3, 3, 3);
        theFriend.getInventory().add(testItem2);
        testItem2.setOwner(theFriend);

        theFriend.setDesiredItem(testItem2);
        String testString2 = theTester.trade(theFriend);
        assertTrue(testString2.contentEquals(
                "Tester does not have the item that Friend wants"));

        theFriend.setDesiredItem(testItem1);
        String testString3 = theTester.trade(theFriend);
        assertTrue(theFriend.getDesiredItem() == testItem2);
        assertTrue(testItem1.getOwner() == theFriend);
        assertTrue(testItem2.getOwner() == theTester);
        assertTrue(theTester.getInventory().contains(testItem2));
        assertTrue(!theTester.getInventory().contains(testItem1));
        assertTrue(!theFriend.getInventory().contains(testItem2));
        assertTrue(theFriend.getInventory().contains(testItem1));
        assertTrue(testString3.contentEquals(
                "Tester traded the TestItem1 for the TestItem2"));
    }

    /**
     * Test of search method, of class Player.
     */
    @Test
    public void testSearch_Room() {
        System.out.println("search");
        ConsumableItem testItem = new ConsumableItem("TestItem", 3, 3, 3, 3);
        this.theRoom.getHiddenItems().add(testItem);
        String testString = theTester.search(theRoom);
        assertTrue(testItem.getOwner() == theTester);
        assertTrue(!theRoom.getHiddenItems().contains(testItem));
        assertTrue(theTester.getInventory().contains(testItem));
        assertTrue(testString.contains(
                "Tester found TestItem and added it to their inventory"));
        String testString2 = theTester.search(theRoom);
        assertTrue(testString2.contains(
                "Tester searched Room1 but found nothing"));

        ConsumableItem testItem2 = new ConsumableItem("TestItem2", 3, 3, 3, 3);
        theRoom.getHiddenItems().add(testItem2);
        theTester.setInventorySize(1);
        String testString4 = theTester.search(theRoom);
        assertTrue(testString4.contentEquals(
                "Tester found TestItem2 but their inventory is full"));
    }

    /**
     * Test of search method, of class Player.
     */
    @Test
    public void testSearch_NPC() {
        System.out.println("search");
        ConsumableItem testItem = new ConsumableItem("TestItem", 3, 3, 3, 3);
        theEnemy.getInventory().add(testItem);
        testItem.setOwner(theEnemy);

        String testString = theTester.search(theEnemy);
        assertTrue(testString.contains(
                "Cannot search the bodies of characters who are alive"));

        theEnemy.setIsAlive(false);
        String testString2 = theTester.search(theEnemy);
        assertTrue(testItem.getOwner() == theTester);
        assertTrue(!theEnemy.getInventory().contains(testItem));
        assertTrue(theTester.getInventory().contains(testItem));
        assertTrue(testString2.contains(
                "Tester took TestItem off the body of Enemy"));
        String testString3 = theTester.search(theEnemy);
        assertTrue(testString3.contains(
                "Tester searched Enemy but found nothing"));

        ConsumableItem testItem2 = new ConsumableItem("TestItem2", 3, 3, 3, 3);
        theEnemy.getInventory().add(testItem2);
        testItem2.setOwner(theEnemy);
        theTester.setInventorySize(1);
        String testString4 = theTester.search(theEnemy);
        assertTrue(testString4.contentEquals(
                "Tester found TestItem2 on Enemy but their inventory is full"));
    }

    /**
     * Test of startBattle method, of class Player.
     */
    @Test
    public void testStartBattle() {
        System.out.println("startBattle");
        String testString = theTester.startBattle(theFriend);
        assertTrue(testString.contentEquals(
                "Cannot start fights with friendly characters"));
        String testString2 = theTester.startBattle(theEnemy);
        assertTrue(testString2.contentEquals("Enemy has killed Tester"));
    }

}
