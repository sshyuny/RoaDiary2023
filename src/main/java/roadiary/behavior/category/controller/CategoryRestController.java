package roadiary.behavior.category.controller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.dto.PriorityAndDirectionDto;
import roadiary.behavior.category.service.CategoryService;

@RestController
public class CategoryRestController {

    private final CategoryService categoryService;
    private final int MAX_PRIORITY = 12; // Priority Category에 저장할 수 있는 최대 개수
    
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/priority")
    public List<CategoryResDto> getCategories() throws Exception {

        // userId 세션에서 가져오기
        Long userId = 1L;

        List<CategoryResDto> categoryResDtos = categoryService.getCategoryList(userId);

        return categoryResDtos;
    }

    /**
     * 카테고리 추가
     * @param categoryContent 요청받은 카테고리 이름
     * @return
     */
    @PostMapping("/category/priority")
    public String saveCategories(HttpEntity<String> httpEntity) {
        
        // userId 세션에서 가져오기
        Long userId = 1L;

        String categoryContent = httpEntity.getBody();

        // [Repository]
        // 이미 priorityofcategory에 최대 개수(MAX_PRIORITY) 이상의 카테고리가 추가된 경우, 카테고리 추가 막음
        List<CategoryResDto> categoryResDtos = categoryService.getCategoryList(userId);
        int savedCategoryNum = categoryResDtos.size();
        if (savedCategoryNum >= MAX_PRIORITY) return "over";

        // [Repository]
        CategoryReqDto categoryReqDto = CategoryReqDto.of(userId, categoryContent);
        categoryService.addCategory(categoryReqDto);  // categoryReqDto에, 요청된 categoryId값이 들어감

        // [Repository]
        int addedPriorityNum = categoryService.addPriority(categoryReqDto, categoryResDtos);
        if (addedPriorityNum == 0) return "dupli";  // 이미 저장된 카테고리일 경우

        return "success";
    }

    @DeleteMapping("/category/priority")
    public void deleteCategories(HttpEntity<String> httpEntity) {

        // userId 세션에서 가져오기
        Long userId = 1L;

        Long categoryId = Long.valueOf(httpEntity.getBody());
        categoryService.deleteAndSortPriority(userId, categoryId);
    }

    @PutMapping("/category/priority")
    public String updateCategories(@RequestBody PriorityAndDirectionDto priorityAndDirectionDto) {
        
        // userId 세션에서 가져오기
        Long userId = 1L;
        int checkPossibleNum = categoryService.updateDirectionOfPriority(
            userId, priorityAndDirectionDto.getCategoryId(), priorityAndDirectionDto.getDirection());

        if (checkPossibleNum == 0) return "0";
        else return "1";
    }
    
}
