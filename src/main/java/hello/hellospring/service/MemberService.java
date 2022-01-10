package hello.hellospring.service;

import hello.hellospring.controller.repository.MemberRepository;
import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {   //외부에서 넣어주도록 변경, new로 생성하지 않음
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    * */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        //Optional<Member> result = memberRepository.findByName(member.getName());
        validateDuplicateMember(member);    //중복 회원 검증
        //Ctrl+Alt+M 특정 메소드로 따로 뽑아낼 수 있다. (extracted)
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())    //Ctrl+Shift+Alt+T*
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
