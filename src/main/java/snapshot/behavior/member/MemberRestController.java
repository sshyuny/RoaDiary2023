package snapshot.behavior.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import snapshot.behavior.member.service.MemberService;

@RestController
public class MemberRestController {

    private MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @GetMapping("/trylogin")
    public void directLoginResult(HttpServletRequest request) {
        
        // 로그인 성공
        memberService.makeLoginStatus(request);

        // 로그인 실패 추가하기
        
    }

    @GetMapping("/destroylogin")
    public void destroylogin(HttpServletRequest request) {

        // 로그아웃
        memberService.destroyLoginStatus(request);
    }
    
}
