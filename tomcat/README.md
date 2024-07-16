# [Apache Tomcat](https://github.com/apache/tomcat) 을 직접 구현

## Tomcat 소스 기반 보일러 코드

### Tomcat

- [Processor](https://github.com/apache/tomcat/blob/main/java/org/apache/coyote/Processor.java)
    - HTTP 요청에 대한 응답을 반환하기 위한 처리를 정의한 인터페이스이다.
- [Http11Processor](https://github.com/apache/tomcat/blob/main/java/org/apache/coyote/http11/Http11Processor.java)
    - HTTP1.1 프로토콜을 지원하는 요청에 대한 응답 처리를 실제로 구현한 구현체이다.
- [Connector](https://github.com/apache/tomcat/blob/main/java/org/apache/catalina/connector/Connector.java)
    - 소켓의 생성 및 연결을 관리하고, 소켓과 Processor 를 연결하는 연결자이다.
- [Tomcat](https://github.com/apache/tomcat/blob/main/java/org/apache/catalina/startup/Tomcat.java)
    - tomcat 모듈의 Entry Point 이다. Tomcat.start 메서드를 통해 톰캣 서버를 실행시킬 수 있다.

### [Servlet](https://jakarta.ee/specifications/servlet/6.1/jakarta-servlet-spec-6.1)

Jakarta EE 의 Servlet Request 와 Servlet Response 를 지킬 수 있도록 노력해야한다.  
가령 HttpRequest 는 getSession() 기능을 제공해야한다.

## 구현사항

[RFC2616](https://datatracker.ietf.org/doc/html/rfc2616#section-6)
와 [RFC7230](https://datatracker.ietf.org/doc/html/rfc7230#section-3.1.1) 문서를 기반으로 HTTP 1.1 프로토콜을 처리할 수 있는 웹서버를 구현한다.

- [X] HttpRequest 를 구현한다.
    - [X] RequestLine 을 파싱한다.
        - [X] HTTP Method 를 인식한다.
        - [X] URI 형태의 Request Target 과 QueryParams 를 파싱한다.
        - [X] HTTP Protocol 을 인식한다.
    - [X] RequestHeader 를 파싱한다.
    - [X] RequestBody 를 파싱한다.
- [X] HttpResponse 를 구현한다.
    - [X] HttpHandler 를 통하여 HttpRequest 를 HttpResponse 로 반환한다.
    - [X] HttpResponse 에 StatusLine 을 통해 응답 상태를 표시한다. (200 OK)
    - [X] HttpHeader 와 HttpBody 는 Request 와 Response 에 동일한 로직이 사용된다.
    - [X] 요청한 경로의 static file(html, css, js) 을 반환할 수 있도록 한다.
- [ ] Cookie 를 구현한다.
- [ ] Session 을 구현한다.
- [ ] Cookie 와 Session 을 활용하여 간단한 로그인 및 회원가입 기능을 구현한다.
- [ ] 동시성 확장을 위한 Thread Pool 혹은 Virtual Thread 를 도입한다. 
