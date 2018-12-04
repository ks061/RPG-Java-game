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
import org.junit.Test;
import view.ImageKey;

/**
 * Tests for the class RPGCharacters
 *
 * @author lts010
 */
public class PlayerTest extends TestCase {

    /**
     * Will use this Player to test the methods
     */
    private Player theTester;

    /**
     * The player will interact with these 2 NPCs, one is friendly and the other
     * isn't
     */
    private NPC theFriend;
    private NPC theEnemy;

    @Override
    public void setUp() {
        theTester = new Player("Tester");
        RPGCharacterStats testStats1 = new RPGCharacterStats(1, 1, 1); //the friend should be weak
        RPGCharacterStats testStats2 = new RPGCharacterStats(100, 100, 100); //the enemy should be strong
        ArrayList<String> testDialogue = new ArrayList<>();
        testDialogue.add("testDialogue1");
        testDialogue.add("testDialogue2");
        //use the same ArrayList<String> for both dialogue sets because it doesn't matter for these tests
        theFriend = new NPC("Friend", testStats1, testDialogue, testDialogue,
                            true);
        theEnemy = new NPC("Enemy", testStats2, testDialogue, testDialogue,
                           false);
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
        String testString = theTester.trade(theEnemy); //the enemy doesn't want anything
        assertTrue(testString.contentEquals("Enemy does not want to trade")); //thus the output for trading with the enemy should be this
        theEnemy.setIsAlive(false); //kill the enemy
        assertTrue(theTester.trade(theEnemy).contentEquals(
                "Cannot trade with dead people.")); //now the message from the attempted trade should be this
        //make two items to exchange for the upcoming trade
        ItemStatistics testStats = new ItemStatistics(3, 3, 3, 3);
        ConsumableItem testItem1 = new ConsumableItem("TestItem1", testStats,
                                                      ImageKey.API);
        theTester.getInventory().add(testItem1); //give an item to the Player
        ConsumableItem testItem2 = new ConsumableItem("TestItem2", testStats,
                                                      ImageKey.API);
        theFriend.getInventory().add(testItem2); //give an item for the friend to trade with

        theFriend.setDesiredItem(testItem2); //the friend wants item 2
        String testString2 = theTester.trade(theFriend); //the player tries to trade for the item the friend has
        assertTrue(testString2.contentEquals( //but he doesn't have the item so the output should be this
                "Tester does not have the item that Friend wants"));

        theFriend.setDesiredItem(testItem1);  //now we say the friend wants the item the Player has
        String testString3 = theTester.trade(theFriend); //they trade items
        assertTrue(theFriend.getDesiredItem() == testItem2); //now the friend should want the item he used to have (in case the Player wants to trade back)
        assertTrue(theTester.getInventory().contains(testItem2)); //the Player should have the item the Friend just had
        assertTrue(!theTester.getInventory().contains(testItem1)); //the Player shouldn't have the item they just gave away
        assertTrue(!theFriend.getInventory().contains(testItem2)); //the friend shouldn't have the item they just gave away
        assertTrue(theFriend.getInventory().contains(testItem1)); //the friend should have the item the Player just had
        assertTrue(testString3.contentEquals( //the output of the trade method should've been this
                "Tester traded the TestItem1 for the TestItem2"));
    }

    /**
     * Test of search method, of class Player.
     */
    @Test
    public void testSearch_NPC() {
        System.out.println("search");
        //make one item
        ItemStatistics testStats = new ItemStatistics(3, 3, 3, 3);
        ConsumableItem testItem = new ConsumableItem("TestItem", testStats,
                                                     ImageKey.API);
        //give it to the enemy
        theEnemy.getInventory().add(testItem);

        String testString = theTester.search(theEnemy);
        assertTrue(testString.contains( //if the Player tries to search the enemy they will be stopped because the enemy is alive
                "Cannot search the bodies of characters who are alive"));

        theEnemy.setIsAlive(false); //now we'll kill the enemy
        String testString2 = theTester.search(theEnemy); //and the Player will search them
        assertTrue(!theEnemy.getInventory().contains(testItem)); //the enemy should no longer have the item
        assertTrue(theTester.getInventory().contains(testItem)); //the Player should now have the enemy's item
        assertTrue(testString2.contains( //the output of the search should be this
                "Tester took TestItem off the body of Enemy"));
        String testString3 = theTester.search(theEnemy); //if the enemy has no items the output should be this
        assertTrue(testString3.contains(
                "Tester searched Enemy but found nothing"));

        //give the enemy another item
        ConsumableItem testItem2 = new ConsumableItem("TestItem2", testStats,
                                                      ImageKey.API);
        theEnemy.getInventory().add(testItem2);
        theTester.setInventorySize(1); //shrink the Player's inventory so their inventory is now full
        String testString4 = theTester.search(theEnemy); //if the Player tries to search the enemy
        assertTrue(testString4.contentEquals( //it should tell them what they found but say that their inventory is full
                "Tester found TestItem2 on Enemy but their inventory is full"));
    }

}
