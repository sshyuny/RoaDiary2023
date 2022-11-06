package roadiary.behavior.record.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.member.authority.SessionKeys;
import roadiary.behavior.record.dto.RecordModifyReqDto;
import roadiary.behavior.record.dto.RecordReqDto;
import roadiary.behavior.record.dto.RecordResDto;
import roadiary.behavior.record.service.RecordsService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class RecordsRestController {
    
    private final RecordsService recordsService;

    @PostMapping("/api/behavior")
    public String saveRecord(HttpServletRequest request, @RequestBody RecordReqDto recordReqDto) {

        // userId 세션에서 가져오기
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        // @@요청 데이터에서 데이터 타입 맞지 않을 경우 처리
        // @@겹치는 시간일 경우 처리
        
        String addedStatus = recordsService.addRecord(recordReqDto, userId);

        return addedStatus;
    }

    @GetMapping("/api/records/manage/{year}/{month}/{day}")
    public List<RecordResDto> getRecords(
            HttpServletRequest request, 
            @PathVariable(value = "year", required = true) String year, 
            @PathVariable(value = "month", required = true) String month, 
            @PathVariable(value = "day", required = true) String day) {

        // userId 세션에서 가져오기
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        LocalDate reqDate = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        List<RecordResDto> recordResDtos = recordsService.getRecords(reqDate, userId);

        return recordResDtos;
    }

    @PutMapping(value="/api/records/manage/{year}/{month}/{day}")
    public void modifyRecord(
            HttpServletRequest request, 
            @PathVariable(value = "year", required = true) String year, 
            @PathVariable(value = "month", required = true) String month, 
            @PathVariable(value = "day", required = true) String day, 
            @RequestBody RecordModifyReqDto recordModifyReqDto) {

        // userId 세션에서 가져오기
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        int updatedNum = recordsService.modifyRecord(userId, recordModifyReqDto);

        //if (updatedNum == 0) @@적절하지 않은 값 요청됨. 예외처리 필요. (클라이언트가 behaviorRecordId를 임의로 변경 등)
    }

    @DeleteMapping(value="/api/records/manage/{year}/{month}/{day}")
    public void deleteRecord(
            HttpServletRequest request, 
            @PathVariable(value = "year", required = true) String year, 
            @PathVariable(value = "month", required = true) String month, 
            @PathVariable(value = "day", required = true) String day, 
            HttpEntity<String> httpEntity) {

        // userId 세션에서 가져오기
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        Long behaviorRecordsId = Long.valueOf(httpEntity.getBody());
        int deletedNum = recordsService.deleteRecord(userId, behaviorRecordsId);

        //if (deletedNum == 0) @@적절하지 않은 값 요청됨. 예외처리 필요. (클라이언트가 behaviorRecordId를 임의로 변경 등)

    }
    
}
