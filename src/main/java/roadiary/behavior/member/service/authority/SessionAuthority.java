package roadiary.behavior.member.service.authority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import roadiary.behavior.member.domain.dto.MemberAuthorityDto;

@Component
public class SessionAuthority implements Authority {
    
    @Override
    public boolean isItLoginStatus(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionKeys.login) != null) return true;

        return false;
    }

    @Override
    public void makeLoginStatus(HttpSession session, MemberAuthorityDto memberAuthorityDto) {

        session.setAttribute(SessionKeys.login, memberAuthorityDto);
        session.setAttribute(SessionKeys.loginUserName, memberAuthorityDto.getNickname());
        session.setAttribute(SessionKeys.loginUserId, memberAuthorityDto.getUserId());
    }

    @Override
    public void destroyLoginStatus(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        session.removeAttribute(SessionKeys.login);
        session.removeAttribute(SessionKeys.loginUserName);
        session.removeAttribute(SessionKeys.loginUserId);
    }

    @Override
    public String getLoginUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute(SessionKeys.loginUserName));
    }
}
