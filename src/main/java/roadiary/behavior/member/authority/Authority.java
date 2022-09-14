package roadiary.behavior.member.authority;

import javax.servlet.http.HttpServletRequest;

import roadiary.behavior.member.dto.MemberAuthorityDTO;

public interface Authority {
    
    public boolean isItLoginStatus(HttpServletRequest request);

    public void makeLoginStatus(HttpServletRequest request, MemberAuthorityDTO memberAuthorityDTO);

    public void destroyLoginStatus(HttpServletRequest request);

}
