package roadiary.behavior.record.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.record.dto.RecordReqDto;
import roadiary.behavior.record.service.RecordsService;

@Controller
@RequiredArgsConstructor
public class RecordsController {

    private final RecordsService recordsService;
    
    @GetMapping("/behavior")
    public String directToSave() {
        return "behavior.html";
    }
    
    @PostMapping("/behavior")
    public String saveRecord(@ModelAttribute RecordReqDto recordReqDto) {

        // userId 세션에서 가져오기
        Long userId = 1L;

        // @@요청 데이터에서 데이터 타입 맞지 않을 경우 처리
        // @@겹치는 시간일 경우 처리
        
        boolean isItAdded = recordsService.addRecord(recordReqDto, userId);

        System.out.println(isItAdded);

        return "redirect:/behavior";
    }

    @GetMapping("/behavior/records")
    public String directToWatch() {
        return "behavior-records.html";
    }
}
