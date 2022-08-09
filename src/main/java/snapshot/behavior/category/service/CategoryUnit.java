package snapshot.behavior.category.service;

import java.util.ArrayList;
import java.util.List;

import snapshot.behavior.category.dto.CategoryDTO;

public class CategoryUnit {
    
    static public List<CategoryDTO> sortCategoryDTOListOrderByTime(List<CategoryDTO> list) {
        List<CategoryDTO> newList = new ArrayList<>(list);
        return newList;
    }
}
