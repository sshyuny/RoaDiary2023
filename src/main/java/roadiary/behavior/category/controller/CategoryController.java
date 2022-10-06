package roadiary.behavior.category.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.dto.CategoryResDto;
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

        // [Repository]
        // 이미 priorityofcategory에 12개 이상의 카테고리가 추가된 경우, 카테고리 추가 막음
        List<CategoryResDto> categoryResDtos = categoryService.getCategoryList(userId);
        int savedCategoryNum = categoryResDtos.size();
        if (savedCategoryNum >= 12) return "redirect:/category?status=over";

        // [Repository]
        CategoryReqDto categoryReqDto = CategoryReqDto.of(userId, categoryContent);
        categoryService.addCategory(categoryReqDto);  // categoryReqDto에, 요청된 categoryId값이 들어감

        // [Repository]
        int addedPriorityNum = categoryService.addPriority(categoryReqDto, categoryResDtos);
        if (addedPriorityNum == 0) return "redirect:/category?status=dupli";  // 이미 저장된 카테고리일 경우

        return "redirect:/category?status=success";
    }

}
