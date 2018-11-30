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
import static junit.framework.TestCase.assertTrue;
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
        ItemStatistics testStats = new ItemStatistics(3, 3, 3, 3);
        ConsumableItem testConsumable = new ConsumableItem("testItem", testStats,
                                                           ImageKey.NOTEPAD);
        Equipment testArmor = new Equipment("testArmor", testStats,
                                            ItemType.ARMOR, ImageKey.API
        );
        theTester.getInventory().add(testConsumable);
        theTester.getInventory().add(testArmor);
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
        RPGCharacterStats testStats = new RPGCharacterStats(10, 10, 5);
        ArrayList<String> testDialogue = new ArrayList<String>();
        testDialogue.add("");
        testDialogue.add("");
        NPC theEnemy = new NPC("theEnemy", testStats, testDialogue, false);
        String testString = theTester.attack(theEnemy);
        System.out.println(theEnemy.getCharacterStats().getHealth());
        assertTrue(
                theEnemy.getCharacterStats().getHealth() == 5 || theEnemy.getCharacterStats().getHealth() == 10 || theEnemy.getCharacterStats().getHealth() == 2);
        if (theEnemy.getCharacterStats().getHealth() == 5) {
            assertTrue(testString.contentEquals(
                    "Tester did 5 damage to theEnemy"));
        }
        else if (theEnemy.getCharacterStats().getHealth() == 10) {
            assertTrue(testString.contentEquals(
                    "Tester missed and did no damage to theEnemy"));
        }
        else {
            assertTrue(testString.contentEquals(
                    "Critical Hit! Tester did 8 damage to theEnemy"));
        }
    }

    /**
     * Test of equip method, of class RPGCharacter.
     */
    @Test
    public void testEquip() {
        System.out.println("equip");
        ItemStatistics testStats1 = new ItemStatistics(3, 3, 3, 0);
        ItemStatistics testStats2 = new ItemStatistics(3, 3, 3, 3);
        Equipment weapon = new Equipment("TestWeapon", testStats1,
                                         ItemType.WEAPON, ImageKey.PEN_AND_PAPER);
        Equipment shield = new Equipment("TestShield", testStats2,
                                         ItemType.SHIELD, ImageKey.MACHINE_CODE);
        Equipment armor = new Equipment("TestArmor", testStats2,
                                        ItemType.ARMOR, ImageKey.API);
        theTester.getInventory().add(weapon);
        theTester.getInventory().add(shield);
        theTester.getInventory().add(armor);
        assertTrue(theTester.getWeapon() == null);
        assertTrue(theTester.getArmor() == null);
        assertTrue(theTester.getShield() == null);
        String stringOne = theTester.equip(weapon);
        String stringTwo = theTester.equip(shield);
        String stringThree = theTester.equip(armor);
        assertTrue(stringOne.contentEquals(
                "Equipped the TestWeapon as a WEAPON"));
        assertTrue(!theTester.getInventory().contains(weapon));
        assertTrue(!theTester.getInventory().contains(armor));
        assertTrue(!theTester.getInventory().contains(shield));
        assertTrue(
                theTester.getCharacterStats().getAttack() == Player.DEFAULT_ATTACK + 9);
        assertTrue(
                theTester.getCharacterStats().getDefense() == Player.DEFAULT_DEFENSE + 9);
        assertTrue(
                theTester.getCharacterStats().getMaxHealth() == Player.DEFAULT_MAX_HEALTH + 9);
        assertTrue(
                theTester.getInventorySize() == Player.DEFAULT_INVENTORY_SIZE + 6);
        ItemStatistics testStats3 = new ItemStatistics(1, 1, 1, 1);
        Equipment armor2 = new Equipment("TestArmor2", testStats3,
                                         ItemType.ARMOR, ImageKey.STACK_OVERFLOW);
        theTester.getInventory().add(armor2);

        String stringFour = theTester.equip(armor2);
        assertTrue(stringFour.contentEquals(
                "Unequipped the TestArmor and equipped the TestArmor2"));
        assertTrue(theTester.getArmor() == armor2);
        assertTrue(theTester.getInventory().contains(armor));
        assertTrue(!theTester.getInventory().contains(armor2));
        assertTrue(
                theTester.getCharacterStats().getAttack() == Player.DEFAULT_ATTACK + 9 - 3 + 1);
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
        ItemStatistics testStats1 = new ItemStatistics(3, 3, 3, 0);
        Equipment weapon = new Equipment("TestWeapon", testStats1,
                                         ItemType.WEAPON, ImageKey.PEN_AND_PAPER);
        theTester.getInventory().add(weapon);
        theTester.equip(weapon);
        assertTrue(theTester.getWeapon() == weapon);
        assertTrue(!this.theTester.getInventory().contains(weapon));
        String stringOne = this.theTester.unequip(weapon);
        assertTrue(stringOne.contentEquals(
                "Unequipped the TestWeapon and added it to your inventory"));
        assertTrue(this.theTester.getWeapon() == null);
        assertTrue(this.theTester.getInventory().contains(weapon));
        assertTrue(
                this.theTester.getCharacterStats().getAttack() == Player.DEFAULT_ATTACK);
        assertTrue(
                this.theTester.getCharacterStats().getDefense() == Player.DEFAULT_DEFENSE);
        assertTrue(
                this.theTester.getCharacterStats().getMaxHealth() == Player.DEFAULT_MAX_HEALTH);
        assertTrue(
                this.theTester.getInventorySize() == Player.DEFAULT_INVENTORY_SIZE);
        this.theTester.equip(weapon);
        this.theTester.getInventory().add(weapon); //fill up inventory
        this.theTester.getInventory().add(weapon);
        this.theTester.getInventory().add(weapon);
        this.theTester.getInventory().add(weapon);

        String stringTwo = this.theTester.unequip(weapon);
        assertTrue(stringTwo.contentEquals(
                "Cannot unequip the TestWeapon because your inventory is full"));
        assertTrue(theTester.getWeapon() == weapon);
    }

    /**
     * Test of consume method, of class RPGCharacter.
     */
    @Test
    public void testConsume() {
        System.out.println("consume");
        ItemStatistics testStats = new ItemStatistics(3, 3, 3, 3);
        ConsumableItem testItem1 = new ConsumableItem("TestItem1", testStats,
                                                      ImageKey.JAVA);
        ConsumableItem testItem2 = new ConsumableItem("TestItem2", testStats,
                                                      ImageKey.JAVA);
        theTester.getInventory().add(testItem1);
        theTester.getInventory().add(testItem2);
        theTester.getCharacterStats().setHealth(6);
        theTester.consume(testItem1);
        assertTrue(!theTester.getInventory().contains(testItem1));
        assertTrue(theTester.getCharacterStats().getAttack() == 13);
        assertTrue(theTester.getCharacterStats().getDefense() == 13);
        assertTrue(theTester.getInventorySize() == 7);
        assertTrue(theTester.getCharacterStats().getHealth() == 9);
        theTester.consume(testItem2);
        assertTrue(!theTester.getInventory().contains(testItem2));
        assertTrue(theTester.getCharacterStats().getAttack() == 16);
        assertTrue(theTester.getCharacterStats().getDefense() == 16);
        assertTrue(theTester.getInventorySize() == 10);
        assertTrue(theTester.getCharacterStats().getHealth() == 10);
    }
}
