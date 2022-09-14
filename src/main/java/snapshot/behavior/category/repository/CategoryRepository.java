package snapshot.behavior.category.repository;

import java.util.List;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.dto.CategoryVO;

public interface CategoryRepository {
    
    public List<CategoryDTO> selectCategoryDTOs(Long userId);

    public int insertCategory(CategoryVO categoryVO);
}
