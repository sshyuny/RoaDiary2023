package snapshot.behavior.category.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import snapshot.behavior.category.dto.CategoryDTO;
import snapshot.behavior.category.mapper.CategoryMapper;

@Component
@Primary
//@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<CategoryDTO> selectCategoryDTOList(Long userId) {
        
        List<CategoryDTO> categoryDTOList = categoryMapper.selectCategoryList();

        return categoryDTOList;
    }
}
