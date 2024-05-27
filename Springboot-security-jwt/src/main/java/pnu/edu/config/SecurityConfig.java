package pnu.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import pnu.edu.config.filter.JWTAuthenticationFilter;
import pnu.edu.config.filter.JWTAuthorizationFIlter;
import pnu.edu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    
    @Autowired
    private MemberRepository memberRepository;

    
    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/member/**").authenticated()
                .requestMatchers("/manager/**").hasRole("MANAGER")
                .anyRequest().permitAll());

        http.formLogin(formLogin -> formLogin.disable()); // form 형식 로그인 비활성화
        http.httpBasic(basic -> basic.disable()); // basic 인증 방식 비활성화

        // 세션을 유지하지 않도록 설정
        http.sessionManagement(ssm -> ssm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // JWTAuthenticationFilter 추가
        http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
        
        //스프링 시큐리티가 등록한 필터들 중에서 authorizaitonfilter 앞에 앞에서 작성한 필터를 삽입한다.
        http.addFilterBefore(new JWTAuthorizationFIlter(memberRepository), AuthorizationFilter.class);

        return http.build();
    }
}
