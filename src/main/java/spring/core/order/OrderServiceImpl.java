package spring.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.core.discount.DiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService {
    /*DIP를 지켰으나 컴파일 에러. 의존성을 주입해주는 무언가가 필요*/
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    /*DIP 위반
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();*/

    /*불변,필수*/
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("생성자가 하나면 autowired를 붙여준다");
        System.out.println("생성자 의존관계 주입일 경우, 빈 생성 단계에서 autowired가 일어날 수밖에 없다.");
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Autowired(required = false) /*선택,변경*/
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("수정자 주입일 경우, 2단계 빈 조립 단계에서 의존성 주입이 일어난다");
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("수정자 주입일 경우, 2단계 빈 조립 단계에서 의존성 주입이 일어난다");
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, int itemPrice, String itemName) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
