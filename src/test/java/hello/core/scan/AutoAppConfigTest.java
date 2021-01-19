package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


//스프링 컨테이너 등록
//1. 스프링 빈을 등록 ->우선순위 생성자 주입은 객체 생성시에 주입
//2. 연관관계 자동으로 주입 ->settter 등


public class AutoAppConfigTest {
    @Test
    void basicScan(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository=bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}
