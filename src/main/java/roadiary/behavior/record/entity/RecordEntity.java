package roadiary.behavior.record.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class RecordEntity {
    
    private long recordsId;
    private long categoryId;
    private long userId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String detail;

    public static RecordEntity create(long categoryId, long userId, LocalDateTime startDateTime, LocalDateTime endDateTime, String detail) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setCategoryId(categoryId);
        recordEntity.setUserId(userId);
        recordEntity.setStartDateTime(startDateTime);
        recordEntity.setEndDateTime(endDateTime);
        recordEntity.setDetail(detail);
        return recordEntity;
    }

}
