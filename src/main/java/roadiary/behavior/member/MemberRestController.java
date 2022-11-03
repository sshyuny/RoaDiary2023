package roadiary.behavior.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import roadiary.behavior.member.authority.SessionKeys;
import roadiary.behavior.member.dto.KakaoUserInfoResDto;
import roadiary.behavior.member.service.MemberService;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @GetMapping("/trylogin")
    public String directLoginResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 로그인 성공
        memberService.makeLoginStatus(request);

        HttpSession session = request.getSession();
        if (session.getAttribute(SessionKeys.afterLoginPage) != null) {
            String sesstionAfterLoginPage = session.getAttribute(SessionKeys.afterLoginPage).toString();
            session.setAttribute(SessionKeys.afterLoginPage, null);
            
            return sesstionAfterLoginPage;
        }

        return "";

        // 로그인 실패 추가하기
        
    }

    @GetMapping("/destroylogin")
    public void destroylogin(HttpServletRequest request) {

        // 로그아웃
        memberService.destroyLoginStatus(request);
    }

    @GetMapping("/oath/kakao")
    public void kakaoLogin(@RequestParam String code, HttpServletResponse response) throws IOException {
        String kakaoAccessToken = memberService.getKaKaoAccessToken(code);
        KakaoUserInfoResDto kakaoUserInfoResDto = memberService.getUserInfoByToken(kakaoAccessToken);
        System.out.println("카카오 로그인 성공 " + kakaoUserInfoResDto.getId() + " " + kakaoUserInfoResDto.getConnected_at());

        response.sendRedirect("/");
    }
    
}
