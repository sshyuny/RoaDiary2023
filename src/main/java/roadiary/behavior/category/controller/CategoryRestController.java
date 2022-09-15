package roadiary.behavior.category.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import roadiary.behavior.category.dto.CategoryResDTO;
import roadiary.behavior.category.service.CategoryService;

@RestController
public class CategoryRestController {

    private final CategoryService categoryService;
    
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryResDTO> getCategories() throws Exception {

        // userId 세션에서 가져오기
        Long userId = 1L;

        List<CategoryResDTO> categoryDTOList = categoryService.getCategoryList(userId);

        return categoryDTOList;
    }

    
}
