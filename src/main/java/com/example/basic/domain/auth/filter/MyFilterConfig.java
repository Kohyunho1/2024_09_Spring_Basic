package com.example.basic.domain.auth.filter;

import com.example.basic.global.reqres.ReqResHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyFilterConfig {

  private final ReqResHandler reqResHandler;

  @Bean
  public FilterRegistrationBean<TestFilter> testFilterRegistrationBean() {
    FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean<>(); // 필터 등록을 해주는 객체
    registrationBean.setFilter(new TestFilter()); // TestFilter를 등록하겠다.
    registrationBean.addUrlPatterns("/**"); // 모든 url

    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean<AdminFilter> adminFilterFilterRegistrationBean() {
    FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>(); // 필터 등록을 해주는 객체
    registrationBean.setFilter(new AdminFilter(reqResHandler)); // AdminFilter 등록하겠다.
    registrationBean.addUrlPatterns("/admin/*"); // /admin/으로 시작하는 url

    return registrationBean;
  }

  @Bean
  public FilterRegistrationBean<LoginFilter> loginFilterFilterRegistrationBean() {
    FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>(); // 필터 등록을 해주는 객체
    registrationBean.setFilter(new LoginFilter(reqResHandler));
    registrationBean.addUrlPatterns("/article/write");
    registrationBean.addUrlPatterns("/article/detail/*");
    registrationBean.addUrlPatterns("/article/update/*");
    registrationBean.addUrlPatterns("/article/delete/*");
    registrationBean.addUrlPatterns("/comment/write/*");

    return registrationBean;
  }
}