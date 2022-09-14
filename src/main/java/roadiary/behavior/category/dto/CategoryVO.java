package roadiary.behavior.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CategoryVO {
    
    private long userId;
    private String categoryContent;
    
}
