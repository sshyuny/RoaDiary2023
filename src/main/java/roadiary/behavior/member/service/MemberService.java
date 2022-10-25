package roadiary.behavior.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import roadiary.behavior.member.authority.Authority;
import roadiary.behavior.member.dto.MemberAuthorityDto;

@Service
public class MemberService {

    private Authority authority;

    public MemberService(Authority authority) {
        this.authority = authority;
    }
    
    public boolean isItExistMember() {
        // 이후 수정
        return true;
    }

    public void makeLoginStatus(HttpServletRequest request) {
        // 이후 수정
        MemberAuthorityDto memberAuthorityDto = new MemberAuthorityDto("temp-user");
        authority.makeLoginStatus(request, memberAuthorityDto);
    }

    public void destroyLoginStatus(HttpServletRequest request) {
        // 이후 수정
        authority.destroyLoginStatus(request);
    }
}
