package nanyoung.nanyoungspring;

import nanyoung.nanyoungspring.repository.JdbcMemberRepository;
import nanyoung.nanyoungspring.repository.MemberRepository;
import nanyoung.nanyoungspring.repository.MemoryMemberRepository;
import nanyoung.nanyoungspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //spring뜰 때 @Configuration읽고 spring Bean에 동록함
    @Bean
    public MemberService memberService(){
        //memberRepository() 메소드 호출해서 spring bean에 등록돼있는 membreRepository를 memberService()에 넣어줌
        return new MemberService(memberRepository());
        
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}

