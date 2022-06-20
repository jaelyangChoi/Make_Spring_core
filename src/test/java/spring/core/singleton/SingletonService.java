package spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;

/**
 * private 생성자를 사용해서 외부에서 new 키워드를 사용하지 못하게 막아야 한다.
 * 구체클래스.getInstance, private 생성자로 자식 생성 힘듦, 내부 속성 변경 힘듦 => 유연성이 떨어짐.
 */
public class SingletonService {
    //자바가 뜰 때 자기자신을 생성해서 static 영역에 둠.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }
    public void logic(){
        System.out.println("instance = " + instance);
    }
}