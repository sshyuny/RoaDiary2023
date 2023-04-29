package roadiary.behavior.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import roadiary.behavior.member.service.MemberService;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    
    @GetMapping("/member")
    public String directToMember() {
        return "member.html";
    }

    /**
     * 사용자가 카카오 로그인 버튼을 눌렀을때 연결됩니다.
     * 카카오에 인가코드를 요청합니다.
     * @return
     */
    @GetMapping("/login/req/kakao")
    public String redirectToKakaoLoginUrl() {
        String kakaoLoginUrl = memberService.getKakaoLoginReqUrl();
        return "redirect:" + kakaoLoginUrl;
    }
}
