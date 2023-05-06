package roadiary.behavior;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

        HttpSession session = request.getSession();

        if (authority.isItLoginStatus(session)) return "main.html";
        else return "main-logout.html";
        
    }
    
    @GetMapping("/login")
    public String directToLogin() {
        return "login.html";
    }

    @ResponseBody
    @GetMapping("/login/name")
    public String getUserName(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (authority.isItLoginStatus(session)) {
            return authority.getLoginUserName(session);
        } else {
            return "";
        }
    }

}
