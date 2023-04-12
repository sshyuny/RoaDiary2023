package roadiary.behavior.category.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter @Getter
public class CategoryEntity {
    
    private long behaviorCategoryId;
    private String content;

    public static CategoryEntity of(String content) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setContent(content);
        return categoryEntity;
    }

}
