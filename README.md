# Spring mvc 프레임워크
### 동작 원리와 구성요소

![mvc](https://github.com/min-zi/mvc-practice/assets/51107988/f599cb0d-2514-45b8-8690-02bdc406cd7b)

**1.** DispatcherServlet 가 브라우저로부터 요청을 받는다. (FrontController 패턴)

<br/>

**2.** DispatcherServlet 은 요청된 URL을 HandlerMapping 객체에 넘기고, 해당 요청을 처리하기 위해 적합한 Controller 메소드(핸들러)를 찾아서 연결한다.

<br/>

**3.** DispatcherServlet 이 HandlerAdapter 에게 요청을 보낸다.

<br/>

**4.** HandlerAdapter 는 Controller 메소드 중 요청에 맞는 메소드를 실행하고 ModelAndView 객체 타입을 리턴한다.

<br/>

**5.** Controller 객체는 비즈니스 로직을 처리하고, 그 결과를 바탕으로 뷰(ex. JSP)에 전달할 객체를 Model 객체에 저장한다. DispatcherServlet 에게 view name 을 리턴한다.

<br/>

**6.** DispatcherServlet 은 view name 을 View Resolver 에게 전달하여 Controller 의 실행 결과를 보여줄 View 객체를 얻는다.
   View 도 Controller 처럼 검색 객체와 실행 객체를 따로 둔다.
   
<br/>

**7.** DispatcherServlet 은 View 객체에 화면 표시를 의뢰한다.

<br/>

**8.** 마지막으로 View 객체는 해당하는 View (ex. JSP, Thymeleaf)를 호출하며, Model 객체에서 화면 표시에 필요한 객체를 가져와 클라이언트에게 화면 표시를 처리한다.

<br/>

