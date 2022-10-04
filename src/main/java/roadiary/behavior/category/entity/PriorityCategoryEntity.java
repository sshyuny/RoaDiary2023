package roadiary.behavior.category.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class PriorityCategoryEntity {
    
    private long user_id;
    private int priority_idx;
    private long behavior_category_id;

}
