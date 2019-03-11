package org.akj.springboot.authorization.config;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthorizationServerConfigurationTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		String pass = "admin";
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		String hashPass = bcryptPasswordEncoder.encode(pass);
		System.out.println(hashPass);

		boolean f = bcryptPasswordEncoder.matches("admin", hashPass);
		Assert.assertTrue(f);
	}

}
