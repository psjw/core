package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;


public class OrderServiceImpl implements OrderService{
//    private final MemberRepository memberRepository=new MemoryMemberRepository();
    //final은 생성자로 반드시 들어가야함
    private final MemberRepository memberRepository;
    //인터페이스에도 의존하고 구현체에도 의존 -> DIP 위반 -> 구체클래스를 제거 하여 인터페이스에만 의존
    //소스 갱신 -> OCP 위반
//   private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy=new RateDiscountPolicy();


    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
