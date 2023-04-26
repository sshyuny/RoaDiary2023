package roadiary.behavior.category.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.domain.entity.PriorityCategoryEntity;

@Mapper
public interface CategoryMapper {
    
    public List<CategoryEntity> selectCategoryEntities(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);

    public int insertPriority(PriorityCategoryEntity priorityCategoryEntity);

    public Integer selectTheMaxPriority(long userId);

    public Long selectCategoryIdByContent(String categoryContent);

    public PriorityCategoryEntity selectPriorityCategoryByUserIdAndCategoryId(@Param("userId") long userId, @Param("categoryId") long categoryId);

    public int deletePriority(@Param("userId") long userId, @Param("categoryId") long categoryId);

    public int updatePriority(PriorityCategoryEntity priorityCategoryEntity);

    public int countPriority(@Param("userId") long userId, @Param("categoryId") long categoryId);

    public Integer selectPriorityIdx(@Param("userId") long userId, @Param("categoryId") long categoryId);

    public List<PriorityCategoryEntity> selectUpPriorityEntities(@Param("userId") long userId, @Param("priorityIdx") long priorityIdx);
    public List<PriorityCategoryEntity> selectDownPriorityEntities(@Param("userId") long userId, @Param("priorityIdx") long priorityIdx);
    
}
