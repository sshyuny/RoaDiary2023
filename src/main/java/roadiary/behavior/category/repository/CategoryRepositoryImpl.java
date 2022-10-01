package roadiary.behavior.category.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.dto.CategoryDTO;
import roadiary.behavior.category.dto.CategoryReqDTO;
import roadiary.behavior.category.mapper.CategoryMapper;

@Component
@Primary
//@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<CategoryDTO> selectCategoryDTOs(Long userId) {
        
        List<CategoryDTO> categoryDTOList = categoryMapper.selectCategoryList(userId);
        return categoryDTOList;
    }

    @Override
    public int insertCategory(CategoryReqDTO categoryVO) {

        int addNum = categoryMapper.insertCategory(categoryVO);
        return addNum;
    }
}
