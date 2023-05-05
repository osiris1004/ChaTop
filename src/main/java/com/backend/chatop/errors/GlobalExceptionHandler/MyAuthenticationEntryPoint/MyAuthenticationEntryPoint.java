package com.backend.chatop.errors.GlobalExceptionHandler.MyAuthenticationEntryPoint;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MyAuthenticationEntryPoint  implements AuthenticationEntryPoint{
    @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    // 401
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
  }

  @ExceptionHandler (AccessDeniedException.class)
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException {
    // 403
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
  }
}
