package org.example.mvc;

import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    boolean supports(Object handler); // 전달 된 handler 를 지원하는 adapter 인가

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}

