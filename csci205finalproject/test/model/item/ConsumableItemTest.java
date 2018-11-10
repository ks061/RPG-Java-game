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
  * Package: model.item
  * File: ConsumableItemTest
  * Description: This file contains ConsumableItemTest.
  * ****************************************
 */
package model.item;

import junit.framework.TestCase;
import model.character.Player;
import org.junit.Test;

/**
 * JUnit tests for the class ConsumableItem
 *
 * @author lts010
 */
public class ConsumableItemTest extends TestCase {

    private Player theOwner;
    private ConsumableItem testItem1;
    private ConsumableItem testItem2;

    @Override
    public void setUp() {
        theOwner = new Player("Tester");
        testItem1 = new ConsumableItem("TestItem1", 3, 3, 3, 3);
        testItem2 = new ConsumableItem("TestItem1", 3, 3, 3, 3);
        theOwner.getInventory().add(testItem1);
        theOwner.getInventory().add(testItem2);
        testItem1.setOwner(theOwner);
        testItem2.setOwner(theOwner);
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of consume method, of class ConsumableItem.
     */
    @Test
    public void testConsume() {
        System.out.println("consume");
        theOwner.setHealth(6);
        testItem1.consume();
        assertTrue(!theOwner.getInventory().contains(testItem1));
        assertTrue(theOwner.getAttack() == 13);
        assertTrue(theOwner.getDefense() == 13);
        assertTrue(theOwner.getInventorySize() == 7);
        assertTrue(theOwner.getHealth() == 9);
        testItem2.consume();
        assertTrue(!theOwner.getInventory().contains(testItem2));
        assertTrue(theOwner.getAttack() == 16);
        assertTrue(theOwner.getDefense() == 16);
        assertTrue(theOwner.getInventorySize() == 10);
        assertTrue(theOwner.getHealth() == 10);
    }

}
