package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        //MemberService memberService = new MemberServiceImpl(memberRepository);
        /*스프링으로 전환
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        
        Member member = new Member(1L, "member1", Grade.VIP);
        memberService.join(member);

        System.out.println("new member = " + member.getName());
        System.out.println("found member = " + memberService.findMember(1L).getName());
    }
}
