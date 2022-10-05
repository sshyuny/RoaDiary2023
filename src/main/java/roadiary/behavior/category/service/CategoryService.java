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

        int addedPriorityNum = categoryRepository.insertPriority(priorityCategoryEntity);
        return addedPriorityNum;
    }

    public int deleteUpdatePriority(CategoryReqDto categoryReqDto) {
        int deletedNum = categoryRepository.deletePriority(
                new PriorityCategoryEntity(categoryReqDto.getUserId(), 0, categoryReqDto.getCategoryId()));
        return deletedNum;
    }
}
