package com.waldronprojects.bookstore.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.waldronprojects.bookstore.config.MvcConfig;
import com.waldronprojects.bookstore.entity.Role;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MvcConfig.class)
public class RoleDaoImplTest {

    @Autowired
    RoleDaoImpl roleDao;

    @Test
    @Transactional
    @Rollback(true)
    public void findRoleByName() {
        assertNotNull(roleDao);
        String roleName = "ROLE_CUSTOMER";
        Role role = roleDao.findRoleByName(roleName);
        assertEquals(roleName, role.getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void findRoleByNameBandName(){
        String roleName = "MISSING_ROLE";
        Role role = roleDao.findRoleByName(roleName);
        assertNull(role.getName());
    }
}