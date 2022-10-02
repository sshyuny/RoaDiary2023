package roadiary.behavior.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import roadiary.behavior.category.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {
    
    public List<CategoryEntity> selectCategoryList(Long userId);

    public int insertCategory(CategoryEntity categoryEntity);

    public int countSavedCategoryNum(long userId);
    
}
