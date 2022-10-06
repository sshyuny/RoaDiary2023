package roadiary.behavior.category.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;

import roadiary.behavior.category.entity.CategoryEntity;
import roadiary.behavior.category.entity.PriorityCategoryEntity;
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
    public int insertPriority(PriorityCategoryEntity priorityCategoryEntity) {
        int addedPriorityNum = categoryMapper.insertPriority(priorityCategoryEntity);
        return addedPriorityNum;
    }

    @Override
    public long selectNewCategoryId(String categoryContent) {
        Long newCategoryId = categoryMapper.selectNewCategoryId(categoryContent);

        if (newCategoryId == null) return 0;  // 새 카테고리일 경우
        else return newCategoryId;  // 이미 등록된 카테고리일 경우
    }

    @Override
    public int deletePriority(PriorityCategoryEntity priorityCategoryEntity) {
        return categoryMapper.deletePriority(priorityCategoryEntity);
    }

    @Override
    public Long selectCateogryIdFromPriority(PriorityCategoryEntity priorityCategoryEntity) {
        return categoryMapper.selectCateogryIdFromPriority(priorityCategoryEntity);
    }

    @Override
    public int updatePriority(PriorityCategoryEntity priorityCategoryEntity) {
        return categoryMapper.updatePriority(priorityCategoryEntity);
    }

    @Override
    public int countPriority(PriorityCategoryEntity priorityCategoryEntity) {
        return categoryMapper.countPriority(priorityCategoryEntity);
    }
}
