package com.web.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "pixeltrice";
	   private String clientSecret = "pixeltrice-secret-key";
	   private String privateKey = "-----BEGIN PRIVATE KEY-----\r\n"
	   		+ "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJWiS0SZuCxXYI\r\n"
	   		+ "3GcpqHtFDy82rzqeVGnhSMHOEw1be8FOiDYyVmSbr7sN5fiQvrT1X48NffHLb7xn\r\n"
	   		+ "57kl/jz+xVXPUcIRtWYN1jyp1LXXr3D0OZ4QBYc8pQX0R88SJHbBHChrVcvJ8SG9\r\n"
	   		+ "u0YF/CGhh3ZBGhPaNuXChX5mHgrgP3+kb+chc4Xd9QckSxpBWFxeEkPcJeEw7XX7\r\n"
	   		+ "TpS/tiY4HoWrlGHqsQ4xh/dPbjw7CpHoKKjKmai72UhPnRPh1nop+pqad+xP531i\r\n"
	   		+ "+k1yLx5FXN21qm0VLlyXMMhwmCV3K0xTf7T6KkofJoOijM1OSb/KxAfveOe41T2w\r\n"
	   		+ "U2O7wYTzAgMBAAECggEANbIBWcbLXttTt9c296gDf44mFBo0sqZmOfrSHCd+h2gA\r\n"
	   		+ "S/YNJe1eFRqO8ozidY816L2cA4gTpCHfB4KHIHelQnfTg18VNu2+EnCQauXbvHH/\r\n"
	   		+ "2FFJMpKW1vhXFybTNM5ekO72ZHFT0D2qttMrQEtVmKLsST4wDyBWs0IwTAE0+0na\r\n"
	   		+ "nuxLri2HtvuSF6MY2PBYiV9LtGkCQMvcwvzOw2LgWwk7VhqMqEZGr7sw2iuJ3sXj\r\n"
	   		+ "HOgkpFzApHZdtgxqkDmYEVbCJnrqE1xBzoTtzvbR/XnD0T5kUleVPJ0jqQL5ZYXZ\r\n"
	   		+ "SyMQx7E2WDjV9Biu/RWbC7f4SdTv6ZdIdYYr3Fb/OQKBgQDnD6Gda0F1ceYoeDH4\r\n"
	   		+ "dS1GVthVZi9Ht2X8lZsq60o2wK6gzeJIsC1SuYaCzbPEtQnkIhHNnq1TGY/Ztfdi\r\n"
	   		+ "TEqaiDC7t9PlDqWnEcmqjGES+s6F1RPWEFG2I7AbUk5a/y3FsXMAa6HuZG+/u0uZ\r\n"
	   		+ "prwsF1T1b2D/V3rfw4NOzygWVwKBgQDfFaJ3q20j8Kzf8Y4s+SngwhsNgPuOLyGX\r\n"
	   		+ "JjG16GCSjoNxgm1B3Tz74AlMPA6/945ebfbXtRChuenledDO05Pd8s7m/yi6vsx9\r\n"
	   		+ "s3sSXoIjxy7sTbYiD12lJQdb5NhHaEjGQ2tgGdkj+S8q86bt/ivrAweolbmUWkZ1\r\n"
	   		+ "s4fVQd3MxQKBgHsa++toKq8NKSgkqdMLczzHpmkRkV0vXFoPx2U9iQPU8vVf3BEI\r\n"
	   		+ "NI2fpQfp2r6SChwO/tdDlGry8o53Ab/Qj+r3ZqeNEIs4m4+AzaZdNpcrI39wgCoL\r\n"
	   		+ "9c0KBJj56B9ZV6S9n1z4+4SM5HENtADAMf93yt8dIVvJ2wB/JWWGv3/vAoGAfi5R\r\n"
	   		+ "iBi5keOXFNAfmAaTDmMcRCYZo1G7UTA0W+0FesALTw/z1pZZ4SnWeH1b5m8S4wyV\r\n"
	   		+ "22q57pAm4TpmxiXcdGTbbMMUeZRkPBFh4oD8YVpPiy9lfpxsXnW0VOD7CGDPEP2f\r\n"
	   		+ "ZWsZbASNDmFkoSbsFnDPSybtIiFxyy9x/EjWOPUCgYEA0Zcz+159ZN7SntqNtxEE\r\n"
	   		+ "mRd6oWD5fBF6sS2cv7oiM4eNgWYlsPYx7oK/VU0k1v7AIak9W64qazFQWJzT7tan\r\n"
	   		+ "0YCaww3msuRaN1QLS15QJu08PDHf8CuCbHoGjZfBqGTDqaU0Dxth44hORAfcTZ04\r\n"
	   		+ "EHoN3HH5/uGVN1dZ/zDHR3E=\r\n"
	   		+ "-----END PRIVATE KEY-----";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n"
	   		+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtaWQQl/2Pp1XpLW4SQmB\r\n"
	   		+ "supoOdU1KsHeP3+DUxh5zbojmF57Yq2Y7o7zszJrxerV7ntAEiQsqRKNcR1hmCzK\r\n"
	   		+ "1PVGyqRiqVGKepA0EjGj8kDN1UL7H6+mR0Df9o7mzGpF2dE4O05Oe8wZDQ2szAa1\r\n"
	   		+ "NcaZ779vjb7ePoPOBJCboDRwkIwFFv/32CMDz2IWqKNKY+3etP+OQRd2zszbeF/n\r\n"
	   		+ "LozcKlcYRimJ+bf0IbwxcXVRQKxWV/HaHjixVoCQJwHdix9rN8Z/n50kCmNgEupI\r\n"
	   		+ "zOg0OSveYmx9bGGW44GEqxgWa1MNlJEfzxtMoajWJ5fYudXMuidWuG4J+oUV/6Z0\r\n"
	   		+ "EwIDAQAB\r\n"
	   		+ "-----END PUBLIC KEY-----";
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}
