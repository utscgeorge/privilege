package com.utstar.springsecurityDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许方法上的@PreAuthorize注解使用
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@RequestMapping(value = "/")
	public String home(){
		return "hello home";
	}

	@RequestMapping(value = "/hello")
	public String hello(){
		return "hello world";
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")//方法请求前校验
	@RequestMapping(value = "/getAuth")
	public String getAuth(){
		return "hello spring security";
	}

	/**
	 * 参数中id小于10,username和登陆用户的username相等,登陆用户的用户名为abc
	 * @param id
	 * @param username
	 * @param user
	 * @return
	 */
	@PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")//方法请求前校验
	@PostAuthorize("returnObject%2==0")//方法请求完成后校验,返回值为偶数
	@RequestMapping(value = "/test")
	public Integer test(Integer id, String username, User user){
		return id;
	}


	/**
	 * PreFilter:对集合类型的参数过滤
	 * PostFilter:对集合类型的返回值过滤
	 * @param ids
	 * @return
	 */
	@PreFilter("filterObject%2==0")
	@PostFilter("filterObject%4==0")
	@RequestMapping(value = "/test2")
	public List<Integer> test2(List<Integer> ids){
		return ids;
	}


}
