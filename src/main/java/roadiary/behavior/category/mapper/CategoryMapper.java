package roadiary.behavior.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityOfCategoryEntity;

@Mapper
public interface CategoryMapper {
    
    public List<CategoryEntity> selectCategoryList(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);

    public PriorityOfCategoryEntity selectPriorityOfCategoryEntity(long userId);

    public int updatePriority(PriorityOfCategoryEntity priorityOfCategoryEntity);

    public Long selectNewCategoryId(String categoryContent);
    
}
