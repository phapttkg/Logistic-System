package team1.logistic.config;
 


import javax.sql.DataSource;
import team1.logistic.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
 
/**
 * 
 * @author An
 * 
 * */

@Configuration
@EnableWebSecurity
@EnableScheduling
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private AccountService accountservice;
 
    @Autowired
    private DataSource dataSource;
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(accountservice).passwordEncoder(passwordEncoder());
 
    }
    
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*", "http:/*")); 
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        configuration.setAllowedHeaders(Arrays.asList("content-type"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
// 		Các trang không yêu cầu login
    	http.csrf().disable();
//		http.authorizeRequests().antMatchers("/*").permitAll();
//    	http
//        .httpBasic().and()
//        .authorizeRequests()
//            .antMatchers(HttpMethod.OPTIONS,"/*").permitAll()
//            .anyRequest().authenticated()
//            .and()
//        .csrf()
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//      http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
		http.cors();

		http.authorizeRequests().antMatchers("/login", "/log_out", "/company-api/*", "/j_spring_security_check")
				.permitAll();

		// Trang /userInfo yêu cầu phải login
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/home","/account/detail.do","/account/detail",
				"/account/changepassword.do","/account/changepassword")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIVIDER') or hasRole('ROLE_DRIVER') "
						+ "or hasRole('ROLE_SHIPPER') or hasRole('ROLE_HUB_STAFF')");

		// Trang chỉ dành cho shipper
		http.authorizeRequests().antMatchers("/shipper/*").access("hasRole('ROLE_SHIPPER')");
		
		// Trang chỉ dành cho driver
		http.authorizeRequests().antMatchers("/driver/*").access("hasRole('ROLE_DRIVER')");

		// Trang chỉ dành cho divider
		http.authorizeRequests().antMatchers("/divider/*").access("hasRole('ROLE_DIVIDER')");

		// Trang chỉ dành cho hub_staff
		http.authorizeRequests().antMatchers("/hub/*").access("hasRole('ROLE_HUB_STAFF')");
        
        // Trang chỉ dành cho admin
		http.authorizeRequests().antMatchers("/account/sign-up", "/account/sign-up.do",
				"/account/edit.do","/account/edit",
				"/account/disable.do","/account/disable")
				.access("hasRole('ROLE_ADMIN')");
 
		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//
				// Submit URL của trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
			    //.loginPage("/login")//
				.defaultSuccessUrl("/home")//
				.failureUrl("/login?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password")
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/logouttttt").logoutSuccessUrl("/account/logoutSuccessful-pageeeee");

		// Cấu hình Remember Me.
		http.authorizeRequests().and() //
				.rememberMe().tokenRepository(this.persistentTokenRepository()) //
				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

	}

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
    
 
}
