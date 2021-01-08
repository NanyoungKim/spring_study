package nanyoung.nanyoungspring.repository;

import nanyoung.nanyoungspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);   //null 반환시 optional로 감싸서 반환
    List<Member> findAll();        //저장된 회원 모두 반환


}
