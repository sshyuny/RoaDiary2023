package roadiary.behavior.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import roadiary.behavior.category.service.CategoryService;

@Controller
public class CategoryController {
    
    private final CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public String directToSaveCategory() {
        return "category.html";
    }

    /**
     * 카테고리 추가
     * @param categoryContent 요청받은 카테고리 이름
     * @return
     */
    @PostMapping("/categories")
    public String saveCategories(@ModelAttribute("categoryContent") String categoryContent) {
        
        // userId 세션에서 가져오기
        Long userId = 1L;

        int addNum = categoryService.addCategory(userId, categoryContent);

        System.out.println(addNum);

        return "redirect:/category";
    }
}
