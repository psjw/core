package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }


    //    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());
//    }
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }
    @Bean
    public MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), discountPolicy());
    }
    //FixDiscountPolicy -> RateDiscountPolicy로 변경
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
