package ca.sheridancollege.securityexample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private LogginAccessDeniedHandler accessDeniedHandler;

    @Bean
    public Argon2PasswordEncoder argon2PasswordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Autowired

    @Lazy
    //private BCryptPasswordEncoder passwordEncoder;
    private Argon2PasswordEncoder argon2PasswordEncoder;

    @Autowired
    private DataSource dataSource;


    @Autowired
    public void setAccessDeniedHandler(LogginAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyRole("USER", "MANAGER")
                .antMatchers("/secured/**").hasAnyRole("USER", "MANAGER")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/secured")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/logout-success")
                .permitAll()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .passwordEncoder(argon2PasswordEncoder)
                .withUser("bugs").password(argon2PasswordEncoder.encode("bunny")).roles("USER")
                .and()
                .withUser("daffy").password(argon2PasswordEncoder.encode("duck")).roles("USER", "MANAGER");
    }
}
