package com.waldronprojects.bookstore.config;

import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
 
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
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
 
    @Bean
    public RoleDao roleDao() {
        return Mockito.mock(RoleDao.class);
    }
}