package spring.core;

import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        //MemberService memberService = new MemberServiceImpl(memberRepository);
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "member1", Grade.VIP);
        memberService.join(member);

        System.out.println("new member = " + member.getName());
        System.out.println("found member = " + memberService.findMember(1L).getName());
    }
}
