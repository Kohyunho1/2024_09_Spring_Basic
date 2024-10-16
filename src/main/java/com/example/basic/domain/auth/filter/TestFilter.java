package com.example.basic.domain.auth.filter;

import jakarta.servlet.*;

import java.io.IOException;


public class TestFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    System.out.println("저는 필터입니다. 모든 요청은 저를 지나갑니다.");
    filterChain.doFilter(servletRequest, servletResponse); // 필터 통과
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}