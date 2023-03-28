package roadiary.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.category.CategoryCommon;
import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityCategoryEntity;
import roadiary.behavior.category.repository.CategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    
    /**
     * 유저의 카테고리순위를 우선순위에 맞춰 반환합니다.
     * @param userId
     * @return
     */
    public List<CategoryResDto> getCategoryList(Long userId) {

        List<CategoryEntity> categoryEntities = categoryRepository.selectCategoryEntities(userId);
        List<CategoryResDto> categoryResDtos = CategoryUnit.fromEntityToResDto(categoryEntities);

        return categoryResDtos;
    }

    /**
     * 개인별 카테고리순위 저장 최대 개수(MAX_PRIORITY) 이상의 카테고리가 이미 저장되어있는지 확인합니다.
     * @return 
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
     * 또는 이미 DB에 등록된 카테고리일 경우에는 저장하지 않고 기존 id값을 반환합니다.
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
     * 유저의 카테고리순위에 요청 카테고리가 저장되어있는지 확인합니다.
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

    /**
     * 유저가 카테고리순위에 새로 저장하기 위해 요청한 카테고리를 카테고리순위 마지막에 넣어줍니다.
     * 유저의 카테고리순위에 저장된 마지막 순위를 확인한 다음 그보다 큰 값으로 순위에 저장합니다.
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

    public void removePriority(Long userId, Long categoryId) {
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
