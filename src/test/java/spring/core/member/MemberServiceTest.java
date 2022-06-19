package spring.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    private MemberService memberService = new MemberServiceImpl();

    @Test
    public void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.BASIC);
        memberService.join(member);

        //when
        Member foundMember = memberService.findMember(member.getId());

        //then
        Assertions.assertThat(member).isEqualTo(foundMember);
    }
}
