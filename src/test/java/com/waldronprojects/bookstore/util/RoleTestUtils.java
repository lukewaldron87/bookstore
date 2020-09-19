package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;

import java.util.Collection;

public class RoleTestUtils {
    
    // Surpress default constructor for noninstantiability
    private RoleTestUtils(){
        throw new AssertionError();
    }

    public static boolean testAssignedRoles(User user, RoleType roleType){
        Collection<Role> roles = user.getRoles();
        boolean containsRole = false;
        for(Role role: roles){
            if(role.getName().equals(roleType.toString())){
                containsRole = true;
                break;
            }
        }
        return containsRole;
    }
}
