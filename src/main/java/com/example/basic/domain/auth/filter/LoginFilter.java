package com.example.basic.domain.auth.filter;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.servlet.*;

import java.io.IOException;

public class LoginFilter implements Filter {

  private ReqResHandler reqResHandler;

  public LoginFilter(ReqResHandler reqResHandler) {
    this.reqResHandler = reqResHandler;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    Member loginMember = reqResHandler.getLoginMember();

    if (loginMember == null) {
      throw new RuntimeException("로그인이 필요한 기능입니다.");
    }

    filterChain.doFilter(servletRequest, servletResponse);

  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
