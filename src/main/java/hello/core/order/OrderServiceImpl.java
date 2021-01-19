package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //Ctrl + F12 확인 가능(자동으로 생성자 생성)
public class OrderServiceImpl implements OrderService{
//    private final MemberRepository memberRepository=new MemoryMemberRepository();
    //final은 생성자로 반드시 들어가야함
//    @Autowired
    private final MemberRepository memberRepository; //필드에 Autowired 주입은 테스트 하기 어려움 -> set메소드로 주입
    //인터페이스에도 의존하고 구현체에도 의존 -> DIP 위반 -> 구체클래스를 제거 하여 인터페이스에만 의존
    //소스 갱신 -> OCP 위반
//   private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy=new RateDiscountPolicy();


//    @Autowired
    private final DiscountPolicy discountPolicy;

    //@Autowired(required = false)//required = false 선택적인 의존관계
/*    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }
    //@Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

    
    //Autowired 매칭
    // 1. 타입 매칭
    // 2. 타입매칭의 결과가 2개이상일 떄 필드명, 파라미터 명으로 빈이름 매칭
    // @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
    //@Qualifier("mainDiscountPolicy") 를 만약 못 찾으면 , mainDiscountPolicy라는 이름의 스프링 빈을 추가로 찾는다.
//    public OrderServiceImpl(MemberRepository memberRepository,@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        //this.discountPolicy = rateDiscountPolicy;
    }

    //@Primary 보다는 @Qualifier가 우선순위가 높다.
    
    
/*
    @Autowired//일반 메서드 주입
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
