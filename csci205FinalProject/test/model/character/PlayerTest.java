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

import java.util.ArrayList;
import junit.framework.TestCase;
import model.item.ConsumableItem;
import model.item.ItemStatistics;
import model.map.Room;
import org.junit.Test;
import view.ImageKey;

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
        RPGCharacterStats testStats1 = new RPGCharacterStats(1, 1, 1);
        RPGCharacterStats testStats2 = new RPGCharacterStats(100, 100, 100);
        ArrayList<String> testDialogue = new ArrayList<>();
        testDialogue.add("testDialogue1");
        testDialogue.add("testDialogue2");
        theFriend = new NPC("Friend", testStats1, testDialogue, testDialogue,
                            true);
        theEnemy = new NPC("Enemy", testStats2, testDialogue, testDialogue,
                           false);
        theRoom = new Room("Room1");
        theTester.setLocation(theRoom);
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of trade method, of class Player.
     */
    @Test
    public void testTrade() {
        System.out.println("trade");
        String testString = theTester.trade(theEnemy);
        assertTrue(testString.contentEquals("Enemy does not want to trade"));
        ItemStatistics testStats = new ItemStatistics(3, 3, 3, 3);
        ConsumableItem testItem1 = new ConsumableItem("TestItem1", testStats,
                                                      ImageKey.API);
        theTester.getInventory().add(testItem1);
        ConsumableItem testItem2 = new ConsumableItem("TestItem2", testStats,
                                                      ImageKey.API);
        theFriend.getInventory().add(testItem2);

        theFriend.setDesiredItem(testItem2);
        String testString2 = theTester.trade(theFriend);
        assertTrue(testString2.contentEquals(
                "Tester does not have the item that Friend wants"));

        theFriend.setDesiredItem(testItem1);
        String testString3 = theTester.trade(theFriend);
        assertTrue(theFriend.getDesiredItem() == testItem2);
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
    public void testSearch_NPC() {
        System.out.println("search");
        ItemStatistics testStats = new ItemStatistics(3, 3, 3, 3);
        ConsumableItem testItem = new ConsumableItem("TestItem", testStats,
                                                     ImageKey.API);
        theEnemy.getInventory().add(testItem);

        String testString = theTester.search(theEnemy);
        assertTrue(testString.contains(
                "Cannot search the bodies of characters who are alive"));

        theEnemy.setIsAlive(false);
        String testString2 = theTester.search(theEnemy);
        assertTrue(!theEnemy.getInventory().contains(testItem));
        assertTrue(theTester.getInventory().contains(testItem));
        assertTrue(testString2.contains(
                "Tester took TestItem off the body of Enemy"));
        String testString3 = theTester.search(theEnemy);
        assertTrue(testString3.contains(
                "Tester searched Enemy but found nothing"));

        ConsumableItem testItem2 = new ConsumableItem("TestItem2", testStats,
                                                      ImageKey.API);
        theEnemy.getInventory().add(testItem2);
        theTester.setInventorySize(1);
        String testString4 = theTester.search(theEnemy);
        assertTrue(testString4.contentEquals(
                "Tester found TestItem2 on Enemy but their inventory is full"));
    }

}
