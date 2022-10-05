package roadiary.behavior.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityCategoryEntity;

@Mapper
public interface CategoryMapper {
    
    public List<CategoryEntity> selectCategoryList(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);

    public int insertPriority(PriorityCategoryEntity priorityCategoryEntity);

    public Long selectNewCategoryId(String categoryContent);

    public int deletePriority(PriorityCategoryEntity priorityCategoryEntity);
    
}
