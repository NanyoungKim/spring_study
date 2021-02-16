package nanyoung.nanyoungspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //localhost8080 요청 오면 Spring Container 스캔해서 맵핑된 컨트롤러 있는지 찾음.  있으면 컨트롤러 호출하고 끝남. 없으면 static파일 찾음.
    @GetMapping("/")        //localhost:8080 들어가면 바로 호출
    public String home(){
        return "home";
    }
}
