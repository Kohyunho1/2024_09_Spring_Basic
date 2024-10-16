package com.example.basic.domain.auth.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {

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
    registrationBean.setFilter(new AdminFilter()); // AdminFilter 등록하겠다.
    registrationBean.addUrlPatterns("/admin/*"); // /admin/으로 시작하는 url

    return registrationBean;
  }
}