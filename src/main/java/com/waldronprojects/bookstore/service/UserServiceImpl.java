package com.waldronprojects.bookstore.service;

import com.waldronprojects.bookstore.dao.UserDao;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapService userMap;

	@Override
	@Transactional
	public User findUsername(String username) {
		return userDao.findByUsername(username); 
	}
	
	@Override
	@Transactional
	public void deleteUser(Long id) {
		userDao.deleteUser(id);
	}

	@Override
	@Transactional
	public List<User> getUsersOfType(UserType customer) {
		return userDao.getUsersOfType(customer);
	}

	@Override
	@Transactional
	public void saveUser(UserDto userDto) {

		/****** CHANGE TO BE TRANSLATED AUTOMATICALLY IN THE CONTROLLER ******/
		User user = userMap.mapDtoToNewEntity(userDto);
		userDao.createOrUpdateUser(user);
	}

	@Override
	@Transactional
	public void updateUser(UserDto userDto) {
		User updatedUserEntity = updateExistingUserEntity(userDto);
		userDao.createOrUpdateUser(updatedUserEntity);
	}

	private User updateExistingUserEntity(UserDto sourceUserDto) {
		// get existing user for current id
		User targetUserEntity = userDao.findUserById(sourceUserDto.getId());
		return userMap.mapDtoToExistingEntity(sourceUserDto, targetUserEntity);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
												.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public  UserDto getUser(Long id) {
		User user = userDao.findUserById(id);
		return userMap.mapEntityToNewDto(user);
	}
}
