package com.waldronprojects.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.factory.UnitTestUserDtoFactory;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.factory.UnitTestUserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.dao.UserDao;
import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	@Captor
	private ArgumentCaptor<User> captor;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private RoleDao roleDao;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void testFindUsername() {
		// Create a customer for userDao.findByUsername to return
		User mockUser = createUserObject(UserType.CUSTOMER);
		Mockito.when(userDao.findByUsername(mockUser.getUsername()))
			.thenReturn(mockUser);
		User returnedUser = userServiceImpl.findUsername(mockUser.getUsername());
		assertEquals(mockUser, returnedUser);
	}
	
	@Test
	public void testDeleteUser() {
		long id = 1;
		//Mockito.when(userDao.deleteUser(id))
		userServiceImpl.deleteUser(id);
		Mockito.verify(userDao, Mockito.times(1)).deleteUser(id);
	}
	
	@Test
	public void testSaveCustomerUser() {
		
		// call saveUser and pass a created userDto to it
		UserDto userDto = createUserDtoObject(UserType.CUSTOMER);
		// mock call to roleDao.findRoleByName
		Mockito.when(roleDao.findRoleByName("ROLE_CUSTOMER"))
				.thenReturn(new Role("ROLE_CUSTOMER"));
		// return unencoded password to allow assert comparision
		String password = userDto.getPassword();
		Mockito.when(passwordEncoder.encode(password))
				.thenReturn(password);


		userServiceImpl.saveUser(userDto);
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		Mockito.verify(userDao, Mockito.times(1))
				.addUser(captor.capture());
		User user = captor.getValue();
		testUserVariables(user, userDto);
		testCustomerVariables((Customer)user, (CustomerDto)userDto);
	}

	@Test
	public void testSaveEmployeeUser() {
		UserDto userDto = createUserDtoObject(UserType.EMPLOYEE);
		Mockito.when(roleDao.findRoleByName("ROLE_EMPLOYEE"))
				.thenReturn(new Role("ROLE_EMPLOYEE"));
		String password = userDto.getPassword();
		Mockito.when(passwordEncoder.encode(password))
				.thenReturn(password);
		userServiceImpl.saveUser(userDto);
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		Mockito.verify(userDao, Mockito.times(1))
				.addUser(captor.capture());
		User user = captor.getValue();
		testUserVariables(user, userDto);
		testEmployeeVariables((Employee)user, (EmployeeDto)userDto);

	}

	@Test
	public void testSaveAdminEmployeeUser() {
		UserDto userDto = createUserDtoObject(UserType.ADMIN);
		// return unencoded password to allow assert comparision
		String password = userDto.getPassword();
		Mockito.when(passwordEncoder.encode(password))
				.thenReturn(password);
		Mockito.when(roleDao.findRoleByName("ROLE_EMPLOYEE"))
				.thenReturn(new Role("ROLE_EMPLOYEE"));
		Mockito.when(roleDao.findRoleByName("ROLE_ADMIN"))
				.thenReturn(new Role("ROLE_ADMIN"));


		userServiceImpl.saveUser(userDto);
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		Mockito.verify(userDao, Mockito.times(1))
				.addUser(captor.capture());
		User user = captor.getValue();
		testUserVariables(user, userDto);
		testEmployeeVariables((Employee)user, (EmployeeDto)userDto);

	}

	@Test
	public void testGetCustomerUser(){
		User user = createUserObject(UserType.CUSTOMER);
		Mockito.when(userDao.findUserById(user.getId()))
				.thenReturn(user);
		UserDto userDto = userServiceImpl.getUser(user.getId());
		testUserVariables(user, userDto);
		testCustomerVariables((Customer)user, (CustomerDto)userDto);
	}

	@Test
	public void testGetEmployeeUser(){
		User user = createUserObject(UserType.EMPLOYEE);
		Mockito.when(userDao.findUserById(user.getId()))
				.thenReturn(user);
		UserDto userDto = userServiceImpl.getUser(user.getId());
		testUserVariables(user, userDto);
		testEmployeeVariables((Employee)user, (EmployeeDto)userDto);
	}

	@Test
	public void testGetAdminEmployeeUser(){
		User user = createUserObject(UserType.ADMIN);
		Mockito.when(userDao.findUserById(user.getId()))
				.thenReturn(user);
		UserDto userDto = userServiceImpl.getUser(user.getId());
		testUserVariables(user, userDto);
		testEmployeeVariables((Employee)user, (EmployeeDto)userDto);
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

	@Test
	public void testLoadCustomerUserByUsername(){
		User user = createUserObject(UserType.CUSTOMER);
		Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);
		UserDetails userDetails = userServiceImpl.loadUserByUsername(user.getUsername());
		testUserDetails(userDetails, user);
	}

	@Test
	public void testLoadEmployeeUserByUsername(){
		User user = createUserObject(UserType.EMPLOYEE);
		Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);
		UserDetails userDetails = userServiceImpl.loadUserByUsername(user.getUsername());
		testUserDetails(userDetails, user);
	}

	@Test
	public void testLoadAdminUserByUsername(){
		User user = createUserObject(UserType.ADMIN);
		Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);
		UserDetails userDetails = userServiceImpl.loadUserByUsername(user.getUsername());
		testUserDetails(userDetails, user);
	}

	private void testUserDetails(UserDetails userDetails, User user){
		assertEquals(userDetails.getUsername(), user.getUsername());
		assertEquals(userDetails.getPassword(), user.getPassword());

		Collection<Role> roleCollection = user.getRoles();
		List<String> roleNameList = new ArrayList<String>();
		for(Role role: roleCollection){
			roleNameList.add(role.getName());
		}

		Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();
		for (GrantedAuthority authority: grantedAuthorities){
			assertNotNull(roleNameList.contains(authority.getAuthority()));
		}

	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadNullUsername(){
		Mockito.when(userDao.findByUsername(null)).thenReturn(null);
		UserDetails userDetails = userServiceImpl.loadUserByUsername(null);
	}

	private User createUserObject(UserType type) {
		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		User user = userEntityFactory.createUser(type);
		return user;
	}
	
	private UserDto createUserDtoObject(UserType type) {
		UserDtoFactory dtoFactory = new UnitTestUserDtoFactory();
		UserDto userDto = dtoFactory.createUserDto(type);
		return userDto;
	}

}
