package roadiary.behavior.record.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecordReqDto {
    
    private long categoryId;
    private Integer startYear;
    private Integer startMonth;
    private Integer startDate;
    private int startHour;
    private int startMin;
    private LocalDate endDate;
    private int endHour;
    private int endMin;
    private String detail;

}
