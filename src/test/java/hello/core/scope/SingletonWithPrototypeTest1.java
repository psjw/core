package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);


        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1=clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2=clientBean2.logic();
//        Assertions.assertThat(count2).isEqualTo(2);
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
//        private final PrototypeBean prototypeBean; //생성시점에 주입되어 계속 같은 것을 사용
//        @Autowired
//        ApplicationContext applicationContext;

        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        //ObjectFactory를 상속 받은게 ObjectProvider -> 기능 몇개 더 추가 -> 스프링에 둘다 의존적임
        private Provider<PrototypeBean> prototypeBeanProvider;


/*        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }*/

        public int logic(){
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            PrototypeBean prototypeBean=prototypeBeanProvider.getObject(); //Dependency Lookup(DL)
            PrototypeBean prototypeBean=prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count=prototypeBean.getCount();
            return count;
        }
    }


    @Scope("singleton")
    static class ClientBean2{
        //        private final PrototypeBean prototypeBean; //생성시점에 주입되어 계속 같은 것을 사용
        @Autowired
        ApplicationContext applicationContext;

//        @Autowired
//        public ClientBean2(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count=prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;
        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init "+this);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
