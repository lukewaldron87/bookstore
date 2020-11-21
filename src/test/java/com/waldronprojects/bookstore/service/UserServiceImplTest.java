package com.waldronprojects.bookstore.service;

import com.waldronprojects.bookstore.dao.UserDao;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserType;
import com.waldronprojects.bookstore.util.UnitTestUserDtoFactory;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	@Mock
	private UserDao userDao;

	@Mock
	private UserMapService userMap;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private UserEntityFactory userEntityFactory;
	private UserDtoFactory userDtoFactory;

	@Before
	public void setUp(){
		userEntityFactory = new UnitTestUserEntityFactory();
		userDtoFactory = new UnitTestUserDtoFactory();
	}

	@Test
	public void testFindUsername() {
		// Create a customer for userDao.findByUsername to return
		User mockUser = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		Mockito.when(userDao.findByUsername(mockUser.getUsername()))
			.thenReturn(mockUser);
		User returnedUser = userServiceImpl.findUsername(mockUser.getUsername());
		assertEquals(mockUser, returnedUser);
	}
	
	@Test
	public void testDeleteUser() {
		long id = 1;
		userServiceImpl.deleteUser(id);
		Mockito.verify(userDao, Mockito.times(1)).deleteUser(id);
	}
	
	@Test
	public void testSaveUser() {

		UserDto userDto = userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
		User userEntity = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		Mockito.when(userMap.mapDtoToNewEntity(userDto)).thenReturn(userEntity);
		userServiceImpl.saveUser(userDto);

		// test userDto is passed to userMap.mapDtoToNewEntity
		ArgumentCaptor<UserDto> userDtoCaptor = ArgumentCaptor.forClass(UserDto.class);
		Mockito.verify(userMap, Mockito.times(1))
				.mapDtoToNewEntity(userDtoCaptor.capture());
		UserDto capturedUserDto = userDtoCaptor.getValue();
		assertEquals(userDto, capturedUserDto);

		// test userEntity is passed to userDao.createOrUpdateUser
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		Mockito.verify(userDao, Mockito.times(1))
				.createOrUpdateUser(userCaptor.capture());
		User capturedUser = userCaptor.getValue();
		assertEquals(userEntity, capturedUser);
	}

	@Test
	public void testUpdateUser(){
		UserDto userDto = userDtoFactory.createPartialUserDto(RoleType.ROLE_CUSTOMER);
		// return Entity from userDao.findUserById for update
		User targetUserEntity = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		Mockito.when(userDao.findUserById(userDto.getId()))
				.thenReturn(targetUserEntity);
		// return entity from userMap.mapDtoToExistingEntity
		User updatedUserEntity = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		Mockito.when(userMap.mapDtoToExistingEntity(userDto, targetUserEntity))
				.thenReturn(updatedUserEntity);

		userServiceImpl.updateUser(userDto);

		// test sourceUserDto and  targetUserEntity is passed to userMap.mapDtoToExistingEntity
		ArgumentCaptor<UserDto> userDtoCaptor = ArgumentCaptor.forClass(UserDto.class);
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		Mockito.verify(userMap, Mockito.times(1))
				.mapDtoToExistingEntity(userDtoCaptor.capture(), userCaptor.capture());
		UserDto capturedUserDto = userDtoCaptor.getValue();
		assertEquals(userDto, capturedUserDto);
		User capturedUser = userCaptor.getValue();
		assertEquals(targetUserEntity, capturedUser);

		// test capturedUser is passed to userDao.createOrUpdateUser
		Mockito.verify(userDao, Mockito.times(1))
				.createOrUpdateUser(userCaptor.capture());
		capturedUser = userCaptor.getValue();
		assertEquals(updatedUserEntity, capturedUser);
	}

	@Test
	public void testGetUser(){
		User user = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		Mockito.when(userDao.findUserById(user.getId()))
				.thenReturn(user);
		UserDto userDto = userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
		Mockito.when(userMap.mapEntityToNewDto(user))
				.thenReturn(userDto);

		UserDto returnedUserDto = userServiceImpl.getUser(user.getId());

		// test user is passed to userMap.mapEntityToNewDto
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		Mockito.verify(userMap, Mockito.times(1))
				.mapEntityToNewDto(userCaptor.capture());
		User capturedUser = userCaptor.getValue();
		assertEquals(user, capturedUser);

		// assert userDto is returned by getUser
		assertEquals(userDto, returnedUserDto);
	}

	@Test
	public void testLoadCustomerUserByUsername(){
		User user = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);
		UserDetails userDetails = userServiceImpl.loadUserByUsername(user.getUsername());
		testUserDetails(userDetails, user);
	}

	@Test
	public void testLoadEmployeeUserByUsername(){
		User user = userEntityFactory.createUser(RoleType.ROLE_EMPLOYEE);
		Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);
		UserDetails userDetails = userServiceImpl.loadUserByUsername(user.getUsername());
		testUserDetails(userDetails, user);
	}

	@Test
	public void testLoadAdminUserByUsername(){
		User user = userEntityFactory.createUser(RoleType.ROLE_ADMIN);
		Mockito.when(userDao.findByUsername(user.getUsername())).thenReturn(user);
		UserDetails userDetails = userServiceImpl.loadUserByUsername(user.getUsername());
		testUserDetails(userDetails, user);
	}

	private void testUserDetails(UserDetails userDetails, User user){
		assertEquals(userDetails.getUsername(), user.getUsername());
		assertEquals(userDetails.getPassword(), user.getPassword());

		Collection<Role> roleCollection = user.getRoles();
		List<String> roleNameList = new ArrayList<>();
		for(Role role: roleCollection){
			roleNameList.add(role.getName());
		}

		Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();
		for (GrantedAuthority authority: grantedAuthorities){
			assertTrue(roleNameList.contains(authority.getAuthority()));
		}

	}

	@Test(expected = UsernameNotFoundException.class)
	public void testLoadNullUsername(){
		Mockito.when(userDao.findByUsername(null)).thenReturn(null);
		userServiceImpl.loadUserByUsername(null);
	}

	@Test
	public void testGetUsersOfType_getsCustomers(){
		UserType userType = UserType.CUSTOMER;
		List<User> userList = createUserListOfType(RoleType.ROLE_CUSTOMER);
		Mockito.when(userDao.getUsersOfType(userType)).thenReturn(userList);
		List<User> returnedUserList = userServiceImpl.getUsersOfType(userType);
		assertEquals(userList, returnedUserList);
	}

	@Test
	public void testGetUsersOfType_getsEmployee(){
		UserType userType = UserType.EMPLOYEE;
		List<User> userList = createUserListOfType(RoleType.ROLE_EMPLOYEE);
		Mockito.when(userDao.getUsersOfType(userType)).thenReturn(userList);
		List<User> returnedUserList = userServiceImpl.getUsersOfType(userType);
		assertEquals(userList, returnedUserList);
	}

	private List<User> createUserListOfType(RoleType roleType){
		List<User> userList = new ArrayList<>();
		for(int i = 0; i<3; i++){
			userList.add(userEntityFactory.createUser(roleType));
		}
		return userList;
	}

}
