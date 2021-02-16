package nanyoung.nanyoungspring.controller;

import nanyoung.nanyoungspring.domain.Member;
import nanyoung.nanyoungspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


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


    //회원 등록 로직 1
    //url에 직접 치는 것을 get 방식이라고 함. 데이터 조회할 때 씀
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";  //View Resolver 가 template에서 createMemberForm.html 을 찾아서 선택하고 thymeleaf에 렌더링 -> html파일이 화면에 뿌려짐
    }

    //회원 등록 로직 2
    //post : 데이터 전달 시 씀
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName()); //회원이 입력한 이름으로 설정됨

        memberService.join(member);

        return ("redirect:/");
    }

    //회원 조회 로직 1
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //모든 회원 조회해서 리스트에 담아놨음.
        return "members/memberList";
    }
}
