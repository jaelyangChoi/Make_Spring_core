package spring.core.scan.filter;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //클래스 레벨에 붙는다.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
