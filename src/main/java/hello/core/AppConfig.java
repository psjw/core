package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
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

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //호출순서 예측(실제 보장되지는 않음)
    //1. call AppConfig.memberService
    //2. call AppConfig.memberRepository
    //3. call AppConfig.memberRepository
    //4. call AppConfig.orderService
    //5. call AppConfig.memberRepository

    //호출순서
    //1. call AppConfig.memberService
    //2. call AppConfig.memberRepository
    //3. call AppConfig.orderService
    
    //@Configuration 없이 @Bean만 사용시 싱글톤 보장 하지 않음
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    //FixDiscountPolicy -> RateDiscountPolicy로 변경
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
