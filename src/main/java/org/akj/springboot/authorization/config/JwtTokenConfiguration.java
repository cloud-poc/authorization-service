package org.akj.springboot.authorization.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class JwtTokenConfiguration {

	@Value("${security.oauth2.jwt.key-pair.store-pass}")
	private String keyStorePass;

	@Value("${security.oauth2.jwt.key-pair.alias}")
	private String keyPairAlias;

	@Value("${security.oauth2.jwt.key-pair.file-path}")
	private String keyStoreFilePath;

	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	/**
	 * token generation: signature
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();

		KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(keyStoreFilePath), keyStorePass.toCharArray())
				.getKeyPair(keyPairAlias);
		accessTokenConverter.setKeyPair(keyPair);
		return accessTokenConverter;
	}
}