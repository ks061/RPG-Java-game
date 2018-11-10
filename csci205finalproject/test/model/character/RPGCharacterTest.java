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

import junit.framework.TestCase;
import model.item.ConsumableItem;
import model.item.Equipment;
import model.item.EquipmentType;
import org.junit.Test;

/**
 * JUnit tests for the class RPGCharacter
 *
 * @author lts010
 */
public class RPGCharacterTest extends TestCase {

    private Player theTester;

    @Override
    public void setUp() {
        theTester = new Player("Tester");
    }

    @Override
    public void tearDown() {
    }

    /**
     * Test of use method, of class RPGCharacter.
     */
    @Test
    public void testUse() {
        System.out.println("use");
        ConsumableItem testConsumable = new ConsumableItem("testItem", 3, 3, 3,
                                                           3);
        Equipment testArmor = new Equipment("testArmor", 3, 3, 3, 3,
                                            EquipmentType.ARMOR);
        theTester.getInventory().add(testConsumable);
        theTester.getInventory().add(testArmor);
        testConsumable.setOwner(theTester);
        testArmor.setOwner(theTester);
        theTester.use(testConsumable);
        assertTrue(!theTester.getInventory().contains(testConsumable));
        theTester.use(testArmor);
        assertTrue(!theTester.getInventory().contains(testArmor));
        assertTrue(theTester.getArmor() == testArmor);
        theTester.use(testArmor);
        assertTrue(theTester.getInventory().contains(testArmor));
        assertTrue(theTester.getArmor() == null);
    }

    /**
     * Test of attack method, of class RPGCharacter.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        NPC theEnemy = new NPC("theEnemy", 10, 10, 5, "", "", false);
        String testString = theTester.attack(theEnemy);
        System.out.println(theEnemy.getHealth());
        assertTrue(
                theEnemy.getHealth() == 5 || theEnemy.getHealth() == 10 || theEnemy.getHealth() == 2);
        if (theEnemy.getHealth() == 5) {
            assertTrue(testString.contentEquals(
                    "Tester did 5 damage to theEnemy"));
        }
        else if (theEnemy.getHealth() == 10) {
            assertTrue(testString.contentEquals(
                    "Tester missed and did no damage to theEnemy"));
        }
        else {
            assertTrue(testString.contentEquals(
                    "Critical Hit! Tester did 8 damage to theEnemy"));
        }
    }
}
