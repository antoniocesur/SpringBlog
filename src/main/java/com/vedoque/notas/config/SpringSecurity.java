package com.vedoque.notas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/img/**").permitAll()
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers("/js/**").permitAll()
                                .requestMatchers("/files/**").permitAll()
                                .requestMatchers("/especialidadxnivel").permitAll()
                                .requestMatchers("/cursoxespecialidad").permitAll()
                                .requestMatchers("/asignaturaxcurso").permitAll()
                                .requestMatchers("/temaxasignatura").permitAll()
                                .requestMatchers("/continuar").permitAll()
                                .requestMatchers("/comprobar").permitAll()
                                .requestMatchers("/anterior").permitAll()
                                .requestMatchers("/fintelosabes").permitAll()
                                .requestMatchers("/telosabes/**").permitAll()
                                .requestMatchers("/usuarios/perfil").authenticated()
                                .requestMatchers("/crud/*/**").hasRole("PROFESOR")
                                .requestMatchers("/crud/*/new/submit").authenticated()
                                .requestMatchers("/crud/*/edit/submit").authenticated()
                                .requestMatchers("/users").hasRole("PROFESOR")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
