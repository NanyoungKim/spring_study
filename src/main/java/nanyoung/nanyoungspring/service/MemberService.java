package nanyoung.nanyoungspring.service;

import nanyoung.nanyoungspring.domain.Member;
import nanyoung.nanyoungspring.repository.MemberRepository;
import nanyoung.nanyoungspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();


    //회원 가입
    //같은 이름의 회원 x
    public Long join(Member member){

        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //멤버에 값 이미 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
