package spring.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.core.AppConfig;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    public void CreateOrder() {
        Member member = new Member(1L, "MemberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(member.getId(), 10000, "ItemA");

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
