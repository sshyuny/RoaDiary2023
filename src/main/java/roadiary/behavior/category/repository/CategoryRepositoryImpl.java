package roadiary.behavior.category.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.mapper.CategoryMapper;
import roadiary.behavior.entity.CategoryEntity;

@Component
@Primary
//@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<CategoryEntity> selectCategoryDTOs(Long userId) {
        
        List<CategoryEntity> categoryDTOList = categoryMapper.selectCategoryList(userId);
        return categoryDTOList;
    }

    @Override
    public int insertCategory(CategoryEntity categoryEntity) {

        int addedNum = categoryMapper.insertCategory(categoryEntity);
        return addedNum;
    }
}
