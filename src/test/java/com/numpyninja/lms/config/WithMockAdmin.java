package com.numpyninja.lms.config;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username="sucheta@gmail.com", password = "ikkd^a", roles="ADMIN")
public @interface WithMockAdmin { }
