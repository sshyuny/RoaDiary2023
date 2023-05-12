package roadiary.behavior.category;

import java.util.ArrayList;
import java.util.List;

import roadiary.behavior.category.domain.entity.CategoryEntity;

public class CategoryTestUtil {
    
    public static List<CategoryEntity> makeCategoryEntitiesUnderMax() {

        List<CategoryEntity> categoryEntities = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            categoryEntities.add(CategoryEntity.of("content" + i));
        }

        return categoryEntities;
    }

    public static List<CategoryEntity> makeCategoryEntitiesAboveMax() {

        List<CategoryEntity> categoryEntities = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            categoryEntities.add(CategoryEntity.of("content" + i));
        }

        return categoryEntities;
    }
}
