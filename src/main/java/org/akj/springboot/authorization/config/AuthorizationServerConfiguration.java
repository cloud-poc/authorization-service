package org.akj.springboot.authorization.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthenticationManager authenticationManager;

//	@Bean("jdbcTokenStore")
//	public JdbcTokenStore tokenStore() {
//		return new JdbcTokenStore(dataSource);
//	}

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Bean("jdbcClientDetailsService")
	public JdbcClientDetailsService clientDetailsService() {
		return new JdbcClientDetailsService(dataSource);
	}

	@Bean
	public ApprovalStore approvalStore() {
		return new JdbcApprovalStore(dataSource);
	}

	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.approvalStore(approvalStore()).authorizationCodeServices(authorizationCodeServices())
//				.tokenServices(tokenServices(endpoints))
				.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter)
				.tokenStore(tokenStore);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
				.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService());
	}

	private DefaultTokenServices tokenServices(AuthorizationServerEndpointsConfigurer endpoints) {
		DefaultTokenServices services = new DefaultTokenServices();
		services.setTokenStore(tokenStore);
		services.setAuthenticationManager(authenticationManager);
		services.setSupportRefreshToken(true);
		services.setClientDetailsService(clientDetailsService());

		return services;

	}

}
