package roadiary.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
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
     * 유저가 Category Priority에 등록한 카테고리를 처리함(Cateogry Priority와 무관).
     * 이미 DB에 등록된 Category가 아닐 경우, DB에 category 등록.
     * categoryId를 categoryReqDto에 넣어줌.
     * @param categoryReqDto
     */
    public void addCategory(CategoryReqDto categoryReqDto) {
        
        CategoryEntity categoryEntity = CategoryEntity.of(categoryReqDto.getCategoryContent());

        // 카테고리 id값 확인(이미 등록된 카테고리일 경우 0 반환)
        long newCategoryId = categoryRepository.selectNewCategoryId(categoryReqDto.getCategoryContent());

        // categoryReqDto에 categoryId 값 추가
        if (newCategoryId == 0) {
            categoryRepository.insertCategory(categoryEntity);  // 여기서 categoryEntity에 categoryId 값이 들어감
            categoryReqDto.setCategoryId(categoryEntity.getBehaviorCategoryId());
        } else {
            categoryReqDto.setCategoryId(newCategoryId);
        }
    }

    /**
     * 유저가 Category Priority에 저장하기 위해 요청한 카테고리를 Priority 테이블에 등록
     * @param categoryReqDto
     * @param categoryResDtos
     * @return
     */
    public int addPriority(CategoryReqDto categoryReqDto, List<CategoryResDto> categoryResDtos) {

        // 요청 카테고리, 저장 유무 확인
        int alreadySaved = categoryRepository.countPriority(categoryReqDto.getUserId(), categoryReqDto.getCategoryId());
        if (alreadySaved != 0) return 0; // 동일한 카테고리가 이미 저장돼있는 경우

        // 사용자에게 기록된 최대 priority idx 확인 후, 그보다 큰 값으로, 요청 카테고리 insert
        int theMaxPriority = categoryRepository.selectTheMaxPriority(categoryReqDto.getUserId());
        PriorityCategoryEntity priorityCategoryEntity = new PriorityCategoryEntity(
            categoryReqDto.getUserId(), theMaxPriority + 1, categoryReqDto.getCategoryId());

        return categoryRepository.insertPriority(priorityCategoryEntity);
    }

    public void deleteAndSortPriority(Long userId, Long categoryId) {

        // [요청된 Priority 삭제]
        categoryRepository.deletePriority(userId, categoryId);

    }

    public int updateDirectionOfPriority(long userId, long categoryId, String direction) {

        
        int priorityIdx = categoryRepository.selectPriorityIdx(userId, categoryId);
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
