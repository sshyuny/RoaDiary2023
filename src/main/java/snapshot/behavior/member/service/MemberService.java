package snapshot.behavior.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import snapshot.behavior.member.authority.Authority;
import snapshot.behavior.member.dto.MemberAuthorityDTO;

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
        MemberAuthorityDTO memberAuthorityDTO = new MemberAuthorityDTO("name");
        authority.makeLoginStatus(request, memberAuthorityDTO);
    }

    public void destroyLoginStatus(HttpServletRequest request) {
        // 이후 수정
        authority.destroyLoginStatus(request);
    }
}
