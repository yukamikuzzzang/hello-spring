package hello.hellospring.controller.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
//@Component
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();    //인스턴스가 아닌 클래스레벨에 붙는거라
    private static long sequence = 0L;
    //공유되는 경우 concurrent 관련 사항을 유의해야함. (동시성 문제) 예시라서 이렇게..

    //인터페이스를 상속받고 여기서 구현해!
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //요즘엔 Null이 반환될 수 있는 경우 optional로 감싸줌.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
