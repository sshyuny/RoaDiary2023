package roadiary.behavior.category.service;

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
    private int maxPriority = 12; // Priority Category에 저장할 수 있는 최대 개수
    
    /**
     * 로그인 유저의 Category를 Priority 순서대로 반환
     * @param userId
     * @return
     */
    public List<CategoryResDto> getCategoryList(Long userId) {

        List<CategoryEntity> categoryEntities = categoryRepository.selectCategoryDtos(userId);
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
            categoryReqDto.setCategoryId(categoryEntity.getBehavior_category_id());
        } else {
            categoryReqDto.setCategoryId(newCategoryId);
        }
    }

    /**
     * 유저가 Category Priority에 등록한 카테고리를 Priority 테이블에 등록
     * @param categoryReqDto
     * @param categoryResDtos
     * @return
     */
    public int addPriority(CategoryReqDto categoryReqDto, List<CategoryResDto> categoryResDtos) {

        PriorityCategoryEntity priorityCategoryEntity = new PriorityCategoryEntity(
            categoryReqDto.getUserId(), categoryResDtos.size() + 1, categoryReqDto.getCategoryId());

        int alreadySaved = categoryRepository.countPriority(priorityCategoryEntity);
        if (alreadySaved != 0) return 0; // 동일한 카테고리가 이미 저장돼있는 경우

        int addedPriorityNum = categoryRepository.insertPriority(priorityCategoryEntity);
        return addedPriorityNum;
    }

    public void deleteSortPriority(CategoryReqDto categoryReqDto) {

        // [요청된 Priority 삭제]
        PriorityCategoryEntity priorityCategoryEntity = new PriorityCategoryEntity(categoryReqDto.getUserId(), 0, categoryReqDto.getCategoryId());
        categoryRepository.deletePriority(priorityCategoryEntity);

        // [비어있는 곳 찾아, 앞으로 당기기]
        priorityCategoryEntity.setBehavior_category_id(0);  // 무의미 값
        int curIdx = 1; int lastNullIdx = maxPriority;
        for (int idx = 1; idx <= maxPriority; idx++ ) {
            // priority(idx)로 category 찾기
            priorityCategoryEntity.setPriority_idx(idx);
            Long behavior_category_id = categoryRepository.selectCateogryIdFromPriority(priorityCategoryEntity);

            if (behavior_category_id != null) {
                if (lastNullIdx < idx) {
                    priorityCategoryEntity.setPriority_idx(curIdx);
                    priorityCategoryEntity.setBehavior_category_id(behavior_category_id);

                    categoryRepository.deletePriority(priorityCategoryEntity);  // categoryId로, 뒤에 있던 category 삭제
                    categoryRepository.insertPriority(priorityCategoryEntity);  // 삭제한 category를 맞는 위치(curIdx)에 넣기

                    lastNullIdx = idx;  // 앞서 지운 (뒤에 있던) category의 위치(idx)를 null 위치로 표시
                }
                ++curIdx;
            }
            else {
                lastNullIdx = idx;  // null 위치로 표시
            }
        }

    }
}
