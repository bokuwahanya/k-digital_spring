package pnu.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration//이클래스가 설정 클래스라고 정의
@EnableWebSecurity// 스프링 시큐리티 적용에 필요한 필터 객체 자동생성
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//접근권한 설정
		http.authorizeHttpRequests(security->security
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		
		http.csrf(cf->cf.disable());//csrf보호 비활성화
		http.formLogin(form->
						form.loginPage("/login")
						.defaultSuccessUrl("/loginSuccess",true)
						); //스프링부트가 제공해주는 로그인을 사용하겠단 설정
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout->logout
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login"));
		
		return http.build();
	}
	
	@Bean
     UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("manager")
                .password(passwordEncoder().encode("abcd"))
                .roles("MANAGER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("abcd"))
                .roles("ADMIN")
                .build());
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    

}
//SecurityFilterChain 객체를 생성해서 Bean으로 등록하면 기본 로그인 화면이 나타나지 않는다. 기본 로그인 화면을 사용하거나
//사용자가 작성한 로그인 화면을 사용하겠다는 설정을 해야 한다
