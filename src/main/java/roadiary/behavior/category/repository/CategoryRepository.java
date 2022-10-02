package roadiary.behavior.category.repository;

import java.util.List;

import roadiary.behavior.entity.CategoryEntity;

public interface CategoryRepository {
    
    public List<CategoryEntity> selectCategoryDTOs(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);
}
