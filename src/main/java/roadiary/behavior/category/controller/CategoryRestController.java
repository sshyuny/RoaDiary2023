package roadiary.behavior.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import roadiary.behavior.member.authority.SessionKeys;

@RestController
public class CategoryRestController {

    private final CategoryService categoryService;
    private final int MAX_PRIORITY = 12; // Priority Category에 저장할 수 있는 최대 개수
    
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/category/priority")
    public List<CategoryResDto> getCategories(HttpServletRequest request) throws Exception {

        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        List<CategoryResDto> categoryResDtos = categoryService.getCategoryList(userId);

        return categoryResDtos;
    }

    /**
     * 카테고리 추가
     * @param categoryContent 요청받은 카테고리 이름
     * @return
     */
    @PostMapping("/api/category/priority")
    public String saveCategories(HttpServletRequest request, HttpEntity<String> httpEntity) {
        
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

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
        int addedPriorityNum = categoryService.addPriority(categoryReqDto);
        if (addedPriorityNum == 0) return "dupli";  // 이미 저장된 카테고리일 경우

        return "success";
    }

    @DeleteMapping("/api/category/priority")
    public void deleteCategories(HttpServletRequest request, HttpEntity<String> httpEntity) {

        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        Long categoryId = Long.valueOf(httpEntity.getBody());
        categoryService.deleteAndSortPriority(userId, categoryId);
    }

    @PutMapping("/api/category/priority")
    public String updateCategories(HttpServletRequest request, @RequestBody PriorityAndDirectionDto priorityAndDirectionDto) {
        
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        int checkPossibleNum = categoryService.updateDirectionOfPriority(
            userId, priorityAndDirectionDto.getCategoryId(), priorityAndDirectionDto.getDirection());

        if (checkPossibleNum == 0) return "0";
        else return "1";
    }
    
}
