package com.proejct.project.config;

import com.proejct.project.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@EnableWebSecurity Spring Security 설정할 클래스라고 정의
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체
        return new BCryptPasswordEncoder();
    }

    @Override
    //WebSecurity는 FilterChainProxy를 생성하는 필터
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    //HttpSecurity를 통해 HTTP 요청에 대한 웹 기반 보안을 구성
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //먼저 정해진 속성은 불변, 이후 정해진 속성은 무시
                //ex) 아래는 모든 페이지에 접근 가능
                //.antMatchers("/**").permitAll();
                //.antMatchers("/mypage").authenticated()
                //로그인 페이지 누구나 접근 가능
                //.antMatchers("/member/**").permitAll()
                //인증 되어야지만 이용 가능
                //.anyRequest().authenticated()
                .antMatchers("/**").permitAll()
                .and() // 로그인 설정
                .formLogin()
                //loginPage 사용 안할시, 기본으로 제공하는 로긍니 폼을 사용
                //커스텀 로그인 form의 action의 경로와 loginPage의 파라미터 경로가 일치해야 인증 처리 가능
                .loginPage("/member/login")
                .defaultSuccessUrl("/member/login/result")
                .permitAll()
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/login"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)    //http세션 초기화
                //.deleteCookies("KEY명") 특정 쿠키를 제거
                .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/login");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
