package com.waldronprojects.bookstore.service;

import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.dao.UserDao;
import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserType;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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
		User user = mapDtoToNewEntity(userDto);
		userDao.createOrUpdateUser(user);
	}

	private User mapDtoToNewEntity(UserDto userDto){
		User user = new User();
		Collection<Role> roleCollection = new ArrayList<>();
		Role role;
		ModelMapper modelMapper = new ModelMapper();
		if(userDto instanceof CustomerDto) {
			user = modelMapper.map(userDto, Customer.class);
			role = roleDao.findRoleByName("ROLE_CUSTOMER");
			roleCollection.add(role);
		}else if(userDto instanceof EmployeeDto) {
			user = modelMapper.map(userDto, Employee.class);
			roleCollection = createEmployeeRoleCollection((EmployeeDto)userDto);
		}
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		user.setPassword(encodedPassword);
		user.setRoles(roleCollection);
		return user;
	}
	
	private Collection<Role> createEmployeeRoleCollection(EmployeeDto employeeDto) {
		Collection<Role> roleCollection = new ArrayList<>();
		roleCollection.add(roleDao.findRoleByName("ROLE_EMPLOYEE"));
		if(employeeDto.getIsAdmin()) {
			roleCollection.add(roleDao.findRoleByName("ROLE_ADMIN"));
		}
		return roleCollection;
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
		return mapDtoToExistingEntity(sourceUserDto, targetUserEntity);
	}

	private User mapDtoToExistingEntity(UserDto sourceUserDto, User targetUserEntity) {
		// update user with values from dto
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(sourceUserDto, targetUserEntity);
		if(sourceUserDto instanceof EmployeeDto) {
			targetUserEntity = mapAdminRole((EmployeeDto) sourceUserDto, targetUserEntity);
		}
		return targetUserEntity;
	}

	private User mapAdminRole(EmployeeDto sourceUserDto, User targetUserEntity) {
		if(sourceUserDto.getIsAdmin()){
			return addAdminRoleFromUserEntity(targetUserEntity);
		}else{
			return removeAdminRoleFromUserEntity(targetUserEntity);
		}
	}

	private User addAdminRoleFromUserEntity(User userEntity) {
		// if not admin add admin role
		if(!checkEmployeeIsAdmin((Employee) userEntity)){
			Collection<Role> roleCollection = userEntity.getRoles();
			Role adminRole = roleDao.findRoleByName("ROLE_ADMIN");
			roleCollection.add(adminRole);
			userEntity.setRoles(roleCollection);
		}
		return userEntity;
	}

	private User removeAdminRoleFromUserEntity(User targetUserEntity) {
		//remove admin role
		if(checkEmployeeIsAdmin((Employee) targetUserEntity)){
			Collection<Role> roleCollection = targetUserEntity.getRoles();
			Iterator<Role> roleIterator = roleCollection.iterator();
			while(roleIterator.hasNext()){
				Role role = roleIterator.next();
				if(role.getName().equals(RoleType.ROLE_ADMIN.toString())){
					roleIterator.remove();
					break;
				}
			}
			targetUserEntity.setRoles(roleCollection);
		}
		return targetUserEntity;
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
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public  UserDto getUser(Long id) {
		User user = userDao.findUserById(id);
		return mapEntityToNewDto(user);
	}
	
	private UserDto mapEntityToNewDto(User user) {
		UserDto userDto = new UserDto();
		ModelMapper modelMapper = new ModelMapper();
		if(user instanceof Customer) {
			userDto = modelMapper.map(user, CustomerDto.class);
		}else if(user instanceof Employee) {
			userDto = modelMapper.map(user, EmployeeDto.class);
			if(checkEmployeeIsAdmin((Employee)user)) {
				userDto = setUserDtoAsAdmin((EmployeeDto) userDto);
			}
		}
		return userDto;
	}

	private boolean checkEmployeeIsAdmin(Employee employee) {
		for(Role role: employee.getRoles()) {
			if(role.getName().equals("ROLE_ADMIN")){
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
