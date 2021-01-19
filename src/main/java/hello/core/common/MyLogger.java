package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value="request") //HTTP 요청 하나당 하나씩 생성 되고 요청이 끝나면 소멸
//requestScope인 경우 Http 요청이 없으므로 Scope 'request' is not active for the current thread 에러 발생
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
//proxyMode = ScopedProxyMode.TARGET_CLASS -> 대상이 Class / Inteface -> INTERFACES
//hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$51c02294 -> CGLIB 라이브러리로 상속받은 가짜 프록시 객체를 만들어 주입
public class MyLogger {
    private String uuid;
    private String requestURL;


    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"]"+message);

    }

    @PostConstruct
    public void init(){
        uuid=UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create: "+this);
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close: "+this);
    }
}
