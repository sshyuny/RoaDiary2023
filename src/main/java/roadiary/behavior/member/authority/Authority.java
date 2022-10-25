package roadiary.behavior.member.authority;

import javax.servlet.http.HttpServletRequest;

import roadiary.behavior.member.dto.MemberAuthorityDto;

public interface Authority {
    
    public boolean isItLoginStatus(HttpServletRequest request);

    public void makeLoginStatus(HttpServletRequest request, MemberAuthorityDto memberAuthorityDto);

    public void destroyLoginStatus(HttpServletRequest request);

    public String getLoginUserName(HttpServletRequest request);

}
