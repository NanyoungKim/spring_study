package nanyoung.nanyoungspring.repository;

import nanyoung.nanyoungspring.domain.Member;
import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.*;


//MemoryMemberRepository 에 작성한 코드가 제대로 동작하는지 테스트해보자.
//개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다.
//이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한번에 실행하기 어렵다는 단점이 있다.
//자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 끝날때마다 repository clear 해줘야 오류 발생 안함
    //테스트는 코드순으로 차례롤 실행되는게 아님


    //클래스 단위에서 테스트 실행하면 아래의 메소드들이 랜덤한 순서로 실행됨.
    //그 과정에서 문제가 발생할 수 있기에 메소드 끝날때마다 데이터(공용데이터 또는 저장소)를 클리어 해줘야 함.
    @AfterEach  //메소드 끝날때마다 콜백메소드 호출하기 위함
    public void afterEach(){
        repository.clearStore();    //레포지토리 지우기
    }


    @Test
    public void save(){

        Member member  = new Member();
        member.setName("spring");

        repository.save(member);    //저장할 때 id 셋팅됨
        Member result = repository.findById(member.getId()).get();  //findById의 반환형이 Optional임. Optional에서 get()으로 꺼낼 수 있음. (get으로 바로 꺼내는게 좋은 방법은 아니지만 테스트할 때는 해도 괜찮음)

        //검증 : new 로 생성한 member 와 디비에 저장된 member 가 같으면 save()가 잘 동작하는것임
        //방법 1) System.out.println("result = " + (result == member));

        //방법 2)
        //Assertions.assertEquals(member,result);

        //방법 3) 최근에 쓰는 편리한 방법
        assertThat(member).isEqualTo(result);


    }

    @Test
    public void findByName(){
        //spring1, spring2 이름의 회원 가입시킴
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        //회원 2명인지 확인
        assertThat(result.size()).isEqualTo(2);

    }



}
