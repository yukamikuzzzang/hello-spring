package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @PersistenceContext
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;   //DI
    }

    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;   //DI
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());   //DI, 멤버 서비스와 리포지토리와 엮어주어야함.

    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();    //구현체
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }


}
