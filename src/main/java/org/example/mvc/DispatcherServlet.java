package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping rmhm;
    private List<HandlerAdapter> handlerAdapters;
    private List<ViewResolver> viewResolvers;


    @Override
    public void init() throws ServletException {
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init(); // 톰캣이 HttpServlet 을 구동시키면서 init() 호출, 객체 rmhm 을 초기화 시켜줌

        handlerAdapters = List.of(new SimpleControllerHandlerAdapter());
        viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started."); // 1. 요청이 들어오면 DispatcherServlet 이 모든 요청을 받는 거 확인

        try {
            // 2. DispatcherServlet -> HandlerMapping - choose Handler
            Controller handler = rmhm.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI())); // 요청 된 경로를 통해 적절한 Controller 를 반환

//            String viewname = handler.handleRequest(request, response);

            // 3. DispatcherServlet -> HandlerAdapter
            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                    .filter(ha -> ha.supports(handler)) // 3-1. handler 를 수행 할 adapter 를 찾기
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));

            ModelAndView modelAndView = handlerAdapter.handle(request, response, handler); // 3-2. 선택 된 handler 를 전달하며 adapter 를 실행
                                                                                            // 5. Controller -> viewName
            // 6. DispatcherServlet -> View Resolver - resolve View
            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), request, response);
            }
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewname);
//            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
