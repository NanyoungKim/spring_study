package nanyoung.nanyoungspring.service;

import nanyoung.nanyoungspring.domain.Member;
import nanyoung.nanyoungspring.repository.MemberRepository;
import nanyoung.nanyoungspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//회원 레포지토리와 도메인을 활용해서 실제 비즈니스 로직을 작성하는 클래스
//메소드 이름 등을 비즈니스 적으로 정한다. (MemoryMemberRepository와는 개발자스럽게 함 )
//@Service
public class MemberService {
//command + shift + T : 테스트 클래스 자동 생성

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        //MemberService는 memberRepositoryrk vlfdygka .
        //MemberService 생성될 때 spring container 에 있는 memberReposirory 넣어줌(연결해줌)
        this.memberRepository = memberRepository;
    }

    //회원 가입, 같은 이름의 회원 가입 방지
    public Long join(Member member){

        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);      //저장소에 정보 저장.
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //멤버에 null이 아니라 어떤 값 이미 있으면 동작함. Optional 이기에 가능. 과거에는 if(null) 로 코딩함.
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //id로 멤버객체 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
