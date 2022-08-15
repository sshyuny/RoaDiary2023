package snapshot.behavior;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String directToMain() {
        return "main.html";
    }
    
    @GetMapping("/login")
    public String directToLogin() {
        return "login.html";
    }
    
    @GetMapping("/save")
    public String directToSave() {
        return "savebehavior.html";
    }

}
