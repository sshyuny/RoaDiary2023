package roadiary.behavior.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    
    @GetMapping("/member")
    public String directToMember() {
        return "member.html";
    }
}
