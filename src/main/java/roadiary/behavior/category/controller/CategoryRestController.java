package roadiary.behavior.category.controller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.dto.PriorityAndDirectionDto;
import roadiary.behavior.category.service.CategoryService;

@RestController
public class CategoryRestController {

    private final CategoryService categoryService;
    
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/priority")
    public List<CategoryResDto> getCategories() throws Exception {

        // userId 세션에서 가져오기
        Long userId = 1L;

        List<CategoryResDto> categoryResDtos = categoryService.getCategoryList(userId);

        return categoryResDtos;
    }

    @DeleteMapping("/priority")
    public void deleteCategories(HttpEntity<String> httpEntity) {

        // userId 세션에서 가져오기
        Long userId = 1L;

        Long categoryId = Long.valueOf(httpEntity.getBody());
        categoryService.deleteAndSortPriority(userId, categoryId);
    }

    @PutMapping("/priority")
    public String updateCategories(@RequestBody PriorityAndDirectionDto priorityAndDirectionDto) {
        
        // userId 세션에서 가져오기
        Long userId = 1L;
        int checkPossibleNum = categoryService.updateDirectionOfPriority(
            userId, priorityAndDirectionDto.getCategoryId(), priorityAndDirectionDto.getDirection());

        if (checkPossibleNum == 0) return "0";
        else return "1";
    }
    
}
