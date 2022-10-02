package roadiary.behavior.member.authority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import roadiary.behavior.member.dto.MemberAuthorityDto;

@Component
public class SessionAuthority implements Authority {
    
    @Override
    public boolean isItLoginStatus(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionKeys.login) != null) return true;

        return false;
    }

    @Override
    public void makeLoginStatus(HttpServletRequest request, MemberAuthorityDto memberAuthorityDto) {

        HttpSession session = request.getSession();
        session.setAttribute(SessionKeys.login, memberAuthorityDto);
    }

    @Override
    public void destroyLoginStatus(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        session.removeAttribute(SessionKeys.login);
    }
}
