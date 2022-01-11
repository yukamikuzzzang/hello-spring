package hello.hellospring;

import hello.hellospring.controller.repository.MemberRepository;
import hello.hellospring.controller.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());   //DI, 멤버 서비스와 리포지토리와 엮어주어야함.

    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();    //구현체
    }

}
