/**
 * 
 */
package org.esco.indicators.domain.beans.people;

import static org.junit.Assert.*;

import org.esco.indicators.domain.beans.group.Group;
import org.junit.Test;

/**
 * @since
 * @author GIP RECIA - Kevin Frapin <kevin.frapin@recia.fr>
 */
public class UserTest {
    //---------------------------------------------------------------------------------- ATTRIBUTES

    //-------------------------------------------------------------------------------- CONSTRUCTORS

    //--------------------------------------------------------------------------- GETTERS / SETTERS

    //------------------------------------------------------------------------------ PUBLIC METHODS
    /**
     * Test method for {@link org.esco.indicators.domain.beans.people.User#addGroup(org.esco.indicators.domain.beans.group.Group)}.
     */
    @Test
    public void testAddGroup1() {
	User user = new User();
	
	Group group = new Group("Fake name");
	user.addGroup(group);
	
	assertTrue(user.getGroups().contains(group));
    }
    
    /**
     * Test method for {@link org.esco.indicators.domain.beans.people.User#addGroup(org.esco.indicators.domain.beans.group.Group)}.
     */
    @Test
    public void testAddGroup2() {
	User user = new User();
	
	Group group = new Group("Fake name");
	user.addGroup(group);
	
	Group otherGroup = new Group("Fake name 2");
	user.addGroup(otherGroup);
	
	assertTrue(user.getGroups().size() == 2);
    }

    //----------------------------------------------------------------------------- PRIVATE METHODS

    //------------------------------------------------------------------------------ STATIC METHODS
}
