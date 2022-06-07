package org.zerock.mreview.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER")
                .antMatchers("/movie/modify").hasRole("USER")

                .and()
                                .formLogin()
                                        .loginPage("/user/loginUser")
                                                .defaultSuccessUrl("/movie/list")
                                                        .and()
                                                                .logout()
                                                                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logoutUser"))
                                                                                .logoutSuccessUrl("/movie/list")
                                                                                        .invalidateHttpSession(true);

        //인가 인증에 문제시 로그인 화면
        http.formLogin();
        http.csrf().disable(); //CSRF 토큰 발행하지 않게 설정

        http.oauth2Login();
        // http.logout();
    }
}
