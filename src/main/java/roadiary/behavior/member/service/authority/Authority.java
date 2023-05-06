package roadiary.behavior.member.service.authority;

import javax.servlet.http.HttpSession;

import roadiary.behavior.member.domain.dto.MemberAuthorityDto;

public interface Authority {
    
    public boolean isItLoginStatus(HttpSession session);

    public void makeLoginSession(HttpSession session, MemberAuthorityDto memberAuthorityDto);

    public void destroyLoginSession(HttpSession session);

    public String getLoginUserName(HttpSession session);

}
