package nanyoung.nanyoungspring.controller;

import nanyoung.nanyoungspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


//spring 이 처음에 뜰 때 spring container라는 spring 통이 생김.
//여기에 @Controller 라는 annotation이 있으면 MemberController객첼르 생성해서 spring에 넣어둠. 그리고 spring에서 관리해 줌.
//즉, spring container 에서 spring bin이 관리됨.

@Controller
public class MemberController {

    //이전에는 아래와 같이 썼음. 그러나 spring 이 관리하게 되면, spring container 에 등록을 하고 여기서 받아서 쓰도록 바꿔야 함.
    //new 로 객체 생성하면 MemberCotroller 뿐 아니라 다른 여러 컴포넌트들이 MemberService 객체에 접근 가능.
    //그런데 MemberService는 별 기능 없어서 여러개 생성할 필요 없이 하나 생성하고 공용으로 쓰면 됨.
    //private final MemberService =new MemberService();


    //spring container 에 등록하자. -> 딱 하나만 등록됨 + 여러 부가적 효과를 볼 수 있음
    private final MemberService memberService;

    //Autowired : MemberController가 생성될 때, spring bin에 등록되어 있는 memberService객체를 가져다가 넣어줌. (Dependency Injection)
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

}
