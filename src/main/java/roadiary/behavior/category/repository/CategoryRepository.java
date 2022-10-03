package roadiary.behavior.category.repository;

import java.util.List;

import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityOfCategoryEntity;

public interface CategoryRepository {
    
    public List<CategoryEntity> selectCategoryDtos(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);

    public PriorityOfCategoryEntity selectPriorityOfCategoryEntity(long userId);

    public int updatePriority(PriorityOfCategoryEntity priorityOfCategoryEntity);

    public long selectNewCategoryId(String categoryContent);
}
