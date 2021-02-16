package nanyoung.nanyoungspring.service;

import nanyoung.nanyoungspring.domain.Member;
import nanyoung.nanyoungspring.repository.MemberRepository;
import nanyoung.nanyoungspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행한다. 즉 진짜 스르핑을 띄워서 테스트함.
@Transactional //Transactional 써줘서 AfterEach 필요없어짐.
    //테스트 실행할 때 트랜잭션 실행하고 테스트 쿼리 다 날린 다음, 테스트 끝나면 롤백해서 디비 데이터 안 변하게 해줌. 매 테스트 메서드 마다 동작함.
    //테스트케이스에 붙었을 때만 동작함. 서비스 같은데에 붙으면 롤백 안하고 정상적으로 데이터 변화 반영됨.
class MemberServiceIntegrationTest {

    //전에는 생성자로 DI 했는데, 테스트는 가장 끝단에서 하는거라서 그냥 Autowired로 쉽게 함.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    //Dependency Injection(DI)
//    @BeforeEach //동작하기 전에 이미 생성된 인스턴스들 넣어서 중복 안되게 해줌
//    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }

//    @AfterEach  //메소드 끝나고나서 메모리 클리어
//    public void afterEach(){
//        memberRepository.clearStore();
//    }

    @Test
    void 회원가입() {   //빌드될 때 실제 코드에 포함되지 않아서 한글로 적어도 무방
        //given : 이런 상황에서
        Member member = new Member();
        member.setName("spring");

        //when : 이걸 실행했을 때 (검증부분)
        Long saveId = memberService.join(member);

        //then : 이런 결과가 나와야해
        //저장한게 저장소에 있는게 맞아?
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test   //회원가입 시 이미 있는 회원이면 예외가 잘 터지는지 확인해야함
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        /*검증 방법 1 */
        memberService.join(member1);              //2. 이 예외가 터져야함               //1. 이 로직을 실행할 때
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        //받은 에러 메세지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");  //던져진 메세지까지 검증 가능




        /*검증 방법 2
        try{
            memberService.join(member2);          //이름이 같은 회원 2명 넣었는데
            fail("예외가 발생해야 합니다.");            //catch에 안걸렸으면
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */








    }


//
//    @Test
//    void findMembers() {
//    }
//
//    @Test
//    void findOne() {
//    }
}