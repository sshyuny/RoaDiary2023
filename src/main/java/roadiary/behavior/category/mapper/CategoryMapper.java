package roadiary.behavior.category.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import roadiary.behavior.category.dto.CategoryDTO;
import roadiary.behavior.category.dto.CategoryReqDTO;

@Mapper
public interface CategoryMapper {
    
    public List<CategoryDTO> selectCategoryList(Long userId);

    public int insertCategory(CategoryReqDTO categoryVO);
    
}
