package roadiary.behavior.category.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.mapper.CategoryMapper;

@Component
@Primary
//@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<CategoryEntity> selectCategoryDtos(Long userId) {
        
        List<CategoryEntity> categoryDtoList = categoryMapper.selectCategoryList(userId);
        return categoryDtoList;
    }

    @Override
    public int insertCategory(CategoryEntity categoryEntity) {

        int addedNum = categoryMapper.insertCategory(categoryEntity);
        return addedNum;
    }

    @Override
    public int countSavedCategoryNum(long userId) {
        int savedCategoryNum = categoryMapper.countSavedCategoryNum(userId);
        return savedCategoryNum;
    }
}
