package roadiary.behavior.category.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter @Getter
public class CategoryEntity {
    
    //private long behavior_category_id;
    private long behaviorCategoryId;
    private String content;

    public static CategoryEntity of(String content) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setContent(content);
        return categoryEntity;
    }

}
