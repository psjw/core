package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        
        //수작업으로 실행해주어야함
        prototypeBean1.destory();
        prototypeBean2.destory();
        ac.close();

    }

    @Scope("prototype")
    static class PrototypeBean{
        //조회시 생성후 초기화실행
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        //종료시 호출안됨
        @PreDestroy
        public void destory(){
            System.out.println("SingletonBean.destory");
        }

    }
}
