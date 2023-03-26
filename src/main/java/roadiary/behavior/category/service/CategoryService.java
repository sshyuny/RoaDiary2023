package roadiary.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.category.CategoryCommon;
import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityCategoryEntity;
import roadiary.behavior.category.repository.CategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    
    /**
     * 로그인 유저의 Category를 Priority 순서대로 반환
     * @param userId
     * @return
     */
    public List<CategoryResDto> getCategoryList(Long userId) {

        List<CategoryEntity> categoryEntities = categoryRepository.selectCategoryEntities(userId);
        List<CategoryResDto> categoryResDtos = CategoryUnit.fromEntityToResDto(categoryEntities);

        return categoryResDtos;
    }

    /**
     * 이미 priorityofcategory에 개인별 최대 개수(MAX_PRIORITY) 이상의 카테고리가 저장되어있는지 확인합니다.
     * @return 이미 최대 카테고리 개수가 저장된 경우 true
     */
    public boolean hasMaxCategorySavedAlready(Long userId) {

        List<CategoryResDto> categoryResDtos = getCategoryList(userId);
        if (categoryResDtos.size() >= CategoryCommon.MAX_PRIORITY) {
            return true;  
        }
        return false;
    }

    /**
     * 새로운 카테고리를 저장하고 id값을 반환합니다.
     * 이미 DB에 등록된 Category일 경우 저장하지 않고 기존 id값을 반환합니다.
     * @param categoryContent
     * @return
     */
    public Long addCategory(String categoryContent) {
        
        CategoryEntity categoryEntity = CategoryEntity.of(categoryContent);

        Long newCategoryId = categoryRepository.selectCategoryByContent(categoryContent);

        if (newCategoryId == null) {
            categoryRepository.insertCategory(categoryEntity);  // 여기서 categoryEntity에 categoryId 값이 들어감
            return categoryEntity.getBehaviorCategoryId();
        }

        return newCategoryId;
    }

    /**
     * 요청된 카테고리가 이미 계정 Category Priority에 저장돼있는지 확인합니다.
     * @param userId
     * @param categoryId
     * @return
     */
    public boolean isAlreadySavedInAccount(long userId, Long categoryId) {
        int alreadySavedNum = categoryRepository.countPriority(userId, categoryId);
        if (alreadySavedNum != 0) {
            return true;
        }
        return false;
    }

    /**
     * 유저가 Category Priority에 저장하기 위해 요청한 카테고리를 Priority 테이블에 등록합니다.
     * 사용자에게 기록된 최대 priority idx 확인 후, 그보다 큰 값으로 요청받은 카테고리를 순위에 저장합니다.
     * @param categoryReqDto
     * @param categoryResDtos
     * @return
     */
    public int addPriority(long userId, Long categoryId) {

        Integer theMaxPriority = categoryRepository.selectTheMaxPriority(userId);
        if (theMaxPriority == null) {
            theMaxPriority = 0;
        }

        PriorityCategoryEntity priorityCategoryEntity = new PriorityCategoryEntity(
            userId, theMaxPriority + 1, categoryId);

        return categoryRepository.insertPriority(priorityCategoryEntity);
    }

    /**
     * 계정의 카테고리순위에 요청 카테고리가 저장되어있는지 확인합니다.
     * @param userId
     * @param categoryId
     * @return
     */
    public boolean hasTheCategoryInAccountPriority(Long userId, long categoryId) {

        Integer priorityIdx = categoryRepository.selectPriorityIdx(userId, categoryId);
        if (priorityIdx == null) {
            return false;
        }
        return true;
    }

    public void removePriority(Long userId, Long categoryId) {

        //없으면 예외처리

        categoryRepository.deletePriority(userId, categoryId);
    }

    public int updateDirectionOfPriority(long userId, long categoryId, String direction) {

        
        Integer priorityIdx = categoryRepository.selectPriorityIdx(userId, categoryId);
        List<PriorityCategoryEntity> priorityCategoryEntities = new ArrayList<>();

        // 선택된 카테고리보다 높은 priority 반환
        if (direction.equals("up")) {
            priorityCategoryEntities = categoryRepository.selectUpPriorityEntities(userId, priorityIdx);
            if (priorityCategoryEntities.size() < 2) return 0;
        } else if (direction.equals("down")) {
            priorityCategoryEntities = categoryRepository.selectDownPriorityEntities(userId, priorityIdx);
            if (priorityCategoryEntities.size() < 2) return 0;
        } else {
            // @@예외 처리
        }

        // priorityCategoryEntities에 저장된 priority 서로 뒤바꿔준 뒤, update 
        CategoryUnit.switchPriority(priorityCategoryEntities);
        for (PriorityCategoryEntity priorityCategoryEntity : priorityCategoryEntities)
            categoryRepository.updatePriority(priorityCategoryEntity);

        return 1;
    }

}
