package roadiary.behavior.record.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import roadiary.behavior.record.dto.RecordReqDTO;

@Controller
public class RecordsController {
    
    @GetMapping("/behavior")
    public String directToSave() {
        return "behavior.html";
    }
    
    @PostMapping("/behavior")
    public String saveRecord(@ModelAttribute RecordReqDTO recordReqDTO) {
        // 저장하는 부분
        return "redirect:/behavior";
    }
}
