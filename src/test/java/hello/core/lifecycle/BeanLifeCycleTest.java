package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest
{
    @Test
    public void lifeCycleTest(){
        //close를 사용하기 위해
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    //스프링 빈의 이벤트 라이프 사이클
    // 스프링 컨테이너 생성 -> 스프링 빈생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
    // 초기화 콜백 : 빈이 생성된후, 빈의 의존관곅 주입이 완료된 후 호출
    // 소멸전 콜백 : 빈이 소멸되기 직전에 호출
    
    //객체생성과 초기화를 분리. -> 단일 책임원칙
    // 생명주기 콜백 3가지
    //1. 인터페이스 -> 스프링 전용인터페이스로 스프링에 의존적임 / 외부라이브러리에는 적용불가
    //2. 설정정보에 초기화
    //3. @PostContruct, @PreDestory 애노테이션 지원
    @Configuration
    static class LifeCycleConfig{
//        @Bean(initMethod = "init",destroyMethod = "close")
        //destroyMethod default 값 (inffered) -> 라이브러리 대부분 close, shutdown -> (inffered)가 종료 메서드를 추론해서 자동으로 추론해서 호출
        // 추론 기능 사용 안함 -> destoryMethod =""
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient=new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
