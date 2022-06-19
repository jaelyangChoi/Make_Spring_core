package spring.core;

import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

/**
 * 관심사의 분리 - 구현 객체를 생성하고 연결하는 책임을 가지는 설정 클래스
 * 1. 이로써 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리됨
 * 2. 설정 파일을 보면 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 되어 있는지 빠르게 파악할 수 있다.
 */
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
    }
}
