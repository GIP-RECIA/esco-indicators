/**
 * 
 */
package org.esco.indicators.domain.beans.group;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @since 
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class GroupTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES

    //-------------------------------------------------------------------------------- CONSTRUCTORS
    
    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.domain.beans.group.Group#Group(java.lang.String)}.
     */
    @Test
    public void testGroup() {
	String name = "Fake name";
	Group group = new Group(name);
	assertEquals(name, group.getName());
    }

    /**
     * Test method for {@link org.esco.indicators.domain.beans.group.Group#getName()}.
     */
    @Test
    public void testGetName() {
	String name = "Fake name";
	Group group = new Group(name);
	assertEquals(name, group.getName());
    }

    /**
     * Test method for {@link org.esco.indicators.domain.beans.group.Group#setName(java.lang.String)}.
     */
    @Test
    public void testSetName() {
	String name = "Fake name";
	Group group = new Group(name);
	
	String newName = "New fake name";
	group.setName(newName);
	
	assertEquals(newName, group.getName());
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
