package com.waldronprojects.bookstore.integrationtests;

import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.util.UnitTestUserDtoFactory;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.jupiter.api.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ModelMapperTest {
	
	@Test
	public void testCreateEntityFromDto() {
		UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
		CustomerDto customerDto = 
				(CustomerDto)userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
		ModelMapper modelMapper = new ModelMapper();
		Customer customerEntity = modelMapper.map(customerDto, Customer.class);
		checkVariables(customerEntity, customerDto);
	}
	
	@Test
	public void testCreateDtoFromEntity() {
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

	@Test
	public void testUpdateDtoWithEntity(){

		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		Customer customerEntity =
				(Customer)userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);

		String newFirstName = "newFirstName";
		String newCity = "newCity";
		customerEntity.setFirstName(newFirstName);
		customerEntity.setCity(newCity);

		UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
		CustomerDto customerDto =
				(CustomerDto)userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(customerEntity, customerDto);
		assertEquals(customerEntity.getFirstName(), customerDto.getFirstName());
		assertEquals(customerEntity.getCity(), customerDto.getCity());

	}

	@Test
	public void testUpdateDtoWithEntity_doNotUpdateNullFields(){

		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		Customer customerEntity =
				(Customer)userEntityFactory.createPartialUser(RoleType.ROLE_CUSTOMER);

		String newFirstName = "newFirstName";
		String newCity = "newCity";
		customerEntity.setFirstName(newFirstName);
		customerEntity.setCity(newCity);

		UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
		CustomerDto customerDto =
				(CustomerDto)userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(customerEntity, customerDto);

		assertEquals(customerEntity.getFirstName(), customerDto.getFirstName());
		assertEquals(customerEntity.getCity(), customerDto.getCity());

		assertNotEquals(customerEntity.getUsername(), customerDto.getUsername());
		assertNotEquals(customerEntity.getPassword(), customerDto.getPassword());
		assertNotEquals(customerEntity.getAddressLine1(), customerDto.getAddressLine1());
		assertNotEquals(customerEntity.getPhoneNumber(), customerDto.getPhoneNumber());

	}

	@Test
	public void testUpdateEntityWithDto(){

		UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
		CustomerDto customerDto =
				(CustomerDto)userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);

		String newFirstName = "newFirstName";
		String newCity = "newCity";

		customerDto.setFirstName(newFirstName);
		customerDto.setCity(newCity);

		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		Customer customerEntity =
				(Customer)userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.map(customerDto, customerEntity);
		assertEquals(customerDto.getFirstName(), customerEntity.getFirstName());
		assertEquals(customerDto.getCity(), customerEntity.getCity());

	}

	@Test
	public void testUpdateEntityWithDto_doNotUpdateNullFields(){

		UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
		CustomerDto customerDto =
				(CustomerDto)userDtoFactory.createPartialUserDto(RoleType.ROLE_CUSTOMER);

		String newFirstName = "newFirstName";
		String newCity = "newCity";

		customerDto.setFirstName(newFirstName);
		customerDto.setCity(newCity);

		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		Customer customerEntity =
				(Customer)userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(customerDto, customerEntity);

		assertEquals(customerDto.getFirstName(), customerEntity.getFirstName());
		assertEquals(customerDto.getCity(), customerEntity.getCity());

		assertNotEquals(customerDto.getUsername(), customerEntity.getUsername());
		assertNotEquals(customerDto.getPassword(), customerEntity.getPassword());
		assertNotEquals(customerDto.getAddressLine1(), customerEntity.getAddressLine1());
		assertNotEquals(customerDto.getPhoneNumber(), customerEntity.getPhoneNumber());

	}

}
