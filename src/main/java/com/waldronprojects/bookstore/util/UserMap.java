package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class UserMap {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserMap() {
    }

    public User mapDtoToNewEntity(UserDto userDto) {
        User user = new User();
        Collection<Role> roleCollection = new ArrayList<Role>();
        Role role;
        ModelMapper modelMapper = new ModelMapper();
        if (userDto instanceof CustomerDto) {
            user = modelMapper.map(userDto, Customer.class);
            role = roleDao.findRoleByName("ROLE_CUSTOMER");
            roleCollection.add(role);
        } else if (userDto instanceof EmployeeDto) {
            user = modelMapper.map(userDto, Employee.class);
            roleCollection = createEmployeeRoleCollection((EmployeeDto) userDto);
        }
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(roleCollection);
        return user;
    }

    private Collection<Role> createEmployeeRoleCollection(EmployeeDto employeeDto) {
        Collection<Role> roleCollection = new ArrayList<Role>();
        roleCollection.add(roleDao.findRoleByName("ROLE_EMPLOYEE"));
        if (employeeDto.getIsAdmin()) {
            roleCollection.add(roleDao.findRoleByName("ROLE_ADMIN"));
        }
        return roleCollection;
    }

    public User mapDtoToExistingEntity(UserDto sourceUserDto, User targetUserEntity) {
        // update user with values from dto
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(sourceUserDto, targetUserEntity);
        if (sourceUserDto instanceof EmployeeDto) {
            targetUserEntity = mapAdminRole((EmployeeDto) sourceUserDto, (Employee) targetUserEntity);
        }
        return targetUserEntity;
    }

    private User mapAdminRole(EmployeeDto sourceUserDto, Employee targetUserEntity) {
        if (sourceUserDto.getIsAdmin()) {
            return addAdminRoleFromUserEntity(targetUserEntity);
        } else {
            return removeAdminRoleFromUserEntity(targetUserEntity);
        }
    }

    private User addAdminRoleFromUserEntity(Employee userEntity) {
        // if not admin add admin role
        if (!checkEmployeeIsAdmin(userEntity)) {
            Collection<Role> roleCollection = userEntity.getRoles();
            Role adminRole = roleDao.findRoleByName("ROLE_ADMIN");
            roleCollection.add(adminRole);
            userEntity.setRoles(roleCollection);
        }
        return userEntity;
    }

    private User removeAdminRoleFromUserEntity(Employee targetUserEntity) {
        //remove admin role
        if (checkEmployeeIsAdmin(targetUserEntity)) {
            Collection<Role> roleCollection = targetUserEntity.getRoles();
            Iterator<Role> roleIterator = roleCollection.iterator();
            while (roleIterator.hasNext()) {
                Role role = roleIterator.next();
                if (role.getName().equals(RoleType.ROLE_ADMIN.toString())) {
                    roleIterator.remove();
                    break;
                }
            }
            targetUserEntity.setRoles(roleCollection);
        }
        return targetUserEntity;
    }

    public UserDto mapEntityToNewDto(User user) {
        UserDto userDto = new UserDto();
        ModelMapper modelMapper = new ModelMapper();
        if (user instanceof Customer) {
            userDto = modelMapper.map(user, CustomerDto.class);
        } else if (user instanceof Employee) {
            userDto = modelMapper.map(user, EmployeeDto.class);
            if (checkEmployeeIsAdmin((Employee) user)) {
                userDto = setUserDtoAsAdmin((EmployeeDto) userDto);
            }
        }
        return userDto;
    }

    private boolean checkEmployeeIsAdmin(Employee employee) {
        for (Role role : employee.getRoles()) {
            if (role.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    private UserDto setUserDtoAsAdmin(EmployeeDto employeeDto) {
        employeeDto.setIsAdmin(true);
        return employeeDto;
    }
}