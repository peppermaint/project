package com.proejct.project.web;

import com.proejct.project.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

//@Controller는 뷰를 리턴
//@ResposeBody는 데이터를 리턴
@Controller
@AllArgsConstructor
public class webController {

    //@RequestMapping(value="/", method = RequestMethod.GET) 대신 GetMapping 사용
    //handlebars-spring-boot-starter 덕분에 컨트롤러에서 문자열을 반환할때 앞의 path와 뒤의 파일 확장자는 자동으로 지정
    // src/main/resources/templates/main.hbs로 전환되어 View Resolver가 처리됨.
    //@ModelAttribute 메서드의 인수에 사용, HTTP요청에 들어 있는 속성값들을 MemberDto 커맨드 객체에 자동으로 바인딩
    @GetMapping("/member/login")
    public String login()
    {
        return "Member/login";
    }

    @GetMapping("/member/login/result")
    public String loginSuccess(){
        return "main";
    }

    @GetMapping("/member/register")
    public String register(){
        return "Member/register";
    }

    @GetMapping("/member/forgot-password")
    public String forgotPassword(){
        return "Member/forgot-password";
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }
}
