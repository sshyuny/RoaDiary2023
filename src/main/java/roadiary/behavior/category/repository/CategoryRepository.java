package roadiary.behavior.category.repository;

import java.util.List;

import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityCategoryEntity;

public interface CategoryRepository {
    
    public List<CategoryEntity> selectCategoryDtos(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);

    public int insertPriority(PriorityCategoryEntity priorityCategoryEntity);

    public long selectNewCategoryId(String categoryContent);

    public int deletePriority(PriorityCategoryEntity priorityCategoryEntity);
}
