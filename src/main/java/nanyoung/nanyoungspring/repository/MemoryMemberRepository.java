package nanyoung.nanyoungspring.repository;

import nanyoung.nanyoungspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성 문제로 인해 공유 변수 일땐 concurrent hashmap 써야함
    private static long sequence = 0L;      //0,1,2,,, 로 key 값 생성해주는 역할. 실무에서는 동시성 문제로 어텀 long 씀.



    @Override
    public Member save(Member member) {     //이름은 여기서 이미 넘어옴.(회원가입 시 입력하므로), 근데 아이디는 시스템에 저장할 때 지정하는 것이므로 setId로 지정.
        member.setId(++sequence);           //sequence 값 하나 올려서 아이디 셋팅 후
        store.put(member.getId(),member);   //store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //과거에는 return store.get(id) 이렇게 리턴했음.
        //최근엔 null을 감싸서 리턴함. Client에서 처리할 수 있도록
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()//루프로 돌림
                .filter(member -> member.getName().equals(name))    //이름이 같은 경우에만 필터링 됨
                .findAny(); //필터링 된 것에서 찾으면 반환한다. 루프 돌면서 찾으면 그냥 반환하는데 루프 끝까지 돌아도 없으면 Optional에 null 포함되서 반환됨.

    }

    @Override
    public List<Member> findAll() {
        //store은 맵인데 여기서는 List로 반환함. -> 자바 실무에서는 리스트 많이 씀(루프 돌리기도 편함)
        return new ArrayList<>(store.values()); //store.values는 Map안 Member 이다. Member 들이 쭉 반환됨.
    }


    //테스트 할 때 메소드 사이에서 충돌 피하기 위함.
    public void clearStore(){
        store.clear();
    }


}
