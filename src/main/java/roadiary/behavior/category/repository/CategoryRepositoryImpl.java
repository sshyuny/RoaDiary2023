package roadiary.behavior.category.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.domain.entity.CategoryEntity;
import roadiary.behavior.category.domain.entity.PriorityCategoryEntity;
import roadiary.behavior.category.repository.mapper.CategoryMapper;

@Component
@Primary
//@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<CategoryEntity> selectCategoryEntities(Long userId) {
        
        List<CategoryEntity> categoryDtoList = categoryMapper.selectCategoryEntities(userId);
        return categoryDtoList;
    }

    @Override
    public int insertCategory(CategoryEntity categoryEntity) {

        int addedNum = categoryMapper.insertCategory(categoryEntity);
        return addedNum;
    }

    @Override
    public int insertPriority(PriorityCategoryEntity priorityCategoryEntity) {
        int addedPriorityNum = categoryMapper.insertPriority(priorityCategoryEntity);
        return addedPriorityNum;
    }

    @Override
    public Integer selectTheMaxPriority(long userId) {
        return categoryMapper.selectTheMaxPriority(userId);
    }

    @Override
    public Long selectCategoryByContent(String categoryContent) {
        return categoryMapper.selectCategoryByContent(categoryContent);
    }

    @Override
    public int deletePriority(long userId, long categoryId) {
        return categoryMapper.deletePriority(userId, categoryId);
    }

    @Override
    public int updatePriority(PriorityCategoryEntity priorityCategoryEntity) {
        return categoryMapper.updatePriority(priorityCategoryEntity);
    }

    @Override
    public int countPriority(long userId, long categoryId) {
        return categoryMapper.countPriority(userId, categoryId);
    }

    @Override
    public Integer selectPriorityIdx(long userId, long categoryId) {
        return categoryMapper.selectPriorityIdx(userId, categoryId);
    }

    @Override
    public List<PriorityCategoryEntity> selectUpPriorityEntities(long userId, long priorityIdx) { 
        return categoryMapper.selectUpPriorityEntities(userId, priorityIdx);
    }
    @Override
    public List<PriorityCategoryEntity> selectDownPriorityEntities(long userId, long priorityIdx) { 
        return categoryMapper.selectDownPriorityEntities(userId, priorityIdx);
    }
}
