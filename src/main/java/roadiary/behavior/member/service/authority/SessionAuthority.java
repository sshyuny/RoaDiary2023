package roadiary.behavior.member.service.authority;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import roadiary.behavior.member.domain.dto.MemberAuthorityDto;

@Component
public class SessionAuthority implements Authority {
    
    @Override
    public boolean isItLoginStatus(HttpSession session) {

        if (session.getAttribute(SessionKeys.login) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void makeLoginSession(HttpSession session, MemberAuthorityDto memberAuthorityDto) {

        session.setAttribute(SessionKeys.login, memberAuthorityDto);
        session.setAttribute(SessionKeys.loginUserName, memberAuthorityDto.getNickname());
        session.setAttribute(SessionKeys.loginUserId, memberAuthorityDto.getUserId());
    }

    @Override
    public void destroyLoginSession(HttpSession session) {
        
        session.removeAttribute(SessionKeys.login);
        session.removeAttribute(SessionKeys.loginUserName);
        session.removeAttribute(SessionKeys.loginUserId);
    }

    @Override
    public String getLoginUserName(HttpSession session) {
        return String.valueOf(session.getAttribute(SessionKeys.loginUserName));
    }
}
