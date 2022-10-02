package roadiary.behavior.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roadiary.behavior.category.dto.CategoryResDTO;
import roadiary.behavior.category.repository.CategoryRepository;
import roadiary.behavior.entity.CategoryEntity;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<CategoryResDTO> getCategoryList(Long userId) {

        List<CategoryEntity> categoryEntities = categoryRepository.selectCategoryDTOs(userId);
        List<CategoryResDTO> categoryResDTOs = CategoryUnit.fromEntityToResDTO(categoryEntities);

        return categoryResDTOs;
    }

    public int addCategory(String categoryContent) {
        
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setContent(categoryContent);

        int addedNum = categoryRepository.insertCategory(categoryEntity);
        System.out.println("categoryEntity id = " + categoryEntity.getBehavior_category_id());
        
        return addedNum;
    }
}
