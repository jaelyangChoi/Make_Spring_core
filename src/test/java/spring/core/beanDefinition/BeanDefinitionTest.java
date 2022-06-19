package spring.core.beanDefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.core.AppConfig;

/**
 * 스프링은 BeanDefinition으로 빈 정보를 추상화한다.
 */

public class BeanDefinitionTest {

    //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); //팩토리 메소드로 등록
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml"); //직접 스프링 빈으로 등록

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
            }
        }
    }
}
