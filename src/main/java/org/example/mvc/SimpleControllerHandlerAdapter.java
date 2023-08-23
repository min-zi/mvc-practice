package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // 4. Handler Adapter -> Controller
        return (handler instanceof Controller); // 4-1. 전달 된 handler 가 Controller 인터페이스를 구현한 구현체라면
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((Controller) handler).handleRequest(request, response); // 4-2. 내부적으로 Controller 를 실행
        return new ModelAndView(viewName);
    }
}
