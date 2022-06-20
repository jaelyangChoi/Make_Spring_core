package spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스프링 빈은 싱글톤 빈이기 때문에, 필드가 공유 필드가 된다.
 * 공유 필드는 진짜 조심해야 된다! 스프링 빈은 항상 무상태(stateless)로 설계하자.
 */
public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA : A사용자 주문 금액 조회
        int priceA =statefulService1.getPrice();
        //ThreadB : B사용자 주문 금액 조회
        int priceB =statefulService2.getPrice();

        System.out.println("priceA = " + priceA);
        System.out.println("priceB = " + priceB);
        Assertions.assertThat(priceA).isNotEqualTo(10000);
    }

    @Test
    @DisplayName("지역변수, 파라미터를 사용해 공유 필드 제거")
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        //ThreadA : A사용자 10000원 주문
        int priceA =statelessService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int priceB =statelessService1.order("userB", 20000);

        System.out.println("priceA = " + priceA);
        System.out.println("priceB = " + priceB);
        Assertions.assertThat(priceA).isEqualTo(10000);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}
