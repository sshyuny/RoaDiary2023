package snapshot.behavior.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import snapshot.behavior.category.dto.CategoryDTO;

@Mapper
public interface CategoryMapper {
    
    public List<CategoryDTO> selectCategoryList();
    
}
