package roadiary.behavior.category.repository;

import java.util.List;

import roadiary.behavior.category.dto.CategoryDTO;
import roadiary.behavior.category.dto.CategoryVO;

public interface CategoryRepository {
    
    public List<CategoryDTO> selectCategoryDTOs(Long userId);

    public int insertCategory(CategoryVO categoryVO);
}
