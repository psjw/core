package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct; //javax -> java에서 지원
import javax.annotation.PreDestroy;

public class NetworkClient
// implements InitializingBean, DisposableBean
{
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 , url= "+url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect : "+url);
    }

    public void call(String message){
        System.out.println("call : "+url+" message = "+message);
    }
    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close "+url);
    }


    //의존관계 주입 종료후 호출
    @PostConstruct //외부라이브러리 적용 안됨 -> @Bean의 기능 사용
    public void init() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    //종료시 호출
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }

/*    //의존관계 주입 종료후 호출
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    //종료시 호출
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }*/
}
