package roadiary.behavior.record.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.record.dto.RecordResDto;
import roadiary.behavior.record.service.RecordsService;

@RestController
@RequiredArgsConstructor
public class RecordsRestController {
    
    private final RecordsService recordsService;

    @GetMapping("/behavior/records/{year}/{month}/{day}")
    public List<RecordResDto> getRecords(
            @PathVariable(value = "year", required = true) String year, 
            @PathVariable(value = "month", required = true) String month, 
            @PathVariable(value = "day", required = true) String day) {

        List<RecordResDto> recordResDtos = new ArrayList<>();

        return recordResDtos;
    }
    
}
