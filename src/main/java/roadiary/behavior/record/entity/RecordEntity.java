package roadiary.behavior.record.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class RecordEntity {
    
    private long behaviorRecordsId;
    private long behaviorCategoryId;
    private long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String detail;

    public static RecordEntity of(long categoryId, long userId, LocalDateTime startTime, LocalDateTime endTime, String detail) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setBehaviorCategoryId(categoryId);
        recordEntity.setUserId(userId);
        recordEntity.setStartTime(startTime);
        recordEntity.setEndTime(endTime);
        recordEntity.setDetail(detail);
        return recordEntity;
    }

    public static RecordEntity of(long behaviorRecordsId, long categoryId, long userId, LocalDateTime startTime, LocalDateTime endTime, String detail) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setBehaviorRecordsId(behaviorRecordsId);
        recordEntity.setBehaviorCategoryId(categoryId);
        recordEntity.setUserId(userId);
        recordEntity.setStartTime(startTime);
        recordEntity.setEndTime(endTime);
        recordEntity.setDetail(detail);
        return recordEntity;
    }

}
