package com.example.callbus.Grobal.security;

import com.example.callbus.Grobal.jwt.JwtAuthFilter;
import com.example.callbus.Grobal.jwt.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration // 설정파일을 만들기 위한 어노테이션 or Bean을 등록하기 위한 어노테이션
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@RequiredArgsConstructor // 생성자 주입 어노테이션
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/configuration/ui",
                        "/configuration/security",
                        "/webjars/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //        http.cors().configurationSource(corsConfigurationSource());

//		// CORS
//		http.cors().configurationSource(request -> {
//			var cors = new CorsConfiguration();
//			cors.setAllowedOriginPatterns(List.of("*"));
//			cors.setAllowedMethods(List.of("*"));
//			cors.setAllowedHeaders(List.of("*"));
//			cors.addExposedHeader("Authorization");
//			cors.addExposedHeader("Refresh_Token");
//			cors.setAllowCredentials(true);
//			return cors;
//		});

        http.csrf().disable() // jwt토큰을 사용하기 때문에 csrf 를 풀어도됨
                .exceptionHandling();

        // jwt사용시 필요없음 STATELESS는 사용자 정보를 가지고 있지 않음
        // STATEFUL 은 사용자 정보를 DB에 저장을함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/board").authenticated()
                .antMatchers(HttpMethod.GET, "/api/board").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/board").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/board").authenticated()

                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

