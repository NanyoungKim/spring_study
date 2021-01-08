package nanyoung.nanyoungspring.repository;

import nanyoung.nanyoungspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //실무에서 동시성 문제로 인해 공유 변수 일땐 concurrent hashmap 써야함
    private static long sequence = 0L;      //0,1,2,,, 로 key 값 생성해줌



    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //M ember 객체들 반
    }

    public void clearStore(){
        store.clear();
    }


}
