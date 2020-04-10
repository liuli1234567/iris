package com.zhongke.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * @ClassName WebSecurityConfg
 * @Description
 * @Author liuli
 * @Date 2020/4/10 16:15
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfg extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
//此处开启不让检测‘/’符号
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
        //super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/memberCard/find").permitAll()
                .antMatchers("/505").permitAll()
                .antMatchers("/403").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/admin/index").hasRole("ADMIN")//指定权限为ADMIN才能访问
                .antMatchers("/person").hasAnyRole("ADMIN","USER")
                .antMatchers("/member/findById/{id}").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()//使用表单认证方式
                .loginProcessingUrl("/authentication/form")//配置默认登录入口
                .successHandler(myLoginSuccessHandler)
                .failureHandler(myLoginFailureHandler)
                .and()
                .csrf().disable();
    }

    /**
     * 自定义认证策略
     *
     * @return
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder().encode("123456");

        logger.info("加密后的密码:" + password);

//        auth.inMemoryAuthentication().withUser("admin").password(password)
//                .roles("ADMIN").and();

        auth.inMemoryAuthentication().withUser("user").password(password)
                .roles("USER").and();

    }

    @Autowired(required = false)
    private AuthenticationSuccessHandler myLoginSuccessHandler; //认证成功结果处理器

    @Autowired(required = false)
    private AuthenticationFailureHandler myLoginFailureHandler; //认证失败结果处理器

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
