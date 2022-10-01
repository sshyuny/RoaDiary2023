package roadiary.behavior.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roadiary.behavior.category.dto.CategoryDTO;
import roadiary.behavior.category.dto.CategoryResDTO;
import roadiary.behavior.category.dto.CategoryReqDTO;
import roadiary.behavior.category.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<CategoryResDTO> getCategoryList(Long userId) {

        List<CategoryDTO> categoryDTOList = categoryRepository.selectCategoryDTOs(userId);

        List<CategoryResDTO> sortedCategoryResDTOList = CategoryUnit.switchCategoryDTOToResDTO(categoryDTOList);

        return sortedCategoryResDTOList;
    }

    public int addCategory(String categoryContent) {
        
        CategoryReqDTO categoryVO = new CategoryReqDTO();
        categoryVO.setCategoryContent(categoryContent);

        int addNum = categoryRepository.insertCategory(categoryVO);
        System.out.println("categoryVO id = " + categoryVO.getId());
        return addNum;
    }
}
