package roadiary.behavior.record.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class RecordDTO {
    
    private long recordsId;
    private long categoryId;
    private long userId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String detail;

    public static RecordDTO create(long categoryId, long userId, LocalDateTime startDateTime, LocalDateTime endDateTime, String detail) {
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setCategoryId(categoryId);
        recordDTO.setUserId(userId);
        recordDTO.setStartDateTime(startDateTime);
        recordDTO.setEndDateTime(endDateTime);
        recordDTO.setDetail(detail);
        return recordDTO;
    }

}
