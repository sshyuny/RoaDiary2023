package snapshot.behavior.category;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.dto.CategoryResDTO;
import snapshot.behavior.category.service.CategoryService;

@RestController
public class CategoryRestController {

    private final CategoryService categoryService;
    
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getcategory")
    public List<CategoryResDTO> getCategory() throws Exception {

        // userId 세션에서 가져오기
        Long userId = 1L;

        List<CategoryResDTO> categoryDTOList = categoryService.getCategoryList(userId);
        
        

        return categoryDTOList;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
