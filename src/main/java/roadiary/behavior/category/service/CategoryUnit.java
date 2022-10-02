package roadiary.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import roadiary.behavior.category.dto.CategoryResDTO;
import roadiary.behavior.entity.CategoryEntity;

public class CategoryUnit {
    

    static public List<CategoryResDTO> fromEntityToResDTO(List<CategoryEntity> categoryEntities) {

        List<CategoryResDTO> newList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryResDTO categoryResDTO = new CategoryResDTO(categoryEntity.getBehavior_category_id(), categoryEntity.getContent());
            newList.add(categoryResDTO);
        }

        return newList;
    }
}
