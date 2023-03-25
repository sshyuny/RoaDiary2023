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

import lombok.RequiredArgsConstructor;
import roadiary.behavior.category.CategoryCommon;
import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.dto.PriorityAndDirectionDto;
import roadiary.behavior.category.service.CategoryService;
import roadiary.behavior.member.authority.SessionKeys;

@RequiredArgsConstructor
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

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

        if (categoryService.hasMaxCategorySavedAlready(userId)) {
            return CategoryCommon.OVER;
        }

        Long categoryId = categoryService.addCategory(categoryContent);

        CategoryReqDto categoryReqDto = CategoryReqDto.of(userId, categoryId, categoryContent);
        int addedPriorityNum = categoryService.addPriority(categoryReqDto);

        if (addedPriorityNum == 0) {
            return CategoryCommon.DUPLI;  // 이미 저장된 카테고리일 경우
        }

        return CategoryCommon.SUCCESS;
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
