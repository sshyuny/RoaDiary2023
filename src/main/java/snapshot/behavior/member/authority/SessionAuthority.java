package snapshot.behavior.member.authority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import snapshot.behavior.member.dto.MemberAuthorityDTO;

@Component
public class SessionAuthority implements Authority {
    
    @Override
    public boolean isItLoginStatus(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute("login") != null) return true;

        return false;
    }

    @Override
    public void makeLoginStatus(HttpServletRequest request, MemberAuthorityDTO memberAuthorityDTO) {

        HttpSession session = request.getSession();
        session.setAttribute("login", memberAuthorityDTO);
    }
}
