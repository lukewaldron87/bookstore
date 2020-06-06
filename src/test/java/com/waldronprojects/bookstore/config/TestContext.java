package com.waldronprojects.bookstore.config;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.service.CustomerService;
import com.waldronprojects.bookstore.service.UserService;
 
@Configuration
public class TestContext {
 
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
 
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
 
        return messageSource;
    }
 
    @Bean
    public CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }
 
    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
 
    @Bean
    public RoleDao roleDao() {
        return Mockito.mock(RoleDao.class);
    }
}