package snapshot.behavior.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    
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
