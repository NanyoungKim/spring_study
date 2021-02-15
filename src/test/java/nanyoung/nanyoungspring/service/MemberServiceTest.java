package nanyoung.nanyoungspring.service;

import nanyoung.nanyoungspring.domain.Member;
import nanyoung.nanyoungspring.repository.MemberRepository;
import nanyoung.nanyoungspring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //Dependency Injection(DI)
    @BeforeEach //동작하기 전에 이미 생성된 인스턴스들 넣어서 중복 안되게 해줌
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  //메소드 끝나고나서 메모리 클리어
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {   //빌드될 때 실제 코드에 포함되지 않아서 한글로 적어도 무방
        //given : 이런 상황에서
        Member member = new Member();
        member.setName("hello");

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






        //then

    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}