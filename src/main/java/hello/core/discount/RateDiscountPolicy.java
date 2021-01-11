package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//F2 오류난 곳으로 이동
//cmd + shift + t : TestClass 생성
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent/100;
        }else{
            return 0;
        }

    }
}
