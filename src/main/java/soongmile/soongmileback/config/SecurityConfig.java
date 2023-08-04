package soongmile.soongmileback.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import soongmile.soongmileback.utils.JwtAuthenticationFilter;
import soongmile.soongmileback.utils.JwtTokenProvider;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/user/join", "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user/**").authenticated()
                .antMatchers("/test").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우에 씀
                .and()
                /*.formLogin()
                .loginPage("/user/login")           // 로그인 페이지의 URL
                .defaultSuccessUrl("/")         // 로그인 성공시에 이동하는 디폴트 페이지는 루트 URL
                .usernameParameter("email")
                .and()*/
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
        /*
        http
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/**")))
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .authorizeRequests()
                        .antMatchers("/user/question/write").authenticated() // 로그인한 사용자만 질문글 작성 가능
                        .anyRequest().permitAll() // 나머지 요청은 모두 허용
                        .and()
                .formLogin((formLogin) -> formLogin         // .formLogin -> 스프링 시큐리티의 로그인 설정을 담당하는 부분
                        .loginPage("/user/login")           // 로그인 페이지의 URL
                        .defaultSuccessUrl("/")         // 로그인 성공시에 이동하는 디폴트 페이지는 루트 URL
                        .usernameParameter("email"))
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));
        return http.build();*/
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
