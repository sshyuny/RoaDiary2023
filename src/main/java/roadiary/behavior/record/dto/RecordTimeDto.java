package roadiary.behavior.record.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordTimeDto {
    
    private long userId;
    private LocalDateTime start;
    private LocalDateTime end;
    
    public static RecordTimeDto create(long userId, LocalDateTime start, LocalDateTime end) {
        RecordTimeDto recordTimeDto = new RecordTimeDto();
        recordTimeDto.setUserId(userId);
        recordTimeDto.setStart(start);
        recordTimeDto.setEnd(end);
        return recordTimeDto;
    }
}
