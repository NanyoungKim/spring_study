package nanyoung.nanyoungspring.repository;

import nanyoung.nanyoungspring.domain.Member;

import java.util.List;
import java.util.Optional;

//DB 저장방식 안 정해져서 일단 인터페이스로 구현한 뒤 나중에 바꿀 수 있도록 함
public interface MemberRepository {

    Member save(Member member);                 //회원 저장소에 저장하면 저장된 회원 반환됨
   //Optional : null 처리 방법으로, id 또는 name 으로 회원 찾는데 없을 때 null을 Optional로 감싸서 반환해줌.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);   //null 반환시 optional로 감싸서 반환
    List<Member> findAll();        //저장된 회원 모두 반환


}
