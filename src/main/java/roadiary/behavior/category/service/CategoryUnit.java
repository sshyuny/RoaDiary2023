package roadiary.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import roadiary.behavior.category.domain.dto.CategoryResDto;
import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.domain.entity.PriorityCategoryEntity;

public class CategoryUnit {
    
    static public List<CategoryResDto> fromEntityToResDto(List<CategoryEntity> categoryEntities) {

        List<CategoryResDto> newList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryResDto categoryResDto = CategoryResDto.of(categoryEntity.getBehaviorCategoryId(), categoryEntity.getContent());
            newList.add(categoryResDto);
        }

        return newList;
    }

    static public void switchPriority(List<PriorityCategoryEntity> priorityCategoryEntities) {
        int priority1 = priorityCategoryEntities.get(0).getPriorityIdx();
        int priority2 = priorityCategoryEntities.get(1).getPriorityIdx();
        priorityCategoryEntities.get(0).setPriorityIdx(priority2);
        priorityCategoryEntities.get(1).setPriorityIdx(priority1);
    }
    
}
