package spring.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.core.AppConfig;
import spring.core.order.OrderService;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    public void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(member);

        //when
        Member foundMember = memberService.findMember(member.getId());

        //then
        Assertions.assertThat(member).isEqualTo(foundMember);
    }
}
