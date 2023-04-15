package roadiary.behavior.record.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.member.service.authority.SessionKeys;
import roadiary.behavior.record.dto.RecordModifyReqDto;
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
    @GetMapping("/behavior-quick")
    public String directToSaveQuick() {
        return "behavior-quick.html";
    }

    @GetMapping("/records/main")
    public String directToBehaviorMainShort() {
        return "records.html";
    }
    @GetMapping("/records/main/{year}/{month}/{day}")
    public String directToBehaviorMain(
            @PathVariable(value = "year", required = true) String year, 
            @PathVariable(value = "month", required = true) String month, 
            @PathVariable(value = "day", required = true) String day) {
        
        return "records.html";
    }
    @PostMapping("/records/main/{year}/{month}/{day}")
    public String modifyRecord(HttpServletRequest request, @ModelAttribute RecordModifyReqDto recordModifyReqDto, 
            @PathVariable(value = "year", required = true) String year, 
            @PathVariable(value = "month", required = true) String month, 
            @PathVariable(value = "day", required = true) String day) {

        // userId 세션에서 가져오기
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        return "redirect:/records/main/" + year + "/" + month + "/" + day;
    }
}
