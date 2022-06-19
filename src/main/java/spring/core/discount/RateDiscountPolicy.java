package spring.core.discount;

import spring.core.member.Grade;
import spring.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {
    private final int disountPercent = 10;


    @Override
    public int discount(Member member, int price) {
        return member.getGrade() == Grade.VIP ? price * disountPercent / 100 : 0;
    }
}
