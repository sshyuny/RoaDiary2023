package roadiary.behavior.record.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecordReqDto {
    
    private Long categoryId;
    
    private Integer startYear;
    private Integer startMonth;
    private Integer startDate;
    private Integer startHour;
    private Integer startMin;

    private Integer endYear;
    private Integer endMonth;
    private Integer endDate;
    private Integer endHour;
    private Integer endMin;

    private String detail;

}
