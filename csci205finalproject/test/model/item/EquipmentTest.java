/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Fall 2018
  *
  * Team Name: PNNSD (Post-Neural Net Stress Disorder)
  * Team Members: Kartikeya Sharma, Logan Stiles, Claudia Shrefler, and Jason Kang
  * Date: Nov 8, 2018
  * Time: 12:46:53 PM
  *
  * Project: csci205FinalProject
  * Package: model.item
  * File: EquipmentTest
  * Description: This file contains EquipmentTest.
  * ****************************************
 */
package model.item;

import junit.framework.TestCase;
import model.character.Player;
import org.junit.Test;

/**
 * JUnit tests for the class Equipment
 *
 * @author lts010
 */
public class EquipmentTest extends TestCase {

    private Player theOwner;
    private Equipment weapon;
    private Equipment shield;
    private Equipment armor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.theOwner = new Player("Tester");
        this.weapon = new Equipment("TestWeapon", 3, 3, 3, 0,
                                    EquipmentType.WEAPON);
        this.shield = new Equipment("TestShield", 3, 3, 3, 3,
                                    EquipmentType.SHIELD);
        this.armor = new Equipment("TestArmor", 3, 3, 3, 3,
                                   EquipmentType.ARMOR);
        weapon.setOwner(theOwner);
        shield.setOwner(theOwner);
        armor.setOwner(theOwner);
        theOwner.getInventory().add(weapon);
        theOwner.getInventory().add(shield);
        theOwner.getInventory().add(armor);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of equip method, of class Equipment.
     */
    @Test
    public void testEquip() {
        System.out.println("equip");
        assertTrue(theOwner.getWeapon() == null);
        assertTrue(theOwner.getArmor() == null);
        assertTrue(theOwner.getShield() == null);
        String stringOne = weapon.equip();
        String stringTwo = shield.equip();
        String stringThree = armor.equip();
        assertTrue(theOwner.getWeapon() == weapon);
        assertTrue(theOwner.getShield() == shield);
        assertTrue(theOwner.getArmor() == armor);
        assertTrue(stringOne.contentEquals(
                "Equipped the TestWeapon as a WEAPON"));
        assertTrue(!theOwner.getInventory().contains(weapon));
        assertTrue(!theOwner.getInventory().contains(armor));
        assertTrue(!theOwner.getInventory().contains(shield));
        assertTrue(theOwner.getAttack() == Player.DEFAULT_ATTACK + 9);
        assertTrue(theOwner.getDefense() == Player.DEFAULT_DEFENSE + 9);
        assertTrue(theOwner.getMaxHealth() == Player.DEFAULT_MAX_HEALTH + 9);
        assertTrue(
                theOwner.getInventorySize() == Player.DEFAULT_INVENTORY_SIZE + 6);
        Equipment armor2 = new Equipment("TestArmor2", 1, 1, 1, 1,
                                         EquipmentType.ARMOR);
        armor2.setOwner(theOwner);
        theOwner.getInventory().add(armor2);

        String stringFour = armor2.equip();
        assertTrue(stringFour.contentEquals(
                "Unequipped the TestArmor and equipped the TestArmor2"));
        assertTrue(theOwner.getArmor() == armor2);
        assertTrue(theOwner.getInventory().contains(this.armor));
        assertTrue(!theOwner.getInventory().contains(armor2));
        assertTrue(theOwner.getAttack() == Player.DEFAULT_ATTACK + 9 - 3 + 1);
        assertTrue(theOwner.getDefense() == Player.DEFAULT_DEFENSE + 9 - 3 + 1);
        assertTrue(
                theOwner.getMaxHealth() == Player.DEFAULT_MAX_HEALTH + 9 - 3 + 1);
        assertTrue(
                theOwner.getInventorySize() == Player.DEFAULT_INVENTORY_SIZE + 6 - 3 + 1);

    }

    /**
     * Test of unequip method, of class Equipment.
     */
    @Test
    public void testUnequip() {
        System.out.println("unequip");
        this.weapon.equip();
        assertTrue(this.theOwner.getWeapon() == this.weapon);
        assertTrue(!this.theOwner.getInventory().contains(this.weapon));
        String stringOne = this.weapon.unequip();
        assertTrue(stringOne.contentEquals(
                "Unequipped the TestWeapon and added it to your inventory"));
        assertTrue(this.theOwner.getWeapon() == null);
        assertTrue(this.theOwner.getInventory().contains(this.weapon));
        assertTrue(this.theOwner.getAttack() == Player.DEFAULT_ATTACK);
        assertTrue(this.theOwner.getDefense() == Player.DEFAULT_DEFENSE);
        assertTrue(this.theOwner.getMaxHealth() == Player.DEFAULT_MAX_HEALTH);
        assertTrue(
                this.theOwner.getInventorySize() == Player.DEFAULT_INVENTORY_SIZE);
        this.weapon.equip();
        Equipment filler1 = new Equipment(
                "FillerItem", 0, 0, 0, 0, EquipmentType.WEAPON);
        Equipment filler2 = new Equipment(
                "FillerItem", 0, 0, 0, 0, EquipmentType.WEAPON);
        this.theOwner.getInventory().add(filler1);
        this.theOwner.getInventory().add(filler2);
        filler1.setOwner(theOwner);
        filler2.setOwner(theOwner);
        String stringTwo = this.weapon.unequip();
        assertTrue(stringTwo.contentEquals(
                "Cannot unequip the TestWeapon because your inventory is full"));
        assertTrue(theOwner.getWeapon() == this.weapon);
        assertTrue(!theOwner.getInventory().contains(this.weapon));

    }
}
