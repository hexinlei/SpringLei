package com.ant.ranger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController

public class AntRangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AntRangerApplication.class, args);
	}

	@RequestMapping("/hello")
	public String home(){
		return "hello,world";
	}

	@Configuration
	@EnableWebSecurity
	protected static class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter{
		@Autowired
		private ClientDetailsService clientDetailsService;

		@Autowired
		public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
					.withUser("bill").password("abc123").roles("ADMIN").and()
					.withUser("bob").password("abc123").roles("USER");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.csrf().disable()
					.authorizeRequests()
					.antMatchers("/oauth/token").permitAll()
					.and()
					.authorizeRequests()
					.antMatchers("/ajax/**").authenticated()
					.and()
					.antMatcher("/hello").authorizeRequests().anyRequest().permitAll();

		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}


		@Bean
		public TokenStore tokenStore() {
			return new InMemoryTokenStore();
		}

		@Bean
		@Autowired
		public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
			TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
			handler.setTokenStore(tokenStore);
			handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
			handler.setClientDetailsService(clientDetailsService);
			return handler;
		}

		@Bean
		@Autowired
		public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
			TokenApprovalStore store = new TokenApprovalStore();
			store.setTokenStore(tokenStore);
			return store;
		}
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Bean
		public JwtAccessTokenConverter accessTokenConverter(){
			return new JwtAccessTokenConverter();
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//			security.allowFormAuthenticationForClients();
			security.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')").checkTokenAccess(
					"hasAuthority('ROLE_TRUSTED_CLIENT')");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager).accessTokenConverter(accessTokenConverter());
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory()
					.withClient("my-trusted-client")
					.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
					.scopes("read", "write", "trust")
					.accessTokenValiditySeconds(60)
					.refreshTokenValiditySeconds(160)
					.and()
					.withClient("my-client-with-registered-redirect")
					.authorizedGrantTypes("authorization_code")
					.authorities("ROLE_CLIENT")
					.scopes("read", "trust")
					.redirectUris("http://anywhere?key=value")
					.and()
					.withClient("ant")
					.authorizedGrantTypes("client_credentials", "password")
					.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
					.scopes("read", "write")
					.secret("ranger");
		}

	}
}
