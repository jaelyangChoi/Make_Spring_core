package spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;
import spring.core.member.MemberRepository;
import spring.core.member.MemberServiceImpl;
import spring.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {


    @Test
    void ConfigutationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = orderService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();

        System.out.println("memberRepository  = " + memberRepository);
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    void ConfigutationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        //AppConfig$$EnhancerBySpringCGLIB : AppConfig를 상속받은 조작된 다른 클래스를 빈으로 등록
        /* AppConfig@CGLIB 예상 코드
         @Bean
         public MemberRepository memberRepository() {
             if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
              return 스프링 컨테이너에서 찾아서 반환;
             } else { //스프링 컨테이너에 없으면
              기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
              return 반환
             }
        }
        * */
    }
}
