package roadiary.behavior.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KakaoUserInfoResDto {
    
    private String id;
    private String has_signed_up;
    private String connected_at;
    private String synched_at;
    private String properties;
    private String kakao_account;

}
