package roadiary.behavior.category.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class PriorityCategoryEntity {

    private long userId;
    private int priorityIdx;
    private long behaviorCategoryId;

    public static PriorityCategoryEntity of(long userId, int priorityIdx, long behaviorCategoryId) {
        PriorityCategoryEntity priorityCategoryEntity = new PriorityCategoryEntity();
        priorityCategoryEntity.setUserId(userId);
        priorityCategoryEntity.setPriorityIdx(priorityIdx);
        priorityCategoryEntity.setBehaviorCategoryId(behaviorCategoryId);
        return priorityCategoryEntity;
    }

}
