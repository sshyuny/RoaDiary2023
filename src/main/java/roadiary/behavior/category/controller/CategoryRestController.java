package roadiary.behavior.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.category.CategoryCommon;
import roadiary.behavior.category.domain.dto.CategoryResDto;
import roadiary.behavior.category.domain.dto.PriorityAndDirectionReqDto;
import roadiary.behavior.category.domain.dto.SimpleReqDto;
import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.domain.entity.PriorityCategoryEntity;
import roadiary.behavior.category.service.CategoryService;
import roadiary.behavior.member.service.authority.SessionKeys;

@RequiredArgsConstructor
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    /**
     * 계정의 전체 카테고리순위 반환 요청
     */
    @GetMapping("/api/category/priority")
    public List<CategoryResDto> getCategories(@SessionAttribute(SessionKeys.loginUserId) long userId, HttpServletRequest request) throws Exception {

        List<CategoryResDto> categoryResDtos = categoryService.getCategoryList(userId);

        return categoryResDtos;
    }

    /**
     * 계정의 카테고리순위에 카테고리 추가 요청 처리
     * @param categoryContent 요청받은 카테고리 이름
     * @return
     */
    @PostMapping("/api/category/priority")
    public boolean saveCategories(@SessionAttribute(SessionKeys.loginUserId) long userId, HttpServletRequest request, 
            @RequestBody SimpleReqDto simpleReqDto) {
        
        String categoryContent = simpleReqDto.getData();

        if (categoryService.hasMaxCategorySavedAlready(userId)) {
            throw new IllegalArgumentException("카테고리 12개가 이미 다 차있기 때문에 새로 카테고리를 추가할 수 없습니다. 먼저 삭제를 한 뒤 추가를 해주세요.");
        }

        CategoryEntity categoryEntity = CategoryEntity.of(categoryContent);
        Long savedCategoryId = categoryService.addCategory(categoryEntity);
        
        if (categoryService.hasTheCategoryInAccountPriority(userId, savedCategoryId)) {
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다. 새로운 항목을 입력해주세요.");
        }

        int addNum = categoryService.addPriority(userId, savedCategoryId);

        return addNum == 1;
    }

    /**
     * 계정의 카테고리순위에서 요청받은 카테고리 삭제 처리
     */
    @DeleteMapping("/api/category/priority")
    public void deleteCategories(@SessionAttribute(SessionKeys.loginUserId) long userId, HttpServletRequest request, 
            @RequestBody SimpleReqDto simpleReqDto) {

        Long categoryId = Long.valueOf(simpleReqDto.getData());

        if (!categoryService.hasTheCategoryInAccountPriority(userId, categoryId)) {
            throw new IllegalArgumentException("저장되지 않은 카테고리를 삭제하려 시도합니다.");
        }

        categoryService.removePriority(userId, categoryId);
    }

    /**
     * 계정의 카테고리순위에서 요청받은 카테고리의 우선순위를 위, 아래로 수정
     */
    @PutMapping("/api/category/priority")
    public void updateCategories(@SessionAttribute(SessionKeys.loginUserId) long userId, HttpServletRequest request, @RequestBody PriorityAndDirectionReqDto priorityAndDirectionReqDto) {
        
        long categoryId = priorityAndDirectionReqDto.getCategoryId();
        String direction = priorityAndDirectionReqDto.getDirection();

        if (!(direction.equals("up") || direction.equals("down"))) {
            throw new IllegalArgumentException("요청된 카테고리 수정 방향이 유효하지 않습니다.");
        }

        if (!categoryService.hasTheCategoryInAccountPriority(userId, categoryId)) {
            throw new IllegalArgumentException("저장되지 않은 카테고리를 수정하려 시도합니다.");
        }

        List<PriorityCategoryEntity> priorityCategoryEntities = categoryService.getTwoCategoryToSwitchPriority(userId, categoryId, direction);

        categoryService.updateDirectionOfPriority(priorityCategoryEntities);
    }
    
}
