package roadiary.behavior.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.repository.CategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    
    public List<CategoryResDto> getCategoryList(Long userId) {

        List<CategoryEntity> categoryEntities = categoryRepository.selectCategoryDtos(userId);
        List<CategoryResDto> categoryResDtos = CategoryUnit.fromEntityToResDto(categoryEntities);

        return categoryResDtos;
    }

    public int getSavedCategoryNum(long userId) {
        int savedCategoryNum = categoryRepository.countSavedCategoryNum(userId);
        return savedCategoryNum;
    }

    public int addCategory(CategoryReqDto categoryReqDto) {
        
        CategoryEntity categoryEntity = CategoryEntity.of(categoryReqDto.getCategoryContent());

        int addedNum = categoryRepository.insertCategory(categoryEntity);  // 여기서 새로 추가된 category의 id값이 들어감
        if (addedNum != 1) {
            // 예외처리 추가하기
            return addedNum;
        }
        
        long behaviorCategoryId = categoryEntity.getBehavior_category_id();
        categoryReqDto.setCategoryId(behaviorCategoryId);

        return addedNum;
    }

    public void addPriority(CategoryReqDto categoryReqDto) {

    }
}
