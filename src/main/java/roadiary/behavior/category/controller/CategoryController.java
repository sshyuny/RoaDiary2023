package roadiary.behavior.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/category")
    public String directToSaveCategory() {
        return "category.html";
    }

}
