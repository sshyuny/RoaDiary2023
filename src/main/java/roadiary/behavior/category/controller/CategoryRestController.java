package roadiary.behavior.category.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import roadiary.behavior.category.CategoryCommon;
import roadiary.behavior.category.domain.dto.CategoryResDto;
import roadiary.behavior.category.domain.dto.PriorityAndDirectionReqDto;
import roadiary.behavior.category.domain.dto.SimpleReqDto;
import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.domain.entity.PriorityCategoryEntity;
import roadiary.behavior.category.service.CategoryService;
import roadiary.behavior.common.ErrorResult;
import roadiary.behavior.member.service.authority.SessionKeys;

@RequiredArgsConstructor
@Slf4j
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalStateException(IllegalArgumentException e, HttpServletRequest request) {
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());
        log.error("user={}", userId, e);
        return new ErrorResult("NOTVALID");
    } 


    /**
     * 계정의 전체 카테고리순위 반환 요청
     * @param userId
     * @param request
     * @return
     * @throws Exception
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
    public String saveCategories(@SessionAttribute(SessionKeys.loginUserId) long userId, HttpServletRequest request, 
            @RequestBody SimpleReqDto simpleReqDto) {
        
        String categoryContent = simpleReqDto.getData();

        if (categoryService.hasMaxCategorySavedAlready(userId)) {
            return CategoryCommon.OVER;
        }

        CategoryEntity categoryEntity = CategoryEntity.of(categoryContent);
        Long savedCategoryId = categoryService.addCategory(categoryEntity);
        
        if (categoryService.hasTheCategoryInAccountPriority(userId, savedCategoryId)) {
            return CategoryCommon.DUPLI;
        }

        categoryService.addPriority(userId, savedCategoryId);

        return CategoryCommon.SUCCESS;
    }

    /**
     * 계정의 카테고리순위에서 요청받은 카테고리 삭제 처리
     * @param request
     * @param httpEntity
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
     * @param request
     * @param priorityAndDirectionReqDto
     * @return
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
