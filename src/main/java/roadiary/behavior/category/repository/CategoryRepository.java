package roadiary.behavior.category.repository;

import java.util.List;

import roadiary.behavior.category.entity.CategoryEntity;

public interface CategoryRepository {
    
    public List<CategoryEntity> selectCategoryDtos(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);

    public int countSavedCategoryNum(long userId);
}
