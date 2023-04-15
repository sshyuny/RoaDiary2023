package roadiary.behavior.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import roadiary.behavior.member.domain.dto.KakaoUserInfoResDto;
import roadiary.behavior.member.service.MemberService;
import roadiary.behavior.member.service.authority.SessionKeys;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    // 방문자용 로그인
    @GetMapping("/trylogin")
    public String directLoginResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        memberService.makeLoginStatus(session);

        if (session.getAttribute(SessionKeys.afterLoginPage) != null) {
            String sesstionAfterLoginPage = session.getAttribute(SessionKeys.afterLoginPage).toString();
            session.setAttribute(SessionKeys.afterLoginPage, null);
            
            return sesstionAfterLoginPage;
        }

        return "";
    }

    @GetMapping("/destroylogin")
    public void destroylogin(HttpServletRequest request) {

        // 로그아웃
        memberService.destroyLoginStatus(request);
    }

    @GetMapping("/oauth/kakao")
    public void kakaoLogin(@RequestParam String code, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String kakaoAccessToken = memberService.getKaKaoAccessToken(code);
        KakaoUserInfoResDto kakaoUserInfoResDto = memberService.getUserInfoByToken(kakaoAccessToken);

        HttpSession session = request.getSession();

        long kakaoId = kakaoUserInfoResDto.getId();
        if (memberService.isItRegisteredMember(kakaoId)) {
            String status = memberService.makeLoginStatus(session, kakaoId);
            if (status.equals("withdrawal")) response.sendRedirect("/?alert=withdrawal");
            else response.sendRedirect("/");
        } else {
            memberService.addKakaoUser(kakaoUserInfoResDto);
            memberService.makeLoginStatus(session, kakaoId);
            // 닉네임 등록 후 메인으로
            response.sendRedirect("/");
        }
    }

    @DeleteMapping("/api/member/withdrawal")
    public String withdrawal(HttpServletRequest request) {

        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());

        if (userId == 1L) return "guestAccount";
        else {
            int withdrawedNum = memberService.withdrawalUser(userId);
            memberService.destroyLoginStatus(request);
            if(withdrawedNum == 1) return "success";
            else return "fail";
        }
    }
    
}
