package nanyoung.nanyoungspring;

import nanyoung.nanyoungspring.repository.MemberRepository;
import nanyoung.nanyoungspring.repository.MemoryMemberRepository;
import nanyoung.nanyoungspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //spring뜰 때 @Configuration읽고 spring Bean에 동록함
    @Bean
    public MemberService memberService(){
        //memberRepository() 메소드 호출해서 spring bean에 등록돼있는 membreRepository를 memberService()에 넣어줌
        return new MemberService(memberRepository());
        
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}

