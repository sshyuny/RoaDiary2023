package roadiary.behavior.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class MemberAuthorityDto {
    
    private long userId;
    private String nickname;

    public static MemberAuthorityDto of(long userId, String nickname) {
        MemberAuthorityDto memberAuthorityDto = new MemberAuthorityDto();
        memberAuthorityDto.setUserId(userId);
        memberAuthorityDto.setNickname(nickname);
        return memberAuthorityDto;
    }

}
