package roadiary.behavior.member.authority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import roadiary.behavior.member.dto.MemberAuthorityDto;

public interface Authority {
    
    public boolean isItLoginStatus(HttpServletRequest request);

    public void makeLoginStatus(HttpSession session, MemberAuthorityDto memberAuthorityDto);

    public void destroyLoginStatus(HttpServletRequest request);

    public String getLoginUserName(HttpServletRequest request);

}
