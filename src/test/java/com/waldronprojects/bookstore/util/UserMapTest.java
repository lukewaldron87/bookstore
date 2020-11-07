package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMapTest {

    @Mock
    private RoleDao roleDao;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private UserMap userMap;

    private UserEntityFactory userEntityFactory;
    private UserDtoFactory userDtoFactory;

    @Before
    public void setUp(){
        userEntityFactory = new UnitTestUserEntityFactory();
        userDtoFactory = new UnitTestUserDtoFactory();
    }

    @Test
    public void mapDtoToNewEntity_CustomerUser() {
        UserDto userDto = userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
        Mockito.when(roleDao.findRoleByName(RoleType.ROLE_CUSTOMER.toString()))
                .thenReturn(new Role(RoleType.ROLE_CUSTOMER.toString()));
        // return unencoded password to allow assert comparison
        String password = userDto.getPassword();
        Mockito.when(passwordEncoder.encode(password))
                .thenReturn(password);

        User user = userMap.mapDtoToNewEntity(userDto);
        testUserVariables(user, userDto);
        testCustomerVariables((Customer)user, (CustomerDto)userDto);
    }

    @Test
    public void mapDtoToNewEntity_EmployeeUser() {
        UserDto userDto = userDtoFactory.createUserDto(RoleType.ROLE_EMPLOYEE);
        Mockito.when(roleDao.findRoleByName(RoleType.ROLE_EMPLOYEE.toString()))
                .thenReturn(new Role(RoleType.ROLE_EMPLOYEE.toString()));
        // return unencoded password to allow assert comparison
        String password = userDto.getPassword();
        Mockito.when(passwordEncoder.encode(password))
                .thenReturn(password);

        User user = userMap.mapDtoToNewEntity(userDto);
        testUserVariables(user, userDto);
        testEmployeeVariables((Employee) user, (EmployeeDto) userDto);
    }

    @Test
    public void mapDtoToNewEntity_AdminEmployeeUser() {
        UserDto userDto = userDtoFactory.createUserDto(RoleType.ROLE_ADMIN);
        Mockito.when(roleDao.findRoleByName(RoleType.ROLE_EMPLOYEE.toString()))
                .thenReturn(new Role(RoleType.ROLE_EMPLOYEE.toString()));
        Mockito.when(roleDao.findRoleByName(RoleType.ROLE_ADMIN.toString()))
                .thenReturn(new Role(RoleType.ROLE_ADMIN.toString()));
        // return unencoded password to allow assert comparison
        String password = userDto.getPassword();
        Mockito.when(passwordEncoder.encode(password))
                .thenReturn(password);

        User user = userMap.mapDtoToNewEntity(userDto);
        testUserVariables(user, userDto);
        testEmployeeVariables((Employee) user, (EmployeeDto) userDto);
    }

    @Test
    public void mapDtoToExistingEntity_CustomerUser() {
        CustomerDto userDto = (CustomerDto)userDtoFactory
                .createPartialUserDto(RoleType.ROLE_CUSTOMER);
        String newFirstName = "newFirstName";
        String newCity = "newCity";
        userDto.setFirstName(newFirstName);
        userDto.setCity(newCity);

        User userEntity = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);

        userEntity = userMap.mapDtoToExistingEntity(userDto, userEntity);
        testUpdateUserVariables(userDto, userEntity);
        testUpdateCustomerUserVariables(userDto, (Customer) userEntity);
    }

    private void testUpdateCustomerUserVariables(CustomerDto userDto, Customer capturedUser) {
        assertNotEquals(userDto.getAddressLine1(), capturedUser.getAddressLine1());
        assertNotEquals(userDto.getPhoneNumber(), capturedUser.getPhoneNumber());
        assertEquals(userDto.getCity(), capturedUser.getCity());
    }

    @Test
    public void mapDtoToExistingEntity_EmployeeUser() {
        EmployeeDto userDto = (EmployeeDto)userDtoFactory
                .createPartialUserDto(RoleType.ROLE_EMPLOYEE);
        String newFirstName = "newFirstName";
        String newTitle = "newTitle";
        userDto.setFirstName(newFirstName);
        userDto.setTitle(newTitle);

        User userEntity = userEntityFactory.createUser(RoleType.ROLE_EMPLOYEE);

        userEntity = userMap.mapDtoToExistingEntity(userDto, userEntity);
        testUpdateUserVariables(userDto, userEntity);
        testUpdateEmployeeUserVariables(userDto, (Employee) userEntity);
    }

    /**
     * Add admin role to employee
     */
    @Test
    public void mapDtoToExistingEntity_EmployeeToAdminEmployeeUser() {
        EmployeeDto userDto = (EmployeeDto)userDtoFactory
                .createPartialUserDto(RoleType.ROLE_ADMIN);
        String newFirstName = "newFirstName";
        String newTitle = "newTitle";
        userDto.setFirstName(newFirstName);
        userDto.setTitle(newTitle);
        // remove roles as they are not assigned by the UI
        userDto.setRoles(null);

        // create an employee user so it doesn't already have the Admin Role assigned
        User userEntity = userEntityFactory.createUser(RoleType.ROLE_EMPLOYEE);
        Mockito.when(roleDao.findRoleByName(RoleType.ROLE_ADMIN.toString()))
                .thenReturn(new Role(RoleType.ROLE_ADMIN.toString()));

        userEntity = userMap.mapDtoToExistingEntity(userDto, userEntity);
        // check if admin role was added
        boolean isAdmin = false;
        for(Role role: userEntity.getRoles()){
            if(role.getName().equals(RoleType.ROLE_ADMIN.toString())){
                isAdmin = true;
                break;
            }
        }
        assertTrue(isAdmin);
    }

    /**
     * Remove admin role to employee
     */
    @Test
    public void mapDtoToExistingEntity_AdminEmployeeToEmployeeUser() {
        EmployeeDto userDto = (EmployeeDto)userDtoFactory
                .createPartialUserDto(RoleType.ROLE_EMPLOYEE);
        String newFirstName = "newFirstName";
        String newTitle = "newTitle";
        userDto.setFirstName(newFirstName);
        userDto.setTitle(newTitle);
        // remove roles as they are not assigned by the UI
        userDto.setRoles(null);

        // Create an Admin user that already has the Admin Role
        User userEntity = userEntityFactory.createUser(RoleType.ROLE_ADMIN);

        userEntity = userMap.mapDtoToExistingEntity(userDto, userEntity);
        // check if admin role was removed
        boolean isAdmin = false;
        for(Role role: userEntity.getRoles()){
            if(role.getName().equals(RoleType.ROLE_ADMIN.toString())){
                isAdmin = true;
                break;
            }
        }
        assertFalse(isAdmin);
    }

    private void testUpdateUserVariables(UserDto userDto, User userEntity) {
        assertNotEquals(userDto.getUsername(), userEntity.getUsername());
        assertNotEquals(userDto.getPassword(), userEntity.getPassword());
        assertNotEquals(userDto.getEmail(), userEntity.getEmail());
        assertEquals(userDto.getFirstName(), userEntity.getFirstName());
    }

    private void testUpdateEmployeeUserVariables(EmployeeDto userDto, Employee userEntity) {
        assertNotEquals(userDto.getDepartment(), userEntity.getDepartment());
        assertEquals(userDto.getTitle(), userEntity.getTitle());
    }

    @Test
    public void mapEntityToNewDto_Customer() {
        User userEntity = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
        UserDto userDto = userMap.mapEntityToNewDto(userEntity);
        testUserVariables(userEntity, userDto);
        testCustomerVariables((Customer)userEntity, (CustomerDto)userDto);
    }

    @Test
    public void mapEntityToNewDto_Employee() {
        User userEntity = userEntityFactory.createUser(RoleType.ROLE_EMPLOYEE);
        UserDto userDto = userMap.mapEntityToNewDto(userEntity);
        testUserVariables(userEntity, userDto);
        testEmployeeVariables((Employee)userEntity, (EmployeeDto)userDto);
    }

    @Test
    public void mapEntityToNewDto_AdminEmployee() {
        User userEntity = userEntityFactory.createUser(RoleType.ROLE_ADMIN);
        UserDto userDto = userMap.mapEntityToNewDto(userEntity);
        testUserVariables(userEntity, userDto);
        testEmployeeVariables((Employee)userEntity, (EmployeeDto)userDto);
    }

    private void testUserVariables(User userEntity, UserDto userDto){
        assertEquals(userDto.getId(), userEntity.getId());
        assertEquals(userDto.getUsername(), userEntity.getUsername());
        assertEquals(userDto.getPassword(), userEntity.getPassword());
        assertEquals(userDto.getFirstName(), userEntity.getFirstName());
        assertEquals(userDto.getLastName(), userEntity.getLastName());
        assertEquals(userDto.getEmail(), userEntity.getEmail());
        testAssignedRoles(userEntity, userDto);
    }

    private void testAssignedRoles(User userEntity, UserDto userDto) {
        Collection<Role> roleCollection = userEntity.getRoles();
        if(userEntity instanceof Customer){
            assertEquals(roleCollection.size(), 1);
            assertEquals(roleCollection.iterator().next().getName(), "ROLE_CUSTOMER");
        }if(userEntity instanceof Employee){
            testRolesAssignedToEmployeeEntity(roleCollection, (EmployeeDto) userDto);
        }
    }

    private void testRolesAssignedToEmployeeEntity(Collection<Role> roleCollection,
                                                   EmployeeDto employeeDto) {
        Iterator<Role> roleIterator = roleCollection.iterator();
        assertEquals(roleIterator.next().getName(), "ROLE_EMPLOYEE");
        if (employeeDto.getIsAdmin()){
            assertEquals(roleCollection.size(), 2);
            assertEquals(roleIterator.next().getName(), "ROLE_ADMIN");
        }else{
            assertEquals(roleCollection.size(), 1);
        }
    }

    private void testCustomerVariables(Customer customerEntity, CustomerDto customerDto) {
        assertEquals(customerDto.getAddressLine1(), customerEntity.getAddressLine1());
        assertEquals(customerDto.getAddressLine2(), customerEntity.getAddressLine2());
        assertEquals(customerDto.getCity(), customerEntity.getCity());
        assertEquals(customerDto.getCountry(), customerEntity.getCountry());
        assertEquals(customerDto.getPostCode(), customerEntity.getPostCode());
        assertEquals(customerDto.getPhoneNumber(), customerEntity.getPhoneNumber());
    }

    private void testEmployeeVariables(Employee employee, EmployeeDto employeeDto) {
        assertEquals(employee.getDepartment(), employeeDto.getDepartment());
        assertEquals(employee.getTitle(), employeeDto.getTitle());
    }
}