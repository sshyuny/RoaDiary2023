package snapshot.behavior.category;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import snapshot.behavior.dto.CategoryDTO;

@RestController
public class CategoryRestController {

    private final CategoryService categoryService;
    
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getcategory")
    public List<CategoryDTO> getCategory() {

        // userId 세션에서 가져오기
        Long userId = 1L;

        List<CategoryDTO> categoryDTOList = categoryService.getCategoryList(userId);
        
        

        return categoryDTOList;
    }
}
