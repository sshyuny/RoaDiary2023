package roadiary.behavior.category.repository;

import java.util.List;

import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.domain.entity.PriorityCategoryEntity;

public interface CategoryRepository {
    
    public List<CategoryEntity> selectCategoryEntities(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);

    public int insertPriority(PriorityCategoryEntity priorityCategoryEntity);

    public Integer selectTheMaxPriority(long userId);

    public Long selectCategoryByContent(String categoryContent);

    public int deletePriority(long userId, long categoryId);

    public int updatePriority(PriorityCategoryEntity priorityCategoryEntity);

    public int countPriority(long userId, long categoryId);

    public Integer selectPriorityIdx(long userId, long categoryId);

    public List<PriorityCategoryEntity> selectUpPriorityEntities(long userId, long pirorityIdx);
    public List<PriorityCategoryEntity> selectDownPriorityEntities(long userId, long pirorityIdx);
    
}
