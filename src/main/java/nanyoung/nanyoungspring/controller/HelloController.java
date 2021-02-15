package nanyoung.nanyoungspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    //1. 정적
    @GetMapping("hello")
    public String hello(Model model) {

        model.addAttribute("data", "hello!!");
        return "hello";
    }

    //2. MVC
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //3. API - 문자
    @GetMapping("hello-string")
    @ResponseBody //http의 응답 바디부에 아래의 내용을 직접 넣겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;  //이 문자가 요청한 클라이언트에 그대로 내려감
        //템플릿 엔진과의 차이 : 템플릿 엔진은 뷰라는 템플릿이 있는 상황에서 조작
        //이건 데이터를 뷰 없이 이 문자 그대로 내려감. 잘 안씀
    }


   //3. API - 데이터(객체)
    @GetMapping("hello-api")
    @ResponseBody   //객체를 반환할때 ResponseBody 설정해놓으면 json 으로 반환하는게 디폴트임

    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   //웹페이지에 json 형식으로 출력됨
    }

    //static으로 선언하면 클래스 안에서 클래스 사용 가능(HelloController.Hello 이런식으로 사용 가능)
    static class Hello{
        private String name;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
