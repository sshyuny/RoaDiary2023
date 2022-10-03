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
        if (priorityOfCategoryEntity.getN2() == null) return 1;
        else if(priorityOfCategoryEntity.getN3() == null) return 2;
        else if(priorityOfCategoryEntity.getN4() == null) return 3;
        else if(priorityOfCategoryEntity.getN5() == null) return 4;
        else if(priorityOfCategoryEntity.getN6() == null) return 5;
        else if(priorityOfCategoryEntity.getN7() == null) return 6;
        else if(priorityOfCategoryEntity.getN8() == null) return 7;
        else if(priorityOfCategoryEntity.getN9() == null) return 8;
        else if(priorityOfCategoryEntity.getN10() == null) return 9;
        else if(priorityOfCategoryEntity.getN11() == null) return 10;
        else if(priorityOfCategoryEntity.getN12() == null) return 11;
        else return 12;
    }

    public PriorityOfCategoryEntity getPriorityOfCategoryEntity(long userId) {
        PriorityOfCategoryEntity priorityOfCategoryEntity = categoryRepository.selectPriorityOfCategoryEntity(userId);
        return priorityOfCategoryEntity;
    }

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

    public int addPriority(CategoryReqDto categoryReqDto, PriorityOfCategoryEntity priorityOfCategoryEntity) {
        if (priorityOfCategoryEntity.getN2() == null) priorityOfCategoryEntity.setN2(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN3() == null) priorityOfCategoryEntity.setN3(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN4() == null) priorityOfCategoryEntity.setN4(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN5() == null) priorityOfCategoryEntity.setN5(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN6() == null) priorityOfCategoryEntity.setN6(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN7() == null) priorityOfCategoryEntity.setN7(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN8() == null) priorityOfCategoryEntity.setN8(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN9() == null) priorityOfCategoryEntity.setN9(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN10() == null) priorityOfCategoryEntity.setN10(categoryReqDto.getCategoryId());
        else if(priorityOfCategoryEntity.getN11() == null) priorityOfCategoryEntity.setN11(categoryReqDto.getCategoryId());
        else priorityOfCategoryEntity.setN12(categoryReqDto.getCategoryId());

        int addedPriorityNum = categoryRepository.updatePriority(priorityOfCategoryEntity);
        return addedPriorityNum;
    }
}
