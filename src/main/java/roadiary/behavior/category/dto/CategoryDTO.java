package roadiary.behavior.category.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CategoryDTO {
    
    // CategoryVO 를 사용할 수 있으면 삭제해도 괜찮을듯
    private long behavior_category_id;
    private String content;

}
