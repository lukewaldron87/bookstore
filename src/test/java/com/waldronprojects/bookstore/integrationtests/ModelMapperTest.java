package com.waldronprojects.bookstore.integrationtests;

import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.util.UnitTestUserDtoFactory;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelMapperTest {
	
	@Test
	public void testDtoToEntity() {
		UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
		CustomerDto customerDto = 
				(CustomerDto)userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
		ModelMapper modelMapper = new ModelMapper();
		Customer customerEntity = modelMapper.map(customerDto, Customer.class);
		checkVariables(customerEntity, customerDto);
	}
	
	@Test
	public void testEntityToDto() {
		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		Customer customerEntity = 
				(Customer)userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		ModelMapper modelMapper = new ModelMapper();
		CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);
		checkVariables(customerEntity, customerDto);
	}
	
	private void checkVariables(Customer customerEntity, CustomerDto customerDto) {
		assertEquals(customerDto.getId(), customerEntity.getId());
		assertEquals(customerDto.getUsername(), customerEntity.getUsername());
		assertEquals(customerDto.getPassword(), customerEntity.getPassword());
		assertEquals(customerDto.getFirstName(), customerEntity.getFirstName());
		assertEquals(customerDto.getLastName(), customerEntity.getLastName());
		assertEquals(customerDto.getEmail(), customerEntity.getEmail());
		assertEquals(customerDto.getRoles(), customerEntity.getRoles());
		assertEquals(customerDto.getAddressLine1(), customerEntity.getAddressLine1());
		assertEquals(customerDto.getAddressLine2(), customerEntity.getAddressLine2());
		assertEquals(customerDto.getCity(), customerEntity.getCity());
		assertEquals(customerDto.getCountry(), customerEntity.getCountry());
		assertEquals(customerDto.getPostCode(), customerEntity.getPostCode());
		assertEquals(customerDto.getPhoneNumber(), customerEntity.getPhoneNumber());
	}

}
