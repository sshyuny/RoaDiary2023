package snapshot.behavior.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/save")
    public String directToSave() {
        return "savebehavior.html";
    }
    
    @GetMapping("/login")
    public String directToLogin() {
        return "login.html";
    }

    @GetMapping("/trylogin")
    public String directLoginResult(HttpServletRequest request) {
        
        // 로그인 성공
        HttpSession session = request.getSession();
        //session.getAttribute(name);

        // 로그인 실패
        
        return "";
    }
}
