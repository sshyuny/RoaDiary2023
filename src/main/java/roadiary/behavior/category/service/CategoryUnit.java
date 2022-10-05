package roadiary.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import roadiary.behavior.category.dto.CategoryResDto;
import roadiary.behavior.category.entity.CategoryEntity;

public class CategoryUnit {
    

    static public List<CategoryResDto> fromEntityToResDto(List<CategoryEntity> categoryEntities) {

        List<CategoryResDto> newList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryResDto categoryResDto = new CategoryResDto(categoryEntity.getBehavior_category_id(), categoryEntity.getContent());
            newList.add(categoryResDto);
        }

        return newList;
    }
    
}
