package snapshot.behavior.category.repository;

import java.util.List;

import snapshot.behavior.category.dto.CategoryDTO;

public interface CategoryRepository {
    
    public List<CategoryDTO> selectCategoryDTOList(Long userId);
    
}
