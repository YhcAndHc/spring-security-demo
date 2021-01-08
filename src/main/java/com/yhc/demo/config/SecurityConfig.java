package com.yhc.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yhc.demo.filter.JwtTokenAuthenticationFilter;
import com.yhc.demo.security.LoginAuthenticationFailureHandler;
import com.yhc.demo.security.LoginAuthenticationSuccessHandler;
import com.yhc.demo.security.MyAccessDecisionManager;
import com.yhc.demo.security.MyFilterInvocationSecurityMetadataSource;
import com.yhc.demo.security.RestAuthenticationFailureHandler;

/**
 * SecurityConfig配置
 * 
 * @author yhc
 * @date 2020-11-23
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userService;
	@Autowired
	private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

	@Autowired
	MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
	@Autowired
	MyAccessDecisionManager myAccessDecisionManager;
	@Autowired
	LoginAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Autowired
	LoginAuthenticationFailureHandler myAuthenticationFailureHandler;

	@Autowired
	JwtTokenAuthenticationFilter jwtAuthenticationTokenFilter;

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// @formatter:off
        httpSecurity
        	// 权限校验
        	.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
		            @Override
		            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
		                o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
		                o.setAccessDecisionManager(myAccessDecisionManager);
		                return o;
		            }
		    	})
        
        	// 一些默认可以访问的接口
        	.and()
        	.authorizeRequests().antMatchers(
    				"/static/**",
    				"/favicon.ico","/v2/api-docs/**",
    				"/**/*.css", "/**/*.js",
    				"/error",  "/register","/captcha.jpg",
    				"/health").permitAll()
        
	        .and()
	        // 跨域请求会先进行一次options请求
	        .authorizeRequests()
	        .antMatchers(HttpMethod.OPTIONS)
	        .permitAll()
	        .anyRequest().authenticated()
	            
            // 登录设置
            .and()
	            .formLogin()
	            .loginPage("/login")
	            .loginProcessingUrl("/login.do")
	            .usernameParameter("username")
	            .passwordParameter("userpwd")
	            .successHandler(myAuthenticationSuccessHandler)
	            .failureHandler(myAuthenticationFailureHandler)
	            .permitAll()
            // 注销设置
            .and()
            	.logout().logoutSuccessUrl("/login?#/logout")
            	
            ;
        
        // 因我们使用jwt进行认证，所以此处不需要session，此设置必须保存，不然security默认可以用cookie进行访问
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
           
        // 使用jwt，可忽略csrf
        httpSecurity.csrf().disable();  
        
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        
        // 添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restAuthenticationFailureHandler)
                .authenticationEntryPoint(restAuthenticationFailureHandler);
        
        // @formatter:on
	}

	// 使用自定义的登录接口，所以此处无须设置登录校验
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
