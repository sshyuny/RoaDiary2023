package roadiary.behavior.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.category.dto.CategoryReqDto;
import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityOfCategoryEntity;
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
    public int countNCols(PriorityOfCategoryEntity priorityOfCategoryEntity) {
        if (priorityOfCategoryEntity.getN2() == 0) return 1;
        else if(priorityOfCategoryEntity.getN3() == 0) return 2;
        else if(priorityOfCategoryEntity.getN4() == 0) return 3;
        else if(priorityOfCategoryEntity.getN5() == 0) return 4;
        else if(priorityOfCategoryEntity.getN6() == 0) return 5;
        else if(priorityOfCategoryEntity.getN7() == 0) return 6;
        else if(priorityOfCategoryEntity.getN8() == 0) return 7;
        else if(priorityOfCategoryEntity.getN9() == 0) return 8;
        else if(priorityOfCategoryEntity.getN10() == 0) return 9;
        else if(priorityOfCategoryEntity.getN11() == 0) return 10;
        else if(priorityOfCategoryEntity.getN12() == 0) return 11;
        else return 12;
    }

    public PriorityOfCategoryEntity getPriorityOfCategoryEntity(long userId) {
        PriorityOfCategoryEntity priorityOfCategoryEntity = categoryRepository.selectPriorityOfCategoryEntity(userId);
        return priorityOfCategoryEntity;
    }

    public int addCategory(CategoryReqDto categoryReqDto) {
        
        CategoryEntity categoryEntity = CategoryEntity.of(categoryReqDto.getCategoryContent());

        int addedNum = categoryRepository.insertCategory(categoryEntity);  // 여기서 새로 추가된 category의 id값이 들어감
        if (addedNum != 1) return addedNum;
        
        long behaviorCategoryId = categoryEntity.getBehavior_category_id();
        categoryReqDto.setCategoryId(behaviorCategoryId);

        return addedNum;
    }

    public void addPriority(CategoryReqDto categoryReqDto, PriorityOfCategoryEntity priorityOfCategoryEntity) {
        if (priorityOfCategoryEntity.getN2() == 0) priorityOfCategoryEntity.setN2(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN3() == 0) priorityOfCategoryEntity.setN3(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN4() == 0) priorityOfCategoryEntity.setN4(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN5() == 0) priorityOfCategoryEntity.setN5(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN6() == 0) priorityOfCategoryEntity.setN6(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN7() == 0) priorityOfCategoryEntity.setN7(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN8() == 0) priorityOfCategoryEntity.setN8(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN9() == 0) priorityOfCategoryEntity.setN9(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN10() == 0) priorityOfCategoryEntity.setN10(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN11() == 0) priorityOfCategoryEntity.setN11(categoryReqDto.getCategoryId());
        else priorityOfCategoryEntity.setN12(categoryReqDto.getCategoryId());
    }
}
