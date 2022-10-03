package roadiary.behavior.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.entity.PriorityOfCategoryEntity;
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

        // 이미 priorityofcategory에 12개 이상의 카테고리가 추가된 경우, 카테고리 추가 막음
        //int savedCategoryNum = categoryService.getSavedCategoryNum(userId);
        //if (savedCategoryNum >= 12) return "redirect:/category?status=over";
        PriorityOfCategoryEntity priorityOfCategoryEntity = categoryService.getPriorityOfCategoryEntity(userId);
        int savedCategoryNum = categoryService.countNCols(priorityOfCategoryEntity);
        if (savedCategoryNum >= 12) return "redirect:/category?status=over";

        // [Repository]
        CategoryReqDto categoryReqDto = CategoryReqDto.of(userId, categoryContent);
        categoryService.addCategory(categoryReqDto);  // categoryReqDto에, 요청된 categoryId값이 들어감

        // [Repository]
        int addedPriorityNum = categoryService.addPriority(categoryReqDto, priorityOfCategoryEntity);

        // 카테고리 priority 추가가 되지 않은 경우
        if (addedPriorityNum != 1) return "redirect:/category?status=not";

        return "redirect:/category?status=success";
    }
}
