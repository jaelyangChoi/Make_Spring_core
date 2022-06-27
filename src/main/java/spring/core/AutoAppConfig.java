package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.core.member.MemoryMemberRepository;

/**
 * @ComponentScan 은 @Component 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
 * 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
 */
@Configuration
@ComponentScan(
        basePackages = "spring.core",
        basePackageClasses = AutoAppConfig.class, //package spring.core
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 빈 vs 자동 빈 충돌 시, 수동 빈 등록이 우선권을 가진다. (수동 빈이 자동 빈을 오버라이딩)
    @Bean(name = "memoryMemberRepository")
    MemoryMemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }
}
