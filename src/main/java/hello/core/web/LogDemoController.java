package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    //requestScope인 경우 Http 요청이 없으므로 Scope 'request' is not active for the current thread 에러 발생
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = "+myLogger.getClass());
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        Thread.sleep(10000);
        logDemoService.logic("testId");
        return "OK";
    }

}
