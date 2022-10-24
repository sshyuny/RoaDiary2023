package roadiary.behavior.record.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.record.dto.RecordModifyReqDto;
import roadiary.behavior.record.dto.RecordResDto;
import roadiary.behavior.record.service.RecordsService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class RecordsRestController {
    
    private final RecordsService recordsService;

    @GetMapping("/behavior/records/{year}/{month}/{day}")
    public List<RecordResDto> getRecords(
            @PathVariable(value = "year", required = true) String year, 
            @PathVariable(value = "month", required = true) String month, 
            @PathVariable(value = "day", required = true) String day) {

        // userId 세션에서 가져오기
        Long userId = 1L;

        LocalDate reqDate = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        List<RecordResDto> recordResDtos = recordsService.getRecords(reqDate, userId);

        return recordResDtos;
    }

    @PutMapping(value="/behavior/records/{year}/{month}/{day}")
    public void putMethodName(@PathVariable(value = "year", required = true) String year, 
            @PathVariable(value = "month", required = true) String month, 
            @PathVariable(value = "day", required = true) String day, 
            @RequestBody RecordModifyReqDto recordModifyReqDto) {

        System.out.println(recordModifyReqDto.getBehaviorCategoryId());
    }
    
}
