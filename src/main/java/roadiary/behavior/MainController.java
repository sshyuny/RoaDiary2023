package roadiary.behavior;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.member.service.authority.Authority;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final Authority authority;

    @GetMapping("/")
    public String directToMain(HttpServletRequest request) {

        if (authority.isItLoginStatus(request)) return "main.html";
        else return "main-logout.html";
        
    }
    
    @GetMapping("/login")
    public String directToLogin() {
        return "login.html";
    }

    @ResponseBody
    @GetMapping("/login/name")
    public String getUserName(HttpServletRequest request) {

        if (authority.isItLoginStatus(request)) return authority.getLoginUserName(request);
        else return "";
    }

}
